package Controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import DAO.KhachHangDAO;
import DAO.TaiKhoanDAO;
import Entity.KhachHang;
import Entity.TaiKhoan;
import TienIch.SceneSwitch;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DangNhapController {
	// Biến lưu trữ người dùng đăng nhập thành công
    private KhachHang loggedInUser = null;

    public KhachHang getLoggedInUser() {
        return loggedInUser;
    }
	 @FXML private TextField txtten;
	 @FXML private TextField txtmk;
	 @FXML private TextField txtgplx;
	 @FXML private Button btndangnhap;
	TaiKhoanDAO tt= new TaiKhoanDAO();
	KhachHangDAO ttkh=new KhachHangDAO();
	@FXML
    private void handleDangNhap(ActionEvent event) {
		try {
			String ten=txtten.getText();
			String pw=txtmk.getText();
			KhachHang kh=tt.checkLogin(ten,pw);
			if(kh != null) {
	                this.loggedInUser = kh; 
	                // 2. Tự đóng cửa sổ Pop-up Đăng nhập lại
	                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	                stage.close();
			}else System.out.println("đăng nhập thất bại");
		} catch (Exception e) {
			System.out.println("có lỗi đăng nhập: "+e);
			showErrorAlert("có lỗi từ hệ thống","lỗi rồi");
		}
        
    }
	
    // Sự kiện khi bấm nút hoặc link "Đăng ký"
    @FXML
    private void handleChuyenSangDangKy(ActionEvent event) {
        // Chuyển sang giao diện đăng ký
        SceneSwitch.switchScene(event, "/Dangnhap/Dangky.fxml","Trang Đăng Ký"); 
    }
    @FXML
    private void handleChuyenSangDangNhap(ActionEvent event) {
        // Chuyển sang giao diện đăng ký
        SceneSwitch.switchScene(event, "/Dangnhap/Dangnhap.fxml","Trang Đăng Nhập"); 
    }
    @FXML
    public void dangky(ActionEvent event) {
        try {
            // 1. Lấy dữ liệu từ giao diện
            String ten = txtten.getText().trim();
            String pw = txtmk.getText().trim();
            String gplx = txtgplx.getText().trim();
            if (ten.isEmpty() || pw.isEmpty() ||gplx.isEmpty()) {
                System.out.println("Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            // 2. Tạo và lưu TaiKhoan (Không set ID thủ công)
            TaiKhoan tk = new TaiKhoan();
            tk.setTenTaiKhoan(ten);
            tk.setVaiTro("khách Hàng");
            tt.save(tk); // Hibernate sẽ tự sinh ID cho 'tk'

            // 3. Tạo và lưu KhachHang gắn liền với TaiKhoan vừa tạo
            KhachHang kh = new KhachHang();
            kh.setTenKhachHang(ten);
            kh.setMatKhau(pw);
            kh.setSoGiaiPhepLaixe(gplx);
            kh.setTaiKhoan(tk); // Gán trực tiếp đối tượng TaiKhoan vừa save
            
            ttkh.save(kh);
	        // Lấy Stage của chính cửa sổ pop-up và đóng lại
	    	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    	    stage.close();
            showErrorAlert("Đăng Ký Thành Công","Chúc Mừng Bạn Đã Đăng Ký Thành Công");
        } catch (Exception e) {
            System.out.println("Có lỗi khi đăng ký: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
	public void dongFormAction(ActionEvent event) {
	    // Lấy Stage của chính cửa sổ pop-up và đóng lại
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.close();
	}
	// Hàm bổ trợ hiển thị thông báo lỗi lên giao diện
	private void showErrorAlert(String title, String content) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
