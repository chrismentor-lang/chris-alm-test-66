if(LOG)
{
  err_idx = _input1.indexOf('no http request handler');
  if(err_idx >=0)
  {
	return 1;
  }
}
else
  return 0;
