import java.time.ZonedDateTime
import java.time.ZoneId
def now = ZonedDateTime.now(ZoneId.of("Australia/Sydney"))
def year = now.year
def month = now.monthValue
// Jul-Jun financial year: if month >= 7, FY ends next calendar year
def fyEndYear = (month >= 7) ? year + 1 : year
// Month of FY: Jul=01, Aug=02, ... Jun=12
def fyMonth = (month >= 7) ? month - 6 : month + 6
def result = "FY" + fyEndYear + "-" + String.format("%02d", fyMonth)
return result
