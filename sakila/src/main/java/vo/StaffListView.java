package vo;

public class StaffListView {
	private int staffId;
	private String name;
	private String address;
	private String postalCode;
	private String phone;
	private String city;
	private String country;
	private int storeId;
	//객체정보 불러오기
	@Override
	public String toString() {
		return "StaffListView [staffId=" + staffId + ", name=" + name + ", address=" + address + ", postalCode="
				+ postalCode + ", phone=" + phone + ", city=" + city + ", country=" + country + ", storeId=" + storeId
				+ "]";
	}
	//getter,setter
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	
}
