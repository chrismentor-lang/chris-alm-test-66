def months = [
    "July", "August", "September", "October", "November", "December",
    "January", "February", "March", "April", "May", "June"
]

def m = (input =~ /Forecast (\d+)\+/)
if (m.find()) {
    def n = m.group(1) as int
    return months[n] + " Forecast"
}
if (input == "Budget") {
    return "Budget"   // or whatever label Budget should map to
}
return input          // fallback for anything unexpected
