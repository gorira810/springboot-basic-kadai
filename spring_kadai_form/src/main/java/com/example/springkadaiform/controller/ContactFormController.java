package com.example.springkadaiform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {

    @GetMapping("/form")
    public String form(Model model) {

        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }

        return "contactFormView";
    }

    @PostMapping("/confirm")
    public String confirm(
            @Validated @ModelAttribute("contactForm") ContactForm contactForm,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        // バリデーションNGの場合
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.contactForm",
                    bindingResult);

            redirectAttributes.addFlashAttribute(
                    "contactForm",
                    contactForm);

            return "redirect:/form";
        }

        // バリデーションOKの場合
        return "confirmView";
    }
}