package com.example.blog.utils;

import com.example.blog.dto.BlogDto;

import java.util.List;
import java.util.Set;

public class Utils {
    public static String generateCategoryString(Set<BlogDto.CategoryDto> categories) {
        // Lấy list name
        List<String> categoryNames = categories.stream()
                .map(BlogDto.CategoryDto::getName).toList();

        // Convert list => array
        String[] arrNames = categoryNames.toArray(new String[0]);

        // Convert array => String
        return String.join(", ", arrNames);
    }
}
