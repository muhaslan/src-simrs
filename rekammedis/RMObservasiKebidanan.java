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


/**
 *
 * @author perpustakaan
 */
public final class RMObservasiKebidanan extends javax.swing.JDialog {
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
    public RMObservasiKebidanan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
//        DlgRiwayatPersalinan.setSize(650,192);
        DlgRiwayatPersalinan.setSize(600, 360);
        
        tabMode=new DefaultTableModel(null,new Object[]{
            // header
            "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","Tanggal","kd_petugas","Nama Petugas","Pemeriksaan Oleh","Masuk Karena",
            "Ketuban","TD","Nadi","D.D.A","Udema","Pemeriksaan Paru - Paru","Pemeriksaan Jantung","Fundus Uteri","Situs Anak","Posisi Punggung",
            "Bagian Paling Depan","Suhu","Pernafasan","Gamelli/Tunggal",
            "Gerak Anak","Tanggal Lahir Bayi","Jenis Kelamin","Jenis Kelahiran","Kondisi Bayi","Waktu Kematian","Sebab Kematian",
            "Frekuensi Jantung","Nilai 1","Usaha Bernafas","Nilai 2","Tonus Otot","Nilai 3","Refleks","Nilai 4","Warna","Nilai 5","Nilai Total"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 41; i++) {
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
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(100);
            }else if(i==8){
                column.setPreferredWidth(150);
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
                column.setPreferredWidth(100);
            }else if(i==21){
                column.setPreferredWidth(100);
            }else if(i==22){
                column.setPreferredWidth(100);
            }else if(i==23){
                column.setPreferredWidth(150);
            }else if(i==24){
                column.setPreferredWidth(100);
            }else if(i==25){
                column.setPreferredWidth(150);
            }else if(i==26){
                column.setPreferredWidth(100);
            }else if(i==27){
                column.setPreferredWidth(150);
            }else if(i==28){
                column.setPreferredWidth(117);
            }else if(i==29){
                column.setPreferredWidth(90);
            }else if(i==30){
                column.setPreferredWidth(110);
            }else if(i==31){
                column.setPreferredWidth(100);
            }else if(i==32){
                column.setPreferredWidth(250);
            }else if(i==33){
                column.setPreferredWidth(150);
            }else if(i==34){
                column.setPreferredWidth(150);
            }else if(i==35){
                column.setPreferredWidth(150);
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
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRiwayatKehamilan=new DefaultTableModel(null,new Object[]{
                "No","Tanggal & Jam","Kala","","Pimpinan & Terapi","id_kala","no_rkm_medis"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbRiwayatKehamilan.setModel(tabModeRiwayatKehamilan);

        tbRiwayatKehamilan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRiwayatKehamilan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbRiwayatKehamilan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(30);
            }else if(i==1){
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(80);
            }else if(i==3){
                column.setPreferredWidth(350);
            }else if(i==4){
                column.setPreferredWidth(350);
            }else if(i==5){
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==6){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwayatKehamilan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRiwayatKehamilan2=new DefaultTableModel(null,new Object[]{
                "No","Tanggal & Jam","Kala","","Pimpinan & Terapi","id_kala","no_rkm_medis"
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
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(80);
            }else if(i==3){
                column.setPreferredWidth(350);
            }else if(i==4){
                column.setPreferredWidth(350);
            }else if(i==5){
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==6){
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
        
//        dokter.addWindowListener(new WindowListener() {
//            @Override
//            public void windowOpened(WindowEvent e) {}
//            @Override
//            public void windowClosing(WindowEvent e) {}
//            @Override
//            public void windowClosed(WindowEvent e) {
//                if(dokter.getTable().getSelectedRow()!= -1){ 
//                    KdDPJP.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
//                    NmDPJP.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),2).toString());  
//                }              
//            }
//            @Override
//            public void windowIconified(WindowEvent e) {}
//            @Override
//            public void windowDeiconified(WindowEvent e) {}
//            @Override
//            public void windowActivated(WindowEvent e) {}
//            @Override
//            public void windowDeactivated(WindowEvent e) {}
//        });
        ChkAccor.setSelected(false);
        isMenu();
        
        // default panelisi
//        panelisi1.setVisible(false);
//        
//        // defeault ketergantungan
//        InputKetergantungan.setVisible(false);
//        KetergantunganSejak.setVisible(false);
//        jLabel76.setVisible(false);
//        
//        // default pilihan lainnya
//        DariLainnya.setVisible(false);
//        MenggunakanLainnya.setVisible(false);
//        CaraDatangLainnya.setVisible(false);
//        
//        // default hubungan denga pasien
//        jLabel37.setVisible(false);
//        HubunganDenganPasien.setVisible(false);
//        
//        // default kesadaran
//        InputPenurunanKesadaran.setVisible(false);
//        
//        // default riwayat alergi
//        InputObat.setVisible(false);
//        InputMakanan.setVisible(false);
//        InputDebu.setVisible(false);
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
        jLabel105 = new widget.Label();
        BtnEdit1 = new widget.Button();
        Kala = new widget.ComboBox();
        TglAsuhan1 = new widget.Tanggal();
        jScrollPane1 = new javax.swing.JScrollPane();
        PimpinanTerapi = new widget.TextArea();
        jLabel100 = new widget.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        InputKala = new widget.TextArea();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnLembarObservasiPasien = new javax.swing.JMenuItem();
        MnPDFObservasiPasien = new javax.swing.JMenuItem();
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
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        TglAsuhan = new widget.Tanggal();
        label12 = new widget.Label();
        PemeriksaanOleh = new widget.TextBox();
        label13 = new widget.Label();
        MasukKarena = new widget.TextBox();
        Scroll6 = new widget.ScrollPane();
        tbRiwayatKehamilan = new widget.Table();
        BtnEditRiwayatPersalinan = new widget.Button();
        BtnHapusRiwayatPersalinan = new widget.Button();
        BtnTambahMasalah = new widget.Button();
        label1 = new widget.Label();
        Ketuban = new widget.ComboBox();
        label2 = new widget.Label();
        TD = new widget.TextBox();
        label3 = new widget.Label();
        Nadi = new widget.TextBox();
        label4 = new widget.Label();
        label5 = new widget.Label();
        ParuParu = new widget.TextBox();
        Udema = new widget.CekBox();
        label6 = new widget.Label();
        DDA = new widget.TextBox();
        label7 = new widget.Label();
        SitusAnak = new widget.TextBox();
        label15 = new widget.Label();
        Jantung = new widget.TextBox();
        label16 = new widget.Label();
        FundusUteri = new widget.TextBox();
        label17 = new widget.Label();
        Suhu = new widget.TextBox();
        label18 = new widget.Label();
        BagianDepan = new widget.TextBox();
        label19 = new widget.Label();
        Pernafasan = new widget.TextBox();
        Gamelli = new widget.ComboBox();
        GerakAnak = new widget.TextBox();
        label20 = new widget.Label();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel94 = new widget.Label();
        label8 = new widget.Label();
        TglLahirBayi = new widget.Tanggal();
        label9 = new widget.Label();
        label10 = new widget.Label();
        label21 = new widget.Label();
        label22 = new widget.Label();
        jLabel57 = new widget.Label();
        jLabel236 = new widget.Label();
        jLabel220 = new widget.Label();
        jLabel223 = new widget.Label();
        jLabel226 = new widget.Label();
        jLabel229 = new widget.Label();
        jLabel219 = new widget.Label();
        SkalaResiko1 = new widget.ComboBox();
        jLabel221 = new widget.Label();
        SkalaResiko2 = new widget.ComboBox();
        jLabel224 = new widget.Label();
        SkalaResiko3 = new widget.ComboBox();
        jLabel227 = new widget.Label();
        SkalaResiko4 = new widget.ComboBox();
        jLabel230 = new widget.Label();
        SkalaResiko5 = new widget.ComboBox();
        jLabel218 = new widget.Label();
        NilaiResiko1 = new widget.TextBox();
        jLabel222 = new widget.Label();
        NilaiResiko2 = new widget.TextBox();
        jLabel225 = new widget.Label();
        NilaiResiko3 = new widget.TextBox();
        jLabel228 = new widget.Label();
        NilaiResiko4 = new widget.TextBox();
        jLabel231 = new widget.Label();
        NilaiResiko5 = new widget.TextBox();
        jLabel235 = new widget.Label();
        NilaiResikoTotal = new widget.TextBox();
        WaktuKematian = new widget.ComboBox();
        Sex = new widget.ComboBox();
        JenisKelahiran = new widget.ComboBox();
        KondisiBayi = new widget.ComboBox();
        label23 = new widget.Label();
        label24 = new widget.Label();
        SebabKematian = new widget.TextBox();
        PosisiPunggung = new widget.ComboBox();
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
        scrollPane8 = new widget.ScrollPane();
        DiagnosaKebidanan2 = new widget.TextArea();

        DlgRiwayatPersalinan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgRiwayatPersalinan.setName("DlgRiwayatPersalinan"); // NOI18N
        DlgRiwayatPersalinan.setUndecorated(true);
        DlgRiwayatPersalinan.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Observasi Persalinan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 70, 50))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setPreferredSize(new java.awt.Dimension(10, 50));
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa2.setName("panelBiasa2"); // NOI18N
        panelBiasa2.setPreferredSize(new java.awt.Dimension(30, 50));
        panelBiasa2.setLayout(null);

        jLabel99.setText("Pimpinan dan Terapi :");
        jLabel99.setName("jLabel99"); // NOI18N
        panelBiasa2.add(jLabel99);
        jLabel99.setBounds(0, 160, 130, 23);

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
        BtnKeluarKehamilan.setBounds(400, 300, 100, 30);

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
        BtnSimpanRiwayatKehamilan.setBounds(150, 300, 100, 30);

        jLabel105.setText("Kala :");
        jLabel105.setName("jLabel105"); // NOI18N
        panelBiasa2.add(jLabel105);
        jLabel105.setBounds(0, 40, 40, 20);

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
        BtnEdit1.setBounds(270, 300, 100, 30);

        Kala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kala 1", "Kala 2", "Kala 3", "Kala 4" }));
        Kala.setName("Kala"); // NOI18N
        panelBiasa2.add(Kala);
        Kala.setBounds(50, 40, 80, 23);

        TglAsuhan1.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-10-2023 10:18:28" }));
        TglAsuhan1.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsuhan1.setName("TglAsuhan1"); // NOI18N
        TglAsuhan1.setOpaque(false);
        TglAsuhan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TglAsuhan1ActionPerformed(evt);
            }
        });
        TglAsuhan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsuhan1KeyPressed(evt);
            }
        });
        panelBiasa2.add(TglAsuhan1);
        TglAsuhan1.setBounds(140, 10, 140, 23);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        PimpinanTerapi.setColumns(20);
        PimpinanTerapi.setRows(5);
        PimpinanTerapi.setName("PimpinanTerapi"); // NOI18N
        jScrollPane1.setViewportView(PimpinanTerapi);

        panelBiasa2.add(jScrollPane1);
        jScrollPane1.setBounds(140, 160, 358, 108);

        jLabel100.setText("Tanggal :");
        jLabel100.setName("jLabel100"); // NOI18N
        panelBiasa2.add(jLabel100);
        jLabel100.setBounds(0, 10, 130, 23);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        InputKala.setColumns(20);
        InputKala.setRows(5);
        InputKala.setName("InputKala"); // NOI18N
        jScrollPane2.setViewportView(InputKala);

        panelBiasa2.add(jScrollPane2);
        jScrollPane2.setBounds(140, 40, 358, 108);

        internalFrame4.add(panelBiasa2, java.awt.BorderLayout.CENTER);

        DlgRiwayatPersalinan.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnLembarObservasiPasien.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarObservasiPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarObservasiPasien.setForeground(new java.awt.Color(50, 50, 50));
        MnLembarObservasiPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarObservasiPasien.setText("Lembar Observasi Pasien");
        MnLembarObservasiPasien.setName("MnLembarObservasiPasien"); // NOI18N
        MnLembarObservasiPasien.setPreferredSize(new java.awt.Dimension(230, 26));
        MnLembarObservasiPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarObservasiPasienActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLembarObservasiPasien);

        MnPDFObservasiPasien.setBackground(new java.awt.Color(255, 255, 254));
        MnPDFObservasiPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPDFObservasiPasien.setForeground(new java.awt.Color(50, 50, 50));
        MnPDFObservasiPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPDFObservasiPasien.setText("PDF Skrining Pasien Telepon");
        MnPDFObservasiPasien.setActionCommand("PDF Observasi Pasien");
        MnPDFObservasiPasien.setName("MnPDFObservasiPasien"); // NOI18N
        MnPDFObservasiPasien.setPreferredSize(new java.awt.Dimension(230, 26));
        MnPDFObservasiPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPDFObservasiPasienActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPDFObservasiPasien);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(933, 500));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Observasi Persalinan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setPreferredSize(new java.awt.Dimension(933, 300));
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
        TabRawat.setPreferredSize(new java.awt.Dimension(923, 176));
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 800));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 800));
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

        label14.setText("Posisi Punggung :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(420, 130, 90, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.setPreferredSize(new java.awt.Dimension(80, 23));
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas);
        KdPetugas.setBounds(520, 40, 100, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setName("NmPetugas"); // NOI18N
        NmPetugas.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPetugas);
        NmPetugas.setBounds(620, 40, 210, 23);

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
        BtnPetugas.setBounds(830, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 10, 90, 23);

        jLabel10.setText("No.Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 70, 23);

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(730, 10, 70, 23);

        TglAsuhan.setForeground(new java.awt.Color(50, 70, 50));
        TglAsuhan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-10-2023 10:18:26" }));
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
        TglAsuhan.setBounds(810, 10, 140, 23);

        label12.setText("Pemeriksaan Luar Oleh :");
        label12.setName("label12"); // NOI18N
        FormInput.add(label12);
        label12.setBounds(0, 40, 136, 23);

        PemeriksaanOleh.setName("PemeriksaanOleh"); // NOI18N
        FormInput.add(PemeriksaanOleh);
        PemeriksaanOleh.setBounds(140, 40, 140, 23);

        label13.setText("Masuk Karena :");
        label13.setName("label13"); // NOI18N
        FormInput.add(label13);
        label13.setBounds(0, 70, 90, 23);

        MasukKarena.setName("MasukKarena"); // NOI18N
        MasukKarena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MasukKarenaActionPerformed(evt);
            }
        });
        FormInput.add(MasukKarena);
        MasukKarena.setBounds(100, 70, 140, 23);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRiwayatKehamilan.setName("tbRiwayatKehamilan"); // NOI18N
        Scroll6.setViewportView(tbRiwayatKehamilan);

        FormInput.add(Scroll6);
        Scroll6.setBounds(40, 290, 744, 150);

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
        FormInput.add(BtnEditRiwayatPersalinan);
        BtnEditRiwayatPersalinan.setBounds(10, 350, 28, 23);

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
        FormInput.add(BtnHapusRiwayatPersalinan);
        BtnHapusRiwayatPersalinan.setBounds(10, 320, 28, 23);

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
        FormInput.add(BtnTambahMasalah);
        BtnTambahMasalah.setBounds(10, 290, 28, 23);

        label1.setText("Ketuban :");
        label1.setName("label1"); // NOI18N
        FormInput.add(label1);
        label1.setBounds(0, 100, 90, 23);

        Ketuban.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Belum", "Pecah" }));
        Ketuban.setName("Ketuban"); // NOI18N
        FormInput.add(Ketuban);
        Ketuban.setBounds(100, 100, 90, 23);

        label2.setText("Pemeriksaan Paru-Paru :");
        label2.setName("label2"); // NOI18N
        FormInput.add(label2);
        label2.setBounds(0, 220, 130, 23);

        TD.setName("TD"); // NOI18N
        FormInput.add(TD);
        TD.setBounds(90, 160, 80, 23);

        label3.setText("Nadi (x/menit) :");
        label3.setName("label3"); // NOI18N
        FormInput.add(label3);
        label3.setBounds(170, 160, 90, 23);

        Nadi.setName(""); // NOI18N
        FormInput.add(Nadi);
        Nadi.setBounds(270, 160, 64, 23);

        label4.setText("D.D.A :");
        label4.setName("label4"); // NOI18N
        FormInput.add(label4);
        label4.setBounds(40, 190, 40, 23);

        label5.setText("TD (mmHg) :");
        label5.setName("label5"); // NOI18N
        FormInput.add(label5);
        label5.setBounds(10, 160, 70, 23);

        ParuParu.setName("ParuParu"); // NOI18N
        FormInput.add(ParuParu);
        ParuParu.setBounds(140, 220, 110, 23);

        Udema.setText("Udema");
        Udema.setName("Udema"); // NOI18N
        Udema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UdemaActionPerformed(evt);
            }
        });
        FormInput.add(Udema);
        Udema.setBounds(180, 190, 70, 23);

        label6.setText("Keadaan Umum :");
        label6.setName("label6"); // NOI18N
        FormInput.add(label6);
        label6.setBounds(0, 130, 90, 23);

        DDA.setName("DDA"); // NOI18N
        FormInput.add(DDA);
        DDA.setBounds(90, 190, 80, 23);

        label7.setText("Jenis Kelahiran :");
        label7.setName("label7"); // NOI18N
        FormInput.add(label7);
        label7.setBounds(10, 540, 130, 23);

        SitusAnak.setName("SitusAnak"); // NOI18N
        FormInput.add(SitusAnak);
        SitusAnak.setBounds(520, 100, 140, 23);

        label15.setText("Petugas :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(440, 40, 70, 23);

        Jantung.setName("Jantung"); // NOI18N
        FormInput.add(Jantung);
        Jantung.setBounds(140, 250, 110, 23);

        label16.setText("Fundus Uteri :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label16);
        label16.setBounds(440, 70, 70, 23);

        FundusUteri.setName("FundusUteri"); // NOI18N
        FormInput.add(FundusUteri);
        FundusUteri.setBounds(520, 70, 140, 23);

        label17.setText("Suhu (°C) :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label17);
        label17.setBounds(450, 190, 60, 23);

        Suhu.setName("Suhu"); // NOI18N
        Suhu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuhuActionPerformed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(520, 190, 90, 23);

        label18.setText("Gamelli/Tunggal :");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label18);
        label18.setBounds(400, 220, 110, 23);

        BagianDepan.setName("BagianDepan"); // NOI18N
        FormInput.add(BagianDepan);
        BagianDepan.setBounds(520, 160, 140, 23);

        label19.setText("Pernafasan (x/menit) :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label19);
        label19.setBounds(620, 190, 120, 23);

        Pernafasan.setName("Pernafasan"); // NOI18N
        Pernafasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PernafasanActionPerformed(evt);
            }
        });
        FormInput.add(Pernafasan);
        Pernafasan.setBounds(750, 190, 90, 23);

        Gamelli.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gamelli", "Tunggal" }));
        Gamelli.setName("Gamelli"); // NOI18N
        Gamelli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GamelliActionPerformed(evt);
            }
        });
        FormInput.add(Gamelli);
        Gamelli.setBounds(520, 220, 110, 23);

        GerakAnak.setName("GerakAnak"); // NOI18N
        FormInput.add(GerakAnak);
        GerakAnak.setBounds(520, 250, 130, 23);

        label20.setText("Gerak Anak :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label20);
        label20.setBounds(400, 250, 110, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 451, 880, 3);

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("KEADAAN BAYI SAAT LAHIR");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(10, 460, 180, 23);

        label8.setText("Pemeriksaan Jantung :");
        label8.setName("label8"); // NOI18N
        FormInput.add(label8);
        label8.setBounds(0, 250, 130, 23);

        TglLahirBayi.setForeground(new java.awt.Color(50, 70, 50));
        TglLahirBayi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-10-2023 10:18:28" }));
        TglLahirBayi.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglLahirBayi.setName("TglLahirBayi"); // NOI18N
        TglLahirBayi.setOpaque(false);
        TglLahirBayi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TglLahirBayiActionPerformed(evt);
            }
        });
        TglLahirBayi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglLahirBayiKeyPressed(evt);
            }
        });
        FormInput.add(TglLahirBayi);
        TglLahirBayi.setBounds(150, 480, 140, 23);

        label9.setText("Tangal & Jam lahir :");
        label9.setName("label9"); // NOI18N
        FormInput.add(label9);
        label9.setBounds(10, 480, 130, 23);

        label10.setText("Sex :");
        label10.setName("label10"); // NOI18N
        FormInput.add(label10);
        label10.setBounds(10, 510, 130, 23);

        label21.setText("Kondisi Bayi :");
        label21.setName("label21"); // NOI18N
        FormInput.add(label21);
        label21.setBounds(10, 570, 130, 23);

        label22.setText("Sebab Kematian :");
        label22.setName("label22"); // NOI18N
        FormInput.add(label22);
        label22.setBounds(410, 570, 90, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("PENILAIAN BAYI \"APGAR SCORE\"");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(10, 600, 210, 23);

        jLabel236.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel236.setText("1. Frekuensi Jantung");
        jLabel236.setName("jLabel236"); // NOI18N
        FormInput.add(jLabel236);
        jLabel236.setBounds(30, 620, 260, 23);

        jLabel220.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel220.setText("2. Usaha Bernafas");
        jLabel220.setName("jLabel220"); // NOI18N
        FormInput.add(jLabel220);
        jLabel220.setBounds(30, 650, 260, 23);

        jLabel223.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel223.setText("3. Tonus Otot");
        jLabel223.setName("jLabel223"); // NOI18N
        FormInput.add(jLabel223);
        jLabel223.setBounds(30, 680, 260, 23);

        jLabel226.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel226.setText("4. Refleks");
        jLabel226.setName("jLabel226"); // NOI18N
        FormInput.add(jLabel226);
        jLabel226.setBounds(30, 710, 260, 23);

        jLabel229.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel229.setText("5. Warna");
        jLabel229.setName("jLabel229"); // NOI18N
        FormInput.add(jLabel229);
        jLabel229.setBounds(30, 740, 260, 23);

        jLabel219.setText("Skala :");
        jLabel219.setName("jLabel219"); // NOI18N
        FormInput.add(jLabel219);
        jLabel219.setBounds(270, 620, 80, 23);

        SkalaResiko1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "< 100 x/menit", "> 100 x/menit" }));
        SkalaResiko1.setName("SkalaResiko1"); // NOI18N
        SkalaResiko1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko1ItemStateChanged(evt);
            }
        });
        SkalaResiko1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko1KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko1);
        SkalaResiko1.setBounds(360, 620, 280, 23);

        jLabel221.setText("Skala :");
        jLabel221.setName("jLabel221"); // NOI18N
        FormInput.add(jLabel221);
        jLabel221.setBounds(270, 650, 80, 23);

        SkalaResiko2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Lambat", "Menangis Kuat" }));
        SkalaResiko2.setName("SkalaResiko2"); // NOI18N
        SkalaResiko2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko2ItemStateChanged(evt);
            }
        });
        SkalaResiko2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko2KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko2);
        SkalaResiko2.setBounds(360, 650, 280, 23);

        jLabel224.setText("Skala :");
        jLabel224.setName("jLabel224"); // NOI18N
        FormInput.add(jLabel224);
        jLabel224.setBounds(270, 680, 80, 23);

        SkalaResiko3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lumpuh", "Ekstremitas Fleksi", "Gerakan Sedikit" }));
        SkalaResiko3.setName("SkalaResiko3"); // NOI18N
        SkalaResiko3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko3ItemStateChanged(evt);
            }
        });
        SkalaResiko3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko3KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko3);
        SkalaResiko3.setBounds(360, 680, 280, 23);

        jLabel227.setText("Skala :");
        jLabel227.setName("jLabel227"); // NOI18N
        FormInput.add(jLabel227);
        jLabel227.setBounds(270, 710, 80, 23);

        SkalaResiko4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Bergerak", "Gerakan Sedikit", "Reaksi Melawan" }));
        SkalaResiko4.setName("SkalaResiko4"); // NOI18N
        SkalaResiko4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko4ItemStateChanged(evt);
            }
        });
        SkalaResiko4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko4KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko4);
        SkalaResiko4.setBounds(360, 710, 280, 23);

        jLabel230.setText("Skala :");
        jLabel230.setName("jLabel230"); // NOI18N
        FormInput.add(jLabel230);
        jLabel230.setBounds(270, 740, 80, 23);

        SkalaResiko5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Biru Pucat", "Tubuh Kemerahan Tangan dan Kaki Biru", "Kemerahan" }));
        SkalaResiko5.setName("SkalaResiko5"); // NOI18N
        SkalaResiko5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SkalaResiko5ItemStateChanged(evt);
            }
        });
        SkalaResiko5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SkalaResiko5KeyPressed(evt);
            }
        });
        FormInput.add(SkalaResiko5);
        SkalaResiko5.setBounds(360, 740, 280, 23);

        jLabel218.setText("Nilai :");
        jLabel218.setName("jLabel218"); // NOI18N
        FormInput.add(jLabel218);
        jLabel218.setBounds(650, 620, 70, 23);

        NilaiResiko1.setEditable(false);
        NilaiResiko1.setText("0");
        NilaiResiko1.setFocusTraversalPolicyProvider(true);
        NilaiResiko1.setName("NilaiResiko1"); // NOI18N
        NilaiResiko1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NilaiResiko1ActionPerformed(evt);
            }
        });
        FormInput.add(NilaiResiko1);
        NilaiResiko1.setBounds(730, 620, 60, 23);

        jLabel222.setText("Nilai :");
        jLabel222.setName("jLabel222"); // NOI18N
        FormInput.add(jLabel222);
        jLabel222.setBounds(650, 650, 70, 23);

        NilaiResiko2.setEditable(false);
        NilaiResiko2.setText("0");
        NilaiResiko2.setFocusTraversalPolicyProvider(true);
        NilaiResiko2.setName("NilaiResiko2"); // NOI18N
        FormInput.add(NilaiResiko2);
        NilaiResiko2.setBounds(730, 650, 60, 23);

        jLabel225.setText("Nilai :");
        jLabel225.setName("jLabel225"); // NOI18N
        FormInput.add(jLabel225);
        jLabel225.setBounds(650, 680, 70, 23);

        NilaiResiko3.setEditable(false);
        NilaiResiko3.setText("0");
        NilaiResiko3.setFocusTraversalPolicyProvider(true);
        NilaiResiko3.setName("NilaiResiko3"); // NOI18N
        FormInput.add(NilaiResiko3);
        NilaiResiko3.setBounds(730, 680, 60, 23);

        jLabel228.setText("Nilai :");
        jLabel228.setName("jLabel228"); // NOI18N
        FormInput.add(jLabel228);
        jLabel228.setBounds(650, 710, 70, 23);

        NilaiResiko4.setEditable(false);
        NilaiResiko4.setText("0");
        NilaiResiko4.setFocusTraversalPolicyProvider(true);
        NilaiResiko4.setName("NilaiResiko4"); // NOI18N
        FormInput.add(NilaiResiko4);
        NilaiResiko4.setBounds(730, 710, 60, 23);

        jLabel231.setText("Nilai :");
        jLabel231.setName("jLabel231"); // NOI18N
        FormInput.add(jLabel231);
        jLabel231.setBounds(650, 740, 70, 23);

        NilaiResiko5.setEditable(false);
        NilaiResiko5.setText("0");
        NilaiResiko5.setFocusTraversalPolicyProvider(true);
        NilaiResiko5.setName("NilaiResiko5"); // NOI18N
        FormInput.add(NilaiResiko5);
        NilaiResiko5.setBounds(730, 740, 60, 23);

        jLabel235.setText("Total :");
        jLabel235.setName("jLabel235"); // NOI18N
        FormInput.add(jLabel235);
        jLabel235.setBounds(650, 770, 70, 23);

        NilaiResikoTotal.setEditable(false);
        NilaiResikoTotal.setText("0");
        NilaiResikoTotal.setFocusTraversalPolicyProvider(true);
        NilaiResikoTotal.setName("NilaiResikoTotal"); // NOI18N
        FormInput.add(NilaiResikoTotal);
        NilaiResikoTotal.setBounds(730, 770, 60, 23);

        WaktuKematian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sebelum Persalinan", "Saat Persalinan" }));
        WaktuKematian.setName("WaktuKematian"); // NOI18N
        FormInput.add(WaktuKematian);
        WaktuKematian.setBounds(250, 570, 150, 23);

        Sex.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-Laki", "Perempuan" }));
        Sex.setName("Sex"); // NOI18N
        FormInput.add(Sex);
        Sex.setBounds(150, 510, 110, 23);

        JenisKelahiran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kelahiran Tunggal", "Kelahiran Kembar / Multiple" }));
        JenisKelahiran.setName("JenisKelahiran"); // NOI18N
        FormInput.add(JenisKelahiran);
        JenisKelahiran.setBounds(150, 540, 170, 23);

        KondisiBayi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hidup", "Mati" }));
        KondisiBayi.setName("KondisiBayi"); // NOI18N
        KondisiBayi.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                KondisiBayiItemStateChanged(evt);
            }
        });
        FormInput.add(KondisiBayi);
        KondisiBayi.setBounds(150, 570, 90, 23);

        label23.setText("Bagian Paling Depan :");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label23);
        label23.setBounds(400, 160, 110, 23);

        label24.setText("Situs Anak :");
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label24);
        label24.setBounds(440, 100, 70, 23);

        SebabKematian.setName("SebabKematian"); // NOI18N
        FormInput.add(SebabKematian);
        SebabKematian.setBounds(510, 570, 140, 23);

        PosisiPunggung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Punggung Kanan", "Punggung Kiri" }));
        PosisiPunggung.setName("PosisiPunggung"); // NOI18N
        FormInput.add(PosisiPunggung);
        PosisiPunggung.setBounds(520, 130, 140, 23);

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
        tbObat.setComponentPopupMenu(jPopupMenu1);
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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-10-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-10-2023" }));
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

        scrollPane9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Observasi Persalinan :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane9.setName("scrollPane9"); // NOI18N

        tbRiwayatKehamilan1.setName("tbRiwayatKehamilan1"); // NOI18N
        tbRiwayatKehamilan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatKehamilan1MouseClicked(evt);
            }
        });
        scrollPane9.setViewportView(tbRiwayatKehamilan1);

        FormMasalahRencana.add(scrollPane9);

        scrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Detail Isian Kala :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane7.setName("scrollPane7"); // NOI18N

        DiagnosaKebidanan1.setEditable(false);
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

        scrollPane8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 254)), "Detail Terapi :", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane8.setName("scrollPane8"); // NOI18N

        DiagnosaKebidanan2.setEditable(false);
        DiagnosaKebidanan2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        DiagnosaKebidanan2.setColumns(20);
        DiagnosaKebidanan2.setRows(5);
        DiagnosaKebidanan2.setName("DiagnosaKebidanan2"); // NOI18N
        DiagnosaKebidanan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKebidanan2KeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(DiagnosaKebidanan2);

        FormMasalahRencana.add(scrollPane8);

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
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),6).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),5).toString():finger)+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString()));
            try {
                ps=koneksi.prepareStatement("select * from riwayat_persalinan where no_rkm_medis=?");
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
            
            Valid.MyReportqry("rptCetakPenilaianAwalKebidananRalanNonPartus2.jasper","report","::[ Laporan Penilaian Awal Kebidanan & Kandungan ]::",
                "select observasi_persalinan.no_rawat,observasi_persalinan.no_rm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,observasi_persalinan.tanggal,observasi_persalinan.kd_petugas," +
                "observasi_persalinan.nm_petugas,observasi_persalinan.kd_dpjp,observasi_persalinan.nm_dpjp,observasi_persalinan.cara_datang,observasi_persalinan.cara_datang_lainnya," +
                "observasi_persalinan.menggunakan,observasi_persalinan.menggunakan_lainnya,observasi_persalinan.asal,observasi_persalinan.asal_lainnya,observasi_persalinan.pengkajian_dari," +
                "observasi_persalinan.hubungan_dengan_pasien,observasi_persalinan.alasan_masuk,observasi_persalinan.penyakit_pernah_diderita,CONCAT(observasi_persalinan.nama_riwayat_penyakit, observasi_persalinan.penyakit_lainnya) AS riwayat_penyakit," +
                "observasi_persalinan.faktor_keturunan_gamelli,observasi_persalinan.ketergantungan,observasi_persalinan.ketergantungan_dengan,observasi_persalinan.sejak," +
                "observasi_persalinan.obat_obatan,observasi_persalinan.nama_obatan,observasi_persalinan.makanan,observasi_persalinan.nama_makanan,observasi_persalinan.debu,observasi_persalinan.nama_debu," +
                "observasi_persalinan.alergi_lainnya,observasi_persalinan.menarche,observasi_persalinan.menstruasi,observasi_persalinan.sejak_menstruasi,observasi_persalinan.sakit_saat_menstruasi," +
                "observasi_persalinan.menikah_ke,observasi_persalinan.lamanya_pernikahan,observasi_persalinan.kontrasepsi,observasi_persalinan.lamanya_kontrasepsi,observasi_persalinan.graphit," +
                "observasi_persalinan.paritas,observasi_persalinan.abortus,observasi_persalinan.haid_terakhir,observasi_persalinan.perkiraan_lahir,observasi_persalinan.umur_kehamilan," +
                "observasi_persalinan.keluhan_kehamilan,observasi_persalinan.tinggi_fundus_uteri,observasi_persalinan.letak_punggung_janin,observasi_persalinan.presentasi_janin," +
                "observasi_persalinan.taksiran_berat_janin,observasi_persalinan.penurunan,observasi_persalinan.aukultasi,observasi_persalinan.frekuensi_aukultasi,observasi_persalinan.pemeriksaan_dalam," +
                "observasi_persalinan.td,observasi_persalinan.nadi,observasi_persalinan.rr,observasi_persalinan.suhu,observasi_persalinan.tb,observasi_persalinan.bb,observasi_persalinan.keadaan_umum," +
                "observasi_persalinan.gcs,observasi_persalinan.kesadaran,observasi_persalinan.input_penurunan_kesadaran,observasi_persalinan.kepala,observasi_persalinan.mata,observasi_persalinan.hidung,observasi_persalinan.gigi_mulut, "+
                "observasi_persalinan.tenggorokan,observasi_persalinan.telinga,observasi_persalinan.ekstremitas,observasi_persalinan.leher,observasi_persalinan.thoraks,observasi_persalinan.jantung,observasi_persalinan.paru,observasi_persalinan.abdomen, "+
                "observasi_persalinan.genitalis_anus,observasi_persalinan.nyeri,observasi_persalinan.skor,observasi_persalinan.kategori,observasi_persalinan.pengaruh_nyeri,observasi_persalinan.hb,observasi_persalinan.hasil_usg, "+
                "observasi_persalinan.status_mental,observasi_persalinan.respon_emosi,observasi_persalinan.suport_suami,concat(observasi_persalinan.masalah_kebidanan,observasi_persalinan.masalah_kebidanan_lainnya) as masalah_kebidanann,observasi_persalinan.diagnosa_kebidanan "+
                "from observasi_persalinan inner join pasien on observasi_persalinan.no_rm_medis=pasien.no_rkm_medis where observasi_persalinan.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);

            Valid.MyReportqry("rptCetakPenilaianAwalKebidananNonPartus.jasper","report","::[ Laporan Penilaian Awal Kebidanan & Kandungan ]::",
                "select observasi_persalinan.no_rawat,observasi_persalinan.no_rm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,observasi_persalinan.tanggal,observasi_persalinan.kd_petugas," +
                "observasi_persalinan.nm_petugas,observasi_persalinan.kd_dpjp,observasi_persalinan.nm_dpjp,observasi_persalinan.cara_datang,observasi_persalinan.cara_datang_lainnya," +
                "observasi_persalinan.menggunakan,observasi_persalinan.menggunakan_lainnya,observasi_persalinan.asal,observasi_persalinan.asal_lainnya,observasi_persalinan.pengkajian_dari," +
                "observasi_persalinan.hubungan_dengan_pasien,observasi_persalinan.alasan_masuk,observasi_persalinan.penyakit_pernah_diderita,CONCAT(observasi_persalinan.nama_riwayat_penyakit, observasi_persalinan.penyakit_lainnya) AS riwayat_penyakit," +
                "observasi_persalinan.faktor_keturunan_gamelli,observasi_persalinan.ketergantungan,observasi_persalinan.ketergantungan_dengan,observasi_persalinan.sejak," +
                "observasi_persalinan.obat_obatan,observasi_persalinan.nama_obatan,observasi_persalinan.makanan,observasi_persalinan.nama_makanan,observasi_persalinan.debu,observasi_persalinan.nama_debu," +
                "observasi_persalinan.alergi_lainnya,observasi_persalinan.menarche,observasi_persalinan.menstruasi,observasi_persalinan.sejak_menstruasi,observasi_persalinan.sakit_saat_menstruasi," +
                "observasi_persalinan.menikah_ke,observasi_persalinan.lamanya_pernikahan,observasi_persalinan.kontrasepsi,observasi_persalinan.lamanya_kontrasepsi,observasi_persalinan.graphit," +
                "observasi_persalinan.paritas,observasi_persalinan.abortus,observasi_persalinan.haid_terakhir,observasi_persalinan.perkiraan_lahir,observasi_persalinan.umur_kehamilan," +
                "observasi_persalinan.keluhan_kehamilan,observasi_persalinan.tinggi_fundus_uteri,observasi_persalinan.letak_punggung_janin,observasi_persalinan.presentasi_janin," +
                "observasi_persalinan.taksiran_berat_janin,observasi_persalinan.penurunan,observasi_persalinan.aukultasi,observasi_persalinan.frekuensi_aukultasi,observasi_persalinan.pemeriksaan_dalam," +
                "observasi_persalinan.td,observasi_persalinan.nadi,observasi_persalinan.rr,observasi_persalinan.suhu,observasi_persalinan.tb,observasi_persalinan.bb,observasi_persalinan.keadaan_umum," +
                "observasi_persalinan.gcs,observasi_persalinan.kesadaran,observasi_persalinan.input_penurunan_kesadaran,observasi_persalinan.kepala,observasi_persalinan.mata,observasi_persalinan.hidung,observasi_persalinan.gigi_mulut, "+
                "observasi_persalinan.tenggorokan,observasi_persalinan.telinga,observasi_persalinan.ekstremitas,observasi_persalinan.leher,observasi_persalinan.thoraks,observasi_persalinan.jantung,observasi_persalinan.paru,observasi_persalinan.abdomen, "+
                "observasi_persalinan.genitalis_anus,observasi_persalinan.nyeri,observasi_persalinan.skor,observasi_persalinan.kategori,observasi_persalinan.pengaruh_nyeri,observasi_persalinan.hb,observasi_persalinan.hasil_usg, "+
                "observasi_persalinan.status_mental,observasi_persalinan.respon_emosi,observasi_persalinan.suport_suami,concat(observasi_persalinan.masalah_kebidanan,observasi_persalinan.masalah_kebidanan_lainnya) as masalah_kebidanann,observasi_persalinan.diagnosa_kebidanan "+
                "from observasi_persalinan inner join pasien on observasi_persalinan.no_rm_medis=pasien.no_rkm_medis where observasi_persalinan.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
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
        if(TNoRM.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Nama Pasien");
        }else if(KdPetugas.getText().trim().equals("")||NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPetugas,"Pengkaji");
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
//        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
//            Valid.textKosong(TNoRw,"Pasien");
//        }else if(KdPetugas.getText().trim().equals("")||NmPetugas.getText().trim().equals("")){
//            Valid.textKosong(BtnPetugas,"Pengkaji");
//        }else if(KdDPJP.getText().trim().equals("")||NmDPJP.getText().trim().equals("")){
//            Valid.textKosong(BtnDPJP,"DPJP");
//        }else{
//            String RiwayatPenyakit=getSelectedPenyakit();
//            String MasalahKebidanann=getSelected();
//            String StatusMental=getSelectedStatusMental();
//            if(Keluhan.getText().trim().equals("")){
//                Valid.textKosong(Menarche,"Keluhan");
//            }else if(PemeriksaanDalam.getText().trim().equals("")){
//                Valid.textKosong(Menikahke,"Pemeriksaan Dalam");
//            }else if(HasilUSG.getText().trim().equals("")){
//                Valid.textKosong(LamanyaMenikah,"Hasil USG");
//            }else if(DiagnosaKebidanan.getText().trim().equals("")){
//                Valid.textKosong(LamanyaMenikah,"Diagnosa Kebidanan");
//            }else{
//                if(Sequel.menyimpantf("observasi_persalinan","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",88,new String[]{
//                    // section 1
//                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdPetugas.getText(),NmPetugas.getText(),KdDPJP.getText(),NmDPJP.getText(),CaraDatang.getSelectedItem().toString(),
//                    CaraDatangLainnya.getText(),Menggunakan.getSelectedItem().toString(),MenggunakanLainnya.getText(),CaraMasuk.getSelectedItem().toString(),DariLainnya.getText(),
//                    PengkajianOleh.getSelectedItem().toString(),HubunganDenganPasien.getText(),AlasanMasuk.getText(),
//                    // section 2
//                    PenyakitDiderita.getSelectedItem().toString(),RiwayatPenyakit,LainnyaInput.getText(),Gamelli.getSelectedItem().toString(),Ketergantungan.getSelectedItem().toString(),InputKetergantungan.getText(),
//                    KetergantunganSejak.getText(),ObatObatan.getSelectedItem().toString(),InputObat.getText(),
//                    Makanan.getSelectedItem().toString(),InputMakanan.getText(),Debu.getSelectedItem().toString(),InputDebu.getText(),AlergiLainnya.getText(),
//                    // section 3
//                    Menarche.getText(),Menstruasi.getSelectedItem().toString(),HariMens.getText(),SakitSaatMens.getSelectedItem().toString(),
//                    Menikahke.getText(),LamanyaMenikah.getText(),NmKontrasepsi.getText(),KontrasepsiLamanya.getText(),
//                    // section 4
//                    InputG.getText(),InputP.getText(),InputA.getText(),Valid.SetTgl(HPHT.getSelectedItem()+""),HPL.getText(),UmurKehamilan.getText(),
//                    Keluhan.getText(),
//                    // section 4.1
//                    TinggiFundus.getText(),LetakPunggungJanin.getSelectedItem().toString(),PresentasiJanin.getSelectedItem().toString(),BeratJanin.getText(),Penurunan.getSelectedItem().toString(),Aukultasi.getText(),FrekAukultasi.getSelectedItem().toString(),
//                    PemeriksaanDalam.getText(),
//                    // section 5
//                    TD.getText(),Nadi.getText(),RR.getText(),Suhu.getText(),TB.getText(),BB.getText(),Keadaan1.getSelectedItem().toString(),GCS.getText(),Kesadaran.getSelectedItem().toString(),InputPenurunanKesadaran.getText(),
//                    Kepala.getSelectedItem().toString(),Mata.getSelectedItem().toString(),Hidung.getSelectedItem().toString(),GigiMulut.getSelectedItem().toString(),Tenggorokan.getSelectedItem().toString(),Telinga.getSelectedItem().toString(),Ekstremitas.getSelectedItem().toString(),
//                    Leher.getSelectedItem().toString(),Thoraks.getSelectedItem().toString(),Jantung.getSelectedItem().toString(),Paru.getSelectedItem().toString(),Abdomen.getSelectedItem().toString(),Anus.getSelectedItem().toString(),
//                    // section 6
//                    Nyeri.getSelectedItem().toString(),SkorNyeri.getSelectedItem().toString(),KategoriNyeri.getSelectedItem().toString(),PengaruhNyeri.getSelectedItem().toString(),
//                    // section 7
//                    HB.getText(),HasilUSG.getText(),
//                    // section 8
//                    StatusMental,
//                    // section 9
//                    ResEmosi.getSelectedItem().toString(),SupportSuami.getSelectedItem().toString(),
//                    // section 10
//                    MasalahKebidanann,MasalahKebidananLainnya.getText(),
//                    // section 11
//                    DiagnosaKebidanan.getText(),TNoRM.getText()
//                })==true){
//                    emptTeks();
//                }
//            }
//        }
          String Udemaa = "";
          if(Udema.isSelected()==true){
              Udemaa = "Ya";
          }else{
              Udemaa = "Tidak";
          }
          if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
          }else if(KdPetugas.getText().trim().equals("")||NmPetugas.getText().trim().equals("")){
            Valid.textKosong(BtnPetugas,"Pengkaji");
          }else if(TglLahir.getText().trim().equals("")){
            Valid.textKosong(TglLahir,"Tanggal Lahir");
          }else if(PemeriksaanOleh.getText().trim().equals("")){
            Valid.textKosong(PemeriksaanOleh,"Pemeriksaan dilakukan oleh");
          }else if(MasukKarena.getText().trim().equals("")){
            Valid.textKosong(MasukKarena,"Masuk Karena");
          }else if(TD.getText().trim().equals("")){
              Valid.textKosong(TD, "TD");
          }else if(Nadi.getText().trim().equals("")){
              Valid.textKosong(Nadi, "Nadi");
          }else if(DDA.getText().trim().equals("")){
            Valid.textKosong(DDA,"DDA");
          }else if(FundusUteri.getText().trim().equals("")){
            Valid.textKosong(FundusUteri,"Fundus Uteri");
          }else if(SitusAnak.getText().trim().equals("")){
              Valid.textKosong(SitusAnak, "Situs Anak");
          }else if(BagianDepan.getText().trim().equals("")){
              Valid.textKosong(BagianDepan, "Bagian Depan");
          }else if(Suhu.getText().trim().equals("")){
              Valid.textKosong(Suhu, "Suhu");
          }else if(Pernafasan.getText().trim().equals("")){
              Valid.textKosong(Pernafasan, "Pernafasan");
          }else if(GerakAnak.getText().trim().equals("")){
              Valid.textKosong(GerakAnak, "Gerak Anak");
          }else if(SebabKematian.getText().trim().equals("")){
              Valid.textKosong(SebabKematian,"Sebaba Kematian");
          }else{
              if(Sequel.menyimpantf("observasi_persalinan","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",39,new String[]{
                    TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),
                    KdPetugas.getText(),NmPetugas.getText(),PemeriksaanOleh.getText(),MasukKarena.getText(),
                    Ketuban.getSelectedItem().toString(),TD.getText(),Nadi.getText(),DDA.getText(),Udemaa,
                    FundusUteri.getText(),SitusAnak.getText(),BagianDepan.getText(),Suhu.getText(),
                    Pernafasan.getText(),Gamelli.getSelectedItem().toString(),GerakAnak.getText(),TNoRM.getText(),ParuParu.getText(),Jantung.getText(),
                    Valid.SetTgl(TglLahirBayi.getSelectedItem()+"")+" "+TglLahirBayi.getSelectedItem().toString().substring(11,19),
                    Sex.getSelectedItem().toString(),JenisKelahiran.getSelectedItem().toString(),KondisiBayi.getSelectedItem().toString(),
                    WaktuKematian.getSelectedItem().toString(),SkalaResiko1.getSelectedItem().toString(),
                    NilaiResiko1.getText(),SkalaResiko2.getSelectedItem().toString(),NilaiResiko2.getText(),
                    SkalaResiko3.getSelectedItem().toString(),NilaiResiko3.getText(),SkalaResiko4.getSelectedItem().toString(),NilaiResiko4.getText(),
                    SkalaResiko5.getSelectedItem().toString(),NilaiResiko5.getText(),NilaiResikoTotal.getText(),PosisiPunggung.getSelectedItem().toString(),SebabKematian.getText()
              })==true){
                    emptTeks();
              }
//            System.out.println(SkalaResiko1.getSelectedItem().toString());
          }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void TglAsuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhanKeyPressed
        //Valid.pindah(evt,Rencana,Informasi);
    }//GEN-LAST:event_TglAsuhanKeyPressed

    private void TglAsuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TglAsuhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglAsuhanActionPerformed

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

    private void MasukKarenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MasukKarenaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MasukKarenaActionPerformed

    private void UdemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UdemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UdemaActionPerformed

    private void SuhuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuhuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuhuActionPerformed

    private void PernafasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PernafasanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PernafasanActionPerformed

    private void GamelliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GamelliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GamelliActionPerformed

    private void BtnEdit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEdit1KeyPressed

    private void BtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit1ActionPerformed
        // TODO add your handling code here:
//        if(UmurAnak.getText().trim().equals("")){
//            Valid.textKosong(UmurAnak,"Tempat Persalinan");
//        }else if(KUAnak.getText().trim().equals("")){
//            Valid.textKosong(KUAnak,"Jenis Persalinan");
//        }else if(RiwayatPersalinan.getText().trim().equals("")){
//            Valid.textKosong(RiwayatPersalinan,"Penyulit Persalinan");
//        }else if(DitolongOleh.getText().trim().equals("")){
//            Valid.textKosong(DitolongOleh,"Keadaan Persalinan");
//        }else if(BBL.getText().trim().equals("")){
//            Valid.textKosong(BBL,"BB/PB");
//        }else{
//            if(Sequel.mengedittf("riwayat_persalinan","id_riwayat=?","no_rkm_medis=?,umur_anak=?,ku_anak=?,riwayat_persalinan=?,ditolong_oleh=?,jk=?,bbl=?",8,new String[]{
//                TNoRM.getText(),
//                UmurAnak.getText(),
//                KUAnak.getText(),
//                RiwayatPersalinan.getText(),
//                DitolongOleh.getText(),
//                JK.getSelectedItem().toString().substring(0,1),
//                BBL.getText(),
//                tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),8).toString()
//            })==true){
//                emptTeksPersalinan();
//                tampilPersalinan();
//                DlgRiwayatPersalinan.dispose();
//            }
//        }
        if(InputKala.getText().trim().equals("")){
            Valid.textKosong(InputKala,"Input Kala");
        }else if(PimpinanTerapi.getText().trim().equals("")){
            Valid.textKosong(PimpinanTerapi,"Inputan dan Terapi");
        }else{
            if(Sequel.mengedittf("observasi_persalinan_lainnya","id_observasi=?","no_rkm_medis=?,tanggal=?,kala=?,inputan_kala=?,terapi=?",6,new String[]{
                TNoRM.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),
                Kala.getSelectedItem().toString(),InputKala.getText(),PimpinanTerapi.getText(),
                tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),5).toString()
            })==true){
                emptTeksPersalinan();
                tampilPersalinan();
                DlgRiwayatPersalinan.dispose();
            }
        }
    }//GEN-LAST:event_BtnEdit1ActionPerformed

    private void BtnSimpanRiwayatKehamilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanRiwayatKehamilanActionPerformed
//        if(UmurAnak.getText().trim().equals("")){
//            Valid.textKosong(UmurAnak,"Tempat Persalinan");
//        }else if(KUAnak.getText().trim().equals("")){
//            Valid.textKosong(KUAnak,"Jenis Persalinan");
//        }else if(RiwayatPersalinan.getText().trim().equals("")){
//            Valid.textKosong(RiwayatPersalinan,"Penyulit Persalinan");
//        }else if(DitolongOleh.getText().trim().equals("")){
//            Valid.textKosong(DitolongOleh,"Keadaan Persalinan");
//        }else if(BBL.getText().trim().equals("")){
//            Valid.textKosong(BBL,"BB/PB");
//        }else{
//            if(Sequel.menyimpantf("riwayat_persalinan","?,?,?,?,?,?,?,?","Riwayat Persalinan",8,new String[]{
//                null,TNoRM.getText(),UmurAnak.getText(),KUAnak.getText(),RiwayatPersalinan.getText(),DitolongOleh.getText(),JK.getSelectedItem().toString().substring(0,1),BBL.getText()
//            })==true){
//                emptTeksPersalinan();
//                tampilPersalinan();
//            }
//        }
        if(InputKala.getText().trim().equals("")){
            Valid.textKosong(InputKala,"Input Kala");
        }else if(PimpinanTerapi.getText().trim().equals("")){
            Valid.textKosong(PimpinanTerapi,"Inputan dan Terapi");
        }else{
            if(Sequel.menyimpantf("observasi_persalinan_lainnya","?,?,?,?,?,?","Observasi Persalinan Lainnya",6,new String[]{
                null,TNoRM.getText(),Valid.SetTgl(TglAsuhan1.getSelectedItem()+"")+" "+TglAsuhan1.getSelectedItem().toString().substring(11,19),
                Kala.getSelectedItem().toString(),InputKala.getText(),PimpinanTerapi.getText()
            })==true){
                emptTeksPersalinan();
                tampilPersalinan();
            }
        }
    }//GEN-LAST:event_BtnSimpanRiwayatKehamilanActionPerformed

    private void BtnKeluarKehamilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarKehamilanActionPerformed
        DlgRiwayatPersalinan.dispose();
    }//GEN-LAST:event_BtnKeluarKehamilanActionPerformed

    private void TglAsuhan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TglAsuhan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglAsuhan1ActionPerformed

    private void TglAsuhan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsuhan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglAsuhan1KeyPressed

    private void BtnTambahMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahMasalahActionPerformed
        if(TNoRM.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Pilih terlebih dahulu pasien yang mau dimasukkan data kelarihannya...");
            //            CaraDatang.requestFocus();
        }else{
            emptTeksPersalinan();
            DlgRiwayatPersalinan.setLocationRelativeTo(internalFrame1);
            DlgRiwayatPersalinan.setVisible(true);
        }
    }//GEN-LAST:event_BtnTambahMasalahActionPerformed

    private void BtnHapusRiwayatPersalinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusRiwayatPersalinanActionPerformed
        if(tbRiwayatKehamilan.getSelectedRow()>-1){
            Sequel.meghapus("observasi_persalinan_lainnya","id_observasi",tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),5).toString());
            tampilPersalinan();
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnHapusRiwayatPersalinanActionPerformed

    private void BtnEditRiwayatPersalinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditRiwayatPersalinanActionPerformed
        // TODO add your handling code here:
                 if(TNoRM.getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Pilih terlebih dahulu pasien yang mau dimasukkan data kelarihannya...");
                    }else{
                        emptTeksPersalinan();
                        DlgRiwayatPersalinan.setLocationRelativeTo(internalFrame1);
                        DlgRiwayatPersalinan.setVisible(true);
                        if(tbRiwayatKehamilan.getSelectedRow()!= -1){
//                                "No","Tanggal & Jam","Kala","","Pimpinan & Terapi","id_kala","no_rkm_medis"
                                 TglAsuhan1.setSelectedItem(tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),1).toString());
                                 Kala.setSelectedItem(tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),2).toString());
                                 InputKala.setText(tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),3).toString());
                                 PimpinanTerapi.setText(tbRiwayatKehamilan.getValueAt(tbRiwayatKehamilan.getSelectedRow(),4).toString());
                             }
                    }
    }//GEN-LAST:event_BtnEditRiwayatPersalinanActionPerformed

    private void tbRiwayatKehamilan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatKehamilan1MouseClicked
        // TODO add your handling code here:
        getMasalah();
    }//GEN-LAST:event_tbRiwayatKehamilan1MouseClicked

    private void DiagnosaKebidanan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKebidanan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaKebidanan2KeyPressed

    private void TglLahirBayiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TglLahirBayiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglLahirBayiActionPerformed

    private void TglLahirBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglLahirBayiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglLahirBayiKeyPressed

    private void SkalaResiko1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko1ItemStateChanged
        if(SkalaResiko1.getSelectedIndex()==0){
            NilaiResiko1.setText("0");
        }else if(SkalaResiko1.getSelectedIndex()==1){
            NilaiResiko1.setText("1");
        }else{
            NilaiResiko1.setText("2");
        }
        isTotalResikoJatuh();
    }//GEN-LAST:event_SkalaResiko1ItemStateChanged

    private void SkalaResiko1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko1KeyPressed
//        Valid.pindah(evt,btnPetugas,SkalaResiko2);
    }//GEN-LAST:event_SkalaResiko1KeyPressed

    private void SkalaResiko2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko2ItemStateChanged
        if(SkalaResiko2.getSelectedIndex()==0){
            NilaiResiko2.setText("0");
        }else if(SkalaResiko2.getSelectedIndex()==1){
            NilaiResiko2.setText("1");
        }else{
            NilaiResiko2.setText("2");
        }
        isTotalResikoJatuh();
    }//GEN-LAST:event_SkalaResiko2ItemStateChanged

    private void SkalaResiko2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko2KeyPressed
        Valid.pindah(evt,SkalaResiko1,SkalaResiko3);
    }//GEN-LAST:event_SkalaResiko2KeyPressed

    private void SkalaResiko3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko3ItemStateChanged
        if(SkalaResiko3.getSelectedIndex()==0){
            NilaiResiko3.setText("0");
        }else if(SkalaResiko3.getSelectedIndex()==1){
            NilaiResiko3.setText("1");
        }else{
            NilaiResiko3.setText("2");
        }
        isTotalResikoJatuh();
    }//GEN-LAST:event_SkalaResiko3ItemStateChanged

    private void SkalaResiko3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko3KeyPressed
        Valid.pindah(evt,SkalaResiko2,SkalaResiko4);
    }//GEN-LAST:event_SkalaResiko3KeyPressed

    private void SkalaResiko4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko4ItemStateChanged
        if(SkalaResiko4.getSelectedIndex()==0){
            NilaiResiko4.setText("0");
        }else if(SkalaResiko4.getSelectedIndex()==1){
            NilaiResiko4.setText("1");
        }else{
            NilaiResiko4.setText("2");
        }
        isTotalResikoJatuh();
    }//GEN-LAST:event_SkalaResiko4ItemStateChanged

    private void SkalaResiko4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko4KeyPressed
        Valid.pindah(evt,SkalaResiko3,SkalaResiko5);
    }//GEN-LAST:event_SkalaResiko4KeyPressed

    private void SkalaResiko5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SkalaResiko5ItemStateChanged
        if(SkalaResiko5.getSelectedIndex()==0){
            NilaiResiko5.setText("0");
        }else if(SkalaResiko5.getSelectedIndex()==1){
            NilaiResiko5.setText("1");
        }else{
            NilaiResiko5.setText("2");
        }
        isTotalResikoJatuh();
    }//GEN-LAST:event_SkalaResiko5ItemStateChanged

    private void SkalaResiko5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SkalaResiko5KeyPressed
//        Valid.pindah(evt,SkalaResiko4,SkalaResiko6);
    }//GEN-LAST:event_SkalaResiko5KeyPressed

    private void KondisiBayiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_KondisiBayiItemStateChanged
        // TODO add your handling code here:
//        isMati();
    }//GEN-LAST:event_KondisiBayiItemStateChanged

    private void NilaiResiko1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NilaiResiko1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NilaiResiko1ActionPerformed

    private void MnLembarObservasiPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarObservasiPasienActionPerformed
//        if(tbPasienTelpon.getSelectedRow()> -1){
//            Map<String, Object> param = new HashMap<>();
//            param.put("namars",akses.getnamars());
//            param.put("alamatrs",akses.getalamatrs());
//            param.put("kotars",akses.getkabupatenrs());
//            param.put("propinsirs",akses.getpropinsirs());
//            param.put("kontakrs",akses.getkontakrs());
//            param.put("emailrs",akses.getemailrs());
//            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
//            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbPasienTelpon.getValueAt(tbPasienTelpon.getSelectedRow(),8).toString());
//            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbPasienTelpon.getValueAt(tbPasienTelpon.getSelectedRow(),9).toString()+"\nID "+(finger.equals("")?tbPasienTelpon.getValueAt(tbPasienTelpon.getSelectedRow(),8).toString():finger)+"\n"+DTPReg1.getSelectedItem());
//            Valid.MyReportqry("rptLembarSkriningPasien1.jasper","report","::[ Lembar Skrining Pasien Telepon ]::",
//                "select skrining_pasien_telepon.tanggal,skrining_pasien_telepon.jam,skrining_pasien_telepon.no_rkm_medis,"+
//                "skrining_pasien_telepon.nm_pasien,skrining_pasien_telepon.tgl_lahir,"+
//                "skrining_pasien_telepon.umur,skrining_pasien_telepon.alamat,skrining_pasien_telepon.jk,skrining_pasien_telepon.kd_petugas,"+
//                "skrining_pasien_telepon.nm_petugas,skrining_pasien_telepon.kd_ptelepon,skrining_pasien_telepon.nm_ptelepon,"+
//                "skrining_pasien_telepon.nm_penelepon,skrining_pasien_telepon.no_telepon,skrining_pasien_telepon.keluhan,"+
//                "skrining_pasien_telepon.tindakan,skrining_pasien_telepon.kondisi,skrining_pasien_telepon.gcs,skrining_pasien_telepon.td,"+
//                "skrining_pasien_telepon.suhu,skrining_pasien_telepon.nadi,skrining_pasien_telepon.pernafasan,skrining_pasien_telepon.saturasi,"+
//                "skrining_pasien_telepon.diagnosa,skrining_pasien_telepon.rekomendasi,skrining_pasien_telepon.saran "+
//                "from skrining_pasien_telepon where skrining_pasien_telepon.no_rkm_medis='"+TNoRM.getText()+"'",param);
//        }else{
//            JOptionPane.showMessageDialog(null,"Maaf silahkan pilih data terlebih dahulu..!!");
//        }
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
                ps=koneksi.prepareStatement("select * from observasi_persalinan_lainnya where no_rkm_medis=?");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
                    rs=ps.executeQuery();
                    i=1;
                    while(rs.next()){
                        param.put("no"+i,i+"");
                        param.put("tanggal"+i,rs.getString("tanggal"));
                        param.put("kala"+i,rs.getString("kala"));
                        param.put("inputan_kala"+i,rs.getString("inputan_kala"));
                        param.put("terapi"+i,rs.getString("terapi"));
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
            
            Valid.MyReportqry("rptLembarObservasiPersalinan.jasper","report","::[ Laporan Observasi Persalinan ]::",
                "select observasi_persalinan.no_rawat,observasi_persalinan.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir, "+ 
                "observasi_persalinan.tanggal,observasi_persalinan.kd_petugas,observasi_persalinan.nm_petugas,"+
                "observasi_persalinan.pemeriksaan_oleh,observasi_persalinan.masuk_karena,observasi_persalinan.ketuban,"+
                "observasi_persalinan.td,observasi_persalinan.nadi,observasi_persalinan.dda,observasi_persalinan.udema,"+
                "observasi_persalinan.fundus_uteri,observasi_persalinan.situs_anak,observasi_persalinan.bagian_paling_depan,"+
                "observasi_persalinan.suhu,observasi_persalinan.pernafasan,observasi_persalinan.gamellitunggal,observasi_persalinan.gerak_anak,"+
                "observasi_persalinan.pemeriksaan_paru,observasi_persalinan.pemeriksaan_jantung,observasi_persalinan.tgl_lahir_bayi,"+
                "observasi_persalinan.sex,observasi_persalinan.jns_kelahiran,observasi_persalinan.kondisi_bayi,observasi_persalinan.sebab_kematian,"+
                "observasi_persalinan.skala1,observasi_persalinan.nilai1,observasi_persalinan.skala2,observasi_persalinan.nilai2,"+
                "observasi_persalinan.skala3,observasi_persalinan.nilai3,observasi_persalinan.skala4,observasi_persalinan.nilai4,"+
                "observasi_persalinan.skala5,observasi_persalinan.nilai5,observasi_persalinan.total_nilai "+
                "from observasi_persalinan inner join pasien on observasi_persalinan.no_rkm_medis=pasien.no_rkm_medis where observasi_persalinan.no_rawat='"+
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
//
//            Valid.MyReportqry("rptCetakPenilaianAwalKebidananPartus.jasper","report","::[ Laporan Penilaian Awal Kebidanan & Kandungan ]::",
//                "select ponek_partus.no_rawat,ponek_partus.no_rm_medis,pasien.nm_pasien,pasien.tgl_lahir,pasien.jk,ponek_partus.tanggal,ponek_partus.kd_petugas," +
//                "ponek_partus.nm_petugas,ponek_partus.kd_dpjp,ponek_partus.nm_dpjp,ponek_partus.cara_datang,ponek_partus.cara_datang_lainnya," +
//                "ponek_partus.menggunakan,ponek_partus.menggunakan_lainnya,ponek_partus.asal,ponek_partus.asal_lainnya,ponek_partus.pengkajian_dari," +
//                "ponek_partus.hubungan_dengan_pasien,ponek_partus.alasan_masuk,ponek_partus.penyakit_pernah_diderita,CONCAT(ponek_partus.nama_riwayat_penyakit, ponek_partus.penyakit_lainnya) AS riwayat_penyakit," +
//                "ponek_partus.faktor_keturunan_gamelli,ponek_partus.ketergantungan,ponek_partus.ketergantungan_dengan,ponek_partus.sejak," +
//                "ponek_partus.obat_obatan,ponek_partus.nama_obatan,ponek_partus.makanan,ponek_partus.nama_makanan,ponek_partus.debu,ponek_partus.nama_debu," +
//                "ponek_partus.alergi_lainnya,ponek_partus.menarche,ponek_partus.menstruasi,ponek_partus.sejak_menstruasi,ponek_partus.sakit_saat_menstruasi," +
//                "ponek_partus.menikah_ke,ponek_partus.lamanya_pernikahan,ponek_partus.kontrasepsi,ponek_partus.lamanya_kontrasepsi,ponek_partus.graphit," +
//                "ponek_partus.paritas,ponek_partus.abortus,ponek_partus.haid_terakhir,ponek_partus.perkiraan_lahir,ponek_partus.umur_kehamilan," +
//                "ponek_partus.keluhan_kehamilan,ponek_partus.tinggi_fundus_uteri,ponek_partus.letak_punggung_janin,ponek_partus.presentasi_janin," +
//                "ponek_partus.taksiran_berat_janin,ponek_partus.penurunan,ponek_partus.aukultasi,ponek_partus.frekuensi_aukultasi,ponek_partus.pemeriksaan_dalam," +
//                "ponek_partus.td,ponek_partus.nadi,ponek_partus.rr,ponek_partus.suhu,ponek_partus.tb,ponek_partus.bb,ponek_partus.keadaan_umum," +
//                "ponek_partus.gcs,ponek_partus.kesadaran,ponek_partus.input_penurunan_kesadaran,ponek_partus.kepala,ponek_partus.mata,ponek_partus.hidung,ponek_partus.gigi_mulut, "+
//                "ponek_partus.tenggorokan,ponek_partus.telinga,ponek_partus.ekstremitas,ponek_partus.leher,ponek_partus.thoraks,ponek_partus.jantung,ponek_partus.paru,ponek_partus.abdomen, "+
//                "ponek_partus.genitalis_anus,ponek_partus.nyeri,ponek_partus.skor,ponek_partus.kategori,ponek_partus.pengaruh_nyeri,ponek_partus.hb,ponek_partus.hasil_usg, "+
//                "ponek_partus.status_mental,ponek_partus.respon_emosi,ponek_partus.suport_suami,concat(ponek_partus.masalah_kebidanan,ponek_partus.masalah_kebidanan_lainnya) as masalah_kebidanann,ponek_partus.diagnosa_kebidanan "+
//                "from ponek_partus inner join pasien on ponek_partus.no_rm_medis=pasien.no_rkm_medis where ponek_partus.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }
    }//GEN-LAST:event_MnLembarObservasiPasienActionPerformed

    private void MnPDFObservasiPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPDFObservasiPasienActionPerformed
//        if(tbPasienTelpon.getSelectedRow()> -1){
//            Map<String, Object> param = new HashMap<>();
//            param.put("namars",akses.getnamars());
//            param.put("alamatrs",akses.getalamatrs());
//            param.put("kotars",akses.getkabupatenrs());
//            param.put("propinsirs",akses.getpropinsirs());
//            param.put("kontakrs",akses.getkontakrs());
//            param.put("emailrs",akses.getemailrs());
//            param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
//            //            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
//            //            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),14).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),13).toString():finger)+"\n"+DTPReg.getSelectedItem());
//            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbPasienTelpon.getValueAt(tbPasienTelpon.getSelectedRow(),8).toString());
//            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbPasienTelpon.getValueAt(tbPasienTelpon.getSelectedRow(),9).toString()+"\nID "+(finger.equals("")?tbPasienTelpon.getValueAt(tbPasienTelpon.getSelectedRow(),8).toString():finger)+"\n"+DTPReg1.getSelectedItem());
//            Valid.MyReportqrypdf("rptLembarSkriningPasien1.jasper","report","::[ Lembar Skrining Pasien Telepon ]::",
//                "select skrining_pasien_telepon.tanggal,skrining_pasien_telepon.jam,skrining_pasien_telepon.no_rkm_medis,"+
//                "skrining_pasien_telepon.nm_pasien,skrining_pasien_telepon.tgl_lahir,"+
//                "skrining_pasien_telepon.umur,skrining_pasien_telepon.alamat,skrining_pasien_telepon.jk,skrining_pasien_telepon.kd_petugas,"+
//                "skrining_pasien_telepon.nm_petugas,skrining_pasien_telepon.kd_ptelepon,skrining_pasien_telepon.nm_ptelepon,"+
//                "skrining_pasien_telepon.nm_penelepon,skrining_pasien_telepon.no_telepon,skrining_pasien_telepon.keluhan,"+
//                "skrining_pasien_telepon.tindakan,skrining_pasien_telepon.kondisi,skrining_pasien_telepon.gcs,skrining_pasien_telepon.td,"+
//                "skrining_pasien_telepon.suhu,skrining_pasien_telepon.nadi,skrining_pasien_telepon.pernafasan,skrining_pasien_telepon.saturasi,"+
//                "skrining_pasien_telepon.diagnosa,skrining_pasien_telepon.rekomendasi,skrining_pasien_telepon.saran "+
//                "from skrining_pasien_telepon where skrining_pasien_telepon.no_rkm_medis='"+TNoRM.getText()+"'",param);
//        }else{
//            JOptionPane.showMessageDialog(null,"Maaf silahkan pilih data terlebih dahulu..!!");
//        }
        
    }//GEN-LAST:event_MnPDFObservasiPasienActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMObservasiKebidanan dialog = new RMObservasiKebidanan(new javax.swing.JFrame(), true);
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
    private widget.TextBox BagianDepan;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
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
    private widget.CekBox ChkAccor;
    private widget.TextBox DDA;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextArea DiagnosaKebidanan1;
    private widget.TextArea DiagnosaKebidanan2;
    private javax.swing.JDialog DlgRiwayatPersalinan;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMasalahRencana;
    private widget.PanelBiasa FormMenu;
    private widget.TextBox FundusUteri;
    private widget.ComboBox Gamelli;
    private widget.TextBox GerakAnak;
    private widget.TextArea InputKala;
    private widget.TextBox Jantung;
    private widget.ComboBox JenisKelahiran;
    private widget.ComboBox Kala;
    private widget.TextBox KdPetugas;
    private widget.ComboBox Ketuban;
    private widget.ComboBox KondisiBayi;
    private widget.Label LCount;
    private widget.TextBox MasukKarena;
    private javax.swing.JMenuItem MnLembarObservasiPasien;
    private javax.swing.JMenuItem MnPDFObservasiPasien;
    private widget.TextBox Nadi;
    private widget.TextBox NilaiResiko1;
    private widget.TextBox NilaiResiko2;
    private widget.TextBox NilaiResiko3;
    private widget.TextBox NilaiResiko4;
    private widget.TextBox NilaiResiko5;
    private widget.TextBox NilaiResikoTotal;
    private widget.TextBox NmPetugas;
    private widget.PanelBiasa PanelAccor;
    private widget.TextBox ParuParu;
    private widget.TextBox PemeriksaanOleh;
    private widget.TextBox Pernafasan;
    private widget.TextArea PimpinanTerapi;
    private widget.ComboBox PosisiPunggung;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll6;
    private widget.TextBox SebabKematian;
    private widget.ComboBox Sex;
    private widget.TextBox SitusAnak;
    private widget.ComboBox SkalaResiko1;
    private widget.ComboBox SkalaResiko2;
    private widget.ComboBox SkalaResiko3;
    private widget.ComboBox SkalaResiko4;
    private widget.ComboBox SkalaResiko5;
    private widget.TextBox Suhu;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglAsuhan;
    private widget.Tanggal TglAsuhan1;
    private widget.TextBox TglLahir;
    private widget.Tanggal TglLahirBayi;
    private widget.CekBox Udema;
    private widget.ComboBox WaktuKematian;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel105;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel218;
    private widget.Label jLabel219;
    private widget.Label jLabel220;
    private widget.Label jLabel221;
    private widget.Label jLabel222;
    private widget.Label jLabel223;
    private widget.Label jLabel224;
    private widget.Label jLabel225;
    private widget.Label jLabel226;
    private widget.Label jLabel227;
    private widget.Label jLabel228;
    private widget.Label jLabel229;
    private widget.Label jLabel230;
    private widget.Label jLabel231;
    private widget.Label jLabel235;
    private widget.Label jLabel236;
    private widget.Label jLabel34;
    private widget.Label jLabel57;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel94;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private widget.Label label1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label2;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.Label label3;
    private widget.Label label4;
    private widget.Label label5;
    private widget.Label label6;
    private widget.Label label7;
    private widget.Label label8;
    private widget.Label label9;
    private widget.PanelBiasa panelBiasa2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
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
                "select observasi_persalinan.no_rawat,observasi_persalinan.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir, "+
                "observasi_persalinan.tanggal,observasi_persalinan.kd_petugas,observasi_persalinan.nm_petugas,observasi_persalinan.pemeriksaan_oleh,"+
                "observasi_persalinan.masuk_karena,observasi_persalinan.ketuban,observasi_persalinan.td,observasi_persalinan.nadi,observasi_persalinan.dda,"+
                "observasi_persalinan.udema,observasi_persalinan.fundus_uteri,observasi_persalinan.situs_anak,observasi_persalinan.bagian_paling_depan,"+
                "observasi_persalinan.suhu,observasi_persalinan.pernafasan,observasi_persalinan.gamellitunggal,observasi_persalinan.gerak_anak,observasi_persalinan.no_rkm_medis,observasi_persalinan.pemeriksaan_paru,observasi_persalinan.pemeriksaan_jantung, "+
                "observasi_persalinan.tgl_lahir_bayi,observasi_persalinan.sex,observasi_persalinan.jns_kelahiran,observasi_persalinan.kondisi_bayi,observasi_persalinan.sebab_kematian,observasi_persalinan.skala1,observasi_persalinan.nilai1, "+
                "observasi_persalinan.skala2,observasi_persalinan.nilai2,observasi_persalinan.skala3,observasi_persalinan.nilai3,observasi_persalinan.skala4,observasi_persalinan.nilai4,observasi_persalinan.skala5, observasi_persalinan.nilai5,observasi_persalinan.total_nilai,observasi_persalinan.posisi_punggung,observasi_persalinan.sebab_kematian2 "+
                "from observasi_persalinan inner join pasien on observasi_persalinan.no_rkm_medis=pasien.no_rkm_medis where "+
                "observasi_persalinan.tanggal between ? and ? "+
                (TCari.getText().trim().equals("")?"":"and (observasi_persalinan.no_rawat like ? or observasi_persalinan.no_rkm_medis like ? or pasien.nm_pasien like ? or observasi_persalinan.nm_petugas like ?)")+
                " order by observasi_persalinan.tanggal");
            
            try{
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                if(!TCari.getText().trim().equals("")){
                    ps.setString(3, "%"+TCari.getText().trim()+"%");
                    ps.setString(4, "%"+TCari.getText().trim()+"%");
                    ps.setString(5, "%"+TCari.getText().trim()+"%");
                    ps.setString(6, "%"+TCari.getText().trim()+"%");
                }
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
//                         "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","Tanggal","kd_petugas","Nama Petugas","Pemeriksaan Oleh","Masuk Karena",
//                        "Ketuban","TD","Nadi","D.D.A","Udema","Fundus Uteri","Situs Anak","Bagian Paling Depan","Suhu","Pernafasan","Gamelli/Tunggal",
//                        "Gerak Anak","no_rkm_medis"
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("tanggal"),
                        rs.getString("kd_petugas"),rs.getString("nm_petugas"),rs.getString("pemeriksaan_oleh"),rs.getString("masuk_karena"),
                        rs.getString("ketuban"),rs.getString("td"),rs.getString("nadi"),rs.getString("dda"),rs.getString("udema"),rs.getString("pemeriksaan_paru"),rs.getString("pemeriksaan_jantung"),rs.getString("fundus_uteri"),
                        rs.getString("situs_anak"),rs.getString("posisi_punggung"),rs.getString("bagian_paling_depan"),rs.getString("suhu"),rs.getString("pernafasan"),rs.getString("gamellitunggal"),rs.getString("gerak_anak"),
                        rs.getString("tgl_lahir_bayi"),rs.getString("sex"),rs.getString("jns_kelahiran"),rs.getString("kondisi_bayi"),rs.getString("sebab_kematian"),rs.getString("sebab_kematian2"),
                        rs.getString("skala1"),rs.getString("nilai1"),rs.getString("skala2"),rs.getString("nilai2"),
                        rs.getString("skala3"),rs.getString("nilai3"),rs.getString("skala4"),rs.getString("nilai4"),
                        rs.getString("skala5"),rs.getString("nilai5"),rs.getString("total_nilai")
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

    public void emptTeks() {
//        TNoRw.setText("");
//        TNoRM.setText("");
//        TPasien.setText("");
//        TglLahir.setText("");
//        Jk.setText("");
//        KdPetugas.setText("");
//        NmPetugas.setText("");
//        KdDPJP.setText(""); 
//        NmDPJP.setText(""); 
//        TglAsuhan.setDate(new Date());
//        CaraDatang.setSelectedIndex(0);
//        Menggunakan.setSelectedIndex(0);
//        PengkajianOleh. setSelectedIndex(0);
//        AlasanMasuk.setText("");
//        HubunganDenganPasien.setText("");
//        PenyakitDiderita.setSelectedIndex(0);
//        for (int i = 0; i  <  panelisi1.getComponentCount(); i++) {
//            if (panelisi1.getComponent(i).getClass().toString().contains("CekBox")) {
//                ((CekBox) panelisi1.getComponent(i)).setSelected(false);
//            }
//        } 
//        Gamelli.setSelectedIndex(0); 
//        Ketergantungan.setSelectedIndex(0);
//        InputKetergantungan.setText("");
//        KetergantunganSejak.setText("");
//        ObatObatan.setSelectedIndex(0);
//        InputObat.setText("");
//        Makanan.setSelectedIndex(0);
//        InputMakanan.setText("");
//        Debu.setSelectedIndex(0);
//        AlergiLainnya.setText("");
//        Menarche.setText("");
//        Menstruasi.setSelectedIndex(0);
//        HariMens.setText( "");
//        SakitSaatMens.setSelectedIndex( 0);
//        Menikahke.setText("");
//        LamanyaMenikah.setText(""); 
//        NmKontrasepsi.setText("");
//        KontrasepsiLamanya.setText("");
//        InputG.setText("");
//        InputP.setText("");
//        InputA.setText("");
//        HPHT.setDate(new Date());
//        HPL.setText("");
//        UmurKehamilan.setText("");
//        Keluhan.setText("");
//        TinggiFundus.setText("");
//        LetakPunggungJanin.setSelectedIndex(0);
//        PresentasiJanin.setSelectedIndex(0);
//        BeratJanin.setText("");
//        Penurunan.setSelectedIndex(0);
//        Aukultasi.setText("");
//        FrekAukultasi.setSelectedIndex(0);
//        PemeriksaanDalam.setText("");
//        TD.setText("");
//        Nadi.setText("");
//        RR.setText("");
//        Suhu.setText("");
//        TB.setText("");
//        BB.setText("");
//        Keadaan1.setSelectedIndex(0);
//        GCS.setText("");
//        Kesadaran.setSelectedIndex(0);
//        InputPenurunanKesadaran.setText("");
//        Kepala.setSelectedIndex(0);
//        Mata.setSelectedIndex(0);
//        Hidung.setSelectedIndex(0);
//        GigiMulut.setSelectedIndex(0);
//        Tenggorokan.setSelectedIndex(0);
//        Telinga.setSelectedIndex(0);
//        Ekstremitas.setSelectedIndex(0);
//        Leher.setSelectedIndex(0);
//        Thoraks.setSelectedIndex(0);
//        Jantung.setSelectedIndex(0);
//        Paru.setSelectedIndex(0);
//        Abdomen.setSelectedIndex(0);
//        Nyeri.setSelectedIndex(0);
//        SkorNyeri.setSelectedIndex(0);
//        KategoriNyeri.setSelectedIndex(0);
//        PengaruhNyeri.setSelectedIndex(0);
//        HB.setText("");
//        HasilUSG.setText("");
//        for(int i=0;i<panelisi2.getComponentCount();i++){
//            if(panelisi2.getComponent(i).getClass().toString().contains("CekBox")){
//                ((CekBox) panelisi2.getComponent(i)).setSelected(false);
//            }
//        }
//        ResEmosi.setSelectedIndex(0);
//        SupportSuami.setSelectedIndex(0);
//        for(int i =0;i<panelisi3.getComponentCount();i++){
//            if(panelisi3.getComponent(i).getClass().toString().contains("CekBox")){
//                ((CekBox) panelisi3.getComponent(i)).setSelected(false);
//            }
//        }
//        DiagnosaKebidanan.setText("");
//         TNoRw.setText("");
//         TNoRM.setText("");
//         TPasien.setText("");
         TglAsuhan.setDate(new Date());
         KdPetugas.setText("");
         NmPetugas.setText("");
         PemeriksaanOleh.setText("");
         MasukKarena.setText("");
         Ketuban.setSelectedIndex(0);
         TD.setText("");
         Nadi.setText("");
         DDA.setText("");
         Udema.setSelected(false);
         ParuParu.setText("");
         Jantung.setText("");
         FundusUteri.setText("");
         SitusAnak.setText("");
         BagianDepan.setText("");
         Suhu.setText("");
         Pernafasan.setText("");
         Gamelli.setSelectedIndex(0);
         GerakAnak.setText("");
         SkalaResiko1.setSelectedIndex(0);
         NilaiResiko1.setText("0");
         SkalaResiko2.setSelectedIndex(0);
         NilaiResiko2.setText("0");
         SkalaResiko3.setSelectedIndex(0);
         NilaiResiko3.setText("0");
         SkalaResiko4.setSelectedIndex(0);
         NilaiResiko4.setText("0");
         SkalaResiko5.setSelectedIndex(0);
         NilaiResiko5.setText("0");
         NilaiResikoTotal.setText("0");
         PosisiPunggung.setSelectedIndex(0);
         SebabKematian.setText("");
    } 

    private void getData() {
//        if(tbObat.getSelectedRow()!= -1){
//            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
//            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
//            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()); 
//            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()); 
//            Jk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
//            TglAsuhan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
//            KdPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
//            NmPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
//            KdDPJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()); 
//            NmDPJP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString()); 
//            CaraDatang.setSelectedItem(Sequel.cariIsi("select cara_datang from observasi_persalinan where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
////            Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='"+norwt+"'", DTPCari1);
//            CaraDatangLainnya.setText(Sequel.cariIsi("select observasi_persalinan.cara_datang_lainnya from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            Menggunakan.setSelectedItem(Sequel.cariIsi("select menggunakan from observasi_persalinan where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            MenggunakanLainnya.setText(Sequel.cariIsi("select observasi_persalinan.menggunakan_lainnya from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            CaraMasuk.setSelectedItem(Sequel.cariIsi("select asal from observasi_persalinan where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            DariLainnya.setText(Sequel.cariIsi("select observasi_persalinan.asal_lainnya from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            PengkajianOleh.setSelectedItem(Sequel.cariIsi("select pengkajian_dari from observasi_persalinan where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            HubunganDenganPasien.setText(Sequel.cariIsi("select observasi_persalinan.hubungan_dengan_pasien from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            AlasanMasuk.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
//            // riwayat kesehatan
//            PenyakitDiderita.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
//            String riwayat_penyakit = tbObat.getValueAt(tbObat.getSelectedRow(),16).toString();
//            String[] split_riwayat_penyakit = riwayat_penyakit.split(",");
//            for (String split_riwayat_penyakit1 : split_riwayat_penyakit) {
//                for (int j = 0; j<panelisi1.getComponentCount(); j++) {
//                    if (panelisi1.getComponent(j).getClass().toString().contains("CekBox")) {
//                        if (((CekBox) panelisi1.getComponent(j)).getText().equals(split_riwayat_penyakit1)) {
//                            ((CekBox) panelisi1.getComponent(j)).setSelected(true);
//                        }
//                    }
//                }
//            }
//            Gamelli.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
//            Ketergantungan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
//            InputKetergantungan.setText(Sequel.cariIsi("select observasi_persalinan.ketergantungan_dengan from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            KetergantunganSejak.setText(Sequel.cariIsi("select observasi_persalinan.sejak from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
////            ObatObatan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
//            ObatObatan.setSelectedItem(Sequel.cariIsi("select observasi_persalinan.obat_obatan from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            InputObat.setText(Sequel.cariIsi("select observasi_persalinan.nama_obatan from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
////            Makanan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
//            Makanan.setSelectedItem(Sequel.cariIsi("select observasi_persalinan.makanan from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            InputMakanan.setText(Sequel.cariIsi("select observasi_persalinan.nama_makanan from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
////            Debu.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
//            Debu.setSelectedItem(Sequel.cariIsi("select observasi_persalinan.debu from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            InputDebu.setText(Sequel.cariIsi("select observasi_persalinan.nama_debu from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
////            AlergiLainnya.setText(Sequel.cariIsi("select observasi_persalinan.alergi_lainnya from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            AlergiLainnya.setText(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
//            Menarche.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
//            Menstruasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
//            HariMens.setText(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
//            SakitSaatMens.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
//            Menikahke.setText(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
//            LamanyaMenikah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
//            NmKontrasepsi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
//            KontrasepsiLamanya.setText(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
//            InputG.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
//            InputP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
//            InputA.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
////            HPHT.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),34));
//            // conversi format tanggal
//            String tanggalAwal=tbObat.getValueAt(tbObat.getSelectedRow(),34).toString();
//            SimpleDateFormat sdfAwal = new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat sdfBaru = new SimpleDateFormat("dd-MM-yyyy");
//            try{
//                Date date = sdfAwal.parse(tanggalAwal);
//                String tanggalBaru = sdfBaru.format(date);
//                HPHT.setSelectedItem(tanggalBaru);
//            }catch(ParseException e){
//                e.printStackTrace();
//            }
////            HariPerkiraanLahir.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),33));
//            HPL.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
//            UmurKehamilan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
//            Keluhan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
//            TinggiFundus.setText(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
//            LetakPunggungJanin.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),39));
//            PresentasiJanin.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),40));
//            BeratJanin.setText(tbObat.getValueAt(tbObat.getSelectedRow(),41).toString());
//            Penurunan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),42));
//            Aukultasi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),43).toString());
//            FrekAukultasi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),44));
//            PemeriksaanDalam.setText(tbObat.getValueAt(tbObat.getSelectedRow(),45).toString());
//            // pemeriksaan fisik
//            TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),46).toString());
//            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),47).toString());
//            RR.setText(tbObat.getValueAt(tbObat.getSelectedRow(),48).toString());
//            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),49).toString());
//            TB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),50).toString());
//            BB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),51).toString());
//            Keadaan1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),52));
//            GCS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),53).toString());
////            Kesadaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),54));
//            Kesadaran.setSelectedItem(Sequel.cariIsi("select observasi_persalinan.kesadaran from observasi_persalinan where observasi_persalinan.no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            // Sdisini input penurunan kesadaran
//            InputPenurunanKesadaran.setText(Sequel.cariIsi("select input_penurunan_kesadaran from observasi_persalinan where no_rawat=?",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()));
//            Kepala.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),55));
//            Mata.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),56));
//            Hidung.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),57));
//            GigiMulut.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),58));
//            Tenggorokan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),59));
//            Telinga.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),60));
//            Ekstremitas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),61));
//            Leher.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),62));
//            Thoraks.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),63));
//            Jantung.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),64));
//            Paru.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),65));
//            Abdomen.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),66));
//            Anus.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),67));
//            // penilaian tingkat nyeri
//            Nyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),68));
//            SkorNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),69));
//            KategoriNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),70));
//            PengaruhNyeri.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),71));
//            // pemeriksaan penunjang terakhir
//            HB.setText(tbObat.getValueAt(tbObat.getSelectedRow(),72).toString());
//            HasilUSG.setText(tbObat.getValueAt(tbObat.getSelectedRow(),73).toString());
//            // status mental
//            String status_mental = tbObat.getValueAt(tbObat.getSelectedRow(),74).toString();
//            String[] status_mental_split = status_mental.split(",");
//            for(String status_mental_split1 : status_mental_split){
//                for(int j=0;j<panelisi2.getComponentCount();j++){
//                    if(panelisi2.getComponent(j).getClass().toString().contains("CekBox")){
//                        if(((CekBox)panelisi2.getComponent(j)).getText().equals(status_mental_split1)){
//                            ((CekBox)panelisi2.getComponent(j)).setSelected(true);
//                        }
//                    }
//                }
//            }
//            ResEmosi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),75).toString());
//            SupportSuami.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),76).toString());
//            // diagnosa kebidanan
//            String masalah_kebidanan = tbObat.getValueAt(tbObat.getSelectedRow(),77).toString();
//            String[] masalah_kebidanan_split = masalah_kebidanan.split(",");
//            for(String masalah_kebidanan_split1 : masalah_kebidanan_split){
//                for(int j=0;j<panelisi3.getComponentCount();j++){
//                    if(panelisi3.getComponent(j).getClass().toString().contains("CekBox")){
//                        if(((CekBox)panelisi3.getComponent(j)).getText().equals(masalah_kebidanan_split1)){
//                            ((CekBox)panelisi3.getComponent(j)).setSelected(true);
//                        }
//                    }
//                }
//            }
//            DiagnosaKebidanan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),78).toString());
//            
//            tampilPersalinan();
//            Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());

//                "No.Rawat","No.RM","Nama Pasien","Tgl.Lahir","Tanggal","kd_petugas","Nama Petugas","Pemeriksaan Oleh","Masuk Karena",
//                "Ketuban","TD","Nadi","D.D.A","Udema","Fundus Uteri","Situs Anak","Bagian Paling Depan","Suhu","Pernafasan","Gamelli/Tunggal",
//                "Gerak Anak"
               
               TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
               TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
               TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
               TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
               Valid.SetTgl2(TglAsuhan,tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
               KdPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
               NmPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
               PemeriksaanOleh.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
               MasukKarena.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
               Ketuban.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
               TD.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
               Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
               DDA.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
               switch(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString()){
                   case "Ya":
                       Udema.setSelected(true);
                       break;
                   case "Tidak":
                       Udema.setSelected(false);
                       break;
               }
               ParuParu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
               Jantung.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
               FundusUteri.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
               SitusAnak.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
               PosisiPunggung.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
               BagianDepan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString());
               Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),20).toString());
               Pernafasan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
               Gamelli.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),22).toString());
               GerakAnak.setText(tbObat.getValueAt(tbObat.getSelectedRow(),23).toString());
               Valid.SetTgl2(TglLahirBayi,tbObat.getValueAt(tbObat.getSelectedRow(),24).toString());
               Sex.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),25).toString());
               JenisKelahiran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),26).toString());
               KondisiBayi.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),27).toString());
               WaktuKematian.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),28).toString());
               SebabKematian.setText(tbObat.getValueAt(tbObat.getSelectedRow(),29).toString());
               SkalaResiko1.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),30).toString());
               NilaiResiko1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString());
               SkalaResiko2.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),32).toString());
               NilaiResiko2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),33).toString());
               SkalaResiko3.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),34).toString());
               NilaiResiko3.setText(tbObat.getValueAt(tbObat.getSelectedRow(),35).toString());
               SkalaResiko4.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),36).toString());
               NilaiResiko4.setText(tbObat.getValueAt(tbObat.getSelectedRow(),37).toString());
               SkalaResiko5.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),38).toString());
               NilaiResiko5.setText(tbObat.getValueAt(tbObat.getSelectedRow(),39).toString());
               NilaiResikoTotal.setText(tbObat.getValueAt(tbObat.getSelectedRow(),40).toString());
               
               tampilPersalinan();
               
//        }
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
        BtnSimpan.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan());
        BtnHapus.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ranapkebidanan()); 
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
//            DiagnosaKebidanan1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),78).toString());
            // get detail inputan kala
            if(tbRiwayatKehamilan1.getSelectedRow()!=-1){
                DiagnosaKebidanan1.setText(Sequel.cariIsi("select observasi_persalinan_lainnya.inputan_kala from observasi_persalinan_lainnya where observasi_persalinan_lainnya.id_observasi=?",tbRiwayatKehamilan1.getValueAt(tbRiwayatKehamilan1.getSelectedRow(),5).toString()));
                DiagnosaKebidanan2.setText(Sequel.cariIsi("select observasi_persalinan_lainnya.terapi from observasi_persalinan_lainnya where observasi_persalinan_lainnya.id_observasi=?",tbRiwayatKehamilan1.getValueAt(tbRiwayatKehamilan1.getSelectedRow(),5).toString()));
//                System.out.println(Sequel.cariIsi("select inputan_kala from observasi_persalinan_lainnya where kala=?",tbRiwayatKehamilan1.getValueAt(tbRiwayatKehamilan1.getSelectedRow(),2).toString()));
            }
            
            Valid.tabelKosong(tabModeRiwayatKehamilan2);
            try {
                ps=koneksi.prepareStatement("select * from observasi_persalinan_lainnya where no_rkm_medis=? order by kala");
                try {
                    ps.setString(1,TNoRM1.getText());
                    rs=ps.executeQuery();
                    i=1;
                    while(rs.next()){
                        tabModeRiwayatKehamilan2.addRow(new String[]{
                            i+"",rs.getString("tanggal"),rs.getString("kala"),rs.getString("inputan_kala"),rs.getString("terapi"),rs.getString("id_observasi"),rs.getString("no_rkm_medis")
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
        TglAsuhan1.setDate(new Date());
        Kala.setSelectedIndex(0);
        InputKala.setText("");
        PimpinanTerapi.setText("");
    }

    private void tampilPersalinan() {
        Valid.tabelKosong(tabModeRiwayatKehamilan);
        try {
            ps=koneksi.prepareStatement("select * from observasi_persalinan_lainnya where observasi_persalinan_lainnya.no_rkm_medis=? order by observasi_persalinan_lainnya.kala");
            try {
                ps.setString(1,TNoRM.getText());
                rs=ps.executeQuery();
                i=1;
                while(rs.next()){
                    tabModeRiwayatKehamilan.addRow(new String[]{
//                         "No","Tanggal & Jam","Kala","","Pimpinan & Terapi","id_kala","no_rkm_medis"
                        i+"",rs.getString("tanggal"),rs.getString("kala"),rs.getString("inputan_kala"),rs.getString("terapi"),rs.getString("id_observasi"),rs.getString("no_rkm_medis")
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
        if(Sequel.queryu2tf("delete from observasi_persalinan where no_rawat=?",1,new String[]{
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
        String Udemaa = "";
        if(Udema.isSelected()==true){
            Udemaa = "Ya";
        }else{
            Udemaa = "Tidak";
        }
        if(Sequel.mengedittf("observasi_persalinan","no_rawat=?","no_rawat=?,tanggal=?,kd_petugas=?,nm_petugas=?,pemeriksaan_oleh=?,masuk_karena=?, "+
                "ketuban=?,td=?,nadi=?,dda=?,udema=?,fundus_uteri=?,situs_anak=?,bagian_paling_depan=?,suhu=?,pernafasan=?, "+
                "gamellitunggal=?,gerak_anak=?,no_rkm_medis=?,pemeriksaan_paru=?,pemeriksaan_jantung=?, "+
                "tgl_lahir_bayi=?,sex=?,jns_kelahiran=?,kondisi_bayi=?,sebab_kematian=?, "+
                "skala1=?,nilai1=?,skala2=?,nilai2=?,skala3=?,nilai3=?, "+
                "skala4=?,nilai4=?,skala5=?,nilai5=?,total_nilai=?,posisi_punggung=?,sebab_kematian2=?"
                ,40,new String[]{
            // get value dari field yang diisikan
            TNoRw.getText(),Valid.SetTgl(TglAsuhan.getSelectedItem()+"")+" "+TglAsuhan.getSelectedItem().toString().substring(11,19),KdPetugas.getText(),NmPetugas.getText(),PemeriksaanOleh.getText(),MasukKarena.getText(),
            Ketuban.getSelectedItem().toString(),TD.getText(),Nadi.getText(),DDA.getText(),Udemaa,FundusUteri.getText(),SitusAnak.getText(),BagianDepan.getText(),
            Suhu.getText(),Pernafasan.getText(),Gamelli.getSelectedItem().toString(),GerakAnak.getText(),TNoRM.getText(),ParuParu.getText(),Jantung.getText(),
            Valid.SetTgl(TglLahirBayi.getSelectedItem()+"")+" "+TglLahirBayi.getSelectedItem().toString().substring(11,19),
            Sex.getSelectedItem().toString(),JenisKelahiran.getSelectedItem().toString(),KondisiBayi.getSelectedItem().toString(),WaktuKematian.getSelectedItem().toString(),SkalaResiko1.getSelectedItem().toString(),
            NilaiResiko1.getText(),SkalaResiko2.getSelectedItem().toString(),NilaiResiko2.getText(),
            SkalaResiko3.getSelectedItem().toString(),NilaiResiko3.getText(),SkalaResiko4.getSelectedItem().toString(),NilaiResiko4.getText(),
            SkalaResiko5.getSelectedItem().toString(),NilaiResiko5.getText(),NilaiResikoTotal.getText(),PosisiPunggung.getSelectedItem().toString(),SebabKematian.getText(),
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tampil();
            emptTeks();
        }
          
    }
    
//    private String getSelected(){
//        String data="";
//        // jika componen cekbox dalam panelisi3 dicentang maka tambahkan ke variabel data menggunakan tanda ","
//        // buat menggunakan perulangan untuk mengecek componen cekbox yang dicentang
//        for(int i=0;i<panelisi3.getComponentCount();i++){
//            if(panelisi3.getComponent(i).getClass().getSimpleName().equals("CekBox")){
//                CekBox cb = (CekBox)panelisi3.getComponent(i);
//                if(cb.isSelected()==true){
//                    data=data+cb.getText()+",";
//                }
//            }
//        }
//        return data;
//    }
    
//    private String getSelectedPenyakit(){
//        String data="";
//        for(int i=0;i<panelisi1.getComponentCount();i++){
//            if(panelisi1.getComponent(i).getClass().getSimpleName().equals("CekBox")){
//                CekBox cb = (CekBox)panelisi1.getComponent(i);
//                if(cb.isSelected()==true){
//                    data += cb.getText()+",";
//                }
//            }
//        }
//        return data;
//    }
    
//    private String getSelectedStatusMental(){
//        String data="";
//        for(int i=0;i<panelisi2.getComponentCount();i++){
//            CekBox cb = (CekBox)panelisi2.getComponent(i);
//            if(cb.isSelected()==true){
//                data+=cb.getText()+",";
//            }
//        }
//        return data;
//    }
    
//    private void isMati(){
//        if(KondisiBayi.getSelectedIndex()==1){
//            label22.setVisible(true);
//            WaktuKematian.setVisible(true);
//        }else{
//            label22.setVisible(false);
//            WaktuKematian.setVisible(false);
//            WaktuKematian.setSelectedIndex(0);
//        }
//    }
    
    private void isTotalResikoJatuh(){
        try {
            NilaiResikoTotal.setText((Integer.parseInt(NilaiResiko1.getText())+Integer.parseInt(NilaiResiko2.getText())+Integer.parseInt(NilaiResiko3.getText())+Integer.parseInt(NilaiResiko4.getText())+Integer.parseInt(NilaiResiko5.getText()))+"");
        } catch (Exception e) {
            NilaiResikoTotal.setText("0");
        }
    }
   
}
