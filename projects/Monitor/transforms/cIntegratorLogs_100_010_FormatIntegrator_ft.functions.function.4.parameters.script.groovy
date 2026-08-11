if(LOG)
{
  job_idx = _input1.indexOf('Starting execution of job ');
  load_idx = _input1.indexOf('Starting execution of load ');
  project_idx = _input1.indexOf(' in project ');
  user_idx = _input1.indexOf('(ID: ');
  if(job_idx >=0 && user_idx >= 0)
  {
	return _input2+"_job_"+_input1.substring(job_idx+26,project_idx);
  }
  else
	return null;
  
}
else
  return null;
