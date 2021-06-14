package com.example.hris.data_gaji;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.hris.data_pegawai.data_pegawai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.hris.config.config.rupiah;

@Controller
public class data_gaji_controller {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private data_gaji_service service;

    //TAMPIL
    @GetMapping("/data_gaji/tampil")
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
            q = " and data_gaji.nip='" + nip +"'";

        }


        List<data_gaji> data_gaji = jdbcTemplate.query(
                "select data_pegawai.nip, data_pegawai.nama , data_gaji.* from data_pegawai,data_gaji where data_gaji.nip = data_pegawai.nip" + q,
                (rs, rowNum) -> new data_gaji(
                        rs.getString("id_gaji")
                        , rs.getString("data_pegawai.nip") + " ( " + rs.getString("data_pegawai.nama") + " )"
                        , rs.getString("bulan")
                        , rs.getString("tahun")
                        , rupiah(Long.parseLong(rs.getString("total_gaji_pokok")))
                        , rupiah(Long.parseLong(rs.getString("total_gaji_overtime")))
                        , rupiah(Long.parseLong(rs.getString("total_potongan_gaji")))
                        , rupiah(Long.parseLong(rs.getString("total_gaji")))
                )
        );

        model.addAttribute("data_gaji", data_gaji);
        return "data_gaji_tampil";
    }

    //TAMBAH
    @GetMapping("/data_gaji/tambah")
    public String tambah(Model model) {
        Date id_gaji = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("yyyymdhhmms");
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("id_gaji", "GJI"+ft.format(id_gaji));

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


        return "data_gaji_tambah";
    }


    public String ambil_database(String query) {
        String sql = query;
        return jdbcTemplate.queryForObject(sql, String.class);

    }

    public int total(String query) {
        String sql = query;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    //PROSES SIMPAN
    @RequestMapping(value = "/data_gaji/proses_simpan", method = RequestMethod.POST)
    public String proses_simpan(@ModelAttribute("data_gaji") data_gaji data_gaji) {

        String nip = "0";
        String jumlah_alfa = "0" ;
        String gaji_pokok = "0";
        String gaji_overtime = "0";
        String potongan = "0";
        String total_potongan = "0";
        String total;
        String bulan="";
        String tahun="";

        nip = data_gaji.getNip();
        bulan = data_gaji.getBulan();
        tahun = data_gaji.getTahun();

        gaji_pokok = ambil_database("select total_gaji_pokok from data_pegawai where nip='"+ nip +"' ");

        if (total("select COUNT(*) from data_absensi where nip='"+ nip +"' and bulan='" + bulan + "' and tahun='"  + tahun + "'") == 0)
        {

            gaji_pokok = "0";

        }
        else
        {
            jumlah_alfa = ambil_database("select jumlah_alfa from data_absensi where nip='"+ nip +"' and bulan='" + bulan + "' and tahun='"  + tahun + "'");
            potongan = ambil_database("select potongan_gaji_alfa from data_pegawai where nip='"+ nip +"'");
            total_potongan = String.valueOf(Integer.parseInt(jumlah_alfa) *  Integer.parseInt(potongan));

        }



        if (total("select COUNT(*) from data_overtime where nip='"+ nip +"' and bulan='" + bulan + "' and tahun='"  + tahun + "' and status='approve'") == 0 )
        {

        }
        else
        {
            gaji_overtime = String.valueOf(
                    Integer.parseInt(ambil_database("select biaya_overtime from data_overtime where nip='"+ nip +"' and bulan='" + bulan + "' and tahun='"  + tahun + "' and status='approve'")) *
                            Integer.parseInt(ambil_database("select jumlah_hari from data_overtime where nip='"+ nip +"' and bulan='" + bulan + "' and tahun='"  + tahun + "' and status='approve'")));

        }




        long tot = Integer.parseInt(gaji_pokok) + Integer.parseInt(gaji_overtime) ;
        tot = tot - Integer.parseInt(total_potongan);
        total = String.valueOf(tot);

        jdbcTemplate.update(
                "INSERT INTO data_gaji (" +
                        "id_gaji" +
                        ", nip" +
                        ", bulan" +
                        ", tahun" +
                        ", total_gaji_pokok" +
                        ", total_gaji_overtime" +
                        ", total_potongan_gaji" +
                        ", total_gaji" +
                        ") " +
                        "VALUES (?,?,?,?,?,?,?,?)",
                data_gaji.getId_gaji()
                , data_gaji.getNip()
                , data_gaji.getBulan()
                , data_gaji.getTahun()
                , gaji_pokok
                , gaji_overtime
                , total_potongan
                , total
        );
        return "redirect:/data_gaji/tampil";
    }

    //EDIT
    @RequestMapping("/data_gaji/edit/{id_gaji}")
    public String edit(@PathVariable(name = "id_gaji") String id_gaji,Model model) {

        List<data_gaji> data_gaji = jdbcTemplate.query(
                "SELECT * from data_gaji WHERE id_gaji = ?", new Object[]{id_gaji},
                (rs, rowNum) -> new data_gaji(
                        rs.getString("id_gaji")
                        , rs.getString("nip")
                        , rs.getString("bulan")
                        ,  rs.getString("tahun")
                        ,  rs.getString("total_gaji_pokok")
                        ,  rs.getString("total_gaji_overtime")
                        ,  rs.getString("total_potongan_gaji")
                        ,  rs.getString("total_gaji")
                )
        );
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("data_gaji", data_gaji.get(0));

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

        return "data_gaji_edit";

    }


    //SLIP
    @RequestMapping("/data_gaji/slip/{id_gaji}")
    public String slip(@PathVariable(name = "id_gaji") String id_gaji,Model model) {


        Date tanggal = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat("d MMMM yyyy");

        List<data_gaji> data_gaji = jdbcTemplate.query(
                "SELECT * from data_gaji WHERE id_gaji = ?", new Object[]{id_gaji},
                (rs, rowNum) -> new data_gaji(
                        rs.getString("id_gaji")
                        , rs.getString("nip")
                        , ft.format(tanggal)
                        ,  rs.getString("bulan") + " " +  rs.getString("tahun")
                        , rupiah(Long.parseLong(rs.getString("total_gaji_pokok")))
                        , rupiah(Long.parseLong(rs.getString("total_gaji_overtime")))
                        , rupiah(Long.parseLong(rs.getString("total_potongan_gaji")))
                        , rupiah(Long.parseLong(rs.getString("total_gaji")))
                )
        );
        model.addAttribute("namaaplikasi", "HRIS Toko Rumah Nenek");
        model.addAttribute("data_gaji", data_gaji.get(0));
        String nip;
        nip =  ambil_database("select nip from data_gaji WHERE id_gaji = '"+ id_gaji +"'");

        List<data_pegawai> data_pegawai = jdbcTemplate.query(
                "SELECT * from data_pegawai where nip='" + nip + "'",
                (rs, rowNum) -> new data_pegawai(
                        rs.getString("id_pegawai")
                        , rs.getString("nip")
                        , rs.getString("nama")
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

        return "data_gaji_slip";

    }

    //PROSES UPDATE
        @RequestMapping(value = "/data_gaji/proses_update", method = RequestMethod.POST)
        public String proses_update(@ModelAttribute("data_gaji") data_gaji data_gaji) {

            jdbcTemplate.update(
                        "UPDATE data_gaji SET " +
                            "nip = ?" +
                            ",bulan= ?" +
                            ",tahun= ?" +
                            ",total_gaji_pokok= ?" +
                            ",total_gaji_overtime= ?" +
                            ",total_potongan_gaji= ?" +
                            ",total_gaji= ?" +
                            "WHERE id_gaji = ?",
                    data_gaji.getNip()
                    , data_gaji.getBulan()
                    , data_gaji.getTahun()
                    , data_gaji.getTotal_gaji_pokok()
                    , data_gaji.getTotal_gaji_overtime()
                    , data_gaji.getTotal_potongan_gaji()
                    , data_gaji.getTotal_gaji()
                    , data_gaji.getId_gaji()

            );
        return "redirect:/data_gaji/tampil";
    }


    //HAPUS
    @RequestMapping("/data_gaji/delete/{id_gaji}")
    public String proses_hapus(@PathVariable(name = "id_gaji") String id_gaji) {

        jdbcTemplate.update("DELETE FROM data_gaji WHERE id_gaji = ?", id_gaji);
        return "redirect:/data_gaji/tampil";
    }
}