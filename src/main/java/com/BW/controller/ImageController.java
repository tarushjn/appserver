package com.BW.controller;

import com.BW.model.Image;
import com.BW.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final Logger LOG = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    // =========================================== Get All Images ==========================================


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Image>> getAll() {
       List<Image> images = imageService.getAll();

        if (images == null || images.isEmpty()){
            LOG.info("no images found");
            return new ResponseEntity<List<Image>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Image>>(images, HttpStatus.OK);
    }

    // =========================================== Get Image By ID =========================================

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Image> get(@PathVariable("id") int id){
        LOG.info("getting image with id: {}", id);
        Image image = imageService.findById(id);

        if (image == null){
            LOG.info("image with id {} not found", id);
            return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Image>(image, HttpStatus.OK);
    }

    // =========================================== Create New Image ========================================

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Image image, UriComponentsBuilder ucBuilder){
        LOG.info("creating new image: {}", image);

        if (imageService.exists(image)){
            LOG.info("a image with name " + image.getImageurl() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        imageService.create(image);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/image/{id}").buildAndExpand(image.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // =========================================== Update Existing Image ===================================

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Image> update(@PathVariable int id, @RequestBody Image image){
        LOG.info("updating image: {}", image);
        Image currentImage = imageService.findById(id);

        if (currentImage == null){
            LOG.info("image with id {} not found", id);
            return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
        }

        currentImage.setId(image.getId());
        currentImage.setImageurl(image.getImageurl());

        imageService.update(image);
        return new ResponseEntity<Image>(currentImage, HttpStatus.OK);
    }

    // =========================================== Delete Image ============================================

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        LOG.info("deleting image with id: {}", id);
        Image image = imageService.findById(id);

        if (image == null){
            LOG.info("Unable to delete. Image with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        imageService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
