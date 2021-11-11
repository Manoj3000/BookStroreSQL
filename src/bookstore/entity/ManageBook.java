package bookstore.entity;

import java.time.LocalDate;

public class ManageBook {
	private String id;
	private String manageId;
	private String userId;
	private String bookId;
	private LocalDate dateOfIssue;
	private LocalDate dateOfReturn;
	private String quantity;
	private String totalPrice;

	public ManageBook() {
	}
	
	public ManageBook(String id, String manageId, String userId, String bookId, LocalDate dateOfIssue, String quantity,
			String totalPrice, LocalDate dateOfReturn) {
		super();
		this.id = id;
		this.manageId = manageId;
		this.userId = userId;
		this.bookId = bookId;
		this.dateOfIssue = dateOfIssue;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.dateOfReturn = dateOfReturn;
	}

	@Override
	public String toString() {
		return "ManageBook [id=" + id + ", manageId=" + manageId + ", userId=" + userId + ", bookId=" + bookId
				+ ", dateOfIssue=" + dateOfIssue + ", quantity=" + quantity + ", totalPrice=" + totalPrice
				+ ", dateOfReturn=" + dateOfReturn + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getManageId() {
		return manageId;
	}

	public void setManageId(String manageId) {
		this.manageId = manageId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public LocalDate getDateOfIssue() {
		return dateOfIssue;
	}

	public void setDateOfIssue(LocalDate dateOfIssue) {
		this.dateOfIssue = dateOfIssue;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDate getDateOfReturn() {
		return dateOfReturn;
	}

	public void setDateOfReturn(LocalDate dateOfReturn) {
		this.dateOfReturn = dateOfReturn;
	}

}
