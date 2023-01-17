package com.example.group7project;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

// Uploads an image to AWS S3 and gets objects from the S3 bucket
public class S3Util {
    private static final String BUCKET = "group7-image-bucket";
    private static AwsCredentialsProvider awsCredentialsProvider;
    private static String accessKey = "";
    private static String secretKey = "";
    
    public static void uploadFile(String fileName, InputStream inputStream) throws S3Exception, AwsServiceException, SdkClientException, IOException {
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        awsCredentialsProvider = StaticCredentialsProvider.create(awsBasicCredentials);

        S3Client client = S3Client.builder().credentialsProvider(awsCredentialsProvider).build();
         
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

    public List<String> getS3Objects() {
        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKey, secretKey);
        awsCredentialsProvider = StaticCredentialsProvider.create(awsBasicCredentials);

        List<String> S3Objects = new ArrayList<>();

        S3Client client = S3Client.builder().region(Region.US_WEST_1).credentialsProvider(awsCredentialsProvider).build();
        ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(BUCKET).build();
        ListObjectsV2Iterable response = client.listObjectsV2Paginator(request);

        for (ListObjectsV2Response page : response) {
            page.contents().forEach((S3Object object) -> {
                S3Objects.add(object.key());
            });
        }

        System.out.println("\nList of all objects in the S3 bucket (" + S3Objects.size() +" total): ");
        System.out.println(S3Objects.toString());

        return S3Objects;
    }
}
