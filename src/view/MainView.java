package view;

import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.JDesktopPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import main.Main;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainView extends View{
	private static MainView mainView;
	
	JDesktopPane desktopPane;
	private CheckoutInternalView checkOutView;
	private LoginInternalView loginView;
	private PromoManagementInternalView promoView;
	private StorageManagementInternalView storageView;
	private HumanResourceInternalView hrView;
	private ManagerInternalView managerView;
	private CashierInternalView cashierView;
	
	//method untuk membuka JInternalFrame
	private void openInternalFrame(JInternalFrame frame) {
	    Dimension jInternalFrameSize = frame.getSize();
	    int width = (900 - jInternalFrameSize.width) / 2;
	    frame.setLocation(width, 0);
	    frame.setVisible(true);
	}
	
	//method untuk membuka InternalFrame CheckOut
	public void openCheckoutFrame() {
		checkOutView.refresh();
		openInternalFrame(checkOutView);
	}
	
	//method untuk membuka InternalFrame Login
	public void openLoginFrame() {
		openInternalFrame(loginView);
	}
	
	//method untuk membuka InternalFrame HumanResource
	public void openHrView() {
		openInternalFrame(hrView);
	}
	
	//method untuk membuka InternalFrame Manager
	public void openManagerView() {
		openInternalFrame(managerView);
	}
	
	//method untuk membuka InternalFrame Promo
	public void openPromoView() {
		openInternalFrame(promoView);
	}
	
	//method untuk membuka InternalFrame Storage
	public void openStorageView() {
		openInternalFrame(storageView);
	}
	
	//method untuk membuka InternalFrame cashier
	public void openCashierView() {
		openInternalFrame(cashierView);
	}
	
	//method untuk menambahkan internalFrame kedalam JDesktopPane
	private void addInternalFrame() {
		desktopPane.add(loginView);
		desktopPane.add(promoView);
		desktopPane.add(storageView);
		desktopPane.add(cashierView);
		desktopPane.add(checkOutView);
		desktopPane.add(hrView);
		desktopPane.add(managerView);
	}

	
	private MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Sudut Meong");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		setBounds(new Rectangle(0, 23, 900, 763));
		
		desktopPane = new JDesktopPane();
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		
		loginView = new LoginInternalView();
		hrView = new HumanResourceInternalView();
		managerView = new ManagerInternalView();
		promoView = new PromoManagementInternalView();
		storageView = new StorageManagementInternalView();
		cashierView = CashierInternalView.getInstance();
		checkOutView = new CheckoutInternalView();
		
		addInternalFrame();
		openLoginFrame();
	}
	
	public static synchronized MainView getInstance() {
		return (mainView==null) ? mainView = new MainView() : mainView;
	}
}
