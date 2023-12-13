package de.srh.toolify.validators;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryPropsValidator {
	
	private String oldCategoryName;
	private String newCategoryName;

	@JsonCreator
	public CategoryPropsValidator(@JsonProperty("oldCategoryName") String oldCategoryName, @JsonProperty("newCategoryName") String newCategoryName) {
		this.oldCategoryName = oldCategoryName;
		this.newCategoryName = newCategoryName;
	}

	public String getCategoryName() {
		return oldCategoryName;
	}

	public void setCategoryName(String oldCategoryName) {
		this.oldCategoryName = oldCategoryName;
	}

	public String getNewCategoryName() {
		return newCategoryName;
	}

	public void setNewCategoryName(String newCategoryName) {
		this.newCategoryName = newCategoryName;
	}

}
