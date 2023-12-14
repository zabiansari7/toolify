package de.srh.toolify.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import de.srh.toolify.validators.ProductPropsValidator;
import de.srh.toolify.validators.ValidatorUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;

@RestController
@Validated
@Tag(name = "Products", description = "The Product APIs for admin")
@RequestMapping("/private")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
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
	
	@GetMapping(value = "/admin/products/product", params = "categoryName")
	public ResponseEntity<List<ProductEntity>> getProductByCategoryName(@PathParam("categoryName") final String categoryName){
		try {
			List<ProductEntity> products = productService.getProductByCategoryName(categoryName);
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/admin/products/product")
	public ResponseEntity<ToolifyResponse> postProduct(@RequestBody final Map<String, Object> product) {
		ProductEntity productEntity;
		try {
			productEntity = (ProductEntity) ValidatorUtil.validate(product, ProductEntity.class);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ToolifyResponse(
							String.format(e.getCause().getLocalizedMessage()), 
							400, 
							HttpStatus.BAD_REQUEST
					), 
					HttpStatus.BAD_REQUEST);
		}
		Long newProductId = productService.saveProduct(productEntity);
		return new ResponseEntity<>(
				new ToolifyResponse(
						String.format("New Product with productId '%d' created successfully.", newProductId), 
						201, 
						HttpStatus.CREATED
				), 
				HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/admin/products/product/{productId}")
	public ResponseEntity<ToolifyResponse> editProductById(@PathVariable final Long productId, @RequestBody final Map<String, Object> productProps) {
		try {
			ValidatorUtil.validate(productProps, ProductPropsValidator.class);
		} catch (Exception e) {
			return new ResponseEntity<>(
					new ToolifyResponse(
							String.format(e.getMessage()), 
							400, 
							HttpStatus.BAD_REQUEST
					), 
					HttpStatus.BAD_REQUEST);
		}
		Long newProductId = productService.updateProduct(productId, productProps);
		return new ResponseEntity<>(
				new ToolifyResponse(
						String.format("Product with productId '%d' updated successfully", newProductId), 
						201, 
						HttpStatus.CREATED
				), 
				HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/admin/products/product/{productId}")
	public ResponseEntity<ToolifyResponse> deleteProduct(@PathVariable final Long productId) {
		productService.deleteProduct(productId);
		return new ResponseEntity<>(
				new ToolifyResponse(
						String.format("Product with productId '%d' has been deleted successfully.", productId), 
						201, 
						HttpStatus.CREATED
				), 
				HttpStatus.CREATED);
	}
}
