package teamA.travel.sieuthi;

import java.io.Serializable;

public class sieuthi implements Serializable {
		private int id;
		private String Name;
		private String Diachi;
		private String Tinh;
		private String Sdt;
		private String Time;
		private String Mota;
		private String Hinh;
		
		public sieuthi(int id, String name, String diachi,String tinh, String sdt, String time, String mota,String hinh){
			this.id = id;
			Name = name;
			Diachi = diachi;
			Sdt = sdt;
			Time = time;
			Hinh = hinh;
			Tinh = tinh;
			Mota = mota;

			
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

		public String getTime() {
			return Time;
		}

		public void setTime(String time) {
			Time = time;
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
		
		
		
}
