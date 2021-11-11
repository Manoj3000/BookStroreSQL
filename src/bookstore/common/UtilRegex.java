package bookstore.common;

public class UtilRegex {
	public static final String ID = "^[0-9]{3}$";
	public static final String NAME = "^[A-Z]{1}[A-Za-z]{1,16}$";
	public static final String MOBILE = "^[6-9]{1}[0-9]{9}$";
	public static final String EMAIL = "^[a-z0-9.]+@[a-z0-9.]+\\.[a-z]{2,6}$";
	public static final String ADDRESS = "^[A-Z]{1}[A-Za-z]{1,20}$";
	public static final String PRICE = "^[1-9]{1}[0-9]+$";
	public static final String BOOKID = "^[B]{1}[0-9]{3}$";
	public static final String USERID = "^[U]{1}[0-9]{3}$";
	public static final String MANAGEID = "^[M]{1}[0-9]{3}$";
}
