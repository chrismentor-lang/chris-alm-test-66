Conn = "localhost";
DB = "Thinkerbell-Application-Management";
Cub = "#_Token";
String [] path = ["Token Value", "vAccountabilityRefreshToken", "~"];

IConnection con = OLAP.getGlobalConnection(Conn);
IDatabase db = con.getDatabaseByName(DB);
ICube cube = db.getCubeByName(Cub);
IElement [] elPath = cube.getCellPath(path);
ICell cell = cube.getCell(elPath);
Object value = cell.getValue();

return value;
