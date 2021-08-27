package com.example.easylogin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.easylogin.model.dao.UserRepository;
import java.util.List;

@Controller
public class LoginController {

	@Autowired
	UserRepository userRepos;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	// indexメソッドを追加。 index.htmlへ遷移するメソッド

	@RequestMapping("/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, Model m) {

		String message = "Welcome!";

		List<com.example.easylogin.model.entity.User> users = userRepos.findByUserNameAndPassword(userName, password);
		if (users.size() > 0) {
			com.example.easylogin.model.entity.User user = users.get(0);
			message += user.getFullName();

		} else {
			message += "guest";
		}

		m.addAttribute("message", message);
		return "login";
	}

}

// @ResponseBody

/*
 * lesson7で書き換え public String showUsers() {
 * java.util.List<com.example.easylogin.model.entity.User> users =
 * userRepos.findAll();
 * 
 * com.example.easylogin.model.entity.User user = users.get(0);
 * 
 * String info = user.getUserName() + " " + user.getPassword();
 * 
 * return info; }
 */

//コンパイルエラーが解消されなかったため、赤線から自動的に解消するメニューを選んだ
//ここでの UserはUserクラスのことを指す→ファイルの正式名称に変更されたと推察