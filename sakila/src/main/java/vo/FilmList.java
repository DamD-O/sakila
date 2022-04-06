package vo;

public class FilmList {
	private int filmId;
	private String title;
	private String description;
	private String categoryName;
	private double price;
	private int length;
	private String rating;
	private String actorName;
	@Override
	public String toString() {
		return "FilmList [filmId=" + filmId + ", title=" + title + ", description=" + description + ", categoryName="
				+ categoryName + ", price=" + price + ", length=" + length + ", rating=" + rating + ", actorName="
				+ actorName + "]";
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getActorName() {
		return actorName;
	}
	public void setActorName(String actorName) {
		this.actorName = actorName;
	}
	
	
}
