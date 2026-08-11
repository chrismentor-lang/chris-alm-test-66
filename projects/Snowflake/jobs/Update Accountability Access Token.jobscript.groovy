import java.text.SimpleDateFormat

DB = "cgLocal_statThinkerbellApplicationManagement_jc";
Cub = "#_Token";
String [] path = ["Valid Till", 'vAccountabilityAccessToken', "~"];
IDatabase db = OLAP.getDatabase(DB);
ICube cube = db.getCubeByName(Cub);
IElement [] elPath = cube.getCellPath(path);
ICell cell = cube.getCell(elPath);
value = cell.getValue();
LOG.info ('Access Token is Valid Till: '+value);

def Calendar todayCal = Calendar.instance
today = todayCal.time;

SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

String formattedDate = outputFormat.format(today);
LOG.info ('Time Now: '+formattedDate);

if (formattedDate<value){
  LOG.info ('No need to generate a new Access Token');
}
else {
  LOG.info ('Generating a new Access Token');
  //Refresh Refresh Token
  API.executeJob("Update Accountability Refresh Token");
  
  // Set Access Token
  DB = "cgLocal_statThinkerbellApplicationManagement_jc";
  Cub = "#_Token";
  path = ["Token Value", "vAccountabilityRefreshToken", "~"];
  db = OLAP.getDatabase(DB);
  cube = db.getCubeByName(Cub);
  elPath = cube.getCellPath(path);
  cell = cube.getCell(elPath);
  vAccountabilityRefreshToken = cell.getValue();
  API.setProperty("vAccountabilityRefreshToken",vAccountabilityRefreshToken);
  API.executeLoad('dToken_100_020_addAccessTokenAttr_dl');
}
