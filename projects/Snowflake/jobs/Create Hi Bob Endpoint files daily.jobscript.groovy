def vHiBobEndpoint = API.getProperty("vHiBobEndpoint");

if (vHiBobEndpoint == 'Salaries' || vHiBobEndpoint == 'Employment') {
	API.executeJob("Refresh Hi Bob Cursor Endpoints files daily");  
}
//else if (vHiBobEndpoint == 'PolicyTypes') {
//	API.executeJob("Loop HiBob Policy Type Endpoints to Refresh Data");  
//}
  else {
	API.executeLoad("fHiBobEndpoint_400_010_varLoadMode_fl");
};
