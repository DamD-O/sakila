package vo;

public class SalesByStore {
	//필드생성
	private String store;
	private String manager;
	private double totalSales;
	//객체저장
	@Override
	public String toString() {
		return "SalesByStore [store=" + store + ", manager=" + manager + ", totalSales=" + totalSales + "]";
	}
	//게터,세터 생성
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public double getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}
	
	
	
}
