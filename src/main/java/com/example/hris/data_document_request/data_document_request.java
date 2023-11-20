package com.example.hris.data_document_request;
import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class data_document_request {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id_document_request;
    private String nip;
    private String tanggal;
    private String keterangan;
    private String status;
	public data_document_request() {

	}


	public data_document_request(String id_document_request, String nip, String tanggal, String keterangan,String status) {
	
		this.id_document_request = id_document_request;
		this.nip = nip;
		this.tanggal = tanggal;
		this.keterangan = keterangan;
		this.status = status;
	}
	public String getId_document_request() {
		return id_document_request;
	}
	public void setid_document_request(String id_document_request) {
		this.id_document_request = id_document_request;
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
