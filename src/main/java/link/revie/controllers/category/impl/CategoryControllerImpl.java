package link.revie.controllers.category.impl;

import link.revie.controllers.category.CategoryController;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class CategoryControllerImpl implements CategoryController {

	@Override
	public void get(@PathVariable String category) {
		// FIXME
	}

}
