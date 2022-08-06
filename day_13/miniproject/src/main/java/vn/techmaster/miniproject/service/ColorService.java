package vn.techmaster.miniproject.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import vn.techmaster.miniproject.exception.BadRequestException;

@Service
public class ColorService {

    public String randomColor(int type) {
        return switch (type) {
            case 1 -> randomColorName();
            case 2 -> randomHexColor();
            case 3 -> randomRgbColor();
            default -> throw new BadRequestException("Type không hợp lệ");
        };
    }

    public String randomColorName() {
        // Định nghĩa 1 mảng -> lấy random 1 giá trị trong mảng
        String[] colors = { "red", "green", "blue", "black", "yellow" };

        Random rd = new Random();
        int rdIndex = rd.nextInt(colors.length);
        return colors[rdIndex];
    }

    public String randomHexColor() {
        // [0-9] [a->f]
        return "#16282C";
    }

    public String randomRgbColor() {
        // 0 -> 255
        return "rgb(123,73, 26)";
    }
}
