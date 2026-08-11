fx_job:

if(LOG)
{
  job_idx = _input1.indexOf('Starting execution of job ');
  load_idx = _input1.indexOf('Starting execution of load ');
  project_idx = _input1.indexOf(' in project ');
  user_idx = _input1.indexOf('(ID: ');
  if(job_idx >=0 && user_idx >= 0 && project_idx >=0)//20231030: Added extra parameter due to anomaly in logs.
  {
	return _input2+"_job_"+_input1.substring(job_idx+26,project_idx);
  }
  else if(job_idx >=0 && user_idx >= 0)//20231030: Added extra if statement due to anomaly in logs.
  {
	project_idx = _input1.indexOf('in project ');
	return _input2+"_job_"+_input1.substring(job_idx+26,project_idx);
  }
  
  else
	return null; //put back to null
  
}
else
  return null; //put back to null
