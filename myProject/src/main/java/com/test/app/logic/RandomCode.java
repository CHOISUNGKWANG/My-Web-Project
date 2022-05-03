package com.test.app.logic;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomCode {
	
	public String randomCode() {
		String code = RandomStringUtils.randomAlphanumeric(5);
		return code;
	}
	
}
