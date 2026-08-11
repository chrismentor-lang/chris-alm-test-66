if(LOG)
{
  load_idx = _input1.indexOf('workbook ');
  user_idx = _input1.indexOf('for user');
  if(load_idx >=0 && user_idx >= 0)
  return _input1.substring(load_idx+9,user_idx);
  else
	return '';
  
}
else
  return '';
