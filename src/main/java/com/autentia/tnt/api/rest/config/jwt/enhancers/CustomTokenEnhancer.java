package com.autentia.tnt.api.rest.config.jwt.enhancers;

import com.autentia.tnt.api.rest.model.User;
import com.autentia.tnt.api.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomTokenEnhancer implements TokenEnhancer {
    UserService userService;

    @Autowired
    public CustomTokenEnhancer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        User user;
        String userLogin;

        if (authentication instanceof LdapUserDetails) {
            userLogin = ((LdapUserDetails) authentication.getPrincipal()).getUsername();
        } else {
            LinkedHashMap userDetails = (LinkedHashMap) authentication.getUserAuthentication().getDetails();
            userLogin = userDetails.get("username").toString();
        }

        user = userService.getUserByLogin(userLogin);
        additionalInfo.put("user", user);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(
                additionalInfo);
        return accessToken;
    }
}
