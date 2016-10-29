package Models;

public class Telephone {

	private int id;
	private String phoneKind;
	private String number;
	private int idContact;

	public Telephone(String phoneKind, String number) {
		super();
		this.phoneKind = phoneKind;
		this.number = number;
	}
	
	public Telephone(String phoneKind, String number, int idContact) {
		super();
		this.phoneKind = phoneKind;
		this.number = number;
		this.idContact = idContact;
	}
	
	public Telephone(int id, String phoneKind, String number, int idContact) {
		super();
		this.id = id;
		this.phoneKind = phoneKind;
		this.number = number;
		this.idContact = idContact;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoneKind() {
		return phoneKind;
	}
	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public int getIdContact() {
		return idContact;
	}

	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}
}
