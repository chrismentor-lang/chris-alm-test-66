API.executeLoad("dAccountabilityEndpoint_302_010_attrIDs_fh");

def minuteStart = new Date().time;
def maxIterationsPerMinute = 100
def iterationCount = 0
LOG.info(minuteStart);

DB = "cgLocal_statThinkerbellApplicationManagement_jc";
Cub = "#_Accountability Endpoint";
String [] path = ["IDs", '${vAccountabilityEndpoint}', "~"];
IDatabase db = OLAP.getDatabase(DB);
ICube cube = db.getCubeByName(Cub);
IElement [] elPath = cube.getCellPath(path);
ICell cell = cube.getCell(elPath);
ids = cell.getValue();

def list = ids.split(",") as List

for (id in list){
	LOG.info(id);
  
	iterationCount++

    // Check if we reached 100 iterations
    if (iterationCount >= maxIterationsPerMinute) {

        def now = new Date().time
        def elapsed = now - minuteStart
        def remaining = 2000 - elapsed   // 1 minute = 60000 ms
		
	  	LOG.info("Sleeping");

        if (remaining > 0) {
            Thread.sleep(remaining)
        }

        // Reset counter and window
        iterationCount = 0
        minuteStart = new Date().time
    }
}
