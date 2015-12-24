package com.cardprototype.page.station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cardprototype.core.domain.conversation.Answer;
import com.cardprototype.core.domain.conversation.Line;
import com.cardprototype.core.repository.AnswerRepository;
import com.cardprototype.core.repository.LineRepository;
import com.cardprototype.page.AbstractController;

/**
 * Initial station page setup
 * @author Kevin Deyne
 */
@Controller
public class ConversationController extends AbstractController {

	public static final String CONVERSATION_CHAR = "/conversation/char/{id}";
	public static final String CONVERSATION_NEWLINE = "/conversation/newline/{answerid}";

	@Autowired
	private LineRepository lineRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@RequestMapping(value={CONVERSATION_CHAR})
	public String conversationCharPage(HttpServletRequest request, Model model, @PathVariable String id) {

		Line line = this.lineRepository.findByCharacterIdAndConversationStartTrue(id);

		model.addAttribute("line", line.getText());
		model.addAttribute("answers", line.getAnswers());

		return "station/conversation";
	}

	@RequestMapping(value=CONVERSATION_NEWLINE)
	public @ResponseBody Map<String, List<? extends Object>> getNewLine(@PathVariable String answerid){
		Map<String, List<? extends Object>> result = new HashMap<String, List<? extends Object>>();

		Answer answer = this.answerRepository.findOne(answerid);
		Line line = answer.getLine();

		List<String> lines = new ArrayList<String>();
		lines.add(line.getText());

		result.put("line", lines);
		result.put("answers", line.getAnswers());

		return result;
	}
}
