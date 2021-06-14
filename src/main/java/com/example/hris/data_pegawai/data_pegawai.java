package com.example.hris.data_pegawai;
import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class data_pegawai {
	@Id
	private String id_pegawai;
    private String nip;
    private String nama;
    private String alamat;
    private String no_telepon;
    private String jabatan;
    private String total_gaji_pokok;
    private String potongan_gaji_alfa;
    private String username;
    private String password;
	public data_pegawai() {

	}


	public data_pegawai(String id_pegawai
			, String nip
			, String nama
			, String alamat
			,String no_telepon
			,String jabatan
			,String total_gaji_pokok
			,String potongan_gaji_alfa
			,String username
			,String password
	) {
	
		this.id_pegawai = id_pegawai;
		this.nip = nip;
		this.nama = nama;
		this.alamat = alamat;
		this.no_telepon = no_telepon;
		this.jabatan = jabatan;
		this.total_gaji_pokok = total_gaji_pokok;
		this.potongan_gaji_alfa = potongan_gaji_alfa;
		this.username = username;
		this.password = password;
	}
	public String getId_pegawai() {
		return id_pegawai;
	}
	public void setId_pegawai(String id_pegawai) {
		this.id_pegawai = id_pegawai;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getNo_telepon() {
		return no_telepon;
	}
	public void setNo_telepon(String no_telepon) {
		this.no_telepon = no_telepon;
	}

	public String getJabatan() {
		return jabatan;
	}
	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public String getTotal_gaji_pokok() {
		return total_gaji_pokok;
	}
	public void setTotal_gaji_pokok(String total_gaji_pokok) {
		this.total_gaji_pokok = total_gaji_pokok;
	}

	public String getPotongan_gaji_alfa() {
		return potongan_gaji_alfa;
	}
	public void setPotongan_gaji_alfa(String potongan_gaji_alfa) {
		this.potongan_gaji_alfa = potongan_gaji_alfa;
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