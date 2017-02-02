package link.revie.controllers.update;

import javax.validation.Valid;

import link.revie.model.entity.Article;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Update")
public interface UpdateController {

	@RequestMapping(value = "/Form/{id}", method = RequestMethod.GET)
	String form(int id, Model model);

	@RequestMapping(value = "/Form/{id}", method = RequestMethod.POST)
	String form(@ModelAttribute Article article, BindingResult bindingResult, Model model);

	@RequestMapping(value = "/Confirm", method = RequestMethod.POST)
	String confirm(@Valid @ModelAttribute Article article, BindingResult bindingResult, Model model);

	@RequestMapping(value = "/Register", method = RequestMethod.POST)
	String register(@ModelAttribute Article article, Model model);

}
