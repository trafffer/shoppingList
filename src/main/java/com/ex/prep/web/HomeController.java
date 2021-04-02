package com.ex.prep.web;

import com.ex.prep.model.entity.enums.CategoryNames;
import com.ex.prep.security.CurrentUser;
import com.ex.prep.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    private final CurrentUser user;
    private final ProductService productService;

    public HomeController(CurrentUser user, ProductService productService) {
        this.user = user;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (user.isAnonymous()) {
            return "index";
        } else {
            model.addAttribute("food", productService.findAllByCategory(CategoryNames.FOOD));
            model.addAttribute("drinks", productService.findAllByCategory(CategoryNames.DRINK));
            model.addAttribute("household", productService.findAllByCategory(CategoryNames.HOUSEHOLD));
            model.addAttribute("other", productService.findAllByCategory(CategoryNames.OTHER));
            model.addAttribute("totalSum", productService.productSum());
            return "home";
        }
    }
}
