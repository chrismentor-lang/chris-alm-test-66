//def String vFloatEndpoint = 'People';
//API.setProperty("vFloatEndpoint", vFloatEndpoint);

if ('${vFloatEndpoint}'=='Reports-People'||'${vFloatEndpoint}'=='Reports-Projects'){
  value = 1;
}
else {
  API.executeLoad("dFloatEndpoint_401_020_insPageCount_dl");
  DB = "cgLocal_statThinkerbellApplicationManagement_jc";
  Cub = "#_Float Endpoint";
  String [] path = ["Page Count", '${vFloatEndpoint}', "~"];
  IDatabase db = OLAP.getDatabase(DB);
  ICube cube = db.getCubeByName(Cub);
  IElement [] elPath = cube.getCellPath(path);
  ICell cell = cube.getCell(elPath);
  value = cell.getValue();
}

LOG.info('Page Count: '+value);

def Integer vFloatPage = 1;
while (vFloatPage <= value){
  API.setProperty("vFloatPage", vFloatPage.toString());
  if (vFloatPage == 1){
	API.setProperty("vFileLoadMode", "create");
	API.executeLoad("fFloatEndpoint_400_010_varLoadMode_fl");
  }
  else {
	API.setProperty("vFileLoadMode", "add");
	API.executeLoad("fFloatEndpoint_400_010_varLoadMode_fl");
  }
  vFloatPage++
}
