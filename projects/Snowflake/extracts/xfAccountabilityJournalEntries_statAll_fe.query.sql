SELECT LISTAGG(DISTINCT "id", ',') 
       WITHIN GROUP (ORDER BY "id") AS ids
FROM "cfAccountabilityJournals_varFileDate_fc";
