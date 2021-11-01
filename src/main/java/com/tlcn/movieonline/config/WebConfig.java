package com.tlcn.movieonline.config;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class WebConfig {

    @Value("${aws.accessKey}")
    private String accessKeyS3;

    @Value("${aws.secretKey}")
    private String secretKeyS3;

    @Value("${aws.s3.region}")
    private String region;

    @Bean
    public Cloudinary cloudinary(){
        Cloudinary c= new Cloudinary(ObjectUtils.asMap(
           "cloud_name","drmy6gdet",
                "api_key","631551962945422",
                "api_secret","zpXLnTNc4D0bqqh7rquGgvPtCv4",
                "secure", true
        ));
        return c;
    }

    @Bean
    public AmazonS3 amazonS3(){
        AWSCredentials awsCredentials= new BasicAWSCredentials(accessKeyS3,secretKeyS3);
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

    @Bean
    public S3Client getClient() {
        AwsCredentials awsCredentials= new AwsCredentials() {
            @Override
            public String accessKeyId() {
                return accessKeyS3;
            }

            @Override
            public String secretAccessKey() {
                return secretKeyS3;
            }
        };
        AwsCredentialsProvider awsCredentialsProvider= new AwsCredentialsProvider() {
            @Override
            public AwsCredentials resolveCredentials() {
                return awsCredentials;
            }
        };
        // Create the S3Client object.
        Region region = Region.AP_SOUTHEAST_1;
        S3Client awsS3 = S3Client.builder()
                .credentialsProvider(awsCredentialsProvider)
                .region(region)
                .build();

        return awsS3;
    }

}
