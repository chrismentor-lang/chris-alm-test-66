os = new ByteArrayOutputStream()
try (gzip = new java.util.zip.GZIPOutputStream(os)) 
{
  FILE.readBinary("cfJedox_varJedoxFileName_fc").transferTo(gzip)
}
FILE.writeBinary("cfJedox_varJedoxFileNameGZIP_fc", new ByteArrayInputStream(os.toByteArray()))
