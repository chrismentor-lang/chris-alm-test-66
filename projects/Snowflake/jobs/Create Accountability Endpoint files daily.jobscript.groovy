if ('${vAccountabilityEndpoint}'=='EstimateEntries' || '${vAccountabilityEndpoint}'=='JournalEntries') {
  DB = "cgLocal_statThinkerbellApplicationManagement_jc";
  Cub = "#_Accountability Endpoint";
  if ('${vAccountabilityEndpoint}'=='EstimateEntries'){
	String[] path = ["Total Records", 'Estimates', "~"] as String[];
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
  
  if (entries > 0){
	dir = FILE.getFileInfo("cfAccountability_varAccountabilityEndpoint_fc");
  	if(dir.exists())
  	{
		FILE.delete("cfAccountability_varAccountabilityEndpoint_fc");
  	}
  
  	// Creating a new file for the latest data
  	API.setProperty("vFileLoadMode", "add");
  	API.executeJob("Create Accountability Estimate and Journal Entries File");
	Thread.sleep(60000);
  }
  else {
	LOG.info('No Records to be Loaded');
	API.executeLoad("fAccountabilityBlank_300_011_varEndpoint_fl");
  }
}
else {
  DB = "cgLocal_statThinkerbellApplicationManagement_jc";
  Cub = "#_Accountability Endpoint";
  String[] path = ["Metadata", '${vAccountabilityEndpoint}', "~"] as String[];
  db = OLAP.getDatabase(DB);
  cube = db.getCubeByName(Cub);
  elPath = cube.getCellPath(path);
  cell = cube.getCell(elPath);
  metadata = cell.getValue();
  
  if (!metadata){
    totalRecords = 1;
  }
  else {
    
    API.executeLoad("dAccountabilityEndpoint_301_010_insTotalRecords_dl");
    DB = "cgLocal_statThinkerbellApplicationManagement_jc";
    Cub = "#_Accountability Endpoint";
    path = ["Total Records", '${vAccountabilityEndpoint}', "~"] as String[];
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
  
  // Purpose: rolling-window rate limiter, never exceed MAX_CALLS in any WINDOW_MS span
  def MAX_CALLS = 97
  def WINDOW_MS = 60000L                     // 1 minute; bump to 61000 if you want clock-skew margin
  def callTimes = new ArrayDeque<Long>()     // timestamps of recent calls, oldest at head
  callTimes.addLast(new Date().time)        // added timestamp for the insert records call
  
  while (Records <= totalRecords){
    
    // Purpose: drop any timestamps that have aged out of the rolling window
    def now = new Date().time
    while (!callTimes.isEmpty() && (now - callTimes.peekFirst()) >= WINDOW_MS) {
        callTimes.pollFirst()
    }
    
    // Purpose: if the window is full, wait until the oldest call ages out
    if (callTimes.size() >= MAX_CALLS) {
        def sleepMs = WINDOW_MS - (now - callTimes.peekFirst())
        if (sleepMs > 0) {                 // guard prevents the negative-timeout exception
            LOG.info("Rate limit guard: " + callTimes.size() + " calls in window, sleeping " + sleepMs + " ms")
            Thread.sleep(sleepMs)
        }
        // Purpose: re-purge after sleeping so the window reflects the new 'now'
        def afterSleep = new Date().time
        while (!callTimes.isEmpty() && (afterSleep - callTimes.peekFirst()) >= WINDOW_MS) {
            callTimes.pollFirst()
        }
    }
    
    // Set Offset
    API.setProperty("vAccountabilityOffset", Records.toString());
    if (Records == 0){
  		API.setProperty("vFileLoadMode", "create");
  		API.executeLoad("fAccountabilityEndpoint_300_010_varLoadMode_fl");
    }
    else {
  		API.setProperty("vFileLoadMode", "add");
  		API.executeLoad("fAccountabilityEndpoint_300_010_varLoadMode_fl");
    }
    callTimes.addLast(new Date().time)     // record after the call returns (conservative)
    LOG.info("Call issued. Window count = " + callTimes.size())
    
    Records = Records + 200;
  }
}
Thread.sleep(60000);
