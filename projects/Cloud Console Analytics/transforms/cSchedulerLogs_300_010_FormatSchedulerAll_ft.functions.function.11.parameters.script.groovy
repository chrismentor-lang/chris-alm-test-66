if(LOG==null||LOG==""||LOG.length()<4)
{
  return 0;
}
else if(LOG[0..3]=="RROR")
{
  return 1;
}
else
{
  return 0;
}
