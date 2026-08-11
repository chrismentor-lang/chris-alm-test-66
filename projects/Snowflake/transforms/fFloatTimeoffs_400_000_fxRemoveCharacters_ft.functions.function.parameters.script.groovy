if (TON == null || TON.isEmpty()){
  return TON;
}
else{
  return TON.replaceAll('\\|', ' ').replaceAll('\n', '\t');
}
