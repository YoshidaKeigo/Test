package link.revie.controllers.movietype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/MovieType")
public interface MovieTypeController {

	@RequestMapping(value = "/{type}", method = RequestMethod.GET)
	String get(String type, Model model);

}
