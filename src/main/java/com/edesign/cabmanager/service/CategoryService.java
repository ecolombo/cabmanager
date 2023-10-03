package com.edesign.cabmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edesign.cabmanager.repository.CategoryRepository;
import com.edesign.cabmanager.entity.Category;
import com.edesign.cabmanager.exception.BadRequestException;
import com.edesign.cabmanager.exception.NotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	// Get all Categories
	// public Page<Category> getCategories(int page, int size, String sort, String sortOrder) {
	public Page<Category> getCategories(int page, int size, String sort, String sortOrder) {
		Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sortBy = Sort.by(direction,sort);
		Pageable pageable = PageRequest.of(page, size, sortBy);
		return categoryRepository.findAll(pageable);
	}	
	
	
	// Get one Category by CategoryId
	public Category getCategory(int CategoryId) {
		return categoryRepository.findById(CategoryId).get();
	}
	
	// Add Category
	public Category addCategory(Category category) {
		System.out.println("[CategoryService.addCategory] Try to create category with name:"+category.getName());
		if(categoryRepository.existsByName(category.getName()))
			throw new BadRequestException("Category with this name already exists.");
		return categoryRepository.save(category);
	}
	
	// Update Category
	public Category updateCategory(Category category) {
		if(category.getCategoryId() <=0 )
			throw new BadRequestException("CategoryId cannot be null or empty.");
		if(categoryRepository.existsById(category.getCategoryId()))
			return categoryRepository.save(category);
		else 
			throw new NotFoundException("Category does not exist with provided categoryId.");

	}
	
	// Delete Category
	public void deleteCategory(int categoryId) {
		if(categoryId <=0 )
			throw new BadRequestException("categoryId id cannot be null or empty.");
		if(categoryRepository.existsById(categoryId))
			categoryRepository.deleteById(categoryId);
		else 
			throw new NotFoundException("The category does not exist with provided categoryId.");
	}
	
	
}
