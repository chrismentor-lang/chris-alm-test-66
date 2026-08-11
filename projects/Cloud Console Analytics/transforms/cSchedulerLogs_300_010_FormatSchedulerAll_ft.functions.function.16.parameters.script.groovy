tsk_idx = LOG.indexOf("[", LOG.indexOf("[") + 1);
if(tsk_idx>-1)
{
  tsk2_idx = LOG.indexOf("]", LOG.indexOf("]") + 1);
  if(tsk2_idx>-1)
  {
	return _input2+"_"+_input1.substring(tsk_idx+1,tsk2_idx);
  }
  else
  {
	return _input2+"~";
  }
}
else
{
  return _input2+"~";
}
