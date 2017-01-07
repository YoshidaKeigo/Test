package link.revie.service.category.impl;

import java.util.List;

import link.revie.model.entity.Category;
import link.revie.model.repository.CategoryRepository;
import link.revie.service.category.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

}
