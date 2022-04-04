package vo;

public class CustomerList {
	private int customerId;
	private String name;
	private String address;
	private String postalCode;
	private String phone;
	private String city;
	private String country;
	private String notes;
	private int storeId;
	
	@Override
	public String toString() {
		return "CustomerList [customerId=" + customerId + ", name=" + name + ", address=" + address + ", postalCode="
				+ postalCode + ", phone=" + phone + ", city=" + city + ", country=" + country + ", notes=" + notes
				+ ", storeId=" + storeId + "]";
	}
	
	//setter /getter
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	
	
	
}
