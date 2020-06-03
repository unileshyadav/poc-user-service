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

package com.cymmetri.user.exception;

public enum ErrorCode {

	/**
	 * Error-codes.
	 */
	UNKNOWN,

	/**
	 * {@code USER_NOT_FOUND}.
	 */
	USER_NOT_FOUND,

	/**
	 * {@code INVALID_ARGUMENTS}.
	 */
	INVALID_ARGUMENTS;

	/**
	 * servicePrefix is prefix for all the error-codes from current module, it will help
	 * to identify service-module from API error-code.
	 */
	private String servicePrefix = "USRSRVC";

	public String code() {

		StringBuilder errorCode = new StringBuilder();
		// @formatter:off
		errorCode
		.append(this.servicePrefix)
		.append('.')
		.append(this);
		// @formatter:on
		return errorCode.toString();
	}

}
