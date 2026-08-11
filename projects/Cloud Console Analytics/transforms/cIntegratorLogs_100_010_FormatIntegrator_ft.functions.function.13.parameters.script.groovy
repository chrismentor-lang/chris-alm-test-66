if(STATUS==null||STATUS=="")
{
  return 0;
}
else if(STATUS.toLowerCase()=="failed")
{
  return 1;
}
else
{
  return 0;
}
