SELECT CASE WHEN "Cursor" = '' THEN '' ELSE "Cursor" END AS "Cursor"
FROM "cfHiBob_varEndpoint_fc"
WHERE "Timestamp" = (
    SELECT MAX("Timestamp") 
    FROM "cfHiBob_varEndpoint_fc"
)
