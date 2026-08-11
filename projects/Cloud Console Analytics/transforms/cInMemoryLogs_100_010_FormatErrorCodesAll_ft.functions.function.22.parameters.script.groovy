if(LOG)
{
  db_idx = LOG.indexOf('delayed auto loading database');
  if(db_idx>0)
  {
	return LOG.substring(db_idx+31,LOG.length()-1);
  }
}
