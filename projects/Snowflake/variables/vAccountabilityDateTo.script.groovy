def Calendar yesterdayCal = Calendar.instance
yesterdayCal.clearTime()
//yesterdayCal.set(2024, Calendar.FEBRUARY, 15) Used for testing the code for custom dates
yesterdayCal.add(Calendar.DAY_OF_MONTH, -1)
yesterdayCal.set(Calendar.DAY_OF_MONTH, yesterdayCal.getActualMaximum(Calendar.DAY_OF_MONTH))
return yesterdayCal.time.format('YYYY-MM-dd')
