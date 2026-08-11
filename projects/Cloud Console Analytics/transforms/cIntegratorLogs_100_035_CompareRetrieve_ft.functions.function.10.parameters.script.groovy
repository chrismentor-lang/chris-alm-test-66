if(LOG)
{
  job_idx = _input1.indexOf('Finished execution of job ');
  status_idx = _input1.indexOf(' with status: ');
  if(job_idx >=0 && status_idx >= 0)
  {
	return _input1.substring(status_idx+14,LOG.length());
  }
  else
	return null;
  
}
else
  return null;
