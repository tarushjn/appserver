package com.jwt.dao;

import java.util.List;
import com.jwt.bean.Image;

public interface ImageDao {
	public List<Image> getImages();
	public Image getImage(Long imageId);
	public int deleteImage(Long imageId);
	public int updateImage(Image image);
	public int createImage(Image image);
	public String saveImage(Image image);
}
