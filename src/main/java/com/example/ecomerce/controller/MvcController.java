package com.example.ecomerce.controller;

import com.example.ecomerce.dto.LoginDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
public class MvcController {

    @GetMapping("/")
    public String greeting(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        return "gretting.html";
    }

    @GetMapping("/authentication")
    public String returnAuthenticationPage(){
        return "authentication.html";
    }

    @PostMapping("/manual/login")
    public void manualLogin(@RequestBody LoginDTO loginDTO, HttpServletResponse response) throws IOException {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        response.sendRedirect("/");
    }
}
