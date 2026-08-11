usi_idx = LOG.indexOf("ETL Job user ");
if(usi_idx>-1)
{
  usr_idx = LOG.substring(usi_idx+13,LOG.length());
  return usr_idx;
}
else
{
  return null;
}
