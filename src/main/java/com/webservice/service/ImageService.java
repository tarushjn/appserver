package com.webservice.service;
import java.util.List;
import com.webservice.bean.Image;


/**
 * Created by Saurabh on 15-04-2017.
 */
public interface ImageService {
    public List<Image> getImages();
    public Image getImage(Long imageId);
    public int deleteImage(Long imageId);
    public int updateImage(Image image);
    public int createImage(Image image);
}
