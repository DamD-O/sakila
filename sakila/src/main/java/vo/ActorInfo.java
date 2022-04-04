package vo;

public class ActorInfo {
	//필드 생성
	private int avtorId;
	private String firstName;
	private String lastName;
	private String filmInfo;
	
	//특정메소드생성?
	@Override
	public String toString(){
		return this.avtorId + this.firstName + this.lastName +this.filmInfo;
	}
	
	
	//setter & getter 생성
	public int getAvtorId() {
		return avtorId;
	}
	public void setAvtorId(int avtorId) {
		this.avtorId = avtorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFilmInfo() {
		return filmInfo;
	}
	public void setFilmInfo(String filmInfo) {
		this.filmInfo = filmInfo;
	}
	
	
}
