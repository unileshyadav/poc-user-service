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

package com.cymmetri.user.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cymmetri.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static Map<String, List<User>> orgnizationDB = new HashMap<String, List<User>>();

	static {
		orgnizationDB = new HashMap<String, List<User>>(0);

		List<User> lst = new ArrayList<User>();
		lst.add(new User("Pankaj kedar", "pk@unotech.com"));
		lst.add(new User("Manoj Borkar", "mb@unotech.com"));

		orgnizationDB.put("unotech", lst);

		lst = new ArrayList<User>();
		lst.add(new User("Sachin Joshi", "sj@abc.com"));
		lst.add(new User("Dheeraj Jadhav", "dj@abc.com"));

		orgnizationDB.put("abc", lst);

	}

	public List<User> findByOrgnization(String orgnization) {
		this.logger.info("[UserRepository] findByOrgnization called for {}", orgnization);

		List<User> users = orgnizationDB.get(orgnization);
		return users;
	}

}
