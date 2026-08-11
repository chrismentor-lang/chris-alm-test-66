import java.time.*
import java.time.format.*

def d = LocalDate.now(ZoneId.of("Australia/Melbourne")).minusDays(1)
def day = d.dayOfMonth

def suffix = (day >= 11 && day <= 13) ? "th" :
             (day % 10 == 1) ? "st" :
             (day % 10 == 2) ? "nd" :
             (day % 10 == 3) ? "rd" : "th"

return day + suffix + " " + d.format(DateTimeFormatter.ofPattern("MMMM yyyy"))
