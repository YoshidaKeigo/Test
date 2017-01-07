package link.revie.controllers.main.impl;

import org.springframework.stereotype.Component;

import link.revie.controllers.main.MainController;

@Component
public class MainControllerImpl implements MainController {

	@Override
	public String main() {
		return "main";
	}

}
