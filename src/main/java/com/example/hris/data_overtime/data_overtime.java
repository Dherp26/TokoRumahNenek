package com.example.hris.data_overtime;
import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class data_overtime {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id_overtime;
    private String nip;
    private String bulan;
    private String tahun;
    private String jumlah_hari;
    private String biaya_overtime;
    private String keterangan;
    private String status;
	public data_overtime() {

	}


	public data_overtime(String id_overtime
			, String nip
			, String bulan
			, String tahun
			,String jumlah_hari
			,String biaya_overtime
			,String keterangan
			,String status
	) {
	
		this.id_overtime = id_overtime;
		this.nip = nip;
		this.bulan = bulan;
		this.tahun = tahun;
		this.jumlah_hari = jumlah_hari;
		this.biaya_overtime = biaya_overtime;
		this.keterangan = keterangan;
		this.status = status;
	}
	public String getId_overtime() {
		return id_overtime;
	}
	public void setId_overtime(String id_overtime) {
		this.id_overtime = id_overtime;
	}
	public String getNip() {
		return nip;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getBulan() {
		return bulan;
	}
	public void setBulan(String bulan) {
		this.bulan = bulan;
	}
	public String getTahun() {
		return tahun;
	}
	public void setTahun(String tahun) {
		this.tahun = tahun;
	}
	public String getJumlah_hari() {
		return jumlah_hari;
	}
	public void setJumlah_hari(String jumlah_hari) {
		this.jumlah_hari = jumlah_hari;
	}

	public String getBiaya_overtime() {
		return biaya_overtime;
	}
	public void setBiaya_overtime(String biaya_overtime) {
		this.biaya_overtime = biaya_overtime;
	}

	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
