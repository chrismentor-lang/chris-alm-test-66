def year = input.replaceAll("[^0-9]", "") as int
def startYear = year - 1

return startYear + "-07-01"
