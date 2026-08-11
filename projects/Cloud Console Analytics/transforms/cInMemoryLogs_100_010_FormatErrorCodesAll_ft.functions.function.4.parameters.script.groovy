if(ERR)
{
  err_idx = ERR.indexOf('description: ');
  if(err_idx >=0)
  {
	return _input1.substring(err_idx+13,ERR.length());
    }
  else 
  {
	return '';
  }
}
else
  return '';
