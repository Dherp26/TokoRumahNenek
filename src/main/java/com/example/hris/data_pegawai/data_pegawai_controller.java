package com.example.hris.data_pegawai;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.hris.config.config.rupiah;

@Controller
public class data_pegawai_controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private data_pegawai_service service;

    //TAMPIL
    @GetMapping("/data_pegawai/tampil")
    public String tampil(Model model,@CookieValue(name = "kodene", defaultValue = "logout") String nama,@CookieValue(name = "sopo", defaultValue = "logout") String sopo) {
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("sopo",  "Welcome " + sopo);
        List<data_pegawai> data_pegawai = jdbcTemplate.query(
                "SELECT * from data_pegawai order by id_pegawai asc",
                (rs, rowNum) -> new data_pegawai(
                        rs.getString("id_pegawai")
                        , rs.getString("nip")
                        , rs.getString("nama")
                        , rs.getString("alamat")
                        , rs.getString("no_telepon")
                        , rs.getString("jabatan")
                        , rupiah(Long.parseLong(rs.getString("total_gaji_pokok")))
                        , rupiah(Long.parseLong(rs.getString("potongan_gaji_alfa")))
                        , rs.getString("username")
                        , rs.getString("password")
                )
        );

        model.addAttribute("data_pegawai", data_pegawai);
        return "data_pegawai_tampil";
    }

    //TAMBAH
    @GetMapping("/data_pegawai/tambah")
    public String tambah(Model model) {
        Date id_pegawai = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyymdhhmms");
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("id_pegawai", "PGW"+ft.format(id_pegawai));
        return "data_pegawai_tambah";
    }

    //PROSES SIMPAN
    @RequestMapping(value = "/data_pegawai/proses_simpan", method = RequestMethod.POST)
    public String proses_simpan(@ModelAttribute("data_pegawai") data_pegawai data_pegawai) {
        jdbcTemplate.update(
                "INSERT INTO data_pegawai (" +
                        "id_pegawai" +
                        ", nip" +
                        ", nama" +
                        ", alamat" +
                        ", no_telepon" +
                        ", jabatan" +
                        ", total_gaji_pokok" +
                        ", potongan_gaji_alfa" +
                        ", username" +
                        ", password" +
                        ") " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?)",
                data_pegawai.getId_pegawai()
                , data_pegawai.getNip()
                , data_pegawai.getNama()
                , data_pegawai.getAlamat()
                , data_pegawai.getNo_telepon()
                , data_pegawai.getJabatan()
                , data_pegawai.getTotal_gaji_pokok()
                , data_pegawai.getPotongan_gaji_alfa()
                , data_pegawai.getUsername()
                , data_pegawai.getPassword()
        );
        return "redirect:/data_pegawai/tampil";
    }

    //EDIT
    @RequestMapping("/data_pegawai/edit/{id_pegawai}")
    public String edit(@PathVariable(name = "id_pegawai") String id_pegawai,Model model) {

        List<data_pegawai> data_pegawai = jdbcTemplate.query(
                "SELECT * from data_pegawai WHERE id_pegawai = ?", new Object[]{id_pegawai},
                (rs, rowNum) -> new data_pegawai(
                        rs.getString("id_pegawai")
                        , rs.getString("nip")
                        , rs.getString("nama")
                        ,  rs.getString("alamat")
                        ,  rs.getString("no_telepon")
                        ,  rs.getString("jabatan")
                        ,  rs.getString("total_gaji_pokok")
                        ,  rs.getString("potongan_gaji_alfa")
                        ,  rs.getString("username")
                        ,  rs.getString("password")
                )
        );

        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("data_pegawai", data_pegawai.get(0));

        return "data_pegawai_edit";

    }

    //PROSES UPDATE
        @RequestMapping(value = "/data_pegawai/proses_update", method = RequestMethod.POST)
        public String proses_update(@ModelAttribute("data_pegawai") data_pegawai data_pegawai) {

            jdbcTemplate.update(
                        "UPDATE data_pegawai SET " +
                            "nip = ?" +
                            ",nama= ?" +
                            ",alamat= ?" +
                            ",no_telepon= ?" +
                            ",jabatan= ?" +
                            ",total_gaji_pokok= ?" +
                            ",potongan_gaji_alfa= ?" +
                            ",username= ?" +
                            ",password= ?" +
                            "WHERE id_pegawai = ?",
                    data_pegawai.getNip()
                    , data_pegawai.getNama()
                    , data_pegawai.getAlamat()
                    , data_pegawai.getNo_telepon()
                    , data_pegawai.getJabatan()
                    , data_pegawai.getTotal_gaji_pokok()
                    , data_pegawai.getPotongan_gaji_alfa()
                    , data_pegawai.getUsername()
                    , data_pegawai.getPassword()
                    , data_pegawai.getId_pegawai()

            );
        return "redirect:/data_pegawai/tampil";
    }


    //HAPUS
    @RequestMapping("/data_pegawai/delete/{nip}")
    public String proses_hapus(@PathVariable(name = "nip") String nip) {

        jdbcTemplate.update("DELETE FROM data_pegawai WHERE nip = ?", nip);
        jdbcTemplate.update("DELETE FROM data_absensi WHERE nip = ?", nip);
        jdbcTemplate.update("DELETE FROM data_document_request WHERE nip = ?", nip);
        jdbcTemplate.update("DELETE FROM data_gaji WHERE nip = ?", nip);
        jdbcTemplate.update("DELETE FROM data_leave WHERE nip = ?", nip);
        jdbcTemplate.update("DELETE FROM data_overtime WHERE nip = ?", nip);

        return "redirect:/data_pegawai/tampil";
    }
}