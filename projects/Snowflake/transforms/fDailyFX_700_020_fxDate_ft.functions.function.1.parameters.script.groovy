def Calendar changeCal = Calendar.instance;
changeCal.setTime(Date.parse("dd-MMM-yyyy",_input1));
change = changeCal.time;
String sYear = change.getAt(Calendar.YEAR);
String sNumMonth = change.getAt(Calendar.MONTH)+1;
if (sNumMonth.toInteger()<10){
  sNumMonth = '0'+sNumMonth;
}
String sDay = change.getAt(Calendar.DATE);
if (sDay.toInteger()<10){
  sDay = '0'+sDay;
}
return sYear+'-'+sNumMonth+'-'+sDay;
