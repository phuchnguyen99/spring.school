package com.spring.data.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties("application.jwt")
public class JwtConfig {
    private String tokenPrefix;
    private String secreteKey;
    private Integer tokenExpirationAfterDays;

    public String getAuthorizationHeader()
    {
        return HttpHeaders.AUTHORIZATION;
    }

    public String getTokenPrefix()
    {
        return tokenPrefix.replace("\\", "");
    }

}
