if(LOG)
{
  user_idx = LOG.indexOf('] user');
  login_idx = LOG.indexOf('logged in');
  if(login_idx >=0)
  {
	user = LOG.substring(user_idx+8,login_idx-2);
	upper = user.toLowerCase();
	if(upper=="admin" || upper=="_internal_suite" || upper == "etl")
	{
	  return 0;
	}
	else
	{
	  return 1;
	}
  }
  else 
  {
	return '';
  }
}
else
  return '';
