package com.leejb.jpa.workspace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="feature")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name"})
public class Feature {
	
	@Id
	@GeneratedValue
	@Column(name = "feature_id")
	private int id;
	private String name;
	private String storageCRS;
	private String featureTypeName;
	private String featureTypeNamespace;
	private String FeatureTypePrefix;
	
	@ManyToOne
	@JoinColumn(name = "jdbc_id")
	private Jdbc jdbc;
	
	private String sqlStatement;
	private String bboxStatement;

	public Feature(String name, int srs, String type, Jdbc jdbc, String query, String bboxQuery) {
		this.name = name;
		this.storageCRS = "EPSG:" + srs;
		this.featureTypeName = type;
		this.featureTypeNamespace = "http://www.deegree.org/app";
		this.FeatureTypePrefix = "app";
		this.jdbc = jdbc;
		this.sqlStatement = query;
		this.bboxStatement = bboxQuery;
	}
	
}
