if(LOG==null || LOG1==null)
{
  return null;
}
else
{
  fin = LOG1.indexOf('Starting execution of job');
  job = LOG1.indexOf(' in project ');//20231026: Changed 'in project' to ' in project '
  if(fin>0)
  {
	if(job>=0)//20231026: Added for anomaly in logs
	{	
	  job1 = LOG1.substring(fin+26,job);//20231026: Changed job-1 to job
	}
	else//20231026: Added for anomaly in logs
	{
	  job = LOG1.indexOf('in project');//20231026: Added for anomaly in logs
	  job1 = LOG1.substring(fin+26,job);//20231026: Added for anomaly in logs
	}
	if(LOG.indexOf(job1)>1)
	{
	  prj = LOG1.indexOf('in project ');
	  uid = LOG1.indexOf('(ID:');
	  if(prj>0 && uid>0)
	  {
		project = LOG1.substring(prj+11,uid-1);
		return MCH+"_"+project+"_job_"+job1;
	  }
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
}
