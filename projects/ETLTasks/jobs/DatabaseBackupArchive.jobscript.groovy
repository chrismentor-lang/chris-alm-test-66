/* sample groovy backup script
 * -------------------------------
 *
 * Copyright (C) 2006-2016 Jedox AG
 * All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains
 * the property of Jedox AG and its suppliers, if any.
 * The intellectual and technical concepts contained herein are
 * proprietary to Jedox AG and its suppliers and may be covered by
 * the Federal Republic of Germany, patents in process, and are
 * protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Jedox AG.
 *
 * \author vladislav.malicevic@jedox.com
 *
 */

// path to backup directory - data directory will be used if empty
String backupDirPath = "${BackupDirPath}";

// definition of helper function for backing up single DB
def backupDb(db, backupDirPath){
      // get current date/time
    Date dateNow = new Date();
    String newDate = dateNow.format( 'yyyyMMdd_HHmm' );

    // get suffix for backup file
    String suffix = "_" + newDate + ".zip";
      String dbN = db.getName();
      String fileName = backupDirPath + dbN + suffix;
  
      if (dbN != "System")
      {
        LOG.info("Starting backup of database: '" + dbN + "'.");
        LOG.info("Trying to write filename: '" + fileName + "'.");
        try
        {
              db.backup(fileName );
              LOG.info("Finished backup of database: '" + dbN + "'.");
        }
        catch (pExc)
        {
              LOG.error(pExc);
        }
     }
}
  
IConnection conn = OLAP.getConnection("OlapTarget");
if (conn != null)
{
  LOG.info("_______________________________________________________________________________________________________________________________");
  for(IDatabase db:conn.getDatabases())
  {
    backupDb (db, backupDirPath);
  };
  LOG.info("_______________________________________________________________________________________________________________________________");
}
else
{
  LOG.error("No connection defined.");
}
