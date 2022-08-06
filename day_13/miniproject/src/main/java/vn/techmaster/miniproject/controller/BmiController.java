package vn.techmaster.miniproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.miniproject.request.BmiRequest;
import vn.techmaster.miniproject.service.BmiService;

@RestController
public class BmiController {

    @Autowired
    private BmiService bmiService;

    @GetMapping("/bmi-get")
    public double calculateBmiGet(@RequestParam double height, @RequestParam double weight) {
        return bmiService.calculateBmi(height, weight);
    }

    @PostMapping("/bmi-post")
    public double calculateBmiPost(@RequestBody BmiRequest request) {
        return bmiService.calculateBmi(request.getHeight(), request.getWeight());
    }
}
