SELECT DISTINCT NAMESPACE FROM JEDOX.PUBLIC.MACHINES
where NAMESPACE <> 'live-mp'
and NAMESPACE <> 'live-minervapartners'
and NAMESPACE <> 'poc-tpg'
and NAMESPACE NOT IN ('live-jims','live-solgen','test-solgen','live-westchester','live-oxfam',
					  'live-freseniuskabiau','test-freseniuskabi','live-energiequelle','test-energiequelle')
