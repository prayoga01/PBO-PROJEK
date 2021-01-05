package Tiket;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtuserid;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FLogin frame = new FLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FLogin() {
		setBackground(new Color(211, 211, 211));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserId = new JLabel("Userid");
		lblUserId.setForeground(new Color(255, 255, 255));
		lblUserId.setFont(new Font("Lato Black", Font.BOLD, 16));
		lblUserId.setBounds(51, 62, 74, 30);
		contentPane.add(lblUserId);
		
		txtuserid = new JTextField();
		txtuserid.setFont(new Font("Swis721 BT", Font.PLAIN, 14));
		txtuserid.setBounds(149, 62, 186, 26);
		contentPane.add(txtuserid);
		txtuserid.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cekkasir()){
					MenuKasir mk = new MenuKasir();
					mk.setLocationRelativeTo(null);
					mk.setVisible(true);
					setVisible(false);
					javax.swing.JOptionPane.showMessageDialog(null, "Haiii anda masuk di program kasir");
				}
				else if(cekadmin()){
					MenuAdmin fmenu = new MenuAdmin();
					fmenu.setLocationRelativeTo(null);
					fmenu.setVisible(true);
					setVisible(false);
					javax.swing.JOptionPane.showMessageDialog(null, "Haiii selamat datang minn :)");
				}
				else {
					javax.swing.JOptionPane.showMessageDialog(null, "userid dan password anda salah");
					txtuserid.setText("");
					txtuserid.requestFocus();
					passwordField.setText("");
				}
			}
		});
		btnLogin.setBounds(187, 173, 89, 30);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Swis721 BT", Font.PLAIN, 14));
		passwordField.setBounds(149, 100, 186, 26);
		contentPane.add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.setForeground(new Color(255, 255, 255));
		chckbxNewCheckBox.setBackground(new Color(0, 128, 128));
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					passwordField.setEchoChar((char)0);
				}else {
					passwordField.setEchoChar('*');
				}
			}
		});
		chckbxNewCheckBox.setBounds(149, 133, 127, 23);
		contentPane.add(chckbxNewCheckBox);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Lato Black", Font.BOLD, 16));
		lblPassword.setBounds(51, 100, 74, 30);
		contentPane.add(lblPassword);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Lato Black", Font.BOLD, 16));
		lblLogin.setBounds(129, 11, 186, 30);
		contentPane.add(lblLogin);
	}
	
	public boolean cekkasir(){
		boolean adauser=false;
		String userid=this.txtuserid.getText();
		String pwd = this.passwordField.getText();
		
		try
		{
			Connection konek = Koneksi.getKoneksi();
			String query = "SELECT userid,password FROM kasir WHERE userid=? and password=?";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, userid);
			p.setString(2, pwd);
			
			ResultSet rs = p.executeQuery();
			while(rs.next())
			{	
				adauser=true;
			}
			rs.close();
		}
		catch(Exception ex)
		{
		}
		return (adauser);
	}
	
	public boolean cekadmin(){
		boolean adauser=false;
		String userid=this.txtuserid.getText();
		String pwd = this.passwordField.getText();
		
		try
		{
			Connection konek = Koneksi.getKoneksi();
			String query = "SELECT userid,password FROM admin WHERE userid=? and password=?";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, userid);
			p.setString(2, pwd);
			
			ResultSet rs = p.executeQuery();
			while(rs.next())
			{	
				adauser=true;
			}
			rs.close();
		}
		catch(Exception ex)
		{
		}
		return (adauser);
	}
}
