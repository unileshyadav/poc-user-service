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

package com.cymmetri.user.dto;

import java.util.Date;

import com.cymmetri.user.exception.CustomException;
import com.cymmetri.user.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Response {

	public Response() {
		this.timestamp = new Date();
	}

	public Response(Throwable exception) {
		this();
		this.fail();

		String errorCodeString = ErrorCode.UNKNOWN.code();

		if (exception instanceof CustomException) {

			CustomException ex = (CustomException) exception;
			ErrorCode errorCode = ex.getErrorCode();

			errorCodeString = errorCode.code();
		}

		this.setErrorCode(errorCodeString);
	}

	public Response(Throwable exception, String message) {
		this(exception);
		this.setMessage(message);
	}

	private Boolean success;

	private Object data;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy hh:mm:ss")
	private Date timestamp;

	// @JsonInclude(Include.NON_EMPTY)
	private String message;

	// @JsonInclude(Include.NON_EMPTY)
	private String errorCode;

	public Boolean getSuccess() {
		return this.success;
	}

	private void setSuccess(Boolean success) {
		this.success = success;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	private void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void fail() {
		this.setSuccess(Boolean.FALSE);
	}

	public void succeed() {
		this.setSuccess(Boolean.TRUE);
	}

}
