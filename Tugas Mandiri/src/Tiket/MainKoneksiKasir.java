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
import java.sql.SQLException;
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
import java.awt.Font;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;


public class MainKoneksiKasir extends JFrame{
	private JPanel contentPane;
	String header[] = {"Tanggal","Kode","Jumlah","Nama pembeli 1","Nama pembeli 2","Nama pembeli 3","Nama pembeli 4","Alamat","Jenis Tiket","Total Bayar (Rp)"};
	DefaultTableModel tabelModel;
	private JTable table;
	private JTextField textFieldNama_1;
	private JTextField textFieldAlamat;
	private JComboBox comboBoxJumlah;
	private JRadioButton rdbtnReguler;
	private JRadioButton rdbtnnonreguler;
	private JLabel lblTanggal;
	private String tgl, Nama1, Nama2, Nama3, Nama4, Alamat, jenis, jumlah, Output, kode, random, id;
	private int bayar, orang, harga ;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnHitung;
	private JTextField textFieldHasil;
	private JDateChooser dateChooser;
	private JTextArea textArea;
	private JTextField textFieldkode;
	private String ID;
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainKoneksiKasir frame = new MainKoneksiKasir();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MainKoneksiKasir() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1238, 732);
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
		btnTampilkanData.setBounds(619, 455, 179, 29);
		contentPane.add(btnTampilkanData);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(20, 503, 1192, 184);
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
		lblNama_1.setForeground(new Color(255, 255, 255));
		lblNama_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNama_1.setBounds(472, 193, 108, 20);
		contentPane.add(lblNama_1);
		
		JLabel lblAlamatDosen = new JLabel("Alamat ");
		lblAlamatDosen.setForeground(new Color(255, 255, 255));
		lblAlamatDosen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAlamatDosen.setBounds(35, 261, 108, 20);
		contentPane.add(lblAlamatDosen);
		
		JLabel lblPembeli = new JLabel("Jumlah Pembeli");
		lblPembeli.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPembeli.setForeground(new Color(255, 255, 255));
		lblPembeli.setBounds(35, 300, 153, 20);
		contentPane.add(lblPembeli);
		
		JLabel lblJenisTiket = new JLabel("Jenis Tiket");
		lblJenisTiket.setForeground(new Color(255, 255, 255));
		lblJenisTiket.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblJenisTiket.setBounds(35, 330, 129, 20);
		contentPane.add(lblJenisTiket);
		
		textFieldNama_1 = new JTextField();
		textFieldNama_1.setBounds(601, 185, 224, 26);
		contentPane.add(textFieldNama_1);
		textFieldNama_1.setColumns(10);
		
		textFieldAlamat = new JTextField();
		textFieldAlamat.setBounds(164, 253, 224, 26);
		contentPane.add(textFieldAlamat);
		textFieldAlamat.setColumns(10);
		
		comboBoxJumlah = new JComboBox();
		comboBoxJumlah.setModel(new DefaultComboBoxModel(new String[] {"1 Orang", "2 Orang", "3 Orang", "4 Orang"}));
		comboBoxJumlah.setBounds(164, 293, 224, 26);
		contentPane.add(comboBoxJumlah);
		
		rdbtnReguler = new JRadioButton("Reguler");
		rdbtnReguler.setForeground(new Color(255, 255, 255));
		rdbtnReguler.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnReguler.setBackground(new Color(119, 136, 153));
		buttonGroup.add(rdbtnReguler);
		rdbtnReguler.setBounds(165, 321, 101, 29);
		contentPane.add(rdbtnReguler);
		
		rdbtnnonreguler = new JRadioButton("Non Reguler");
		rdbtnnonreguler.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnnonreguler.setForeground(new Color(255, 255, 255));
		rdbtnnonreguler.setBackground(new Color(119, 136, 153));
		buttonGroup.add(rdbtnnonreguler);
		rdbtnnonreguler.setBounds(291, 321, 155, 29);
		contentPane.add(rdbtnnonreguler);
		
		JButton btnTambah = new JButton("Insert");
		btnTambah.setBackground(new Color(255, 255, 255));
		btnTambah.setForeground(new Color(0, 0, 0));
		btnTambah.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertDataTable();
				emptyTable();
				clearInput();
				getDataTable();
			}
		});
		btnTambah.setBounds(289, 455, 127, 29);
		contentPane.add(btnTambah);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateDataTable();
				emptyTable();
				clearInput();
				getDataTable();
				
			}
		});
		btnUpdate.setBounds(462, 455, 127, 29);
		contentPane.add(btnUpdate);
		
		JLabel lblNama_2 = new JLabel("Nama 2");
		lblNama_2.setForeground(new Color(255, 255, 255));
		lblNama_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNama_2.setBounds(472, 226, 108, 20);
		contentPane.add(lblNama_2);
		
		JLabel lblNama_3 = new JLabel("Nama 3");
		lblNama_3.setForeground(new Color(255, 255, 255));
		lblNama_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNama_3.setBounds(472, 263, 108, 20);
		contentPane.add(lblNama_3);
		
		JLabel lblNama_4 = new JLabel("Nama 4");
		lblNama_4.setForeground(new Color(255, 255, 255));
		lblNama_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNama_4.setBounds(472, 300, 108, 20);
		contentPane.add(lblNama_4);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(601, 224, 224, 26);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(601, 259, 224, 26);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(601, 297, 224, 26);
		contentPane.add(textField_4);
		
		JLabel lblNewLabel = new JLabel("Rp. 10.000");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(174, 347, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblRp = new JLabel("Rp. 20.000");
		lblRp.setForeground(new Color(255, 255, 255));
		lblRp.setBounds(301, 347, 69, 14);
		contentPane.add(lblRp);
		
		JLabel lblTotalBayar = new JLabel("Total Bayar");
		lblTotalBayar.setForeground(new Color(255, 255, 255));
		lblTotalBayar.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTotalBayar.setBounds(35, 69, 155, 29);
		contentPane.add(lblTotalBayar);
		
		btnHitung = new JButton("Total Harga");
		btnHitung.setBackground(Color.GREEN);
		btnHitung.setForeground(Color.BLACK);
		btnHitung.setFont(new Font("Kozuka Gothic Pr6N EL", Font.BOLD, 12));
		btnHitung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hitung();
			}
		});
		btnHitung.setBounds(380, 393, 129, 29);
		contentPane.add(btnHitung);
		
		lblTanggal = new JLabel("Tanggal di booking");
		lblTanggal.setForeground(new Color(255, 255, 255));
		lblTanggal.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTanggal.setBounds(35, 185, 129, 20);
		contentPane.add(lblTanggal);
		
		textFieldHasil = new JTextField();
		textFieldHasil.setFont(new Font("Tahoma", Font.PLAIN, 50));
		textFieldHasil.setColumns(10);
		textFieldHasil.setBounds(35, 94, 874, 58);
		contentPane.add(textFieldHasil);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(164, 185, 224, 20);
		contentPane.add(dateChooser);
		
		textArea = new JTextArea();
		textArea.setBounds(919, 94, 298, 328);
		contentPane.add(textArea);
		
		JButton btnCetak = new JButton("CETAK FAKTUR");
		btnCetak.setBackground(new Color(255, 255, 255));
		btnCetak.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCetak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				random();
				proses ();
			}
		});
		btnCetak.setBounds(543, 393, 142, 29);
		contentPane.add(btnCetak);
		
		JLabel lblKode = new JLabel("Kode");
		lblKode.setForeground(new Color(255, 255, 255));
		lblKode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblKode.setBounds(35, 224, 108, 20);
		contentPane.add(lblKode);
		
		textFieldkode = new JTextField();
		textFieldkode.setEditable(false);
		textFieldkode.setFont(new Font("Tahoma", Font.BOLD, 11));
		textFieldkode.setForeground(Color.RED);
		textFieldkode.setText("kode akan keluar sendiri!!!!");
		textFieldkode.setColumns(10);
		textFieldkode.setBounds(164, 216, 224, 26);
		contentPane.add(textFieldkode);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 77, 29);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Leelawadee UI", Font.BOLD, 14));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Konfirmasi Tiket");
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem.setFont(new Font("Franklin Gothic Medium Cond", Font.PLAIN, 16));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchData sc = new searchData();
				sc.setLocationRelativeTo(null);
				sc.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit Program");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FLogin fl = new FLogin();
				fl.setLocationRelativeTo(null);
				fl.setVisible(true);
			}
		});
		mntmNewMenuItem_1.setBackground(Color.RED);
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_1.setFont(new Font("Franklin Gothic Demi Cond", Font.PLAIN, 16));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JLabel lblBookingTiket = new JLabel("BOOKING TIKET");
		lblBookingTiket.setForeground(Color.WHITE);
		lblBookingTiket.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBookingTiket.setBounds(440, 23, 196, 38);
		contentPane.add(lblBookingTiket);
	}
	
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
	
	public void InsertDataTable(){
		String tgl = (new java.text.SimpleDateFormat ("yyyy-MM-dd")).format(dateChooser.getDate());
		//String id ="";
		//id+=""+(random)+"\n";
		//textFieldkode.setText(id);
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
	
	public void proses () {
		String tgl = (new java.text.SimpleDateFormat ("yyyy-MM-dd")).format(dateChooser.getDate());
		String random=textFieldkode.getText();
		String Nama1=textFieldNama_1.getText();
		String Nama2=textField_2.getText();
		String Nama3=textField_3.getText();
		String Nama4=textField_4.getText();
		String Alamat=textFieldAlamat.getText();

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
		Output+=("=====================================================")+"\n";
		Output+=("\tNOTA PEMBAYARAN")+"\n";
		Output+=("=====================================================")+"\n";
		Output+= "Tanggal \t\t:" +tgl +"\n";
		Output+= "ID Tiket \t\t:" +random+"\n"; 
		Output+= "Jenis Tiket \t\t: " +jenis +"\n";
		Output+= "Pembeli 1 \t\t: " +Nama1 +"\n";
		Output+= "Pembeli 2 \t\t: " +Nama2 +"\n";
		Output+= "Pembeli 3 \t\t:" +Nama3 +"\n";
		Output+= "Pembeli 4 \t\t:" +Nama4 +"\n";
		Output+= "Alamat \t\t:" +Alamat +"\n";
		Output+= "Jumlah Orang \t\t: " +jumlah +"\n"; 
		Output+="Harga Tiket \t\t:" +Math.round(orang)+" x Rp" +Math.round(harga)+"\n";
		Output+=("=====================================================")+"\n";
		Output+="Total Harga \t\t: Rp."+Math.round(bayar)+"\n";
		Output+=("=====================================================")+"\n";
		Output+=("TERIMAKASIH AND HAVE NICE DAY")+"\n";
		Output+=("\tBOOKING TICKET")+"\n";
	 textArea.setText(Output); 
	}
}
