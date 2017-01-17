/**
 * @author Alejandro Parana
 * @date 11/24/2015
 * CSCI511-01 - Project 3
 */
import java.io.BufferedReader;
import java.io.FileReader;
public class CommandTranslator 
{
    public static void main(String[] args) throws Exception 
    {
        // Variables
        String TEST_FILE = "test.txt";  
        int track, sector, arm;    
        String line;
        String[] val;
        int instCount = 0;
        
        // File Reader
        try (BufferedReader bf = new BufferedReader(new FileReader(TEST_FILE))) {
            arm = 0; track = 0; sector = 0;
            while((line = bf.readLine()) != null)
            {
                line = line.trim();
                val = line.split(" ");
 
                if(val[0].equals("SEEK"))
                {
                    int trackSeek, sectorSeek;
                    
                    if (val[1].length() == 1)
                    {
                        trackSeek = 0;
                        sectorSeek = Integer.parseInt(val[1]);
                    }
                    else
                    {
                        String[] valArray = val[1].split("");
                        trackSeek = Integer.parseInt(valArray[0]);
                        sectorSeek =  Integer.parseInt(valArray[1]);
                    }
                    
                    if ((trackSeek - track) > 0)
                    {
                        arm = 1;
                        System.out.println("ARM " + arm);
                        instCount++;
                        track += arm;
                        sector = (sector + 1) % 10;

                        while((trackSeek - track) > 0)
                        {
                            track += arm;
                            System.out.println("IDLE");
                            instCount++;
                            sector = (sector + 1) % 10;

                            if (track < 0)
                            {
                                track = 0;
                                arm = 0;
                            }
                            if (track > 9)
                            {
                                track = 9;
                                arm = 0;
                            }
                        } 
                        arm = 0;
                        System.out.println("ARM " + arm);
                        instCount++;
                        sector = (sector + 1) % 10;
                    }
                  
                    else if ((trackSeek - track) < 0)
                    {
                        arm = -1;
                        System.out.println("ARM -1");
                        instCount++;
                        track += arm;
                        sector = (sector + 1) % 10;
                        
                        while((trackSeek - track) < 0)
                        {
                            track += arm;
                            System.out.println("IDLE");
                            instCount++;
                            sector = (sector + 1) % 10;
                            if(track<0)
                            {
                                track = 0;
                                arm = 0;
                            }
                            if(track > 9)
                            {
                                track = 9;
                                arm = 0;
                            }
                        }
                        arm = 0;
                        System.out.println("ARM " + arm); 
                        instCount++;
                        sector = (sector + 1) % 10;
                    }
                
                    while(sectorSeek != sector)
                    {
                        System.out.println("IDLE");
                        instCount++;
                        sector = (sector + 1) % 10;
                    }
                }
                
                if(val[0].equals("READ"))
                {
                    int readAmt = Integer.parseInt(val[1]);
                    while (readAmt > 0)
                    {
                        if (sector == 9)
                        {
                        	System.out.println("READ");
                        	instCount++;
                            sector = (sector  + 1) % 10;
                            if (--readAmt != 0)
                            {
                            	System.out.println("ARM 1");
                            	instCount++;
	                        	sector = (sector  + 1) % 10;
	                        	track++;
	                        	System.out.println("ARM 0");
	                        	instCount++;
	                        	sector = (sector  + 1) % 10;
                            }
                        	
                        	while (sector != 0)
                        	{
                        		System.out.println("IDLE");
                        		instCount++;
                        		sector = (sector  + 1) % 10;
                        	}
                        }
                        else
                        {
                          System.out.println("READ");
                          instCount++;
                          sector = (sector  + 1) % 10;
                          readAmt--;
                        }
                       
                        
                    }
                }
                
                
                if(val[0].equals("WRITE"))
                {
                    for(int i = 1; i < val.length; i++)
                    { 
                     	boolean written = false;
                        if (sector == 9)
                        {
                       	    int j = i;
                       	    if (++j < val.length-1)
                       	    {
                       	    	System.out.println("WRITE " + val[i]);
		                      	instCount++;
		                     	sector = (sector  + 1) % 10;
		                     	written = true;
                       	    	System.out.println("ARM 1");
                       	    	instCount++;
	                        	sector = (sector  + 1) % 10;
	                        	track++;
	                        	System.out.println("ARM 0");
	                        	instCount++;
	                        	sector = (sector  + 1) % 10;

		                        while (sector != 0)
	                        	{
	                        		System.out.println("IDLE");
	                        		instCount++;
	                        		sector = (sector  + 1) % 10;
	                        	}
                       	    }
                        }
                        if (!written)
                        {
                        	System.out.println("WRITE " + val[i]);
                      		instCount++;
                     		sector = (sector  + 1) % 10; 
                 		 }
                    }
                }
                
                if (val[0].contains("="))
                {
                    System.out.println("===============================");
                    arm = 0; track = 0; sector = 0;
                    if (instCount != 0)
                    {
                    	 System.out.println("==========" + "Time: " + instCount + "============");
                    	 instCount = 0;
                    }
                }
            }   
        }  
    }
}
    
