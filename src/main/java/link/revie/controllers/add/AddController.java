package link.revie.controllers.add;

import javax.validation.Valid;

import link.revie.model.entity.Article;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public interface AddController {

	@RequestMapping(value = "/AddForm", method = RequestMethod.GET)
	String form(Model model);

	@RequestMapping(value = "/AddForm", method = RequestMethod.POST)
	String form(@ModelAttribute Article article, BindingResult bindingResult, Model model);

	@RequestMapping(value = "/AddConfirm", method = RequestMethod.POST)
	String confirm(@Valid @ModelAttribute Article article, BindingResult bindingResult, Model model);

	@RequestMapping(value = "/AddRegister", method = RequestMethod.POST)
	String register(@ModelAttribute Article article, Model model);

}
