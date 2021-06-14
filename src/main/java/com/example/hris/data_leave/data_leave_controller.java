package com.example.hris.data_leave;


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
public class data_leave_controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private data_leave_service service;

    //TAMPIL
    @GetMapping("/data_leave/tampil")
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
            q = " and data_leave.nip='" + nip +"'";

        }


        List<data_leave> data_leave = jdbcTemplate.query(
                "select data_pegawai.nip, data_pegawai.nama , data_leave.* from data_pegawai,data_leave where data_leave.nip = data_pegawai.nip" + q,
                (rs, rowNum) -> new data_leave(
                        rs.getString("id_leave")
                        , rs.getString("data_pegawai.nip") + " ( " + rs.getString("data_pegawai.nama") + " )"
                        , rs.getString("tanggal")
                        , rs.getString("keterangan")
                        , rs.getString("status")
                )
        );

        model.addAttribute("data_leave", data_leave);
        return "data_leave_tampil";
    }

    //TAMBAH
    @GetMapping("/data_leave/tambah")
    public String tambah(Model model,@CookieValue(name = "kodene", defaultValue = "logout") String nip,@CookieValue(name = "sopo", defaultValue = "logout") String sopo) {
        Date id_leave = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyymdhhmms");
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("id_leave", "LVE"+ft.format(id_leave));

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
                "SELECT * from data_pegawai "+ q +"",
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


        return "data_leave_tambah";
    }

    //PROSES SIMPAN
    @RequestMapping(value = "/data_leave/proses_simpan", method = RequestMethod.POST)
    public String proses_simpan(@ModelAttribute("data_leave") data_leave data_leave) {
        jdbcTemplate.update(
                "INSERT INTO data_leave (" +
                        "id_leave" +
                        ", nip" +
                        ", tanggal" +
                        ", keterangan" +
                        ", status" +
                        ") " +
                        "VALUES (?,?,?,?,?)",
                data_leave.getId_leave()
                , data_leave.getNip()
                , data_leave.getTanggal()
                , data_leave.getKeterangan()
                , data_leave.getStatus()
        );
        return "redirect:/data_leave/tampil";
    }

    //EDIT
    @RequestMapping("/data_leave/edit/{id_leave}")
    public String edit(@PathVariable(name = "id_leave") String id_leave,Model model) {

        List<data_leave> data_leave = jdbcTemplate.query(
                "SELECT * from data_leave WHERE id_leave = ?", new Object[]{id_leave},
                (rs, rowNum) -> new data_leave(
                        rs.getString("id_leave")
                        , rs.getString("nip")
                        , rs.getString("tanggal")
                        ,  rs.getString("keterangan")
                        ,  rs.getString("status")
                )
        );
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("data_leave", data_leave.get(0));

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

        return "data_leave_edit";

    }

    //PROSES UPDATE
        @RequestMapping(value = "/data_leave/proses_update", method = RequestMethod.POST)
        public String proses_update(@ModelAttribute("data_leave") data_leave data_leave) {

            jdbcTemplate.update(
                        "UPDATE data_leave SET " +
                            "nip = ?" +
                            ",tanggal= ?" +
                            ",keterangan= ?" +
                            ",status= ?" +
                            "WHERE id_leave = ?",
                    data_leave.getNip()
                    , data_leave.getTanggal()
                    , data_leave.getKeterangan()
                    , data_leave.getStatus()
                    , data_leave.getId_leave()

            );
        return "redirect:/data_leave/tampil";
    }


    //HAPUS
    @RequestMapping("/data_leave/delete/{id_leave}")
    public String proses_hapus(@PathVariable(name = "id_leave") String id_leave) {

        jdbcTemplate.update("DELETE FROM data_leave WHERE id_leave = ?", id_leave);
        return "redirect:/data_leave/tampil";
    }
}