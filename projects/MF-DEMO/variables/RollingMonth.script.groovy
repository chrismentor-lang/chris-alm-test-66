import com.jedox.etl.core.function.FunctionException

connName = "localhost_Static"; dbName = "MF-DEMO"

try {
    def versionDim = OLAP.getGlobalConnection(connName).getDatabaseByName(dbName).getDimensionByName("Version")
    def monthDim = OLAP.getGlobalConnection(connName).getDatabaseByName(dbName).getDimensionByName("Month")
    
    // Step 1: Get Current Period from Actual
    def actual = versionDim.getElementByName("Actual")
    if (!actual) throw new FunctionException("Element 'Actual' not found in Version dimension.")
    
    def currentPeriod = actual.getAttributeValue("Current Period")
    if (!currentPeriod) throw new FunctionException("'Current Period' attribute returned null for Actual.")
    
    // Step 2: Look up that element in Month dimension and get Prior Year attribute
    def monthElem = monthDim.getElementByName(currentPeriod.toString())
    if (!monthElem) throw new FunctionException("Element '" + currentPeriod + "' not found in Month dimension.")
    
    def priorYear = monthElem.getAttributeValue("Prior Year")
    if (!priorYear) throw new FunctionException("'Prior Year' attribute returned null for " + currentPeriod + ".")
    
    // Step 3: Concatenate R12
    return priorYear.toString() + "-R12"
} catch (Exception e) {
    throw new FunctionException("Failed: " + e.getMessage(), e)
}
