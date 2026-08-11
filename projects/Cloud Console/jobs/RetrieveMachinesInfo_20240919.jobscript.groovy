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

//END OF TOKEN RETRIEVAL

//START OF FILE CREATION
String filename = "machines_record_data.csv";

API.setProperty('vLoadMode','create');
Source = API.initSource("tMachines_100_010_ListLoop_je");

while (Source.nextRow()) {
  id = Source.getColumnString("company_idx");LOG.info('Company IDX:'+id);
  HttpGet httpGet = new HttpGet();
  def url3 = "https://console.cloud.jedox.com/customer/list_machines2?company_idx="+id+"&csrf_token="+value2;

  try {
	httpGet.setURI(new URI(url3));
	HttpResponse response = httpClient.execute(httpGet);
	int statusCode = response.getStatusLine().getStatusCode();
	HttpEntity responseEntity = response.getEntity();

	if (statusCode >= 200 && statusCode < 300) {
	  String content = EntityUtils.toString(responseEntity);
	  LOG.info(responseEntity);	
	  def baseDirectory = './data/files/customer_logs/'; 
	  LOG.info(baseDirectory);//LOG.info(fileNameBal);
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
  API.executeLoad('tMachines_100_003_AddData_fl');
  API.setProperty('vLoadMode','add');
}
try {
  httpClient.close();
} catch (IOException e) {
  e.printStackTrace();
}
