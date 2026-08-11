import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone

cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
cal.add(Calendar.MONTH, -6);

sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

result = sdf.format(cal.getTime());
LOG.info('6 months ago: ' + result);
return result;
