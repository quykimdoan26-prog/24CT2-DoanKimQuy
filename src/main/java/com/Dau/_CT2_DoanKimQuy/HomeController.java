package com.Dau._CT2_DoanKimQuy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String welcome() {
        return "<div style='min-height:100vh; display:flex; justify-content:center; align-items:center; "
             + "margin:0; background:linear-gradient(135deg,#667eea,#764ba2); font-family:Arial,sans-serif;'>"
             + "<div style='width:min(90%,700px); padding:55px 35px; text-align:center; color:white; "
             + "background:rgba(255,255,255,.16); border:1px solid rgba(255,255,255,.35); "
             + "border-radius:24px; box-shadow:0 20px 45px rgba(0,0,0,.25); backdrop-filter:blur(12px);'>"
             + "<div style='font-size:56px; margin-bottom:18px;'>✨</div>"
             + "<h1 style='margin:0 0 18px; font-size:clamp(26px,5vw,44px); line-height:1.25; "
             + "text-shadow:2px 3px 8px rgba(0,0,0,.2);'>Chào bạn khóa 24CT2</h1>"
             + "<p style='margin:0; font-size:clamp(18px,3vw,25px); letter-spacing:.5px;'>"
             + "Môn Học Công Nghệ Phần Mềm</p>"
             + "</div></div>";
    }
}
