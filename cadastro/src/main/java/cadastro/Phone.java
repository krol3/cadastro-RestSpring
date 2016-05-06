package cadastro;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String ddd;
	private String number;

	@ManyToOne
	@JoinColumn(name = "USERID")
	private User user;

	public Phone(String ddd, String number) {
		this.ddd = ddd;
		this.number = number;
	}

	public Phone() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	// @ManyToOne(cascade = CascadeType.ALL, optional = false)
	// @JoinColumn(name = "USERID")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
