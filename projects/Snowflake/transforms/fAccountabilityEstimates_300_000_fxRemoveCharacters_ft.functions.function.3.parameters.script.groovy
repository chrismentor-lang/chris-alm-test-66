if (_in1 == null || _in1.isEmpty()){
  return _in1;
}
else{
  return _in1.replaceAll('\\|', ' ').replaceAll('\n', '\t');
}
