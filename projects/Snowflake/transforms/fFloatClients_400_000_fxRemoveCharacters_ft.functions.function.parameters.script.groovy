if (NAM == null || NAM.isEmpty()){
  return NAM;
}
else{
  return NAM.replaceAll('\\|', '**').replaceAll('\n', '\t');
}
