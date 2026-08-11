if(ERR)
{
  err_idx = ERR.indexOf('error code: ');
  des_idx = ERR.indexOf('description: ');
  if(err_idx >=0)
  {
	sub = ERR.substring(err_idx+12,des_idx);
	return 'Error code '+sub.trim();
    }
  else 
  {
	return '';
  }
}
else
  return '';
