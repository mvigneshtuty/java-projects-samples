package org.nfjs.springws.pcatalog.core.repository;

import java.util.List;

import org.nfjs.springws.pcatalog.core.bean.Product;
import org.nfjs.springws.pcatalog.domain.request.AddProductRequestType;
import org.nfjs.springws.pcatalog.domain.request.DeleteProductRequestType;
import org.nfjs.springws.pcatalog.domain.request.GetProductRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "productRepository")
public class ProductRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public int addNewProduct(AddProductRequestType newProductObj) {
		return jdbcTemplate.update("insert into products (product,category) values (?,?)", newProductObj.getProduct(),
				newProductObj.getProductCategory());
	}

	public List<Product> getProduct(GetProductRequestType getProductObj) {
		List<Product> productsList = jdbcTemplate.query("select * from products where category = ?",
				new Object[] { getProductObj.getProductCategory() }, new BeanPropertyRowMapper<Product>(Product.class));
		return productsList;
	}

	public int deleteProduct(DeleteProductRequestType deleteProductObj) {
		String deleteProductSql = "delete from products where product = ?";
		if (deleteProductObj.getProductCategory() != null) {
			deleteProductSql = deleteProductSql + "and category = ?";
			return jdbcTemplate.update(deleteProductSql, deleteProductObj.getProduct(),
					deleteProductObj.getProductCategory());
		}
		return jdbcTemplate.update(deleteProductSql, deleteProductObj.getProduct());
	}
}
