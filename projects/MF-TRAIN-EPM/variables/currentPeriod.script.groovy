import com.jedox.etl.core.function.FunctionException

// change below as required
connName = "localhost_Static" ; dbName = "MF-TRAIN" ; dimName = "Month"

try {
    def dim = OLAP.getGlobalConnection(connName).getDatabaseByName(dbName).getDimensionByName(dimName)
    
    // Find the first element where the 'Current Period' attribute equals 'Y'
    def match = dim.getElements().find { it.getAttributeValue("Current Period") == "Y" }
    
    if (!match) throw new FunctionException("No month element found matching criteria.")

    // Return element name (e.g., "2026-05"). Change to .getAttributeValue("Month Value") if needed.
    return match.getName() 

} catch (Exception e) {
    throw new FunctionException("Failed to evaluate Month: " + e.getMessage(), e)
}
