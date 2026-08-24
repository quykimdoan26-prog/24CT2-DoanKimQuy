package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import DAO.HangXeDAO;
import DAO.XeDAO;
import Entity.HangXe;
import Entity.KhachHang;
import Entity.Xe;
import TienIch.SceneSwitch;
import TienIch.ScreenCacheManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class QuanLyXeController {
	// table view
	@FXML
	private TableView<Xe> tblview;
	@FXML
	private TableColumn<Xe, Integer> clmaxe;
	@FXML
	private TableColumn<Xe, String> cltenxe;
	@FXML
	private TableColumn<Xe, String> clnsx;
	@FXML
	private TableColumn<Xe, Integer> clsochongoi;
	@FXML
	private TableColumn<Xe, String> clmota;
	@FXML
	private TableColumn<Xe, String> clngaynhap;
	@FXML
	private TableColumn<Xe, String> clgiathue;
	@FXML
	private TableColumn<Xe, String> clmausac;
	@FXML
	private TableColumn<Xe, String> cltrangthai;
	@FXML
	private TableColumn<Xe, String> clhangxe;
	@FXML
	private TableColumn<Xe, Void> clthaotac; // Khai báo cột thao tác
	//
	XeDAO tt = new XeDAO();

	public void initialize(URL location, ResourceBundle resources) {
	    // 1. Ánh xạ các cột thông thường
	    clmaxe.setCellValueFactory(new PropertyValueFactory<>("maXe"));
	    cltenxe.setCellValueFactory(new PropertyValueFactory<>("tenXe"));
	    clnsx.setCellValueFactory(new PropertyValueFactory<>("namSanXuat"));
	    clsochongoi.setCellValueFactory(new PropertyValueFactory<>("soChoNgoi"));
	    clmota.setCellValueFactory(new PropertyValueFactory<>("moTa"));
	    clngaynhap.setCellValueFactory(new PropertyValueFactory<>("ngayNhap"));
	    clgiathue.setCellValueFactory(new PropertyValueFactory<>("giaThue"));
	    clmausac.setCellValueFactory(new PropertyValueFactory<>("mauSac"));
	    cltrangthai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));

	    // 2. Ánh xạ cột Khóa Ngoại (Tên Hãng Xe)
	    clhangxe.setCellValueFactory(cellData -> {
	        Xe xe = cellData.getValue();
	        if (xe != null && xe.getHangXe() != null) {
	            return new SimpleStringProperty(xe.getHangXe().getTenHangXe());
	        }
	        return new SimpleStringProperty("");
	    });

	 // 3. Cấu hình Cột Thao Tác
	    clthaotac.setCellFactory(param -> new TableCell<Xe, Void>() {
	        @Override
		        protected void updateItem(Void item, boolean empty) {
		            super.updateItem(item, empty);
	
		            if (empty) {
		                setGraphic(null);
		            } else {
		                // Chỉ định rõ package JavaFX ngay tại đây
		                javafx.scene.control.Button btnThue = new javafx.scene.control.Button("Thuê");
		                btnThue.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-cursor: hand;");
	
		                btnThue.setOnAction(event -> {
		                		if (idkh.getText().isEmpty()) {
		                			showErrorAlert("Lỗi chưa đăng nhập", "Bạn hãy đang nhập để thuê xe");
		                			return;
		                		}
		                		Xe xeDuocChon = getTableView().getItems().get(getIndex());
		                		int Idkh=Integer.parseInt(idkh.getText());
		                    
		                    if (xeDuocChon != null && !"Đang Được Thuê".equals(xeDuocChon.getTrangThai())){
		                        ThuexeController.thueXeAction(btnThue, xeDuocChon,Idkh);
		                    }else {
		                    		showErrorAlert("Lỗi trạng thái xe", "Hiện tại xe đang được thuê");
		                    }
		                });
	
		                setGraphic(btnThue);
		            }
		        }
	    });

	    // 4. Nạp dữ liệu lên TableView
	    loadDataToTable();
	}

	void loadDataToTable() {
		try {
			// Lấy danh sách xe từ CSDL thông qua DAO (Giả sử DAO có hàm getAll)
			List<Xe> list = (List<Xe>) tt.findAll();
			System.out.println("=== KÍCH THƯỚC DANH SÁCH LẤY TỪ DB: " + list.size() + " ===");
			// Chuyển List thành ObservableList để JavaFX quản lý hiển thị
			// khởi tạo vùng nhớ list lắng nghe (theo dõi dữ liệu khi thêm,xóa ,sữa từ hàm
			// có sẵn)
			// chứa địa chỉ list xe thuần ở trong và table có thể lắng nghe từ đây.
			ObservableList<Xe> XeList = FXCollections.observableArrayList(list);
			// Gán danh sách vào TableView
			tblview.setItems(XeList);

		} catch (Exception e) {
			System.out.println("Lỗi khi tải dữ liệu lên TableView: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@FXML
	private TextField txtmaxe;//
	@FXML
	private TextField txttenxe;
	@FXML
	private TextField txtnsx;//
	@FXML
	private TextField txtmausac;
	@FXML
	private TextField txtchongoi;//
	@FXML
	private TextField txthangxe;
	@FXML
	private TextField txtmota;
	@FXML
	private TextField txtngaynhap;//
	@FXML
	private TextField txtgiathue;//
	@FXML
	private TextField txttrangthai;
	@FXML
	private TextField idkh;
	HangXeDAO tthx = new HangXeDAO();
	
	public void themxe(ActionEvent event) {
		try {
			// 1. Kiểm tra không để trống các trường bắt buộc
			if (txtnsx.getText().isEmpty() || txtchongoi.getText().isEmpty() || txthangxe.getText().isEmpty()
					|| txtgiathue.getText().isEmpty() || txtngaynhap.getText().isEmpty()) {

				showErrorAlert("Lỗi nhập liệu", "Vui lòng điền đầy đủ các thông tin bắt buộc!");
				return;
			}

			// 2. Chuyển đổi dữ liệu an toàn bên trong try-catch
			int namsx = Integer.parseInt(txtnsx.getText().trim());
			int socn = Integer.parseInt(txtchongoi.getText().trim());
			int idhang = Integer.parseInt(txthangxe.getText().trim());

			String giaThueClean = txtgiathue.getText().trim().replace(".", "").replace(",", ".");
			double giaThue = Double.parseDouble(giaThueClean);

			// Giả định định dạng nhập ngày là YYYY-MM-DD
			LocalDate ngaynhap = LocalDate.parse(txtngaynhap.getText().trim());

			// 3. Lấy đối tượng HangXe và kiểm tra tồn tại
			HangXe hx = tthx.findById(idhang);
			if (hx == null) {
				showErrorAlert("Lỗi dữ liệu", "Không tìm thấy Hãng xe với Mã ID: " + idhang);
				return;
			}

			// 4. Khởi tạo và lưu Entity
			Xe xe = new Xe();
			xe.setNamSanXuat(namsx);
			xe.setSoChoNgoi(socn);
			xe.setGiaThue(giaThue);
			xe.setNgayNhap(ngaynhap);
			xe.setTenXe(txttenxe.getText().trim());
			xe.setMauSac(txtmausac.getText().trim());
			xe.setHangXe(hx);
			xe.setMoTa(txtmota.getText().trim());
			xe.setTrangThai(txttrangthai.getText().trim());

			tt.save(xe);
			System.out.println("Lưu thành công xe mới ID: " + xe.getMaXe());
			SceneSwitch.switchScene(event, "/Dangnhap/Trangquanlyxechothue.fxml","Trang Chủ");
		} catch (NumberFormatException e) {
			showErrorAlert("Lỗi định dạng", "Năm sản xuất, số chỗ ngồi, giá thuê và ID hãng xe phải là số!");
		} catch (java.time.format.DateTimeParseException e) {
			showErrorAlert("Lỗi định dạng ngày", "Ngày nhập không đúng định dạng (YYYY-MM-DD)!");
		} catch (Exception e) {
			System.out.println("Có lỗi phát sinh: " + e.getMessage());
			showErrorAlert("Lỗi hệ thống", "Không thể lưu thông tin xe: " + e.getMessage());
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

	// chuyen huong
	@FXML
	public void chuyentrangthemxe(ActionEvent event) {
		SceneSwitch.switchSceneAdmin(event, "/Dangnhap/ThemXe.fxml","Trang Quản Lý xe");
	}
	@FXML
	public void chuyentrangthuexe(ActionEvent event) {
		SceneSwitch.switchScene(event,"/Dangnhap/Danhsachthuexe.fxml","Danh Sách Đơn thuê xe");
	}
	public void chuyentrangdonthuexe(ActionEvent event) {
		SceneSwitch.switchScene(event, "/Dangnhap/Thuexe.fxml","Form Thuê Xe");
	}
	@FXML
	public static void chuyentrangadmin(ActionEvent event) {
		SceneSwitch.switchSceneAdmin(event, "/Dangnhap/TrangchuAdmin.fxml","Trang Chủ Quản Lý");
		ScreenCacheManager.ScreenUI ui=ScreenCacheManager.getScreen("/Dangnhap/TrangchuAdmin.fxml");
		// 3. Phải ép kiểu (Cast) về QuanLyXeController
	    if (ui != null) {
	        QuanLyXeController ctl = (QuanLyXeController) ui.getController();
	        ctl.initializeAdmin(null, null);
	    }
	}
	@FXML
	public void Gobackadmin(ActionEvent event) {
		SceneSwitch.switchSceneAdmin(event, "/Dangnhap/TrangchuAdmin.fxml","Trang Chủ Quản Lý");
	}
	@FXML
	public void LichSuThuexeAction(ActionEvent event){
	 	ScreenCacheManager.ScreenUI ui = ScreenCacheManager.getScreen("/Dangnhap/LichSuThueXeKH.fxml");
	 	if(ui==null) {
	 		System.out.println("không thể load file Lịch Sử Thuê Xe");
	 	}
	 	Parent root=ui.getRoot();
	 	ThuexeController ctrl=(ThuexeController) ui.getController();
	 	int maKh = Integer.parseInt(idkh.getText().trim());
	 	ctrl.loadDataLSKHToTable(maKh);
	 // 3. Khởi tạo Stage Pop-up Modal
	 	// Nếu root đang bị gắn ở Scene nào đó thì gỡ nó ra trước
	    if (root.getScene() != null) {
	        root.getScene().setRoot(new javafx.scene.Group());
	    }
        Stage popupStage = new Stage();
        popupStage.setTitle("Lịch Sử Thuê Xe");
        popupStage.setScene(new Scene(root));
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        // 4. Hiển thị Pop-up và DỪNG LẠI CHỜ người dùng đăng nhập xong
        popupStage.show(); 
	}
	
	@FXML private Button btndangnhap;
	public void setkhachhang(KhachHang kh) {
		btndangnhap.setText("Xin chào:"+kh.getTenKhachHang()); // Set chữ cho nút
		btndangnhap.setDisable(true);
		idkh.setText(String.valueOf(kh.getMaKhachHang()));
		idkh.setEditable(false);
	}
	public static void TrangchuAction(javafx.scene.Node node,KhachHang kh) {
	    try {
	        // 1. Dùng Class.getResource thay vì getClass()
	        FXMLLoader loader = new FXMLLoader(ThuexeController.class.getResource("/Dangnhap/Trangquanlyxechothue.fxml"));
	        Parent root = loader.load();
	
	        // 2. Lấy controller instance mới
	       QuanLyXeController controller = loader.getController();
	
	        // 3. Gọi hàm set dữ liệu
	        if (controller != null) {
	            controller.setkhachhang(kh);
	            controller.initialize(null, null);
	        }
	        // 4. Lấy Stage từ Node truyền vào thay vì tblview
	        Stage stage = (Stage) node.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
	    } catch (Exception e) {
	        System.out.println("Lỗi khi chuyển sang trang chủ: " + e.getMessage());
	        e.printStackTrace();
	    		}
    }
	@FXML
	public void updateXe(ActionEvent event) {
		try {
			int maxe=Integer.parseInt(txtmaxe.getText());
			Xe xe=tt.findById(maxe);
			if(!txttenxe.getText().isEmpty()){
				xe.setTenXe(txttenxe.getText());
			}
			if(!txtnsx.getText().isEmpty()){
				int nsx=Integer.parseInt(txtnsx.getText());
				xe.setNamSanXuat(nsx);
			}
			if(!txtmausac.getText().isEmpty()){
				xe.setMauSac(txtmausac.getText());
			}
			if(!txtchongoi.getText().isEmpty()) {
				int scn=Integer.parseInt(txtchongoi.getText());
				xe.setSoChoNgoi(scn);
			}
			if(!txtmota.getText().isEmpty()) {
				xe.setMoTa(txtmota.getText());
			}
			if(!txtngaynhap.getText().isEmpty()) {
				LocalDate ngaynhap=LocalDate.parse(txtngaynhap.getText());
				xe.setNgayNhap(ngaynhap);
			}
			if(!txtgiathue.getText().isEmpty()) {
				double giathue=Double.parseDouble(txtgiathue.getText());
				xe.setGiaThue(giathue);
			}
			if(!txttrangthai.getText().isEmpty()) {
				xe.setTrangThai(txttrangthai.getText());
			}
			tt.update(xe);
			// 2. Tự đóng cửa sổ Pop-up Đăng nhập lại
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
			showErrorAlert("trạng thái update", "update thành công xe id:"+txtmaxe.getText());
		} catch (Exception e) {
			System.out.println("có lỗi:"+e);
			showErrorAlert("trạng thái update", "update không thành công");
		}
	}
	public void setupdatexe(Xe xe) {
		int idxe=xe.getMaXe();
		txtmaxe.setText(String.valueOf(idxe));
		txtmaxe.setEditable(false);
	}
	public static void UpdatexeAction(javafx.scene.Node node, Xe xe) {
	    try {
	        // 1. Nạp giao diện từ FXML
	        ScreenCacheManager.ScreenUI screenUI = ScreenCacheManager.getScreen("/Dangnhap/UpdateXe.fxml");
	        Parent root = screenUI.getRoot();

	        // 2. Truyền đối tượng Xe sang Controller mới
	        QuanLyXeController controller = (QuanLyXeController)screenUI.getController();
	        if (controller != null) {
	            controller.setupdatexe(xe);
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
	        System.out.println("Lỗi khi mở Form Cập Nhật Xe: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	public void initializeAdmin(URL location, ResourceBundle resources) {
	    // 1. Ánh xạ các cột thông thường
	    clmaxe.setCellValueFactory(new PropertyValueFactory<>("maXe"));
	    cltenxe.setCellValueFactory(new PropertyValueFactory<>("tenXe"));
	    clnsx.setCellValueFactory(new PropertyValueFactory<>("namSanXuat"));
	    clsochongoi.setCellValueFactory(new PropertyValueFactory<>("soChoNgoi"));
	    clmota.setCellValueFactory(new PropertyValueFactory<>("moTa"));
	    clngaynhap.setCellValueFactory(new PropertyValueFactory<>("ngayNhap"));
	    clgiathue.setCellValueFactory(new PropertyValueFactory<>("giaThue"));
	    clmausac.setCellValueFactory(new PropertyValueFactory<>("mauSac"));
	    cltrangthai.setCellValueFactory(new PropertyValueFactory<>("trangThai"));

	    // 2. Ánh xạ cột Khóa Ngoại (Tên Hãng Xe)
	    clhangxe.setCellValueFactory(cellData -> {
	        Xe xe = cellData.getValue();
	        if (xe != null && xe.getHangXe() != null) {
	            return new SimpleStringProperty(xe.getHangXe().getTenHangXe());
	        }
	        return new SimpleStringProperty("");
	    });
	    
	 // 3. Cấu hình Cột Thao Tác
	    clthaotac.setCellFactory(param -> new TableCell<Xe, Void>() {
	        @Override
		        protected void updateItem(Void item, boolean empty) {
		            super.updateItem(item, empty);
	
		            if (empty) {
		                setGraphic(null);
		            } else {
		                // Chỉ định rõ package JavaFX ngay tại đây
		                javafx.scene.control.Button btnupdate = new javafx.scene.control.Button("update");
		                btnupdate.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-cursor: hand;");
	
		                btnupdate.setOnAction(event -> {
		                    Xe xeDuocChon = getTableView().getItems().get(getIndex());
		                    if (xeDuocChon != null) {
		                        QuanLyXeController.UpdatexeAction(btnupdate, xeDuocChon);
		                    }
		                });
	
		                setGraphic(btnupdate);
		            }
		        }
	    });
	    // 4. Nạp dữ liệu lên TableView
	    loadDataToTable();
	}

	@FXML
	public static void formDangNhap(javafx.scene.Node node){
	    		try {
		        // 1. Nạp giao diện từ FXML
		        FXMLLoader loader = new FXMLLoader(ThuexeController.class.getResource("/Dangnhap/Dangnhap.fxml"));
		        Parent root = loader.load();

		        // 3. Khởi tạo một Cửa sổ mới (Stage mới)
		        Stage popupStage = new Stage();
		        popupStage.setTitle("Trang đăng nhập");
		        popupStage.setScene(new Scene(root));

		        // 4. Thiết lập chế độ Modal (Khóa màn hình chính cho đến khi đóng cửa sổ này)
		        popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
		        
		        // (Tùy chọn) Gán cửa sổ cha để pop-up luôn nằm trên màn hình chính
		        if (node != null && node.getScene() != null) {
		            popupStage.initOwner(node.getScene().getWindow());
		        }

		        // 5. Hiển thị cửa sổ dạng pop-up
		        popupStage.showAndWait();

		    } catch (Exception e) {
		        System.out.println("Lỗi khi mở Form Cập Nhật Xe: " + e.getMessage());
		        e.printStackTrace();
		    }
	 }
	@FXML
	public void dongFormAction(ActionEvent event) {
	    // Lấy Stage của chính cửa sổ pop-up và đóng lại
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.close();
	}
	@FXML
	public void taidulieuadmin(){
		initializeAdmin(null, null);
	}
	public void openDangnhap(ActionEvent event){
		try {
	        // 1. Nạp FXML Pop-up Đăng nhập
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dangnhap/Dangnhap.fxml"));
	        Parent root = loader.load();

	        // 2. Lấy controller của Pop-up
	        DangNhapController dangNhapCtrl = loader.getController();

	        // 3. Khởi tạo Stage Pop-up Modal
	        Stage popupStage = new Stage();
	        popupStage.setTitle("Đăng nhập");
	        popupStage.setScene(new Scene(root));
	        popupStage.initModality(Modality.APPLICATION_MODAL);
	        popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());

	        // 4. Hiển thị Pop-up và DỪNG LẠI CHỜ người dùng đăng nhập xong
	        popupStage.showAndWait(); 
	        // ========================================================
	        // CODE DƯỚI ĐÂY SẼ CHẠY NGAY SAU KHU POP-UP ĐẮNG NHẬP ĐÓNG LẠI
	        // ========================================================
	        // 5. Kiểm tra xem người dùng đã đăng nhập thành công chưa
	        KhachHang kh = dangNhapCtrl.getLoggedInUser();
	        
	        if (kh != null) { // có tài khoản người dùng trong csdl;
	            String vaitro=kh.getTaiKhoan().getVaiTro();
	            if("khách Hàng".equalsIgnoreCase(vaitro)){
	            		this.setkhachhang(kh);      // Gọi hàm set dữ liệu khách hàng (đổi tên nút, set ID...)
	            		this.initialize(null, null);
	            }
	            else if("admin".equalsIgnoreCase(vaitro)) {
	            		QuanLyXeController.chuyentrangadmin(event);
	            }else showErrorAlert("Lỗi người dùng", "không có vai trò hợp lệ");
	            } else {
	                	showErrorAlert("Lỗi đăng nhập", "đăng nhập thất bại");
	            }
	    } catch (Exception e) {
	        System.out.println("Lỗi khi mở form đăng nhập: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
}
