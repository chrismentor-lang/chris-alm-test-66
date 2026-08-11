import java.time.ZonedDateTime
import java.time.ZoneId

def now = ZonedDateTime.now(ZoneId.of("Australia/Sydney"))
def year = now.year
def month = now.monthValue

// Jul-Jun financial year: if month >= 7, FY ends next calendar year
def fyEndYear = (month >= 7) ? year + 1 : year

def financialYear = "FY" + fyEndYear

return financialYear
