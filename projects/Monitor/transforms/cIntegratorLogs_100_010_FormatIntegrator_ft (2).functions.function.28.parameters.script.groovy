if(LOG)
{
  low = LOG.toLowerCase();
  cube_idx = low.indexOf('records loaded to cube: ');
  if(cube_idx >=0)
  {
	amt = LOG.substring(cube_idx+24,LOG.length());
	return amt.toInteger();
  }
  else
  {
	return null;
  }
}
else
{
  return null;
}
