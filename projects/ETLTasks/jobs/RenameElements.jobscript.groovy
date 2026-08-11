source = API.initSource("Dimension_Anonym_T1");
   def oldNames = [];
   def newNames = [];
   while (source.nextRow()) {
     oldNames.add(source.getColumnString("OldName"));
     newNames.add(source.getColumnString("NewName"));
   };
   OLAP.erename("OlapTarget","${Dimension}",oldNames as String[],newNames as String[]);
