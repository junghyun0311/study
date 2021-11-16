package com.example.demo.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {

	private Long member_info_seq;
	private String member_id;
}
