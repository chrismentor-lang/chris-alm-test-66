import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

// Parse the original timestamp
def originalTimestamp = _input1;//"2023-06-22 13:00:06.33";
def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
def dateTime = LocalDateTime.parse(originalTimestamp, formatter);

// Subtract 5.72 seconds
def newDateTime = dateTime.minus(_input2, ChronoUnit.MILLIS);

// Format the new timestamp
def newTimestamp = newDateTime.format(formatter);
if(newTimestamp[22..22]=="0")
   {
	 return newTimestamp[0..18];
   }
else
  return newTimestamp[0..18];
