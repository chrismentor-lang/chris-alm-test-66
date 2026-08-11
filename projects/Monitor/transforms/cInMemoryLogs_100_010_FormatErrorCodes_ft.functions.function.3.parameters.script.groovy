if(LOG)
{
  err_idx = _input1.indexOf('error code: ');
  par_idx = _input1.indexOf('(parameter ');
  if(err_idx >=0 && par_idx >= 0)
  {
	return _input1.substring(err_idx,par_idx);
  }
  else if(err_idx >=0)
  {
	return _input1.substring(err_idx,LOG.length());
  }
	return '';
  
}
else
  return '';
