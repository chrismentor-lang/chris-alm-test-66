if((DB==null||DB=="")&&(DL==null||DL==""))
{
  return MACHINE+"~";
}
else if(DB==null||DB=="")
{
  return MACHINE+"_"+DL;
}
else
{
  return MACHINE+"_"+DB;
}
