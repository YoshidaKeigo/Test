package link.revie.controllers.search;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Search")
public interface SearchController {

	@RequestMapping(value = "/Title/{title}", method = RequestMethod.GET)
	String title(String title, Model model);

	@RequestMapping(value = "/String/{string}", method = RequestMethod.GET)
	String string(String string, Model model);

}
