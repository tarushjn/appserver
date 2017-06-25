package com.jwt.bean;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Created by Saurabh on 15-04-2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
	private Long imageId;
	private String imageUrl;
	private String faces;
	private Long age;

	@JsonCreator
	public Image(@JsonProperty("imageId") Long imageId, @JsonProperty("imageUrl") String imageUrl,
				 @JsonProperty("faces") String faces, @JsonProperty("age") Long age) {
		this.imageId = imageId;
		this.imageUrl = imageUrl;
		this.faces = faces;
		this.age = age;
	}

	public Image() {

	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getFaces() {
		return faces;
	}

	public void setFaces(String faces) {
		this.faces = faces;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}


	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Image Id:- " + getImageId());
		str.append(" First Name:- " + getImageUrl());
		str.append("No. of Faces:- " + getFaces());
		str.append(" Age:- " + getAge());
		return str.toString();
	}
}
