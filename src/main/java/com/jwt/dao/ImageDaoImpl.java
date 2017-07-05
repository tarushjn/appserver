package com.jwt.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jwt.bean.Image;

@Repository("imageDao")
public class ImageDaoImpl  implements ImageDao {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Image> getImages() {
		List<Image> images = null ;

		try {
			images = jdbcTemplate.query("SELECT * FROM image_results",new BeanPropertyRowMapper<Image>(Image.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return images;
	}

	public Image getImage(Long imageId) {
		Image image = null;
		try {
			image = jdbcTemplate.queryForObject("SELECT * FROM trn_employee WHERE employee_id = ?",
					new Object[] { imageId }, new BeanPropertyRowMapper<Image>(Image.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return image;

	}

	public int deleteImage(Long imageId) {
		int count = jdbcTemplate.update("DELETE from trn_employee WHERE employee_id = ?", new Object[] { imageId });
		return count;
	}

	public int updateImage(Image image) {
		int count = jdbcTemplate.update(
				"UPDATE trn_employee set first_name = ? , last_name = ? , age = ? where employee_id = ?", new Object[] {
						image.getImageUrl(), image.getFaces(), image.getAge(), image.getImageId() });
		return count;
	}

	public int createImage(Image image) {
		int count = jdbcTemplate.update(
				"INSERT INTO image_results (image_ID, no_of_faces, image_results_url) VALUES (?,?,?)", new Object[] {
						image.getImageId(), image.getFaces(), image.getImageUrl(),  image.getAge() });
		return count;
	}

	public String saveImage(Image image) {
		String count = jdbcTemplate.update("INSERT INTO image_details (image_URL) VALUES (?)", new Object[] {
						image.getImageUrl() });
		return count;
	}

}
