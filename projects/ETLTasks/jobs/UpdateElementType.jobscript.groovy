LOG.info("Starting update elements type in dimension");

def targetElementType = IElement.ElementType.ELEMENT_NUMERIC;
if(API.getProperty("TargetElementType").equals("N")){
API.setProperty("_cellType","only_string");/*_cellType are the cells that we need to backup*/
API.setProperty("_elementType","S");/*_elementType is the type to be changed */
}else if(API.getProperty("TargetElementType").equals("S")){
API.setProperty("_cellType","only_numeric");
API.setProperty("_elementType","N");
targetElementType =  IElement.ElementType.ELEMENT_STRING;
}else{
LOG.error("Target type can only be N or S.");
return;
};

/*collect the list of the elements that need to be changed*/
  elementToBeChangedSource = API.initSource("ElementTypeTransform");

   def names = [];
   int elemCount=0;
   while (elementToBeChangedSource.nextRow()) {
     name=elementToBeChangedSource.getColumnString(":element");
     names.add(name);
     LOG.info("The element \"" + name + "\" type will be changed to " + targetElementType);
     elemCount++;
   };

if(names.size()==0){
  LOG.warn("No element found that needs to be changed.");
  return;
};

/* Create temporary directory */
int randomNum = new Random().nextInt();
API.setProperty("FilePath","temp"+randomNum);
String tempDirStr = API.getLocalFilesDir()+"\\temp" + randomNum + "\\";
File tempDir = new File(tempDirStr);
tempDir.mkdir();

/* make a backup in files for the cells */
state = API.executeJob("CubeFile_Type_Job");
 if (!state.isOK())
     return;

  /** change the type in the dimension */
  IDimension dim1 = OLAP.getDatabase("OlapSource").getDimensionByName(API.getProperty("Dimension"));  
  IElement[] elems = dim1.getElementsByName(names as String[],false);
  dim1.updateElementsType(elems, targetElementType);
  LOG.info("Number of elements that are changed is: " + elemCount);

/* restore the cube cells if possible */
state = API.executeJob("CubesFromFile_Insert_All");
 if (!state.isOK())
     return;
     
/* Delete temporary directory */
for(File file: tempDir.listFiles()) file.delete();
tempDir.delete();

LOG.info("Finished update element types in dimension");
