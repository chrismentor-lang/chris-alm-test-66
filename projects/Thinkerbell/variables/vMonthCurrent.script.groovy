// Today
def Calendar todayCal = Calendar.instance
todayCal.clearTime();
today = todayCal.time;

return today.format('YYYY-MM');;
