def df = new java.text.SimpleDateFormat("yyyy-MM-dd")
def cal = Calendar.instance
cal.time = df.parse(API.getProperty("vStartDateFTE"))
cal.add(Calendar.YEAR, 1)
cal.add(Calendar.DATE, -1)
return df.format(cal.time)
