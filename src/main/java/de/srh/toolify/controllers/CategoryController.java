package de.srh.toolify.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.srh.toolify.dto.ToolifyResponse;
import de.srh.toolify.entities.CategoryEntity;
import de.srh.toolify.services.CategoryService;
import de.srh.toolify.validators.CategoryPropsValidator;
import de.srh.toolify.validators.ValidatorUtil;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/private/admin/categories")
@Tag(name = "Categories", description = "The Category api for administration purpose.")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/all")
	public List<CategoryEntity> getAllCategories(){
		return categoryService.getCategories();
	}
	
	@PostMapping("/category")
	public ResponseEntity<ToolifyResponse> postCategory(@RequestBody final Map<String, Object> category){
		CategoryEntity categoryEntity;
		try {
			categoryEntity = (CategoryEntity) ValidatorUtil.validate(category, CategoryEntity.class);
		} catch (Exception e) {
			return new ResponseEntity<>(
                    new ToolifyResponse(e.getMessage(), 400, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
		}
		long newId = categoryService.saveCategory(categoryEntity);
		return new ResponseEntity<>(
                new ToolifyResponse(String.format("New category with id '%d' created successfully", newId), 201, HttpStatus.CREATED), HttpStatus.CREATED);
	}
	
	@PutMapping("/category/{categoryId}")
	public ResponseEntity<ToolifyResponse> editCategory(@RequestBody final Map<String, Object> categoryProps, @PathVariable final Long categoryId){
		try {
			ValidatorUtil.validate(categoryProps, CategoryPropsValidator.class);
		} catch (Exception e) {
			return new ResponseEntity<>(
                    new ToolifyResponse(e.getMessage(), 400, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
		}
		long id = categoryService.updateCategory(categoryProps, categoryId);
		return new ResponseEntity<>(
                new ToolifyResponse(String.format("Category with id '%d' has been updated successfully", id), 201, HttpStatus.CREATED), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/category/{categoryId}")
	public ResponseEntity<ToolifyResponse> deleteCategoryById(@PathVariable final Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(
                new ToolifyResponse(String.format("Category with id '%d' has been deleted successfully", categoryId), 201, HttpStatus.CREATED), HttpStatus.CREATED);
	}
}
