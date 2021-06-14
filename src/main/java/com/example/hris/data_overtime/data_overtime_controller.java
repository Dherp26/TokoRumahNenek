package com.example.hris.data_overtime;


import java.text.*;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.hris.data_pegawai.data_pegawai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.hris.config.config.rupiah;

@Controller
public class data_overtime_controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private data_overtime_service service;



    //TAMPIL
    @GetMapping("/data_overtime/tampil")
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
            q = " and data_overtime.nip='" + nip +"'";

        }


        List<data_overtime> data_overtime = jdbcTemplate.query(
                "select data_pegawai.nip, data_pegawai.nama , data_overtime.* from data_pegawai,data_overtime where data_overtime.nip = data_pegawai.nip" + q,
                (rs, rowNum) -> new data_overtime(
                        rs.getString("id_overtime")
                        , rs.getString("data_pegawai.nip") + " ( " + rs.getString("data_pegawai.nama") + " )"
                        , rs.getString("bulan")
                        , rs.getString("tahun")
                        , rs.getString("jumlah_hari") + " hari"
                        , rupiah(Long.parseLong(rs.getString("biaya_overtime")))
                        , rs.getString("keterangan")
                        , rs.getString("status")
                )
        );

        model.addAttribute("data_overtime", data_overtime);
        return "data_overtime_tampil";
    }

    //TAMBAH
    @GetMapping("/data_overtime/tambah")
    public String tambah(Model model,@CookieValue(name = "kodene", defaultValue = "logout") String nip,@CookieValue(name = "sopo", defaultValue = "logout") String sopo) {
        Date id_overtime = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyymdhhmms");
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("id_overtime", "OVR"+ft.format(id_overtime));

        String q;
        if (sopo.equals("Administrator"))
        {
            q = "";
        }
        else
        {
            q = "where nip='" + nip +"'";

        }

        List<data_pegawai> data_pegawai = jdbcTemplate.query(
                "SELECT * from data_pegawai "+ q +" order by id_pegawai asc",
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




        return "data_overtime_tambah";
    }

    //PROSES SIMPAN
    @RequestMapping(value = "/data_overtime/proses_simpan", method = RequestMethod.POST)
    public String proses_simpan(@ModelAttribute("data_overtime") data_overtime data_overtime) {
        jdbcTemplate.update(
                "INSERT INTO data_overtime (" +
                        "id_overtime" +
                        ", nip" +
                        ", bulan" +
                        ", tahun" +
                        ", jumlah_hari" +
                        ", biaya_overtime" +
                        ", keterangan" +
                        ", status" +
                        ") " +
                        "VALUES (?,?,?,?,?,?,?,?)",
                data_overtime.getId_overtime()
                , data_overtime.getNip()
                , data_overtime.getBulan()
                , data_overtime.getTahun()
                , data_overtime.getJumlah_hari()
                , data_overtime.getBiaya_overtime()
                , data_overtime.getKeterangan()
                , data_overtime.getStatus()
        );
        return "redirect:/data_overtime/tampil";
    }

    //EDIT
    @RequestMapping("/data_overtime/edit/{id_overtime}")
    public String edit(@PathVariable(name = "id_overtime") String id_overtime,Model model) {

        List<data_overtime> data_overtime = jdbcTemplate.query(
                "SELECT * from data_overtime WHERE id_overtime = ?", new Object[]{id_overtime},
                (rs, rowNum) -> new data_overtime(
                        rs.getString("id_overtime")
                        , rs.getString("nip")
                        , rs.getString("bulan")
                        ,  rs.getString("tahun")
                        ,  rs.getString("jumlah_hari")
                        ,  rs.getString("biaya_overtime")
                        ,  rs.getString("keterangan")
                        ,  rs.getString("status")
                )
        );
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("data_overtime", data_overtime.get(0));

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

        return "data_overtime_edit";

    }

    //PROSES UPDATE
        @RequestMapping(value = "/data_overtime/proses_update", method = RequestMethod.POST)
        public String proses_update(@ModelAttribute("data_overtime") data_overtime data_overtime) {

            jdbcTemplate.update(
                        "UPDATE data_overtime SET " +
                            "nip = ?" +
                            ",bulan= ?" +
                            ",tahun= ?" +
                            ",jumlah_hari= ?" +
                            ",biaya_overtime= ?" +
                            ",keterangan= ?" +
                            ",status= ?" +
                            "WHERE id_overtime = ?",
                    data_overtime.getNip()
                    , data_overtime.getBulan()
                    , data_overtime.getTahun()
                    , data_overtime.getJumlah_hari()
                    , data_overtime.getBiaya_overtime()
                    , data_overtime.getKeterangan()
                    , data_overtime.getStatus()
                    , data_overtime.getId_overtime()

            );
        return "redirect:/data_overtime/tampil";
    }


    //HAPUS
    @RequestMapping("/data_overtime/delete/{id_overtime}")
    public String proses_hapus(@PathVariable(name = "id_overtime") String id_overtime) {

        jdbcTemplate.update("DELETE FROM data_overtime WHERE id_overtime = ?", id_overtime);
        return "redirect:/data_overtime/tampil";
    }
}