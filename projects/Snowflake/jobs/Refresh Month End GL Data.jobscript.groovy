vPrevMonth = API.getProperty("vPrevMonth");
dateString = vPrevMonth+'-'+'02';

Date today = Date.parse("yyyy-MM-dd", dateString)

month = today.format('MMMM');

DB = "cgLocal_statThinkerbell_jc";
Cub = "#_Accounting Month";
String [] path = ["Accounting Month ID", month, "~"];
IDatabase db = OLAP.getDatabase(DB);
ICube cube = db.getCubeByName(Cub);
IElement [] elPath = cube.getCellPath(path);
ICell cell = cube.getCell(elPath);
AccountingMonth = cell.getValue();
//LOG.info(month);
//LOG.info(AccountingMonth);

API.setProperty('vAccountingMonth',AccountingMonth);

year = today.format('YYY');
Integer sNumMonth = today.getAt(Calendar.MONTH)+1;
String sYear = (sNumMonth>6)?year.toInteger()+1:year;

DB = "cgLocal_statThinkerbell_jc";
Cub = "#_Accounting Year";
path = ["Accounting Year ID", sYear, "~"];
db = OLAP.getDatabase(DB);
cube = db.getCubeByName(Cub);
elPath = cube.getCellPath(path);
cell = cube.getCell(elPath);
AccountingYear = cell.getValue();
//LOG.info(sYear);
//LOG.info(AccountingYear);

API.setProperty('vAccountingYear',AccountingYear);

API.setProperty('vSnowflakeSchemaType','INCREMENTAL');

// Refreshing the Journals Data for vMonth
API.setProperty('vAccountabilityEndpoint','Journals');
API.setProperty('vAccountabilityTable','JOURNALS');
API.executeJob('Refresh Accountability Data');

// Refreshing the GL Trans Data for yesterday modified and deleting the GL Trans whose Journal ID does not exist in Journals Harmonisation Layer
API.setProperty('vAccountabilityEndpoint','GeneralLedgerTransactions');
API.setProperty('vAccountabilityTable','GENERAL_LEDGER_TRANSACTIONS');
API.executeJob('Refresh Accountability Data');

Thread.sleep(30000);
// Refreshing the Journals Data for previous month
API.setProperty('vAccountabilityEndpoint','JournalEntries');
API.setProperty('vAccountabilityTable','JOURNAL_ENTRIES');
API.executeJob('Refresh Accountability Data');
