package de.srh.toolify.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.ToolifyResponse;
import de.srh.toolify.entities.ProductEntity;
import de.srh.toolify.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@Tag(name = "Products", description = "The Product APIs for all Users")
@RequestMapping("/public/products")
public class ProductPublicController {

private final ProductService productService;
	
	@Autowired
	public ProductPublicController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(value = "/all")
	public ResponseEntity<List<ProductEntity>> getAllProducts(){
		try {
			List<ProductEntity> products = productService.getAllProducts();
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping(value = "/product/{productId}")
	public ResponseEntity<ProductEntity> getProductByProductId(@PathVariable final Long productId){
		try {
			ProductEntity product = productService.getProductByProductId(productId);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/product/{productId}/quantity")
	public ResponseEntity<ToolifyResponse> getMaxQuantityByProductId(@PathVariable final Long productId){
		try {
			int maxQuantity = productService.getMaxQuantityByProductId(productId);
			return new ResponseEntity<>(new ToolifyResponse(String.valueOf(maxQuantity), 200, HttpStatus.OK), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
