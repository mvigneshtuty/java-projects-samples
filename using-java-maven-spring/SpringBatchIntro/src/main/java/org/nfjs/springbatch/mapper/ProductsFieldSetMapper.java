package org.nfjs.springbatch.mapper;

import org.nfjs.springbatch.domain.Product;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class ProductsFieldSetMapper implements FieldSetMapper<Product> {

	public Product mapFieldSet(FieldSet fset) throws BindException {
		Product p = new Product();
		p.setId(fset.readString("PRODUCT_ID"));
		p.setName(fset.readString("NAME"));
		p.setDesc(fset.readString("DESCRIPTION"));
		p.setPrice(fset.readString("PRICE"));
		return p;
	}

}
