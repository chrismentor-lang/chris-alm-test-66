if(LOG)
{
  err_idx = _input1.indexOf('got corrupted HTTP request');
  if(err_idx >=0)
  {
	return 1;
  }
}
else
  return 0;
