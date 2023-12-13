package de.srh.toolify.services;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.srh.toolify.entities.CategoryEntity;
import de.srh.toolify.entities.ProductEntity;
import de.srh.toolify.exceptions.ProductException;
import de.srh.toolify.repositories.CategoryRepository;
import de.srh.toolify.repositories.ProductRepository;

@Service
public class ProductService {
	private static final Logger LOGGER = LogManager.getLogger(ProductService.class);
	private static final String CHANGED_CATEGORY_ID_KEY = "categoryTo";
	
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	
	@Autowired
	ModelMapper mapper;

	@Autowired
	public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
	
	public ProductEntity getProductByProductId(Long productId) {
		LOGGER.log(Level.INFO, String.format("Product with productId %d is being fetched", productId));
		return productRepository.findById(productId)
				.orElseThrow(() -> new ProductException(String.format("Product not found by productId %d", productId), null));
	}
	
	public Long saveProduct(ProductEntity product) {
		try {
			CategoryEntity category = categoryRepository
					.findByCategoryName(product.getCategory().getCategoryName())
						.orElseThrow(() -> new ProductException(String.format("Category name '%s' not found in the Toolify list", product.getCategory().getCategoryName()), null));
			product.setCategory(category);
			product.setCreatedOn(Instant.now());
			return productRepository.saveAndFlush(product).getProductId();
		} catch (Exception e) {
			e.printStackTrace();	
			throw new ProductException(e.getMessage(), e);
		}
	}
	
	public Long updateProduct(Long productId, Map<String, Object> productProps) {
		ProductEntity existingProduct = this.getProductByProductId(productId);
		if (productProps.containsKey(CHANGED_CATEGORY_ID_KEY)) {
			Long categoryId = Long.valueOf(productProps.get(CHANGED_CATEGORY_ID_KEY).toString());
			CategoryEntity category = this.getCategoryById(categoryId);
			existingProduct.setCategory(category);
			existingProduct.setUpdatedOn(Instant.now());
		}
		mapper.map(productProps, existingProduct);
		return productRepository.saveAndFlush(existingProduct).getProductId();
		
	}
	
	public void deleteProduct(Long productId) {
		ProductEntity existingProduct = this.getProductByProductId(productId);
		productRepository.delete(existingProduct);
	}
	
	private CategoryEntity getCategoryById(Long id) {
		return categoryRepository
			.findById(id)
			.orElseThrow(() -> new ProductException(String.format("Category with id %d not found in the Toolify list", id), null));
	}

	public List<ProductEntity> getProductByCategoryName(String categoryName) {
		CategoryEntity category = categoryRepository.findByCategoryName(categoryName).orElseThrow(() -> new ProductException(String.format("Category with category name '%s' not found.", categoryName), null));
		return productRepository.findByCategory(category).orElseThrow(() -> new ProductException(String.format("Product with category name '%s' not found.", categoryName), null));
	}
}
