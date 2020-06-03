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

package com.cymmetri.user.endpoint;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.cymmetri.user.config.principal.PrincipalContext;
import com.cymmetri.user.dto.Response;
import com.cymmetri.user.entity.User;
import com.cymmetri.user.exception.UserNotFoundException;
import com.cymmetri.user.service.UserService;
import io.swagger.annotations.Api;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/", produces = "application/json")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@GetMapping("srvc/user/info")
	public ResponseEntity<Response> welcome() {
		JSONObject json = new JSONObject();
		json.put("date", LocalDate.now());
		json.put("time", LocalTime.now());
		json.put("title", "User Service");

		Response response = new Response();
		response.succeed();
		response.setData(json.toMap());

		return ResponseEntity.ok().body(response);
	}

	@GetMapping("user/{orgnization}")
	public ResponseEntity<Response> getUsers(@PathVariable String orgnization) throws UserNotFoundException {

		this.logger.info("[UserController] getUsers called for {}", orgnization);

		List<User> users = this.userService.findByOrgnization(orgnization);
		Response response = new Response();
		response.succeed();
		response.setData(users);

		return ResponseEntity.ok().body(response);
	}

	@GetMapping("srvc/user/current")
	public ResponseEntity<Response> getCurrentUser() {
		Response response = new Response();
		response.succeed();
		response.setData(PrincipalContext.getPrincipal());

		return ResponseEntity.ok().body(response);
	}

}
