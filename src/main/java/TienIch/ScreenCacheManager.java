package TienIch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ScreenCacheManager {

    // Class nội bộ để bọc cả View và Controller
    public static class ScreenUI {
        private final Parent root;
        private final Object controller;

        public ScreenUI(Parent root, Object controller) {
            this.root = root;
            this.controller = controller;
        }

        public Parent getRoot() { return root; }
        public Object getController() { return controller; }
    }

    // Map dùng làm bộ nhớ đệm lưu trữ các màn hình đã nạp
    private static final Map<String, ScreenUI> cache = new HashMap<>();

    /**
     * Lấy màn hình theo đường dẫn FXML.
     * Nếu chưa có trong cache -> Tải mới và lưu lại.
     * Nếu đã có -> Trả về đối tượng có sẵn trong RAM.
     */
    public static ScreenUI getScreen(String fxmlPath) {
        if (!cache.containsKey(fxmlPath)) {
            try {
                FXMLLoader loader = new FXMLLoader(ScreenCacheManager.class.getResource(fxmlPath));
                Parent root = loader.load();
                Object controller = loader.getController();

                // Lưu vào bộ nhớ đệm
                ScreenUI screenUI = new ScreenUI(root, controller);
                cache.put(fxmlPath, screenUI);
                System.out.println("--> Đọc từ đĩa/FXML: " + fxmlPath);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            System.out.println("--> Lấy từ Cache RAM: " + fxmlPath);
        }

        return cache.get(fxmlPath);
    }

    /**
     * Xóa cache của 1 màn hình cụ thể khi cần load lại hoàn toàn mới
     */
    public static void clearCache(String fxmlPath) {
        cache.remove(fxmlPath);
    }
}