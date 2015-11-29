package com.cardprototype.page.station;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cardprototype.page.AbstractController;

/**
 * Initial station page setup
 * @author Kevin Deyne
 */
@Controller
public class StationController extends AbstractController {


	@RequestMapping(value={"/station}"})
	public String initPage(HttpServletRequest request, Model model) {

		//load up station where player is current located
		//characters in station

		return "station/index";
	}
}
