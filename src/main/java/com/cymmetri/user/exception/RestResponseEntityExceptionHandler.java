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

import com.cymmetri.user.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		this.log.error("Exception: ", ex);

		return this.exception(new InvalidArgumentsException());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception ex) {
		this.log.error("Exception: ", ex);
		return buildResponseEntity(new Response(ex), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
		String debugMessage = String.format("Exception: ");
		this.log.error(debugMessage, ex);
		return buildResponseEntity(new Response(ex), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> exception(UserNotFoundException ex) {
		this.log.error("Exception: ", ex);
		return buildResponseEntity(new Response(ex), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidArgumentsException.class)
	public ResponseEntity<Object> exception(InvalidArgumentsException ex) {
		this.log.error("Exception: ", ex);
		return buildResponseEntity(new Response(ex), HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Object> buildResponseEntity(Response response, HttpStatus status) {
		return new ResponseEntity<>(response, status);
	}

}
