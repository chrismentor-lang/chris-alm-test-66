//Refresh Access Token
//API.executeJob("Update Accountability Access Token");

// Set Access Token
//DB = "cgLocal_statThinkerbellApplicationManagement_jc";
//Cub = "#_Token";
//String [] path = ["Token Value", "vAccountabilityAccessToken", "~"];
//IDatabase db = OLAP.getDatabase(DB);
//ICube cube = db.getCubeByName(Cub);
//IElement [] elPath = cube.getCellPath(path);
//ICell cell = cube.getCell(elPath);
//vAccountabilityAccessToken = cell.getValue();
//API.setProperty("vAccountabilityAccessToken",vAccountabilityAccessToken);

if ('${vAccountabilityEndpoint}'=='EstimateEntries' || '${vAccountabilityEndpoint}'=='JournalEntries') {
  DB = "cgLocal_statThinkerbellApplicationManagement_jc";
  Cub = "#_Accountability Endpoint";
  if ('${vAccountabilityEndpoint}'=='EstimateEntries'){
	path = ["Total Records", 'Estimates', "~"];
	db = OLAP.getDatabase(DB);
	cube = db.getCubeByName(Cub);
	elPath = cube.getCellPath(path);
	cell = cube.getCell(elPath);
	entries = cell.getValue();
  }
  else {
	String [] path1 = ["Total Records", 'GeneralLedgerTransactions', "~"];
	db = OLAP.getDatabase(DB);
	cube = db.getCubeByName(Cub);
	IElement [] elPath1 = cube.getCellPath(path1);
	ICell cell1 = cube.getCell(elPath1);
	entries1 = cell1.getValue();
	
	if (entries1 == null){
	  entries1 = 0;
	}
	
	String [] path2 = ["Total Records", 'Journals', "~"];
	IElement [] elPath2 = cube.getCellPath(path2);
	ICell cell2 = cube.getCell(elPath2);
	entries2 = cell2.getValue();
	
	if (entries2 == null){
	  entries2 = 0;
	}
	entries = entries1+entries2;
  }
  /*
  if (entries > 0){
	dir = FILE.getFileInfo("cfAccountability_varAccountabilityEndpoint_fc");
  	if(dir.exists())
  	{
		FILE.delete("cfAccountability_varAccountabilityEndpoint_fc");
  	}
  
  	// Creating a new file for the latest data
  	API.setProperty("vFileLoadMode", "add");
  	//API.executeJob("Create Accountability Estimate and Journal Entries File");
  }
  else {
	LOG.info('No Records to be Loaded');
	//API.executeLoad("fAccountabilityBlank_300_011_varEndpoint_fl");
  }*/
}
else {
  DB = "cgLocal_statThinkerbellApplicationManagement_jc";
  Cub = "#_Accountability Endpoint";
  path = ["Metadata", '${vAccountabilityEndpoint}', "~"];
  db = OLAP.getDatabase(DB);
  cube = db.getCubeByName(Cub);
  elPath = cube.getCellPath(path);
  cell = cube.getCell(elPath);
  metadata = cell.getValue();
  
  if (!metadata){
    totalRecords = 1;
  }
  else {
    //Refresh Access Token
    //API.executeJob("Update Accountability Access Token");
    
    // Set Access Token
    //DB = "cgLocal_statThinkerbellApplicationManagement_jc";
    //Cub = "#_Token";
    //path = ["Token Value", "vAccountabilityAccessToken", "~"];
    //db = OLAP.getDatabase(DB);
    //cube = db.getCubeByName(Cub);
    //elPath = cube.getCellPath(path);
    //cell = cube.getCell(elPath);
    //vAccountabilityAccessToken = cell.getValue();
    //API.setProperty("vAccountabilityAccessToken",vAccountabilityAccessToken);
    
    API.executeLoad("dAccountabilityEndpoint_301_010_insTotalRecords_dl");
    DB = "cgLocal_statThinkerbellApplicationManagement_jc";
    Cub = "#_Accountability Endpoint";
    path = ["Total Records", '${vAccountabilityEndpoint}', "~"];
    db = OLAP.getDatabase(DB);
    cube = db.getCubeByName(Cub);
    elPath = cube.getCellPath(path);
    cell = cube.getCell(elPath);
    totalRecords = cell.getValue();
  }
  
  if (totalRecords == null){
	totalRecords = 0;
  }
  
  LOG.info('Total Records: '+totalRecords);
  
  def Integer Records = 0;
  
  def minuteStart = new Date().time;
  def maxIterationsPerMinute = 95;
  def iterationCount = 0;
  
  while (Records <= totalRecords){
    //Refresh Access Token
    //API.executeJob("Update Accountability Access Token");
    
    // Set Access Token
    //DB = "cgLocal_statThinkerbellApplicationManagement_jc";
    //Cub = "#_Token";
    //path = ["Token Value", "vAccountabilityAccessToken", "~"];
    //db = OLAP.getDatabase(DB);
    //cube = db.getCubeByName(Cub);
    //elPath = cube.getCellPath(path);
    //cell = cube.getCell(elPath);
    //vAccountabilityAccessToken = cell.getValue();
    //API.setProperty("vAccountabilityAccessToken",vAccountabilityAccessToken);
    
    // Set Offset
    API.setProperty("vAccountabilityOffset", Records.toString());
    if (Records == 0){
  		API.setProperty("vFileLoadMode", "create");
  		API.executeLoad("fAccountabilityEndpoint_300_010_varLoadMode_fl_Sandbox");
    }
    else {
  		API.setProperty("vFileLoadMode", "add");
  		API.executeLoad("fAccountabilityEndpoint_300_010_varLoadMode_fl_Sandbox");
    }
    Records = Records + 200;
	
	iterationCount++;

    // Check if we reached 100 iterations
    if (iterationCount >= maxIterationsPerMinute) {

        def now = new Date().time
        def elapsed = now - minuteStart
        def remaining = 60000 - elapsed   // 1 minute = 60000 ms
		
	  	LOG.info("Reached more than 100 calls per minute");

        if (remaining > 0) {
            Thread.sleep(remaining)
        }

        // Reset counter and window
        iterationCount = 0
        minuteStart = new Date().time
    }
  }
}
