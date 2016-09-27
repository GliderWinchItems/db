README.txt
09/26/2015

jcanpoll.java

NOTES:

1. Socket connection
  Default: 127.0.0.1 32123
  Or, command line: IP Port

- Database: 
  location-
    %HOME/gitrepo/svn_common/trunk/db
  name-
    pcc

  Starting database server (manual)
  In above location:
    ./db_start

  If database ('pcc') directory not present-
    ./db_create
  
  Update database from .sql tables--
    ./db_updatej
  Note if this fails connection, try a second time.
  (For some reason the server start/connection sometimes
  works OK every-other-time, which maybe due to the script
  not pausing long enough for it to get started.)


2. The database is "read-only" so changes do not write
  back into the source datababse.  One can enter new
  hex CAN IDs for test purposes and these will not 
  affect the source database.
  
3. DLC 
  default is zero.
  Set by typing in decimal number into the field

- PAYLOAD
  Entry fields all load into a long which is then
    converted to bytes when the msg (String) is prepared
    (adding sequence and checksum) for sending.

    The payload bytes beyond the number in the dlc 
    are dropped by the sending of the msg (which is
    controlled by the dlc).


