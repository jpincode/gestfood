package com.gestfood.gestfood.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gestfood.gestfood.business.service.DeskService;
import com.gestfood.gestfood.business.service.ProductService;

@Controller
public class HomeController {
    @Autowired
    private DeskService deskService;
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("page_title", "GestFood");
        return mv;
    }

    @GetMapping("/order/create")
    public ModelAndView orderHome() {
        ModelAndView mv = new ModelAndView("createOrder");
        mv.addObject("page_name", "Order Creation");
        mv.addObject("desks", deskService.read());
        mv.addObject("products", productService.read());
        return mv;
    }

    @GetMapping("/admin/employee")
    public ModelAndView employeeHome() {
        ModelAndView mv = new ModelAndView("users/homeEmployee");
        mv.addObject("page_name", "Employee Registration");
        return mv;
    }

    @GetMapping("/admin/desk")
    public ModelAndView deskHome() {
        ModelAndView mv = new ModelAndView("desks/homeDesk");
        mv.addObject("page_name", "Desk Management");
        return mv;
    }

    @GetMapping("/admin/product")
    public ModelAndView productHome() {
        ModelAndView mv = new ModelAndView("products/homeProduct");
        mv.addObject("page_name", "Product Management");
        return mv;
    }

    @GetMapping("/admin/desk/create")
    public ModelAndView deskCreate() {
        ModelAndView mv = new ModelAndView("desks/createDesk");
        mv.addObject("page_name", "Desk Management");
        return mv;
    }

    @GetMapping("/admin/product/create")
    public ModelAndView productCreate() {
        ModelAndView mv = new ModelAndView("products/createProduct");
        mv.addObject("page_name", "Product Management");
        return mv;
    }

    @GetMapping("/admin/employee/create")
    public ModelAndView employeeCreate() {
        ModelAndView mv = new ModelAndView("users/createEmployee");
        mv.addObject("page_name", "Employee Management");
        return mv;
    }

    @GetMapping("/error404")
    public ModelAndView error404() {
        ModelAndView mv = new ModelAndView("error404");
        mv.addObject("page_name", "Error 404");
        return mv;
    }
}
