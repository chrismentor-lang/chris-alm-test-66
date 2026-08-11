os = new ByteArrayOutputStream()
try (gzip = new java.util.zip.GZIPOutputStream(os)) 
{
  FILE.readBinary('cfRBA_varDailyFX_fc').transferTo(gzip)
}
FILE.writeBinary('cfRBA_varDailyFXGZIP_fc', new ByteArrayInputStream(os.toByteArray()))
