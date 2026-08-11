//DETERMINE DAY YESTERDAY
tz = TimeZone.getTimeZone("Australia/Sydney");
today = new Date();
to = today.format('yyyy-MM-dd',timezone=tz);
API.setProperty('vTo',to);
LOG.info('Today/To is: '+to);
yesterday = new Date()-1;
from = yesterday.format('yyyy-MM-dd',timezone=tz);
API.setProperty('vFrom',from);
LOG.info('Yesterday was: '+from);

API.executeLoad('dDate_003_030_Yesterday_dl');
API.executeLoad('dDate_003_004_TDHierarchy_dl');
