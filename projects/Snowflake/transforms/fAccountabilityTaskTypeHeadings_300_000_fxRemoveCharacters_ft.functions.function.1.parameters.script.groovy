if (DESC == null || DESC.isEmpty()){
  return DESC;
}
else{
  return DESC.replaceAll('\\|', ' ').replaceAll('\n', '\t');
}
