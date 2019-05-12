package student_data;

public class Student {
	
	private int id;
	private String lastName, firstName, street, postcode, postOffice;
	
	public Student() {}

	public Student(int id, String lastName, String firstName, String street, String postcode, String postOffice) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.street = street;
		this.postcode = postcode;
		this.postOffice = postOffice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPostOffice() {
		return postOffice;
	}

	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", street=" + street
				+ ", postcode=" + postcode + ", postOffice=" + postOffice + "]";
	}
	
	
	
	
	

}
