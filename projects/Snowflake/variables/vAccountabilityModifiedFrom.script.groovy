import java.time.ZonedDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

// Get current date in Australian timezone
def australianZone = ZoneId.of("Australia/Sydney") // or "Australia/Melbourne"
def nowInAustralia = ZonedDateTime.now(australianZone)

// Get yesterday's date at midnight in Australian timezone, then convert to UTC
def midnightAustraliaYesterday = nowInAustralia.toLocalDate()
	.minusDays(1) 
    .atStartOfDay(australianZone)  // Midnight in Australian timezone
    .withZoneSameInstant(ZoneId.of("UTC"))  // Convert to UTC

// Format as yyyy-MM-ddTHH:mm:ssZ
def formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
def formattedDate = midnightAustraliaYesterday.format(formatter)

return  formattedDate // Output: 2026-01-06T13:00:00Z
