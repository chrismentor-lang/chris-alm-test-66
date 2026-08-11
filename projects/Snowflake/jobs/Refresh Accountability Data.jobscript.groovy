tz = TimeZone.getTimeZone("Australia/Sydney");

st = API.getJobState();
def id = st.getExecutionId();
def idstring = id.toString()+'_${vAccountabilityEndpoint}';
def job = 'Accountability_${vAccountabilityEndpoint}';
API.setProperty("vJobID", idstring);
LOG.info('vJobID: '+idstring);

state = API.executeLoad("dJobExecution_200_020_loadJobID_ld");

//def start = state.getStartDate ();
def start = new Date();
def startfrm = start.format('yyyy-MM-dd hh:mm:ss',timezone=tz);
LOG.info('Start: '+startfrm);

def desc = 'Accountability_${vAccountabilityEndpoint} refreshed at '+startfrm; // Please update this with a Brief Description of the job
def vMonth = '';
def cubes = 'Snowflake Accountability_${vAccountabilityEndpoint}'; // Please define the cubes here that will be affected by this job (Refer to the cubes dimesnion to get the exact element name

def user = state.getUserName();
LOG.info('User: '+user);

IDatabase db = OLAP.getDatabase("cgLocal_statThinkerbell_jc"); //20251204: Changed from JDX DBG to cjJedoxOlap_varDB_jc
ICube cube = db.getCubeByName("#_Job Execution");
String [] path1 = ["User",idstring,"~"];
String [] path2 = ["Timestamp",idstring,"~"];
String [] path4 = ["Description",idstring,"~"];
String [] path5 = ["Job",idstring,"~"];
String [] path6 = ["Cubes",idstring,"~"];
IElement [][] elPaths = [cube.getCellPath(path1) , cube.getCellPath(path2) , cube.getCellPath(path4), cube.getCellPath(path5), cube.getCellPath(path6)];
Object [] values = [user,startfrm,desc,job,cubes];
cube.loadCells(elPaths, values, cube.getCellLoadContext(ICube.SplashMode.SPLASH_DEFAULT, 100, false, true),null);

// Update the section below to run your job with the required variables
	state = API.executeJob("Update Accountability Tables");

// Please do not update anything after this line

LOG.info('State: '+state);

def id2 = state.getExecutionId();
def idstring2 = id2.toString()+'_${vAccountabilityEndpoint}';
LOG.info('ID: '+idstring2);

def end = new Date();
def endfrm = end.format('yyyy-MM-dd hh:mm:ss', timezone=tz);
LOG.info('End: '+endfrm);

def status = state.getStatus ();
LOG.info('Status: '+status);

String [] path3 = ["Status",idstring,"~"];
String [] path7 = ["vMonth",idstring,"~"];
IElement [][] elPaths2 = [cube.getCellPath(path3), cube.getCellPath(path7)];
Object [] values2 = [status, vMonth];
cube.loadCells(elPaths2, values2, cube.getCellLoadContext(ICube.SplashMode.SPLASH_DEFAULT, 100, false, true),null);
LOG.info("Cube cells written: "+values2.length);
