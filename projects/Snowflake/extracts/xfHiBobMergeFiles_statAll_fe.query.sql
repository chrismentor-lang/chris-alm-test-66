SELECT '{ "results": [' || GROUP_CONCAT(SUBSTRING("Json Value", 2, LENGTH("Json Value") - 2) SEPARATOR ',') || '] }' AS merged_json
FROM "cfHiBob_varHiBobMerged_fc";
