package com.leejb.jpa.workspace.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "jdbc")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Jdbc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "jdbc_id")
	private int id;
	private String name;
	private String url;
	private String user;
	private String password;
	private String dbType;
	private boolean readOnly;
	
	public Jdbc(String name, String username, String password) {
		this.name = name;
		this.user = username;
		this.password = password;
		this.readOnly = false;
	}

}