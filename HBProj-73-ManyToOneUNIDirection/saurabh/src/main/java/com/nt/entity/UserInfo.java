package com.nt.entity;

import java.io.Serializable;
import java.util.Set;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserInfo implements Serializable{
	private Integer userId;
	@NonNull
	private String username;
	@NonNull
	private String addrs;
	//Special Property to build 1..* association(by holding multiple object of child class)
	private Set<PhoneNumber> phones;
	
	public UserInfo() {
		System.out.println("UserInfo.UserInfo()-0 Param Constructor");
	}
}
