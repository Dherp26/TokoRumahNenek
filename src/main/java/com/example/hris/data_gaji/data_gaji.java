package com.example.hris.data_gaji;
import org.hibernate.HibernateException;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class data_gaji {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id_gaji;
    private String nip;
    private String bulan;
    private String tahun;
    private String total_gaji_pokok;
    private String total_gaji_overtime;
    private String total_potongan_gaji;
    private String total_gaji;
	public data_gaji() {

	}


	public data_gaji(String id_gaji
			, String nip
			, String bulan
			, String tahun
			,String total_gaji_pokok
			,String total_gaji_overtime
			,String total_potongan_gaji
			,String total_gaji
	) {
	
		this.id_gaji = id_gaji;
		this.nip = nip;
		this.bulan = bulan;
		this.tahun = tahun;
		this.total_gaji_pokok = total_gaji_pokok;
		this.total_gaji_overtime = total_gaji_overtime;
		this.total_potongan_gaji = total_potongan_gaji;
		this.total_gaji = total_gaji;
	}
	public String getId_gaji() {
		return id_gaji;
	}
	public void setId_gaji(String id_gaji) {
		this.id_gaji = id_gaji;
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
	public String getTotal_gaji_pokok() {
		return total_gaji_pokok;
	}
	public void setTotal_gaji_pokok(String total_gaji_pokok) {
		this.total_gaji_pokok = total_gaji_pokok;
	}

	public String getTotal_gaji_overtime() {
		return total_gaji_overtime;
	}
	public void setTotal_gaji_overtime(String total_gaji_overtime) {
		this.total_gaji_overtime = total_gaji_overtime;
	}

	public String getTotal_potongan_gaji() {
		return total_potongan_gaji;
	}
	public void setTotal_potongan_gaji(String total_potongan_gaji) {
		this.total_potongan_gaji = total_potongan_gaji;
	}

	public String getTotal_gaji() {
		return total_gaji;
	}
	public void setTotal_gaji(String total_gaji) {
		this.total_gaji = total_gaji;
	}

}
