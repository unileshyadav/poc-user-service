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

import javax.servlet.ServletRequestListener;

import com.cymmetri.user.config.SwaggerConfig;
import com.cymmetri.user.config.principal.PrincipalContextListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableOAuth2Sso
@EnableFeignClients
@Import({ SwaggerConfig.class })
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	ServletListenerRegistrationBean<ServletRequestListener> tenantContextListener() {
		ServletListenerRegistrationBean<ServletRequestListener> srb = new ServletListenerRegistrationBean<>();
		srb.setListener(new PrincipalContextListener());
		return srb;
	}

}
