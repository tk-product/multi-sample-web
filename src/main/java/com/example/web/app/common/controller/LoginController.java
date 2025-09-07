package com.example.web.app.common.controller;

import com.example.web.app.common.session.UserSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class LoginController {

    @GetMapping("/local-login")
    public String loginForm() {
        return "login"; // Thymeleaf テンプレート login.html
    }

    @PostMapping("/local-login")
    public String doLogin(@RequestParam String userId,
                          @RequestParam String password,
                          @RequestParam(required = false) String redirect,
                          HttpServletRequest request) {
        // 簡易チェック (実際はDB認証など)
        if ("user".equals(userId) && "pass".equals(password)) {
            HttpSession session = request.getSession(true);
            session.setAttribute("USER_SESSION",
                    new UserSession(userId, "テストユーザー", Set.of("ADMIN", "VIEWER")));
            return "redirect:" + (redirect != null ? redirect : "/");
        }
        return "login"; // 失敗時は戻す
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // セッション破棄
        return "redirect:/local-login"; // ログイン画面に戻す
    }
}
