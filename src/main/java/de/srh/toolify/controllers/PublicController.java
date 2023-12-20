package de.srh.toolify.controllers;

import java.util.List;

import de.srh.toolify.entities.CategoryEntity;
import de.srh.toolify.services.CategoryService;
import jakarta.websocket.server.PathParam;
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
@RequestMapping("/public")
public class PublicController {

	private final ProductService productService;
	private final CategoryService categoryService;
	
	@Autowired
	public PublicController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
        this.categoryService = categoryService;
    }
	
	@GetMapping(value = "/products/all")
	public ResponseEntity<List<ProductEntity>> getAllProducts(){
		try {
			List<ProductEntity> products = productService.getAllProducts();
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping(value = "/products/product/{productId}")
	public ResponseEntity<ProductEntity> getProductByProductId(@PathVariable final Long productId){
		try {
			ProductEntity product = productService.getProductByProductId(productId);
			return new ResponseEntity<>(product, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/products/product/{productId}/quantity")
	public ResponseEntity<ToolifyResponse> getMaxQuantityByProductId(@PathVariable final Long productId){
		try {
			int maxQuantity = productService.getMaxQuantityByProductId(productId);
			return new ResponseEntity<>(new ToolifyResponse(String.valueOf(maxQuantity), 200, HttpStatus.OK), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/categories/all")
	public List<CategoryEntity> getAllCategories(){
		return categoryService.getCategories();
	}

	@GetMapping(value = "/products/product", params = "categoryName")
	public ResponseEntity<List<ProductEntity>> getProductByCategoryName(@PathParam("categoryName") final String categoryName){
		try {
			List<ProductEntity> products = productService.getProductByCategoryName(categoryName);
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
