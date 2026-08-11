//DETERMINE DAY FOR FROM AND TO
tz = TimeZone.getTimeZone("Australia/Sydney");

from = API.getProperty('vFrom');
LOG.info('From date is: '+from);
to = API.getProperty('vTo');
LOG.info('To date is: '+to);

yr = from[0..3];
API.setProperty('vYear',yr);
LOG.info('Year is: '+yr);

mth = from[5..6];
API.setProperty('vMonth',mth);
LOG.info('Month is: '+mth);

day = from[8..9];
API.setProperty('vDay',day);
LOG.info('Day is: '+day);

API.executeJob('Spreadsheet Company Cubes Loop');
