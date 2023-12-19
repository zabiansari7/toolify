package de.srh.toolify.validators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryPropsValidator {
	
	private String categoryName;

	@JsonCreator
	public CategoryPropsValidator(@JsonProperty("categoryName") String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String oldCategoryName) {
		this.categoryName = oldCategoryName;
	}

}
