int lgt = LOG.length();
if(LOG==null||LOG=="\"")
{
  return null;
}
else if(LOG[0..0]=="\"" && LOG[lgt-2..lgt-1]=="\"")
{
  sub = LOG[1..lgt-2];
  return sub.replace('"','');
}
else if(LOG[0..0]=="\"")
{
  sub = LOG[1..lgt-1];
  return sub.replace('"','');
}
else
{
  return LOG.replace('"','');
}
