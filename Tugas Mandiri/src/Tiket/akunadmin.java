package Tiket;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;

import Tiket.Koneksi;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;

public class akunadmin extends JFrame {

	private JPanel contentPane;
	private JLabel lblPassword;
	private JLabel lblID;
	private JTextField textFieldID;
	private String id, pass;
	private JPasswordField passwordField;
	private JButton btnExit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					akunadmin frame = new akunadmin();
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
	public akunadmin() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblID = new JLabel("Userid");
		lblID.setForeground(new Color(255, 255, 255));
		lblID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblID.setBounds(66, 67, 83, 26);
		contentPane.add(lblID);
		
		textFieldID = new JTextField();
		textFieldID.setBounds(142, 69, 209, 23);
		contentPane.add(textFieldID);
		textFieldID.setColumns(10);
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPassword.setBounds(66, 104, 83, 26);
		contentPane.add(lblPassword);
		
		JButton btnDAFTAR = new JButton("DAFTAR");
		btnDAFTAR.setBackground(Color.GREEN);
		btnDAFTAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				daftar ();
				clear ();
				javax.swing.JOptionPane.showMessageDialog(null, "Selamat pembuatan akun admin SUKSES:)");
			}
		});
		btnDAFTAR.setBounds(190, 167, 96, 32);
		contentPane.add(btnDAFTAR);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(142, 107, 209, 23);
		contentPane.add(passwordField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.setForeground(new Color(255, 255, 255));
		chckbxNewCheckBox.setBackground(new Color(0, 128, 128));
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					passwordField.setEchoChar((char)0);
				}else {
					passwordField.setEchoChar('*');
				}
			}
		});
		chckbxNewCheckBox.setBounds(142, 137, 124, 23);
		contentPane.add(chckbxNewCheckBox);
		
		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAdmin sd = new MenuAdmin();
				sd.setLocationRelativeTo(null);
				sd.setVisible(true);
			}
		});
		btnExit.setBounds(190, 210, 96, 32);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("DAFTAR AKUN ADMIN");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Century Schoolbook", Font.BOLD, 16));
		lblNewLabel.setBounds(128, 11, 209, 26);
		contentPane.add(lblNewLabel);
	}
	public void daftar (){
		String id = textFieldID.getText();
		String pass = passwordField. getText();
		try{
			Connection konek=Koneksi.getKoneksi();
			String query = "INSERT INTO admin(userid, password) values (?,?)"; 
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, id);
			p.setString(2, pass);
			
			p.executeUpdate();
			p.close();
			  
		}
		catch(Exception ex){
			System.out.println(ex);
			}
	}
	
	public void clear () {
		textFieldID.setText("");
		passwordField.setText("");
	}
}
