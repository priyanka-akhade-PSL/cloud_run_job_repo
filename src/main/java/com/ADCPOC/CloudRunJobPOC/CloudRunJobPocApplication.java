package com.ADCPOC.CloudRunJobPOC;

import com.google.cloud.storage.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
public class CloudRunJobPocApplication implements CommandLineRunner {

    String fileName = "samplefile.json";
    String bucketName = "test-bucket-search-conversation-new";

    public static void main(String[] args) {
        SpringApplication.run(CloudRunJobPocApplication.class, args);
    }

    private void uploadFileToBucket() throws IOException {
//		logger.info("Uploading file to GCS Bucket...");
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Blob blob = storage.create(blobInfo, Files.readAllBytes(Paths.get("samplefile.json")));
        System.out.println(blob);
//		logger.info("File has been uploaded!");
    }

    @Override
    public void run(String... args) throws Exception {
        createTrustManager();
        uploadFileToBucket();
    }

    private void createTrustManager() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HostnameVerifier allHostsValid = (hostname, session) -> true;
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
