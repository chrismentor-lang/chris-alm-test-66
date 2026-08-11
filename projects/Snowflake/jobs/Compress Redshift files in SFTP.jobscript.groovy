os = new ByteArrayOutputStream()
try (gzip = new java.util.zip.GZIPOutputStream(os)) 
{
  FILE.readBinary('cfRedshit_varRedshiftSchemaTable_fc').transferTo(gzip)
}
FILE.writeBinary('cfRedshit_varRedshiftSchemaTableGZIP_fc', new ByteArrayInputStream(os.toByteArray()))
