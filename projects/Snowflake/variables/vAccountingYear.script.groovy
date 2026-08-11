TimeZone.setDefault(TimeZone.getTimeZone('Australia/Sydney'));
def Calendar todayCal = Calendar.instance;
todayCal.clearTime();
todayCal.add(Calendar.DATE, -1);
today = todayCal.time;

year = today.format('YYY');

Integer sNumMonth = today.getAt(Calendar.MONTH)+1;

String sYear = (sNumMonth>6)?year.toInteger()+1:year;

Conn = "localhost";
DB = "Thinkerbell";
Cub = "#_Accounting Year";
String [] path = ["Accounting Year ID", sYear, "~"];

IConnection con = OLAP.getGlobalConnection(Conn);
IDatabase db = con.getDatabaseByName(DB);
ICube cube = db.getCubeByName(Cub);
IElement [] elPath = cube.getCellPath(path);
ICell cell = cube.getCell(elPath);
Object value = cell.getValue();

return value;
