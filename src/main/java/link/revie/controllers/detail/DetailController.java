package link.revie.controllers.detail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/Detail")
public interface DetailController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	String detail(int id, Model model);
	
}
