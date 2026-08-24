package TienIch;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneSwitch {
	public static void switchScene(javafx.event.ActionEvent event, String fxmlPath,String Title) {
        try {
            // 1. Tải file FXML mới
            Parent root = FXMLLoader.load(SceneSwitch.class.getResource(fxmlPath));
            
            // 3. Khởi tạo Stage Pop-up Modal
	        Stage popupStage = new Stage();
	        popupStage.setTitle(Title);
	        popupStage.setScene(new Scene(root));
	        popupStage.initModality(Modality.APPLICATION_MODAL);
	        popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
	        // 4. Hiển thị Pop-up và DỪNG LẠI CHỜ người dùng đăng nhập xong
	        popupStage.show(); 
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Không thể tải file FXML: " + fxmlPath);
        }
    }
	public static void switchSceneAdmin(javafx.event.ActionEvent event, String fxmlPath,String Title) {
        try {
            // 1. Tải file FXML mới
        	ScreenCacheManager.ScreenUI ui = ScreenCacheManager.getScreen(fxmlPath);
        	if (ui == null) {
              System.err.println("Không tìm thấy hoặc không thể nạp file FXML: " + fxmlPath);
              return;
            }
        	Parent root= ui.getRoot();
        	// 2. Lấy Stage (cửa sổ) hiện tại từ event
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
         // 1. Nếu cửa sổ đã có Scene -> chỉ thay thế Root (tránh tạo Scene mới)
            if (currentStage.getScene() != null) {
            	currentStage.getScene().setRoot(root);
            } else {
                // 2. Nếu là lần đầu chưa có Scene thì mới tạo
            	currentStage.setScene(new Scene(root));
            }
            currentStage.setTitle(Title);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Không thể tải file FXML: " + fxmlPath);
        }
    }
}
