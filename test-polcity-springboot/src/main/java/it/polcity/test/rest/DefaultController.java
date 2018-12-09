package it.polcity.test.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author marco.marini
 *	Controller di default 
 */
@RestController
public class DefaultController {

	@RequestMapping("/")
	public String index() {
		return "Test Polcity - Index page";
	}
}