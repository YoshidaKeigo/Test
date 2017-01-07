package link.revie.controllers.update;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Update")
public interface UpdateController {

	@RequestMapping(value = "/Form/{id}", method = RequestMethod.GET)
	void form(@PathVariable Long id);

	@RequestMapping(value = "/Confirm", method = RequestMethod.GET)
	void confirm();

	@RequestMapping(value = "/Execute", method = RequestMethod.GET)
	void execute();

}
