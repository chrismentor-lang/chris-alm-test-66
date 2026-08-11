TimeZone.setDefault(TimeZone.getTimeZone('Australia/Sydney'));
def Calendar todayCal = Calendar.instance;
todayCal.clearTime();
todayCal.add(Calendar.DATE, -1);
today = todayCal.time;

month = today.format('MMMM');

Conn = "localhost";
DB = "Thinkerbell";
Cub = "#_Accounting Month";
String [] path = ["Accounting Month ID", month, "~"];

IConnection con = OLAP.getGlobalConnection(Conn);
IDatabase db = con.getDatabaseByName(DB);
ICube cube = db.getCubeByName(Cub);
IElement [] elPath = cube.getCellPath(path);
ICell cell = cube.getCell(elPath);
Object value = cell.getValue();

return value;
