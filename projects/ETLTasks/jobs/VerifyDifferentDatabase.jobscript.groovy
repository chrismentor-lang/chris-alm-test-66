if ("${SourceConn}".equalsIgnoreCase("${TargetConn}") && 
	"${SourceDB}".equalsIgnoreCase("${TargetDB}") ) {
  LOG.error("The source and the target OLAP databases must be different.");
}
