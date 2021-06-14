package com.example.hris.data_document_request;


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
public class data_document_request_controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private data_document_request_service service;

    //TAMPIL
    @GetMapping("/data_document_request/tampil")
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
            q = " and data_document_request.nip='" + nip +"'";

        }


        List<data_document_request> data_document_request = jdbcTemplate.query(
                "select data_pegawai.nip, data_pegawai.nama , data_document_request.* from data_pegawai,data_document_request where data_document_request.nip = data_pegawai.nip" + q,
                (rs, rowNum) -> new data_document_request(
                        rs.getString("id_document_request")
                        , rs.getString("data_pegawai.nip") + " ( " + rs.getString("data_pegawai.nama") + " )"
                        , rs.getString("tanggal")
                        , rs.getString("keterangan")
                        , rs.getString("status")
                )
        );

        model.addAttribute("data_document_request", data_document_request);
        return "data_document_request_tampil";
    }

    //TAMBAH
    @GetMapping("/data_document_request/tambah")
    public String tambah(Model model,@CookieValue(name = "kodene", defaultValue = "logout") String nip,@CookieValue(name = "sopo", defaultValue = "logout") String sopo) {
        Date id_document_request = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyymdhhmms");
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("id_document_request", "DCR"+ft.format(id_document_request));

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

        return "data_document_request_tambah";
    }

    //PROSES SIMPAN
    @RequestMapping(value = "/data_document_request/proses_simpan", method = RequestMethod.POST)
    public String proses_simpan(@ModelAttribute("data_document_request") data_document_request data_document_request) {
        jdbcTemplate.update(
                "INSERT INTO data_document_request (" +
                        "id_document_request" +
                        ", nip" +
                        ", tanggal" +
                        ", keterangan" +
                        ", status" +
                        ") " +
                        "VALUES (?,?,?,?,?)",
                data_document_request.getId_document_request()
                , data_document_request.getNip()
                , data_document_request.getTanggal()
                , data_document_request.getKeterangan()
                , data_document_request.getStatus()
        );
        return "redirect:/data_document_request/tampil";
    }

    //EDIT
    @RequestMapping("/data_document_request/edit/{id_document_request}")
    public String edit(@PathVariable(name = "id_document_request") String id_document_request,Model model) {

        List<data_document_request> data_document_request = jdbcTemplate.query(
                "SELECT * from data_document_request WHERE id_document_request = ?", new Object[]{id_document_request},
                (rs, rowNum) -> new data_document_request(
                        rs.getString("id_document_request")
                        , rs.getString("nip")
                        , rs.getString("tanggal")
                        ,  rs.getString("keterangan")
                        ,  rs.getString("status")
                )
        );
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("data_document_request", data_document_request.get(0));

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

        return "data_document_request_edit";

    }

    //PROSES UPDATE
        @RequestMapping(value = "/data_document_request/proses_update", method = RequestMethod.POST)
        public String proses_update(@ModelAttribute("data_document_request") data_document_request data_document_request) {

            jdbcTemplate.update(
                        "UPDATE data_document_request SET " +
                            "nip = ?" +
                            ",tanggal= ?" +
                            ",keterangan= ?" +
                            ",status= ?" +
                            "WHERE id_document_request = ?",
                    data_document_request.getNip()
                    , data_document_request.getTanggal()
                    , data_document_request.getKeterangan()
                    , data_document_request.getStatus()
                    , data_document_request.getId_document_request()

            );
        return "redirect:/data_document_request/tampil";
    }


    //HAPUS
    @RequestMapping("/data_document_request/delete/{id_document_request}")
    public String proses_hapus(@PathVariable(name = "id_document_request") String id_document_request) {

        jdbcTemplate.update("DELETE FROM data_document_request WHERE id_document_request = ?", id_document_request);
        return "redirect:/data_document_request/tampil";
    }
}