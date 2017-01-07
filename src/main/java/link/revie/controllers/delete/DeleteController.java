package link.revie.controllers.delete;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Delete")
public interface DeleteController {

	@RequestMapping(value = "/Confirm/{id}", method = RequestMethod.GET)
	void confirm(@PathVariable Long id);

	@RequestMapping(value = "/Execute/{id}", method = RequestMethod.GET)
	void execute(@PathVariable Long id);

}
