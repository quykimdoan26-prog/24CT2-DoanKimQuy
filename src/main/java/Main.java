

import Controller.QuanLyXeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public void start(Stage primaryStage) {
        try {
            // Load file FXML Đăng nhập từ thư mục resources
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dangnhap/Trangquanlyxechothue.fxml"));
            Parent root = loader.load();
         // 2. Lấy instance của StudentController từ FXMLLoader
            QuanLyXeController controller = loader.getController();
            // 3. Gọi hàm tải dữ liệu thủ công từ Main
            controller.initialize(null, null);
//          Khởi tạo Scene
            Scene scene = new Scene(root);
            
            // Cấu hình Cửa sổ (Stage)
            primaryStage.setTitle("Hệ Thống Quản Lý Cho Thuê Xe");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false); // Cố định kích thước cửa sổ
            primaryStage.centerOnScreen();    // Căn giữa màn hình
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khi tải file FXML Đăng nhập!");
        }
    }

    // Hàm main khởi chạy ứng dụng JavaFX
    public static void main(String[] args) {
        launch(args);
    }
}
