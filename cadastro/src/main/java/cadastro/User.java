package cadastro;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USERID")
	private long id;

	private String name;
	private String email;
	private String password;
	@JsonIgnore
	private Date created;
	@JsonIgnore
	private Date modified;
	@JsonIgnore
	private Date last_login;
	@JsonIgnore
	private String token;
	// User
	// @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	// @OneToMany(targetEntity=Phone.class, mappedBy="user",
	// fetch=FetchType.EAGER)
	private List<Phone> phones;

	public User(String name, String email, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	// @OneToMany(orphanRemoval = true)
	// @JoinColumn(name = "userId")
	public List<Phone> getPhones() {
		return phones;
	}

	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Usuario {" + "id=" + id + ", created='" + name + '\'' + ", modified='" + modified + '\''
				+ ", last_login='" + last_login + '\'' + ", token='" + token + '}';
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
