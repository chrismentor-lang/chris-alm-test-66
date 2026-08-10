os = new ByteArrayOutputStream()
try (gzip = new java.util.zip.GZIPOutputStream(os)) 
{
  FILE.readBinary('cfAccountability_varAccountabilityEndpoint_fc').transferTo(gzip)
}
FILE.writeBinary('cfAccountability_varAccountabilityEndpointGZIP_fc', new ByteArrayInputStream(os.toByteArray()))
