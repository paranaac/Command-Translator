# Command-Translator

@date 11/24/2015

CSCI511-01 - Operating Systems Project #3


There are only three user commands
• SEEK #: This seeks out a block. The # will be an integer from 0 to 99. The file position will be changed
to #. Example: SEEK 82 sets the file position to 82.

• READ #: This reads in a sequential set of blocks, incrementing the file position after every read. The #
will be an integer from 1 to the maximum value that does not exceed 100-File Position. You will not
need to worry about the user reading outside the end of the file. Example: READ 3 will read the current
file position, increment the file position, read the new file position, increment the file position, read that
file position, and increment the file position.

• WRITE # # #...: This writes a value to the current file position, incrementing the file position after every
write. There are multiple values given. Each one will be written, one at a time, each to the next block.
Example: WRITE 4 2 9 will write 4 to the current file position, increment the file position, write 2 to
that file position, increment the file position, write 9 to that file position, and increment the file position
again.

You will be given up to 32 user commands in a batch. You must do the following:

• Translate the user commands to hardware commands.

• Optimize the hardware commands to reduce latency while ensuring that the optimized commands are
still valid.
