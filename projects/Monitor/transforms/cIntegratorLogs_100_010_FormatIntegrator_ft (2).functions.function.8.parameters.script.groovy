if(JOB==null && LOAD==null)
{
  return MACH+"~";
}
else if(JOB==null)
{
  return LOAD;
}
else if(LOAD==null)
{
  return JOB;
}
