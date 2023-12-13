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
		categoryEntity.setCreatedOn(Instant.now());
		return categoryRepository.saveAndFlush(categoryEntity).getCategoryId();	
	}

	public long updateCategory(Map<String, Object> categoryProps) {
		String oldName = categoryProps.get("oldCategoryName").toString();
		String newName = categoryProps.get("newCategoryName").toString();
		CategoryEntity existingCategory = categoryRepository.findByCategoryName(oldName).orElseThrow(() -> new CategoryException(String.format("Category with category name '%s' not found", oldName), null));
		existingCategory.setUpdatedOn(Instant.now());
		existingCategory.setCategoryName(newName);
		return categoryRepository.saveAndFlush(existingCategory).getCategoryId();
	}

	public void deleteCategory(Long categoryId) {
		CategoryEntity category = getCategoryById(categoryId);
		categoryRepository.delete(category);
	}
	
}
