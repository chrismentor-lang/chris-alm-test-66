if (CON == null || CON.isEmpty()){
  return CON;
}
else{
  return CON.replaceAll('\\|', ' ').replaceAll('\n', '\t');
}
