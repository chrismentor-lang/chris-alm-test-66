os = new ByteArrayOutputStream()
try (gzip = new java.util.zip.GZIPOutputStream(os)) 
{
  FILE.readBinary('cfAccountability_varAccountabilityEndpoint_fc_Sandbox').transferTo(gzip)
}
FILE.writeBinary('cfAccountability_varAccountabilityEndpointGZIP_fc_Sandbox', new ByteArrayInputStream(os.toByteArray()))
