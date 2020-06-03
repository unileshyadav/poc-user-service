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

package com.cymmetri.user.config.principal;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class PrincipalContextListener implements ServletRequestListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String TENANT_HEADER = "tenant";

	private final String USER_HEADER = "user";

	private final String ROLE_HEADER = "role";

	@Override
	public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
		PrincipalContext.clear();
	}

	@Override
	public void requestInitialized(ServletRequestEvent servletRequestEvent) {
		HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
		String tenantId = request.getHeader(this.TENANT_HEADER);
		String userId = request.getHeader(this.USER_HEADER);
		String roles = request.getHeader(this.ROLE_HEADER);

		Principal principal = new Principal(userId, tenantId, roles);

		this.logger.info("Incomeing request {}", principal.toString());
		PrincipalContext.setPrincipal(principal);
	}

}
