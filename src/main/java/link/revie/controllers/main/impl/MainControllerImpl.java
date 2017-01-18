package link.revie.controllers.main.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.google.common.collect.Lists;

import link.revie.controllers.main.MainController;
import link.revie.model.CategoryType;

@Component
public class MainControllerImpl implements MainController {

	@Override
	public String main(Model model) {
		List<CategoryType> categories = Lists.newArrayList(CategoryType.values());
		
		model.addAttribute("categories", categories);	
		return "main";
	}
	
}
