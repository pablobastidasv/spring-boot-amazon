package co.pablobastidasv;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.aws.autoconfigure.context.properties.AwsRegionProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonLocalConfig {

    private static final String SQS_SERVER = "http://localhost:4576";
    public static final String QUEUE_NAME = "my_queue";

    private static final Logger log = LoggerFactory.getLogger(AmazonLocalConfig.class);

    @Bean
    public AmazonSQSAsync amazonSQS(
            AwsClientBuilder.EndpointConfiguration endpointConfiguration
    ) {
        log.info("Configuring amazon SQS");
        var credentialsProvider = new AWSStaticCredentialsProvider(new AnonymousAWSCredentials());
        return AmazonSQSAsyncClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(credentialsProvider)
                .build();
    }

    @Bean
    public AwsClientBuilder.EndpointConfiguration endpointConfiguration(AwsRegionProperties awsRegionProperties) {
        log.info("Endpoint pointing to {}.", SQS_SERVER);
        var region = awsRegionProperties.getStatic();
        return new AwsClientBuilder.EndpointConfiguration(SQS_SERVER, region);
    }
}
