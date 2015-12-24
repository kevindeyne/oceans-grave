package com.cardprototype.page.boot;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cardprototype.page.AbstractController;
import com.cardprototype.page.battle.BattleController;
import com.cardprototype.page.station.StationController;

/**
 * Initial page a user arrives on
 * Determines whether the user gets to see a splash screen or gets to continue
 * TODO will act as testing hub
 * @author Kevin Deyne
 */
@Controller
public class BootController extends AbstractController {

	@RequestMapping(value={"", "/"})
	public String continueOrSplash(HttpServletRequest request, Model model) {

		model.addAttribute("urlBattlePrototype", BattleController.BATTLE);
		model.addAttribute("urlStationPrototype", StationController.TEST);

		return "boot/test";
	}

}
