def Calendar yesterdayCal = Calendar.instance
yesterdayCal.clearTime()
//yesterdayCal.set(2024, Calendar.FEBRUARY, 15) //Used for testing the code for custom dates
yesterdayCal.add(Calendar.DAY_OF_MONTH, -1)
yesterdayCal.add(Calendar.MONTH, -2)
yesterdayCal.set(Calendar.DAY_OF_MONTH, 1)
LOG.info('Test Date: '+yesterdayCal.time.format('YYYY-MM-dd'))
sleep(6000);
