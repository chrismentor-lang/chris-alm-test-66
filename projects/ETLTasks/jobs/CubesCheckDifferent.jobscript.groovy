if ("${SourceConn}".equalsIgnoreCase("${TargetConn}") && 
	"${SourceDB}".equalsIgnoreCase("${TargetDB}") && "${Cube}".equalsIgnoreCase("${TargetCube}") ) {
  LOG.error("The source and the target cubes have to be different.");
};
