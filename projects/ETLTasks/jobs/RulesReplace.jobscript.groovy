import java.io.File;

API.setProperty("TargetConn","${SourceConn}");
API.setProperty("TargetDB","${SourceDB}");

/* Create temporary directory */
int randomNum = new Random().nextInt();
API.setProperty("FilePath","temp"+randomNum);
String tempDirStr = API.getLocalFilesDir()+"\\temp" + randomNum + "\\";
File tempDir = new File(tempDirStr);
tempDir.mkdir();

API.executeLoad("RuleToFile_Load");
state = API.executeLoad("RulesUpdate_Load");
if (!state.isOK()) {
      API.executeLoad("RuleFromFile_Load");
};

/* Delete temporary directory */
for(File file: tempDir.listFiles()) file.delete();
tempDir.delete();
