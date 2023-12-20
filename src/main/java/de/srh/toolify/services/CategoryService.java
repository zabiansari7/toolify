package de.srh.toolify.services;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.srh.toolify.entities.CategoryEntity;
import de.srh.toolify.exceptions.CategoryException;
import de.srh.toolify.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	public CategoryRepository categoryRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	private CategoryEntity getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryException(String.format("Category with id '%d' not found.", categoryId), null));
	}
	
	public List<CategoryEntity> getCategories() {
		return categoryRepository.findAll();
	}
	
	public long saveCategory(CategoryEntity categoryEntity) {
		try {
			categoryEntity.setCreatedOn(Instant.now());
			return categoryRepository.saveAndFlush(categoryEntity).getCategoryId();
		} catch (Exception e) {
			throw new CategoryException(e.getMessage(), e);
		}

	}

	public long updateCategory(Map<String, Object> categoryProps, Long categoryId) {
		try {
			String categoryName = categoryProps.get("categoryName").toString();
			CategoryEntity existingCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryException(String.format("Category with category id '%d' not found", categoryId), null));
			existingCategory.setUpdatedOn(Instant.now());
			existingCategory.setCategoryName(categoryName);
			return categoryRepository.saveAndFlush(existingCategory).getCategoryId();
		} catch (Exception e) {
			throw new CategoryException(e.getMessage(), e);
		}
	}

	public void deleteCategory(Long categoryId) {
		try {
			CategoryEntity category = getCategoryById(categoryId);
			categoryRepository.delete(category);
		} catch (Exception e){
			throw new CategoryException(e.getMessage(), e);
		}
	}
	
}
