if(LOG)
{
  err_idx = _input1.indexOf('Excel Add-in version needs to be updated');
  if(err_idx >=0)
  {
	return 1;
  }
}
else
  return 0;
