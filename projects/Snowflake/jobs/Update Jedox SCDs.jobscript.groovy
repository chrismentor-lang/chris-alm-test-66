if ('${vSCD}'=='1'){
  API.executeLoad('rJedox_InsertIntoHarmonisationSCD_rs');
}
else {
  LOG.info('No SCD for this table');
}
