package de.srh.toolify.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.ToolifyResponse;
import de.srh.toolify.entities.ProductEntity;
import de.srh.toolify.services.ProductService;

@Validated
@RestController
@RequestMapping("/admin/api/products")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public ResponseEntity<List<ProductEntity>> getAllProducts(){
		try {
			List<ProductEntity> products = productService.getAllProducts();
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping(value = "{productId}")
	public ResponseEntity<ProductEntity> getProductByProductId(@PathVariable final Long productId){
		try {
			ProductEntity product = productService.getProductByProductId(productId);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping
	public ResponseEntity<ToolifyResponse> postProduct(@RequestBody final ProductEntity product) {
		Long newProductId = productService.saveProduct(product);
		return new ResponseEntity<>(
				new ToolifyResponse(
						String.format("New Product with productId '%d' created successfully.", newProductId), 
						201, 
						HttpStatus.CREATED
				), 
				HttpStatus.CREATED);
	}
	
	@PutMapping(value = "{productId}")
	public ResponseEntity<ToolifyResponse> editProductById(@PathVariable final Long productId, @RequestBody final Map<String, Object> productProps) {
		Long newProductId = productService.updateProduct(productId, productProps);
		return new ResponseEntity<>(
				new ToolifyResponse(
						String.format("Product with productId '%d' updated successfully.", newProductId), 
						201, 
						HttpStatus.CREATED
				), 
				HttpStatus.CREATED);
	}
}
