package com.ex.prep.web;

import com.ex.prep.model.binding.ProductAddBindingModel;
import com.ex.prep.model.constant.Constant;
import com.ex.prep.model.entity.enums.CategoryNames;
import com.ex.prep.security.CurrentUser;
import com.ex.prep.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CurrentUser user;

    public ProductController(ProductService productService, CurrentUser user) {
        this.productService = productService;
        this.user = user;
    }

    @ModelAttribute("productModel")
    public ProductAddBindingModel productModel() {
        return new ProductAddBindingModel();
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (user.isAnonymous()){
            return "redirect:/login";
        }
        model.addAttribute("categories", CategoryNames.values());
        if (!model.containsAttribute("productModel")) {
            model.addAttribute("productModel", new ProductAddBindingModel());
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute ProductAddBindingModel productModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productModel", productModel);
            redirectAttributes.addFlashAttribute(Constant.BINDING + "productModel", bindingResult);
            return "redirect:add";
        }
        if (user.isAnonymous()) {
            return "redirect:/login";
        } else {
            productService.save(productModel);
            return "redirect:/";
        }
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable String id) {
        productService.buyId(id);

        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAll(){
        productService.buyAll();
        return "redirect:/";
    }
}
