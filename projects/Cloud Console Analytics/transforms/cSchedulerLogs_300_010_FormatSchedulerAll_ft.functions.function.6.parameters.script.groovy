loc_idx = LOG.indexOf("- ETL Job locator");
if(loc_idx>-1)
{
  job_idx = LOG.indexOf(".jobs.");
  if(job_idx>-1)
  {
	return LOG.substring(loc_idx+18,job_idx);
  }
  else
  {
	return null;
  }
}
else
{
  return null;
}
