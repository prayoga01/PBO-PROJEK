package Tiket;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Tiket.Koneksi;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

public class searchData extends JFrame {

	private JPanel contentPane;
	String header[] = {"Tanggal","Kode","Jumlah","Nama pembeli 1","Nama pembeli 2","Nama pembeli 3","Nama pembeli 4","Alamat","Jenis Tiket","Total Bayar"};
	DefaultTableModel tabelModel;
	private JTable table;
	private String ID;
	private int halaman;
	private JTextField textcari;
	private JButton btnNewButton;
	private JButton btnTampilkanSemuaData;
	private JLabel lblNewLabel;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchData frame = new searchData();
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
	public searchData() {
		setBackground(new Color(220, 220, 220));	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 525);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 210, 902, 265);
		contentPane.add(scrollPane);
		
		tabelModel = new DefaultTableModel(null,header);
		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setModel(tabelModel);
		scrollPane.setViewportView(table);
		
		textcari = new JTextField();
		textcari.setBackground(new Color(255, 255, 255));
		textcari.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		textcari.setBounds(186, 70, 531, 34);
		contentPane.add(textcari);
		textcari.setColumns(10);
		
		btnNewButton = new JButton("SEARCH");
		btnNewButton.setBackground(new Color(124, 252, 0));
		btnNewButton.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyTable();
				cari();
			}
		});
		btnNewButton.setBounds(403, 120, 109, 34);
		contentPane.add(btnNewButton);
		
		btnTampilkanSemuaData = new JButton("TAMPILKAN SEMUA DATA");
		btnTampilkanSemuaData.setBackground(new Color(255, 255, 255));
		btnTampilkanSemuaData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyTable();
				getDataTable();
				 
			}
		});
		btnTampilkanSemuaData.setFont(new Font("Segoe UI Black", Font.BOLD, 11));
		btnTampilkanSemuaData.setBounds(351, 165, 211, 34);
		contentPane.add(btnTampilkanSemuaData);
		
		lblNewLabel = new JLabel("DATA TIKET");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(351, 39, 217, 34);
		contentPane.add(lblNewLabel);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(192, 192, 192));
		menuBar.setBounds(0, 0, 922, 28);
		contentPane.add(menuBar);
		
		mnNewMenu = new JMenu("Menu");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnNewMenu.setForeground(new Color(0, 0, 0));
		mnNewMenu.setBackground(new Color(255, 255, 255));
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Back");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainKoneksiKasir sd = new MainKoneksiKasir();
				sd.setLocationRelativeTo(null);
				sd.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		mntmNewMenuItem_1 = new JMenuItem("Exit ");
		mntmNewMenuItem_1.setBackground(new Color(255, 0, 0));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FLogin fl = new FLogin();
				fl.setLocationRelativeTo(null);
				fl.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
	}
	public void emptyTable(){
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged(); 
	}
	//-----MENAMPILKAN SEMUA DATA YANG ADA DI TABEL-----//
		public void getDataTable()
		{
			try
			{
				Connection konek = Koneksi.getKoneksi();
				Statement state = konek.createStatement();
				String query = "SELECT * FROM tiket";
				ResultSet rs = state.executeQuery(query);
				while(rs.next())
				{
					Object obj[] = new Object[10];
					obj[0] = rs.getDate(1);
					obj[1] = rs.getString(2);
					obj[2] = rs.getString(3);
					obj[3] = rs.getString(4);
					obj[4] = rs.getString(5);
					obj[5] = rs.getString(6);
					obj[6] = rs.getString(7);
					obj[7] = rs.getString(8);
					obj[8] = rs.getString(9);
					obj[9] = rs.getString(10);
					tabelModel.addRow(obj);
				}
				rs.close();
				state.close();
			}
			catch(Exception ex){
				System.out.println(ex);
			}
		}
		//----PROSES MENCARI DATA----//
	public void cari()
	{
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT Tanggal,ID,Jumlah_pembeli,Nama1,Nama2, Nama3, Nama4, Alamat,Jenis_tiket,Total_bayar FROM tiket WHERE Tanggal LIKE '%" + textcari.getText()+"%' OR ID LIKE '%" + textcari.getText()+"%' OR Jumlah_pembeli LIKE '%" + textcari.getText()+"%' OR Nama1 LIKE '%" + textcari.getText()+"%' OR Nama2 LIKE '%" + textcari.getText()+"%' OR Nama3 LIKE '%" + textcari.getText()+"%' OR Nama4 LIKE '%" + textcari.getText()+"%' OR Alamat LIKE '%" + textcari.getText()+"%' OR Jenis_tiket LIKE '%" + textcari.getText() +"%' OR Total_bayar LIKE '%" + textcari.getText()+ "%'";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				Object obj[] = new Object[10];
				obj[0] = rs.getDate(1);
				obj[1] = rs.getString(2);
				obj[2] = rs.getString(3);
				obj[3] = rs.getString(4);
				obj[4] = rs.getString(5);
				obj[5] = rs.getString(6);
				obj[6] = rs.getString(7);
				obj[7] = rs.getString(8);
				obj[8] = rs.getString(9);
				obj[9] = rs.getString(10);
				tabelModel.addRow(obj);
			}
			rs.close();
			state.close();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
}
