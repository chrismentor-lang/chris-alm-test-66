import java.time.*

// Budget has no actuals, so treat it as n = 0
def n = forecastInput.startsWith("Budget")
    ? 0
    : (forecastInput =~ /Forecast (\d+)\+/)[0][1] as int

// Extract FY year
def fyYear = fyInput.replaceAll("[^0-9]", "") as int

// Map to month (July = 1 → June = 12)
def month = (n <= 6) ? n + 6 : n - 6

// Determine calendar year
def year = (n <= 6) ? fyYear - 1 : fyYear

// Get last day of month
def lastDate = YearMonth.of(year, month).atEndOfMonth()
return lastDate.toString()
