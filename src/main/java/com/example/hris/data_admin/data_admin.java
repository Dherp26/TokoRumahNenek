package com.example.hris.data_admin;
import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class data_admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id_admin;
    private String nama;
    private String username;
    private String password;
	public data_admin() {

	}


	public data_admin(String id_admin, String nama, String username, String password) {
	
		this.id_admin = id_admin;
		this.nama = nama;
		this.username = username;
		this.password = password;
	}
	public String getId_admin() {
		return id_admin;
	}
	public void setId_admin(String id_admin) {
		this.id_admin = id_admin;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
