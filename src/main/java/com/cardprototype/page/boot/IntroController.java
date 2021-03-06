package com.cardprototype.page.boot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cardprototype.page.AbstractController;


@Controller
public class IntroController extends AbstractController {

	@RequestMapping(value={"", "/"})
	public String intro(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "boot/intro";
	}

}