package link.revie.controllers.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public interface MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	String main();

}
