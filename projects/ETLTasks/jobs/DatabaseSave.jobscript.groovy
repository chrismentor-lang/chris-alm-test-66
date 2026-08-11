LOG.info("Starting Jedox Database Save");
IDatabase db = OLAP.getDatabase("OlapTarget");
db.save(true);
LOG.info("Finished Jedox Database Save");
