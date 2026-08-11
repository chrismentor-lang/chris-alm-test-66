// Today
def Calendar todayCal = Calendar.instance
todayCal.clearTime();
todayCal.add(Calendar.DATE,-1);
today = todayCal.time;

return today.format('YYYY-MM');;
