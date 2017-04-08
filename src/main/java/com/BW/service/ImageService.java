package com.BW.service;

import com.BW.model.Image;

import java.util.List;

public interface ImageService {

    List<Image> getAll();

    Image findById(int id);

    Image findByName(String name);

    void create(Image image);

    void update(Image image);

    void delete(int id);

    boolean exists(Image image);
}
