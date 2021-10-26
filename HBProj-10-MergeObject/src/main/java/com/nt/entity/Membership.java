package com.nt.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Membership implements Serializable {

	private Long mid;
	private String name;
	private String addrs;
	private Long rewardPoints;
}
