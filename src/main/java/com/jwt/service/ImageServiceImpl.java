package com.jwt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.bean.Image;
import com.jwt.dao.ImageDao;

@Service("imageService")
public class ImageServiceImpl implements ImageService {
	@Autowired
	private ImageDao imageDao;

	public List<Image> getImages() {
		List<Image> images = imageDao.getImages();
		return images;
	}

	public Image getImage(Long imageId) {
		Image image = imageDao.getImage(imageId);
		return image;
	}

	public int deleteImage(Long imageId) {
		return imageDao.deleteImage(imageId);
	}

	public int updateImage(Image image) {
		return imageDao.updateImage(image);
	}

	public int createImage(Image image) {
		return imageDao.createImage(image);
	}
public String saveImage(Image image){
		return imageDao.saveImage(image);
}

}

