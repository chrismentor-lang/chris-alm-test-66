API.setProperty("vJedoxFileName","RevenueTrackerVersion");
API.setProperty("vJedoxSnowflakeTable","REVENUE_TRACKER_VERSION");
API.setProperty("vJedoxWinSCPFolder","Snowflake/Jedox/RevenueTrackerVersion");
API.executeJob("Update Jedox Tables");
API.executeLoad("rRevenueTrackerVersion_InsertIntoDWFCSnapshot_rs");
