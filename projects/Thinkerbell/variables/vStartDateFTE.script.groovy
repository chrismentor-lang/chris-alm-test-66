def calendar = Calendar.instance
calendar.add(Calendar.DATE, -1)
return new java.text.SimpleDateFormat("yyyy-MM-dd").format(calendar.time)
