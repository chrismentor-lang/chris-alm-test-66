if(STATUS==null||STATUS=="")
{
  return 0;
}
else if(STATUS.toLowerCase()=="completed with errors")
{
  return 1;
}
else
{
  return 0;
}
