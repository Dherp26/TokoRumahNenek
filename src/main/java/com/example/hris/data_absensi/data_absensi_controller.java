package com.example.hris.data_absensi;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.hris.data_pegawai.data_pegawai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class data_absensi_controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private data_absensi_service service;

    //TAMPIL
    @GetMapping("/data_absensi/tampil")
    public String tampil(Model model,@CookieValue(name = "kodene", defaultValue = "logout") String nip,@CookieValue(name = "sopo", defaultValue = "logout") String sopo) {
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("sopo",  "Welcome " + sopo);

        String q;
        if (sopo.equals("Administrator"))
        {
            q = "";
        }
        else
        {
            q = "and data_absensi.nip='" + nip +"'";

        }

        List<data_absensi> data_absensi = jdbcTemplate.query(
                "select data_pegawai.nip, data_pegawai.nama , data_absensi.* from data_pegawai,data_absensi where data_absensi.nip = data_pegawai.nip " + q,
                (rs, rowNum) -> new data_absensi(
                        rs.getString("id_absensi")
                        , rs.getString("data_pegawai.nip") + " ( " + rs.getString("data_pegawai.nama") + " )"
                        , rs.getString("bulan")
                        , rs.getString("tahun")
                        , rs.getString("jumlah_hadir") + " Hari"
                        , rs.getString("jumlah_sakit") + " Hari"
                        , rs.getString("jumlah_izin") + " Hari"
                        , rs.getString("jumlah_alfa") + " Hari"
                )
        );

        model.addAttribute("data_absensi", data_absensi);
        return "data_absensi_tampil";
    }

    //TAMBAH
    @GetMapping("/data_absensi/tambah")
    public String tambah(Model model) {
        Date id_absensi = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyymdhhmms");
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("id_absensi", "ABS"+ft.format(id_absensi));


        List<data_pegawai> data_pegawai = jdbcTemplate.query(
                "SELECT * from data_pegawai order by id_pegawai asc",
                (rs, rowNum) -> new data_pegawai(
                        rs.getString("id_pegawai")
                        , rs.getString("nip")
                        , rs.getString("nip") + " ( " + rs.getString("nama") + " )"
                        , rs.getString("alamat")
                        , rs.getString("no_telepon")
                        , rs.getString("jabatan")
                        , rs.getString("total_gaji_pokok")
                        , rs.getString("potongan_gaji_alfa")
                        , rs.getString("username")
                        , rs.getString("password")
                )
        );

        model.addAttribute("data_pegawai", data_pegawai);


        return "data_absensi_tambah";
    }

    //PROSES SIMPAN
    @RequestMapping(value = "/data_absensi/proses_simpan", method = RequestMethod.POST)
    public String proses_simpan(@ModelAttribute("data_absensi") data_absensi data_absensi) {
        jdbcTemplate.update(
                "INSERT INTO data_absensi (" +
                        "id_absensi" +
                        ", nip" +
                        ", bulan" +
                        ", tahun" +
                        ", jumlah_hadir" +
                        ", jumlah_sakit" +
                        ", jumlah_izin" +
                        ", jumlah_alfa" +
                        ") " +
                        "VALUES (?,?,?,?,?,?,?,?)",
                data_absensi.getId_absensi()
                , data_absensi.getNip()
                , data_absensi.getBulan()
                , data_absensi.getTahun()
                , data_absensi.getJumlah_hadir()
                , data_absensi.getJumlah_sakit()
                , data_absensi.getJumlah_izin()
                , data_absensi.getJumlah_alfa()
        );
        return "redirect:/data_absensi/tampil";
    }

    //EDIT
    @RequestMapping("/data_absensi/edit/{id_absensi}")
    public String edit(@PathVariable(name = "id_absensi") String id_absensi,Model model) {

        List<data_absensi> data_absensi = jdbcTemplate.query(
                "SELECT * from data_absensi WHERE id_absensi = ?", new Object[]{id_absensi},
                (rs, rowNum) -> new data_absensi(
                        rs.getString("id_absensi")
                        , rs.getString("nip")
                        , rs.getString("bulan")
                        ,  rs.getString("tahun")
                        ,  rs.getString("jumlah_hadir")
                        ,  rs.getString("jumlah_sakit")
                        ,  rs.getString("jumlah_izin")
                        ,  rs.getString("jumlah_alfa")
                )
        );
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("data_absensi", data_absensi.get(0));

        List<data_pegawai> data_pegawai = jdbcTemplate.query(
                "SELECT * from data_pegawai order by id_pegawai asc",
                (rs, rowNum) -> new data_pegawai(
                        rs.getString("id_pegawai")
                        , rs.getString("nip")
                        , rs.getString("nip") + " ( " + rs.getString("nama") + " )"
                        , rs.getString("alamat")
                        , rs.getString("no_telepon")
                        , rs.getString("jabatan")
                        , rs.getString("total_gaji_pokok")
                        , rs.getString("potongan_gaji_alfa")
                        , rs.getString("username")
                        , rs.getString("password")
                )
        );

        model.addAttribute("data_pegawai", data_pegawai);

        return "data_absensi_edit";

    }

    //PROSES UPDATE
        @RequestMapping(value = "/data_absensi/proses_update", method = RequestMethod.POST)
        public String proses_update(@ModelAttribute("data_absensi") data_absensi data_absensi) {

            jdbcTemplate.update(
                        "UPDATE data_absensi SET " +
                            "nip = ?" +
                            ",bulan= ?" +
                            ",tahun= ?" +
                            ",jumlah_hadir= ?" +
                            ",jumlah_sakit= ?" +
                            ",jumlah_izin= ?" +
                            ",jumlah_alfa= ?" +
                            "WHERE id_absensi = ?",
                    data_absensi.getNip()
                    , data_absensi.getBulan()
                    , data_absensi.getTahun()
                    , data_absensi.getJumlah_hadir()
                    , data_absensi.getJumlah_sakit()
                    , data_absensi.getJumlah_izin()
                    , data_absensi.getJumlah_alfa()
                    , data_absensi.getId_absensi()

            );
        return "redirect:/data_absensi/tampil";
    }


    //HAPUS
    @RequestMapping("/data_absensi/delete/{id_absensi}")
    public String proses_hapus(@PathVariable(name = "id_absensi") String id_absensi) {

        jdbcTemplate.update("DELETE FROM data_absensi WHERE id_absensi = ?", id_absensi);
        return "redirect:/data_absensi/tampil";
    }
}