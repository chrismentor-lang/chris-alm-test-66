//DETERMINE DAY FOR FROM AND TO
tz = TimeZone.getTimeZone("Australia/Sydney");
today = new Date();
to = today.format('yyyy-MM-dd',timezone=tz);
API.setProperty('vTo',to);
LOG.info('Today/To is: '+to);
yesterday = new Date()-1;
from = yesterday.format('yyyy-MM-dd',timezone=tz);
API.setProperty('vFrom',from);
LOG.info('Yesterday/From was: '+from);
yr = yesterday.format('yyyy',timezone=tz);
API.setProperty('vYear',yr);
LOG.info('Year was: '+yr);
mth = yesterday.format('MM',timezone=tz);
API.setProperty('vMonth',mth);
LOG.info('Month was: '+mth);
day = yesterday.format('dd',timezone=tz);
API.setProperty('vDay',day);
LOG.info('Day was: '+day);

API.executeJob('Integrator Company Cubes Loop');
