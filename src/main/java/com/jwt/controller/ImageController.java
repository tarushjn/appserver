package com.jwt.controller;
import java.io.IOException;
import java.util.List;

import com.amazonaws.Response;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.jwt.service.S3Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.jwt.bean.Image;
import com.jwt.service.ImageService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

/**
 * Created by Saurabh on 15-04-2017.
 */
@RestController
public class ImageController {

	@Autowired
	private ImageService imageService;

	@Autowired
	private S3Wrapper s3Wrapper;


	@Controller
	public class HomeController {@RequestMapping(value="/")
	public String index() {
		return "index";
		}
	}

	@RequestMapping(value = "/image", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Image>> images() {

		HttpHeaders headers = new HttpHeaders();
		List<Image> images = imageService.getImages();

		if (images == null) {
			return new ResponseEntity<List<Image>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(images.size()));
		return new ResponseEntity<List<Image>>(images, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public ResponseEntity<Image> getImage(@PathVariable("id") Long imageId) {
		Image image = imageService.getImage(imageId);
		if (image == null) {
			return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Image>(image, HttpStatus.OK);
	}

	@RequestMapping(value = "/image/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Image> deleteImage(@PathVariable("id") Long imageId) {
		HttpHeaders headers = new HttpHeaders();
		Image image = imageService.getImage(imageId);
		if (image == null) {
			return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
		}
		imageService.deleteImage(imageId);
		headers.add("Image Deleted - ", String.valueOf(imageId));
		return new ResponseEntity<Image>(image, headers, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/image", method = RequestMethod.POST,produces = "application/json")
	public ResponseEntity<Image> createImage(@RequestBody Image image) {
		HttpHeaders headers = new HttpHeaders();
		if (image == null) {
			return new ResponseEntity<Image>(HttpStatus.BAD_REQUEST);
		}
		imageService.createImage(image);
		headers.add("Image Created  - ", String.valueOf(image.getImageId()));
		return new ResponseEntity<Image>(image, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Image> updateImage(@PathVariable("id") Long imageId, @RequestBody Image image) {
		HttpHeaders headers = new HttpHeaders();
		Image isExist = imageService.getImage(imageId);
		if (isExist == null) {
			return new ResponseEntity<Image>(HttpStatus.NOT_FOUND);
		} else if (image == null) {
			return new ResponseEntity<Image>(HttpStatus.BAD_REQUEST);
		}
		imageService.updateImage(image);
		headers.add("Image Updated  - ", String.valueOf(imageId));
		return new ResponseEntity<Image>(image, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public List<PutObjectResult> upload(@RequestParam("file") MultipartFile[] multipartFiles) {
		return s3Wrapper.upload(multipartFiles);
		}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ResponseEntity<byte[]> download(@RequestParam String key) throws IOException {
		return s3Wrapper.download(key);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<S3ObjectSummary> list() throws IOException {
		return s3Wrapper.list();
	}

}

