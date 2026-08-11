LOG.info("Starting Creation of Olap Model from File");
/* Dimensions */
columnsSource = API.initSource("GenericFile_Columns");
if (!columnsSource.nextRow()) {
  LOG.error("No columns found in Source file");
  return;
}
dimension = columnsSource.getColumnString("Column");
/* Do not take the last column which contains the cube value */
boolean ok=false;
while (columnsSource.nextRow()) {
   API.setProperty("Dimension",dimension);
   state = API.executeLoad("DimensionGeneric_Load");
   if (!state.isOK())
      return;
   dimension = columnsSource.getColumnString("Column");
   ok=true;
};
if (!ok) {
  LOG.error("Source file has only 1 column. At least 2 columns required for Cube data.");
}
/* Cube */
state = API.executeLoad("CubeGeneric_Load");
if (!state.isOK())
    return;

LOG.info("Finished Creation of Olap Model from File");
