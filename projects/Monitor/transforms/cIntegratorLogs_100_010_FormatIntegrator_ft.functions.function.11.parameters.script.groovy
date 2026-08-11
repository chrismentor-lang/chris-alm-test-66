if(STATUS==null||STATUS=="")
{
  return 0;
}
else if(STATUS.toLowerCase()=="completed successfully")
{
  return 1;
}
else
{
  return 0;
}
