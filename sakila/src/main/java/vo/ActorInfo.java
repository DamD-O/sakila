package vo;

public class ActorInfo {
	//필드 생성
	private int avtorId;
	private String firstName;
	private String lastName;
	private String filmInfo;
	
	//최상위클래스 공부 필요.
	@Override
	public String toString() {
		return "ActorInfo [avtorId=" + avtorId + ", firstName=" + firstName + ", lastName=" + lastName + ", filmInfo="
				+ filmInfo + "]";
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
