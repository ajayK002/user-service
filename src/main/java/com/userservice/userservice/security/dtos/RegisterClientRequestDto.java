package com.userservice.userservice.security.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;

import java.util.Set;

@Getter
@Setter
public class RegisterClientRequestDto {
    private String clientId;
    private String clientSecret;
    private ClientAuthenticationMethod clientAuthenticationMethod;
    private Set<AuthorizationGrantType> authorizationGrantType;
    private String redirectUri;
    private Set<OidcScopes> scopes;

}
