def tz = TimeZone.getTimeZone("Australia/Sydney")
def yesterday = new Date() - 1
def cal = Calendar.getInstance(tz)
cal.setTime(yesterday)

def year = cal.get(Calendar.YEAR)
def month = cal.get(Calendar.MONTH) + 1 // Calendar months are 0 based

// If yesterday is before July, current fiscal year ends this June
// so next fiscal year ends the June after that
def fiscalYearEnd
if (month < 7) {
    fiscalYearEnd = (year + 1).toString() + '-06-30'
} else {
    fiscalYearEnd = (year + 2).toString() + '-06-30'
}

return fiscalYearEnd
