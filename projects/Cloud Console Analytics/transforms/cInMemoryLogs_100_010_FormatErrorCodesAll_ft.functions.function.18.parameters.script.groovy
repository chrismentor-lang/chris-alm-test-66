if(LOG)
{
  disk_idx = LOG.indexOf('Disk free space:');
  if(disk_idx>0)
  {
	return LOG.substring(disk_idx+16,LOG.length());
  }
}
