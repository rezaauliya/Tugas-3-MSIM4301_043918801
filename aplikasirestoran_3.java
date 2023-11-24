package aplikasirestoran_3;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

abstract class MenuItem {
	String nama;
	double harga;
	String kategori;
	
	public MenuItem(String nama, double harga, String kategori) {
		this.nama = nama;
		this.harga = harga;
		this.kategori = kategori;
	}
	
	public abstract void tampilMenu();
	
}

class Makanan extends MenuItem {
	
	public Makanan(String nama, double harga) {
		super(nama, harga, "Makanan");
		}
	
	@Override
	public void tampilMenu() {
		DecimalFormat kursIndonesia = (DecimalFormat)
				DecimalFormat.getCurrencyInstance();
				DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
		
		formatRp.setCurrencySymbol("Rp. ");
		
			formatRp.setMonetaryDecimalSeparator(',');
				formatRp.setGroupingSeparator('.');
				kursIndonesia.setDecimalFormatSymbols(formatRp);
		
		System.out.println(nama +"\t " +kursIndonesia.format(harga) + "\tKategori : " +kategori);
	}
}

class Minuman extends MenuItem {
	public Minuman(String nama, double harga) {
		super(nama, harga, "Minuman");
		}
	
	@Override
	public void tampilMenu() {
		DecimalFormat kursIndonesia = (DecimalFormat)
				DecimalFormat.getCurrencyInstance();
				DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
		
		formatRp.setCurrencySymbol("Rp. ");
		
			formatRp.setMonetaryDecimalSeparator(',');
				formatRp.setGroupingSeparator('.');
				kursIndonesia.setDecimalFormatSymbols(formatRp);
		
		System.out.println(nama +"\t " +kursIndonesia.format(harga) + "\tKategori : " +kategori);
	}
}

class Diskon extends MenuItem {
	public Diskon (String nama, double harga) {
		super(nama, harga, "Diskon");
	}
	
	@Override
	public void tampilMenu() {
		DecimalFormat kursIndonesia = (DecimalFormat)
				DecimalFormat.getCurrencyInstance();
				DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
		
		formatRp.setCurrencySymbol("Rp. ");
		
			formatRp.setMonetaryDecimalSeparator(',');
				formatRp.setGroupingSeparator('.');
				kursIndonesia.setDecimalFormatSymbols(formatRp);
		
		System.out.println(nama +" " +kursIndonesia.format(harga) + "  Kategori : " +kategori);
	}
}


class Menu {
	private ArrayList<MenuItem> daftarmenu;
	
	public Menu() {
		this.daftarmenu = new ArrayList<>();
	}
	
	public void isimenu(MenuItem isi) {
		daftarmenu.add(isi);
	}
	
	public void tampilMenu() {
		System.out.println("=============== DAFTAR MENU ===============");
		for (MenuItem isi : daftarmenu) {
			isi.tampilMenu();
		}
	}
	
	public MenuItem isimenuNama (String namaMenu) {
		for (MenuItem isi : daftarmenu) {
			if (isi.nama.equals(namaMenu)) {
				return isi;
			}
		} return null;
	}
	
	public ArrayList<MenuItem> getDaftarmenu() {
		
		return daftarmenu;
	}
	
	public void inputMenukeFile() {
		try (PrintWriter x = new PrintWriter(new File("menu-1.txt"))) {
			for (MenuItem isi : daftarmenu) {
				x.println(isi.nama + ", " +isi.harga + ", " + isi.kategori);
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error input menu ke dalam file : " +e.getMessage());
		}
	}
}


public class aplikasirestoran_3 {

	private static Menu menu = new Menu();
	private static Pesanan pesanan = new Pesanan();
	
	private static void inisialisasimenu() {
		try (Scanner inputFile = new Scanner (new File ("menu-1.txt"))) {
			while (inputFile.hasNextLine()) {
				String a = inputFile.nextLine();
				String[] b = a.split(",");
				if (b.length == 3) {
					String nama = b[0].trim();
					double harga = Double.parseDouble(b[1].trim());
					String kategori = b[2].trim();
					
					MenuItem isiMenu;
					if (kategori.equals("Makanan")) {
						isiMenu = new Makanan (nama, harga);
					} else if (kategori.equals("Minuman")) {
						isiMenu = new Minuman (nama, harga);
					} else {
						isiMenu = new Diskon (nama, harga);
					}
					menu.isimenu(isiMenu);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error saat membaca menu dari file : " +e.getMessage());
		}
	}
	
	
	private static void tampilMenuutama() {

		System.out.println("================= Menu Utama =================");
		System.out.println("[1] Pesan Makanan/Minuman");
		System.out.println("[2] Kelola Data Menu Makanan/Minuman");
		System.out.println("[0] Keluar Aplikasi");
		System.out.println();
		System.out.print("Masukkan kode menu yang Anda inginkan (0-2) : ");
	}
	
	
	private static void pemesanan(Scanner input) {
		DecimalFormat kursIndonesia = (DecimalFormat)
				DecimalFormat.getCurrencyInstance();
				DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
		
		formatRp.setCurrencySymbol("Rp. ");
		
			formatRp.setMonetaryDecimalSeparator(',');
				formatRp.setGroupingSeparator('.');
				kursIndonesia.setDecimalFormatSymbols(formatRp);

		System.out.println();
		System.out.println("========== Menu Makanan ==========");
		for (int i=0; i<menu.getDaftarmenu().size(); i++) {
			MenuItem menuu = menu.getDaftarmenu().get(i);
			if (menuu.kategori.equals("Makanan")) {
				System.out.println((i+1) +". " +menuu.nama +" \t\t: " +kursIndonesia.format(menuu.harga));					
			}
		}
		System.out.println();
		System.out.println("========== Menu Minuman ==========");
		for (int i=0; i<menu.getDaftarmenu().size(); i++) {
			MenuItem menuu = menu.getDaftarmenu().get(i);
			if (menuu.kategori.equals("Minuman")) {
				System.out.println((i+1) +". " +menuu.nama +"\t\t: " +kursIndonesia.format(menuu.harga));					
			}
		}
		while (true) {
			System.out.println();
			System.out.println("<<< Ketik 'x' untuk kembali ke menu utama dan membatalkan pesanan >>>");
			System.out.println("<<< Ketik 'selesai' untuk menyelesaikan pesanan dan menampilkan struk >>>");
			System.out.println();
			System.out.print("Masukkan kode menu makanan/minuman yang ingin Anda pesan : ");
			String choice = input.nextLine();
			
			switch (choice) {
			case "x" :
				System.out.println();
				return;
			} 
			
			if (choice.equalsIgnoreCase("selesai")) {
				pesanan.struk();
				break;
			} else {
				try {
					int menuIdx = Integer.parseInt(choice)-1;
					if (menuIdx >= 0 && menuIdx < menu.getDaftarmenu().size()) {
						MenuItem menuPilihan = menu.getDaftarmenu().get(menuIdx);
						System.out.print("Jumlah : ");
						int jum = Integer.parseInt(input.nextLine());
					
						System.out.println();
						System.out.println("Pesanan Anda berhasil ditambahkan!");
						System.out.println(menuPilihan.nama + " = " + jum);
					
						pesanan.addPesanan(menuPilihan,jum);
						
					} else {
						System.out.println("Kode menu yang Anda pilih tidak valid!");
					}
				} catch (NumberFormatException e) {
					System.out.println("Kode menu yang Anda pilih tidak valid!");
				}
			} 
		}
	}
	
	
	private static void kelolamenu(Scanner input) {
		DecimalFormat kursIndonesia = (DecimalFormat)
				DecimalFormat.getCurrencyInstance();
				DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
		
		formatRp.setCurrencySymbol("Rp. ");
		
			formatRp.setMonetaryDecimalSeparator(',');
				formatRp.setGroupingSeparator('.');
				kursIndonesia.setDecimalFormatSymbols(formatRp);

		while (true) {
			System.out.println();
			System.out.println("===== Pengelolaan Data Menu Makanan/Minuman =====");
			System.out.println("[1] Tambah menu makanan/minuman baru");
			System.out.println("[2] Ubah harga makanan/minuman");
			System.out.println("[3] Hapus menu makanan/minuman");
			System.out.println("[4] Tampilkan menu makanan/minuman");
			System.out.println("[0] Kembali ke Menu Utama");
			System.out.println();
			System.out.print("Masukkan kode menu kelola data yang Anda inginkan (0-4) : ");
			String pilihankelola = input.nextLine();
			System.out.println();
			
			switch (pilihankelola) {
			case "1" :
				System.out.println("========== Menu Makanan ==========");
				for (int i=0; i<menu.getDaftarmenu().size(); i++) {
					MenuItem menuu = menu.getDaftarmenu().get(i);
					if (menuu.kategori.equals("Makanan")) {
						System.out.println((i+1) +". " +menuu.nama +" \t\t: " +kursIndonesia.format(menuu.harga));					
					}
				}
				System.out.println();
				System.out.println("========== Menu Minuman ==========");
				for (int i=0; i<menu.getDaftarmenu().size(); i++) {
					MenuItem menuu = menu.getDaftarmenu().get(i);
					if (menuu.kategori.equals("Minuman")) {
						System.out.println((i+1) +". " +menuu.nama +"\t\t: " +kursIndonesia.format(menuu.harga));					
					}
				}
				tambahMenu(input);
				break;
				
			case "2" :
				System.out.println("========== Menu Makanan ==========");
				for (int i=0; i<menu.getDaftarmenu().size(); i++) {
					MenuItem menuu = menu.getDaftarmenu().get(i);
					if (menuu.kategori.equals("Makanan")) {
						System.out.println((i+1) +". " +menuu.nama +" \t\t: " +kursIndonesia.format(menuu.harga));					
					}
				}
				System.out.println();
				System.out.println("========== Menu Minuman ==========");
				for (int i=0; i<menu.getDaftarmenu().size(); i++) {
					MenuItem menuu = menu.getDaftarmenu().get(i);
					if (menuu.kategori.equals("Minuman")) {
						System.out.println((i+1) +". " +menuu.nama +"\t\t: " +kursIndonesia.format(menuu.harga));					
					}
				}
				ubahHargamenu(input);
				break;
			
			case "3" : 
				System.out.println("========== Menu Makanan ==========");
				for (int i=0; i<menu.getDaftarmenu().size(); i++) {
					MenuItem menuu = menu.getDaftarmenu().get(i);
					if (menuu.kategori.equals("Makanan")) {
						System.out.println((i+1) +". " +menuu.nama +" \t\t: " +kursIndonesia.format(menuu.harga));					
					}
				}
				System.out.println();
				System.out.println("========== Menu Minuman ==========");
				for (int i=0; i<menu.getDaftarmenu().size(); i++) {
					MenuItem menuu = menu.getDaftarmenu().get(i);
					if (menuu.kategori.equals("Minuman")) {
						System.out.println((i+1) +". " +menuu.nama +"\t\t: " +kursIndonesia.format(menuu.harga));					
					}
				}
				hapusMenu(input);
				break;
				
			case "4" :
				System.out.println("========== Menu Makanan ==========");
				for (int i=0; i<menu.getDaftarmenu().size(); i++) {
					MenuItem menuu = menu.getDaftarmenu().get(i);
					if (menuu.kategori.equals("Makanan")) {
						System.out.println((i+1) +". " +menuu.nama +" \t\t: " +kursIndonesia.format(menuu.harga));					
					}
				}
				System.out.println();
				System.out.println("========== Menu Minuman ==========");
				for (int i=0; i<menu.getDaftarmenu().size(); i++) {
					MenuItem menuu = menu.getDaftarmenu().get(i);
					if (menuu.kategori.equals("Minuman")) {
						System.out.println((i+1) +". " +menuu.nama +"\t\t: " +kursIndonesia.format(menuu.harga));					
					}
				} break;
				
			case "0" :
				return;
			
			default :
				System.out.println("Kode menu yang Anda pilih tidak valid!");
				break;
			}
		}
	}
	
	
	private static void tambahMenu(Scanner input) {
		System.out.println();
		System.out.print("Nama menu makanan/minuman baru : ");
		String namaBaru = input.nextLine();
		System.out.print("Harga menu makanan/minuman Baru : ");
		double hargaBaru = Double.parseDouble(input.nextLine());
		System.out.println("===== Kategori menu baru =====");
		System.out.println("[1] Makanan");
		System.out.println("[2] Minuman");
		System.out.print("Masukkan kode kategori menu baru (1/2) : ");
		String kategoriBaru = input.nextLine();
		
		MenuItem menubaru;
		switch (kategoriBaru) {
		case "1" :
			menubaru = new Makanan(namaBaru, hargaBaru);
			break;
			
		case "2" :
			menubaru = new Minuman(namaBaru, hargaBaru);
			break;
			
		default :
			System.out.println("Input yang Anda masukkan tidak sesuai!");
			return;
		}
		
		menu.isimenu(menubaru);
		System.out.println();
		System.out.println("Menu baru berhasil ditambahkan!");
	}
		
	private static void ubahHargamenu(Scanner input) {
		
		System.out.println();
		System.out.print("Masukkan nomor urut menu yang ingin diubah harganya : ");
		int menuIdx = Integer.parseInt(input.nextLine())-1;
		
		if(menuIdx>=0 && menuIdx<menu.getDaftarmenu().size()) {
			System.out.print("Harga baru : ");
			Double hargaBaru = Double.parseDouble(input.nextLine());
			menu.getDaftarmenu().get(menuIdx).harga = hargaBaru;
			System.out.println();
			System.out.println("Harga menu berhasil diubah!");
		} else {
			System.out.println("Kode menu yang Anda pilih tidak valid!");
		}
	}
	
	private static void hapusMenu(Scanner input) {
		System.out.println();
		System.out.print("Masukkan nomor urut menu yang ingin dihapus : ");
		int hapusIdx = Integer.parseInt(input.nextLine())-1;
		
		if(hapusIdx>=0 && hapusIdx<menu.getDaftarmenu().size()) {
			System.out.print("Apakah Anda yakin ingin menghapus menu ini? (y/t) : ");
			String konfirmasi = input.nextLine().toLowerCase();
			switch (konfirmasi) {
			case "y" :
				menu.getDaftarmenu().remove(hapusIdx);
				System.out.println();
				System.out.println("Menu berhasil dihapus!");
				break;
			case "t" :
				break;
			}
		} else {
			System.out.println("Kode menu yang Anda pilih tidak valid!");
		}
	}
	

	public static void main(String[] args) {
		inisialisasimenu();
		Scanner input = new Scanner (System.in);
		
		while(true) {
			tampilMenuutama();
			String choice = input.nextLine();
			
			switch(choice) {
			case "1" :
				pemesanan(input);
				break;
				
			case "2" :
				kelolamenu(input);
				break;
				
			case "0" :
				System.out.println("Terima kasih telah berkunjung ke restoran kami...");
				menu.inputMenukeFile();
				return;
				
			default :
				System.out.println("Kode yang Anda masukan tidak valid...");
				break;
			}
		}
	}

}

class Pesanan {
	private ArrayList<MenuItem> itemPesanan;
	private ArrayList<Integer> jmlPesanan;
	
	public Pesanan() {
		this.itemPesanan = new ArrayList<>();
		this.jmlPesanan = new ArrayList<>();
	}
	
	public void addPesanan (MenuItem isi, int jml) {
		itemPesanan.add(isi);
		jmlPesanan.add(jml);
	}
	
	public void struk() {
		DecimalFormat kursIndonesia = (DecimalFormat)
				DecimalFormat.getCurrencyInstance();
				DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
		
		formatRp.setCurrencySymbol("Rp. ");
		
			formatRp.setMonetaryDecimalSeparator(',');
				formatRp.setGroupingSeparator('.');
				kursIndonesia.setDecimalFormatSymbols(formatRp);
	
		double totalbayar = 0;
		double diskon = 0;
		double x = 0.1;
		double biayapelayanan = 20000;
		boolean freeMinuman = false;
		
		System.out.println();
		System.out.println("=================== STRUK PESANAN ANDA ===================");
		System.out.println();
		
		for (int i=0; i<itemPesanan.size(); i++) {
			MenuItem isi = itemPesanan.get(i);
			int jml = jmlPesanan.get(i);
			double totalPerItem = isi.harga*jml;
			
			System.out.println(isi.nama + "\t" +kursIndonesia.format(isi.harga) +"\tX " +jml +"\t= " +kursIndonesia.format(totalPerItem));
			
			totalbayar += totalPerItem;
		
			if (totalbayar > 50000 && !freeMinuman && isi.kategori.equals("Minuman")) {
				freeMinuman = true;
				System.out.println("Selamat Anda mendapatkan promo gratis 1 Es Teh!");
			}
		}
			
		if (totalbayar > 100000) {
				System.out.println();
				System.out.println("Total bayar awal : " +kursIndonesia.format(totalbayar));
			
				System.out.println();
				diskon = totalbayar*x;
				System.out.println("Selamat! Anda mendapatkan diskon 10%");
				System.out.println("Total diskon 10% = "+kursIndonesia.format(diskon));
				
				System.out.println();
				double pajak = totalbayar*x;
				System.out.println("Berlaku pajak 10%");
				System.out.println("Total biaya pajak : "+kursIndonesia.format(pajak));
				
				System.out.println();
				System.out.println("Berlaku biaya pelayanan : "+kursIndonesia.format(biayapelayanan));
				
				double totalakhir = (totalbayar+pajak+biayapelayanan)-diskon;
				System.out.println();
				System.out.println("Total bayar akhir = "+kursIndonesia.format(totalakhir));
	 	
				System.out.println();
				System.out.println("Terima kasih atas kunjungan Anda...");
				System.out.println();
		} else {
			System.out.println();
			System.out.println("Total bayar awal : " +kursIndonesia.format(totalbayar));
		
			System.out.println();
			double pajak = totalbayar*x;
			System.out.println("Berlaku pajak 10%");
			System.out.println("Total biaya pajak : "+kursIndonesia.format(pajak));
			
			System.out.println();
			System.out.println("Berlaku biaya pelayanan : "+kursIndonesia.format(biayapelayanan));
			
			double totalakhir = totalbayar+pajak+biayapelayanan;
			System.out.println();
			System.out.println("Total bayar akhir = "+kursIndonesia.format(totalakhir));
 	
			System.out.println();
			System.out.println("Terima kasih atas kunjungan Anda...");
			System.out.println();
	
		}

}
}




