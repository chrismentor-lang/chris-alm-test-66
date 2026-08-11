def tz = TimeZone.getTimeZone("Australia/Sydney")
def yesterday = new Date() - 1
def cal = Calendar.getInstance(tz)
cal.setTime(yesterday)

def year = cal.get(Calendar.YEAR)
def month = cal.get(Calendar.MONTH) + 1 // Calendar months are 0 based

// If yesterday is before July, fiscal year started in previous year
def fiscalYearStart
if (month < 7) {
    fiscalYearStart = (year - 1).toString() + '-07-01'
} else {
    fiscalYearStart = year.toString() + '-07-01'
}

return fiscalYearStart
