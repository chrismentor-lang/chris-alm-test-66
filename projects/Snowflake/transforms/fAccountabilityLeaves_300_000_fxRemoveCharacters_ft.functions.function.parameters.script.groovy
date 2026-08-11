if (NOT == null || NOT.isEmpty()){
  return NOT;
}
else{
  return NOT.replaceAll('\\|', ' ').replaceAll('\n', '\t');
}
