package teamA.travel.khachsan;

import java.io.Serializable;

public class Khachsan implements Serializable {
		private int id;
		private String Name;
		private String Diachi;
		private String Sdt;
		private String Tinh;
		private String Fax;
		private String Mail;
		private String Web;
		private String Mota;
		private String Hinh;
		private String Hinh1;
		private String Hinh2;
		private String Hinh3;
		
		public Khachsan(int id, String name, String diachi, String sdt, String tinh, String fax, String mail, String web, String mota,String hinh,String hinh1,String hinh2,String hinh3){
			this.id = id;
			Name = name;
			Diachi = diachi;
			Hinh = hinh;
			Mota = mota;
			Hinh1 = hinh1;
			Hinh2 = hinh2;
			Hinh3 = hinh3;
			Fax = fax;
			Web = web;
			Mail = mail;
			Sdt = sdt;
			Tinh = tinh;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return Name;
		}

		public void setName(String name) {
			Name = name;
		}

		public String getDiachi() {
			return Diachi;
		}

		public void setDiachi(String diachi) {
			Diachi = diachi;
		}

		public String getFax() {
			return Fax;
		}

		public void setFax(String fax) {
			Fax = fax;
		}

		public String getMail() {
			return Mail;
		}

		public void setMail(String mail) {
			Mail = mail;
		}

		public String getWeb() {
			return Web;
		}

		public void setWeb(String web) {
			Web = web;
		}

		public String getMota() {
			return Mota;
		}

		public void setMota(String mota) {
			Mota = mota;
		}

		public String getHinh() {
			return Hinh;
		}

		public void setHinh(String hinh) {
			Hinh = hinh;
		}

		public String getHinh1() {
			return Hinh1;
		}

		public void setHinh1(String hinh1) {
			Hinh1 = hinh1;
		}

		public String getHinh2() {
			return Hinh2;
		}

		public void setHinh2(String hinh2) {
			Hinh2 = hinh2;
		}

		public String getHinh3() {
			return Hinh3;
		}

		public void setHinh3(String hinh3) {
			Hinh3 = hinh3;
		}

		public String getSdt() {
			return Sdt;
		}

		public void setSdt(String sdt) {
			Sdt = sdt;
		}

		public String getTinh() {
			return Tinh;
		}

		public void setTinh(String tinh) {
			Tinh = tinh;
		}
		
		
		
}