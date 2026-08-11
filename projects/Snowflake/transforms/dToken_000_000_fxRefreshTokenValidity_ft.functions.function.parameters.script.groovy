import java.text.SimpleDateFormat
def Calendar todayCal = Calendar.instance

today = todayCal.time;
todayCal.add(Calendar.DATE,30);
valid_till = todayCal.time;

SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

String formattedDate = outputFormat.format(valid_till);

String sYear = today.getAt(Calendar.YEAR);
String sDate = today.getAt(Calendar.DATE);
String sMonth = today.getAt(Calendar.MONTH)+1;
//return sYear + '-' + sMonth + '-' + sDate;
return formattedDate;
