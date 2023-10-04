package com.edesign.cabmanager.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edesign.cabmanager.dto.ResponseDto;
import com.edesign.cabmanager.entity.Category;
import com.edesign.cabmanager.service.CategoryService;

// CRUD operations for category
// todo: implement ResponseDto, exception handling etc.

@RestController
public class CategoryController {

	@Autowired 
	CategoryService categoryService;
	
	/**
	 * Get all categories
	 * @return
	 */
	@GetMapping("/categories")
	public Page<Category> getCategories(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "categoryId") String sort, @RequestParam(defaultValue = "ASC") String sortOrder){
		return categoryService.getCategories(page,size, sort, sortOrder);
	}
	
	/**
	 * Get one category
	 * @param categoryId
	 * @return
	 */
	@GetMapping("/categories/{categoryId}")
	public Category getCategory(@PathVariable int categoryId){
		return categoryService.getCategory(categoryId);
	}
	
	/**
	 * Add category
	 * @param category
	 * @return
	 */
	@PostMapping("/categories")
	public Category addCategory(@RequestBody Category category){
		return categoryService.addCategory(category);
	}
	
	/**
	 * Update category
	 * @param category
	 * @return
	 */
	@PutMapping("/categories")
	public Category updateCategory(@RequestBody Category category){
		return categoryService.updateCategory(category);
	}
	
	/**
	 * Delete category
	 * @param categoryId
	 * @return
	 */
	@DeleteMapping("/categories/{categoryId}")
	public ResponseDto updateCategory(@PathVariable int categoryId){
		categoryService.deleteCategory(categoryId);
		return new ResponseDto("Category is deleted sucessfully with categoryId : "+categoryId, new Date(),HttpStatus.OK.name(),null);
	}
}
