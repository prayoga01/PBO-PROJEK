package Tiket;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
	private static Connection koneksi;
	public static Connection getKoneksi(){
		if (koneksi==null){
			try {
				String url = "jdbc:mysql://localhost/tiket_wisata";
				String username="root";
				String password="";
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				koneksi = DriverManager.getConnection(url,username,password);
			
			}
			catch(Exception ex){
				System.out.println("Conector : "+ex);
			}
		}
		return koneksi;
	}
}
