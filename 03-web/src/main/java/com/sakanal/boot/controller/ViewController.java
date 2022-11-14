package com.sakanal.boot.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {
    @RequestMapping("/success")
    public ModelAndView toSuccess(HttpSession session, Model model){
        ModelAndView modelAndView = new ModelAndView("success");
        modelAndView.addObject("info","另一条信息");
//        model.addAttribute("info","另一条信息");
        session.setAttribute("info","一条信息");
        return modelAndView;
    }
}
