package com.BW.service;

import com.BW.model.Image;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ImageServiceImpl implements ImageService {

    private static final AtomicInteger counter = new AtomicInteger();
    static List<Image> images = new ArrayList<Image>(
            Arrays.asList(
                    new Image(counter.incrementAndGet(), "www.google.com"),
                    new Image(counter.incrementAndGet(), "www.nytimes.com"),
                    new Image(counter.incrementAndGet(), "www.brainwizsolution.com"),
                    new Image(counter.incrementAndGet(), "www.bw.com")));

    @Override
    public List<Image> getAll(int offset, int count) {
        return images;
    }

    @Override
    public Image findById(int id) {
        for (Image image : images){
            if (image.getId() == id){
                return image;
            }
        }
        return null;
    }

    @Override
    public Image findByName(String name) {
        for (Image image : images){
            if (image.getImageurl().equals(name)){
                return image;
            }
        }
        return null;
    }

    @Override
    public void create(Image image) {
        image.setId(counter.incrementAndGet());
        images.add(image);
    }

    @Override
    public void update(Image image) {
        int index = images.indexOf(image);
        images.set(index, image);
    }

    @Override
    public void delete(int id) {
        Image image = findById(id);
        images.remove(image);
    }

    @Override
    public boolean exists(Image image) {

        return findByName(image.getImageurl()) != null;
    }
}
