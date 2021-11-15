package com.example.demo.JPA;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_member_authorities_mapping")
@Getter
@Setter
@ToString
public class MemberAuthoritiesMapping {
	
	@Id
	@Column
	@GeneratedValue
	private long memberAuthoritiesMappingSeq;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_info_seq", referencedColumnName = "memberInfoSeq")
	private MemberInfo memberInfo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="member_authorities_code_seq", referencedColumnName="memberAuthoritiesCodeSeq")
	private MemberAuthoritiesCode memberAuthoritiesCode;
	
	@Column
	@CreationTimestamp
	private LocalDate registerDate;
	
}
