if(LOG)
{
  job_idx = _input1.indexOf('Finished execution of job ');
  load_idx = _input1.indexOf('Finished execution of load ');
  status_idx = _input1.indexOf(' with status: ');
  if(job_idx >=0 && status_idx >= 0)
  {
	return _input1.substring(status_idx+14,LOG.length());
  }
  else
	return '';
  
}
else
  return '';
