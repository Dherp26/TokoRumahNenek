package com.example.hris.data_admin;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class data_admin_controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private data_admin_service service;

    //TAMPIL
    @GetMapping("/data_admin/tampil")
    public String tampil(Model model,@CookieValue(name = "kodene", defaultValue = "logout") String username,@CookieValue(name = "sopo", defaultValue = "logout") String sopo) {
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("sopo",  "Welcome " + sopo);


        List<data_admin> data_admin = jdbcTemplate.query(
                "SELECT * from data_admin order by id_admin asc",
                (rs, rowNum) -> new data_admin(
                        rs.getString("id_admin")
                        , rs.getString("nama")
                        , rs.getString("username")
                        , rs.getString("password")
                )
        );

        model.addAttribute("data_admin", data_admin);
        return "data_admin_tampil";
    }

    //TAMBAH
    @GetMapping("/data_admin/tambah")
    public String tambah(Model model) {
        Date id_admin = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyymdhhmms");
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("id_admin", "ADM"+ft.format(id_admin));
        return "data_admin_tambah";
    }

    //PROSES SIMPAN
    @RequestMapping(value = "/data_admin/proses_simpan", method = RequestMethod.POST)
    public String proses_simpan(@ModelAttribute("data_admin") data_admin data_admin) {
        jdbcTemplate.update(
                "INSERT INTO data_admin (" +
                        "id_admin" +
                        ", nama" +
                        ", username" +
                        ", password" +
                        ") " +
                        "VALUES (?,?,?,?)",
                data_admin.getId_admin()
                , data_admin.getNama()
                , data_admin.getUsername()
                , data_admin.getPassword()
        );
        return "redirect:/data_admin/tampil";
    }

    //EDIT
    @RequestMapping("/data_admin/edit/{id_admin}")
    public String edit(@PathVariable(name = "id_admin") String id_admin,Model model) {

        List<data_admin> data_admin = jdbcTemplate.query(
                "SELECT * from data_admin WHERE id_admin = ?", new Object[]{id_admin},
                (rs, rowNum) -> new data_admin(
                        rs.getString("id_admin")
                        , rs.getString("nama")
                        , rs.getString("username")
                        ,  rs.getString("password")
                )
        );
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("data_admin", data_admin.get(0));

        return "data_admin_edit";

    }

    //PROSES UPDATE
        @RequestMapping(value = "/data_admin/proses_update", method = RequestMethod.POST)
        public String proses_update(@ModelAttribute("data_admin") data_admin data_admin) {

            jdbcTemplate.update(
                        "UPDATE data_admin SET " +
                            "nama = ?" +
                            ",username= ?" +
                            ",password= ?" +
                            "WHERE id_admin = ?",
                    data_admin.getNama()
                    , data_admin.getUsername()
                    , data_admin.getPassword()
                    , data_admin.getId_admin()
            );
        return "redirect:/data_admin/tampil";
    }


    //HAPUS
    @RequestMapping("/data_admin/delete/{id_admin}")
    public String proses_hapus(@PathVariable(name = "id_admin") String id_admin) {

        jdbcTemplate.update("DELETE FROM data_admin WHERE id_admin = ?", id_admin);
        return "redirect:/data_admin/tampil";
    }
}