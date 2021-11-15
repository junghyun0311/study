package com.example.demo.JPA;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_member_authorities_code")
@Getter
@Setter
@ToString
public class MemberAuthoritiesCode {
	
	@Id
	@Column
	@GeneratedValue
	private long memberAuthoritiesCodeSeq;
	
	@Column(nullable = false)
	private String authority;
	
	@Column
	@CreationTimestamp
	private LocalDateTime registerDate;
	
	@OneToMany(mappedBy = "memberAuthoritiesCode" ,fetch = FetchType.LAZY)
	private List<MemberAuthoritiesMapping> memberAuthoritiesMappingList = new ArrayList<>();
	  
}
