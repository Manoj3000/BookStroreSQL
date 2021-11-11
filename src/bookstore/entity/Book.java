package bookstore.entity;

public class Book {	
	private String id;
	private String bookId;
	private String bookName;
	private String bookAuthor;
	private String bookType;
	private int quantity;
	private String bookPrice;
	private String bookDesc;

	public Book() {
	}

	public Book(String id, String bookId, String bookName, String bookAuthor, String bookType, int quantity,
			String bookPrice, String bookDesc) {
		this.id = id;
		this.bookId = bookId;
		this.bookName = bookName;
		this.bookAuthor = bookAuthor;
		this.bookType = bookType;
		this.quantity = quantity;
		this.bookPrice = bookPrice;
		this.bookDesc = bookDesc;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", bookId=" + bookId + ", bookName=" + bookName + ", bookAuthor=" + bookAuthor
				+ ", bookType=" + bookType + ", quantity=" + quantity + ", bookPrice=" + bookPrice + ", bookDesc="
				+ bookDesc + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
}
