package bookstore.common;

public enum ErrorMessages {

	DATA_NOT_FOUND(" Data Not Found"),
	FIELD_IS_EMPTY(" Field Is Empty"),
	DATA_NOT_MATCH(" Invalid Data"),
	INVALID_DATA("Please Enter Valid "),
	USER_NOT_FOUND("User Not Found!");
	private String constant;
	
	private ErrorMessages(String constant) {
		this.constant = constant;
	}
	
	public String getConstant() {
		return constant;
	}
}
