os = new ByteArrayOutputStream()
API.setProperty("vHiBobConn", "stat");
API.setProperty("vHiBobPage", "1");

API.executeLoad("fHiBobEmploymentEndpoint_900_010_statLoadMode_fl");    // Load first page into CSV file
API.executeLoad("dHiBobEndpoint_500_020_addEmploymentCursor_dl");     // Add cursor to attribute
try (gzip = new java.util.zip.GZIPOutputStream(os)) 
{
  FILE.readBinary("cfHiBob_varHiBobEmploymentEndpoint_fc").transferTo(gzip);
}
FILE.writeBinary("cfHiBob_varHiBobEmploymentEndpointGZIP_fc", new ByteArrayInputStream(os.toByteArray()));

// Read cursor from dimension
DB = "cgLocal_statThinkerbellApplicationManagement_jc";
Cub = "#_Hi Bob Endpoint";
String [] path = ["Cursor", 'Employment', "~"];
IDatabase db = OLAP.getDatabase(DB);
ICube cube = db.getCubeByName(Cub);
IElement [] elPath = cube.getCellPath(path);
ICell cell = cube.getCell(elPath);
nextCursor = cell.getValue();
LOG.info('First cursor: ' + nextCursor);


API.setProperty("vHiBobCursorEmployment", nextCursor);
int vPage = 1;

// Run loop until next cursor becomes null
while (nextCursor != null) {
	vPage ++;
	API.setProperty("vHiBobConn", "var");
	API.setProperty("vHiBobPage", vPage.toString()); 
	API.executeLoad("fHiBobEmploymentEndpoint_900_010_statLoadMode_fl");    // Load next page into CSV file
	API.executeLoad("dHiBobEndpoint_500_020_addEmploymentCursor_dl");              // Update cursor to attribute
	try (gzip = new java.util.zip.GZIPOutputStream(os)) 
	{
	  FILE.readBinary("cfHiBob_varHiBobEmploymentEndpoint_fc").transferTo(gzip);
	}
	FILE.writeBinary("cfHiBob_varHiBobEmploymentEndpointGZIP_fc", new ByteArrayInputStream(os.toByteArray()));
  	String [] path2 = ["Cursor", 'Employment', "~"];
	IElement [] elPath2 = cube.getCellPath(path2);
	ICell cell2 = cube.getCell(elPath2);
	nextCursor = cell2.getValue();
	LOG.info('Next cursor: ' + nextCursor);
};

API.executeLoad("fHiBobEmploymentJson_900_000_statLoadMode_fl");       // create merged json file for Employment
API.executeLoad("fHiBobMergedEmployment_800_000_statLoadMode_fl");     // create csv load for final merged Employment csv
