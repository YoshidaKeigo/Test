package link.revie.controllers.delete;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Delete")
public interface DeleteController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	String execute(int id, Model model);

}
