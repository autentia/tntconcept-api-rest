package com.autentia.tnt.api.rest.config.security;

import com.autentia.tnt.api.rest.utils.services.SecretKeyService;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


@Configuration
@EnableAuthorizationServer
@Profile("!test")
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AuthorizationConfig.class);

    @Value("${client.name}")
    private String clientName;

    @Value("${client.secret}")
    private String clientSecret;

    @Value("${client.scope}")
    private String clientScope;

    @Value("${spring.ldap.urls}")
    private String ldapUrl;

    @Value("${spring.ldap.user-search-base}")
    private String ldapUserSearchBase;

    @Value("${spring.ldap.group-search-base}")
    private String ldapGroupSearchBase;

    @Value("${spring.ldap.group-search-filter}")
    private String ldapGroupSearchFilter;

    @Value("${security.jwt.access-validity-seconds}")
    private int accessTokenValiditySeconds;

    @Value("${security.jwt.refresh-validity-seconds}")
    private int refreshTokenValiditySeconds;

    @Autowired
    private SecretKeyService keyProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientName)
                .secret(clientSecret)
                .authorizedGrantTypes("refresh_token", "password")
                .scopes(clientScope).autoApprove(true)
                .accessTokenValiditySeconds(accessTokenValiditySeconds)
                .refreshTokenValiditySeconds(refreshTokenValiditySeconds);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices())
                .userDetailsService(ldapUserDetailsManager());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        try {
            converter.setKeyPair(keyProvider.getKeyPair());
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            logger.error("Error with authorization signing key", e);
        }
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DefaultTokenServices defaultTokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();;

        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        return defaultTokenServices;
    }

    @Bean
    public LdapUserDetailsService ldapUserDetailsManager() {
        return new LdapUserDetailsService(userSearch(), ldapAuthoritiesPopulator());
    }

    @Bean
    public FilterBasedLdapUserSearch userSearch() {
        return new FilterBasedLdapUserSearch(ldapUserSearchBase, "uid={0}", contextSource());
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return new DefaultSpringSecurityContextSource(ldapUrl);
    }

    @Bean
    public DefaultLdapAuthoritiesPopulator ldapAuthoritiesPopulator() {
        DefaultLdapAuthoritiesPopulator authPopulator = new DefaultLdapAuthoritiesPopulator(contextSource(), ldapGroupSearchBase);
        authPopulator.setGroupSearchFilter(ldapGroupSearchFilter);
        return authPopulator;
    }

}
