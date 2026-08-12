os = new ByteArrayOutputStream()
try (gzip = new java.util.zip.GZIPOutputStream(os)) 
{
  FILE.readBinary('cfFloat_varFloatEndpoint_fc').transferTo(gzip)
}
FILE.writeBinary('cfFloat_varFloatEndpointGZIP_fc', new ByteArrayInputStream(os.toByteArray()))
