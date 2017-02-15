package link.revie.controllers.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/Login")
public interface LoginController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    String login();
}
