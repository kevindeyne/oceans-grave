package com.cardprototype.page.battle;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cardprototype.core.domain.EventQueue;
import com.cardprototype.core.domain.Player;
import com.cardprototype.core.repository.EventQueueRepository;
import com.cardprototype.core.repository.PlayerRepository;
import com.cardprototype.page.AbstractController;

/**
 * Initial battle page setup
 * @author Kevin Deyne
 */
@Controller
public class BattleController extends AbstractController {

	public static final String BATTLE = "/battle";

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private EventQueueRepository eventQueueRepository;

	@RequestMapping(value={BATTLE})
	public String initPage(HttpServletRequest request, Model model) {

		Player player = this.playerRepository.findOne(Player.EXAMPLE_ID);
		//new battle? -> clear all prev events and keep the database clean
		clearAllEvents();

		model.addAttribute("abilities", player.getAbilities());

		model.addAttribute("urlSkillAction", AbilityController.SKILL_ACTION);
		model.addAttribute("urlEnemyAction", AbilityController.ENEMY_ACTION);

		return "battle/index";
	}

	/**
	 * Be sure to clean up previous battles and clean the event queue
	 */
	private void clearAllEvents() {
		List<EventQueue> eventQueues = this.eventQueueRepository.findByPlayerId(Player.EXAMPLE_ID);
		this.eventQueueRepository.delete(eventQueues);
	}

}
