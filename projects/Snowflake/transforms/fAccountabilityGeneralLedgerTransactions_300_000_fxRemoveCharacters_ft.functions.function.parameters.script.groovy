if (DES == null || DES.isEmpty()){
  return DES;
}
else{
  return DES.replaceAll('\\|', ' ').replaceAll('\n', '\t');
}
