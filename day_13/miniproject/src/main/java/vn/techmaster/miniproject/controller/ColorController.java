package vn.techmaster.miniproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.miniproject.service.ColorService;

@RestController
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping("/random-color")
    public String randomColor(@RequestParam int type) {
        return colorService.randomColor(type);
    }
}
