import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

String input_file = "./data/files/customer_logs/${vTableSnowflake}.csv";
String output_file = "./data/files/customer_logs/${vTableSnowflake}.csv.gz";

byte[] buffer = new byte[2048];
FileInputStream inputStream = new FileInputStream(input_file);
FileOutputStream outputStream = new FileOutputStream(output_file);
GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
int length;
while ((length = inputStream.read(buffer)) > 0) {
  gzipOutputStream.write(buffer, 0, length);
}
inputStream.close();
gzipOutputStream.close();
