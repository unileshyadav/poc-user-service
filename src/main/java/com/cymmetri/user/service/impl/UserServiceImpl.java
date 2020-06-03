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

package com.cymmetri.user.service.impl;

import java.util.List;

import com.cymmetri.user.entity.User;
import com.cymmetri.user.exception.UserNotFoundException;
import com.cymmetri.user.repository.UserRepository;
import com.cymmetri.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findByOrgnization(String orgnization) throws UserNotFoundException {

		this.logger.info("[UserService] findByOrgnization called for {}", orgnization);

		List<User> users = this.userRepository.findByOrgnization(orgnization);
		if (users == null) {
			// users = Collections.emptyList();
			throw new UserNotFoundException();
		}
		return users;
	}

}
