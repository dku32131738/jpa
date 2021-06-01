package com.leejb.jpa.workspace.dto;

import com.leejb.jpa.workspace.entity.Jdbc;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
public class JdbcDto {
	
	private String id;
	private String dbType;
	private String host;
	private int port;
	private String dbName;
	private String username;
	private String password;
	
	public Jdbc getJdbc() {
		Jdbc jdbc = new Jdbc(this.id,this.username,this.password);
		jdbc.setDbType(this.dbType);
		jdbc.setReadOnly(false);
		jdbc.setUrl(getUrl());
		return jdbc;
	}
	
	private String getUrl() {
		String resultUrl;
		switch (this.dbName) {
		case "oracle": resultUrl = "jdbc:oracle:thin:@//" + this.host + ":" + this.port + "/" + this.dbName;
		break;
		default: resultUrl = null;
		break;
		}
		return resultUrl;
	}

}
