package link.revie.controllers.add;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Add")
public interface AddController {

	@RequestMapping(value = "/Form", method = RequestMethod.GET)
	void form();

	@RequestMapping(value = "/Confirm", method = RequestMethod.GET)
	void confirm();

	@RequestMapping(value = "/Execute", method = RequestMethod.GET)
	void execute();

}
