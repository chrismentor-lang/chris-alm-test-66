if(LOG)
{
  db_idx = LOG.indexOf('system] database');
  size_idx = LOG.indexOf('disk size');
  if(db_idx>0 && size_idx>0)
  {
	return LOG.substring(db_idx+18,size_idx-3);
  }
}
