after_idx = LOG.indexOf('after ');
fafter_idx = LOG.indexOf('flag after ');//20231130: Added to correct for job in PMHC
status_idx = LOG.indexOf('with status');

if(after_idx>0 && status_idx>0)
{
  if(fafter_idx>0)
  {
	sub = LOG.substring(fafter_idx+11,status_idx-2);
	db = sub.toDouble()*1000;
  }
  else
  {
	sub = LOG.substring(after_idx+6,status_idx-2);
	db = sub.toDouble()*1000;
  }
  return db.toInteger();
}
else
{
  0;
}
