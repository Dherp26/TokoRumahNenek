package com.example.hris.data_absensi;
import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class data_absensi {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id_absensi;
    private String nip;
    private String bulan;
    private String tahun;
    private String jumlah_hadir;
    private String jumlah_sakit;
    private String jumlah_izin;
    private String jumlah_alfa;
	public data_absensi() {

	}


	public data_absensi(String id_absensi
			, String nip
			, String bulan
			, String tahun
			,String jumlah_hadir
			,String jumlah_sakit
			,String jumlah_izin
			,String jumlah_alfa
	) {
	
		this.id_absensi = id_absensi;
		this.nip = nip;
		this.bulan = bulan;
		this.tahun = tahun;
		this.jumlah_hadir = jumlah_hadir;
		this.jumlah_sakit = jumlah_sakit;
		this.jumlah_izin = jumlah_izin;
		this.jumlah_alfa = jumlah_alfa;
	}
	public String getId_absensi() {
		return id_absensi;
	}
	public void setId_absensi(String id_absensi) {
		this.id_absensi = id_absensi;
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
	public String getJumlah_hadir() {
		return jumlah_hadir;
	}
	public void setJumlah_hadir(String jumlah_hadir) {
		this.jumlah_hadir = jumlah_hadir;
	}

	public String getJumlah_sakit() {
		return jumlah_sakit;
	}
	public void setJumlah_sakit(String jumlah_sakit) {
		this.jumlah_sakit = jumlah_sakit;
	}

	public String getJumlah_izin() {
		return jumlah_izin;
	}
	public void setJumlah_izin(String jumlah_izin) {
		this.jumlah_izin = jumlah_izin;
	}

	public String getJumlah_alfa() {
		return jumlah_alfa;
	}
	public void setJumlah_alfa(String jumlah_alfa) {
		this.jumlah_alfa = jumlah_alfa;
	}

}
