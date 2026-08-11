if(LOG)
{
  db_idx = LOG.indexOf('system] database');
  size_idx = LOG.indexOf('disk size');
  if(db_idx>0 && size_idx>0)
  {
	size = LOG.substring(size_idx+10,LOG.length());
	mb_idx = size.indexOf(' MB');
	if(mb_idx>0)
	{
	  str = LOG.substring(size_idx+10,LOG.length()-3);
	  return str.toInteger();
	}
	else
	{
	  return "size not in MB";
	}
  }
}
