package bookstore.entity;

public class User {
	private String id;
	private String userId;
	private String name;
	private String mobile;
	private String email;
	private String address;

	public User() {
	}
	
	public User(String id, String userId, String name, String mobile, String email, String address) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", name=" + name + ", mobile=" + mobile + ", email=" + email
				+ ", address=" + address + "]";
	}
}
