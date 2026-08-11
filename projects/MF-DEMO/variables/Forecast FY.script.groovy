import com.jedox.etl.core.function.FunctionException

connName = "localhost_Static"; dbName = "MF-DEMO"; dimName = "Version"

try {
    def dim = OLAP.getGlobalConnection(connName).getDatabaseByName(dbName).getDimensionByName(dimName)
    def elem = dim.getElementByName("Forecast")

    if (!elem) throw new FunctionException("Element 'Forecast' not found in Version dimension.")

    def topNValue = elem.getAttributeValue("Financial Year")

    return topNValue ? topNValue.toString() : "0"
} catch (Exception e) {
    throw new FunctionException("Failed to retrieve TopN Value: " + e.getMessage(), e)
}
