if(LOG)
{
  job_idx = _input1.indexOf('Starting execution of job ');
  load_idx = _input1.indexOf('Starting execution of load ');
  project_idx = _input1.indexOf(' in project ');
  user_idx = _input1.indexOf('(ID: ');
  if((load_idx >=0 || job_idx >=0) && user_idx >= 0)
  {
	return _input1.substring(project_idx+11,user_idx);
  }
  else
	return null;
  
}
else
  return null;
