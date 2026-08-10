import java.time.ZonedDateTime
import java.time.ZoneId

def now = ZonedDateTime.now(ZoneId.of("Australia/Sydney"))
def year = now.year
def month = now.monthValue

// Jul-Jun financial year: if month >= 7, current FY ends next calendar year
def currentFyEndYear = (month >= 7) ? year + 1 : year

// Next financial year is simply one more
def nextFyEndYear = currentFyEndYear + 1

def nextFinancialYear = "FY" + nextFyEndYear

return nextFinancialYear
