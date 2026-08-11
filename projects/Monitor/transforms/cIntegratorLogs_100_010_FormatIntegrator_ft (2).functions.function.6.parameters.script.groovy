if(LOG)
{
  job_idx = _input1.indexOf('Starting execution of job ');
  load_idx = _input1.indexOf('Starting execution of load ');
  project_idx = _input1.indexOf(' in project ');
  user_idx = _input1.indexOf('(ID: ');
  if(load_idx >=0 && user_idx >= 0)
  {
	return _input2+"_load_"+_input1.substring(load_idx+27,project_idx);
  }
  else
	return null;
  
}
else
  return null;
