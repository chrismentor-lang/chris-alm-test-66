// Today
def Calendar todayCal = Calendar.instance
todayCal.clearTime();
todayCal.add(Calendar.DATE,-1);
today = todayCal.time;
String sYear = today.getAt(Calendar.YEAR);
String sDate = today.getAt(Calendar.DATE);
String sNumMonth = today.getAt(Calendar.MONTH)+1;
String sDay = (sDate.toInteger()<10)?"0"+(sDate.toInteger()):(sDate.toInteger());
String sMonth = (sNumMonth.toInteger()<10)?"0"+sNumMonth:sNumMonth;
LOG.info('Today: ' + sYear + '-' + sMonth + '-' + sDay);
start_year = sYear;
start_month = sMonth.toInteger();
String sFYear = (sNumMonth.toInteger()<7)?(sYear.toInteger()):(sYear.toInteger()+1);

return sYear + '-' + sMonth + '-' + sDay;
