package link.revie.controllers.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	void main();

}
