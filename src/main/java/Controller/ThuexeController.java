package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import DAO.KhachHangDAO;
import DAO.ThueXeDAO;
import DAO.XeDAO;
import Entity.KhachHang;
import Entity.ThueXe;
import Entity.Xe;
import TienIch.SceneSwitch;
import TienIch.ScreenCacheManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ThuexeController {
	//table view
		@FXML private TableView<ThueXe> tblview;
		@FXML private TableColumn<ThueXe, String> cltenkh;
	    @FXML private TableColumn<ThueXe, String> cltenxe;
	    @FXML private TableColumn<ThueXe, String> clngaynhan;
	    @FXML private TableColumn<ThueXe, String> clngaytra;
	    @FXML private TableColumn<ThueXe, String> clgiathue;
	    @FXML private TableColumn<ThueXe, String> cltrangthai;
	    ThueXeDAO tt=new ThueXeDAO();
	    XeDAO ttx=new XeDAO();
	    KhachHangDAO ttkh=new KhachHangDAO();
	    @FXML private TextField txtmakh;
		@FXML private TextField txtmaxe;
		@FXML private TextField txtngaynhan;
		@FXML private TextField txtngaytra;
		@FXML private TextField txtgiathue;
		@FXML private TextField txttrangthai;
		@FXML private TextField txtgiaxe;
		@FXML private TextField txttenxe;
	    public void initialize(URL location, ResourceBundle resources) {
	        // 1. Ánh xạ các cột thông thường (Sửa lại clnsx trỏ đúng namSanXuat)
	        clngaynhan.setCellValueFactory(new PropertyValueFactory<>("ngayNhanXe"));
	        clngaytra.setCellValueFactory(new PropertyValueFactory<>("ngayTraXe"));
	        clgiathue.setCellValueFactory(new PropertyValueFactory<>("giaThue")); // ✅ Đã sửa
	        cltrangthai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
	        cltenkh.setCellValueFactory(cellData -> {
        		//cell data dữ liệu của một bộ trong bảng
            ThueXe hs = cellData.getValue();
            if (hs != null && hs.getKhachHang() != null) {
                return new SimpleStringProperty(hs.getKhachHang().getTenKhachHang());
            }
            return new SimpleStringProperty("");
	        });
	        cltenxe.setCellValueFactory(cellData -> {
        		//cell data dữ liệu của một bộ trong bảng
            ThueXe hs = cellData.getValue();
            if (hs != null && hs.getKhachHang() != null) {
                return new SimpleStringProperty(hs.getXe().getTenXe());
            }
            return new SimpleStringProperty("");
	        });
	        // 3. Gọi hàm tải dữ liệu lên bảng
	        loadDataToTable();
	    }
	    public void loadDataToTable() {
	        try {
				List<ThueXe> list=(List<ThueXe>) tt.findAll();
	    			ObservableList<ThueXe> ThueXelist = FXCollections.observableArrayList(list);
	    			// Gán danh sách vào TableView
	    			tblview.setItems(ThueXelist);
			} catch (Exception e) {
				System.out.println("có lỗi:"+e);
			}
	    }
	    public void taidulieuthuexe() {
	    		initialize(null, null);
	    }
	    public void chuyenhuong(ActionEvent event){
	    		SceneSwitch.switchScene(event, "/Dangnhap/Trangquanlyxechothue.fxml","Trang Chủ");
	    }
	    @FXML
	    public void themhosothuexe(ActionEvent event){
	    		try {
					if(txtmaxe.getText().isEmpty() ||txtmakh.getText().isEmpty()) {
						System.out.println("không được để trống xe và khách hàng");
						return;
					}
					int maxe=Integer.parseInt(txtmaxe.getText());
					int makh=Integer.parseInt(txtmakh.getText());
					LocalDate ngaynhan = LocalDate.parse(txtngaynhan.getText().trim());
					LocalDate ngaytra = LocalDate.parse(txtngaytra.getText().trim());
					LocalDate homNay = LocalDate.now();
					if(ngaytra.isBefore(ngaynhan)){
						showErrorAlert("Lỗi Ngày Đăng Ký Xe","Ngày Trả Xe Phải Sau Ngày Nhận Xe Bạn Ơi bớt nguuuu");
						return;
					}else if(ngaynhan.isBefore(homNay)) {
						showErrorAlert("Lỗi Ngày Đăng Ký Xe","Ngày Nhận không thể là quá khứ nhé bạn Yêu");
						return;
					}
					String trangthai=txttrangthai.getText();
					double giathue=Double.parseDouble(txtgiathue.getText());
					Xe xe=ttx.findById(maxe);
					KhachHang kh=ttkh.findById(makh);
					ThueXe dt=new ThueXe();
					dt.setXe(xe);
					dt.setKhachHang(kh);
					dt.setNgayNhanXe(ngaynhan);
					dt.setNgayTraXe(ngaytra);
					dt.setGiaThue(giathue);
					dt.setTrangThai(trangthai);
					
					tt.save(dt);
					System.out.println("thêm thành công");
					dt.getXe().setTrangThai("Đang Được Thuê");
					ttx.update(xe);
					Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	                stage.close();
	                showErrorAlert("Trạng thái thuê xe","chúc mừng bạn đã thuê xe thành công");
				} catch (Exception e) {
					System.out.println("có lỗi:"+e);
				}
	    }
	    public void SetThue(Xe xe,int idkh){
	    	// Tự động gán dữ liệu lên các TextField trên Form Thuê Xe
	        if (xe != null) {
	            txtmaxe.setText(String.valueOf(xe.getMaXe()));
	            // Khóa không cho sửa Mã xe (chỉ đọc)
	            txttenxe.setText(xe.getTenXe());
	            txtgiaxe.setText(String.valueOf(xe.getGiaThue()));
	            txttenxe.setEditable(false);
	            txtgiaxe.setEditable(false);
	            txtmaxe.setEditable(false);
	            txtgiathue.setText(String.valueOf(xe.getGiaThue()));
	            txttrangthai.setText(xe.getTrangThai());
	            txtgiathue.setEditable(false);
	            txttrangthai.setEditable(false);
	        }
	        		txtmakh.setText(String.valueOf(idkh));
	        		txtmakh.setEditable(false);
	    }
	    public static void thueXeAction(javafx.scene.Node node, Xe xe,int id) {
		    try {
		        ScreenCacheManager.ScreenUI ui=ScreenCacheManager.getScreen("/Dangnhap/ThueXe.fxml");
		        // 2. Lấy controller instance mới
		        if (ui == null) {
		            System.err.println("Không tìm thấy hoặc không thể nạp file FXML: " + "/Dangnhap/ThueXe.fxml");
		            return;
		        }
		        ThuexeController controller = (ThuexeController) ui.getController();
		        Parent root=ui.getRoot();
		        // 3. Gọi hàm set dữ liệu
		        if (controller != null) {
		            controller.SetThue(xe,id);
		        }
		     // Nếu root đang bị gắn ở Scene nào đó thì gỡ nó ra trước
		        if (root.getScene() != null) {
		            root.getScene().setRoot(new javafx.scene.Group());
		        }
		     // 3. Khởi tạo Stage Pop-up Modal
		        Stage popupStage = new Stage();
		        popupStage.setTitle("Đăng nhập");
		        popupStage.setScene(new Scene(root));
		        popupStage.initModality(Modality.APPLICATION_MODAL);
		        // (Tùy chọn) Gán cửa sổ cha để pop-up luôn nằm trên màn hình chính
		        if (node != null && node.getScene() != null) {
		            popupStage.initOwner(node.getScene().getWindow());
		        }
		        // 5. Hiển thị cửa sổ dạng pop-up
		        popupStage.showAndWait();
		        
		    } catch (Exception e) {
		        System.out.println("Lỗi khi chuyển sang Form Thuê Xe: " + e.getMessage());
		        e.printStackTrace();
		    		}
	    }
	    public void loadDataLSKHToTable(int idkh){
	    	// 1. Ánh xạ các cột thông thường (Sửa lại clnsx trỏ đúng namSanXuat)
	        clngaynhan.setCellValueFactory(new PropertyValueFactory<>("ngayNhanXe"));
	        clngaytra.setCellValueFactory(new PropertyValueFactory<>("ngayTraXe"));
	        clgiathue.setCellValueFactory(new PropertyValueFactory<>("giaThue")); 
	        cltrangthai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
	        cltenkh.setCellValueFactory(cellData -> {
        		//cell data dữ liệu của một bộ trong bảng
            ThueXe hs = cellData.getValue();
            if (hs != null && hs.getKhachHang() != null) {
                return new SimpleStringProperty(hs.getKhachHang().getTenKhachHang());
            }
            return new SimpleStringProperty("");
	        });
	        cltenxe.setCellValueFactory(cellData -> {
        		//cell data dữ liệu của một bộ trong bảng
            ThueXe hs = cellData.getValue();
            if (hs != null && hs.getKhachHang() != null) {
                return new SimpleStringProperty(hs.getXe().getTenXe());
            }
            return new SimpleStringProperty("");
	        });
	        try {
				List<ThueXe> list=(List<ThueXe>) tt.findByCustomerIdList(idkh);
	    			ObservableList<ThueXe> ThueXelist = FXCollections.observableArrayList(list);
	    			// Gán danh sách vào TableView
	    			tblview.setItems(ThueXelist);
			} catch (Exception e) {
				System.out.println("có lỗi khi lấy lịch sử thuê xe:"+e);
			}
	    }
	 // Hàm bổ trợ hiển thị thông báo lỗi lên giao diện
		private void showErrorAlert(String title, String content) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle(title);
			alert.setHeaderText(null);
			alert.setContentText(content);
			alert.showAndWait();
		}
}
