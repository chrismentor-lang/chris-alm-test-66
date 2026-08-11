import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URI;
import java.net.URISyntaxException;

//DETERMINE DAY FOR FROM AND TO
tz = TimeZone.getTimeZone("Australia/Sydney");
today = new Date();
to = today.format('yyyy-MM-dd',timezone=tz);
API.setProperty('vTo',to);
LOG.info('Today/To is: '+to);
yesterday = new Date()-1;
from = yesterday.format('yyyy-MM-dd',timezone=tz);
API.setProperty('vFrom',from);
LOG.info('Yesterday/From was: '+from);

//START OF LOGIN
String loginUrl = "https://console.cloud.jedox.com/user/login";
String email = "jedox.console@minerva.com.au";
String password = "kT@R6K3tRXJC*Oq";
String token = "ABC";
LOG.info(loginUrl);
// Create the request body as a JSON object
String requestBody = "email=" + email + "&password="+ password + "&csrf_token="+ token;

CloseableHttpClient httpClient = HttpClients.createDefault();
HttpPost httpPost = new HttpPost(loginUrl);

// Set the request body
StringEntity entity = new StringEntity(requestBody, ContentType.APPLICATION_FORM_URLENCODED);
httpPost.setEntity(entity);

try {
  HttpResponse response = httpClient.execute(httpPost);
  int statusCode = response.getStatusLine().getStatusCode();
  HttpEntity responseEntity = response.getEntity();

  if (statusCode >= 200 && statusCode < 300) {
	String responseText = EntityUtils.toString(responseEntity);
	LOG.info(responseText);
  } else {

	System.out.println("Login failed. Response code: " + statusCode);
	String responseText = EntityUtils.toString(responseEntity);
	System.out.println("Error response: " + responseText);
  }
} catch (IOException e) {
  e.printStackTrace();
} 
///END OF LOGIN

//START OF TOKEN RETRIEVAL
HttpGet httpGet2 = new HttpGet();
def url2 = "https://console.cloud.jedox.com/?view=console";

try {
  httpGet2.setURI(new URI(url2));
  HttpResponse response = httpClient.execute(httpGet2);
  int statusCode = response.getStatusLine().getStatusCode();
  HttpEntity responseEntity = response.getEntity();

  if (statusCode >= 200 && statusCode < 300) {
	String content = EntityUtils.toString(responseEntity);
	LOG.info(responseEntity);
	LOG.info("content:.  "+content);

	String value2 = null;
	String pattern = "<input\\s+type=\"hidden\"\\s+id=\"unique_token\"\\s+value=\"([^\"]+)\"";
	java.util.regex.Pattern regexPattern = java.util.regex.Pattern.compile(pattern);
	java.util.regex.Matcher matcher = regexPattern.matcher(content);

	if (matcher.find()) {
	  value2 = matcher.group(1);
	  LOG.info('This is the token value: '+value2);
	  API.setProperty('vToken',value2);
	}

  } else {
	LOG.info("Request failed. Response code: " + statusCode);
  }
} catch (IOException e) {
  e.printStackTrace();
}

value2 = API.getProperty('vToken');
LOG.info('vToken: '+value2);

schema = 'LOGS';
API.setProperty('vSchema',schema);
LOG.info('Schema: '+schema);
service = '2';
errortype = '0,1,2,3,4,5';
sortorder = 'asc';
limit = API.getProperty('vLimit');
API.setProperty('vStage','PUBLIC.JEDOX_STAGE');

//END OF TOKEN RETRIEVAL

//START OF FILE CREATION
API.setProperty('vTableSnowflake','RPC');
table = API.getProperty('vTableSnowflake');
String filename = table+"_raw.csv";
HttpGet httpGet = new HttpGet();

mode = API.setProperty('vLoadMode','create');

Source = API.initSource("tLog_000_200_MachineDateHoursLists_tv");

while (Source.nextRow()) {
  machineid = Source.getColumnString("NAMESPACE");
  timefrom = Source.getColumnString("TimeFrom");
  timeto = Source.getColumnString("TimeTo");
  API.setProperty('vMachineID', machineid);
  LOG.info('Machine ID:'+machineid);
  LOG.info('Time From:'+timefrom);
  LOG.info('Time To:'+timeto);

  def url3 = "https://console.cloud.jedox.com/user/download_log?machine_id="+machineid+"&from="+timefrom+"&to="+timeto+"&service="+service+"&error_type="+errortype+"&search_input=&sort_order="+sortorder+"&limit="+limit+"&csrf_token="+value2;

  try {
	httpGet.setURI(new URI(url3));
	HttpResponse response = httpClient.execute(httpGet);
	int statusCode = response.getStatusLine().getStatusCode();
	HttpEntity responseEntity = response.getEntity();

	if (statusCode >= 200 && statusCode < 300) {
	  String content = EntityUtils.toString(responseEntity);
	  LOG.info(responseEntity);	
	  def baseDirectory = './data/files/customer_logs/'; 
	  LOG.info(baseDirectory);
	  File directoryBal = new File(baseDirectory)
	  if (! directoryBal.exists()){
		directoryBal.mkdirs();
	  }
	  FileWriter writer = new FileWriter(baseDirectory+filename);
	  writer.write(content);
	  writer.close();

	} else {
	  LOG.info("Request failed. Response code: " + statusCode);
	}
  } catch (IOException e) {
	e.printStackTrace();
  }
  API.executeLoad('fLog_000_040_Transformed_fl');
  mode = API.setProperty('vLoadMode','add');
}

API.executeJob('LoadToSnowflakeLogInsert');

try {
  httpClient.close();
} catch (IOException e) {
  e.printStackTrace();
}
