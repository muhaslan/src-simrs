package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPetugas;
import widget.CekBox;


/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenAwalKebidananIGDPartus extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabModeRiwayatKehamilan,tabModeRiwayatKehamilan2;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private StringBuilder htmlContent;
    private String pilihan="",finger="";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenAwalKebidananIGDPartus(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        DlgRiwayatPersalinan.setSize(650,192);
        
        tabMode=new DefaultTableModel(null,new Object[]{
            // header
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Tanggal Asuhan","NIP Pengkaji","Nama Pengkaji","NIP DPJP",
            "Nama DPJP","Cara Datang","Menggunakan","Dari","Pengkajian Diperoleh Dari","Alasan Masuk",
            // riwayat kesehatan
            "Penyakit yang Diderita","Riwayat  Penyakit","Faktor Keturunan Gemelli","Ketergantungan","Alergi Obat-Obatan",
            "Alergi Makanan","Alergi Debu","Alergi Lainnya",
            // riwayat obstetri
            "Menarche","Menstruasi","Siklus","Sakit Saat Menstruasi","Menikah yang ke","Lamanya Pernikahan",
            "Kontrasepsi yang pernah digunakan","Lamanya",
            // data kehamilan
            "Graphite","Paritas","Abortus","Hari Pertama Haid Terakhir","Hari Perkiraan Lahir","Umur Kehamilan","Keluhan Kehamilan Saat Ini",
            "Tinggi Fundus Uteri","Letak Punggung Janin","Presentasi Janin","Taksiran Berat Janin","Penurunan","Aukultasi","Frekuensi Aukultasi",
            "Pemeriksaan Dalam",
            // pemeriksaan fisik
            "TD","Nadi","RR","Suhu","TB","BB","Keadaan Umum","GCS","Kesadaran","Kepala","Mata","Hidung","Gigi & Mulut","Tenggorokan",
            "Telinga","Ekstremitas","Leher","Thoraks","Jantung","Paru","Abdomen","Genitalia & Anus",
            // penilaian tingkat nyeri
            "Nyeri","Skor","Kategori","Pengaruh Nyeri",
            // pemeriksaan penunjang terakhir
            "HB","Hasil USG",
            // status mental
            "Status Mental",
            // data psikososial
            "Respon Emosi","Suport Suami/Keluarga","Masalah Kebidanan",
            // Diagnosa kebidanan
            "Diagnosa Kebidanan"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 79; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(130);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
//                column.setPreferredWidth(150);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==7){
                column.setPreferredWidth(100);
            }else if(i==8){
//                column.setPreferredWidth(150);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(150);
            }else if(i==11){
                column.setPreferredWidth(117);
            }else if(i==12){
                column.setPreferredWidth(90);
            }else if(i==13){
                column.setPreferredWidth(110);
            }else if(i==14){
                column.setPreferredWidth(100);
            }else if(i==15){
                column.setPreferredWidth(250);
            }else if(i==16){
                column.setPreferredWidth(150);
            }else if(i==17){
                column.setPreferredWidth(150);
            }else if(i==18){
                column.setPreferredWidth(150);
            }else if(i==19){
                column.setPreferredWidth(100);
            }else if(i==20){
                column.setPreferredWidth(175);
            }else if(i==21){
                column.setPreferredWidth(180);
            }else if(i==22){
                column.setPreferredWidth(100);
            }else if(i==23){
                column.setPreferredWidth(100);
            }else if(i==24){
                column.setPreferredWidth(108);
            }else if(i==25){
                column.setPreferredWidth(100);
            }else if(i==26){
                column.setPreferredWidth(100);
            }else if(i==27){
                column.setPreferredWidth(136);
            }else if(i==28){
                column.setPreferredWidth(100);
            }else if(i==29){
                column.setPreferredWidth(100);
            }else if(i==30){
                column.setPreferredWidth(100);
            }else if(i==31){
                column.setPreferredWidth(106);
            }else if(i==32){
                column.setPreferredWidth(100);
            }else if(i==33){
                column.setPreferredWidth(106);
            }else if(i==34){
                column.setPreferredWidth(100);
            }else if(i==35){
                column.setPreferredWidth(106);
            }else if(i==36){
                column.setPreferredWidth(100);
            }else if(i==37){
                column.setPreferredWidth(100);
            }else if(i==38){
                column.setPreferredWidth(100);
            }else if(i==39){
                column.setPreferredWidth(100);
            }else if(i==40){
                column.setPreferredWidth(100);
            }else if(i==41){
                column.setPreferredWidth(100);
            }else if(i==42){
                column.setPreferredWidth(100);
            }else if(i==43){
                column.setPreferredWidth(100);
            }else if(i==44){
                column.setPreferredWidth(100);
            }else if(i==45){
                column.setPreferredWidth(100);
            }else if(i==46){
                column.setPreferredWidth(100);
            }else if(i==47){
                column.setPreferredWidth(105);
            }else if(i==48){
                column.setPreferredWidth(100);
            }else if(i==49){
                column.setPreferredWidth(150);
            }else if(i==50){
                column.setPreferredWidth(100);
            }else if(i==51){
                column.setPreferredWidth(100);
            }else if(i==52){
                column.setPreferredWidth(120);
            }else if(i==53){
                column.setPreferredWidth(100);
            }else if(i==54){
                column.setPreferredWidth(120);
            }else if(i==55){
                column.setPreferredWidth(104);
            }else if(i==56){
                column.setPreferredWidth(100);
            }else if(i==57){
                column.setPreferredWidth(150);
            }else if(i==58){
                column.setPreferredWidth(100);
            }else if(i==59){
                column.setPreferredWidth(100);
            }else if(i==60){
                column.setPreferredWidth(100);
            }else if(i==61){
                column.setPreferredWidth(100);
            }else if(i==62){
                column.setPreferredWidth(103);
            }else if(i==63){
                column.setPreferredWidth(105);
            }else if(i==64){
                column.setPreferredWidth(100);
            }else if(i==65){
                column.setPreferredWidth(100);
            }else if(i==66){
                column.setPreferredWidth(100);
            }else if(i==67){
                column.setPreferredWidth(100);
            }else if(i==68){
                column.setPreferredWidth(100);
            }else if(i==69){
                column.setPreferredWidth(100);
            }else if(i==70){
                column.setPreferredWidth(100);
            }else if(i==71){
                column.setPreferredWidth(100);
            }else if(i==72){
                column.setPreferredWidth(100);
            }else if(i==73){
                column.setPreferredWidth(100);
            }else if(i==74){
                column.setPreferredWidth(100);
            }else if(i==75){
                column.setPreferredWidth(100);
            }else if(i==76){
                column.setPreferredWidth(100);
            }else if(i==77){
                column.setPreferredWidth(100);
            }else if(i==78){
                column.setPreferredWidth(100);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRiwayatKehamilan=new DefaultTableModel(null,new Object[]{
                "No","P/L","UMUR ANAK","KU ANAK","BBL (gram)","RIWAYAT PERSALINAN","DITOLONG OLEH & TEMPAT","no_rkm_medis","id_riwayat"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbRiwayatKehamilan.setModel(tabModeRiwayatKehamilan);

        tbRiwayatKehamilan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRiwayatKehamilan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbRiwayatKehamilan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(30);
            }else if(i==1){
                column.setPreferredWidth(40);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(150);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==8){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwayatKehamilan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRiwayatKehamilan2=new DefaultTableModel(null,new Object[]{
                "No","P/L","UMUR ANAK","KU ANAK","BBL (gram)","RIWAYAT PERSALINAN","DITOLONG OLEH & TEMPAT"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbRiwayatKehamilan1.setModel(tabModeRiwayatKehamilan2);

        tbRiwayatKehamilan1.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRiwayatKehamilan1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbRiwayatKehamilan1.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(30);
            }else if(i==1){
                column.setPreferredWidth(40);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(150);
            }else if(i==6){
                column.setPreferredWidth(150);
            }
        }
        tbRiwayatKehamilan1.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){ 
                    if(i==1){
                        KdPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                        NmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),2).toString());  
                    }else{
//                        KdPetugas2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
//                        NmPetugas2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),2).toString());  
                    }
                         
                }              
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){ 
                    KdDPJP.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NmDPJP.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),2).toString());  
                }              
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        ChkAccor.setSelected(false);
        isMenu();
        
        // default panelisi
        panelisi1.setVisible(false);
        
        // defeault ketergantungan
        InputKetergantungan.setVisible(false);
        KetergantunganSejak.setVisible(false);
        jLabel76.setVisible(false);
        
        // default pilihan lainnya
        DariLainnya.setVisible(false);
        MenggunakanLainnya.setVisible(false);
        CaraDatangLainnya.setVisible(false);
        
        // default hubungan denga pasien
        jLabel37.setVisible(false);
        HubunganDenganPasien.setVisible(false);
        
        // default kesadaran
        InputPenurunanKesadaran.setVisible(false);
        
        // default riwayat alergi
        InputObat.setVisible(false);
        InputMakanan.setVisible(false);
        InputDebu.setVisible(false);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DlgRiwayatPersalinan = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelBiasa2 = new widget.PanelBiasa();
        jLabel99 = new widget.Label();
        BtnKeluarKehamilan = new widget.Button();
        BtnSimpanRiwayatKehamilan = new widget.Button();
        UmurAnak = new widget.TextBox();
        jLabel105 = new widget.Label();
        KUAnak = new widget.TextBox();
        jLabel108 = new widget.Label();
        JK = new widget.ComboBox();
        jLabel110 = new widget.Label();
        BBL = new widget.TextBox();
        jLabel111 = new widget.Label();
        RiwayatPersalinan = new widget.TextBox();
        jLabel112 = new widget.Label();
        DitolongOleh = new widget.TextBox();
        BtnEdit1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        label14 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel36 = new widget.Label();
        CaraDatang = new widget.ComboBox();
        TglAsuhan = new widget.Tanggal();
        label16 = new widget.Label();
        KdDPJP = new widget.TextBox();
        NmDPJP = new widget.TextBox();
        BtnDPJP = new widget.Button();
        Menggunakan = new widget.ComboBox();
        jLabel37 = new widget.Label();
        CaraMasuk = new widget.ComboBox();
        jLabel38 = new widget.Label();
        jLabel94 = new widget.Label();
        jLabel73 = new widget.Label();
        jSeparator2 = new javax.swing.JSeparator();
        DariLainnya = new widget.TextBox();
        HubunganDenganPasien = new widget.TextBox();
        MenggunakanLainnya = new widget.TextBox();
        jLabel200 = new widget.Label();
        PenyakitDiderita = new widget.ComboBox();
        CaraDatangLainnya = new widget.TextBox();
        jLabel212 = new widget.Label();
        jLabel213 = new widget.Label();
        AlasanMasuk = new widget.TextBox();
        PengkajianOleh = new widget.ComboBox();
        panelisi1 = new widget.panelisi();
        TBC = new widget.CekBox();
        Asthma = new widget.CekBox();
        Hypertensi = new widget.CekBox();
        PenyakitGinjal = new widget.CekBox();
        PenyakitJantung = new widget.CekBox();
        PenyakitThyroid = new widget.CekBox();
        Hepatitis = new widget.CekBox();
        DiabetesMelitus = new widget.CekBox();
        PenyakitKelamin = new widget.CekBox();
        Epilepsi = new widget.CekBox();
        LainnyaInput = new widget.TextBox();
        label10 = new widget.Label();
        panelInputan = new widget.panelisi();
        jLabel74 = new widget.Label();
        Gamelli = new widget.ComboBox();
        jLabel75 = new widget.Label();
        Ketergantungan = new widget.ComboBox();
        InputKetergantungan = new widget.TextBox();
        KetergantunganSejak = new widget.TextBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        ObatObatan = new widget.ComboBox();
        jLabel79 = new widget.Label();
        Makanan = new widget.ComboBox();
        jLabel80 = new widget.Label();
        Debu = new widget.ComboBox();
        jLabel81 = new widget.Label();
        AlergiLainnya = new widget.TextBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel101 = new widget.Label();
        BtnTambahMasalah = new widget.Button();
        BtnHapusRiwayatPersalinan = new widget.Button();
        Scroll6 = new widget.ScrollPane();
        tbRiwayatKehamilan = new widget.Table();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel129 = new widget.Label();
        jLabel82 = new widget.Label();
        Menarche = new widget.TextBox();
        jLabel86 = new widget.Label();
        jLabel85 = new widget.Label();
        Menstruasi = new widget.ComboBox();
        HariMens = new widget.TextBox();
        jLabel84 = new widget.Label();
        jLabel87 = new widget.Label();
        SakitSaatMens = new widget.ComboBox();
        jLabel91 = new widget.Label();
        Menikahke = new widget.TextBox();
        jLabel89 = new widget.Label();
        LamanyaMenikah = new widget.TextBox();
        jLabel96 = new widget.Label();
        NmKontrasepsi = new widget.TextBox();
        jLabel92 = new widget.Label();
        KontrasepsiLamanya = new widget.TextBox();
        jLabel131 = new widget.Label();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel88 = new widget.Label();
        InputG = new widget.TextBox();
        jLabel93 = new widget.Label();
        InputP = new widget.TextBox();
        jLabel95 = new widget.Label();
        InputA = new widget.TextBox();
        jLabel90 = new widget.Label();
        HPHT = new widget.Tanggal();
        jLabel97 = new widget.Label();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        scrollPane1 = new widget.ScrollPane();
        Keluhan = new widget.TextArea();
        jLabel100 = new widget.Label();
        jLabel104 = new widget.Label();
        TinggiFundus = new widget.TextBox();
        jLabel117 = new widget.Label();
        jLabel113 = new widget.Label();
        LetakPunggungJanin = new widget.ComboBox();
        PresentasiJanin = new widget.ComboBox();
        jLabel114 = new widget.Label();
        jLabel115 = new widget.Label();
        BeratJanin = new widget.TextBox();
        jLabel118 = new widget.Label();
        Penurunan = new widget.ComboBox();
        jLabel116 = new widget.Label();
        jLabel120 = new widget.Label();
        Aukultasi = new widget.TextBox();
        jLabel119 = new widget.Label();
        FrekAukultasi = new widget.ComboBox();
        jLabel98 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        PemeriksaanDalam = new widget.TextArea();
        jLabel153 = new widget.Label();
        jLabel22 = new widget.Label();
        TD = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel17 = new widget.Label();
        Nadi = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel26 = new widget.Label();
        RR = new widget.TextBox();
        jLabel25 = new widget.Label();
        jLabel18 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel15 = new widget.Label();
        TB = new widget.TextBox();
        jLabel24 = new widget.Label();
        jLabel12 = new widget.Label();
        BB = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel41 = new widget.Label();
        Keadaan1 = new widget.ComboBox();
        jLabel28 = new widget.Label();
        GCS = new widget.TextBox();
        jLabel39 = new widget.Label();
        Kesadaran = new widget.ComboBox();
        jLabel40 = new widget.Label();
        Kepala = new widget.ComboBox();
        jLabel53 = new widget.Label();
        Leher = new widget.ComboBox();
        jLabel42 = new widget.Label();
        Mata = new widget.ComboBox();
        jLabel49 = new widget.Label();
        Thoraks = new widget.ComboBox();
        Jantung = new widget.ComboBox();
        jLabel50 = new widget.Label();
        Hidung = new widget.ComboBox();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        GigiMulut = new widget.ComboBox();
        jLabel51 = new widget.Label();
        Paru = new widget.ComboBox();
        Abdomen = new widget.ComboBox();
        jLabel52 = new widget.Label();
        Tenggorokan = new widget.ComboBox();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        Telinga = new widget.ComboBox();
        jLabel54 = new widget.Label();
        Anus = new widget.ComboBox();
        jLabel48 = new widget.Label();
        Ekstremitas = new widget.ComboBox();
        jLabel167 = new widget.Label();
        jSeparator10 = new javax.swing.JSeparator();
        PanelWall = new usu.widget.glass.PanelGlass();
        Nyeri = new widget.ComboBox();
        label2 = new widget.Label();
        label1 = new widget.Label();
        SkorNyeri = new widget.ComboBox();
        label3 = new widget.Label();
        KategoriNyeri = new widget.ComboBox();
        PengaruhNyeri = new widget.ComboBox();
        label4 = new widget.Label();
        jLabel202 = new widget.Label();
        jSeparator11 = new javax.swing.JSeparator();
        label5 = new widget.Label();
        HB = new widget.TextBox();
        label6 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        HasilUSG = new widget.TextArea();
        jSeparator12 = new javax.swing.JSeparator();
        jLabel203 = new widget.Label();
        panelisi2 = new widget.panelisi();
        Disorientasi = new widget.CekBox();
        Orientasi = new widget.CekBox();
        Agitasi = new widget.CekBox();
        cekBox2 = new widget.CekBox();
        Kooperatif = new widget.CekBox();
        TidakRespon = new widget.CekBox();
        jLabel204 = new widget.Label();
        jSeparator13 = new javax.swing.JSeparator();
        label8 = new widget.Label();
        ResEmosi = new widget.ComboBox();
        label9 = new widget.Label();
        SupportSuami = new widget.ComboBox();
        jLabel205 = new widget.Label();
        panelisi3 = new widget.panelisi();
        RisikoPendarahan = new widget.CekBox();
        nyeri = new widget.CekBox();
        cemas = new widget.CekBox();
        PerubahanNutrisi = new widget.CekBox();
        cekBox6 = new widget.CekBox();
        eliminasi = new widget.CekBox();
        PengetahuanKomunikasiInformasi = new widget.CekBox();
        label7 = new widget.Label();
        MasalahKebidananLainnya = new widget.TextBox();
        jLabel201 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        DiagnosaKebidanan = new widget.TextArea();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel121 = new widget.Label();
        jLabel122 = new widget.Label();
        InputDebu = new widget.TextBox();
        InputObat = new widget.TextBox();
        InputMakanan = new widget.TextBox();
        InputPenurunanKesadaran = new widget.TextBox();
        HPL = new widget.TextBox();
        UmurKehamilan = new widget.TextBox();
        BtnEditRiwayatPersalinan = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        jLabel34 = new widget.Label();
        TNoRM1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        BtnPrint1 = new widget.Button();
        FormMasalahRencana = new widget.PanelBiasa();
        scrollPane9 = new widget.ScrollPane();
        tbRiwayatKehamilan1 = new widget.Table();
        scrollPane7 = new widget.ScrollPane();
        DiagnosaKebidanan1 = new widget.TextArea();

        DlgRiwayatPersalinan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgRiwayatPersalinan.setName("DlgRiwayatPersalinan"); // NOI18N
        DlgRiwayatPersalinan.setUndecorated(true);
        DlgRiwayatPersalinan.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Riwayat Persalinan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 70, 50))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa2.setName("panelBiasa2"); // NOI18N
        panelBiasa2.setLayout(null);

        jLabel99.setText("Umur Anak :");
        jLabel99.setName("jLabel99"); // NOI18N
        panelBiasa2.add(jLabel99);
        jLabel99.setBounds(0, 10, 130, 23);

        BtnKeluarKehamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnKeluarKehamilan.setMnemonic('U');
        BtnKeluarKehamilan.setText("Tutup");
        BtnKeluarKehamilan.setToolTipText("Alt+U");
        BtnKeluarKehamilan.setName("BtnKeluarKehamilan"); // NOI18N
        BtnKeluarKehamilan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluarKehamilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarKehamilanActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnKeluarKehamilan);
        BtnKeluarKehamilan.setBounds(400, 140, 100, 30);

        BtnSimpanRiwayatKehamilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanRiwayatKehamilan.setMnemonic('S');
        BtnSimpanRiwayatKehamilan.setText("Simpan");
        BtnSimpanRiwayatKehamilan.setToolTipText("Alt+S");
        BtnSimpanRiwayatKehamilan.setName("BtnSimpanRiwayatKehamilan"); // NOI18N
        BtnSimpanRiwayatKehamilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanRiwayatKehamilanActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnSimpanRiwayatKehamilan);
        BtnSimpanRiwayatKehamilan.setBounds(150, 140, 100, 30);

        UmurAnak.setHighlighter(null);
        UmurAnak.setName("UmurAnak"); // NOI18N
        UmurAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                UmurAnakKeyPressed(evt);
            }
        });
        panelBiasa2.add(UmurAnak);
        UmurAnak.setBounds(134, 10, 270, 23);

        jLabel105.setText("KU Anak :");
        jLabel105.setName("jLabel105"); // NOI18N
        panelBiasa2.add(jLabel105);
        jLabel105.setBounds(0, 40, 130, 23);

        KUAnak.setHighlighter(null);
        KUAnak.setName("KUAnak"); // NOI18N
        KUAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KUAnakKeyPressed(evt);
            }
        });
        panelBiasa2.add(KUAnak);
        KUAnak.setBounds(134, 40, 270, 23);

        jLabel108.setText("Jenis Kelamin :");
        jLabel108.setName("jLabel108"); // NOI18N
        panelBiasa2.add(jLabel108);
        jLabel108.setBounds(430, 10, 96, 23);

        JK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-Laki", "Perempuan" }));
        JK.setName("JK"); // NOI18N
        JK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JKKeyPressed(evt);
            }
        });
        panelBiasa2.add(JK);
        JK.setBounds(530, 10, 95, 23);

        jLabel110.setText("BBL (gram) :");
        jLabel110.setName("jLabel110"); // NOI18N
        panelBiasa2.add(jLabel110);
        jLabel110.setBounds(430, 40, 96, 23);

        BBL.setHighlighter(null);
        BBL.setName("BBL"); // NOI18N
        BBL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBLKeyPressed(evt);
            }
        });
        panelBiasa2.add(BBL);
        BBL.setBounds(530, 40, 95, 23);

        jLabel111.setText("Riwayat Persalinan :");
        jLabel111.setName("jLabel111"); // NOI18N
        panelBiasa2.add(jLabel111);
        jLabel111.setBounds(0, 70, 130, 23);

        RiwayatPersalinan.setHighlighter(null);
        RiwayatPersalinan.setName("RiwayatPersalinan"); // NOI18N
        RiwayatPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RiwayatPersalinanKeyPressed(evt);
            }
        });
        panelBiasa2.add(RiwayatPersalinan);
        RiwayatPersalinan.setBounds(133, 70, 270, 23);

        jLabel112.setText("Ditolong Oleh & Tempat :");
        jLabel112.setName("jLabel112"); // NOI18N
        panelBiasa2.add(jLabel112);
        jLabel112.setBounds(0, 100, 130, 23);

        DitolongOleh.setHighlighter(null);
        DitolongOleh.setName("DitolongOleh"); // NOI18N
        DitolongOleh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DitolongOlehKeyPressed(evt);
            }
        });
        panelBiasa2.add(DitolongOleh);
        DitolongOleh.setBounds(133, 100, 270, 23);

        BtnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit1.setMnemonic('G');
        BtnEdit1.setText("Ganti");
        BtnEdit1.setToolTipText("Alt+G");
        BtnEdit1.setName("BtnEdit1"); // NOI18N
        BtnEdit1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit1ActionPerformed(evt);
            }
        });
        BtnEdit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit1KeyPressed(evt);
            }
        });
        panelBiasa2.add(BtnEdit1);
        BtnEdit1.setBounds(270, 140, 100, 30);

        internalFrame4.add(panelBiasa2, java.awt.BorderLayout.CENTER);

        DlgRiwayatPersalinan.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Penilaian Awal Kebidanan IGD ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(933, 3000));
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnAll);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar);

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 627));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2350));
        FormInput.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(309, 10, 260, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 10, 100, 23);

        label14.setText("Pengkaji :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 40, 70, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(74, 40, 100, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(176, 40, 210, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        BtnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(388, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 80, 23);

        Jk.setEditable(false);
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(774, 10, 80, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(850, 10, 70, 23);

        jLabel11.setText("J.K. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(740, 10, 30, 23);

        jLabel36.setText("Cara Datang :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 70, 70, 23);

        CaraDatang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Berjalan", "Ambulance", "Kendaraan Pribadi", "Lainnya" }));
        CaraDatang.setName("CaraDatang"); // NOI18N
        CaraDatang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaraDatangActionPerformed(evt);
            }
        });
        CaraDatang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CaraDatangKeyPressed(evt);
            }
        });
        FormInput.add(CaraDatang);
        CaraDatang.setBounds(75, 70, 150, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-09-2023 09:01:18" }));
        TglAsuhan.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan.setName("TglAsuhan"); // NOI18N
        TglAsuhan.setOpaque(false);
        TglAsuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TglAsuhanActionPerformed(evt);
            }
        });
        TglAsuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhanKeyPressed(evt);
            }
        });
        FormInput.add(TglAsuhan);
        TglAsuhan.setBounds(930, 10, 140, 23);

        label16.setText("DPJP :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(390, 40, 70, 23);

        KdDPJP.setEditable(false);
        KdDPJP.setName("KdDPJP"); // NOI18N
        KdDPJP.setPreferredSize(new java.awt.Dimension(80, 23));
        KdDPJP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDPJPKeyPressed(evt);
            }
        });
        FormInput.add(KdDPJP);
        KdDPJP.setBounds(470, 40, 130, 23);

        NmDPJP.setEditable(false);
        NmDPJP.setName("NmDPJP"); // NOI18N
        NmDPJP.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDPJP);
        NmDPJP.setBounds(600, 40, 360, 23);

        BtnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDPJP.setMnemonic('2');
        BtnDPJP.setToolTipText("Alt+2");
        BtnDPJP.setName("BtnDPJP"); // NOI18N
        BtnDPJP.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDPJPActionPerformed(evt);
            }
        });
        BtnDPJP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDPJPKeyPressed(evt);
            }
        });
        FormInput.add(BtnDPJP);
        BtnDPJP.setBounds(970, 40, 28, 23);

        Menggunakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Stertcher", "Kursi Roda", "Restrain", "Lainnya" }));
        Menggunakan.setName("Menggunakan"); // NOI18N
        Menggunakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenggunakanActionPerformed(evt);
            }
        });
        Menggunakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenggunakanKeyPressed(evt);
            }
        });
        FormInput.add(Menggunakan);
        Menggunakan.setBounds(400, 70, 205, 23);

        jLabel37.setText("Hubungan dengan pasien :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(920, 100, 140, 23);

        CaraMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rumah", "Puskesmas", "Rumah Sakit", "Lainnya" }));
        CaraMasuk.setName("CaraMasuk"); // NOI18N
        CaraMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CaraMasukActionPerformed(evt);
            }
        });
        CaraMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CaraMasukKeyPressed(evt);
            }
        });
        FormInput.add(CaraMasuk);
        CaraMasuk.setBounds(733, 70, 110, 23);

        jLabel38.setText("Dari :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(700, 70, 30, 23);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("I. RIWAYAT KESEHATAN");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 130, 180, 23);

        jLabel73.setText("1. Penyakit yang pernah diderita :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 150, 190, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 130, 880, 1);

        DariLainnya.setHighlighter(null);
        DariLainnya.setName("DariLainnya"); // NOI18N
        FormInput.add(DariLainnya);
        DariLainnya.setBounds(847, 70, 80, 23);

        HubunganDenganPasien.setHighlighter(null);
        HubunganDenganPasien.setName("HubunganDenganPasien"); // NOI18N
        FormInput.add(HubunganDenganPasien);
        HubunganDenganPasien.setBounds(1070, 100, 80, 23);

        MenggunakanLainnya.setHighlighter(null);
        MenggunakanLainnya.setName("MenggunakanLainnya"); // NOI18N
        FormInput.add(MenggunakanLainnya);
        MenggunakanLainnya.setBounds(610, 70, 80, 23);

        jLabel200.setText("Pengkajian diperoleh dari :");
        jLabel200.setName("jLabel200"); // NOI18N
        FormInput.add(jLabel200);
        jLabel200.setBounds(2, 100, 140, 23);

        PenyakitDiderita.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        PenyakitDiderita.setName("PenyakitDiderita"); // NOI18N
        PenyakitDiderita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PenyakitDideritaActionPerformed(evt);
            }
        });
        PenyakitDiderita.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenyakitDideritaKeyPressed(evt);
            }
        });
        FormInput.add(PenyakitDiderita);
        PenyakitDiderita.setBounds(195, 150, 150, 23);

        CaraDatangLainnya.setHighlighter(null);
        CaraDatangLainnya.setName("CaraDatangLainnya"); // NOI18N
        FormInput.add(CaraDatangLainnya);
        CaraDatangLainnya.setBounds(230, 70, 80, 23);

        jLabel212.setText("Menggunakan :");
        jLabel212.setName("jLabel212"); // NOI18N
        FormInput.add(jLabel212);
        jLabel212.setBounds(315, 70, 80, 23);

        jLabel213.setText("Alasan Masuk :");
        jLabel213.setName("jLabel213"); // NOI18N
        FormInput.add(jLabel213);
        jLabel213.setBounds(310, 100, 80, 23);

        AlasanMasuk.setHighlighter(null);
        AlasanMasuk.setName("AlasanMasuk"); // NOI18N
        AlasanMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlasanMasukActionPerformed(evt);
            }
        });
        FormInput.add(AlasanMasuk);
        AlasanMasuk.setBounds(400, 100, 190, 23);

        PengkajianOleh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pasien Sendiri", "Keluarga" }));
        PengkajianOleh.setName("PengkajianOleh"); // NOI18N
        PengkajianOleh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PengkajianOlehActionPerformed(evt);
            }
        });
        PengkajianOleh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PengkajianOlehKeyPressed(evt);
            }
        });
        FormInput.add(PengkajianOleh);
        PengkajianOleh.setBounds(148, 100, 150, 23);

        panelisi1.setBorder(null);
        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setLayout(null);

        TBC.setText("TBC");
        TBC.setName("TBC"); // NOI18N
        TBC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TBCActionPerformed(evt);
            }
        });
        panelisi1.add(TBC);
        TBC.setBounds(40, 30, 90, 17);

        Asthma.setText("Asthma");
        Asthma.setName("Asthma"); // NOI18N
        Asthma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AsthmaActionPerformed(evt);
            }
        });
        panelisi1.add(Asthma);
        Asthma.setBounds(40, 10, 90, 17);

        Hypertensi.setText("Hypertensi");
        Hypertensi.setName("Hypertensi"); // NOI18N
        Hypertensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HypertensiActionPerformed(evt);
            }
        });
        panelisi1.add(Hypertensi);
        Hypertensi.setBounds(40, 50, 90, 17);

        PenyakitGinjal.setText("Penyakit Ginjal");
        PenyakitGinjal.setName("PenyakitGinjal"); // NOI18N
        PenyakitGinjal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PenyakitGinjalActionPerformed(evt);
            }
        });
        panelisi1.add(PenyakitGinjal);
        PenyakitGinjal.setBounds(140, 30, 110, 17);

        PenyakitJantung.setText("Penyakit Jantung");
        PenyakitJantung.setName("PenyakitJantung"); // NOI18N
        PenyakitJantung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PenyakitJantungActionPerformed(evt);
            }
        });
        panelisi1.add(PenyakitJantung);
        PenyakitJantung.setBounds(140, 10, 104, 17);

        PenyakitThyroid.setText("Penyakit Thyroid");
        PenyakitThyroid.setName("PenyakitThyroid"); // NOI18N
        PenyakitThyroid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PenyakitThyroidActionPerformed(evt);
            }
        });
        panelisi1.add(PenyakitThyroid);
        PenyakitThyroid.setBounds(140, 50, 110, 17);

        Hepatitis.setText("Hepatitis");
        Hepatitis.setName("Hepatitis"); // NOI18N
        Hepatitis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HepatitisActionPerformed(evt);
            }
        });
        panelisi1.add(Hepatitis);
        Hepatitis.setBounds(270, 10, 63, 17);

        DiabetesMelitus.setText("Diabetes Melitus");
        DiabetesMelitus.setName("DiabetesMelitus"); // NOI18N
        DiabetesMelitus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiabetesMelitusActionPerformed(evt);
            }
        });
        panelisi1.add(DiabetesMelitus);
        DiabetesMelitus.setBounds(270, 30, 110, 17);

        PenyakitKelamin.setText("Penyakit Kulit/Kelamin");
        PenyakitKelamin.setName("PenyakitKelamin"); // NOI18N
        PenyakitKelamin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PenyakitKelaminActionPerformed(evt);
            }
        });
        panelisi1.add(PenyakitKelamin);
        PenyakitKelamin.setBounds(270, 50, 140, 17);

        Epilepsi.setText("Epilepsi");
        Epilepsi.setName("Epilepsi"); // NOI18N
        Epilepsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EpilepsiActionPerformed(evt);
            }
        });
        panelisi1.add(Epilepsi);
        Epilepsi.setBounds(410, 10, 70, 17);

        LainnyaInput.setHighlighter(null);
        LainnyaInput.setName("LainnyaInput"); // NOI18N
        panelisi1.add(LainnyaInput);
        LainnyaInput.setBounds(480, 30, 130, 23);

        label10.setText(" Lainnya :");
        label10.setName("label10"); // NOI18N
        panelisi1.add(label10);
        label10.setBounds(415, 30, 60, 20);

        FormInput.add(panelisi1);
        panelisi1.setBounds(890, 180, 650, 80);

        panelInputan.setBorder(null);
        panelInputan.setName("panelInputan"); // NOI18N
        panelInputan.setLayout(null);

        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("        2. Faktor Keturunan Gemelli :");
        jLabel74.setName("jLabel74"); // NOI18N
        panelInputan.add(jLabel74);
        jLabel74.setBounds(0, 10, 180, 23);

        Gamelli.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Ada" }));
        Gamelli.setName("Gamelli"); // NOI18N
        Gamelli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GamelliKeyPressed(evt);
            }
        });
        panelInputan.add(Gamelli);
        Gamelli.setBounds(180, 10, 150, 23);

        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel75.setText("        3. Ketergantungan               : ");
        jLabel75.setName("jLabel75"); // NOI18N
        panelInputan.add(jLabel75);
        jLabel75.setBounds(0, 40, 180, 23);

        Ketergantungan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Ketergantungan.setName("Ketergantungan"); // NOI18N
        Ketergantungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KetergantunganActionPerformed(evt);
            }
        });
        Ketergantungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetergantunganKeyPressed(evt);
            }
        });
        panelInputan.add(Ketergantungan);
        Ketergantungan.setBounds(180, 40, 150, 23);

        InputKetergantungan.setHighlighter(null);
        InputKetergantungan.setName("InputKetergantungan"); // NOI18N
        InputKetergantungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputKetergantunganActionPerformed(evt);
            }
        });
        panelInputan.add(InputKetergantungan);
        InputKetergantungan.setBounds(340, 40, 150, 23);

        KetergantunganSejak.setHighlighter(null);
        KetergantunganSejak.setName("KetergantunganSejak"); // NOI18N
        KetergantunganSejak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KetergantunganSejakActionPerformed(evt);
            }
        });
        panelInputan.add(KetergantunganSejak);
        KetergantunganSejak.setBounds(540, 40, 80, 23);

        jLabel76.setText("Sejak : ");
        jLabel76.setName("jLabel76"); // NOI18N
        panelInputan.add(jLabel76);
        jLabel76.setBounds(500, 40, 40, 23);

        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel77.setText("        4. Riwayat Alergi                  ");
        jLabel77.setName("jLabel77"); // NOI18N
        panelInputan.add(jLabel77);
        jLabel77.setBounds(0, 70, 180, 23);

        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText("   Obat - Obatan :");
        jLabel78.setName("jLabel78"); // NOI18N
        panelInputan.add(jLabel78);
        jLabel78.setBounds(30, 90, 90, 23);

        ObatObatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        ObatObatan.setName("ObatObatan"); // NOI18N
        ObatObatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ObatObatanActionPerformed(evt);
            }
        });
        ObatObatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObatObatanKeyPressed(evt);
            }
        });
        panelInputan.add(ObatObatan);
        ObatObatan.setBounds(125, 90, 150, 23);

        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("   Makanan         :");
        jLabel79.setName("jLabel79"); // NOI18N
        panelInputan.add(jLabel79);
        jLabel79.setBounds(30, 120, 90, 23);

        Makanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Makanan.setName("Makanan"); // NOI18N
        Makanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MakananActionPerformed(evt);
            }
        });
        Makanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MakananKeyPressed(evt);
            }
        });
        panelInputan.add(Makanan);
        Makanan.setBounds(125, 120, 150, 23);

        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("   Debu              :");
        jLabel80.setName("jLabel80"); // NOI18N
        panelInputan.add(jLabel80);
        jLabel80.setBounds(30, 150, 90, 23);

        Debu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Debu.setName("Debu"); // NOI18N
        Debu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DebuActionPerformed(evt);
            }
        });
        Debu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DebuKeyPressed(evt);
            }
        });
        panelInputan.add(Debu);
        Debu.setBounds(125, 150, 150, 23);

        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("   Lainnya           :");
        jLabel81.setName("jLabel81"); // NOI18N
        panelInputan.add(jLabel81);
        jLabel81.setBounds(30, 180, 90, 23);

        AlergiLainnya.setHighlighter(null);
        AlergiLainnya.setName("AlergiLainnya"); // NOI18N
        AlergiLainnya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlergiLainnyaActionPerformed(evt);
            }
        });
        panelInputan.add(AlergiLainnya);
        AlergiLainnya.setBounds(125, 180, 150, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        panelInputan.add(jSeparator1);
        jSeparator1.setBounds(0, 210, 880, 1);

        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel101.setText("II. RIWAYAT KEHAMILAN / PERSALINAN SEBELUMNYA");
        jLabel101.setName("jLabel101"); // NOI18N
        panelInputan.add(jLabel101);
        jLabel101.setBounds(10, 210, 280, 23);

        BtnTambahMasalah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahMasalah.setMnemonic('3');
        BtnTambahMasalah.setToolTipText("Alt+3");
        BtnTambahMasalah.setName("BtnTambahMasalah"); // NOI18N
        BtnTambahMasalah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambahMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahMasalahActionPerformed(evt);
            }
        });
        panelInputan.add(BtnTambahMasalah);
        BtnTambahMasalah.setBounds(30, 240, 28, 23);

        BtnHapusRiwayatPersalinan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusRiwayatPersalinan.setMnemonic('3');
        BtnHapusRiwayatPersalinan.setToolTipText("Alt+3");
        BtnHapusRiwayatPersalinan.setName("BtnHapusRiwayatPersalinan"); // NOI18N
        BtnHapusRiwayatPersalinan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusRiwayatPersalinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusRiwayatPersalinanActionPerformed(evt);
            }
        });
        panelInputan.add(BtnHapusRiwayatPersalinan);
        BtnHapusRiwayatPersalinan.setBounds(30, 270, 28, 23);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRiwayatKehamilan.setName("tbRiwayatKehamilan"); // NOI18N
        Scroll6.setViewportView(tbRiwayatKehamilan);

        panelInputan.add(Scroll6);
        Scroll6.setBounds(60, 240, 744, 93);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        panelInputan.add(jSeparator3);
        jSeparator3.setBounds(0, 340, 880, 1);

        jLabel129.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel129.setText("III. RIWAYAT OBSTETRI");
        jLabel129.setName("jLabel129"); // NOI18N
        panelInputan.add(jLabel129);
        jLabel129.setBounds(10, 340, 180, 23);

        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("   - Menarche                   :");
        jLabel82.setName("jLabel82"); // NOI18N
        panelInputan.add(jLabel82);
        jLabel82.setBounds(20, 360, 140, 23);

        Menarche.setHighlighter(null);
        Menarche.setName("Menarche"); // NOI18N
        Menarche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenarcheActionPerformed(evt);
            }
        });
        panelInputan.add(Menarche);
        Menarche.setBounds(160, 360, 70, 23);

        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel86.setText("Tahun");
        jLabel86.setName("jLabel86"); // NOI18N
        panelInputan.add(jLabel86);
        jLabel86.setBounds(230, 360, 50, 23);

        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText("- Menstruasi                 :");
        jLabel85.setName("jLabel85"); // NOI18N
        panelInputan.add(jLabel85);
        jLabel85.setBounds(30, 390, 130, 23);

        Menstruasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teratur", "Tidak Teratur" }));
        Menstruasi.setName("Menstruasi"); // NOI18N
        Menstruasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenstruasiActionPerformed(evt);
            }
        });
        Menstruasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MenstruasiKeyPressed(evt);
            }
        });
        panelInputan.add(Menstruasi);
        Menstruasi.setBounds(160, 390, 110, 23);

        HariMens.setHighlighter(null);
        HariMens.setName("HariMens"); // NOI18N
        HariMens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HariMensActionPerformed(evt);
            }
        });
        panelInputan.add(HariMens);
        HariMens.setBounds(310, 390, 140, 23);

        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("kali");
        jLabel84.setName("jLabel84"); // NOI18N
        panelInputan.add(jLabel84);
        jLabel84.setBounds(390, 480, 30, 23);

        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel87.setText("- Sakit Saat Menstruasi :");
        jLabel87.setName("jLabel87"); // NOI18N
        panelInputan.add(jLabel87);
        jLabel87.setBounds(30, 420, 130, 23);

        SakitSaatMens.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        SakitSaatMens.setName("SakitSaatMens"); // NOI18N
        SakitSaatMens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SakitSaatMensActionPerformed(evt);
            }
        });
        SakitSaatMens.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SakitSaatMensKeyPressed(evt);
            }
        });
        panelInputan.add(SakitSaatMens);
        SakitSaatMens.setBounds(160, 420, 110, 23);

        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel91.setText("   - Menikah yang ke        :");
        jLabel91.setName("jLabel91"); // NOI18N
        panelInputan.add(jLabel91);
        jLabel91.setBounds(20, 450, 140, 23);

        Menikahke.setHighlighter(null);
        Menikahke.setName("Menikahke"); // NOI18N
        Menikahke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenikahkeActionPerformed(evt);
            }
        });
        panelInputan.add(Menikahke);
        Menikahke.setBounds(160, 450, 110, 23);

        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel89.setText("   Lamanya pernikahan :");
        jLabel89.setName("jLabel89"); // NOI18N
        panelInputan.add(jLabel89);
        jLabel89.setBounds(270, 450, 130, 23);

        LamanyaMenikah.setHighlighter(null);
        LamanyaMenikah.setName("LamanyaMenikah"); // NOI18N
        LamanyaMenikah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LamanyaMenikahActionPerformed(evt);
            }
        });
        panelInputan.add(LamanyaMenikah);
        LamanyaMenikah.setBounds(390, 450, 110, 23);

        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel96.setText("   - Kontrasepsi yang pernah digunakan :");
        jLabel96.setName("jLabel96"); // NOI18N
        panelInputan.add(jLabel96);
        jLabel96.setBounds(20, 480, 210, 23);

        NmKontrasepsi.setHighlighter(null);
        NmKontrasepsi.setName("NmKontrasepsi"); // NOI18N
        NmKontrasepsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmKontrasepsiActionPerformed(evt);
            }
        });
        panelInputan.add(NmKontrasepsi);
        NmKontrasepsi.setBounds(230, 480, 160, 23);

        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("   Lamanya :");
        jLabel92.setName("jLabel92"); // NOI18N
        panelInputan.add(jLabel92);
        jLabel92.setBounds(420, 480, 70, 23);

        KontrasepsiLamanya.setHighlighter(null);
        KontrasepsiLamanya.setName("KontrasepsiLamanya"); // NOI18N
        KontrasepsiLamanya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KontrasepsiLamanyaActionPerformed(evt);
            }
        });
        panelInputan.add(KontrasepsiLamanya);
        KontrasepsiLamanya.setBounds(490, 480, 110, 23);

        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("IV. DATA KEHAMILAN SEKARANG");
        jLabel131.setName("jLabel131"); // NOI18N
        panelInputan.add(jLabel131);
        jLabel131.setBounds(10, 510, 180, 23);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        panelInputan.add(jSeparator4);
        jSeparator4.setBounds(0, 510, 880, 1);

        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel88.setText("- G");
        jLabel88.setName("jLabel88"); // NOI18N
        panelInputan.add(jLabel88);
        jLabel88.setBounds(30, 540, 20, 23);

        InputG.setHighlighter(null);
        InputG.setName("InputG"); // NOI18N
        InputG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputGActionPerformed(evt);
            }
        });
        panelInputan.add(InputG);
        InputG.setBounds(48, 540, 40, 23);

        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("P");
        jLabel93.setName("jLabel93"); // NOI18N
        panelInputan.add(jLabel93);
        jLabel93.setBounds(100, 540, 20, 23);

        InputP.setHighlighter(null);
        InputP.setName("InputP"); // NOI18N
        InputP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputPActionPerformed(evt);
            }
        });
        panelInputan.add(InputP);
        InputP.setBounds(109, 540, 40, 23);

        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("A");
        jLabel95.setName("jLabel95"); // NOI18N
        panelInputan.add(jLabel95);
        jLabel95.setBounds(160, 540, 20, 23);

        InputA.setHighlighter(null);
        InputA.setName("InputA"); // NOI18N
        InputA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputAActionPerformed(evt);
            }
        });
        panelInputan.add(InputA);
        InputA.setBounds(170, 540, 40, 23);

        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel90.setText("   - Hari pertama haid terakhir :");
        jLabel90.setName("jLabel90"); // NOI18N
        panelInputan.add(jLabel90);
        jLabel90.setBounds(20, 570, 160, 23);

        HPHT.setForeground(new java.awt.Color(50, 70, 50));
        HPHT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2023" }));
        HPHT.setDisplayFormat("dd-MM-yyyy");
        HPHT.setName("HPHT"); // NOI18N
        HPHT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                HPHTItemStateChanged(evt);
            }
        });
        HPHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HPHTActionPerformed(evt);
            }
        });
        panelInputan.add(HPHT);
        HPHT.setBounds(180, 570, 90, 22);

        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel97.setText("   - Hari perkiraan lahir            :");
        jLabel97.setName("jLabel97"); // NOI18N
        panelInputan.add(jLabel97);
        jLabel97.setBounds(20, 600, 160, 23);

        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("   - Umur kehamilan                :");
        jLabel102.setName("jLabel102"); // NOI18N
        panelInputan.add(jLabel102);
        jLabel102.setBounds(20, 630, 160, 23);

        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("   - Keluhan yang dirasakan selama kehamilan saat ini :");
        jLabel103.setName("jLabel103"); // NOI18N
        panelInputan.add(jLabel103);
        jLabel103.setBounds(20, 660, 280, 23);

        scrollPane1.setName("scrollPane1"); // NOI18N

        Keluhan.setColumns(20);
        Keluhan.setRows(5);
        Keluhan.setName("Keluhan"); // NOI18N
        scrollPane1.setViewportView(Keluhan);

        panelInputan.add(scrollPane1);
        scrollPane1.setBounds(300, 670, 290, 60);

        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("   - Palpasi");
        jLabel100.setName("jLabel100"); // NOI18N
        panelInputan.add(jLabel100);
        jLabel100.setBounds(20, 740, 160, 23);

        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel104.setText("      1. Tinggi fundus uteri     :");
        jLabel104.setName("jLabel104"); // NOI18N
        panelInputan.add(jLabel104);
        jLabel104.setBounds(20, 760, 150, 23);

        TinggiFundus.setHighlighter(null);
        TinggiFundus.setName("TinggiFundus"); // NOI18N
        TinggiFundus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TinggiFundusActionPerformed(evt);
            }
        });
        panelInputan.add(TinggiFundus);
        TinggiFundus.setBounds(170, 760, 70, 23);

        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel117.setText("cm");
        jLabel117.setName("jLabel117"); // NOI18N
        panelInputan.add(jLabel117);
        jLabel117.setBounds(240, 760, 50, 23);

        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("      2. Letak punggung janin :");
        jLabel113.setName("jLabel113"); // NOI18N
        panelInputan.add(jLabel113);
        jLabel113.setBounds(20, 790, 150, 23);

        LetakPunggungJanin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Punggung kiri", "Punggung kanan", "Kepala", "Bokong" }));
        LetakPunggungJanin.setName("LetakPunggungJanin"); // NOI18N
        LetakPunggungJanin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LetakPunggungJaninActionPerformed(evt);
            }
        });
        LetakPunggungJanin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LetakPunggungJaninKeyPressed(evt);
            }
        });
        panelInputan.add(LetakPunggungJanin);
        LetakPunggungJanin.setBounds(170, 790, 110, 23);

        PresentasiJanin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kepala", "Bokong", "Kosong" }));
        PresentasiJanin.setName("PresentasiJanin"); // NOI18N
        PresentasiJanin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PresentasiJaninKeyPressed(evt);
            }
        });
        panelInputan.add(PresentasiJanin);
        PresentasiJanin.setBounds(170, 820, 110, 23);

        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel114.setText("      3. Presentasi janin         :");
        jLabel114.setName("jLabel114"); // NOI18N
        panelInputan.add(jLabel114);
        jLabel114.setBounds(20, 820, 150, 23);

        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel115.setText("      4. Taksiran berat janin   :");
        jLabel115.setName("jLabel115"); // NOI18N
        panelInputan.add(jLabel115);
        jLabel115.setBounds(20, 850, 150, 23);

        BeratJanin.setHighlighter(null);
        BeratJanin.setName("BeratJanin"); // NOI18N
        BeratJanin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BeratJaninActionPerformed(evt);
            }
        });
        panelInputan.add(BeratJanin);
        BeratJanin.setBounds(170, 850, 70, 23);

        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel118.setText("gram");
        jLabel118.setName("jLabel118"); // NOI18N
        panelInputan.add(jLabel118);
        jLabel118.setBounds(240, 850, 50, 23);

        Penurunan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hodge 1", "Hodge 2", "Hodge 3", "Hodge 4" }));
        Penurunan.setName("Penurunan"); // NOI18N
        Penurunan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PenurunanKeyPressed(evt);
            }
        });
        panelInputan.add(Penurunan);
        Penurunan.setBounds(170, 880, 110, 23);

        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("      5. Penurunan                 :");
        jLabel116.setName("jLabel116"); // NOI18N
        panelInputan.add(jLabel116);
        jLabel116.setBounds(20, 880, 150, 23);

        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel120.setText("      6. Aukultasi (DJJ)          :");
        jLabel120.setName("jLabel120"); // NOI18N
        panelInputan.add(jLabel120);
        jLabel120.setBounds(20, 910, 150, 23);

        Aukultasi.setHighlighter(null);
        Aukultasi.setName("Aukultasi"); // NOI18N
        Aukultasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AukultasiActionPerformed(evt);
            }
        });
        panelInputan.add(Aukultasi);
        Aukultasi.setBounds(170, 910, 70, 23);

        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel119.setText("x/menit");
        jLabel119.setName("jLabel119"); // NOI18N
        panelInputan.add(jLabel119);
        jLabel119.setBounds(240, 910, 40, 23);

        FrekAukultasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Teratur", "Teratur" }));
        FrekAukultasi.setName("FrekAukultasi"); // NOI18N
        FrekAukultasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FrekAukultasiKeyPressed(evt);
            }
        });
        panelInputan.add(FrekAukultasi);
        FrekAukultasi.setBounds(285, 910, 110, 23);

        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("      7. Pemeriksaan dalam    :");
        jLabel98.setName("jLabel98"); // NOI18N
        panelInputan.add(jLabel98);
        jLabel98.setBounds(20, 940, 150, 23);

        scrollPane2.setName("scrollPane2"); // NOI18N

        PemeriksaanDalam.setColumns(20);
        PemeriksaanDalam.setRows(5);
        PemeriksaanDalam.setName("PemeriksaanDalam"); // NOI18N
        scrollPane2.setViewportView(PemeriksaanDalam);

        panelInputan.add(scrollPane2);
        scrollPane2.setBounds(170, 940, 330, 50);

        jLabel153.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel153.setText("V. Pemeriksaan Fisik");
        jLabel153.setName("jLabel153"); // NOI18N
        panelInputan.add(jLabel153);
        jLabel153.setBounds(10, 1000, 490, 23);

        jLabel22.setText("TD :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelInputan.add(jLabel22);
        jLabel22.setBounds(0, 1030, 50, 23);

        TD.setFocusTraversalPolicyProvider(true);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        panelInputan.add(TD);
        TD.setBounds(55, 1030, 76, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("mmHg");
        jLabel23.setName("jLabel23"); // NOI18N
        panelInputan.add(jLabel23);
        jLabel23.setBounds(132, 1030, 50, 23);

        jLabel17.setText("Nadi :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelInputan.add(jLabel17);
        jLabel17.setBounds(180, 1030, 40, 23);

        Nadi.setFocusTraversalPolicyProvider(true);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        panelInputan.add(Nadi);
        Nadi.setBounds(225, 1030, 45, 23);

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("x/menit");
        jLabel16.setName("jLabel16"); // NOI18N
        panelInputan.add(jLabel16);
        jLabel16.setBounds(270, 1030, 50, 23);

        jLabel26.setText("RR :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelInputan.add(jLabel26);
        jLabel26.setBounds(310, 1030, 40, 23);

        RR.setFocusTraversalPolicyProvider(true);
        RR.setName("RR"); // NOI18N
        RR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RRKeyPressed(evt);
            }
        });
        panelInputan.add(RR);
        RR.setBounds(355, 1030, 45, 23);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("x/menit");
        jLabel25.setName("jLabel25"); // NOI18N
        panelInputan.add(jLabel25);
        jLabel25.setBounds(400, 1030, 50, 23);

        jLabel18.setText("Suhu :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelInputan.add(jLabel18);
        jLabel18.setBounds(450, 1030, 40, 23);

        Suhu.setFocusTraversalPolicyProvider(true);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        panelInputan.add(Suhu);
        Suhu.setBounds(495, 1030, 45, 23);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("°C");
        jLabel20.setName("jLabel20"); // NOI18N
        panelInputan.add(jLabel20);
        jLabel20.setBounds(540, 1030, 30, 23);

        jLabel15.setText("TB :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelInputan.add(jLabel15);
        jLabel15.setBounds(570, 1030, 30, 23);

        TB.setFocusTraversalPolicyProvider(true);
        TB.setName("TB"); // NOI18N
        TB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBKeyPressed(evt);
            }
        });
        panelInputan.add(TB);
        TB.setBounds(605, 1030, 45, 23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText(" cm");
        jLabel24.setName("jLabel24"); // NOI18N
        panelInputan.add(jLabel24);
        jLabel24.setBounds(647, 1030, 30, 23);

        jLabel12.setText("BB :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelInputan.add(jLabel12);
        jLabel12.setBounds(680, 1030, 30, 23);

        BB.setFocusTraversalPolicyProvider(true);
        BB.setName("BB"); // NOI18N
        BB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BBKeyPressed(evt);
            }
        });
        panelInputan.add(BB);
        BB.setBounds(713, 1030, 45, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Kg");
        jLabel13.setName("jLabel13"); // NOI18N
        panelInputan.add(jLabel13);
        jLabel13.setBounds(758, 1030, 30, 23);

        jLabel41.setText("Keadaan Umum :");
        jLabel41.setName("jLabel41"); // NOI18N
        panelInputan.add(jLabel41);
        jLabel41.setBounds(0, 1060, 127, 23);

        Keadaan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sehat", "Sakit Ringan", "Sakit Sedang", "Sakit Berat" }));
        Keadaan1.setName("Keadaan1"); // NOI18N
        Keadaan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Keadaan1KeyPressed(evt);
            }
        });
        panelInputan.add(Keadaan1);
        Keadaan1.setBounds(130, 1060, 120, 23);

        jLabel28.setText("GCS(E,V,M) :");
        jLabel28.setName("jLabel28"); // NOI18N
        panelInputan.add(jLabel28);
        jLabel28.setBounds(260, 1060, 70, 23);

        GCS.setFocusTraversalPolicyProvider(true);
        GCS.setName("GCS"); // NOI18N
        GCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCSKeyPressed(evt);
            }
        });
        panelInputan.add(GCS);
        GCS.setBounds(340, 1060, 60, 23);

        jLabel39.setText("Kesadaran :");
        jLabel39.setName("jLabel39"); // NOI18N
        panelInputan.add(jLabel39);
        jLabel39.setBounds(400, 1060, 70, 23);

        Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Penurunan Kesadaran" }));
        Kesadaran.setName("Kesadaran"); // NOI18N
        Kesadaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KesadaranActionPerformed(evt);
            }
        });
        Kesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranKeyPressed(evt);
            }
        });
        panelInputan.add(Kesadaran);
        Kesadaran.setBounds(480, 1060, 130, 23);

        jLabel40.setText("Kepala :");
        jLabel40.setName("jLabel40"); // NOI18N
        panelInputan.add(jLabel40);
        jLabel40.setBounds(0, 1090, 127, 23);

        Kepala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Kepala.setName("Kepala"); // NOI18N
        Kepala.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KepalaKeyPressed(evt);
            }
        });
        panelInputan.add(Kepala);
        Kepala.setBounds(130, 1090, 120, 23);

        jLabel53.setText("Leher :");
        jLabel53.setName("jLabel53"); // NOI18N
        panelInputan.add(jLabel53);
        jLabel53.setBounds(270, 1090, 95, 23);

        Leher.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Leher.setName("Leher"); // NOI18N
        Leher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LeherKeyPressed(evt);
            }
        });
        panelInputan.add(Leher);
        Leher.setBounds(370, 1090, 128, 23);

        jLabel42.setText("Mata :");
        jLabel42.setName("jLabel42"); // NOI18N
        panelInputan.add(jLabel42);
        jLabel42.setBounds(0, 1120, 127, 23);

        Mata.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Mata.setName("Mata"); // NOI18N
        Mata.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MataKeyPressed(evt);
            }
        });
        panelInputan.add(Mata);
        Mata.setBounds(130, 1120, 120, 23);

        jLabel49.setText("Thoraks :");
        jLabel49.setName("jLabel49"); // NOI18N
        panelInputan.add(jLabel49);
        jLabel49.setBounds(270, 1120, 95, 23);

        Thoraks.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Thoraks.setName("Thoraks"); // NOI18N
        Thoraks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThoraksKeyPressed(evt);
            }
        });
        panelInputan.add(Thoraks);
        Thoraks.setBounds(370, 1120, 128, 23);

        Jantung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Jantung.setName("Jantung"); // NOI18N
        Jantung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JantungKeyPressed(evt);
            }
        });
        panelInputan.add(Jantung);
        Jantung.setBounds(370, 1150, 128, 23);

        jLabel50.setText("Jantung :");
        jLabel50.setName("jLabel50"); // NOI18N
        panelInputan.add(jLabel50);
        jLabel50.setBounds(270, 1150, 95, 23);

        Hidung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Hidung.setName("Hidung"); // NOI18N
        Hidung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HidungKeyPressed(evt);
            }
        });
        panelInputan.add(Hidung);
        Hidung.setBounds(130, 1150, 120, 23);

        jLabel44.setText("Hidung :");
        jLabel44.setName("jLabel44"); // NOI18N
        panelInputan.add(jLabel44);
        jLabel44.setBounds(0, 1150, 127, 23);

        jLabel45.setText("Gigi & Mulut :");
        jLabel45.setName("jLabel45"); // NOI18N
        panelInputan.add(jLabel45);
        jLabel45.setBounds(0, 1180, 127, 23);

        GigiMulut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        GigiMulut.setName("GigiMulut"); // NOI18N
        GigiMulut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GigiMulutKeyPressed(evt);
            }
        });
        panelInputan.add(GigiMulut);
        GigiMulut.setBounds(130, 1180, 120, 23);

        jLabel51.setText("Paru :");
        jLabel51.setName("jLabel51"); // NOI18N
        panelInputan.add(jLabel51);
        jLabel51.setBounds(270, 1180, 95, 23);

        Paru.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Paru.setName("Paru"); // NOI18N
        Paru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ParuKeyPressed(evt);
            }
        });
        panelInputan.add(Paru);
        Paru.setBounds(370, 1180, 128, 23);

        Abdomen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Abdomen.setName("Abdomen"); // NOI18N
        Abdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AbdomenKeyPressed(evt);
            }
        });
        panelInputan.add(Abdomen);
        Abdomen.setBounds(370, 1210, 128, 23);

        jLabel52.setText("Abdomen :");
        jLabel52.setName("jLabel52"); // NOI18N
        panelInputan.add(jLabel52);
        jLabel52.setBounds(270, 1210, 95, 23);

        Tenggorokan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Tenggorokan.setName("Tenggorokan"); // NOI18N
        Tenggorokan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TenggorokanKeyPressed(evt);
            }
        });
        panelInputan.add(Tenggorokan);
        Tenggorokan.setBounds(130, 1210, 120, 23);

        jLabel46.setText("Tenggorokan :");
        jLabel46.setName("jLabel46"); // NOI18N
        panelInputan.add(jLabel46);
        jLabel46.setBounds(0, 1210, 127, 23);

        jLabel47.setText("Telinga :");
        jLabel47.setName("jLabel47"); // NOI18N
        panelInputan.add(jLabel47);
        jLabel47.setBounds(0, 1240, 127, 23);

        Telinga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Telinga.setName("Telinga"); // NOI18N
        Telinga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TelingaKeyPressed(evt);
            }
        });
        panelInputan.add(Telinga);
        Telinga.setBounds(130, 1240, 120, 23);

        jLabel54.setText("Genitalia & Anus :");
        jLabel54.setName("jLabel54"); // NOI18N
        panelInputan.add(jLabel54);
        jLabel54.setBounds(270, 1240, 95, 23);

        Anus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Anus.setName("Anus"); // NOI18N
        Anus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AnusKeyPressed(evt);
            }
        });
        panelInputan.add(Anus);
        Anus.setBounds(370, 1240, 128, 23);

        jLabel48.setText("Ekstremitas :");
        jLabel48.setName("jLabel48"); // NOI18N
        panelInputan.add(jLabel48);
        jLabel48.setBounds(0, 1270, 127, 23);

        Ekstremitas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Abnormal" }));
        Ekstremitas.setName("Ekstremitas"); // NOI18N
        Ekstremitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EkstremitasKeyPressed(evt);
            }
        });
        panelInputan.add(Ekstremitas);
        Ekstremitas.setBounds(130, 1270, 120, 23);

        jLabel167.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel167.setText("VI. PENILAIAN TINGKAT NYERI");
        jLabel167.setName("jLabel167"); // NOI18N
        panelInputan.add(jLabel167);
        jLabel167.setBounds(10, 1300, 380, 23);

        jSeparator10.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator10.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator10.setName("jSeparator10"); // NOI18N
        panelInputan.add(jSeparator10);
        jSeparator10.setBounds(0, 1300, 880, 1);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        panelInputan.add(PanelWall);
        PanelWall.setBounds(30, 1330, 460, 190);

        Nyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        Nyeri.setName("Nyeri"); // NOI18N
        Nyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NyeriActionPerformed(evt);
            }
        });
        panelInputan.add(Nyeri);
        Nyeri.setBounds(95, 1540, 70, 20);

        label2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label2.setText("Nyeri :");
        label2.setName("label2"); // NOI18N
        panelInputan.add(label2);
        label2.setBounds(60, 1540, 40, 20);

        label1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label1.setText("Skor :");
        label1.setName("label1"); // NOI18N
        panelInputan.add(label1);
        label1.setBounds(180, 1540, 40, 20);

        SkorNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        SkorNyeri.setName("SkorNyeri"); // NOI18N
        panelInputan.add(SkorNyeri);
        SkorNyeri.setBounds(213, 1540, 46, 20);

        label3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label3.setText("Kategori :");
        label3.setName("label3"); // NOI18N
        panelInputan.add(label3);
        label3.setBounds(270, 1540, 60, 20);

        KategoriNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ringan", "Sedang", "Berat" }));
        KategoriNyeri.setName("KategoriNyeri"); // NOI18N
        panelInputan.add(KategoriNyeri);
        KategoriNyeri.setBounds(323, 1540, 80, 20);

        PengaruhNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidur", "Aktivitas Fisik", "Emosi", "Nafsu Makan", "Konsentrasi" }));
        PengaruhNyeri.setName("PengaruhNyeri"); // NOI18N
        panelInputan.add(PengaruhNyeri);
        PengaruhNyeri.setBounds(173, 1570, 170, 20);

        label4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label4.setText("Nyeri mempengaruhi :");
        label4.setName("label4"); // NOI18N
        panelInputan.add(label4);
        label4.setBounds(60, 1570, 110, 20);

        jLabel202.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel202.setText("VII. PEMERIKSAAN PENUNJANG TERAKHIR");
        jLabel202.setName("jLabel202"); // NOI18N
        panelInputan.add(jLabel202);
        jLabel202.setBounds(10, 1600, 380, 23);

        jSeparator11.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator11.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator11.setName("jSeparator11"); // NOI18N
        panelInputan.add(jSeparator11);
        jSeparator11.setBounds(0, 1600, 880, 1);

        label5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label5.setText("           - HB           :");
        label5.setName("label5"); // NOI18N
        panelInputan.add(label5);
        label5.setBounds(0, 1630, 100, 20);

        HB.setName("HB"); // NOI18N
        HB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HBActionPerformed(evt);
            }
        });
        panelInputan.add(HB);
        HB.setBounds(105, 1630, 60, 24);

        label6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label6.setText("           - Hasil USG :");
        label6.setName("label6"); // NOI18N
        panelInputan.add(label6);
        label6.setBounds(0, 1660, 100, 20);

        scrollPane3.setName("scrollPane3"); // NOI18N

        HasilUSG.setColumns(20);
        HasilUSG.setRows(5);
        HasilUSG.setName("HasilUSG"); // NOI18N
        scrollPane3.setViewportView(HasilUSG);

        panelInputan.add(scrollPane3);
        scrollPane3.setBounds(105, 1660, 300, 50);

        jSeparator12.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator12.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator12.setName("jSeparator12"); // NOI18N
        panelInputan.add(jSeparator12);
        jSeparator12.setBounds(0, 1720, 880, 1);

        jLabel203.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel203.setText("VIII. STATUS MENTAL");
        jLabel203.setName("jLabel203"); // NOI18N
        panelInputan.add(jLabel203);
        jLabel203.setBounds(10, 1720, 380, 23);

        panelisi2.setBorder(null);
        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setLayout(null);

        Disorientasi.setText("Disorientasi orang/tempat/waktu");
        Disorientasi.setName("Disorientasi"); // NOI18N
        panelisi2.add(Disorientasi);
        Disorientasi.setBounds(10, 30, 190, 17);

        Orientasi.setText("Orientasi");
        Orientasi.setName("Orientasi"); // NOI18N
        Orientasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrientasiActionPerformed(evt);
            }
        });
        panelisi2.add(Orientasi);
        Orientasi.setBounds(10, 10, 70, 17);

        Agitasi.setText("Agitasi");
        Agitasi.setName("Agitasi"); // NOI18N
        panelisi2.add(Agitasi);
        Agitasi.setBounds(220, 30, 61, 17);

        cekBox2.setText("Latergi");
        cekBox2.setName("cekBox2"); // NOI18N
        panelisi2.add(cekBox2);
        cekBox2.setBounds(360, 30, 61, 17);

        Kooperatif.setText("Kooperatif");
        Kooperatif.setName("Kooperatif"); // NOI18N
        Kooperatif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KooperatifActionPerformed(evt);
            }
        });
        panelisi2.add(Kooperatif);
        Kooperatif.setBounds(220, 10, 80, 17);

        TidakRespon.setText("Tidak ada respon");
        TidakRespon.setName("TidakRespon"); // NOI18N
        panelisi2.add(TidakRespon);
        TidakRespon.setBounds(360, 10, 110, 17);

        panelInputan.add(panelisi2);
        panelisi2.setBounds(20, 1745, 490, 60);

        jLabel204.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel204.setText("IX. DATA PSIKOSOSIAL");
        jLabel204.setName("jLabel204"); // NOI18N
        panelInputan.add(jLabel204);
        jLabel204.setBounds(10, 1810, 380, 23);

        jSeparator13.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator13.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator13.setName("jSeparator13"); // NOI18N
        panelInputan.add(jSeparator13);
        jSeparator13.setBounds(0, 1810, 880, 1);

        label8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label8.setText("        - Respon Emosi :");
        label8.setName("label8"); // NOI18N
        panelInputan.add(label8);
        label8.setBounds(0, 1830, 120, 20);

        ResEmosi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Senang", "Tenang", "Sedih", "Tegang", "Takut" }));
        ResEmosi.setName("ResEmosi"); // NOI18N
        panelInputan.add(ResEmosi);
        ResEmosi.setBounds(120, 1830, 100, 20);

        label9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label9.setText("        - Support suami/keluarga :");
        label9.setName("label9"); // NOI18N
        panelInputan.add(label9);
        label9.setBounds(0, 1860, 170, 20);

        SupportSuami.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sangat mendukung", "Mendukung", "Kurang mendukung" }));
        SupportSuami.setName("SupportSuami"); // NOI18N
        SupportSuami.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupportSuamiActionPerformed(evt);
            }
        });
        panelInputan.add(SupportSuami);
        SupportSuami.setBounds(170, 1860, 140, 20);

        jLabel205.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel205.setText("X. DAFTAR MASALAH KEBIDANAN");
        jLabel205.setName("jLabel205"); // NOI18N
        panelInputan.add(jLabel205);
        jLabel205.setBounds(10, 1890, 380, 23);

        panelisi3.setBorder(null);
        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setLayout(null);

        RisikoPendarahan.setText("Risiko perdarahan");
        RisikoPendarahan.setName("RisikoPendarahan"); // NOI18N
        RisikoPendarahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RisikoPendarahanActionPerformed(evt);
            }
        });
        panelisi3.add(RisikoPendarahan);
        RisikoPendarahan.setBounds(80, 30, 110, 17);

        nyeri.setText("Nyeri");
        nyeri.setName("nyeri"); // NOI18N
        nyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nyeriActionPerformed(evt);
            }
        });
        panelisi3.add(nyeri);
        nyeri.setBounds(10, 10, 60, 17);

        cemas.setText("Cemas");
        cemas.setName("cemas"); // NOI18N
        cemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cemasActionPerformed(evt);
            }
        });
        panelisi3.add(cemas);
        cemas.setBounds(10, 30, 60, 17);

        PerubahanNutrisi.setText("Perubahan nutrisi");
        PerubahanNutrisi.setName("PerubahanNutrisi"); // NOI18N
        PerubahanNutrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PerubahanNutrisiActionPerformed(evt);
            }
        });
        panelisi3.add(PerubahanNutrisi);
        PerubahanNutrisi.setBounds(80, 10, 110, 17);

        cekBox6.setText("Perubahan nutrisi");
        cekBox6.setName("cekBox6"); // NOI18N
        cekBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekBox6ActionPerformed(evt);
            }
        });
        panelisi3.add(cekBox6);
        cekBox6.setBounds(80, 10, 110, 17);

        eliminasi.setText("Eliminasi");
        eliminasi.setName("eliminasi"); // NOI18N
        eliminasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminasiActionPerformed(evt);
            }
        });
        panelisi3.add(eliminasi);
        eliminasi.setBounds(200, 10, 70, 17);

        PengetahuanKomunikasiInformasi.setText("Pengetahuan/Komunikasi/Informasi");
        PengetahuanKomunikasiInformasi.setName("PengetahuanKomunikasiInformasi"); // NOI18N
        PengetahuanKomunikasiInformasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PengetahuanKomunikasiInformasiActionPerformed(evt);
            }
        });
        panelisi3.add(PengetahuanKomunikasiInformasi);
        PengetahuanKomunikasiInformasi.setBounds(200, 30, 200, 17);

        label7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label7.setText("Lainnya :");
        label7.setName("label7"); // NOI18N
        panelisi3.add(label7);
        label7.setBounds(420, 10, 50, 20);

        MasalahKebidananLainnya.setName("MasalahKebidananLainnya"); // NOI18N
        panelisi3.add(MasalahKebidananLainnya);
        MasalahKebidananLainnya.setBounds(470, 10, 150, 24);

        panelInputan.add(panelisi3);
        panelisi3.setBounds(20, 1910, 630, 60);

        jLabel201.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel201.setText("XI. DIAGNOSA KEBIDANAN");
        jLabel201.setName("jLabel201"); // NOI18N
        panelInputan.add(jLabel201);
        jLabel201.setBounds(10, 1980, 380, 23);

        scrollPane4.setName("scrollPane4"); // NOI18N

        DiagnosaKebidanan.setColumns(20);
        DiagnosaKebidanan.setRows(5);
        DiagnosaKebidanan.setName("DiagnosaKebidanan"); // NOI18N
        scrollPane4.setViewportView(DiagnosaKebidanan);

        panelInputan.add(scrollPane4);
        scrollPane4.setBounds(10, 2005, 400, 70);

        jSeparator14.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator14.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator14.setName("jSeparator14"); // NOI18N
        panelInputan.add(jSeparator14);
        jSeparator14.setBounds(0, 1890, 880, 1);

        jSeparator15.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator15.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator15.setName("jSeparator15"); // NOI18N
        panelInputan.add(jSeparator15);
        jSeparator15.setBounds(0, 1980, 880, 1);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        panelInputan.add(jSeparator5);
        jSeparator5.setBounds(0, 1000, 880, 1);

        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel121.setText("Hari");
        jLabel121.setName("jLabel121"); // NOI18N
        panelInputan.add(jLabel121);
        jLabel121.setBounds(450, 390, 50, 23);

        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("Siklus");
        jLabel122.setName("jLabel122"); // NOI18N
        panelInputan.add(jLabel122);
        jLabel122.setBounds(280, 390, 30, 23);

        InputDebu.setName("InputDebu"); // NOI18N
        InputDebu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputDebuActionPerformed(evt);
            }
        });
        panelInputan.add(InputDebu);
        InputDebu.setBounds(280, 150, 180, 24);

        InputObat.setName("InputObat"); // NOI18N
        InputObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputObatActionPerformed(evt);
            }
        });
        panelInputan.add(InputObat);
        InputObat.setBounds(280, 90, 180, 24);

        InputMakanan.setName("InputMakanan"); // NOI18N
        InputMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputMakananActionPerformed(evt);
            }
        });
        panelInputan.add(InputMakanan);
        InputMakanan.setBounds(280, 120, 180, 24);

        InputPenurunanKesadaran.setName("InputPenurunanKesadaran"); // NOI18N
        InputPenurunanKesadaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputPenurunanKesadaranActionPerformed(evt);
            }
        });
        panelInputan.add(InputPenurunanKesadaran);
        InputPenurunanKesadaran.setBounds(620, 1060, 130, 24);

        HPL.setHighlighter(null);
        HPL.setName("HPL"); // NOI18N
        HPL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HPLActionPerformed(evt);
            }
        });
        panelInputan.add(HPL);
        HPL.setBounds(180, 600, 120, 23);

        UmurKehamilan.setHighlighter(null);
        UmurKehamilan.setName("UmurKehamilan"); // NOI18N
        UmurKehamilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UmurKehamilanActionPerformed(evt);
            }
        });
        panelInputan.add(UmurKehamilan);
        UmurKehamilan.setBounds(180, 630, 120, 23);

        BtnEditRiwayatPersalinan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEditRiwayatPersalinan.setMnemonic('3');
        BtnEditRiwayatPersalinan.setToolTipText("Alt+3");
        BtnEditRiwayatPersalinan.setName("BtnEditRiwayatPersalinan"); // NOI18N
        BtnEditRiwayatPersalinan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnEditRiwayatPersalinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditRiwayatPersalinanActionPerformed(evt);
            }
        });
        panelInputan.add(BtnEditRiwayatPersalinan);
        BtnEditRiwayatPersalinan.setBounds(30, 298, 28, 23);

        FormInput.add(panelInputan);
        panelInputan.setBounds(0, 180, 880, 2100);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Penilaian", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-09-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-09-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(470, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

        ChkAccor.setBackground(new java.awt.Color(255, 250, 248));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelected(true);
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(255, 255, 255));
        FormMenu.setBorder(null);
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(115, 43));
        FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel34.setText("Pasien :");
        jLabel34.setName("jLabel34"); // NOI18N
        jLabel34.setPreferredSize(new java.awt.Dimension(55, 23));
        FormMenu.add(jLabel34);

        TNoRM1.setEditable(false);
        TNoRM1.setHighlighter(null);
        TNoRM1.setName("TNoRM1"); // NOI18N
        TNoRM1.setPreferredSize(new java.awt.Dimension(100, 23));
        FormMenu.add(TNoRM1);

        TPasien1.setEditable(false);
        TPasien1.setBackground(new java.awt.Color(245, 250, 240));
        TPasien1.setHighlighter(null);
        TPasien1.setName("TPasien1"); // NOI18N
        TPasien1.setPreferredSize(new java.awt.Dimension(250, 23));
        FormMenu.add(TPasien1);

        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/item (copy).png"))); // NOI18N
        BtnPrint1.setMnemonic('T');
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPrint1);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.NORTH);

        FormMasalahRencana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)));
        FormMasalahRencana.setName("FormMasalahRencana"); // NOI18N
        FormMasalahRencana.setLayout(new java.awt.GridLayout(3, 0, 1, 1));

        scrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Riwayat Persalinan :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane9.setName("scrollPane9"); // NOI18N

        tbRiwayatKehamilan1.setName("tbRiwayatKehamilan1"); // NOI18N
        scrollPane9.setViewportView(tbRiwayatKehamilan1);

        FormMasalahRencana.add(scrollPane9);

        scrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Diagnosa Kebidanan :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane7.setName("scrollPane7"); // NOI18N

        DiagnosaKebidanan1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        DiagnosaKebidanan1.setColumns(20);
        DiagnosaKebidanan1.setRows(5);
        DiagnosaKebidanan1.setName("DiagnosaKebidanan1"); // NOI18N
        DiagnosaKebidanan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKebidanan1KeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(DiagnosaKebidanan1);

        FormMasalahRencana.add(scrollPane7);

        PanelAccor.add(FormMasalahRencana, java.awt.BorderLayout.CENTER);

        internalFrame3.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Data Penilaian", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleName("::[ Asesmen Awal Kebidanan IGD ]::");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //tampilMasalah();
    }//GEN-LAST:event_formWindowOpened

    private void BtnKeluarKehamilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarKehamilanActionPerformed
        DlgRiwayatPersalinan.dispose();
    }//GEN-LAST:event_BtnKeluarKehamilanActionPerformed

    private void BtnSimpanRiwayatKehamilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanRiwayatKehamilanActionPerformed
        if(UmurAnak.getText().trim().equals("")){
            Valid.textKosong(UmurAnak,"Tempat Persalinan");
        }else if(KUAnak.getText().trim().equals("")){
            Valid.textKosong(KUAnak,"Jenis Persalinan");
        }else if(RiwayatPersalinan.getText().trim().equals("")){
            Valid.textKosong(RiwayatPersalinan,"Penyulit Persalinan");
        }else if(DitolongOleh.getText().trim().equals("")){
            Valid.textKosong(DitolongOleh,"Keadaan Persalinan");
        }else if(BBL.getText().trim().equals("")){
            Valid.textKosong(BBL,"BB/PB");
        }else{
            if(Sequel.menyimpantf("riwayat_persalinan_partus","?,?,?,?,?,?,?,?","Riwayat Persalinan",8,new String[]{
                    null,TNoRM.getText(),UmurAnak.getText(),KUAnak.getText(),RiwayatPersalinan.getText(),DitolongOleh.getText(),JK.getSelectedItem().toString().substring(0,1),BBL.getText()
                })==true){
                emptTeksPersalinan();
                tampilPersalinan();
            }
        }
    }//GEN-LAST:event_BtnSimpanRiwayatKehamilanActionPerformed

    private void JKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JKKeyPressed
//        Valid.pindah(evt,UsiaHamil,BBPB);
    }//GEN-LAST:event_JKKeyPressed

    private void UmurAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UmurAnakKeyPressed
        Valid.pindah(evt,BtnKeluarKehamilan,KUAnak);
    }//GEN-LAST:event_UmurAnakKeyPressed

    private void KUAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KUAnakKeyPressed
//        Valid.pindah(evt,TempatPersalinan,Penolong);
    }//GEN-LAST:event_KUAnakKeyPressed

    private void RiwayatPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RiwayatPersalinanKeyPressed
//        Valid.pindah(evt,Penolong,Keadaan);
    }//GEN-LAST:event_RiwayatPersalinanKeyPressed

    private void DitolongOlehKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DitolongOlehKeyPressed
//        Valid.pindah(evt,Penyulit,UsiaHamil);
    }//GEN-LAST:event_DitolongOlehKeyPressed

    private void BBLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBLKeyPressed
        Valid.pindah(evt,JK,BtnSimpanRiwayatKehamilan);
    }//GEN-LAST:event_BBLKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void DiagnosaKebidanan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKebidanan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaKebidanan1KeyPressed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
            param.put("nyeri",Sequel.cariGambar("select gambar.nyeri from gambar"));
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),7).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),6).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString()));
            try {
                ps=koneksi.prepareStatement("select * from riwayat_persalinan_partus where no_rkm_medis=?");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
                    rs=ps.executeQuery();
                    i=1;
                    while(rs.next()){
                        param.put("no"+i,i+"");
                        param.put("umur_anak"+i,rs.getString("umur_anak"));
                        param.put("ku_anak"+i,rs.getString("ku_anak"));
                        param.put("riwayat_persalinan"+i,rs.getString("riwayat_persalinan"));
                        param.put("ditolong_oleh"+i,rs.getString("ditolong_oleh"));
                        param.put("jk"+i,rs.getString("jk"));
                        param.put("bbl"+i,rs.getString("bbl"));
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println("Notif : "+e);
                } finally{
                    if(rs!=null){
                        rs.close();
                    }
                    if(ps!=null){
                        ps.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            }
            
            Valid.MyReportqry("rptCetakPenilaianAwalKebidananRalanPartus2.jasper","report","::[ Laporan Penilaian Awal Kebidanan & Kandungan ]::",
                "select ponek_partus.no_rawat,ponek_partus.no_rm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,ponek_partus.tanggal,ponek_partus.kd_petugas," +
                "ponek_partus.nm_petugas,ponek_partus.kd_dpjp,ponek_partus.nm_dpjp,ponek_partus.cara_datang,ponek_partus.cara_datang_lainnya," +
                "ponek_partus.menggunakan,ponek_partus.menggunakan_lainnya,ponek_partus.asal,ponek_partus.asal_lainnya,ponek_partus.pengkajian_dari," +
                "ponek_partus.hubungan_dengan_pasien,ponek_partus.alasan_masuk,ponek_partus.penyakit_pernah_diderita,CONCAT(ponek_partus.nama_riwayat_penyakit, ponek_partus.penyakit_lainnya) AS riwayat_penyakit," +
                "ponek_partus.faktor_keturunan_gamelli,ponek_partus.ketergantungan,ponek_partus.ketergantungan_dengan,ponek_partus.sejak," +
                "ponek_partus.obat_obatan,ponek_partus.nama_obatan,ponek_partus.makanan,ponek_partus.nama_makanan,ponek_partus.debu,ponek_partus.nama_debu," +
                "ponek_partus.alergi_lainnya,ponek_partus.menarche,ponek_partus.menstruasi,ponek_partus.sejak_menstruasi,ponek_partus.sakit_saat_menstruasi," +
                "ponek_partus.menikah_ke,ponek_partus.lamanya_pernikahan,ponek_partus.kontrasepsi,ponek_partus.lamanya_kontrasepsi,ponek_partus.graphit," +
                "ponek_partus.paritas,ponek_partus.abortus,ponek_partus.haid_terakhir,ponek_partus.perkiraan_lahir,ponek_partus.umur_kehamilan," +
                "ponek_partus.keluhan_kehamilan,ponek_partus.tinggi_fundus_uteri,ponek_partus.letak_punggung_janin,ponek_partus.presentasi_janin," +
                "ponek_partus.taksiran_berat_janin,ponek_partus.penurunan,ponek_partus.aukultasi,ponek_partus.frekuensi_aukultasi,ponek_partus.pemeriksaan_dalam," +
                "ponek_partus.td,ponek_partus.nadi,ponek_partus.rr,ponek_partus.suhu,ponek_partus.tb,ponek_partus.bb,ponek_partus.keadaan_umum," +
                "ponek_partus.gcs,ponek_partus.kesadaran,ponek_partus.input_penurunan_kesadaran,ponek_partus.kepala,ponek_partus.mata,ponek_partus.hidung,ponek_partus.gigi_mulut, "+
                "ponek_partus.tenggorokan,ponek_partus.telinga,ponek_partus.ekstremitas,ponek_partus.leher,ponek_partus.thoraks,ponek_partus.jantung,ponek_partus.paru,ponek_partus.abdomen, "+
                "ponek_partus.genitalis_anus,ponek_partus.nyeri,ponek_partus.skor,ponek_partus.kategori,ponek_partus.pengaruh_nyeri,ponek_partus.hb,ponek_partus.hasil_usg, "+
                "ponek_partus.status_mental,ponek_partus.respon_emosi,ponek_partus.suport_suami,concat(ponek_partus.masalah_kebidanan,ponek_partus.masalah_kebidanan_lainnya) as masalah_kebidanann,ponek_partus.diagnosa_kebidanan "+
                "from ponek_partus inner join pasien on ponek_partus.no_rm_medis=pasien.no_rkm_medis where ponek_partus.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);

            Valid.MyReportqry("rptCetakPenilaianAwalKebidananPartus.jasper","report","::[ Laporan Penilaian Awal Kebidanan & Kandungan ]::",
                "select ponek_partus.no_rawat,ponek_partus.no_rm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,ponek_partus.tanggal,ponek_partus.kd_petugas," +
                "ponek_partus.nm_petugas,ponek_partus.kd_dpjp,ponek_partus.nm_dpjp,ponek_partus.cara_datang,ponek_partus.cara_datang_lainnya," +
                "ponek_partus.menggunakan,ponek_partus.menggunakan_lainnya,ponek_partus.asal,ponek_partus.asal_lainnya,ponek_partus.pengkajian_dari," +
                "ponek_partus.hubungan_dengan_pasien,ponek_partus.alasan_masuk,ponek_partus.penyakit_pernah_diderita,CONCAT(ponek_partus.nama_riwayat_penyakit, ponek_partus.penyakit_lainnya) AS riwayat_penyakit," +
                "ponek_partus.faktor_keturunan_gamelli,ponek_partus.ketergantungan,ponek_partus.ketergantungan_dengan,ponek_partus.sejak," +
                "ponek_partus.obat_obatan,ponek_partus.nama_obatan,ponek_partus.makanan,ponek_partus.nama_makanan,ponek_partus.debu,ponek_partus.nama_debu," +
                "ponek_partus.alergi_lainnya,ponek_partus.menarche,ponek_partus.menstruasi,ponek_partus.sejak_menstruasi,ponek_partus.sakit_saat_menstruasi," +
                "ponek_partus.menikah_ke,ponek_partus.lamanya_pernikahan,ponek_partus.kontrasepsi,ponek_partus.lamanya_kontrasepsi,ponek_partus.graphit," +
                "ponek_partus.paritas,ponek_partus.abortus,ponek_partus.haid_terakhir,ponek_partus.perkiraan_lahir,ponek_partus.umur_kehamilan," +
                "ponek_partus.keluhan_kehamilan,ponek_partus.tinggi_fundus_uteri,ponek_partus.letak_punggung_janin,ponek_partus.presentasi_janin," +
                "ponek_partus.taksiran_berat_janin,ponek_partus.penurunan,ponek_partus.aukultasi,ponek_partus.frekuensi_aukultasi,ponek_partus.pemeriksaan_dalam," +
                "ponek_partus.td,ponek_partus.nadi,ponek_partus.rr,ponek_partus.suhu,ponek_partus.tb,ponek_partus.bb,ponek_partus.keadaan_umum," +
                "ponek_partus.gcs,ponek_partus.kesadaran,ponek_partus.input_penurunan_kesadaran,ponek_partus.kepala,ponek_partus.mata,ponek_partus.hidung,ponek_partus.gigi_mulut, "+
                "ponek_partus.tenggorokan,ponek_partus.telinga,ponek_partus.ekstremitas,ponek_partus.leher,ponek_partus.thoraks,ponek_partus.jantung,ponek_partus.paru,ponek_partus.abdomen, "+
                "ponek_partus.genitalis_anus,ponek_partus.nyeri,ponek_partus.skor,ponek_partus.kategori,ponek_partus.pengaruh_nyeri,ponek_partus.hb,ponek_partus.hasil_usg, "+
                "ponek_partus.status_mental,ponek_partus.respon_emosi,ponek_partus.suport_suami,concat(ponek_partus.masalah_kebidanan,ponek_partus.masalah_kebidanan_lainnya) as masalah_kebidanann,ponek_partus.diagnosa_kebidanan "+
                "from ponek_partus inner join pasien on ponek_partus.no_rm_medis=pasien.no_rkm_medis where ponek_partus.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            isMenu();
        }else{
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    ChkAccor.setSelected(true);
                    isMenu();
                    getMasalah();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                ChkAccor.setSelected(true);
                isMenu();
                getMasalah();
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if((evt.getClickCount()==2)&&(tbObat.getSelectedColumn()==0)){
                TabRawat.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            tampil();
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            try{
                File g = new File("file2.css");
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
                    ".isi td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi2 td{font: 11px tahoma;height:12px;background: #ffffff;color:#323232;}"+
                    ".isi3 td{border-right: 1px solid #e2e7dd;font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"
                );
                bg.close();

                File f;
                BufferedWriter bw;

                ps=koneksi.prepareStatement(
                    "select penilaian_awal_keperawatan_kebidanan_ranap.no_rawat,penilaian_awal_keperawatan_kebidanan_ranap.tanggal,penilaian_awal_keperawatan_kebidanan_ranap.informasi,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.tiba_diruang_rawat,penilaian_awal_keperawatan_kebidanan_ranap.cara_masuk,penilaian_awal_keperawatan_kebidanan_ranap.keluhan,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.rpk,penilaian_awal_keperawatan_kebidanan_ranap.psk,penilaian_awal_keperawatan_kebidanan_ranap.rp,penilaian_awal_keperawatan_kebidanan_ranap.alergi,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.komplikasi_sebelumnya,penilaian_awal_keperawatan_kebidanan_ranap.keterangan_komplikasi_sebelumnya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_umur,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_banyaknya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_siklus,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_ket_siklus,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_dirasakan,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_status,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_status,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia1,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia1,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia2,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia2,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia3,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia3,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_g,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_p,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_a,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_hidup,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_hpht,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_usiahamil,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_tp,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_imunisasi,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_anc,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_ancke,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_ket_ancke,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_keluhan_hamil_muda,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_keluhan_hamil_tua,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_komplikasi,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_ket_komplikasi,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_kapaberhenti,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_alasanberhenti,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_genekologi,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_obat,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_obat,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_merokok,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_merokok,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_alkohol,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_alkohol,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_narkoba,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_mental,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_keadaan_umum,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_gcs,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_td,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_nadi,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_rr,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_suhu,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_spo2,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_bb,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tb,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lila,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tfu,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tbj,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_letak,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_presentasi,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_penurunan,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_his,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_kekuatan,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_djj,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_djj,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_portio,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_pembukaan,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ketuban,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_hodge,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_panggul,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_inspekulo,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_inspekulo,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lakmus,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_lakmus,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ctg,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_ctg,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_kepala,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_muka,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_mata,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_hidung,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_telinga,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_mulut,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_leher,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_dada,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_perut,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_genitalia,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_ekstrimitas,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_kemampuan_aktifitas,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_berjalan,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_berjalan,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_aktivitas,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ambulasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ekstrimitas_atas,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_ekstrimitas_atas,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ekstrimitas_bawah,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_ekstrimitas_bawah,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_kemampuan_menggenggam,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_kemampuan_menggenggam,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_koordinasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_koordinasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_gangguan_fungsi,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_kondisipsiko,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_adakah_prilaku,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_adakah_prilaku,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_gangguan_jiwa,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_hubungan_pasien,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_tinggal_dengan,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_tinggal_dengan,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_budaya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_budaya,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_pend_pj,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_edukasi_pada,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_edukasi_pada,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_penyebab,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_penyebab,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_kualitas,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_kualitas,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_lokasi,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_menyebar,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_skala,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_waktu,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_hilang,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_hilang,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_diberitahukan_dokter,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_jam_diberitahukan_dokter,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala1,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai1,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala2,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai2,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala3,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai3,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala4,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai4,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala5,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai5,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala6,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai6,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_totalnilai,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi1,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.nilai_gizi1,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi2,penilaian_awal_keperawatan_kebidanan_ranap.nilai_gizi2,bahasa_pasien.nama_bahasa,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.nilai_total_gizi,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_diagnosa_khusus,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_ket_diagnosa_khusus,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_diketahui_dietisen,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_jam_diketahui_dietisen,penilaian_awal_keperawatan_kebidanan_ranap.masalah,"+
                    "penilaian_awal_keperawatan_kebidanan_ranap.rencana,penilaian_awal_keperawatan_kebidanan_ranap.nip1,penilaian_awal_keperawatan_kebidanan_ranap.nip2,penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter, "+
                    "pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab "+
                    "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join penilaian_awal_keperawatan_kebidanan_ranap on reg_periksa.no_rawat=penilaian_awal_keperawatan_kebidanan_ranap.no_rawat "+
                    "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_kebidanan_ranap.nip1=pengkaji1.nip "+
                    "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_kebidanan_ranap.nip2=pengkaji2.nip "+
                    "inner join dokter on penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter=dokter.kd_dokter "+
                    "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                    "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where "+
                    "penilaian_awal_keperawatan_kebidanan_ranap.tanggal between ? and ? "+
                    (TCari.getText().trim().equals("")?"":"and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_awal_keperawatan_kebidanan_ranap.nip1 like ? or pengkaji1.nama like ? or penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter like ? or dokter.nm_dokter like ?)")+
                    " order by penilaian_awal_keperawatan_kebidanan_ranap.tanggal");
                try {
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                    if(!TCari.getText().equals("")){
                        ps.setString(3,"%"+TCari.getText()+"%");
                        ps.setString(4,"%"+TCari.getText()+"%");
                        ps.setString(5,"%"+TCari.getText()+"%");
                        ps.setString(6,"%"+TCari.getText()+"%");
                        ps.setString(7,"%"+TCari.getText()+"%");
                        ps.setString(8,"%"+TCari.getText()+"%");
                        ps.setString(9,"%"+TCari.getText()+"%");
                    }
                    rs=ps.executeQuery();
                    pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih laporan..!","Pilihan Cetak",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Laporan 1 (HTML)","Laporan 2 (WPS)","Laporan 3 (CSV)"},"Laporan 1 (HTML)");
                    switch (pilihan) {
                        case "Laporan 1 (HTML)":
                        htmlContent = new StringBuilder();
                        htmlContent.append(
                            "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>No.Rawat</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>No.RM</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='160px'>Nama Pasien</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Tgl.Lahir</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='25px'>J.K.</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='85px'>NIP Pengkaji 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Nama Pengkaji 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='85px'>NIP Pengkaji 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Nama Pengkaji 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='85px'>Kode DPJP</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Nama DPJP</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='117px'>Tgl.Asuhan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='90px'>Anamnesis</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='110px'>Tiba di Ruang Rawat</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Cara Masuk</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='250px'>Keluhan Utama</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Penyakit Selama Kehamilan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Riwayat Penyakit Keluarga</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Riwayat Pembedahan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>Riwayat Alergi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='175px'>Komplikasi Kehamilan Sebelumnya</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='180px'>Keterangan Komplikasi Sebelumnya</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='84px'>Umur Menarche</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='78px'>Lamanya Mens</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='108px'>Banyaknya Pembalut</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Siklus Haid</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'>Ket.Siklus Haid</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='136px'>Dirasakan Saat Menstruasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='81px'>Status Menikah</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Jml.Nikah</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='96px'>Usia Perkawinan 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='106px'>Status Perkawinan 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='96px'>Usia Perkawinan 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='106px'>Status Perkawinan 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='96px'>Usia Perkawinan 3</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='106px'>Status Perkawinan 3</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='22px'>G</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='22px'>P</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='22px'>A</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='37px'>Hidup</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>HPHT</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='59px'>Usia Hamil</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Tg.Perkiraan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='97px'>Riwayat Imunisasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>ANC</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='45px'>ANC Ke</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Ket. ANC</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>Keluhan Hamil Muda</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='98px'>Keluhan Hamil Tua</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Riwayat Keluarga Berencana</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Lamanya KB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='90px'>Komplikasi KB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'>Ket Komplikasi KB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='90px'>Berhenti KB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'>Alasan Berhenti KB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='104px'>Riwayat Genekologi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>Obat/Vitamin</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Keterangan Obat/Vitamin</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Merokok</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Rokok/Hari</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='45px'>Alkohol</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='68px'>Alkohol/Hari</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='103px'>Obat Tidur/Narkoba</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>Kesadaran Mental</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='84px'>Keadaan Umum</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='63px'>GCS(E,V,M)</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>TD</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>Nadi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>RR</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>Suhu</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>SpO2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>BB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>TB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'>LILA</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'>TFU</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'>TBJ</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='27px'>GD</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'>Letak</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Presentasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Penurunan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='75px'>Kontraksi/HIS</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Kekuatan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Lamanya</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>DJJ</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='85px'>Keterangan DJJ</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Portio</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='45px'>Serviks</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='53px'>Ketuban</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='53px'>Hodge</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='145px'>Panggul</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Inspekulo</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='115px'>Keterangan Inspekulo</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Lakmus</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='115px'>Keterangan Lakmus</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>CTG</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='115px'>Keterangan CTG</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'>Kepala</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Muka</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='128px'>Mata</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Hidung</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Telinga</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Mulut</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='140px'>Leher</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='87px'>Dada</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>Perut</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Genitalia</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>Ekstremitas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'>a. Aktivitas Sehari-hari</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='140px'>b. Berjalan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='140px'>Ket. Berjalan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='68px'>c. Aktifitas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='101px'>d. Alat Ambulasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>e. Ekstrimitas Atas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='110px'>Ket. Ekstrimitas Atas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='107px'>f. Ekstrimitas Bawah</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='118px'>Ket. Ekstrimitas Bawah</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='154px'>g. Kemampuan Menggenggam</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='164px'>Ket. Kemampuan Menggenggam</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='133px'>h. Kemampuan Koordinasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='143px'>Ket. Kemampuan Koordinasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='160px'>i. Kesimpulan Gangguan Fungsi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='106px'>a. Kondisi Psikologis</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='98px'>b. Adakah Perilaku</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='107px'>Keterangan Perilaku</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='156px'>c. Gangguan Jiwa di Masa Lalu</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='198px'>d. Hubungan dengan Anggota Keluarga</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>e. Agama</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='95px'>f. Tinggal Dengan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='103px'>Keterangan Tinggal</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='110px'>g. Pekerjaan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>h. Pembayaran</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>i. Nilai-nilai Kepercayaan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='142px'>Ket. Nilai-nilai Kepercayaan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='110px'>j. Bahasa Sehari-hari</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='108px'>k. Pendidikan Pasien</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>l. Pendidikan P.J.</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='148px'>m. Edukasi Diberikan Kepada</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='148px'>Keterangan Edukasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='83px'>Penilaian Nyeri</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='83px'>Penyebab</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='115px'>Keterangan Penyebab</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='87px'>Kualitas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='107px'>Keterangan Kualitas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>Lokasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'>Menyebar</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='63px'>Skala Nyeri</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Durasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='87px'>Nyeri hilang bila</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='126px'>Keterangan Nyeri Hilang</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='110px'>Diberitahukan Dokter</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Pada Jam</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='90px'>1. Riwayat Jatuh</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='225px'>2. Diagnosis Sekunder (&GreaterEqual; 2 Diagnosis Medis)</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='218px'>3. Alat Bantu</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 3</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='160px'>4. Terpasang Infuse</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 4</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='225px'>5. Gaya Berjalan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 5</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='215px'>6. Status Mental</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 6</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='58px'>Total Nilai</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='380px'>1. Apakah ada penurunan BB yang tidak diinginkan selama 6 bulan terakhir ?</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Skor 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='317px'>2. Apakah asupan makan berkurang karena tidak nafsu makan ?</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Skor 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='58px'>Total Skor</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='165px'>Pasien dengan diagnosis khusus</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='149px'>Keterangan Diagnosa Khusus</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='209px'>Sudah dibaca dan diketahui oleh Dietisen</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='107px'>Jam Dibaca Dietisen</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'>Asesmen/Penilaian Kebidanan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'>Rencana Kebidanan</td>"+
                            "</tr>"
                        );
                        while(rs.next()){
                            htmlContent.append(
                                "<tr class='isi'>"+
                                "<td valign='top'>"+rs.getString("no_rawat")+"</td>"+
                                "<td valign='top'>"+rs.getString("no_rkm_medis")+"</td>"+
                                "<td valign='top'>"+rs.getString("nm_pasien")+"</td>"+
                                "<td valign='top'>"+rs.getString("tgl_lahir")+"</td>"+
                                "<td valign='top'>"+rs.getString("jk")+"</td>"+
                                "<td valign='top'>"+rs.getString("nip1")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkaji1")+"</td>"+
                                "<td valign='top'>"+rs.getString("nip2")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkaji2")+"</td>"+
                                "<td valign='top'>"+rs.getString("kd_dokter")+"</td>"+
                                "<td valign='top'>"+rs.getString("nm_dokter")+"</td>"+
                                "<td valign='top'>"+rs.getString("tanggal")+"</td>"+
                                "<td valign='top'>"+rs.getString("informasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("tiba_diruang_rawat")+"</td>"+
                                "<td valign='top'>"+rs.getString("cara_masuk")+"</td>"+
                                "<td valign='top'>"+rs.getString("keluhan")+"</td>"+
                                "<td valign='top'>"+rs.getString("psk")+"</td>"+
                                "<td valign='top'>"+rs.getString("rpk")+"</td>"+
                                "<td valign='top'>"+rs.getString("rp")+"</td>"+
                                "<td valign='top'>"+rs.getString("alergi")+"</td>"+
                                "<td valign='top'>"+rs.getString("komplikasi_sebelumnya")+"</td>"+
                                "<td valign='top'>"+rs.getString("keterangan_komplikasi_sebelumnya")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_umur")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_lamanya")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_banyaknya")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_siklus")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_ket_siklus")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_dirasakan")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_status")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_ket_status")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_usia1")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_ket_usia1")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_usia2")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_ket_usia2")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_usia3")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_ket_usia3")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_persalinan_g")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_persalinan_p")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_persalinan_a")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_persalinan_hidup")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_hpht")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_usiahamil")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_tp")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_imunisasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_anc")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_ancke")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_ket_ancke")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_keluhan_hamil_muda")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_keluhan_hamil_tua")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb_lamanya")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb_komplikasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb_ket_komplikasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb_kapaberhenti")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb_alasanberhenti")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_genekologi")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_obat")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_obat")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_merokok")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_merokok")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_alkohol")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_alkohol")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_narkoba")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_mental")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_keadaan_umum")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_gcs")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_td")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_nadi")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_rr")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_suhu")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_spo2")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_bb")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_tb")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_lila")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_tfu")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_tbj")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_letak")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_presentasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_penurunan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_penurunan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_his")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_kekuatan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_lamanya")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_djj")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ket_djj")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_portio")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_pembukaan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ketuban")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_hodge")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_panggul")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_inspekulo")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ket_inspekulo")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_lakmus")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ket_lakmus")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ctg")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ket_ctg")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_kepala")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_muka")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_mata")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_hidung")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_telinga")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_mulut")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_leher")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_dada")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_perut")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_genitalia")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_ekstrimitas")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_kemampuan_aktifitas")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_berjalan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ket_berjalan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_aktivitas")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ambulasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ekstrimitas_atas")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ket_ekstrimitas_atas")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ekstrimitas_bawah")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ket_ekstrimitas_bawah")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_kemampuan_menggenggam")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ket_kemampuan_menggenggam")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_koordinasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ket_koordinasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_gangguan_fungsi")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_kondisipsiko")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_adakah_prilaku")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_ket_adakah_prilaku")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_gangguan_jiwa")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_hubungan_pasien")+"</td>"+
                                "<td valign='top'>"+rs.getString("agama")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_tinggal_dengan")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_ket_tinggal_dengan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pekerjaan")+"</td>"+
                                "<td valign='top'>"+rs.getString("png_jawab")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_budaya")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_ket_budaya")+"</td>"+
                                "<td valign='top'>"+rs.getString("nama_bahasa")+"</td>"+
                                "<td valign='top'>"+rs.getString("pnd")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_pend_pj")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_edukasi_pada")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_ket_edukasi_pada")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_penyebab")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_ket_penyebab")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_kualitas")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_ket_kualitas")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_lokasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_menyebar")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_skala")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_waktu")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_hilang")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_ket_hilang")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_diberitahukan_dokter")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_jam_diberitahukan_dokter")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala1")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai1")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala2")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai2")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala3")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai3")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala4")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai4")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala5")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai5")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala6")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai6")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_totalnilai")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi1")+"</td>"+
                                "<td valign='top'>"+rs.getString("nilai_gizi1")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi2")+"</td>"+
                                "<td valign='top'>"+rs.getString("nilai_gizi2")+"</td>"+
                                "<td valign='top'>"+rs.getString("nilai_total_gizi")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi_diagnosa_khusus")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi_ket_diagnosa_khusus")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi_diketahui_dietisen")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi_jam_diketahui_dietisen")+"</td>"+
                                "<td valign='top'>"+rs.getString("masalah")+"</td>"+
                                "<td valign='top'>"+rs.getString("rencana")+"</td>"+
                                "</tr>"
                            );
                        }
                        f = new File("RMPenilaianAwalKeperawatanKebidananRanap.html");
                        bw = new BufferedWriter(new FileWriter(f));
                        bw.write("<html>"+
                            "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"+
                            "<body>"+
                            "<table width='18500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                            htmlContent.toString()+
                            "</table>"+
                            "</body>"+
                            "</html>"
                        );

                        bw.close();
                        Desktop.getDesktop().browse(f.toURI());
                        break;
                        case "Laporan 2 (WPS)":
                        htmlContent = new StringBuilder();
                        htmlContent.append(
                            "<tr class='isi'>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>No.Rawat</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>No.RM</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='160px'>Nama Pasien</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Tgl.Lahir</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='25px'>J.K.</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='85px'>NIP Pengkaji 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Nama Pengkaji 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='85px'>NIP Pengkaji 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Nama Pengkaji 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='85px'>Kode DPJP</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Nama DPJP</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='117px'>Tgl.Asuhan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='90px'>Anamnesis</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='110px'>Tiba di Ruang Rawat</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Cara Masuk</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='250px'>Keluhan Utama</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Penyakit Selama Kehamilan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Riwayat Penyakit Keluarga</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Riwayat Pembedahan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>Riwayat Alergi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='175px'>Komplikasi Kehamilan Sebelumnya</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='180px'>Keterangan Komplikasi Sebelumnya</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='84px'>Umur Menarche</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='78px'>Lamanya Mens</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='108px'>Banyaknya Pembalut</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Siklus Haid</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'>Ket.Siklus Haid</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='136px'>Dirasakan Saat Menstruasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='81px'>Status Menikah</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='54px'>Jml.Nikah</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='96px'>Usia Perkawinan 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='106px'>Status Perkawinan 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='96px'>Usia Perkawinan 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='106px'>Status Perkawinan 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='96px'>Usia Perkawinan 3</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='106px'>Status Perkawinan 3</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='22px'>G</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='22px'>P</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='22px'>A</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='37px'>Hidup</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>HPHT</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='59px'>Usia Hamil</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Tg.Perkiraan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='97px'>Riwayat Imunisasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>ANC</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='45px'>ANC Ke</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Ket. ANC</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>Keluhan Hamil Muda</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='98px'>Keluhan Hamil Tua</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Riwayat Keluarga Berencana</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>Lamanya KB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='90px'>Komplikasi KB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'>Ket Komplikasi KB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='90px'>Berhenti KB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'>Alasan Berhenti KB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='104px'>Riwayat Genekologi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>Obat/Vitamin</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='150px'>Keterangan Obat/Vitamin</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Merokok</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Rokok/Hari</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='45px'>Alkohol</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='68px'>Alkohol/Hari</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='103px'>Obat Tidur/Narkoba</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='105px'>Kesadaran Mental</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='84px'>Keadaan Umum</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='63px'>GCS(E,V,M)</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>TD</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>Nadi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>RR</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>Suhu</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>SpO2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>BB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35px'>TB</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'>LILA</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'>TFU</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'>TBJ</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='27px'>GD</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'>Letak</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Presentasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Penurunan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='75px'>Kontraksi/HIS</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65px'>Kekuatan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Lamanya</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>DJJ</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='85px'>Keterangan DJJ</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Portio</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='45px'>Serviks</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='53px'>Ketuban</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='53px'>Hodge</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='145px'>Panggul</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Inspekulo</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='115px'>Keterangan Inspekulo</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Lakmus</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='115px'>Keterangan Lakmus</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>CTG</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='115px'>Keterangan CTG</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='80px'>Kepala</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Muka</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='128px'>Mata</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Hidung</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Telinga</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50px'>Mulut</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='140px'>Leher</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='87px'>Dada</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>Perut</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Genitalia</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>Ekstremitas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='120px'>a. Aktivitas Sehari-hari</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='140px'>b. Berjalan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='140px'>Ket. Berjalan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='68px'>c. Aktifitas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='101px'>d. Alat Ambulasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>e. Ekstrimitas Atas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='110px'>Ket. Ekstrimitas Atas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='107px'>f. Ekstrimitas Bawah</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='118px'>Ket. Ekstrimitas Bawah</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='154px'>g. Kemampuan Menggenggam</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='164px'>Ket. Kemampuan Menggenggam</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='133px'>h. Kemampuan Koordinasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='143px'>Ket. Kemampuan Koordinasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='160px'>i. Kesimpulan Gangguan Fungsi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='106px'>a. Kondisi Psikologis</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='98px'>b. Adakah Perilaku</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='107px'>Keterangan Perilaku</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='156px'>c. Gangguan Jiwa di Masa Lalu</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='198px'>d. Hubungan dengan Anggota Keluarga</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70px'>e. Agama</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='95px'>f. Tinggal Dengan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='103px'>Keterangan Tinggal</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='110px'>g. Pekerjaan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>h. Pembayaran</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='130px'>i. Nilai-nilai Kepercayaan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='142px'>Ket. Nilai-nilai Kepercayaan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='110px'>j. Bahasa Sehari-hari</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='108px'>k. Pendidikan Pasien</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>l. Pendidikan P.J.</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='148px'>m. Edukasi Diberikan Kepada</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='148px'>Keterangan Edukasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='83px'>Penilaian Nyeri</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='83px'>Penyebab</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='115px'>Keterangan Penyebab</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='87px'>Kualitas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='107px'>Keterangan Kualitas</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='100px'>Lokasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='55px'>Menyebar</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='63px'>Skala Nyeri</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Durasi</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='87px'>Nyeri hilang bila</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='126px'>Keterangan Nyeri Hilang</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='110px'>Diberitahukan Dokter</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='60px'>Pada Jam</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='90px'>1. Riwayat Jatuh</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='225px'>2. Diagnosis Sekunder (&GreaterEqual; 2 Diagnosis Medis)</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='218px'>3. Alat Bantu</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 3</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='160px'>4. Terpasang Infuse</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 4</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='225px'>5. Gaya Berjalan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 5</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='215px'>6. Status Mental</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Nilai 6</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='58px'>Total Nilai</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='380px'>1. Apakah ada penurunan BB yang tidak diinginkan selama 6 bulan terakhir ?</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Skor 1</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='317px'>2. Apakah asupan makan berkurang karena tidak nafsu makan ?</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='40px'>Skor 2</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='58px'>Total Skor</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='165px'>Pasien dengan diagnosis khusus</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='149px'>Keterangan Diagnosa Khusus</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='209px'>Sudah dibaca dan diketahui oleh Dietisen</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='107px'>Jam Dibaca Dietisen</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'>Asesmen/Penilaian Kebidanan</td>"+
                            "<td valign='middle' bgcolor='#FFFAF8' align='center' width='200px'>Rencana Kebidanan</td>"+
                            "</tr>"
                        );
                        while(rs.next()){
                            htmlContent.append(
                                "<tr class='isi'>"+
                                "<td valign='top'>"+rs.getString("no_rawat")+"</td>"+
                                "<td valign='top'>"+rs.getString("no_rkm_medis")+"</td>"+
                                "<td valign='top'>"+rs.getString("nm_pasien")+"</td>"+
                                "<td valign='top'>"+rs.getString("tgl_lahir")+"</td>"+
                                "<td valign='top'>"+rs.getString("jk")+"</td>"+
                                "<td valign='top'>"+rs.getString("nip1")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkaji1")+"</td>"+
                                "<td valign='top'>"+rs.getString("nip2")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkaji2")+"</td>"+
                                "<td valign='top'>"+rs.getString("kd_dokter")+"</td>"+
                                "<td valign='top'>"+rs.getString("nm_dokter")+"</td>"+
                                "<td valign='top'>"+rs.getString("tanggal")+"</td>"+
                                "<td valign='top'>"+rs.getString("informasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("tiba_diruang_rawat")+"</td>"+
                                "<td valign='top'>"+rs.getString("cara_masuk")+"</td>"+
                                "<td valign='top'>"+rs.getString("keluhan")+"</td>"+
                                "<td valign='top'>"+rs.getString("psk")+"</td>"+
                                "<td valign='top'>"+rs.getString("rpk")+"</td>"+
                                "<td valign='top'>"+rs.getString("rp")+"</td>"+
                                "<td valign='top'>"+rs.getString("alergi")+"</td>"+
                                "<td valign='top'>"+rs.getString("komplikasi_sebelumnya")+"</td>"+
                                "<td valign='top'>"+rs.getString("keterangan_komplikasi_sebelumnya")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_umur")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_lamanya")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_banyaknya")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_siklus")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_ket_siklus")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_mens_dirasakan")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_status")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_ket_status")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_usia1")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_ket_usia1")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_usia2")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_ket_usia2")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_usia3")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_perkawinan_ket_usia3")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_persalinan_g")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_persalinan_p")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_persalinan_a")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_persalinan_hidup")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_hpht")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_usiahamil")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_tp")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_imunisasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_anc")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_ancke")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_ket_ancke")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_keluhan_hamil_muda")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_hamil_keluhan_hamil_tua")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb_lamanya")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb_komplikasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb_ket_komplikasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb_kapaberhenti")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kb_alasanberhenti")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_genekologi")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_obat")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_obat")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_merokok")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_merokok")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_alkohol")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_ket_alkohol")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_kebiasaan_narkoba")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_mental")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_keadaan_umum")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_gcs")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_td")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_nadi")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_rr")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_suhu")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_spo2")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_bb")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_tb")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_lila")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_tfu")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_tbj")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_letak")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_presentasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_penurunan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_penurunan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_his")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_kekuatan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_lamanya")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_djj")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ket_djj")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_portio")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_pembukaan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ketuban")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_hodge")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_panggul")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_inspekulo")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ket_inspekulo")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_lakmus")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ket_lakmus")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ctg")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_kebidanan_ket_ctg")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_kepala")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_muka")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_mata")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_hidung")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_telinga")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_mulut")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_leher")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_dada")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_perut")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_genitalia")+"</td>"+
                                "<td valign='top'>"+rs.getString("pemeriksaan_umum_ekstrimitas")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_kemampuan_aktifitas")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_berjalan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ket_berjalan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_aktivitas")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ambulasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ekstrimitas_atas")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ket_ekstrimitas_atas")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ekstrimitas_bawah")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ket_ekstrimitas_bawah")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_kemampuan_menggenggam")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ket_kemampuan_menggenggam")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_koordinasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_ket_koordinasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("pengkajian_fungsi_gangguan_fungsi")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_kondisipsiko")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_adakah_prilaku")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_ket_adakah_prilaku")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_gangguan_jiwa")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_hubungan_pasien")+"</td>"+
                                "<td valign='top'>"+rs.getString("agama")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_tinggal_dengan")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_ket_tinggal_dengan")+"</td>"+
                                "<td valign='top'>"+rs.getString("pekerjaan")+"</td>"+
                                "<td valign='top'>"+rs.getString("png_jawab")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_budaya")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_ket_budaya")+"</td>"+
                                "<td valign='top'>"+rs.getString("nama_bahasa")+"</td>"+
                                "<td valign='top'>"+rs.getString("pnd")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_pend_pj")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_edukasi_pada")+"</td>"+
                                "<td valign='top'>"+rs.getString("riwayat_psiko_ket_edukasi_pada")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_penyebab")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_ket_penyebab")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_kualitas")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_ket_kualitas")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_lokasi")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_menyebar")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_skala")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_waktu")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_hilang")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_ket_hilang")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_diberitahukan_dokter")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_nyeri_jam_diberitahukan_dokter")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala1")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai1")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala2")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai2")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala3")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai3")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala4")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai4")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala5")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai5")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_skala6")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_nilai6")+"</td>"+
                                "<td valign='top'>"+rs.getString("penilaian_jatuh_totalnilai")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi1")+"</td>"+
                                "<td valign='top'>"+rs.getString("nilai_gizi1")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi2")+"</td>"+
                                "<td valign='top'>"+rs.getString("nilai_gizi2")+"</td>"+
                                "<td valign='top'>"+rs.getString("nilai_total_gizi")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi_diagnosa_khusus")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi_ket_diagnosa_khusus")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi_diketahui_dietisen")+"</td>"+
                                "<td valign='top'>"+rs.getString("skrining_gizi_jam_diketahui_dietisen")+"</td>"+
                                "<td valign='top'>"+rs.getString("masalah")+"</td>"+
                                "<td valign='top'>"+rs.getString("rencana")+"</td>"+
                                "</tr>"
                            );
                        }
                        f = new File("RMPenilaianAwalKeperawatanKebidananRanap.wps");
                        bw = new BufferedWriter(new FileWriter(f));
                        bw.write("<html>"+
                            "<head><link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" /></head>"+
                            "<body>"+
                            "<table width='18500px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                            htmlContent.toString()+
                            "</table>"+
                            "</body>"+
                            "</html>"
                        );

                        bw.close();
                        Desktop.getDesktop().browse(f.toURI());
                        break;
                        case "Laporan 3 (CSV)":
                        htmlContent = new StringBuilder();
                        htmlContent.append(
                            "\"No.Rawat\";\"No.RM\";\"Nama Pasien\";\"Tgl.Lahir\";\"J.K.\";\"NIP Pengkaji 1\";\"Nama Pengkaji 1\";\"NIP Pengkaji 2\";\"Nama Pengkaji 2\";\"Kode DPJP\";\"Nama DPJP\";\"Tgl.Asuhan\";\"Anamnesis\";\"Tiba di Ruang Rawat\";\"Cara Masuk\";\"Keluhan Utama\";\"Penyakit Selama Kehamilan\";\"Riwayat Penyakit Keluarga\";\"Riwayat Pembedahan\";\"Riwayat Alergi\";\"Komplikasi Kehamilan Sebelumnya\";\"Keterangan Komplikasi Sebelumnya\";\"Umur Menarche\";\"Lamanya Mens\";\"Banyaknya Pembalut\";\"Siklus Haid\";\"Ket.Siklus Haid\";\"Dirasakan Saat Menstruasi\";\"Status Menikah\";\"Jml.Nikah\";\"Usia Perkawinan 1\";\"Status Perkawinan 1\";\"Usia Perkawinan 2\";\"Status Perkawinan 2\";\"Usia Perkawinan 3\";\"Status Perkawinan 3\";\"G\";\"P\";\"A\";\"Hidup\";\"HPHT\";\"Usia Hamil\";\"Tg.Perkiraan\";\"Riwayat Imunisasi\";\"ANC\";\"ANC Ke\";\"Ket. ANC\";\"Keluhan Hamil Muda\";\"Keluhan Hamil Tua\";\"Riwayat Keluarga Berencana\";\"Lamanya KB\";\"Komplikasi KB\";\"Ket Komplikasi KB\";\"Berhenti KB\";\"Alasan Berhenti KB\";\"Riwayat Genekologi\";\"Obat/Vitamin\";\"Keterangan Obat/Vitamin\";\"Merokok\";\"Rokok/Hari\";\"Alkohol\";\"Alkohol/Hari\";\"Obat Tidur/Narkoba\";\"Kesadaran Mental\";\"Keadaan Umum\";\"GCS(E,V,M)\";\"TD\";\"Nadi\";\"RR\";\"Suhu\";\"SpO2\";\"BB\";\"TB\";\"LILA\";\"TFU\";\"TBJ\";\"GD\";\"Letak\";\"Presentasi\";\"Penurunan\";\"Kontraksi/HIS\";\"Kekuatan\";\"Lamanya\";\"DJJ\";\"Keterangan DJJ\";\"Portio\";\"Serviks\";\"Ketuban\";\"Hodge\";\"Panggul\";\"Inspekulo\";\"Keterangan Inspekulo\";\"Lakmus\";\"Keterangan Lakmus\";\"CTG\";\"Keterangan CTG\";\"Kepala\";\"Muka\";\"Mata\";\"Hidung\";\"Telinga\";\"Mulut\";\"Leher\";\"Dada\";\"Perut\";\"Genitalia\";\"Ekstremitas\";\"a. Aktivitas Sehari-hari\";\"b. Berjalan\";\"Ket. Berjalan\";\"c. Aktifitas\";\"d. Alat Ambulasi\";\"e. Ekstrimitas Atas\";\"Ket. Ekstrimitas Atas\";\"f. Ekstrimitas Bawah\";\"Ket. Ekstrimitas Bawah\";\"g. Kemampuan Menggenggam\";\"Ket. Kemampuan Menggenggam\";\"h. Kemampuan Koordinasi\";\"Ket. Kemampuan Koordinasi\";\"i. Kesimpulan Gangguan Fungsi\";\"a. Kondisi Psikologis\";\"b. Adakah Perilaku\";\"Keterangan Perilaku\";\"c. Gangguan Jiwa di Masa Lalu\";\"d. Hubungan dengan Anggota Keluarga\";\"e. Agama\";\"f. Tinggal Dengan\";\"Keterangan Tinggal\";\"g. Pekerjaan\";\"h. Pembayaran\";\"i. Nilai-nilai Kepercayaan\";\"Ket. Nilai-nilai Kepercayaan\";\"j. Bahasa Sehari-hari\";\"k. Pendidikan Pasien\";\"l. Pendidikan P.J.\";\"m. Edukasi Diberikan Kepada\";\"Keterangan Edukasi\";\"Penilaian Nyeri\";\"Penyebab\";\"Keterangan Penyebab\";\"Kualitas\";\"Keterangan Kualitas\";\"Lokasi\";\"Menyebar\";\"Skala Nyeri\";\"Durasi\";\"Nyeri hilang bila\";\"Keterangan Nyeri Hilang\";\"Diberitahukan Dokter\";\"Pada Jam\";\"1. Riwayat Jatuh\";\"Nilai 1\";\"2. Diagnosis Sekunder (≥ 2 Diagnosis Medis)\";\"Nilai 2\";\"3. Alat Bantu\";\"Nilai 3\";\"4. Terpasang Infuse\";\"Nilai 4\";\"5. Gaya Berjalan\";\"Nilai 5\";\"6. Status Mental\";\"Nilai 6\";\"Total Nilai\";\"1. Apakah ada penurunan BB yang tidak diinginkan selama 6 bulan terakhir ?\";\"Skor 1\";\"2. Apakah asupan makan berkurang karena tidak nafsu makan ?\";\"Skor 2\";\"Total Skor\";\"Pasien dengan diagnosis khusus\";\"Keterangan Diagnosa Khusus\";\"Sudah dibaca dan diketahui oleh Dietisen\";\"Jam Dibaca Dietisen\";\"Asesmen/Penilaian Kebidanan\";\"Rencana Kebidanan\"\n"
                        );
                        while(rs.next()){
                            htmlContent.append(
                                "\""+rs.getString("no_rawat")+"\";\""+" "+rs.getString("no_rkm_medis")+"\";\""+rs.getString("nm_pasien")+"\";\""+rs.getString("tgl_lahir")+"\";\""+rs.getString("jk")+"\";\""+rs.getString("nip1")+"\";\""+rs.getString("pengkaji1")+"\";\""+rs.getString("nip2")+"\";\""+rs.getString("pengkaji2")+"\";\""+rs.getString("kd_dokter")+"\";\""+rs.getString("nm_dokter")+"\";\""+rs.getString("tanggal")+"\";\""+rs.getString("informasi")+"\";\""+rs.getString("tiba_diruang_rawat")+"\";\""+rs.getString("cara_masuk")+"\";\""+rs.getString("keluhan")+"\";\""+rs.getString("psk")+"\";\""+rs.getString("rpk")+"\";\""+rs.getString("rp")+"\";\""+rs.getString("alergi")+"\";\""+rs.getString("komplikasi_sebelumnya")+"\";\""+rs.getString("keterangan_komplikasi_sebelumnya")+"\";\""+rs.getString("riwayat_mens_umur")+"\";\""+rs.getString("riwayat_mens_lamanya")+"\";\""+rs.getString("riwayat_mens_banyaknya")+"\";\""+rs.getString("riwayat_mens_siklus")+"\";\""+rs.getString("riwayat_mens_ket_siklus")+"\";\""+rs.getString("riwayat_mens_dirasakan")+"\";\""+rs.getString("riwayat_perkawinan_status")+"\";\""+rs.getString("riwayat_perkawinan_ket_status")+"\";\""+rs.getString("riwayat_perkawinan_usia1")+"\";\""+rs.getString("riwayat_perkawinan_ket_usia1")+"\";\""+rs.getString("riwayat_perkawinan_usia2")+"\";\""+rs.getString("riwayat_perkawinan_ket_usia2")+"\";\""+rs.getString("riwayat_perkawinan_usia3")+"\";\""+rs.getString("riwayat_perkawinan_ket_usia3")+"\";\""+rs.getString("riwayat_persalinan_g")+"\";\""+rs.getString("riwayat_persalinan_p")+"\";\""+rs.getString("riwayat_persalinan_a")+"\";\""+rs.getString("riwayat_persalinan_hidup")+"\";\""+rs.getString("riwayat_hamil_hpht")+"\";\""+rs.getString("riwayat_hamil_usiahamil")+"\";\""+rs.getString("riwayat_hamil_tp")+"\";\""+rs.getString("riwayat_hamil_imunisasi")+"\";\""+rs.getString("riwayat_hamil_anc")+"\";\""+rs.getString("riwayat_hamil_ancke")+"\";\""+rs.getString("riwayat_hamil_ket_ancke")+"\";\""+rs.getString("riwayat_hamil_keluhan_hamil_muda")+"\";\""+rs.getString("riwayat_hamil_keluhan_hamil_tua")+"\";\""+rs.getString("riwayat_kb")+"\";\""+rs.getString("riwayat_kb_lamanya")+"\";\""+rs.getString("riwayat_kb_komplikasi")+"\";\""+rs.getString("riwayat_kb_ket_komplikasi")+"\";\""+rs.getString("riwayat_kb_kapaberhenti")+"\";\""+rs.getString("riwayat_kb_alasanberhenti")+"\";\""+rs.getString("riwayat_genekologi")+"\";\""+rs.getString("riwayat_kebiasaan_obat")+"\";\""+rs.getString("riwayat_kebiasaan_ket_obat")+"\";\""+rs.getString("riwayat_kebiasaan_merokok")+"\";\""+rs.getString("riwayat_kebiasaan_ket_merokok")+"\";\""+rs.getString("riwayat_kebiasaan_alkohol")+"\";\""+rs.getString("riwayat_kebiasaan_ket_alkohol")+"\";\""+rs.getString("riwayat_kebiasaan_narkoba")+"\";\""+rs.getString("pemeriksaan_kebidanan_mental")+"\";\""+rs.getString("pemeriksaan_kebidanan_keadaan_umum")+"\";\""+rs.getString("pemeriksaan_kebidanan_gcs")+"\";\""+rs.getString("pemeriksaan_kebidanan_td")+"\";\""+rs.getString("pemeriksaan_kebidanan_nadi")+"\";\""+rs.getString("pemeriksaan_kebidanan_rr")+"\";\""+rs.getString("pemeriksaan_kebidanan_suhu")+"\";\""+rs.getString("pemeriksaan_kebidanan_spo2")+"\";\""+rs.getString("pemeriksaan_kebidanan_bb")+"\";\""+rs.getString("pemeriksaan_kebidanan_tb")+"\";\""+rs.getString("pemeriksaan_kebidanan_lila")+"\";\""+rs.getString("pemeriksaan_kebidanan_tfu")+"\";\""+rs.getString("pemeriksaan_kebidanan_tbj")+"\";\""+rs.getString("pemeriksaan_kebidanan_letak")+"\";\""+rs.getString("pemeriksaan_kebidanan_presentasi")+"\";\""+rs.getString("pemeriksaan_kebidanan_penurunan")+"\";\""+rs.getString("pemeriksaan_kebidanan_penurunan")+"\";\""+rs.getString("pemeriksaan_kebidanan_his")+"\";\""+rs.getString("pemeriksaan_kebidanan_kekuatan")+"\";\""+rs.getString("pemeriksaan_kebidanan_lamanya")+"\";\""+rs.getString("pemeriksaan_kebidanan_djj")+"\";\""+rs.getString("pemeriksaan_kebidanan_ket_djj")+"\";\""+rs.getString("pemeriksaan_kebidanan_portio")+"\";\""+rs.getString("pemeriksaan_kebidanan_pembukaan")+"\";\""+rs.getString("pemeriksaan_kebidanan_ketuban")+"\";\""+rs.getString("pemeriksaan_kebidanan_hodge")+"\";\""+rs.getString("pemeriksaan_kebidanan_panggul")+"\";\""+rs.getString("pemeriksaan_kebidanan_inspekulo")+"\";\""+rs.getString("pemeriksaan_kebidanan_ket_inspekulo")+"\";\""+rs.getString("pemeriksaan_kebidanan_lakmus")+"\";\""+rs.getString("pemeriksaan_kebidanan_ket_lakmus")+"\";\""+rs.getString("pemeriksaan_kebidanan_ctg")+"\";\""+rs.getString("pemeriksaan_kebidanan_ket_ctg")+"\";\""+rs.getString("pemeriksaan_umum_kepala")+"\";\""+rs.getString("pemeriksaan_umum_muka")+"\";\""+rs.getString("pemeriksaan_umum_mata")+"\";\""+rs.getString("pemeriksaan_umum_hidung")+"\";\""+rs.getString("pemeriksaan_umum_telinga")+"\";\""+rs.getString("pemeriksaan_umum_mulut")+"\";\""+rs.getString("pemeriksaan_umum_leher")+"\";\""+rs.getString("pemeriksaan_umum_dada")+"\";\""+rs.getString("pemeriksaan_umum_perut")+"\";\""+rs.getString("pemeriksaan_umum_genitalia")+"\";\""+rs.getString("pemeriksaan_umum_ekstrimitas")+"\";\""+rs.getString("pengkajian_fungsi_kemampuan_aktifitas")+"\";\""+rs.getString("pengkajian_fungsi_berjalan")+"\";\""+rs.getString("pengkajian_fungsi_ket_berjalan")+"\";\""+rs.getString("pengkajian_fungsi_aktivitas")+"\";\""+rs.getString("pengkajian_fungsi_ambulasi")+"\";\""+rs.getString("pengkajian_fungsi_ekstrimitas_atas")+"\";\""+rs.getString("pengkajian_fungsi_ket_ekstrimitas_atas")+"\";\""+rs.getString("pengkajian_fungsi_ekstrimitas_bawah")+"\";\""+rs.getString("pengkajian_fungsi_ket_ekstrimitas_bawah")+"\";\""+rs.getString("pengkajian_fungsi_kemampuan_menggenggam")+"\";\""+rs.getString("pengkajian_fungsi_ket_kemampuan_menggenggam")+"\";\""+rs.getString("pengkajian_fungsi_koordinasi")+"\";\""+rs.getString("pengkajian_fungsi_ket_koordinasi")+"\";\""+rs.getString("pengkajian_fungsi_gangguan_fungsi")+"\";\""+rs.getString("riwayat_psiko_kondisipsiko")+"\";\""+rs.getString("riwayat_psiko_adakah_prilaku")+"\";\""+rs.getString("riwayat_psiko_ket_adakah_prilaku")+"\";\""+rs.getString("riwayat_psiko_gangguan_jiwa")+"\";\""+rs.getString("riwayat_psiko_hubungan_pasien")+"\";\""+rs.getString("agama")+"\";\""+rs.getString("riwayat_psiko_tinggal_dengan")+"\";\""+rs.getString("riwayat_psiko_ket_tinggal_dengan")+"\";\""+rs.getString("pekerjaan")+"\";\""+rs.getString("png_jawab")+"\";\""+rs.getString("riwayat_psiko_budaya")+"\";\""+rs.getString("riwayat_psiko_ket_budaya")+"\";\""+rs.getString("nama_bahasa")+"\";\""+rs.getString("pnd")+"\";\""+rs.getString("riwayat_psiko_pend_pj")+"\";\""+rs.getString("riwayat_psiko_edukasi_pada")+"\";\""+rs.getString("riwayat_psiko_ket_edukasi_pada")+"\";\""+rs.getString("penilaian_nyeri")+"\";\""+rs.getString("penilaian_nyeri_penyebab")+"\";\""+rs.getString("penilaian_nyeri_ket_penyebab")+"\";\""+rs.getString("penilaian_nyeri_kualitas")+"\";\""+rs.getString("penilaian_nyeri_ket_kualitas")+"\";\""+rs.getString("penilaian_nyeri_lokasi")+"\";\""+rs.getString("penilaian_nyeri_menyebar")+"\";\""+rs.getString("penilaian_nyeri_skala")+"\";\""+rs.getString("penilaian_nyeri_waktu")+"\";\""+rs.getString("penilaian_nyeri_hilang")+"\";\""+rs.getString("penilaian_nyeri_ket_hilang")+"\";\""+rs.getString("penilaian_nyeri_diberitahukan_dokter")+"\";\""+rs.getString("penilaian_nyeri_jam_diberitahukan_dokter")+"\";\""+rs.getString("penilaian_jatuh_skala1")+"\";\""+rs.getString("penilaian_jatuh_nilai1")+"\";\""+rs.getString("penilaian_jatuh_skala2")+"\";\""+rs.getString("penilaian_jatuh_nilai2")+"\";\""+rs.getString("penilaian_jatuh_skala3")+"\";\""+rs.getString("penilaian_jatuh_nilai3")+"\";\""+rs.getString("penilaian_jatuh_skala4")+"\";\""+rs.getString("penilaian_jatuh_nilai4")+"\";\""+rs.getString("penilaian_jatuh_skala5")+"\";\""+rs.getString("penilaian_jatuh_nilai5")+"\";\""+rs.getString("penilaian_jatuh_skala6")+"\";\""+rs.getString("penilaian_jatuh_nilai6")+"\";\""+rs.getString("penilaian_jatuh_totalnilai")+"\";\""+rs.getString("skrining_gizi1")+"\";\""+rs.getString("nilai_gizi1")+"\";\""+rs.getString("skrining_gizi2")+"\";\""+rs.getString("nilai_gizi2")+"\";\""+rs.getString("nilai_total_gizi")+"\";\""+rs.getString("skrining_gizi_diagnosa_khusus")+"\";\""+rs.getString("skrining_gizi_ket_diagnosa_khusus")+"\";\""+rs.getString("skrining_gizi_diketahui_dietisen")+"\";\""+rs.getString("skrining_gizi_jam_diketahui_dietisen")+"\";\""+rs.getString("masalah")+"\";\""+rs.getString("rencana")+"\"\n"
                            );
                        }
                        f = new File("RMPenilaianAwalKeperawatanKebidananRanap.csv");
                        bw = new BufferedWriter(new FileWriter(f));
                        bw.write(htmlContent.toString());

                        bw.close();
                        Desktop.getDesktop().browse(f.toURI());
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Notif : "+e);
                } finally{
                    if(rs!=null){
                        rs.close();
                    }
                    if(ps!=null){
                        ps.close();
                    }
                }
            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
//        if(TNoRM.getText().trim().equals("")){
//            Valid.textKosong(TNoRw,"Nama Pasien");
//        }else if(KdPetugas.getText().trim().equals("")||NmPetugas.getText().trim().equals("")){
//            Valid.textKosong(BtnPetugas,"Pengkaji 1");
//        }else if(Masalah.getText().trim().equals("")){
//            Valid.textKosong(Masalah,"Masalah Kebidanan");
//        }else if(Tindakan.getText().trim().equals("")){
//            Valid.textKosong(Tindakan,"Tindakan");
//        }else{
//            if(tbObat.getSelectedRow()>-1){
//                if(akses.getkode3().equals("Admin Utama")){
//                    ganti();
//                }else{
//                    if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
//                        ganti();
//                    }else{
//                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh petugas yang bersangkutan..!!");
//                    }
//                }
//            }else{
//                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
//            }
//        }
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(KdPetugas.getText().trim().equals("")||NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPetugas,"Pengkaji");
        }else if(KdDPJP.getText().trim().equals("")||NmDPJP.getText().trim().equals("")){
            Valid.textKosong(BtnDPJP,"DPJP");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode3().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()>-1){
            if(akses.getkode3().equals("Admin Utama")){
                hapus();
            }else{
                if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }

    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed

    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
//        // validasi data pasien
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(KdPetugas.getText().trim().equals("")||NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPetugas,"Pengkaji");
        }else if(KdDPJP.getText().trim().equals("")||NmDPJP.getText().trim().equals("")){
            Valid.textKosong(BtnDPJP,"DPJP");
        }else{
            String RiwayatPenyakit=getSelectedPenyakit();
            String MasalahKebidanann=getSelected();
            String StatusMental=getSelectedStatusMental();
            if(Keluhan.getText().trim().equals("")){
                Valid.textKosong(Menarche,"Keluhan");
            }else if(PemeriksaanDalam.getText().trim().equals("")){
                Valid.textKosong(Menikahke,"Pemeriksaan Dalam");
            }else if(HasilUSG.getText().trim().equals("")){
                Valid.textKosong(LamanyaMenikah,"Hasil USG");
            }else if(DiagnosaKebidanan.getText().trim().equals("")){
                Valid.textKosong(LamanyaMenikah,"Diagnosa Kebidanan");
            }else{
                if(Sequel.menyimpantf("ponek_partus","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",88,new String[]{
                    // section 1
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdPetugas.getText(),NmPetugas.getText(),KdDPJP.getText(),NmDPJP.getText(),CaraDatang.getSelectedItem().toString(),
                    CaraDatangLainnya.getText(),Menggunakan.getSelectedItem().toString(),MenggunakanLainnya.getText(),CaraMasuk.getSelectedItem().toString(),DariLainnya.getText(),
                    PengkajianOleh.getSelectedItem().toString(),HubunganDenganPasien.getText(),AlasanMasuk.getText(),
                    // section 2
                    PenyakitDiderita.getSelectedItem().toString(),RiwayatPenyakit,LainnyaInput.getText(),Gamelli.getSelectedItem().toString(),Ketergantungan.getSelectedItem().toString(),InputKetergantungan.getText(),
                    KetergantunganSejak.getText(),ObatObatan.getSelectedItem().toString(),InputObat.getText(),
                    Makanan.getSelectedItem().toString(),InputMakanan.getText(),Debu.getSelectedItem().toString(),InputDebu.getText(),AlergiLainnya.getText(),
                    // section 3
                    Menarche.getText(),Menstruasi.getSelectedItem().toString(),HariMens.getText(),SakitSaatMens.getSelectedItem().toString(),
                    Menikahke.getText(),LamanyaMenikah.getText(),NmKontrasepsi.getText(),KontrasepsiLamanya.getText(),
                    // section 4
                    InputG.getText(),InputP.getText(),InputA.getText(),Valid.SetTgl(HPHT.getSelectedItem()+""),HPL.getText(),UmurKehamilan.getText(),
                    Keluhan.getText(),
                    // section 4.1
                    TinggiFundus.getText(),LetakPunggungJanin.getSelectedItem().toString(),PresentasiJanin.getSelectedItem().toString(),BeratJanin.getText(),Penurunan.getSelectedItem().toString(),Aukultasi.getText(),FrekAukultasi.getSelectedItem().toString(),
                    PemeriksaanDalam.getText(),
                    // section 5
                    TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),TB.getText(),BB.getText(),Keadaan1.getSelectedItem().toString(),GCS.getText(),Kesadaran.getSelectedItem().toString(),InputPenurunanKesadaran.getText(),
                    Kepala.getSelectedItem().toString(),Mata.getSelectedItem().toString(),Hidung.getSelectedItem().toString(),GigiMulut.getSelectedItem().toString(),Tenggorokan.getSelectedItem().toString(),Telinga.getSelectedItem().toString(),Ekstremitas.getSelectedItem().toString(),
                    Leher.getSelectedItem().toString(),Thoraks.getSelectedItem().toString(),Jantung.getSelectedItem().toString(),Paru.getSelectedItem().toString(),Abdomen.getSelectedItem().toString(),Anus.getSelectedItem().toString(),
                    // section 6
                    Nyeri.getSelectedItem().toString(),SkorNyeri.getSelectedItem().toString(),KategoriNyeri.getSelectedItem().toString(),PengaruhNyeri.getSelectedItem().toString(),
                    // section 7
                    HB.getText(),HasilUSG.getText(),
                    // section 8
                    StatusMental,
                    // section 9
                    ResEmosi.getSelectedItem().toString(),SupportSuami.getSelectedItem().toString(),
                    // section 10
                    MasalahKebidanann,MasalahKebidananLainnya.getText(),
                    // section 11
                    DiagnosaKebidanan.getText(),TNoRM.getText()
                })==true){
                    emptTeks();
                }
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
//        Valid.pindah(evt,RR,SPO);
    }//GEN-LAST:event_SuhuKeyPressed

    private void RRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RRKeyPressed
        Valid.pindah(evt,Nadi,Suhu);
    }//GEN-LAST:event_RRKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        Valid.pindah(evt,TD,RR);
    }//GEN-LAST:event_NadiKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        Valid.pindah(evt,BB,Nadi);
    }//GEN-LAST:event_TDKeyPressed

    private void BBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BBKeyPressed
        Valid.pindah(evt,TB,TD);
    }//GEN-LAST:event_BBKeyPressed

    private void TBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBKeyPressed
        Valid.pindah(evt,GCS,BB);
    }//GEN-LAST:event_TBKeyPressed

    private void GCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCSKeyPressed
        Valid.pindah(evt,Kesadaran,TB);
    }//GEN-LAST:event_GCSKeyPressed

    private void Keadaan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Keadaan1KeyPressed
//        Valid.pindah(evt,Alergi,Kesadaran);
    }//GEN-LAST:event_Keadaan1KeyPressed

    private void PenurunanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenurunanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenurunanKeyPressed

    private void AukultasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AukultasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AukultasiActionPerformed

    private void BeratJaninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BeratJaninActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BeratJaninActionPerformed

    private void InputGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputGActionPerformed

    private void HPHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HPHTActionPerformed
        hitungHPL();
        hitungUmurKehamilan();
    }//GEN-LAST:event_HPHTActionPerformed

    private void InputAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputAActionPerformed

    private void InputPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputPActionPerformed

    private void MenarcheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenarcheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenarcheActionPerformed

    private void KontrasepsiLamanyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KontrasepsiLamanyaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KontrasepsiLamanyaActionPerformed

    private void NmKontrasepsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmKontrasepsiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmKontrasepsiActionPerformed

    private void LamanyaMenikahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LamanyaMenikahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LamanyaMenikahActionPerformed

    private void MenikahkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenikahkeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenikahkeActionPerformed

    private void SakitSaatMensKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SakitSaatMensKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SakitSaatMensKeyPressed

    private void SakitSaatMensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SakitSaatMensActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SakitSaatMensActionPerformed

    private void HariMensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HariMensActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HariMensActionPerformed

    private void MenstruasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenstruasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenstruasiKeyPressed

    private void MenstruasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenstruasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenstruasiActionPerformed

    private void TinggiFundusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TinggiFundusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TinggiFundusActionPerformed

    private void AlergiLainnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlergiLainnyaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlergiLainnyaActionPerformed

    private void DebuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DebuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DebuKeyPressed

    private void DebuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DebuActionPerformed
        // TODO add your handling code here:
        showRiwayatAlergi();
    }//GEN-LAST:event_DebuActionPerformed

    private void MakananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MakananKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_MakananKeyPressed

    private void MakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MakananActionPerformed
        // TODO add your handling code here:
        showRiwayatAlergi();
    }//GEN-LAST:event_MakananActionPerformed

    private void ObatObatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObatObatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ObatObatanKeyPressed

    private void ObatObatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ObatObatanActionPerformed
        // TODO add your handling code here:
        showRiwayatAlergi();
    }//GEN-LAST:event_ObatObatanActionPerformed

    private void KetergantunganSejakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KetergantunganSejakActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetergantunganSejakActionPerformed

    private void InputKetergantunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputKetergantunganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputKetergantunganActionPerformed

    private void KetergantunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetergantunganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetergantunganKeyPressed

    private void GamelliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GamelliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GamelliKeyPressed

    private void EpilepsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EpilepsiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EpilepsiActionPerformed

    private void PenyakitKelaminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PenyakitKelaminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitKelaminActionPerformed

    private void DiabetesMelitusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiabetesMelitusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiabetesMelitusActionPerformed

    private void HepatitisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HepatitisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HepatitisActionPerformed

    private void PenyakitThyroidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PenyakitThyroidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitThyroidActionPerformed

    private void PenyakitJantungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PenyakitJantungActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitJantungActionPerformed

    private void PenyakitGinjalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PenyakitGinjalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitGinjalActionPerformed

    private void HypertensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HypertensiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HypertensiActionPerformed

    private void AsthmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AsthmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AsthmaActionPerformed

    private void TBCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TBCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBCActionPerformed

    private void PengkajianOlehKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PengkajianOlehKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PengkajianOlehKeyPressed

    private void AlasanMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlasanMasukActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlasanMasukActionPerformed

    private void PenyakitDideritaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PenyakitDideritaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PenyakitDideritaKeyPressed

    private void PresentasiJaninKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PresentasiJaninKeyPressed
        //        Valid.pindah(evt,FrekAukultasi,KeteranganBerjalan);
    }//GEN-LAST:event_PresentasiJaninKeyPressed

    private void LetakPunggungJaninKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LetakPunggungJaninKeyPressed
        //        Valid.pindah(evt,KeteranganBerjalan,AlatAmbulasi);
    }//GEN-LAST:event_LetakPunggungJaninKeyPressed

    private void LetakPunggungJaninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LetakPunggungJaninActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LetakPunggungJaninActionPerformed

    private void FrekAukultasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FrekAukultasiKeyPressed
        //        Valid.pindah(evt,PemeriksaanEkstrimitas,PresentasiJanin);
    }//GEN-LAST:event_FrekAukultasiKeyPressed

    private void BtnHapusRiwayatPersalinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusRiwayatPersalinanActionPerformed
        if(tbRiwayatKehamilan.getSelectedRow()>-1){
            Sequel.meghapus("riwayat_persalinan_partus","id_riwayat",tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),8).toString());
            tampilPersalinan();
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnHapusRiwayatPersalinanActionPerformed

    private void BtnTambahMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahMasalahActionPerformed
        if(TNoRM.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Pilih terlebih dahulu pasien yang mau dimasukkan data kelarihannya...");
            CaraDatang.requestFocus();
        }else{
            emptTeksPersalinan();
            DlgRiwayatPersalinan.setLocationRelativeTo(internalFrame1);
            DlgRiwayatPersalinan.setVisible(true);
        }
    }//GEN-LAST:event_BtnTambahMasalahActionPerformed

    private void CaraMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CaraMasukKeyPressed
        //        Valid.pindah(evt,Menggunakan,KeluhanUtama);
    }//GEN-LAST:event_CaraMasukKeyPressed

    private void MenggunakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MenggunakanKeyPressed
        Valid.pindah(evt,CaraDatang,CaraMasuk);
    }//GEN-LAST:event_MenggunakanKeyPressed

    private void BtnDPJPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDPJPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDPJPKeyPressed

    private void BtnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDPJPActionPerformed
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDPJPActionPerformed

    private void KdDPJPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDPJPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdDPJPKeyPressed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        //Valid.pindah(evt,Rencana,Informasi);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void TglAsuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TglAsuhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglAsuhanActionPerformed

    private void CaraDatangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CaraDatangKeyPressed
//        Valid.pindah(evt,TanggalPersalinan,Menggunakan);
    }//GEN-LAST:event_CaraDatangKeyPressed

    private void BtnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPetugasKeyPressed
        //Valid.pindah(evt,Monitoring,BtnSimpan);
    }//GEN-LAST:event_BtnPetugasKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        i=1;
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed

    }//GEN-LAST:event_KdPetugasKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{
            Valid.pindah(evt,TCari,BtnPetugas);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void KesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranKeyPressed
        Valid.pindah(evt,DitolongOleh,GCS);
    }//GEN-LAST:event_KesadaranKeyPressed

    private void KepalaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KepalaKeyPressed
//        Valid.pindah(evt,SPO,Mata);
    }//GEN-LAST:event_KepalaKeyPressed

    private void MataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MataKeyPressed
        Valid.pindah(evt,Kepala,Hidung);
    }//GEN-LAST:event_MataKeyPressed

    private void HidungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HidungKeyPressed
        Valid.pindah(evt,Mata,GigiMulut);
    }//GEN-LAST:event_HidungKeyPressed

    private void GigiMulutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GigiMulutKeyPressed
        Valid.pindah(evt,Hidung,Tenggorokan);
    }//GEN-LAST:event_GigiMulutKeyPressed

    private void TenggorokanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TenggorokanKeyPressed
        Valid.pindah(evt,GigiMulut,Telinga);
    }//GEN-LAST:event_TenggorokanKeyPressed

    private void TelingaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TelingaKeyPressed
        Valid.pindah(evt,Tenggorokan,Leher);
    }//GEN-LAST:event_TelingaKeyPressed

    private void LeherKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LeherKeyPressed
        Valid.pindah(evt,Telinga,Thoraks);
    }//GEN-LAST:event_LeherKeyPressed

    private void ThoraksKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThoraksKeyPressed
        Valid.pindah(evt,Leher,Jantung);
    }//GEN-LAST:event_ThoraksKeyPressed

    private void JantungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JantungKeyPressed
        Valid.pindah(evt,Thoraks,Paru);
    }//GEN-LAST:event_JantungKeyPressed

    private void ParuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ParuKeyPressed
        Valid.pindah(evt,Jantung,Abdomen);
    }//GEN-LAST:event_ParuKeyPressed

    private void AbdomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AbdomenKeyPressed
//        Valid.pindah(evt,Ekstremitas,KetFisik);
    }//GEN-LAST:event_AbdomenKeyPressed

    private void AnusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AnusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AnusKeyPressed

    private void EkstremitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EkstremitasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EkstremitasKeyPressed

    private void HBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HBActionPerformed

    private void OrientasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrientasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OrientasiActionPerformed

    private void KooperatifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KooperatifActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KooperatifActionPerformed

    private void SupportSuamiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupportSuamiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SupportSuamiActionPerformed

    private void RisikoPendarahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RisikoPendarahanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RisikoPendarahanActionPerformed

    private void nyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nyeriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nyeriActionPerformed

    private void cemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cemasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cemasActionPerformed

    private void PerubahanNutrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PerubahanNutrisiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PerubahanNutrisiActionPerformed

    private void cekBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekBox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekBox6ActionPerformed

    private void eliminasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminasiActionPerformed

    private void PengetahuanKomunikasiInformasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PengetahuanKomunikasiInformasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PengetahuanKomunikasiInformasiActionPerformed

    private void PenyakitDideritaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PenyakitDideritaActionPerformed
        // TODO add your handling code here:
        isAda();
    }//GEN-LAST:event_PenyakitDideritaActionPerformed

    private void KetergantunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KetergantunganActionPerformed
        isKetergantugan();
    }//GEN-LAST:event_KetergantunganActionPerformed

    private void InputDebuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputDebuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputDebuActionPerformed

    private void InputObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputObatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputObatActionPerformed

    private void InputMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputMakananActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputMakananActionPerformed

    private void CaraDatangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaraDatangActionPerformed
        // TODO add your handling code here:
        isDatangLainnya();
    }//GEN-LAST:event_CaraDatangActionPerformed

    private void MenggunakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenggunakanActionPerformed
        // TODO add your handling code here:
        isMenggunakanLainnya();
    }//GEN-LAST:event_MenggunakanActionPerformed

    private void CaraMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CaraMasukActionPerformed
        // TODO add your handling code here:
        isMasukLainnya();
    }//GEN-LAST:event_CaraMasukActionPerformed

    private void PengkajianOlehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PengkajianOlehActionPerformed
        // TODO add your handling code here:
        pengkajiOleh();
    }//GEN-LAST:event_PengkajianOlehActionPerformed

    private void InputPenurunanKesadaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputPenurunanKesadaranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputPenurunanKesadaranActionPerformed

    private void KesadaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KesadaranActionPerformed
        // TODO add your handling code here:
        cekKesadaran();
    }//GEN-LAST:event_KesadaranActionPerformed

    private void NyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NyeriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NyeriActionPerformed

    private void HPLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HPLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HPLActionPerformed

    private void HPHTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_HPHTItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_HPHTItemStateChanged

    private void UmurKehamilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UmurKehamilanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UmurKehamilanActionPerformed

    private void BtnEditRiwayatPersalinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditRiwayatPersalinanActionPerformed
        // TODO add your handling code here:
         if(TNoRM.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Pilih terlebih dahulu pasien yang mau dimasukkan data kelarihannya...");
            CaraDatang.requestFocus();
        }else{
            emptTeksPersalinan();
            DlgRiwayatPersalinan.setLocationRelativeTo(internalFrame1);
            DlgRiwayatPersalinan.setVisible(true);
            if(tbRiwayatKehamilan.getSelectedRow()!= -1){
                 UmurAnak.setText(tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),2).toString());
                 KUAnak.setText(tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),3).toString());
                 RiwayatPersalinan.setText(tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),5).toString());
                 DitolongOleh.setText(tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),6).toString());
                 switch (tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),1).toString()){
                     case "L":
                         JK.setSelectedItem("Laki-Laki");
                         break;
                     case "P":
                         JK.setSelectedItem("Perempuan");
                         break;
                 }
                 BBL.setText(tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),4).toString());
             }
        }
    }//GEN-LAST:event_BtnEditRiwayatPersalinanActionPerformed

    private void BtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit1ActionPerformed
        // TODO add your handling code here:
         if(UmurAnak.getText().trim().equals("")){
            Valid.textKosong(UmurAnak,"Tempat Persalinan");
        }else if(KUAnak.getText().trim().equals("")){
            Valid.textKosong(KUAnak,"Jenis Persalinan");
        }else if(RiwayatPersalinan.getText().trim().equals("")){
            Valid.textKosong(RiwayatPersalinan,"Penyulit Persalinan");
        }else if(DitolongOleh.getText().trim().equals("")){
            Valid.textKosong(DitolongOleh,"Keadaan Persalinan");
        }else if(BBL.getText().trim().equals("")){
            Valid.textKosong(BBL,"BB/PB");
        }else{
            if(Sequel.mengedittf("riwayat_persalinan_partus","id_riwayat=?","no_rkm_medis=?,umur_anak=?,ku_anak=?,riwayat_persalinan=?,ditolong_oleh=?,jk=?,bbl=?",8,new String[]{
                TNoRM.getText(),
                UmurAnak.getText(),
                KUAnak.getText(),
                RiwayatPersalinan.getText(),
                DitolongOleh.getText(),
                JK.getSelectedItem().toString().substring(0,1),
                BBL.getText(),
                tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),8).toString()
            })==true){
                emptTeksPersalinan();
                tampilPersalinan();
                DlgRiwayatPersalinan.dispose();
            }
        }
    }//GEN-LAST:event_BtnEdit1ActionPerformed

    private void BtnEdit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEdit1KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenAwalKebidananIGDPartus dialog = new RMAsesmenAwalKebidananIGDPartus(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.ComboBox Abdomen;
    private widget.CekBox Agitasi;
    private widget.TextBox AlasanMasuk;
    private widget.TextBox AlergiLainnya;
    private widget.ComboBox Anus;
    private widget.CekBox Asthma;
    private widget.TextBox Aukultasi;
    private widget.TextBox BB;
    private widget.TextBox BBL;
    private widget.TextBox BeratJanin;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDPJP;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit1;
    private widget.Button BtnEditRiwayatPersalinan;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusRiwayatPersalinan;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluarKehamilan;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanRiwayatKehamilan;
    private widget.Button BtnTambahMasalah;
    private widget.ComboBox CaraDatang;
    private widget.TextBox CaraDatangLainnya;
    private widget.ComboBox CaraMasuk;
    private widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox DariLainnya;
    private widget.ComboBox Debu;
    private widget.CekBox DiabetesMelitus;
    private widget.TextArea DiagnosaKebidanan;
    private widget.TextArea DiagnosaKebidanan1;
    private widget.CekBox Disorientasi;
    private widget.TextBox DitolongOleh;
    private javax.swing.JDialog DlgRiwayatPersalinan;
    private widget.ComboBox Ekstremitas;
    private widget.CekBox Epilepsi;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMasalahRencana;
    private widget.PanelBiasa FormMenu;
    private widget.ComboBox FrekAukultasi;
    private widget.TextBox GCS;
    private widget.ComboBox Gamelli;
    private widget.ComboBox GigiMulut;
    private widget.TextBox HB;
    private widget.Tanggal HPHT;
    private widget.TextBox HPL;
    private widget.TextBox HariMens;
    private widget.TextArea HasilUSG;
    private widget.CekBox Hepatitis;
    private widget.ComboBox Hidung;
    private widget.TextBox HubunganDenganPasien;
    private widget.CekBox Hypertensi;
    private widget.TextBox InputA;
    private widget.TextBox InputDebu;
    private widget.TextBox InputG;
    private widget.TextBox InputKetergantungan;
    private widget.TextBox InputMakanan;
    private widget.TextBox InputObat;
    private widget.TextBox InputP;
    private widget.TextBox InputPenurunanKesadaran;
    private widget.ComboBox JK;
    private widget.ComboBox Jantung;
    private widget.TextBox Jk;
    private widget.TextBox KUAnak;
    private widget.ComboBox KategoriNyeri;
    private widget.TextBox KdDPJP;
    private widget.TextBox KdPetugas;
    private widget.ComboBox Keadaan1;
    private widget.TextArea Keluhan;
    private widget.ComboBox Kepala;
    private widget.ComboBox Kesadaran;
    private widget.ComboBox Ketergantungan;
    private widget.TextBox KetergantunganSejak;
    private widget.TextBox KontrasepsiLamanya;
    private widget.CekBox Kooperatif;
    private widget.Label LCount;
    private widget.TextBox LainnyaInput;
    private widget.TextBox LamanyaMenikah;
    private widget.ComboBox Leher;
    private widget.ComboBox LetakPunggungJanin;
    private widget.ComboBox Makanan;
    private widget.TextBox MasalahKebidananLainnya;
    private widget.ComboBox Mata;
    private widget.TextBox Menarche;
    private widget.ComboBox Menggunakan;
    private widget.TextBox MenggunakanLainnya;
    private widget.TextBox Menikahke;
    private widget.ComboBox Menstruasi;
    private widget.TextBox Nadi;
    private widget.TextBox NmDPJP;
    private widget.TextBox NmKontrasepsi;
    private widget.TextBox NmPetugas;
    private widget.ComboBox Nyeri;
    private widget.ComboBox ObatObatan;
    private widget.CekBox Orientasi;
    private widget.PanelBiasa PanelAccor;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ComboBox Paru;
    private widget.TextArea PemeriksaanDalam;
    private widget.ComboBox PengaruhNyeri;
    private widget.CekBox PengetahuanKomunikasiInformasi;
    private widget.ComboBox PengkajianOleh;
    private widget.ComboBox Penurunan;
    private widget.ComboBox PenyakitDiderita;
    private widget.CekBox PenyakitGinjal;
    private widget.CekBox PenyakitJantung;
    private widget.CekBox PenyakitKelamin;
    private widget.CekBox PenyakitThyroid;
    private widget.CekBox PerubahanNutrisi;
    private widget.ComboBox PresentasiJanin;
    private widget.TextBox RR;
    private widget.ComboBox ResEmosi;
    private widget.CekBox RisikoPendarahan;
    private widget.TextBox RiwayatPersalinan;
    private widget.ComboBox SakitSaatMens;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll6;
    private widget.ComboBox SkorNyeri;
    private widget.TextBox Suhu;
    private widget.ComboBox SupportSuami;
    private widget.TextBox TB;
    private widget.CekBox TBC;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private javax.swing.JTabbedPane TabRawat;
    private widget.ComboBox Telinga;
    private widget.ComboBox Tenggorokan;
    private widget.Tanggal TglAsuhan;
    private widget.TextBox TglLahir;
    private widget.ComboBox Thoraks;
    private widget.CekBox TidakRespon;
    private widget.TextBox TinggiFundus;
    private widget.TextBox UmurAnak;
    private widget.TextBox UmurKehamilan;
    private widget.CekBox cekBox2;
    private widget.CekBox cekBox6;
    private widget.CekBox cemas;
    private widget.CekBox eliminasi;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel108;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel129;
    private widget.Label jLabel13;
    private widget.Label jLabel131;
    private widget.Label jLabel15;
    private widget.Label jLabel153;
    private widget.Label jLabel16;
    private widget.Label jLabel167;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel200;
    private widget.Label jLabel201;
    private widget.Label jLabel202;
    private widget.Label jLabel203;
    private widget.Label jLabel204;
    private widget.Label jLabel205;
    private widget.Label jLabel21;
    private widget.Label jLabel212;
    private widget.Label jLabel213;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel28;
    private widget.Label jLabel34;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel73;
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private widget.Label label1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label14;
    private widget.Label label16;
    private widget.Label label2;
    private widget.Label label3;
    private widget.Label label4;
    private widget.Label label5;
    private widget.Label label6;
    private widget.Label label7;
    private widget.Label label8;
    private widget.Label label9;
    private widget.CekBox nyeri;
    private widget.PanelBiasa panelBiasa2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelInputan;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.panelisi panelisi3;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbObat;
    private widget.Table tbRiwayatKehamilan;
    private widget.Table tbRiwayatKehamilan1;
    // End of variables declaration//GEN-END:variables

//    private void tampil() {
//        Valid.tabelKosong(tabMode);
//        try{
//            ps=koneksi.prepareStatement(
//                "select penilaian_awal_keperawatan_kebidanan_ranap.no_rawat,penilaian_awal_keperawatan_kebidanan_ranap.tanggal,penilaian_awal_keperawatan_kebidanan_ranap.informasi,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.tiba_diruang_rawat,penilaian_awal_keperawatan_kebidanan_ranap.cara_masuk,penilaian_awal_keperawatan_kebidanan_ranap.keluhan,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.rpk,penilaian_awal_keperawatan_kebidanan_ranap.psk,penilaian_awal_keperawatan_kebidanan_ranap.rp,penilaian_awal_keperawatan_kebidanan_ranap.alergi,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.komplikasi_sebelumnya,penilaian_awal_keperawatan_kebidanan_ranap.keterangan_komplikasi_sebelumnya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_umur,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_banyaknya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_siklus,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_ket_siklus,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_mens_dirasakan,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_status,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_status,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia1,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia1,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia2,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia2,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_usia3,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_perkawinan_ket_usia3,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_g,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_p,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_a,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_persalinan_hidup,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_hpht,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_usiahamil,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_tp,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_imunisasi,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_anc,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_ancke,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_ket_ancke,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_keluhan_hamil_muda,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_hamil_keluhan_hamil_tua,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_komplikasi,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_ket_komplikasi,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_kapaberhenti,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kb_alasanberhenti,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_genekologi,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_obat,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_obat,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_merokok,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_merokok,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_alkohol,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_ket_alkohol,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_kebiasaan_narkoba,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_mental,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_keadaan_umum,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_gcs,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_td,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_nadi,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_rr,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_suhu,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_spo2,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_bb,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tb,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lila,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tfu,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_tbj,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_letak,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_presentasi,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_penurunan,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_his,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_kekuatan,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lamanya,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_djj,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_djj,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_portio,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_pembukaan,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ketuban,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_hodge,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_panggul,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_inspekulo,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_inspekulo,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_lakmus,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_lakmus,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ctg,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_kebidanan_ket_ctg,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_kepala,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_muka,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_mata,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_hidung,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_telinga,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_mulut,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_leher,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_dada,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_perut,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_genitalia,penilaian_awal_keperawatan_kebidanan_ranap.pemeriksaan_umum_ekstrimitas,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_kemampuan_aktifitas,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_berjalan,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_berjalan,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_aktivitas,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ambulasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ekstrimitas_atas,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_ekstrimitas_atas,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ekstrimitas_bawah,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_ekstrimitas_bawah,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_kemampuan_menggenggam,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_kemampuan_menggenggam,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_koordinasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_ket_koordinasi,penilaian_awal_keperawatan_kebidanan_ranap.pengkajian_fungsi_gangguan_fungsi,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_kondisipsiko,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_adakah_prilaku,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_adakah_prilaku,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_gangguan_jiwa,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_hubungan_pasien,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_tinggal_dengan,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_tinggal_dengan,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_budaya,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_budaya,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_pend_pj,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_edukasi_pada,penilaian_awal_keperawatan_kebidanan_ranap.riwayat_psiko_ket_edukasi_pada,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_penyebab,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_penyebab,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_kualitas,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_kualitas,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_lokasi,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_menyebar,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_skala,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_waktu,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_hilang,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_ket_hilang,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_diberitahukan_dokter,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_nyeri_jam_diberitahukan_dokter,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala1,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai1,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala2,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai2,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala3,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai3,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala4,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai4,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala5,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai5,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_skala6,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_nilai6,penilaian_awal_keperawatan_kebidanan_ranap.penilaian_jatuh_totalnilai,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi1,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.nilai_gizi1,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi2,penilaian_awal_keperawatan_kebidanan_ranap.nilai_gizi2,bahasa_pasien.nama_bahasa,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.nilai_total_gizi,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_diagnosa_khusus,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_ket_diagnosa_khusus,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_diketahui_dietisen,penilaian_awal_keperawatan_kebidanan_ranap.skrining_gizi_jam_diketahui_dietisen,penilaian_awal_keperawatan_kebidanan_ranap.masalah,"+
//                "penilaian_awal_keperawatan_kebidanan_ranap.rencana,penilaian_awal_keperawatan_kebidanan_ranap.nip1,penilaian_awal_keperawatan_kebidanan_ranap.nip2,penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter, "+
//                "pasien.tgl_lahir,pasien.jk,pengkaji1.nama as pengkaji1,pengkaji2.nama as pengkaji2,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.agama,pasien.pekerjaan,pasien.pnd,penjab.png_jawab "+
//                "from reg_periksa inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
//                "inner join penilaian_awal_keperawatan_kebidanan_ranap on reg_periksa.no_rawat=penilaian_awal_keperawatan_kebidanan_ranap.no_rawat "+
//                "inner join petugas as pengkaji1 on penilaian_awal_keperawatan_kebidanan_ranap.nip1=pengkaji1.nip "+
//                "inner join petugas as pengkaji2 on penilaian_awal_keperawatan_kebidanan_ranap.nip2=pengkaji2.nip "+
//                "inner join dokter on penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter=dokter.kd_dokter "+
//                "inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
//                "inner join penjab on penjab.kd_pj=reg_periksa.kd_pj where "+
//                "penilaian_awal_keperawatan_kebidanan_ranap.tanggal between ? and ? "+
//                 (TCari.getText().trim().equals("")?"":"and (reg_periksa.no_rawat like ? or pasien.no_rkm_medis like ? or pasien.nm_pasien like ? or penilaian_awal_keperawatan_kebidanan_ranap.nip1 like ? or pengkaji1.nama like ? or penilaian_awal_keperawatan_kebidanan_ranap.kd_dokter like ? or dokter.nm_dokter like ?)")+
//                 " order by penilaian_awal_keperawatan_kebidanan_ranap.tanggal");
//            
//            try {
//                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
//                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
//                if(!TCari.getText().equals("")){
//                    ps.setString(3,"%"+TCari.getText()+"%");
//                    ps.setString(4,"%"+TCari.getText()+"%");
//                    ps.setString(5,"%"+TCari.getText()+"%");
//                    ps.setString(6,"%"+TCari.getText()+"%");
//                    ps.setString(7,"%"+TCari.getText()+"%");
//                    ps.setString(8,"%"+TCari.getText()+"%");
//                    ps.setString(9,"%"+TCari.getText()+"%");
//                }   
//                rs=ps.executeQuery();
//                while(rs.next()){
//                    tabMode.addRow(new String[]{
//                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("jk"),rs.getString("nip1"),rs.getString("pengkaji1"),rs.getString("nip2"),rs.getString("pengkaji2"),
//                        rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("tanggal"),rs.getString("informasi"),rs.getString("tiba_diruang_rawat"),rs.getString("cara_masuk"),rs.getString("keluhan"),rs.getString("psk"),rs.getString("rpk"),
//                        rs.getString("rp"),rs.getString("alergi"),rs.getString("komplikasi_sebelumnya"),rs.getString("keterangan_komplikasi_sebelumnya"),rs.getString("riwayat_mens_umur"),rs.getString("riwayat_mens_lamanya"),rs.getString("riwayat_mens_banyaknya"),
//                        rs.getString("riwayat_mens_siklus"),rs.getString("riwayat_mens_ket_siklus"),rs.getString("riwayat_mens_dirasakan"),rs.getString("riwayat_perkawinan_status"),rs.getString("riwayat_perkawinan_ket_status"),rs.getString("riwayat_perkawinan_usia1"),
//                        rs.getString("riwayat_perkawinan_ket_usia1"),rs.getString("riwayat_perkawinan_usia2"),rs.getString("riwayat_perkawinan_ket_usia2"),rs.getString("riwayat_perkawinan_usia3"),rs.getString("riwayat_perkawinan_ket_usia3"),
//                        rs.getString("riwayat_persalinan_g"),rs.getString("riwayat_persalinan_p"),rs.getString("riwayat_persalinan_a"),rs.getString("riwayat_persalinan_hidup"),rs.getString("riwayat_hamil_hpht"),rs.getString("riwayat_hamil_usiahamil"),
//                        rs.getString("riwayat_hamil_tp"),rs.getString("riwayat_hamil_imunisasi"),rs.getString("riwayat_hamil_anc"),rs.getString("riwayat_hamil_ancke"),rs.getString("riwayat_hamil_ket_ancke"),rs.getString("riwayat_hamil_keluhan_hamil_muda"),
//                        rs.getString("riwayat_hamil_keluhan_hamil_tua"),rs.getString("riwayat_kb"),rs.getString("riwayat_kb_lamanya"),rs.getString("riwayat_kb_komplikasi"),rs.getString("riwayat_kb_ket_komplikasi"),rs.getString("riwayat_kb_kapaberhenti"),
//                        rs.getString("riwayat_kb_alasanberhenti"),rs.getString("riwayat_genekologi"),rs.getString("riwayat_kebiasaan_obat"),rs.getString("riwayat_kebiasaan_ket_obat"),rs.getString("riwayat_kebiasaan_merokok"),rs.getString("riwayat_kebiasaan_ket_merokok"),
//                        rs.getString("riwayat_kebiasaan_alkohol"),rs.getString("riwayat_kebiasaan_ket_alkohol"),rs.getString("riwayat_kebiasaan_narkoba"),rs.getString("pemeriksaan_kebidanan_mental"),rs.getString("pemeriksaan_kebidanan_keadaan_umum"),
//                        rs.getString("pemeriksaan_kebidanan_gcs"),rs.getString("pemeriksaan_kebidanan_td"),rs.getString("pemeriksaan_kebidanan_nadi"),rs.getString("pemeriksaan_kebidanan_rr"),rs.getString("pemeriksaan_kebidanan_suhu"),
//                        rs.getString("pemeriksaan_kebidanan_spo2"),rs.getString("pemeriksaan_kebidanan_bb"),rs.getString("pemeriksaan_kebidanan_tb"),rs.getString("pemeriksaan_kebidanan_lila"),rs.getString("pemeriksaan_kebidanan_tfu"),
//                        rs.getString("pemeriksaan_kebidanan_tbj"),rs.getString("pemeriksaan_kebidanan_letak"),rs.getString("pemeriksaan_kebidanan_presentasi"),rs.getString("pemeriksaan_kebidanan_penurunan"),rs.getString("pemeriksaan_kebidanan_penurunan"),
//                        rs.getString("pemeriksaan_kebidanan_his"),rs.getString("pemeriksaan_kebidanan_kekuatan"),rs.getString("pemeriksaan_kebidanan_lamanya"),rs.getString("pemeriksaan_kebidanan_djj"),rs.getString("pemeriksaan_kebidanan_ket_djj"),
//                        rs.getString("pemeriksaan_kebidanan_portio"),rs.getString("pemeriksaan_kebidanan_pembukaan"),rs.getString("pemeriksaan_kebidanan_ketuban"),rs.getString("pemeriksaan_kebidanan_hodge"),rs.getString("pemeriksaan_kebidanan_panggul"),
//                        rs.getString("pemeriksaan_kebidanan_inspekulo"),rs.getString("pemeriksaan_kebidanan_ket_inspekulo"),rs.getString("pemeriksaan_kebidanan_lakmus"),rs.getString("pemeriksaan_kebidanan_ket_lakmus"),rs.getString("pemeriksaan_kebidanan_ctg"),
//                        rs.getString("pemeriksaan_kebidanan_ket_ctg"),rs.getString("pemeriksaan_umum_kepala"),rs.getString("pemeriksaan_umum_muka"),rs.getString("pemeriksaan_umum_mata"),rs.getString("pemeriksaan_umum_hidung"),rs.getString("pemeriksaan_umum_telinga"),
//                        rs.getString("pemeriksaan_umum_mulut"),rs.getString("pemeriksaan_umum_leher"),rs.getString("pemeriksaan_umum_dada"),rs.getString("pemeriksaan_umum_perut"),rs.getString("pemeriksaan_umum_genitalia"),rs.getString("pemeriksaan_umum_ekstrimitas"),
//                        rs.getString("pengkajian_fungsi_kemampuan_aktifitas"),rs.getString("pengkajian_fungsi_berjalan"),rs.getString("pengkajian_fungsi_ket_berjalan"),rs.getString("pengkajian_fungsi_aktivitas"),rs.getString("pengkajian_fungsi_ambulasi"),
//                        rs.getString("pengkajian_fungsi_ekstrimitas_atas"),rs.getString("pengkajian_fungsi_ket_ekstrimitas_atas"),rs.getString("pengkajian_fungsi_ekstrimitas_bawah"),rs.getString("pengkajian_fungsi_ket_ekstrimitas_bawah"),
//                        rs.getString("pengkajian_fungsi_kemampuan_menggenggam"),rs.getString("pengkajian_fungsi_ket_kemampuan_menggenggam"),rs.getString("pengkajian_fungsi_koordinasi"),rs.getString("pengkajian_fungsi_ket_koordinasi"),
//                        rs.getString("pengkajian_fungsi_gangguan_fungsi"),rs.getString("riwayat_psiko_kondisipsiko"),rs.getString("riwayat_psiko_adakah_prilaku"),rs.getString("riwayat_psiko_ket_adakah_prilaku"),rs.getString("riwayat_psiko_gangguan_jiwa"),
//                        rs.getString("riwayat_psiko_hubungan_pasien"),rs.getString("agama"),rs.getString("riwayat_psiko_tinggal_dengan"),rs.getString("riwayat_psiko_ket_tinggal_dengan"),rs.getString("pekerjaan"),rs.getString("png_jawab"),
//                        rs.getString("riwayat_psiko_budaya"),rs.getString("riwayat_psiko_ket_budaya"),rs.getString("nama_bahasa"),rs.getString("pnd"),rs.getString("riwayat_psiko_pend_pj"),rs.getString("riwayat_psiko_edukasi_pada"),
//                        rs.getString("riwayat_psiko_ket_edukasi_pada"),rs.getString("penilaian_nyeri"),rs.getString("penilaian_nyeri_penyebab"),rs.getString("penilaian_nyeri_ket_penyebab"),rs.getString("penilaian_nyeri_kualitas"),
//                        rs.getString("penilaian_nyeri_ket_kualitas"),rs.getString("penilaian_nyeri_lokasi"),rs.getString("penilaian_nyeri_menyebar"),rs.getString("penilaian_nyeri_skala"),rs.getString("penilaian_nyeri_waktu"),rs.getString("penilaian_nyeri_hilang"),
//                        rs.getString("penilaian_nyeri_ket_hilang"),rs.getString("penilaian_nyeri_diberitahukan_dokter"),rs.getString("penilaian_nyeri_jam_diberitahukan_dokter"),rs.getString("penilaian_jatuh_skala1"),rs.getString("penilaian_jatuh_nilai1"),
//                        rs.getString("penilaian_jatuh_skala2"),rs.getString("penilaian_jatuh_nilai2"),rs.getString("penilaian_jatuh_skala3"),rs.getString("penilaian_jatuh_nilai3"),rs.getString("penilaian_jatuh_skala4"),rs.getString("penilaian_jatuh_nilai4"),
//                        rs.getString("penilaian_jatuh_skala5"),rs.getString("penilaian_jatuh_nilai5"),rs.getString("penilaian_jatuh_skala6"),rs.getString("penilaian_jatuh_nilai6"),rs.getString("penilaian_jatuh_totalnilai"),rs.getString("skrining_gizi1"),
//                        rs.getString("nilai_gizi1"),rs.getString("skrining_gizi2"),rs.getString("nilai_gizi2"),rs.getString("nilai_total_gizi"),rs.getString("skrining_gizi_diagnosa_khusus"),rs.getString("skrining_gizi_ket_diagnosa_khusus"),
//                        rs.getString("skrining_gizi_diketahui_dietisen"),rs.getString("skrining_gizi_jam_diketahui_dietisen"),rs.getString("masalah"),rs.getString("rencana")
//                    });
//                }
//            } catch (Exception e) {
//                System.out.println("Notif : "+e);
//            } finally{
//                if(rs!=null){
//                    rs.close();
//                }
//                if(ps!=null){
//                    ps.close();
//                }
//            }
//            
//        }catch(Exception e){
//            System.out.println("Notifikasi : "+e);
//        }
//        LCount.setText(""+tabMode.getRowCount());
//    }
    
    // function tampil baru
    private void tampil(){
        Valid.tabelKosong(tabMode);
        try {
            ps=koneksi.prepareStatement(
                "select ponek_partus.no_rawat,ponek_partus.no_rm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,ponek_partus.tanggal,ponek_partus.tanggal,ponek_partus.kd_petugas,ponek_partus.nm_petugas,ponek_partus.kd_dpjp,ponek_partus.nm_dpjp, "+
                "ponek_partus.cara_datang,ponek_partus.cara_datang_lainnya,ponek_partus.menggunakan,ponek_partus.menggunakan_lainnya,ponek_partus.asal,ponek_partus.asal_lainnya, "+
                "ponek_partus.pengkajian_dari,ponek_partus.hubungan_dengan_pasien,ponek_partus.alasan_masuk,ponek_partus.penyakit_pernah_diderita, "+
                "concat(ponek_partus.nama_riwayat_penyakit,ponek_partus.penyakit_lainnya) as riwayat_penyakit, "+
                "ponek_partus.faktor_keturunan_gamelli,ponek_partus.ketergantungan,ponek_partus.ketergantungan_dengan,ponek_partus.sejak,ponek_partus.obat_obatan, "+
                "ponek_partus.nama_obatan,ponek_partus.makanan,ponek_partus.nama_makanan,ponek_partus.debu,ponek_partus.nama_debu,ponek_partus.alergi_lainnya,ponek_partus.menarche,ponek_partus.menstruasi, "+
                "ponek_partus.sejak_menstruasi,ponek_partus.sakit_saat_menstruasi,ponek_partus.menikah_ke,ponek_partus.lamanya_pernikahan,ponek_partus.kontrasepsi,ponek_partus.lamanya_kontrasepsi, "+
                "ponek_partus.graphit,ponek_partus.paritas,ponek_partus.abortus,ponek_partus.haid_terakhir,ponek_partus.perkiraan_lahir,ponek_partus.umur_kehamilan,ponek_partus.keluhan_kehamilan, "+
                "ponek_partus.tinggi_fundus_uteri,ponek_partus.letak_punggung_janin,ponek_partus.presentasi_janin,ponek_partus.taksiran_berat_janin,ponek_partus.penurunan,ponek_partus.aukultasi, "+
                "ponek_partus.frekuensi_aukultasi,ponek_partus.pemeriksaan_dalam,ponek_partus.td,ponek_partus.nadi,ponek_partus.rr,ponek_partus.suhu,ponek_partus.tb,ponek_partus.bb, "+
                "ponek_partus.keadaan_umum,ponek_partus.gcs,ponek_partus.kesadaran,ponek_partus.input_penurunan_kesadaran,ponek_partus.kepala,ponek_partus.mata,ponek_partus.hidung,ponek_partus.gigi_mulut, "+
                "ponek_partus.tenggorokan,ponek_partus.telinga,ponek_partus.ekstremitas,ponek_partus.leher,ponek_partus.thoraks,ponek_partus.jantung,ponek_partus.paru,ponek_partus.abdomen, "+
                "ponek_partus.genitalis_anus,ponek_partus.nyeri,ponek_partus.skor,ponek_partus.kategori,ponek_partus.pengaruh_nyeri,ponek_partus.hb,ponek_partus.hasil_usg, "+
                "ponek_partus.status_mental,ponek_partus.respon_emosi,ponek_partus.suport_suami,concat(ponek_partus.masalah_kebidanan,ponek_partus.masalah_kebidanan_lainnya) as masalah_kebidanann,ponek_partus.diagnosa_kebidanan "+
                "from ponek_partus inner join pasien on ponek_partus.no_rm_medis=pasien.no_rkm_medis where "+
                "ponek_partus.tanggal between ? and ? "+
                (TCari.getText().trim().equals("")?"":"and (ponek_partus.no_rawat like ? or ponek_partus.no_rm_medis like ? or pasien.nm_pasien like ? or ponek_partus.nm_petugas like ? or ponek_partus.nm_dpjp like ?)")+
                " order by ponek_partus.tanggal");
            
            try{
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                if(!TCari.getText().trim().equals("")){
                    ps.setString(3, "%"+TCari.getText().trim()+"%");
                    ps.setString(4, "%"+TCari.getText().trim()+"%");
                    ps.setString(5, "%"+TCari.getText().trim()+"%");
                    ps.setString(6, "%"+TCari.getText().trim()+"%");
                    ps.setString(7, "%"+TCari.getText().trim()+"%");
                }
                rs=ps.executeQuery();
                while(rs.next()){
                    String JK="";
                    switch(rs.getString("jk")){
                        case "P":
                            JK="Perempuan";
                            break;
                        case "L":
                            JK="Laki-Laki";
                            break;
                    }
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),JK,rs.getString("tanggal"),rs.getString("kd_petugas"),rs.getString("nm_petugas"),rs.getString("kd_dpjp"),rs.getString("nm_dpjp"),
                        concatColumn(rs.getString("cara_datang"),rs.getString("cara_datang_lainnya")),concatColumn(rs.getString("menggunakan"),rs.getString("menggunakan_lainnya")),concatColumn(rs.getString("asal"),rs.getString("asal_lainnya")),concatColumn2(rs.getString("pengkajian_dari"),rs.getString("hubungan_dengan_pasien")),
                        rs.getString("alasan_masuk"),rs.getString("penyakit_pernah_diderita"),rs.getString("riwayat_penyakit"),rs.getString("faktor_keturunan_gamelli"),
                        concatColumn3(rs.getString("ketergantungan"),rs.getString("ketergantungan_dengan"),rs.getString("sejak")),concatColumn4(rs.getString("obat_obatan"),rs.getString("nama_obatan")),
                        concatColumn4(rs.getString("makanan"),rs.getString("nama_makanan")),concatColumn4(rs.getString("debu"),rs.getString("nama_debu")),rs.getString("alergi_lainnya"),rs.getString("menarche"),
                        rs.getString("menstruasi"),rs.getString("sejak_menstruasi"),rs.getString("sakit_saat_menstruasi"),rs.getString("menikah_ke"),rs.getString("lamanya_pernikahan"),rs.getString("kontrasepsi"),rs.getString("lamanya_kontrasepsi"),
                        rs.getString("graphit"),rs.getString("paritas"),rs.getString("abortus"),rs.getString("haid_terakhir"),rs.getString("perkiraan_lahir"),rs.getString("umur_kehamilan"),rs.getString("keluhan_kehamilan"),
                        rs.getString("tinggi_fundus_uteri"),rs.getString("letak_punggung_janin"),rs.getString("presentasi_janin"),rs.getString("taksiran_berat_janin"),rs.getString("penurunan"),rs.getString("aukultasi"),
                        rs.getString("frekuensi_aukultasi"),rs.getString("pemeriksaan_dalam"),rs.getString("td"),rs.getString("nadi"),rs.getString("rr"),rs.getString("suhu"),rs.getString("tb"),rs.getString("bb"),
                        rs.getString("keadaan_umum"),rs.getString("gcs"),concatColumn5(rs.getString("kesadaran"),rs.getString("input_penurunan_kesadaran")),rs.getString("kepala"),rs.getString("mata"),rs.getString("hidung"),
                        rs.getString("gigi_mulut"),rs.getString("tenggorokan"),rs.getString("telinga"),rs.getString("ekstremitas"),rs.getString("leher"),rs.getString("thoraks"),rs.getString("jantung"),rs.getString("paru"),
                        rs.getString("abdomen"),rs.getString("genitalis_anus"),rs.getString("nyeri"),rs.getString("skor"),rs.getString("kategori"),rs.getString("pengaruh_nyeri"),rs.getString("hb"),rs.getString("hasil_usg"),
                        rs.getString("status_mental"),rs.getString("respon_emosi"),rs.getString("suport_suami"),rs.getString("masalah_kebidanann"),rs.getString("diagnosa_kebidanan")
                    });
                }
            }catch (Exception e){
                System.out.println("Notif : "+e);
            }finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Notifikasi : "+e);
        }
         LCount.setText(""+tabMode.getRowCount());
    }
    
    private String concatColumn(String kolom1, String kolom2){
        if(kolom1.equals("Lainnya")){
            return kolom2;
        }else{
            return kolom1;
        }
    }

    private String concatColumn2(String kolom1,String kolom2){
        if(kolom1.equals("Keluarga")){
            return kolom1+" ("+kolom1+")";
        }else{
            return kolom1;
        }
    }

    private String concatColumn3(String kolom1, String kolom2, String kolom3){
        if(kolom1.equals("Ya")){
            return kolom1+" Ketergantungan dengan : "+kolom2+" sejak : "+kolom3;
        }else{
            return kolom1;
        }
    }
    
    private String concatColumn4(String kolom1, String kolom2){
        if(kolom1.equals("Ya")){
            return kolom1+" ("+kolom2+")";
        }else{
            return kolom1;
        }
    }
    
    private String concatColumn5(String kolom1, String kolom2){
        if(kolom1.equals("Penurunan Kesadaran")){
            return kolom1+" : "+kolom2;
        }else{
            return kolom1;
        }
    }

    public void emptTeks() {
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        TglLahir.setText("");
        Jk.setText("");
        KdPetugas.setText("");
        NmPetugas.setText("");
        KdDPJP.setText(""); 
        NmDPJP.setText(""); 
        TglAsuhan.setDate(new Date());
        CaraDatang.setSelectedIndex(0);
        Menggunakan.setSelectedIndex(0);
        PengkajianOleh. setSelectedIndex(0);
        AlasanMasuk.setText("");
        HubunganDenganPasien.setText("");
        PenyakitDiderita.setSelectedIndex(0);
        for (int i = 0; i  <  panelisi1.getComponentCount(); i++) {
            if (panelisi1.getComponent(i).getClass().toString().contains("CekBox")) {
                ((CekBox) panelisi1.getComponent(i)).setSelected(false);
            }
        } 
        Gamelli.setSelectedIndex(0); 
        Ketergantungan.setSelectedIndex(0);
        InputKetergantungan.setText("");
        KetergantunganSejak.setText("");
        ObatObatan.setSelectedIndex(0);
        InputObat.setText("");
        Makanan.setSelectedIndex(0);
        InputMakanan.setText("");
        Debu.setSelectedIndex(0);
        AlergiLainnya.setText("");
        Menarche.setText("");
        Menstruasi.setSelectedIndex(0);
        HariMens.setText( "");
        SakitSaatMens.setSelectedIndex( 0);
        Menikahke.setText("");
        LamanyaMenikah.setText(""); 
        NmKontrasepsi.setText("");
        KontrasepsiLamanya.setText("");
        InputG.setText("");
        InputP.setText("");
        InputA.setText("");
        HPHT.setDate(new Date());
        HPL.setText("");
        UmurKehamilan.setText("");
        Keluhan.setText("");
        TinggiFundus.setText("");
        LetakPunggungJanin.setSelectedIndex(0);
        PresentasiJanin.setSelectedIndex(0);
        BeratJanin.setText("");
        Penurunan.setSelectedIndex(0);
        Aukultasi.setText("");
        FrekAukultasi.setSelectedIndex(0);
        PemeriksaanDalam.setText("");
        TD.setText("");
        Nadi.setText("");
        RR.setText("");
        Suhu.setText("");
        TB.setText("");
        BB.setText("");
        Keadaan1.setSelectedIndex(0);
        GCS.setText("");
        Kesadaran.setSelectedIndex(0);
        InputPenurunanKesadaran.setText("");
        Kepala.setSelectedIndex(0);
        Mata.setSelectedIndex(0);
        Hidung.setSelectedIndex(0);
        GigiMulut.setSelectedIndex(0);
        Tenggorokan.setSelectedIndex(0);
        Telinga.setSelectedIndex(0);
        Ekstremitas.setSelectedIndex(0);
        Leher.setSelectedIndex(0);
        Thoraks.setSelectedIndex(0);
        Jantung.setSelectedIndex(0);
        Paru.setSelectedIndex(0);
        Abdomen.setSelectedIndex(0);
        Nyeri.setSelectedIndex(0);
        SkorNyeri.setSelectedIndex(0);
        KategoriNyeri.setSelectedIndex(0);
        PengaruhNyeri.setSelectedIndex(0);
        HB.setText("");
        HasilUSG.setText("");
        for(int i=0;i<panelisi2.getComponentCount();i++){
            if(panelisi2.getComponent(i).getClass().toString().contains("CekBox")){
                ((CekBox) panelisi2.getComponent(i)).setSelected(false);
            }
        }
        ResEmosi.setSelectedIndex(0);
        SupportSuami.setSelectedIndex(0);
        for(int i =0;i<panelisi3.getComponentCount();i++){
            if(panelisi3.getComponent(i).getClass().toString().contains("CekBox")){
                ((CekBox) panelisi3.getComponent(i)).setSelected(false);
            }
        }
        DiagnosaKebidanan.setText("");
        
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); 
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()); 
            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            TglAsuhan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            KdPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            NmPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            KdDPJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); 
            NmDPJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()); 
            CaraDatang.setSelectedItem(Sequel.cariIsi("select cara_datang from ponek_partus where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='"+norwt+"'", DTPCari1);
            CaraDatangLainnya.setText(Sequel.cariIsi("select ponek_partus.cara_datang_lainnya from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            Menggunakan.setSelectedItem(Sequel.cariIsi("select menggunakan from ponek_partus where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            MenggunakanLainnya.setText(Sequel.cariIsi("select ponek_partus.menggunakan_lainnya from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            CaraMasuk.setSelectedItem(Sequel.cariIsi("select asal from ponek_partus where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            DariLainnya.setText(Sequel.cariIsi("select ponek_partus.asal_lainnya from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            PengkajianOleh.setSelectedItem(Sequel.cariIsi("select pengkajian_dari from ponek_partus where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            HubunganDenganPasien.setText(Sequel.cariIsi("select ponek_partus.hubungan_dengan_pasien from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            AlasanMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            // riwayat kesehatan
            PenyakitDiderita.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            String riwayat_penyakit = tbObat.getValueAt(tbObat.getSelectedRow(),16).toString();
            String[] split_riwayat_penyakit = riwayat_penyakit.split(",");
            for (String split_riwayat_penyakit1 : split_riwayat_penyakit) {
                for (int j = 0; j<panelisi1.getComponentCount(); j++) {
                    if (panelisi1.getComponent(j).getClass().toString().contains("CekBox")) {
                        if (((CekBox) panelisi1.getComponent(j)).getText().equals(split_riwayat_penyakit1)) {
                            ((CekBox) panelisi1.getComponent(j)).setSelected(true);
                        }
                    }
                }
            }
            Gamelli.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            Ketergantungan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            InputKetergantungan.setText(Sequel.cariIsi("select ponek_partus.ketergantungan_dengan from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            KetergantunganSejak.setText(Sequel.cariIsi("select ponek_partus.sejak from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            ObatObatan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
            ObatObatan.setSelectedItem(Sequel.cariIsi("select ponek_partus.obat_obatan from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            InputObat.setText(Sequel.cariIsi("select ponek_partus.nama_obatan from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            Makanan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
            Makanan.setSelectedItem(Sequel.cariIsi("select ponek_partus.makanan from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            InputMakanan.setText(Sequel.cariIsi("select ponek_partus.nama_makanan from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            Debu.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
            Debu.setSelectedItem(Sequel.cariIsi("select ponek_partus.debu from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            InputDebu.setText(Sequel.cariIsi("select ponek_partus.nama_debu from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            AlergiLainnya.setText(Sequel.cariIsi("select ponek_partus.alergi_lainnya from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            AlergiLainnya.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
            Menarche.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
            Menstruasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
            HariMens.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
            SakitSaatMens.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
            Menikahke.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
            LamanyaMenikah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
            NmKontrasepsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
            KontrasepsiLamanya.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
            InputG.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
            InputP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
            InputA.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
//            HPHT.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),34));
            // conversi format tanggal
            String tanggalAwal=tbObat.getValueAt(tbObat.getSelectedRow(),34).toString();
            SimpleDateFormat sdfAwal = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdfBaru = new SimpleDateFormat("dd-MM-yyyy");
            try{
                Date date = sdfAwal.parse(tanggalAwal);
                String tanggalBaru = sdfBaru.format(date);
                HPHT.setSelectedItem(tanggalBaru);
            }catch(ParseException e){
                e.printStackTrace();
            }
//            HariPerkiraanLahir.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),33));
            HPL.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
            UmurKehamilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
            Keluhan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
            TinggiFundus.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
            LetakPunggungJanin.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),39));
            PresentasiJanin.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),40));
            BeratJanin.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
            Penurunan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),42));
            Aukultasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
            FrekAukultasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),44));
            PemeriksaanDalam.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
            // pemeriksaan fisik
            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
            RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());
            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());
            BB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
            Keadaan1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),52));
            GCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());
//            Kesadaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),54));
            Kesadaran.setSelectedItem(Sequel.cariIsi("select ponek_partus.kesadaran from ponek_partus where ponek_partus.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            // Sdisini input penurunan kesadaran
            InputPenurunanKesadaran.setText(Sequel.cariIsi("select input_penurunan_kesadaran from ponek_partus where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
            Kepala.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),55));
            Mata.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),56));
            Hidung.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),57));
            GigiMulut.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),58));
            Tenggorokan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),59));
            Telinga.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),60));
            Ekstremitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),61));
            Leher.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),62));
            Thoraks.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),63));
            Jantung.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),64));
            Paru.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),65));
            Abdomen.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),66));
            Anus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),67));
            // penilaian tingkat nyeri
            Nyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),68));
            SkorNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),69));
            KategoriNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),70));
            PengaruhNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),71));
            // pemeriksaan penunjang terakhir
            HB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),72).toString());
            HasilUSG.setText(tbObat.getValueAt(tbObat.getSelectedRow(),73).toString());
            // status mental
            String status_mental = tbObat.getValueAt(tbObat.getSelectedRow(),74).toString();
            String[] status_mental_split = status_mental.split(",");
            for(String status_mental_split1 : status_mental_split){
                for(int j=0;j<panelisi2.getComponentCount();j++){
                    if(panelisi2.getComponent(j).getClass().toString().contains("CekBox")){
                        if(((CekBox)panelisi2.getComponent(j)).getText().equals(status_mental_split1)){
                            ((CekBox)panelisi2.getComponent(j)).setSelected(true);
                        }
                    }
                }
            }
            ResEmosi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),75).toString());
            SupportSuami.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),76).toString());
            // diagnosa kebidanan
            String masalah_kebidanan = tbObat.getValueAt(tbObat.getSelectedRow(),77).toString();
            String[] masalah_kebidanan_split = masalah_kebidanan.split(",");
            for(String masalah_kebidanan_split1 : masalah_kebidanan_split){
                for(int j=0;j<panelisi3.getComponentCount();j++){
                    if(panelisi3.getComponent(j).getClass().toString().contains("CekBox")){
                        if(((CekBox)panelisi3.getComponent(j)).getText().equals(masalah_kebidanan_split1)){
                            ((CekBox)panelisi3.getComponent(j)).setSelected(true);
                        }
                    }
                }
            }
            DiagnosaKebidanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),78).toString());
            
            tampilPersalinan();
            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
        }
    }

    private void isRawat() {
        try {
            ps=koneksi.prepareStatement(
                    "select pasien.nm_pasien, if(pasien.jk='L','Laki-Laki','Perempuan') as jk,pasien.tgl_lahir,pasien.agama,"+
                    "bahasa_pasien.nama_bahasa,pasien.pnd,pasien.pekerjaan,pasien.gol_darah "+
                    "from pasien inner join bahasa_pasien on bahasa_pasien.id=pasien.bahasa_pasien "+
                    "where pasien.no_rkm_medis=?");
            try {
                ps.setString(1,TNoRM.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    TPasien.setText(rs.getString("nm_pasien"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
    
    public void setNoRm(String norwt,Date tgl2,String norm,String nmpasien) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TCari.setText(norwt);
        Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='"+norwt+"'", DTPCari1);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
        tampilPersalinan();
    }
    
    
    public void isCek(){
//        BtnSimpan.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan());
//        BtnHapus.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan());
//        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan());
//        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan()); 
        if(akses.getjml2()>=1){
            KdPetugas.setEditable(false);
            BtnPetugas.setEnabled(false);
            KdPetugas.setText(akses.getkode2());
            Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?", NmPetugas,KdPetugas.getText());
            if(NmPetugas.getText().equals("")){
                KdPetugas.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        }            
    }

    public void setTampil(){
       TabRawat.setSelectedIndex(1);
       tampil();
    }
    
    private void isMenu(){
        if(ChkAccor.isSelected()==true){
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(470,HEIGHT));
            FormMenu.setVisible(true);  
            FormMasalahRencana.setVisible(true);
            ChkAccor.setVisible(true);
        }else if(ChkAccor.isSelected()==false){    
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15,HEIGHT));
            FormMenu.setVisible(false);  
            FormMasalahRencana.setVisible(false);  
            ChkAccor.setVisible(true);
        }
    }

    private void getMasalah() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRM1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TPasien1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            DiagnosaKebidanan1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),78).toString());
            Valid.tabelKosong(tabModeRiwayatKehamilan2);
            try {
                ps=koneksi.prepareStatement("select * from riwayat_persalinan_partus where riwayat_persalinan_partus.no_rkm_medis=?");
                try {
                    ps.setString(1,TNoRM1.getText());
                    rs=ps.executeQuery();
                    i=1;
                    while(rs.next()){
                        tabModeRiwayatKehamilan2.addRow(new String[]{
                            i+"",rs.getString("jk"),rs.getString("umur_anak"),rs.getString("ku_anak"),rs.getString("bbl"),rs.getString("riwayat_persalinan"),rs.getString("ditolong_oleh")
                        });
                        i++;
                    }
                } catch (Exception e) {
                    System.out.println("Notif : "+e);
                } finally{
                    if(rs!=null){
                        rs.close();
                    }
                    if(ps!=null){
                        ps.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            }
        }
    }
   
//    private void isTotalResikoJatuh(){
//        try {
//            NilaiResikoTotal.setText((Integer.parseInt(NilaiResiko1.getText())+Integer.parseInt(NilaiResiko2.getText())+Integer.parseInt(NilaiResiko3.getText())+Integer.parseInt(NilaiResiko4.getText())+Integer.parseInt(NilaiResiko5.getText())+Integer.parseInt(NilaiResiko6.getText()))+"");
//            if(Integer.parseInt(NilaiResikoTotal.getText())<25){
//                TingkatResiko.setText("Tingkat Resiko : Risiko Rendah (0-24), Tindakan : Intervensi pencegahan risiko jatuh standar");
//            }else if(Integer.parseInt(NilaiResikoTotal.getText())<45){
//                TingkatResiko.setText("Tingkat Resiko : Risiko Sedang (25-44), Tindakan : Intervensi pencegahan risiko jatuh standar");
//            }else if(Integer.parseInt(NilaiResikoTotal.getText())>=45){
//                TingkatResiko.setText("Tingkat Resiko : Risiko Tinggi (> 45), Tindakan : Intervensi pencegahan risiko jatuh standar dan Intervensi risiko jatuh tinggi");
//            }
//        } catch (Exception e) {
//            NilaiResikoTotal.setText("0");
//            TingkatResiko.setText("Tingkat Resiko : Risiko Rendah (0-24), Tindakan : Intervensi pencegahan risiko jatuh standar");
//        }
//    }
    
//    private void isTotalGizi(){
//        try {
//            NilaiGiziTotal.setText((Integer.parseInt(NilaiGizi1.getText())+Integer.parseInt(NilaiGizi2.getText()))+"");
//        } catch (Exception e) {
//            NilaiGiziTotal.setText("0");
//        }
//    }

    private void emptTeksPersalinan() {
        
        UmurAnak.setText("");
        KUAnak.setText("");
        RiwayatPersalinan.setText("");
        DitolongOleh.setText("");
        JK.setSelectedIndex(0);
        BBL.setText("");
    }

    private void tampilPersalinan() {
        Valid.tabelKosong(tabModeRiwayatKehamilan);
        try {
            ps=koneksi.prepareStatement("select * from riwayat_persalinan_partus where riwayat_persalinan_partus.no_rkm_medis=?");
            try {
                ps.setString(1,TNoRM.getText());
                rs=ps.executeQuery();
                i=1;
                while(rs.next()){
                    tabModeRiwayatKehamilan.addRow(new String[]{
                        i+"",rs.getString("jk"),rs.getString("umur_anak"),rs.getString("ku_anak"),rs.getString("bbl"),rs.getString("riwayat_persalinan"),rs.getString("ditolong_oleh"),rs.getString("no_rkm_medis"),rs.getString("id_riwayat")
                    });
                    i++;
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from ponek_partus where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            TNoRM1.setText("");
            TPasien1.setText("");
            ChkAccor.setSelected(false);
            isMenu();
            tampil();
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }

    private void ganti() {
//        if(Sequel.mengedittf("penilaian_awal_keperawatan_kebidanan_ranap","no_rawat=?","no_rawat=?,tanggal=?,informasi=?,tiba_diruang_rawat=?,cara_masuk=?,keluhan=?,rpk=?,psk=?,rp=?,alergi=?,komplikasi_sebelumnya=?,keterangan_komplikasi_sebelumnya=?,riwayat_mens_umur=?,riwayat_mens_lamanya=?,riwayat_mens_banyaknya=?,riwayat_mens_siklus=?,riwayat_mens_ket_siklus=?,riwayat_mens_dirasakan=?,riwayat_perkawinan_status=?,riwayat_perkawinan_ket_status=?,riwayat_perkawinan_usia1=?,riwayat_perkawinan_ket_usia1=?,riwayat_perkawinan_usia2=?,riwayat_perkawinan_ket_usia2=?,riwayat_perkawinan_usia3=?,riwayat_perkawinan_ket_usia3=?,riwayat_persalinan_g=?,riwayat_persalinan_p=?,riwayat_persalinan_a=?,riwayat_persalinan_hidup=?,riwayat_hamil_hpht=?,riwayat_hamil_usiahamil=?,riwayat_hamil_tp=?,riwayat_hamil_imunisasi=?,riwayat_hamil_anc=?,riwayat_hamil_ancke=?,riwayat_hamil_ket_ancke=?,riwayat_hamil_keluhan_hamil_muda=?,riwayat_hamil_keluhan_hamil_tua=?,riwayat_kb=?,riwayat_kb_lamanya=?,riwayat_kb_komplikasi=?,riwayat_kb_ket_komplikasi=?,riwayat_kb_kapaberhenti=?,riwayat_kb_alasanberhenti=?,riwayat_genekologi=?,riwayat_kebiasaan_obat=?,riwayat_kebiasaan_ket_obat=?,riwayat_kebiasaan_merokok=?,riwayat_kebiasaan_ket_merokok=?,riwayat_kebiasaan_alkohol=?,riwayat_kebiasaan_ket_alkohol=?,riwayat_kebiasaan_narkoba=?,pemeriksaan_kebidanan_mental=?,pemeriksaan_kebidanan_keadaan_umum=?,pemeriksaan_kebidanan_gcs=?,pemeriksaan_kebidanan_td=?,pemeriksaan_kebidanan_nadi=?,pemeriksaan_kebidanan_rr=?,pemeriksaan_kebidanan_suhu=?,pemeriksaan_kebidanan_spo2=?,pemeriksaan_kebidanan_bb=?,pemeriksaan_kebidanan_tb=?,pemeriksaan_kebidanan_lila=?,pemeriksaan_kebidanan_tfu=?,pemeriksaan_kebidanan_tbj=?,pemeriksaan_kebidanan_letak=?,pemeriksaan_kebidanan_presentasi=?,pemeriksaan_kebidanan_penurunan=?,pemeriksaan_kebidanan_his=?,pemeriksaan_kebidanan_kekuatan=?,pemeriksaan_kebidanan_lamanya=?,pemeriksaan_kebidanan_djj=?,pemeriksaan_kebidanan_ket_djj=?,pemeriksaan_kebidanan_portio=?,pemeriksaan_kebidanan_pembukaan=?,pemeriksaan_kebidanan_ketuban=?,pemeriksaan_kebidanan_hodge=?,pemeriksaan_kebidanan_panggul=?,pemeriksaan_kebidanan_inspekulo=?,pemeriksaan_kebidanan_ket_inspekulo=?,pemeriksaan_kebidanan_lakmus=?,pemeriksaan_kebidanan_ket_lakmus=?,pemeriksaan_kebidanan_ctg=?,pemeriksaan_kebidanan_ket_ctg=?,pemeriksaan_umum_kepala=?,pemeriksaan_umum_muka=?,pemeriksaan_umum_mata=?,pemeriksaan_umum_hidung=?,pemeriksaan_umum_telinga=?,pemeriksaan_umum_mulut=?,pemeriksaan_umum_leher=?,pemeriksaan_umum_dada=?,pemeriksaan_umum_perut=?,pemeriksaan_umum_genitalia=?,pemeriksaan_umum_ekstrimitas=?,pengkajian_fungsi_kemampuan_aktifitas=?,pengkajian_fungsi_berjalan=?,pengkajian_fungsi_ket_berjalan=?,pengkajian_fungsi_aktivitas=?,pengkajian_fungsi_ambulasi=?,pengkajian_fungsi_ekstrimitas_atas=?,pengkajian_fungsi_ket_ekstrimitas_atas=?,pengkajian_fungsi_ekstrimitas_bawah=?,pengkajian_fungsi_ket_ekstrimitas_bawah=?,pengkajian_fungsi_kemampuan_menggenggam=?,pengkajian_fungsi_ket_kemampuan_menggenggam=?,pengkajian_fungsi_koordinasi=?,pengkajian_fungsi_ket_koordinasi=?,pengkajian_fungsi_gangguan_fungsi=?,riwayat_psiko_kondisipsiko=?,riwayat_psiko_adakah_prilaku=?,riwayat_psiko_ket_adakah_prilaku=?,riwayat_psiko_gangguan_jiwa=?,riwayat_psiko_hubungan_pasien=?,riwayat_psiko_tinggal_dengan=?,riwayat_psiko_ket_tinggal_dengan=?,riwayat_psiko_budaya=?,riwayat_psiko_ket_budaya=?,riwayat_psiko_pend_pj=?,riwayat_psiko_edukasi_pada=?,riwayat_psiko_ket_edukasi_pada=?,penilaian_nyeri=?,penilaian_nyeri_penyebab=?,penilaian_nyeri_ket_penyebab=?,penilaian_nyeri_kualitas=?,penilaian_nyeri_ket_kualitas=?,penilaian_nyeri_lokasi=?,penilaian_nyeri_menyebar=?,penilaian_nyeri_skala=?,penilaian_nyeri_waktu=?,penilaian_nyeri_hilang=?,penilaian_nyeri_ket_hilang=?,penilaian_nyeri_diberitahukan_dokter=?,penilaian_nyeri_jam_diberitahukan_dokter=?,penilaian_jatuh_skala1=?,penilaian_jatuh_nilai1=?,penilaian_jatuh_skala2=?,penilaian_jatuh_nilai2=?,penilaian_jatuh_skala3=?,penilaian_jatuh_nilai3=?,penilaian_jatuh_skala4=?,penilaian_jatuh_nilai4=?,penilaian_jatuh_skala5=?,penilaian_jatuh_nilai5=?,penilaian_jatuh_skala6=?,penilaian_jatuh_nilai6=?,penilaian_jatuh_totalnilai=?,skrining_gizi1=?,nilai_gizi1=?,skrining_gizi2=?,nilai_gizi2=?,nilai_total_gizi=?,skrining_gizi_diagnosa_khusus=?,skrining_gizi_ket_diagnosa_khusus=?,skrining_gizi_diketahui_dietisen=?,skrining_gizi_jam_diketahui_dietisen=?,masalah=?,rencana=?,nip1=?,nip2=?,kd_dokter=?",163,new String[]{
//                TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),CaraDatang.getSelectedItem().toString(),Menggunakan.getSelectedItem().toString(),CaraMasuk.getSelectedItem().toString(),KeluhanUtama.getText(),RPK.getText(),PSK.getText(),RBedah.getText(),Alergi.getText(),KomplikasiKehamilan.getSelectedItem().toString(),KeteranganKomplikasiKehamilan.getText(),
//                UmurMinarche.getText(),LamaMenstruasi.getText(),BanyaknyaPembalut.getText(),SiklusMenstruasi.getText(),KetSiklusMenstruasi.getSelectedItem().toString(),DirasakanMenstruasi.getSelectedItem().toString(),StatusMenikah.getSelectedItem().toString(),KaliMenikah.getText(),UsiaKawin1.getText(),StatusKawin1.getSelectedItem().toString(),UsiaKawin2.getText(),StatusKawin2.getSelectedItem().toString(),UsiaKawin3.getText(),
//                StatusKawin3.getSelectedItem().toString(),G.getText(),P.getText(),A.getText(),Hidup.getText(),Valid.SetTgl(HPHT.getSelectedItem()+""),UsiaKehamilan.getText(),Valid.SetTgl(TP.getSelectedItem()+""),RiwayatImunisasi.getSelectedItem().toString(),ANC.getText(),ANCKe.getText(),RiwayatANC.getSelectedItem().toString(),KeluhanHamilMuda.getSelectedItem().toString(),KeluhanHamilTua.getSelectedItem().toString(),
//                RiwayatKB.getSelectedItem().toString(),LamanyaKB.getText(),KomplikasiKB.getSelectedItem().toString(),KeteranganKomplikasiKB.getText(),BerhentiKB.getText(),AlasanBerhentiKB.getText(),RiwayatGenekologi.getSelectedItem().toString(),KebiasaanObat.getSelectedItem().toString(),KebiasaanObatDiminum.getText(),KebiasaanMerokok.getSelectedItem().toString(),KebiasaanJumlahRokok.getText(),KebiasaanAlkohol.getSelectedItem().toString(),
//                KebiasaanJumlahAlkohol.getText(),KebiasaanNarkoba.getSelectedItem().toString(),KesadaranMental.getText(),KeadaanMentalUmum.getSelectedItem().toString(),GCS.getText(),TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),SpO2.getText(),BB.getText(),TB.getText(),LILA.getText(),TFU.getText(),TBJ.getText(),Letak.getText(),Presentasi.getText(),Penurunan.getText(),Kontraksi.getText(),Kekuatan.getText(),LamanyaKontraksi.getText(),
//                DJJ.getText(),KeteranganDJJ.getSelectedItem().toString(),Portio.getText(),PembukaanServiks.getText(),Ketuban.getText(),Hodge.getText(),PemeriksaanPanggul.getSelectedItem().toString(),Inspekulo.getSelectedItem().toString(),KeteranganInspekulo.getText(),Lakmus.getSelectedItem().toString(),KeteranganLakmus.getText(),CTG.getSelectedItem().toString(),KeteranganCTG.getText(),PemeriksaanKepala.getSelectedItem().toString(),
//                PemeriksaanMuka.getSelectedItem().toString(),PemeriksaanMata.getSelectedItem().toString(),PemeriksaanHidung.getSelectedItem().toString(),PemeriksaanTelinga.getSelectedItem().toString(),PemeriksaanMulut.getSelectedItem().toString(),PemeriksaanLeher.getSelectedItem().toString(),PemeriksaanDada.getSelectedItem().toString(),PemeriksaanPerut.getSelectedItem().toString(),PemeriksaanGenitalia.getSelectedItem().toString(),
//                PemeriksaanEkstrimitas.getSelectedItem().toString(),AktifitasSehari2.getSelectedItem().toString(),Berjalan.getSelectedItem().toString(),KeteranganBerjalan.getText(),Aktifitas.getSelectedItem().toString(),AlatAmbulasi.getSelectedItem().toString(),EkstrimitasAtas.getSelectedItem().toString(),KeteranganEkstrimitasAtas.getText(),EkstrimitasBawah.getSelectedItem().toString(),KeteranganEkstrimitasBawah.getText(),
//                KemampuanMenggenggam.getSelectedItem().toString(),KeteranganKemampuanMenggenggam.getText(),KemampuanKoordinasi.getSelectedItem().toString(),KeteranganKemampuanKoordinasi.getText(),KesimpulanGangguanFungsi.getSelectedItem().toString(),KondisiPsikologis.getSelectedItem().toString(),AdakahPerilaku.getSelectedItem().toString(),KeteranganAdakahPerilaku.getText(),GangguanJiwa.getSelectedItem().toString(),
//                HubunganAnggotaKeluarga.getSelectedItem().toString(),TinggalDengan.getSelectedItem().toString(),KeteranganTinggalDengan.getText(),NilaiKepercayaan.getSelectedItem().toString(),KeteranganNilaiKepercayaan.getText(),PendidikanPJ.getSelectedItem().toString(),EdukasiPsikolgis.getSelectedItem().toString(),KeteranganEdukasiPsikologis.getText(),Nyeri.getSelectedItem().toString(),Provokes.getSelectedItem().toString(),
//                KetProvokes.getText(),Quality.getSelectedItem().toString(),KetQuality.getText(),Lokasi.getText(),Menyebar.getSelectedItem().toString(),SkalaNyeri.getSelectedItem().toString(),Durasi.getText(),NyeriHilang.getSelectedItem().toString(),KetNyeri.getText(),PadaDokter.getSelectedItem().toString(),KetPadaDokter.getText(),SkalaResiko1.getSelectedItem().toString(),NilaiResiko1.getText(),SkalaResiko2.getSelectedItem().toString(),
//                NilaiResiko2.getText(),SkalaResiko3.getSelectedItem().toString(),NilaiResiko3.getText(),SkalaResiko4.getSelectedItem().toString(),NilaiResiko4.getText(),SkalaResiko5.getSelectedItem().toString(),NilaiResiko5.getText(),SkalaResiko6.getSelectedItem().toString(),NilaiResiko6.getText(),NilaiResikoTotal.getText(),SkalaGizi1.getSelectedItem().toString(),NilaiGizi1.getText(),SkalaGizi2.getSelectedItem().toString(),
//                NilaiGizi2.getText(),NilaiGiziTotal.getText(),DiagnosaKhususGizi.getSelectedItem().toString(),KeteranganDiagnosaKhususGizi.getText(),DiketahuiDietisen.getSelectedItem().toString(),KeteranganDiketahuiDietisen.getText(),Masalah.getText(),Tindakan.getText(),KdPetugas.getText(),KdPetugas2.getText(),KdDPJP.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
//             })==true){
//                getMasalah();
//                tampil();
//                emptTeks();
//                TabRawat.setSelectedIndex(1);
//        }
        if(Sequel.mengedittf(
                "ponek_partus","no_rawat=?","no_rawat=?,tanggal=?,kd_petugas=?,nm_petugas=?,kd_dpjp=?,nm_dpjp=?,"+
                 "cara_datang=?,cara_datang_lainnya=?,menggunakan=?,menggunakan_lainnya=?,asal=?,asal_lainnya=?,"+
                 "pengkajian_dari=?,hubungan_dengan_pasien=?,alasan_masuk=?,penyakit_pernah_diderita=?,nama_riwayat_penyakit=?,"+
                 "penyakit_lainnya=?,faktor_keturunan_gamelli=?,ketergantungan=?,ketergantungan_dengan=?,sejak=?,obat_obatan=?,nama_obatan=?,"+
                 "makanan=?,nama_makanan=?,debu=?,nama_debu=?,alergi_lainnya=?,menarche=?,menstruasi=?,sejak_menstruasi=?,"+
                 "sakit_saat_menstruasi=?,menikah_ke=?,lamanya_pernikahan=?,kontrasepsi=?,lamanya_kontrasepsi=?,graphit=?,paritas=?,"+
                 "abortus=?,haid_terakhir=?,perkiraan_lahir=?,umur_kehamilan=?,keluhan_kehamilan=?,tinggi_fundus_uteri=?,letak_punggung_janin=?,"+
                 "presentasi_janin=?,taksiran_berat_janin=?,penurunan=?,aukultasi=?,frekuensi_aukultasi=?,pemeriksaan_dalam=?,td=?,nadi=?,rr=?,"+
                 "suhu=?,tb=?,bb=?,keadaan_umum=?,gcs=?,kesadaran=?,input_penurunan_kesadaran=?,kepala=?,mata=?,hidung=?,gigi_mulut=?,"+
                 "tenggorokan=?,telinga=?,ekstremitas=?,leher=?,thoraks=?,jantung=?,paru=?,abdomen=?,genitalis_anus=?,nyeri=?,skor=?,kategori=?,"+
                 "pengaruh_nyeri=?,hb=?,hasil_usg=?,status_mental=?,respon_emosi=?,suport_suami=?,masalah_kebidanan=?,masalah_kebidanan_lainnya=?,"+
                 "diagnosa_kebidanan=?,no_rm_medis=?"
                ,89,new String[]{
            // get value dari field yang diisikan
            TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+""),KdPetugas.getText(),NmPetugas.getText(),KdDPJP.getText(),
            NmDPJP.getText(),CaraDatang.getSelectedItem().toString(),CaraDatangLainnya.getText(),Menggunakan.getSelectedItem().toString(),
            MenggunakanLainnya.getText(),CaraMasuk.getSelectedItem().toString(),DariLainnya.getText(),PengkajianOleh.getSelectedItem().toString(),
            HubunganDenganPasien.getText(),AlasanMasuk.getText(),PenyakitDiderita.getSelectedItem().toString(),getSelectedPenyakit(),LainnyaInput.getText(),
            Gamelli.getSelectedItem().toString(),Ketergantungan.getSelectedItem().toString(),InputKetergantungan.getText(),KetergantunganSejak.getText(),
            ObatObatan.getSelectedItem().toString(),InputObat.getText(),Makanan.getSelectedItem().toString(),InputMakanan.getText(),Debu.getSelectedItem().toString(),
            InputDebu.getText(),AlergiLainnya.getText(),Menarche.getText(),Menstruasi.getSelectedItem().toString(),HariMens.getText(),SakitSaatMens.getSelectedItem().toString(),
            Menikahke.getText(),LamanyaMenikah.getText(),NmKontrasepsi.getText(),KontrasepsiLamanya.getText(),
            // section 4
            InputG.getText(),InputP.getText(),InputA.getText(),Valid.SetTgl(HPHT.getSelectedItem()+""),HPL.getText(),UmurKehamilan.getText(),
            Keluhan.getText(),
            // section 4.1
            TinggiFundus.getText(),LetakPunggungJanin.getSelectedItem().toString(),PresentasiJanin.getSelectedItem().toString(),BeratJanin.getText(),Penurunan.getSelectedItem().toString(),Aukultasi.getText(),FrekAukultasi.getSelectedItem().toString(),
            PemeriksaanDalam.getText(),
            // section 5
            TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),TB.getText(),BB.getText(),Keadaan1.getSelectedItem().toString(),GCS.getText(),Kesadaran.getSelectedItem().toString(),InputPenurunanKesadaran.getText(),
            Kepala.getSelectedItem().toString(),Mata.getSelectedItem().toString(),Hidung.getSelectedItem().toString(),GigiMulut.getSelectedItem().toString(),Tenggorokan.getSelectedItem().toString(),Telinga.getSelectedItem().toString(),Ekstremitas.getSelectedItem().toString(),
            Leher.getSelectedItem().toString(),Thoraks.getSelectedItem().toString(),Jantung.getSelectedItem().toString(),Paru.getSelectedItem().toString(),Abdomen.getSelectedItem().toString(),Anus.getSelectedItem().toString(),
            // section 6
            Nyeri.getSelectedItem().toString(),SkorNyeri.getSelectedItem().toString(),KategoriNyeri.getSelectedItem().toString(),PengaruhNyeri.getSelectedItem().toString(),
            // section 7
            HB.getText(),HasilUSG.getText(),
            // section 8
            getSelectedStatusMental(),
            // section 9
            ResEmosi.getSelectedItem().toString(),SupportSuami.getSelectedItem().toString(),
            // section 10
            getSelected(),MasalahKebidananLainnya.getText(),
            // section 11
            DiagnosaKebidanan.getText(),TNoRM.getText(),
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            emptTeks();
            
        }
    }
    
    // function for show hide panelInputan & panelisi1
    private void isAda(){
        if(PenyakitDiderita.getSelectedItem().toString().equals("Ada")){
            panelisi1.setBounds(0, 180, 650, 80);
            panelisi1.setVisible(true);
            panelInputan.setBounds(0, 260, 880, 2100);
        }else if(PenyakitDiderita.getSelectedItem().toString().equals("Tidak Ada")){
            panelisi1.setBounds(890, 180, 650, 80);
            panelisi1.setVisible(false);
            panelInputan.setBounds(0, 180, 880, 2100);
            for (int i = 0; i  <  panelisi1.getComponentCount(); i++) {
            if (panelisi1.getComponent(i).getClass().toString().contains("CekBox")) {
                ((CekBox) panelisi1.getComponent(i)).setSelected(false);
            }
        } 
        }
    }
    
    // function for show field ketergantungan
    private void isKetergantugan(){
        if(Ketergantungan.getSelectedItem().toString().equals("Ya")){
            InputKetergantungan.setVisible(true);
            KetergantunganSejak.setVisible(true);
            jLabel76.setVisible(true);
        }else if(Ketergantungan.getSelectedItem().toString().equals("Tidak")){
            InputKetergantungan.setVisible(false);
            KetergantunganSejak.setVisible(false);
            jLabel76.setVisible(false);
        }
    }
    
    // function for show pilihan lainnya
    private void isDatangLainnya(){
        if(CaraDatang.getSelectedItem().toString().equals("Lainnya")){
            CaraDatangLainnya.setVisible(true);
        }else{
            CaraDatangLainnya.setVisible(false);
            CaraDatangLainnya.setText("");
        }
    }
    
    private void isMenggunakanLainnya(){
        if(Menggunakan.getSelectedItem().toString().equals("Lainnya")){
            MenggunakanLainnya.setVisible(true);
        }else{
            MenggunakanLainnya.setVisible(false);
            MenggunakanLainnya.setText("");
        }
    }
    
    private void isMasukLainnya(){
        if(CaraMasuk.getSelectedItem().toString().equals("Lainnya")){
            DariLainnya.setVisible(true);
        }else{
            DariLainnya.setVisible(false);
            DariLainnya.setText("");
        }
    }
    
    private void pengkajiOleh(){
        if(PengkajianOleh.getSelectedItem().toString().equals("Keluarga")){
            jLabel37.setVisible(true);
            HubunganDenganPasien.setVisible(true);
            jLabel37.setBounds(300, 100, 140, 23);
            HubunganDenganPasien.setBounds(447, 100, 80, 23);
            jLabel213.setBounds(535, 100, 80, 23);
            AlasanMasuk.setBounds(620, 100, 190, 23);
        }else{
            jLabel37.setVisible(false);
            HubunganDenganPasien.setVisible(false);
            jLabel37.setBounds(840, 100, 140, 23);
            HubunganDenganPasien.setBounds(990, 100, 80, 23);
            jLabel213.setBounds(310, 100, 80, 23);
            AlasanMasuk.setBounds(400, 100, 190, 23);
            HubunganDenganPasien.setText("");
        }
    }
    
    private void cekKesadaran(){
        if(Kesadaran.getSelectedItem().toString().equals("Penurunan Kesadaran")){
            InputPenurunanKesadaran.setVisible(true);
        }else{
            InputPenurunanKesadaran.setVisible(false);
            InputPenurunanKesadaran.setText("");
        }
    }
    
    private String getSelected(){
        String data="";
        // jika componen cekbox dalam panelisi3 dicentang maka tambahkan ke variabel data menggunakan tanda ","
        // buat menggunakan perulangan untuk mengecek componen cekbox yang dicentang
        for(int i=0;i<panelisi3.getComponentCount();i++){
            if(panelisi3.getComponent(i).getClass().getSimpleName().equals("CekBox")){
                CekBox cb = (CekBox)panelisi3.getComponent(i);
                if(cb.isSelected()==true){
                    data=data+cb.getText()+",";
                }
            }
        }
        return data;
    }
    
    private String getSelectedPenyakit(){
        String data="";
        for(int i=0;i<panelisi1.getComponentCount();i++){
            if(panelisi1.getComponent(i).getClass().getSimpleName().equals("CekBox")){
                CekBox cb = (CekBox)panelisi1.getComponent(i);
                if(cb.isSelected()==true){
                    data += cb.getText()+",";
                }
            }
        }
        return data;
    }
    
    private String getSelectedStatusMental(){
        String data="";
        for(int i=0;i<panelisi2.getComponentCount();i++){
            CekBox cb = (CekBox)panelisi2.getComponent(i);
            if(cb.isSelected()==true){
                data+=cb.getText()+",";
            }
        }
        return data;
    }
    
    private void showRiwayatAlergi(){
        if(ObatObatan.getSelectedItem().toString().equals("Ya")){
            InputObat.setVisible(true);
        }else{
            InputObat.setVisible(false);
            InputObat.setText("");
        }
        
        if(Makanan.getSelectedItem().toString().equals("Ya")){
            InputMakanan.setVisible(true);
        }else{
            InputMakanan.setVisible(false);
            InputMakanan.setText("");
        }
        
        if(Debu.getSelectedItem().toString().equals("Ya")){
            InputDebu.setVisible(true);
        }else{
            InputDebu.setVisible(false);
            InputDebu.setText("");
        }
    }
    
    private void hitungHPL() {
    // Format tanggal dd-MM-yyyy
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    
    // Ambil objek terpilih dari combo box HPHT
    Object selectedObject = HPHT.getSelectedItem();
    
    if (selectedObject != null) {
        String hphtString = selectedObject.toString();
        
        try {
            // Pastikan tanggal HPHT tidak kosong
            if (!hphtString.isEmpty()) {
                // Parsing tanggal HPHT
                Date hpht = format.parse(hphtString);
                Calendar cal = Calendar.getInstance();
                cal.setTime(hpht);
                
                // Tambahkan 7 hari ke tanggal HPHT
                cal.add(Calendar.DATE, 7);
                
                // Cek bulan HPHT
                int bulanHPHT = cal.get(Calendar.MONTH);
                
                // Tambahkan 9 bulan jika bulan HPHT adalah Januari - Maret (0 - 2)
                if (bulanHPHT >= 0 && bulanHPHT <= 2) {
                    cal.add(Calendar.MONTH, 9);
                }
                // Kurangkan 3 bulan dan tambahkan 1 tahun jika bulan HPHT adalah April - Desember (3 - 11)
                else if (bulanHPHT >= 3 && bulanHPHT <= 11) {
                    cal.add(Calendar.MONTH, -3);
                    cal.add(Calendar.YEAR, 1);
                }
                
                // Hasilnya adalah HPL
                Date hplDate = cal.getTime();
                
                // Format tanggal HPL
                String hplDateString = format.format(hplDate);
                
                // Tampilkan HPL
                HPL.setText(hplDateString);
            } else {
                // Tampilkan pesan kesalahan jika tanggal HPHT kosong
                HPL.setText("Tanggal HPHT tidak boleh kosong.");
            }
        } catch (ParseException e) {
            // Handle exception
            // Tampilkan pesan kesalahan jika parsing gagal
            HPL.setText("Format tanggal HPHT tidak valid.");
        }
    } else {
        // Handle case where no item is selected in the combo box
        HPL.setText("Pilih tanggal HPHT terlebih dahulu.");
    }
}
    

    public void hitungUmurKehamilan() {
    // Tanggal HPHT
    Object selectedObject = HPHT.getSelectedItem();
    // Tanggal saat ini
    Date today = new Date();
    
    if (selectedObject != null) {
        String hphtString = selectedObject.toString();
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date hphtDate = format.parse(hphtString);
            
            // Menghitung selisih hari antara tanggal HPHT dan tanggal saat ini
            long selisihHari = today.getTime() - hphtDate.getTime();
            int minggu = (int) (selisihHari / (1000 * 60 * 60 * 24 * 7)); // 1 minggu = 7 hari
            int hari = (int) (selisihHari / (1000 * 60 * 60 * 24)) % 7;
            
            UmurKehamilan.setText(minggu + " minggu " + hari + " hari");
        } catch (ParseException e) {
            System.out.println("Format tanggal HPHT tidak valid.");
        }
    } else {
        UmurKehamilan.setText("Pilih tanggal HPHT terlebih dahulu.");
    }
}




}
