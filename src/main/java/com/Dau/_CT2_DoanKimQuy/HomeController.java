package com.Dau._CT2_DoanKimQuy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String welcome() {
        return "<div style='text-align: center; margin-top: 50px; font-family: Arial, sans-serif;'>"
             + "<h1 style='color: #004085;'>Chào bạn khóa 24CT đến với học phần CNPM-DAU</h1>"
             + "</div>";
    }
}
