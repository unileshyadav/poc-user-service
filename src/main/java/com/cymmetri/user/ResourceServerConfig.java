/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cymmetri.user;

import feign.RequestInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private final ResourceServerProperties sso;

	private final OAuth2ClientContext oAuth2ClientContext;

	@Autowired
	public ResourceServerConfig(ResourceServerProperties sso, OAuth2ClientContext oAuth2ClientContext) {
		this.sso = sso;
		this.oAuth2ClientContext = oAuth2ClientContext;
	}

	@Bean
	public RequestInterceptor getUserFeignClientInterceptor() {
		return new UserFeignClientInterceptor();
	}

	@Bean
	@ConfigurationProperties(prefix = "security.oauth2.client")
	public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
		return new ClientCredentialsResourceDetails();
	}

	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor() {
		return new OAuth2FeignRequestInterceptor(this.oAuth2ClientContext, clientCredentialsResourceDetails());
	}

	@Bean
	public OAuth2RestOperations restTemplate(OAuth2ClientContext auth2ClientContext) {
		return new OAuth2RestTemplate(clientCredentialsResourceDetails(), auth2ClientContext);
	}

	@Bean
	@Primary
	public ResourceServerTokenServices resourceServerTokenServices() {
		return new UserInfoTokenServices(this.sso.getUserInfoUri(), this.sso.getClientId());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// .antMatchers(HttpMethod.POST, "/").permitAll()
				// .antMatchers(HttpMethod.OPTIONS, "/").permitAll()
				.antMatchers("/actuator/**", "/pub/**").permitAll().anyRequest().authenticated();
		// http
		// .requestMatcher(new RequestHeaderRequestMatcher("Authorization"))
		// .authorizeRequests()
		// .antMatchers("/actuator/**").permitAll()
		// .anyRequest().fullyAuthenticated();
	}

	// @Override
	// public void configure(WebSecurity web) throws Exception {
	// web
	// .ignoring()
	// .antMatchers("/actuator/**");
	// }

}
