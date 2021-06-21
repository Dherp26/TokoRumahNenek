package com.example.hris.home;


import com.example.hris.data_admin.data_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class index {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int total(String tabel) {
        String sql = "SELECT COUNT(*) FROM " + tabel;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    //HOME
    @GetMapping("/")
    public String viewHomePage(Model model,@CookieValue(name = "kodene", defaultValue = "logout") String username,@CookieValue(name = "sopo", defaultValue = "logout") String sopo) {

        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek"  );
        model.addAttribute("sopo",  "Welcome " + sopo);

        model.addAttribute("dashboard1", total("data_pegawai"));
        model.addAttribute("dashboard2", total("data_absensi"));
        model.addAttribute("dashboard3", total("data_overtime"));
        model.addAttribute("dashboard4", total("data_document_request"));
        return "index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        return "login";
    }


    public int login_admin(String username,String password) {
        String sql = "SELECT COUNT(*) FROM data_admin where username='" + username + "' and password = '" + password+ "'";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int login_pegawai(String username,String password) {
        String sql = "SELECT COUNT(*) FROM data_pegawai where username='" + username + "' and password = '" + password+ "'";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public String admin_siapa(String username,String password) {
        String sql = "SELECT id_admin FROM data_admin where username='" + username + "' and password = '" + password+ "'";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    public String pegawai_siapa(String username,String password) {
        String sql = "SELECT nip FROM data_pegawai where username='" + username + "' and password = '" + password+ "'";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    @RequestMapping(value = "/login/validasi", method = RequestMethod.POST)
    public String validasi(@ModelAttribute("data_admin") data_admin data_admin,  @RequestParam("username") String user1,
                           @RequestParam("password") String pass1) {
         String user2 = "udin";
         String pass2 ="123";
         String admin_siapa = "admin";
//        if (login_admin(data_admin.getUsername(),data_admin.getPassword()) == 1)
//        {
//            //admin
//            return "redirect:/login?berhasil=login&kodene=" + admin_siapa(data_admin.getUsername(),data_admin.getPassword()) + "&jenenge=" + data_admin.getUsername() + "&siapa=Administrator";
//        }

        if ( user1.equals(user2) && pass1.equals(pass2))
        {
            //admin
            return "redirect:/login?berhasil=login&kodene=" + admin_siapa + "&jenenge=" + user2 + "&siapa=Administrator";
        }
        else
        {
            if (login_pegawai(data_admin.getUsername(),data_admin.getPassword()) == 1)
            {
                //pegawai
                return "redirect:/login?berhasil=login&kodene=" + pegawai_siapa(data_admin.getUsername(),data_admin.getPassword()) + "&jenenge=" + data_admin.getUsername() + "&siapa=" + data_admin.getUsername();

            }
            else
            {
                return "redirect:/login?retry";
            }

        }

    }



}