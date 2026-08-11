os = new ByteArrayOutputStream()
def vHiBobEndpoint = API.getProperty("vHiBobEndpoint");

try (gzip = new java.util.zip.GZIPOutputStream(os)) 
{
  FILE.readBinary('cfHiBob_varHiBobEndpoint_fc').transferTo(gzip)
}
FILE.writeBinary('cfHiBob_varHiBobEndpointGZIP_fc', new ByteArrayInputStream(os.toByteArray()))
;
