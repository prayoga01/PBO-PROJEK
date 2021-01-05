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
import java.text.SimpleDateFormat;
import java.util.Random;

import Tiket.Koneksi;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;
import java.awt.BorderLayout;
import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;


public class MainKoneksi extends JFrame{
	private JPanel contentPane;
	String header[] = {"Tanggal","ID","Jumlah","Nama pembeli 1","Nama pembeli 2","Nama pembeli 3","Nama pembeli 4","Alamat","Jenis Tiket","Total Bayar (Rp)"};
	DefaultTableModel tabelModel;
	private JTable table;
	private JTextField textFieldNama_1;
	private JTextField textFieldAlamat;
	private JComboBox comboBoxJumlah;
	private JRadioButton rdbtnReguler;
	private JRadioButton rdbtnnonreguler;
	private JLabel lblTanggal;
	private String tgl, Nama1, Nama2, Nama3, Nama4, Alamat, jenis, jumlah, Output, kode, random, id;
	private int  bayar, orang, harga ;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnHitung;
	private JTextField textFieldHasil;
	private JDateChooser dateChooser;
	private JTextField textFieldkode;
	private String ID ;
	private JTextField textcari;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainKoneksi frame = new MainKoneksi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainKoneksi() {
		setBackground(new Color(0, 128, 128));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1005, 716);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 136, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnTampilkanData = new JButton("TAMPILKAN DATA");
		btnTampilkanData.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTampilkanData.setBackground(new Color(255, 255, 255));
		btnTampilkanData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyTable();
				getDataTable(); 
			}
		});
		btnTampilkanData.setBounds(663, 372, 179, 29);
		contentPane.add(btnTampilkanData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 434, 969, 225);
		contentPane.add(scrollPane);
		
		tabelModel = new DefaultTableModel(null,header);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i= table.getSelectedRow();
				
				

				Date tgl =(Date) tabelModel.getValueAt(i,0);
				//ID = (int) tabelModel.getValueAt(i,1);
				String id=(String) tabelModel.getValueAt(i,1);
				ID= id;
				String Jumlah=(String) tabelModel.getValueAt(i,2);
				String Nama1=(String) tabelModel.getValueAt(i,3);
				String Nama2=(String) tabelModel.getValueAt(i,4);
				String Nama3=(String) tabelModel.getValueAt(i,5);
				String Nama4=(String) tabelModel.getValueAt(i,6);
				String Alamat=(String) tabelModel.getValueAt(i,7);
				String jenis=(String) tabelModel.getValueAt(i,8);
				String bayar=(String) tabelModel.getValueAt(i,9);
									
				dateChooser.setDate(tgl);
				textFieldkode.setText(id);
				comboBoxJumlah.setSelectedItem(Jumlah);
				textFieldNama_1.setText(Nama1);
				textField_2.setText(Nama2);
				textField_3.setText(Nama3);
				textField_4.setText(Nama4);
				textFieldAlamat.setText(Alamat);
					
					if (jenis.equals("Reguler")) {
						rdbtnReguler.setSelected(true);	
					}else if (jenis.equals("Non Reguler")) {
						rdbtnnonreguler.setSelected(true);	
						}
					
					textFieldHasil.setText(bayar);
			}
		});
		table.setModel(tabelModel);
		scrollPane.setViewportView(table);
		
		JLabel lblNama_1 = new JLabel("Nama 1");
		lblNama_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNama_1.setForeground(new Color(255, 255, 255));
		lblNama_1.setBounds(489, 142, 108, 20);
		contentPane.add(lblNama_1);
		
		JLabel lblAlamatDosen = new JLabel("Alamat ");
		lblAlamatDosen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAlamatDosen.setForeground(new Color(255, 255, 255));
		lblAlamatDosen.setBounds(88, 223, 108, 20);
		contentPane.add(lblAlamatDosen);
		
		JLabel lblPembeli = new JLabel("Jumlah Pembeli");
		lblPembeli.setForeground(new Color(255, 255, 255));
		lblPembeli.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPembeli.setBounds(88, 262, 153, 20);
		contentPane.add(lblPembeli);
		
		JLabel lblJenisTiket = new JLabel("Jenis Tiket");
		lblJenisTiket.setForeground(new Color(255, 255, 255));
		lblJenisTiket.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblJenisTiket.setBounds(88, 298, 129, 20);
		contentPane.add(lblJenisTiket);
		
		textFieldNama_1 = new JTextField();
		textFieldNama_1.setBackground(new Color(255, 255, 255));
		textFieldNama_1.setBounds(618, 139, 224, 26);
		contentPane.add(textFieldNama_1);
		textFieldNama_1.setColumns(10);
		
		textFieldAlamat = new JTextField();
		textFieldAlamat.setBackground(new Color(255, 255, 255));
		textFieldAlamat.setBounds(217, 215, 224, 26);
		contentPane.add(textFieldAlamat);
		textFieldAlamat.setColumns(10);
		
		comboBoxJumlah = new JComboBox();
		comboBoxJumlah.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBoxJumlah.setBackground(new Color(255, 255, 255));
		comboBoxJumlah.setModel(new DefaultComboBoxModel(new String[] {"1 Orang", "2 Orang", "3 Orang", "4 Orang"}));
		comboBoxJumlah.setBounds(217, 254, 224, 26);
		contentPane.add(comboBoxJumlah);
		
		rdbtnReguler = new JRadioButton("Reguler");
		rdbtnReguler.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnReguler.setForeground(new Color(255, 255, 255));
		rdbtnReguler.setBackground(new Color(119, 136, 153));
		buttonGroup.add(rdbtnReguler);
		rdbtnReguler.setBounds(218, 289, 101, 29);
		contentPane.add(rdbtnReguler);
		
		rdbtnnonreguler = new JRadioButton("Non Reguler");
		rdbtnnonreguler.setForeground(new Color(255, 255, 255));
		rdbtnnonreguler.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnnonreguler.setBackground(new Color(119, 136, 153));
		buttonGroup.add(rdbtnnonreguler);
		rdbtnnonreguler.setBounds(344, 289, 101, 29);
		contentPane.add(rdbtnnonreguler);
		
		JButton btnTambah = new JButton("Insert");
		btnTambah.setBackground(new Color(255, 255, 255));
		btnTambah.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertDataTable();
				emptyTable();
				clearInput();
				getDataTable();
			}
		});
		btnTambah.setBounds(147, 372, 127, 29);
		contentPane.add(btnTambah);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.setBackground(new Color(255, 255, 255));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateDataTable();
				emptyTable();
				clearInput();
				getDataTable();
			}
		});
		btnUpdate.setBounds(320, 372, 127, 29);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteDataTable();
				emptyTable();
				clearInput();
				getDataTable();
			}
		});
		btnDelete.setBounds(496, 372, 135, 29);
		contentPane.add(btnDelete);
		
		JLabel lblNama_2 = new JLabel("Nama 2");
		lblNama_2.setForeground(new Color(255, 255, 255));
		lblNama_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNama_2.setBounds(489, 182, 108, 20);
		contentPane.add(lblNama_2);
		
		JLabel lblNama_3 = new JLabel("Nama 3");
		lblNama_3.setForeground(new Color(255, 255, 255));
		lblNama_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNama_3.setBounds(489, 213, 108, 20);
		contentPane.add(lblNama_3);
		
		JLabel lblNama_4 = new JLabel("Nama 4");
		lblNama_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNama_4.setForeground(new Color(255, 255, 255));
		lblNama_4.setBounds(489, 244, 108, 20);
		contentPane.add(lblNama_4);
		
		textField_2 = new JTextField();
		textField_2.setBackground(new Color(255, 255, 255));
		textField_2.setColumns(10);
		textField_2.setBounds(618, 174, 224, 26);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBackground(new Color(255, 255, 255));
		textField_3.setColumns(10);
		textField_3.setBounds(618, 207, 224, 26);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBackground(new Color(255, 255, 255));
		textField_4.setColumns(10);
		textField_4.setBounds(618, 241, 224, 26);
		contentPane.add(textField_4);
		
		JLabel lblNewLabel = new JLabel("Rp. 10.000");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(227, 315, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblRp = new JLabel("Rp. 20.000");
		lblRp.setForeground(new Color(255, 255, 255));
		lblRp.setBounds(354, 315, 69, 14);
		contentPane.add(lblRp);
		
		JLabel lblTotalBayar = new JLabel("Total Bayar");
		lblTotalBayar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTotalBayar.setForeground(new Color(255, 255, 255));
		lblTotalBayar.setBounds(489, 279, 108, 20);
		contentPane.add(lblTotalBayar);
		
		btnHitung = new JButton("Total Harga");
		btnHitung.setForeground(new Color(0, 0, 0));
		btnHitung.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHitung.setBackground(new Color(255, 255, 255));
		btnHitung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				random(); // show code ticket
				hitung();
			}
		});
		btnHitung.setBounds(734, 309, 108, 24);
		contentPane.add(btnHitung);
		
		lblTanggal = new JLabel("Tanggal");
		lblTanggal.setForeground(new Color(255, 255, 255));
		lblTanggal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTanggal.setBounds(88, 147, 69, 20);
		contentPane.add(lblTanggal);
		
		textFieldHasil = new JTextField();
		textFieldHasil.setBackground(new Color(255, 255, 255));
		textFieldHasil.setColumns(10);
		textFieldHasil.setBounds(618, 275, 224, 26);
		contentPane.add(textFieldHasil);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(217, 139, 224, 28);
		contentPane.add(dateChooser);
		
		JLabel lblKode = new JLabel("Kode");
		lblKode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKode.setForeground(new Color(255, 255, 255));
		lblKode.setBounds(88, 186, 108, 20);
		contentPane.add(lblKode);
		
		textFieldkode = new JTextField();
		textFieldkode.setEditable(false);
		textFieldkode.setText("Kode akan keluar sendiri !!!");
		textFieldkode.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldkode.setForeground(Color.RED);
		textFieldkode.setColumns(10);
		textFieldkode.setBounds(216, 179, 224, 26);
		contentPane.add(textFieldkode);
		
		textcari = new JTextField();
		textcari.setBackground(new Color(255, 255, 255));
		textcari.setFont(new Font("Tahoma", Font.BOLD, 20));
		textcari.setColumns(10);
		textcari.setBounds(442, 80, 314, 34);
		contentPane.add(textcari);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emptyTable();
				cari();
			}
		});
		btnNewButton.setBounds(766, 79, 82, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("ADMIN");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 36));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(347, 11, 217, 57);
		contentPane.add(lblNewLabel_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 101, 22);
		contentPane.add(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setForeground(new Color(0, 0, 0));
		mnMenu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnMenu.setBackground(new Color(255, 255, 255));
		menuBar.add(mnMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Home");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuAdmin sd = new MenuAdmin();
				sd.setLocationRelativeTo(null);
				sd.setVisible(true);
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Log Out");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FLogin sd = new FLogin();
				sd.setLocationRelativeTo(null);
				sd.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnMenu.add(mntmNewMenuItem_1);
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
	
	public void emptyTable(){
		tabelModel.getDataVector().removeAllElements();
		tabelModel.fireTableDataChanged(); 
	}
	
	//-----RANDOM CODE TICKET-----//
	public void random() {
		kode = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		random = "";
		int length = 4;
		
		Random rand = new Random ();
		
		char[] text = new char[length];
		
		for(int i =0; i < length; i++) {
			text[i] = kode.charAt(rand.nextInt(kode.length()));
		}
		
		for(int i = 0; i < text.length; i++) {
			random += text[i];
		}
		String id ="";
		id+=""+(random)+"\n";
		textFieldkode.setText(id);
	}
	
	//-----RUMUS PERHITUNGAN HARGA-----//
	public void hitung() {
		 jumlah= (String) comboBoxJumlah.getSelectedItem();
		 if (jumlah == "1 Orang") {
			 orang = 1;
		 }else if (jumlah == "2 Orang") {
			 orang = 2;
		 } else if (jumlah == "3 Orang") {
			 orang = 3;
		 }else if (jumlah == "4 Orang") {
			 orang = 4;		 }
		 
		 jenis="";
			harga = 0;
			if (rdbtnReguler.isSelected()) {
				harga=(int)(10000);
				jenis="Reguler";
			}
			else if (rdbtnnonreguler.isSelected()) {
				harga=(int)(20000);
				jenis="Non Reguler";	
			}
			bayar = orang*harga;  
			
			String Output ="";
			Output+="Rp. "+Math.round(bayar)+"\n";
			
			textFieldHasil.setText(Output);
	}
	
	//----- MEMASUKAN DATA KE TABLE-----//
public void InsertDataTable(){
	String tgl = (new java.text.SimpleDateFormat ("yyyy-MM-dd")).format(dateChooser.getDate());
	String id ="";
	id+=""+(random)+"\n";
	textFieldkode.setText(id);
	String Nama1=textFieldNama_1.getText();
	String Nama2=textField_2.getText();
	String Nama3=textField_3.getText();
	String Nama4=textField_4.getText();
	String Alamat=textFieldAlamat.getText();
	String jumlah=comboBoxJumlah.getSelectedItem().toString();
	if (rdbtnReguler.isSelected()) {
			jenis="Reguler";
		}
		else if (rdbtnnonreguler.isSelected()) {
			jenis="Non Reguler";
		} 
	String Output ="";
	Output+="Rp. "+Math.round(bayar)+"\n";
	textFieldHasil.setText(Output);
		
	try{
		Connection konek=Koneksi.getKoneksi();
		String query = "INSERT INTO tiket(Tanggal,ID,Jumlah_pembeli,Nama1,Nama2,Nama3,Nama4,Alamat,Jenis_tiket,Total_bayar) values (?,?,?,?,?,?,?,?,?,?)"; 
		PreparedStatement p = konek.prepareStatement(query);
		p.setString(1, tgl);
		p.setString(2, id);
		p.setString(3, jumlah);
		p.setString(4, Nama1);
		p.setString(5, Nama2); 
		p.setString(6, Nama3);
		p.setString(7, Nama4);
		p.setString(8, Alamat);
		p.setString(9, jenis);
		p.setString(10, Output);

		p.executeUpdate();
		p.close();
		  
	}
	catch(Exception ex){
		System.out.println(ex);
		}
	}   

	//-----UDATE DATA DI TABLE-----//		
	public void UpdateDataTable(){
		String tgl = (new java.text.SimpleDateFormat ("yyyy-MM-dd")).format(dateChooser.getDate());
		String id = textFieldkode.getText();
		String Nama1=textFieldNama_1.getText();
		String Nama2=textField_2.getText();
		String Nama3=textField_3.getText();
		String Nama4=textField_4.getText();
		String Alamat=textFieldAlamat.getText();
		String jumlah=comboBoxJumlah.getSelectedItem().toString();
			if (rdbtnReguler.isSelected()) {
				jenis="Reguler";
			}
			else if (rdbtnnonreguler.isSelected()) {
				jenis="Non Reguler";
			}
			String Output ="";
			Output+="Rp. "+Math.round(bayar)+"\n";
			
			textFieldHasil.setText(Output);
			
		try{
			Connection konek=Koneksi.getKoneksi();
			 
			String query = "Update tiket set Tanggal=?, Jumlah_pembeli=?, Nama1=?, Nama2=?, Nama3=?, Nama4=?, Alamat=?, Jenis_tiket=?, Total_bayar=? where ID=?";
			PreparedStatement p = konek.prepareStatement(query);
			p.setString(1, tgl);
			p.setString(2, jumlah);
			p.setString(3, Nama1);
			p.setString(4, Nama2);
			p.setString(5, Nama3);
			p.setString(6, Nama4);
			p.setString(7, Alamat);
			p.setString(8, jenis);
			p.setString(9, Output);
			p.setString(10, ID);
		
			p.executeUpdate();
			p.close();
			
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	//-----CLEAR INPUTAN DI JLABLE-----//
	public void clearInput(){
		dateChooser.setCalendar(null);
		textFieldkode.setText("");
		textFieldNama_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_4.setText("");
		textFieldAlamat.setText("");
		comboBoxJumlah.setSelectedIndex(0);
		buttonGroup.clearSelection();		
		textFieldHasil.setText("");	
	}
	
	//-----DELETE DATA-----//
	public void DeleteDataTable(){
	//String id = textFieldkode.getText();
		try{
			Connection konek=Koneksi.getKoneksi();
			String query = "Delete From tiket where ID=?"; 
			PreparedStatement p = konek.prepareStatement(query);
			//p.setString(1, id);
			p.setString(1,ID );
			p.executeUpdate();
			p.close();
		}
		catch(Exception ex){
			System.out.println(ex);
		}
	}
	
	//-----MENCARI DATA-----//
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
