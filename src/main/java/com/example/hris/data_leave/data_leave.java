package com.example.hris.data_leave;
import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class data_leave {
	@Id
	private String id_leave;
    private String nip;
    private String tanggal;
    private String keterangan;
    private String status;
	public data_leave() {

	}


	public data_leave(String id_leave, String nip, String tanggal, String keterangan,String status) {
	
		this.id_leave = id_leave;
		this.nip = nip;
		this.tanggal = tanggal;
		this.keterangan = keterangan;
		this.status = status;
	}
	public String getId_leave() {
		return id_leave;
	}
	public void setid_leave(String id_leave) {
		this.id_leave = id_leave;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getTanggal() {
		return tanggal;
	}
	public void settanggal(String tanggal) {
		this.tanggal = tanggal;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setketerangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public String getStatus() {
		return status;
	}
	public void setstatus(String status) {
		this.status = status;
	}

}
