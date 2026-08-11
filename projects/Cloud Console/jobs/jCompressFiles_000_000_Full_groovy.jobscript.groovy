os = new ByteArrayOutputStream()
try (gzip = new java.util.zip.GZIPOutputStream(os)) 
{
  FILE.readBinary('fLog_000_000_TransformedCSV_fc').transferTo(gzip)
}
FILE.writeBinary('fLog_000_000_TransformedGZIP_fc', new ByteArrayInputStream(os.toByteArray()))
