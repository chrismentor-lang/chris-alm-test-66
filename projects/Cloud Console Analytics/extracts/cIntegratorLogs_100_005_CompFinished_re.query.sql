SELECT 
MACHINE,
TIME_UTC,
TIME_LOCAL,
LEVEL,
SERVICE,
LOG 
FROM JEDOX.LOGS.VW_INTEGRATOR
WHERE TIME_LOCAL > '${vFrom}' AND TIME_LOCAL < '${vTo}'
--AND LOG like '%Finished execution of job%'
AND LOG like '%Finished execution of job%'
AND MACHINE like'%-${vCompany}%'
