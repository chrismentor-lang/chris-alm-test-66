SELECT "Version", CONCAT(LEFT("Day",7),'-~') as "Day","Scenario", "Client", "Task Type", "Currency", "Detail Billable Rate", "Measure", "#Value" FROM CONSUMPTION.JEDOX_CUBES.VW_BILLABLE_RATE
WHERE "Day" = '${vDay}';
