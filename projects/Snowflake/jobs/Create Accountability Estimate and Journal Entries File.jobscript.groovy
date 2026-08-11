// Insert Iteration IDs to the dimension attribute
API.executeLoad("dAccountabilityEndpoint_302_010_attrIDs_fh");
//Thread.sleep(45000);
DB = "cgLocal_statThinkerbellApplicationManagement_jc";
Cub = "#_Accountability Endpoint";
String [] path = ["IDs", '${vAccountabilityEndpoint}', "~"];
IDatabase db = OLAP.getDatabase(DB);
ICube cube = db.getCubeByName(Cub);
IElement [] elPath = cube.getCellPath(path);
ICell cell = cube.getCell(elPath);
ids = cell.getValue();
def list = ids.split(",") as List

// Purpose: rolling-window rate limiter, never exceed MAX_CALLS in any WINDOW_MS span
def MAX_CALLS = 97
def WINDOW_MS = 60000L                     // 1 minute; bump to 61000 if you want clock-skew margin
def callTimes = new ArrayDeque<Long>()     // timestamps of recent calls, oldest at head

for (id in list){
	//LOG.info(id);
	API.setProperty("vAccountabilityEntry",id);

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

	API.executeLoad("fAccountabilityEndpoint_300_010_varLoadMode_fl");
	callTimes.addLast(new Date().time)     // record after the call returns (conservative)
	LOG.info("Call issued. Window count = " + callTimes.size())
}
