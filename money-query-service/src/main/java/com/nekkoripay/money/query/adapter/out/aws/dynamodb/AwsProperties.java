package com.nekkoripay.money.query.adapter.out.aws.dynamodb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws")
@Getter
@Setter
public class AwsProperties {
    private String accessKey;
    private String secretKey;
}
