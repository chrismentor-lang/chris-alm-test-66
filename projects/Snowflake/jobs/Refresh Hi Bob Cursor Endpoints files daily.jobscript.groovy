os = new ByteArrayOutputStream()
API.setProperty("vHiBobConn", "stat");
API.setProperty("vHiBobPage", "1");

API.executeLoad("fHiBob_900_010_createEndpoint_fl");    // Load first page into CSV file
API.executeLoad("dHiBobEndpoint_500_020_addCursor_dl");     // Add cursor to attribute

// Read cursor from dimension
DB = "cgLocal_statThinkerbellApplicationManagement_jc";
Cub = "#_Hi Bob Endpoint";
String [] path = ["Cursor", '${vHiBobEndpoint}', "~"];
IDatabase db = OLAP.getDatabase(DB);
ICube cube = db.getCubeByName(Cub);
IElement [] elPath = cube.getCellPath(path);
ICell cell = cube.getCell(elPath);
nextCursor = cell.getValue();
LOG.info('First cursor: ' + nextCursor);


API.setProperty("vHiBobCursor", nextCursor);
int vPage = 1;

// Run loop until next cursor becomes null
while (nextCursor != null) {
	vPage ++;
	API.setProperty("vHiBobConn", "var");
	API.setProperty("vHiBobPage", vPage.toString()); 
	API.executeLoad("fHiBob_900_010_createEndpoint_fl");    // Load next page into CSV file
	API.executeLoad("dHiBobEndpoint_500_020_addCursor_dl");              // Update cursor to attribute
	String [] path2 = ["Cursor", '${vHiBobEndpoint}', "~"];
	IElement [] elPath2 = cube.getCellPath(path2);
	ICell cell2 = cube.getCell(elPath2);
	nextCursor = cell2.getValue();
	LOG.info('Next cursor: ' + nextCursor);
    API.setProperty("vHiBobCursor", nextCursor); 
};

API.executeLoad("fHiBobJson_900_000_statLoadMode_fl");       // create merged json file for salaries
API.executeLoad("fHiBobMerged_800_000_statLoadMode_fl");     // create csv load for final merged salaries csv
