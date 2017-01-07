package link.revie.controllers.category;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Category")
public interface CategoryController {

	@RequestMapping(value = "/{category}", method = RequestMethod.GET)
	void get(@PathVariable String category);

}
