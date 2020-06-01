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
	
	private void openInternalFrame(JInternalFrame frame) {
	    Dimension jInternalFrameSize = frame.getSize();
	    int width = (900 - jInternalFrameSize.width) / 2;
	    frame.setLocation(width, 0);
	    frame.setVisible(true);
	}

	public void openCheckoutFrame() {
		checkOutView.refresh();
		openInternalFrame(checkOutView);
	}
	
	public void openLoginFrame() {
		openInternalFrame(loginView);
	}
	
	public void openHrView() {
		openInternalFrame(hrView);
	}
	
	public void openManagerView() {
		openInternalFrame(managerView);
	}
	
	public void openPromoView() {
		openInternalFrame(promoView);
	}
	
	public void openStorageView() {
		openInternalFrame(storageView);
	}
	
	public void openCashierView() {
		openInternalFrame(cashierView);
	}
	
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
