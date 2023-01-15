package com.example.group7project;

import java.io.IOException;
import java.io.InputStream;
 
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;
 
public class UploadImageToS3 {
    private static final String BUCKET = "group-7-bucket";
     
    public static void uploadFile(String fileName, InputStream inputStream) throws S3Exception, AwsServiceException, SdkClientException, IOException {
        S3Client client = S3Client.builder().build();
         
        PutObjectRequest request = PutObjectRequest.builder()
                            .bucket(BUCKET)
                            .key(fileName)
                            .build();
                            // .acl("public-read")
                            // .contentType("image/png")
         
        client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));

        S3Waiter waiter = client.waiter();
        HeadObjectRequest waitRequest = HeadObjectRequest.builder()
                                    .bucket(BUCKET)
                                    .key(fileName)
                                    .build();
                 
        WaiterResponse<HeadObjectResponse> waitResponse = waiter.waitUntilObjectExists(waitRequest);
                 
        waitResponse.matched().response().ifPresent(x -> {
            System.out.println("The file " + fileName + " is now ready.");
        });
    }
}
