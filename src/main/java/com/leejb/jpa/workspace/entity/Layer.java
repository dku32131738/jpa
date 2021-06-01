package com.leejb.jpa.workspace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "feature", "style"})
public class Layer {
	
	@Id @GeneratedValue
	@Column(name = "layer_id")
	private String id;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "feature_id")
	private Feature feature;
	private String style;

	public Layer(String id, Feature feature, String style) {
		this.id = id;
		this.feature = feature;
		this.style = style;
	}
}
