/*import java.text.SimpleDateFormat

DB = "cgLocal_statThinkerbellApplicationManagement_jc";
Cub = "#_Token";
String [] path = ["Valid Till", 'vAccountabilityRefreshToken', "~"];
IDatabase db = OLAP.getDatabase(DB);
ICube cube = db.getCubeByName(Cub);
IElement [] elPath = cube.getCellPath(path);
ICell cell = cube.getCell(elPath);
value = cell.getValue();
LOG.info ('Refresh Token is Valid Till: '+value);
def Calendar todayCal = Calendar.instance
today = todayCal.time;
SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String formattedDate = outputFormat.format(today);
LOG.info ('Time Now: '+formattedDate);
if (formattedDate<value){
  LOG.info ('No need to generate a new Refresh Token');
}
else {*/
  LOG.info ('Generating a new Refresh Token');
  API.executeLoad('dToken_000_020_addRefreshTokenAttr_dl');
//*}
