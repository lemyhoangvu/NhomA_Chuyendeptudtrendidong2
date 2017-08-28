package teamA.travel.lehoi;

import java.io.Serializable;

public class Lehoi implements Serializable {
		private int id;
		private String Name;
		private String Diachi;
		private String Mota;
		private String Hinh;
		private String Hinh1;
		private String Hinh2;
		private String Hinh3;
		
		public Lehoi(int id, String name, String diachi, String mota,String hinh,String hinh1,String hinh2,String hinh3){
			this.id = id;
			Name = name;
			Diachi = diachi;
			Hinh = hinh;
			Mota = mota;
			Hinh1 = hinh1;
			Hinh2 = hinh2;
			Hinh3 = hinh3;
			
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
		
		public String getHinh() {
			return Hinh;
		}

		public void setHinh(String hinh) {
			Hinh = hinh;
		}

		public String getMota() {
			return Mota;
		}

		public void setMota(String mota) {
			Mota = mota;
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
		
		
		
}
