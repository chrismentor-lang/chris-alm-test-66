SELECT LISTAGG(DISTINCT "id", ',') 
       WITHIN GROUP (ORDER BY "id") AS ids
FROM "cfAccountabilityEstimates_varFileDate_fc";
