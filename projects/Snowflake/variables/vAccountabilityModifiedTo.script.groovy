import java.text.SimpleDateFormat
import java.util.TimeZone

def sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
sdf.setTimeZone(TimeZone.getTimeZone("UTC"))
def timestamp = sdf.format(new Date())

return timestamp
