package com.jwt.dao;

import java.util.List;
import com.jwt.bean.Image;

public interface ImageDao {
	 List<Image> getImages();
	Image getImage(Long imageId);
	int deleteImage(Long imageId);
	 int updateImage(Image image);
	 int createImage(Image image);
	 String saveImage(Image image);
}
