import com.jedox.etl.core.function.FunctionException

connName = "localhost_Static"; dbName = "MF-DEMO"; dimName = "Month"
attrName = "Current Period"; flagValue = "Y"

try {
    def dim = OLAP.getGlobalConnection(connName).getDatabaseByName(dbName).getDimensionByName(dimName)

    // true = pull attribute values with the elements (one round trip, not one per element)
    def hit = dim.getElements(true).find { el ->
        def v = el.getAttributeValue(attrName)
        v != null && v.toString().trim().equalsIgnoreCase(flagValue)
    }

    if (!hit) throw new FunctionException("No element in '" + dimName + "' has attribute '" + attrName + "' = '" + flagValue + "'.")

    return hit.getName()
} catch (Exception e) {
    throw new FunctionException("Failed to resolve current month: " + e.getMessage(), e)
}
