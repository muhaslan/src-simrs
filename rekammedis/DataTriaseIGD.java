/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPegawai;


/**
 *
 * @author perpustakaan
 */
public final class DataTriaseIGD extends javax.swing.JDialog {
    private DefaultTableModel tabMode,tabModePemeriksaan,tabModeSkala1,tabModeSkala2,tabModeSkala3,tabModeSkala4,tabModeSkala5,tabModePemeriksaan2;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps2,ps3,ps4;
    private ResultSet rs,rs2,rs3,rs4;
    private int i=0,jml=0,index=0,jmlskala1=0,jmlskala2=0,jmlskala3=0,jmlskala4=0,jmlskala5=0;
    private MasterTriaseMacamKasus kasus=new MasterTriaseMacamKasus(null,false);
    private boolean[] pilih; 
    private String[] kode,pengkajian;
    private DlgCariPegawai pegawai=new DlgCariPegawai(null,false);
    private String keputusan="",pilihan="",datatriase="",finger="",kodepetugas="";
    private StringBuilder htmlContent;
    private boolean sukses=true;
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public DataTriaseIGD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        tabMode=new DefaultTableModel(null,new Object[]{
                "No.Rawat","No.RM","Nama Pasien","Tgl.Kunjungan","Cara Masuk","Transportasi",
                "Alasan Kedatangan","Keterangan","Kode Kasus","Macam Kasus"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbTriase.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbTriase.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTriase.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbTriase.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(160);
            }else if(i==3){
                column.setPreferredWidth(120);
            }else if(i==4){
                column.setPreferredWidth(80);
            }else if(i==5){
                column.setPreferredWidth(80);
            }else if(i==6){
                column.setPreferredWidth(110);
            }else if(i==7){
                column.setPreferredWidth(180);
            }else if(i==8){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==9){
                column.setPreferredWidth(300);
            }
        }
        tbTriase.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModePemeriksaan=new DefaultTableModel(null,new Object[]{
                "Kode Pemeriksaan","Pemeriksaan"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
       
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
        
        kasus.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    kasus.dispose();
                }                
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        LoadHTML2.setEditable(true);
        LoadHTML2.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
        LoadHTML2.setDocument(doc);
        
        ChkAccor.setSelected(false);
        isMenu();
        
//        isCttn();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        LoadHTML2 = new widget.editorpane();
        internalFrame1 = new widget.InternalFrame();
        TabPilihan = new javax.swing.JTabbedPane();
        internalFrame4 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbTriase = new widget.Table();
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
        ScrollHTML = new widget.ScrollPane();
        LoadHTML = new widget.editorpane();
        panelGlass8 = new widget.panelisi();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        LoadHTML2.setBorder(null);
        LoadHTML2.setName("LoadHTML2"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Triase IGD ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabPilihan.setBackground(new java.awt.Color(255, 255, 254));
        TabPilihan.setForeground(new java.awt.Color(50, 50, 50));
        TabPilihan.setName("TabPilihan"); // NOI18N
        TabPilihan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPilihanMouseClicked(evt);
            }
        });

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbTriase.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTriase.setName("tbTriase"); // NOI18N
        tbTriase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTriaseMouseClicked(evt);
            }
        });
        tbTriase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTriaseKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbTriaseKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbTriase);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Triase :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-08-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-08-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
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
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        internalFrame4.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

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

        ScrollHTML.setBorder(null);
        ScrollHTML.setName("ScrollHTML"); // NOI18N
        ScrollHTML.setOpaque(true);
        ScrollHTML.setPreferredSize(new java.awt.Dimension(470, 16));

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N
        ScrollHTML.setViewportView(LoadHTML);

        PanelAccor.add(ScrollHTML, java.awt.BorderLayout.CENTER);

        internalFrame4.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabPilihan.addTab("Data Triase", internalFrame4);

        internalFrame1.add(TabPilihan, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            BtnKeluarActionPerformed(null);
//        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){ 
            keputusan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih laporan..!","Laporan Triase IGD",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Laporan 1","Laporan 2"},"Laporan 1");
            switch (keputusan) {
                case "Laporan 1":
                    Map<String, Object> param = new HashMap<>(); 
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());   
                    param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                    Valid.MyReportqry("rptDataTriaseIGD.jasper","report","::[ Data Triase IGD ]::",
                        "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,data_triase_igd.tgl_kunjungan,"+
                        "data_triase_igd.cara_masuk,data_triase_igd.alat_transportasi,data_triase_igd.alasan_kedatangan,"+
                        "data_triase_igd.keterangan_kedatangan,data_triase_igd.kode_kasus,master_triase_macam_kasus.macam_kasus "+
                        "from reg_periksa inner join pasien inner join data_triase_igd inner join master_triase_macam_kasus "+
                        "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                        "and data_triase_igd.kode_kasus=master_triase_macam_kasus.kode_kasus "+
                        "where data_triase_igd.tgl_kunjungan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and reg_periksa.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                        "data_triase_igd.tgl_kunjungan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and pasien.no_rkm_medis like '%"+TCari.getText().trim()+"%' or "+
                        "data_triase_igd.tgl_kunjungan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                        "data_triase_igd.tgl_kunjungan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and data_triase_igd.cara_masuk like '%"+TCari.getText().trim()+"%' or "+
                        "data_triase_igd.tgl_kunjungan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and data_triase_igd.alat_transportasi like '%"+TCari.getText().trim()+"%' or "+
                        "data_triase_igd.tgl_kunjungan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and data_triase_igd.alasan_kedatangan like '%"+TCari.getText().trim()+"%' or "+
                        "data_triase_igd.tgl_kunjungan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and data_triase_igd.keterangan_kedatangan like '%"+TCari.getText().trim()+"%' or "+
                        "data_triase_igd.tgl_kunjungan between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and master_triase_macam_kasus.macam_kasus like '%"+TCari.getText().trim()+"%' order by data_triase_igd.tgl_kunjungan",param);
                    break;
                case "Laporan 2":
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    try {
                        htmlContent = new StringBuilder();
                        htmlContent.append(                             
                            "<tr class='isi'>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center' width='12%'><b>Pasien</b></td>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center' width='12%'><b>Kunjungan</b></td>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center' width='18%'><b>Kedatangan</b></td>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center' width='13%'><b>Keluhan/Anamnesis Singkat</b></td>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center' width='10%'><b>Tanda Vital</b></td>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center' width='21%'><b>Pemeriksaan</b></td>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center' width='14%'><b>Keputusan</b></td>"+
                            "</tr>"
                        );
                        
                        for(i=0;i<tabMode.getRowCount();i++){  
                            htmlContent.append(                             
                                "<tr class='isi'>"+
                                    "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                        "<table width='100%' border='0' cellpadding='0' cellspacing='0'align='center'>"+
                                            "<tr class='isi2'>"+
                                                "<td width='34%' valign='top'>No.Rawat</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+tbTriase.getValueAt(i,0).toString()+"</td>"+
                                            "</tr>"+
                                            "<tr class='isi2'>"+
                                                "<td width='34%' valign='top'>No.R.M.</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+tbTriase.getValueAt(i,1).toString()+"</td>"+
                                            "</tr>"+
                                            "<tr class='isi2'>"+
                                                "<td width='34%' valign='top'>Nama Pasien</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+tbTriase.getValueAt(i,2).toString()+"</td>"+
                                            "</tr>"+
                                        "</table>"+
                                    "</td>"+
                                    "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                        "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"+
                                            "<tr class='isi2'>"+
                                                "<td width='34%' valign='top'>Tgl.Kunjungan</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+tbTriase.getValueAt(i,3).toString()+"</td>"+
                                            "</tr>"+
                                            "<tr class='isi2'>"+
                                                "<td width='34%' valign='top'>Cara Masuk</td><td valign='top'>:</td><td width='65%' valign='top'>"+tbTriase.getValueAt(i,4).toString()+"</td>"+
                                            "</tr>"+
                                            "<tr class='isi2'>"+
                                                "<td width='34%' valign='top'>Transportasi</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+tbTriase.getValueAt(i,5).toString()+"</td>"+
                                            "</tr>"+
                                        "</table>"+
                                    "</td>"+
                                    "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                        "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"+
                                            "<tr class='isi2'>"+
                                                "<td width='31%' valign='top'>Alasan Kedatangan</td><td valign='top'>:&nbsp;</td><td width='68%' valign='top'>"+tbTriase.getValueAt(i,6).toString()+"</td>"+
                                            "</tr>"+
                                            "<tr class='isi2'>"+
                                                "<td width='31%' valign='top'>Keterangan</td><td valign='top'>:&nbsp;</td><td width='68%' valign='top'>"+tbTriase.getValueAt(i,7).toString()+"</td>"+
                                            "</tr>"+
                                            "<tr class='isi2'>"+
                                                "<td width='31%' valign='top'>Macam Kasus</td><td valign='top'>:&nbsp;</td><td width='68%' valign='top'>"+tbTriase.getValueAt(i,9).toString()+"</td>"+
                                            "</tr>"+
                                        "</table>"+
                                    "</td>"
                            );
                            
                            ps=koneksi.prepareStatement(
                                "select data_triase_igdprimer.keluhan_utama,data_triase_igdprimer.kebutuhan_khusus,data_triase_igdprimer.catatan,"+
                                "data_triase_igdprimer.plan,data_triase_igdprimer.tanggaltriase,data_triase_igdprimer.nik,data_triase_igd.tekanan_darah,"+
                                "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                                "data_triase_igd.no_rawat from data_triase_igdprimer inner join data_triase_igd on data_triase_igd.no_rawat="+
                                "data_triase_igdprimer.no_rawat where data_triase_igd.no_rawat=?");
                            try {
                                ps.setString(1,tbTriase.getValueAt(i,0).toString());
                                rs=ps.executeQuery();
                                if(rs.next()){
                                    htmlContent.append(
                                        "<td valign='top'> Keluhan Utama : "+rs.getString("keluhan_utama")+"<br>Kebutuhan Khusus : "+rs.getString("kebutuhan_khusus")+"</td>"+
                                        "<td valign='top'> Suhu (C) : "+rs.getString("suhu")+", Respirasi(/menit) : "+rs.getString("pernapasan")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Nyeri : "+rs.getString("nyeri")+"</td>"
                                    );
                                    ps2=koneksi.prepareStatement(
                                        "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                        "from master_triase_pemeriksaan inner join master_triase_skala1 inner join data_triase_igddetail_skala1 "+
                                        "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala1.kode_pemeriksaan and "+
                                        "master_triase_skala1.kode_skala1=data_triase_igddetail_skala1.kode_skala1 where data_triase_igddetail_skala1.no_rawat=? "+
                                        "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                    try {
                                        ps2.setString(1,rs.getString("no_rawat"));
                                        rs2=ps2.executeQuery();
                                        if(rs2.next()){
                                            htmlContent.append(
                                                "<td valign='top'>"+
                                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"
                                            );
                                            rs2.beforeFirst();
                                            while(rs2.next()){
                                                htmlContent.append(
                                                    "<tr class='isi5'>"+
                                                        "<td width='40%' valign='top'>"+rs2.getString("nama_pemeriksaan")+"</td>"+
                                                        "<td valign='top'>:&nbsp;</td>"+
                                                        "<td width='59%' valign='top'>"+
                                                            "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"
                                                    );
                                                ps3=koneksi.prepareStatement(
                                                    "select master_triase_skala1.pengkajian_skala1 from master_triase_skala1 inner join data_triase_igddetail_skala1 "+
                                                    "on master_triase_skala1.kode_skala1=data_triase_igddetail_skala1.kode_skala1 where "+
                                                    "master_triase_skala1.kode_pemeriksaan=? and data_triase_igddetail_skala1.no_rawat=? "+
                                                    "order by data_triase_igddetail_skala1.kode_skala1");
                                                try {
                                                    ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                                    ps3.setString(2,rs.getString("no_rawat"));
                                                    rs3=ps3.executeQuery();
                                                    while(rs3.next()){
                                                        htmlContent.append(                             
                                                            "<tr class='isi5'>"+
                                                                "<td border='0' valign='middle' width='100%'>"+rs3.getString("pengkajian_skala1")+"</td>"+
                                                            "</tr>"
                                                        );
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notif : "+e);
                                                } finally{
                                                    if(rs3!=null){
                                                        rs3.close();
                                                    }
                                                    if(ps3!=null){
                                                        ps3.close();
                                                    }
                                                }
                                                htmlContent.append(
                                                            "</table>"+
                                                        "</td>"+
                                                    "</tr>"
                                                );
                                            }
                                            htmlContent.append(
                                                    "</table>"+
                                                "</td>"
                                            );
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Notif : "+e);
                                    } finally{
                                        if(rs2!=null){
                                            rs2.close();
                                        }
                                        if(ps2!=null){
                                            ps2.close();
                                        }
                                    }
                                    
                                    ps2=koneksi.prepareStatement(
                                        "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                        "from master_triase_pemeriksaan inner join master_triase_skala2 inner join data_triase_igddetail_skala2 "+
                                        "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala2.kode_pemeriksaan and "+
                                        "master_triase_skala2.kode_skala2=data_triase_igddetail_skala2.kode_skala2 where data_triase_igddetail_skala2.no_rawat=? "+
                                        "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                    try {
                                        ps2.setString(1,rs.getString("no_rawat"));
                                        rs2=ps2.executeQuery();
                                        if(rs2.next()){
                                            htmlContent.append(
                                                "<td valign='top'>"+
                                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"
                                            );
                                            rs2.beforeFirst();
                                            while(rs2.next()){
                                                htmlContent.append(
                                                    "<tr class='isi6'>"+
                                                        "<td width='40%' valign='top'>"+rs2.getString("nama_pemeriksaan")+"</td>"+
                                                        "<td valign='top'>:&nbsp;</td>"+
                                                        "<td width='59%' valign='top'>"+
                                                            "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"
                                                    );
                                                ps3=koneksi.prepareStatement(
                                                    "select master_triase_skala2.pengkajian_skala2 from master_triase_skala2 inner join data_triase_igddetail_skala2 "+
                                                    "on master_triase_skala2.kode_skala2=data_triase_igddetail_skala2.kode_skala2 where "+
                                                    "master_triase_skala2.kode_pemeriksaan=? and data_triase_igddetail_skala2.no_rawat=? "+
                                                    "order by data_triase_igddetail_skala2.kode_skala2");
                                                try {
                                                    ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                                    ps3.setString(2,rs.getString("no_rawat"));
                                                    rs3=ps3.executeQuery();
                                                    while(rs3.next()){
                                                        htmlContent.append(                             
                                                            "<tr class='isi6'>"+
                                                                "<td border='0' valign='middle' width='100%'>"+rs3.getString("pengkajian_skala2")+"</td>"+
                                                            "</tr>"
                                                        );
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notif : "+e);
                                                } finally{
                                                    if(rs3!=null){
                                                        rs3.close();
                                                    }
                                                    if(ps3!=null){
                                                        ps3.close();
                                                    }
                                                }
                                                htmlContent.append(
                                                            "</table>"+
                                                        "</td>"+
                                                    "</tr>"
                                                );
                                            }
                                            htmlContent.append(
                                                    "</table>"+
                                                "</td>"
                                            );
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Notif : "+e);
                                    } finally{
                                        if(rs2!=null){
                                            rs2.close();
                                        }
                                        if(ps2!=null){
                                            ps2.close();
                                        }
                                    }
                                    htmlContent.append(
                                        "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                            "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"+
                                                "<tr class='isi2'>"+
                                                    "<td width='34%' valign='top'>Keputusan</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>Zona Merah "+rs.getString("plan")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi2'>"+
                                                    "<td width='34%' valign='top'>Tanggal & Jam</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("tanggaltriase")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi2'>"+
                                                    "<td width='34%' valign='top'>Catatan</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("catatan")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi2'>"+
                                                    "<td width='34%' valign='top'>Dokter/Petugas</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("nik")+" "+Sequel.cariIsi("select pegawai.nama from pegawai where pegawai.nik=?",rs.getString("nik"))+"</td>"+
                                                "</tr>"+
                                            "</table>"+
                                        "</td>"
                                    );
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
                            
                            ps=koneksi.prepareStatement(
                                "select data_triase_igdsekunder.anamnesa_singkat,data_triase_igdsekunder.catatan,"+
                                "data_triase_igdsekunder.plan,data_triase_igdsekunder.tanggaltriase,data_triase_igdsekunder.nik,data_triase_igd.tekanan_darah,"+
                                "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                                "data_triase_igd.no_rawat from data_triase_igdsekunder inner join data_triase_igd on data_triase_igd.no_rawat="+
                                "data_triase_igdsekunder.no_rawat where data_triase_igd.no_rawat=?");
                            try {
                                ps.setString(1,tbTriase.getValueAt(i,0).toString());
                                rs=ps.executeQuery();
                                if(rs.next()){
                                    htmlContent.append(
                                        "<td valign='top'> Anamnesa Singkat : "+rs.getString("anamnesa_singkat")+"</td>"+
                                        "<td valign='top'> Suhu (C) : "+rs.getString("suhu")+", Respirasi(/menit) : "+rs.getString("pernapasan")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Nyeri : "+rs.getString("nyeri")+"</td>"
                                    );
                                    ps2=koneksi.prepareStatement(
                                        "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                        "from master_triase_pemeriksaan inner join master_triase_skala3 inner join data_triase_igddetail_skala3 "+
                                        "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala3.kode_pemeriksaan and "+
                                        "master_triase_skala3.kode_skala3=data_triase_igddetail_skala3.kode_skala3 where data_triase_igddetail_skala3.no_rawat=? "+
                                        "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                    try {
                                        ps2.setString(1,rs.getString("no_rawat"));
                                        rs2=ps2.executeQuery();
                                        if(rs2.next()){
                                            htmlContent.append(
                                                "<td valign='top'>"+
                                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"
                                            );
                                            rs2.beforeFirst();
                                            while(rs2.next()){
                                                htmlContent.append(
                                                    "<tr class='isi7'>"+
                                                        "<td width='40%' valign='top'>"+rs2.getString("nama_pemeriksaan")+"</td>"+
                                                        "<td valign='top'>:&nbsp;</td>"+
                                                        "<td width='59%' valign='top'>"+
                                                            "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"
                                                    );
                                                ps3=koneksi.prepareStatement(
                                                    "select master_triase_skala3.pengkajian_skala3 from master_triase_skala3 inner join data_triase_igddetail_skala3 "+
                                                    "on master_triase_skala3.kode_skala3=data_triase_igddetail_skala3.kode_skala3 where "+
                                                    "master_triase_skala3.kode_pemeriksaan=? and data_triase_igddetail_skala3.no_rawat=? "+
                                                    "order by data_triase_igddetail_skala3.kode_skala3");
                                                try {
                                                    ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                                    ps3.setString(2,rs.getString("no_rawat"));
                                                    rs3=ps3.executeQuery();
                                                    while(rs3.next()){
                                                        htmlContent.append(                             
                                                            "<tr class='isi7'>"+
                                                                "<td border='0' valign='middle' width='100%'>"+rs3.getString("pengkajian_skala3")+"</td>"+
                                                            "</tr>"
                                                        );
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notif : "+e);
                                                } finally{
                                                    if(rs3!=null){
                                                        rs3.close();
                                                    }
                                                    if(ps3!=null){
                                                        ps3.close();
                                                    }
                                                }
                                                htmlContent.append(
                                                            "</table>"+
                                                        "</td>"+
                                                    "</tr>"
                                                );
                                            }
                                            htmlContent.append(
                                                    "</table>"+
                                                "</td>"
                                            );
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Notif : "+e);
                                    } finally{
                                        if(rs2!=null){
                                            rs2.close();
                                        }
                                        if(ps2!=null){
                                            ps2.close();
                                        }
                                    }
                                    
                                    ps2=koneksi.prepareStatement(
                                        "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                        "from master_triase_pemeriksaan inner join master_triase_skala4 inner join data_triase_igddetail_skala4 "+
                                        "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala4.kode_pemeriksaan and "+
                                        "master_triase_skala4.kode_skala4=data_triase_igddetail_skala4.kode_skala4 where data_triase_igddetail_skala4.no_rawat=? "+
                                        "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                    try {
                                        ps2.setString(1,rs.getString("no_rawat"));
                                        rs2=ps2.executeQuery();
                                        if(rs2.next()){
                                            htmlContent.append(
                                                "<td valign='top'>"+
                                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"
                                            );
                                            rs2.beforeFirst();
                                            while(rs2.next()){
                                                htmlContent.append(
                                                    "<tr class='isi8'>"+
                                                        "<td width='40%' valign='top'>"+rs2.getString("nama_pemeriksaan")+"</td>"+
                                                        "<td valign='top'>:&nbsp;</td>"+
                                                        "<td width='59%' valign='top'>"+
                                                            "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"
                                                    );
                                                ps3=koneksi.prepareStatement(
                                                    "select master_triase_skala4.pengkajian_skala4 from master_triase_skala4 inner join data_triase_igddetail_skala4 "+
                                                    "on master_triase_skala4.kode_skala4=data_triase_igddetail_skala4.kode_skala4 where "+
                                                    "master_triase_skala4.kode_pemeriksaan=? and data_triase_igddetail_skala4.no_rawat=? "+
                                                    "order by data_triase_igddetail_skala4.kode_skala4");
                                                try {
                                                    ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                                    ps3.setString(2,rs.getString("no_rawat"));
                                                    rs3=ps3.executeQuery();
                                                    while(rs3.next()){
                                                        htmlContent.append(                             
                                                            "<tr class='isi8'>"+
                                                                "<td border='0' valign='middle' width='100%'>"+rs3.getString("pengkajian_skala4")+"</td>"+
                                                            "</tr>"
                                                        );
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notif : "+e);
                                                } finally{
                                                    if(rs3!=null){
                                                        rs3.close();
                                                    }
                                                    if(ps3!=null){
                                                        ps3.close();
                                                    }
                                                }
                                                htmlContent.append(
                                                            "</table>"+
                                                        "</td>"+
                                                    "</tr>"
                                                );
                                            }
                                            htmlContent.append(
                                                    "</table>"+
                                                "</td>"
                                            );
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Notif : "+e);
                                    } finally{
                                        if(rs2!=null){
                                            rs2.close();
                                        }
                                        if(ps2!=null){
                                            ps2.close();
                                        }
                                    }
                                    
                                    ps2=koneksi.prepareStatement(
                                        "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                        "from master_triase_pemeriksaan inner join master_triase_skala5 inner join data_triase_igddetail_skala5 "+
                                        "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala5.kode_pemeriksaan and "+
                                        "master_triase_skala5.kode_skala5=data_triase_igddetail_skala5.kode_skala5 where data_triase_igddetail_skala5.no_rawat=? "+
                                        "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                    try {
                                        ps2.setString(1,rs.getString("no_rawat"));
                                        rs2=ps2.executeQuery();
                                        if(rs2.next()){
                                            htmlContent.append(
                                                "<td valign='top'>"+
                                                    "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"
                                            );
                                            rs2.beforeFirst();
                                            while(rs2.next()){
                                                htmlContent.append(
                                                    "<tr class='isi9'>"+
                                                        "<td width='40%' valign='top'>"+rs2.getString("nama_pemeriksaan")+"</td>"+
                                                        "<td valign='top'>:&nbsp;</td>"+
                                                        "<td width='59%' valign='top'>"+
                                                            "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"
                                                    );
                                                ps3=koneksi.prepareStatement(
                                                    "select master_triase_skala5.pengkajian_skala5 from master_triase_skala5 inner join data_triase_igddetail_skala5 "+
                                                    "on master_triase_skala5.kode_skala5=data_triase_igddetail_skala5.kode_skala5 where "+
                                                    "master_triase_skala5.kode_pemeriksaan=? and data_triase_igddetail_skala5.no_rawat=? "+
                                                    "order by data_triase_igddetail_skala5.kode_skala5");
                                                try {
                                                    ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                                    ps3.setString(2,rs.getString("no_rawat"));
                                                    rs3=ps3.executeQuery();
                                                    while(rs3.next()){
                                                        htmlContent.append(                             
                                                            "<tr class='isi9'>"+
                                                                "<td border='0' valign='middle' width='100%'>"+rs3.getString("pengkajian_skala5")+"</td>"+
                                                            "</tr>"
                                                        );
                                                    }
                                                } catch (Exception e) {
                                                    System.out.println("Notif : "+e);
                                                } finally{
                                                    if(rs3!=null){
                                                        rs3.close();
                                                    }
                                                    if(ps3!=null){
                                                        ps3.close();
                                                    }
                                                }
                                                htmlContent.append(
                                                            "</table>"+
                                                        "</td>"+
                                                    "</tr>"
                                                );
                                            }
                                            htmlContent.append(
                                                    "</table>"+
                                                "</td>"
                                            );
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Notif : "+e);
                                    } finally{
                                        if(rs2!=null){
                                            rs2.close();
                                        }
                                        if(ps2!=null){
                                            ps2.close();
                                        }
                                    }
                                    htmlContent.append(
                                        "<td valign='top' cellpadding='0' cellspacing='0'>"+
                                            "<table width='100%' border='0' cellpadding='0' cellspacing='0' align='center'>"+
                                                "<tr class='isi2'>"+
                                                    "<td width='34%' valign='top'>Keputusan</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("plan")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi2'>"+
                                                    "<td width='34%' valign='top'>Tanggal & Jam</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("tanggaltriase")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi2'>"+
                                                    "<td width='34%' valign='top'>Catatan</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("catatan")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi2'>"+
                                                    "<td width='34%' valign='top'>Dokter/Petugas</td><td valign='top'>:&nbsp;</td><td width='65%' valign='top'>"+rs.getString("nik")+" "+Sequel.cariIsi("select pegawai.nama from pegawai where pegawai.nik=?",rs.getString("nik"))+"</td>"+
                                                "</tr>"+
                                            "</table>"+
                                        "</td>"
                                    );
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
                            
                            htmlContent.append(
                                "</tr>"
                            );
                        }
                        
                        LoadHTML2.setText(
                            "<html>"+
                              "<table width='1400px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                               htmlContent.toString()+
                              "</table>"+
                            "</html>"
                        );
                        
                        File g = new File("file2.css");            
                        BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                        bg.write(
                            ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                            ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                            ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                            ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                            ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                            ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                            ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                            ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                            ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                        );
                        bg.close();

                        File f = new File("DataTriaseIGD.html");            
                        BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                        bw.write(LoadHTML2.getText().replaceAll("<head>","<head>"+
                                    "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                                    "<table width='1400px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                        "<tr class='isi2'>"+
                                            "<td valign='top' align='center'>"+
                                                "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                                akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                                akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                                "<font size='2' face='Tahoma'>DATA TRIASE IGD<br><br></font>"+        
                                            "</td>"+
                                       "</tr>"+
                                    "</table>")
                        );
                        bw.close();                         
                        Desktop.getDesktop().browse(f.toURI());
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    }
                    this.setCursor(Cursor.getDefaultCursor());
                    TNoRM1.setText("");
                    TPasien1.setText("");
                    LoadHTML.setText("");
                    ChkAccor.setSelected(false);
                    isMenu();
                    break;
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            BtnPrintActionPerformed(null);
//        }else{
//            Valid.pindah(evt, BtnEdit, BtnKeluar);
//        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            TCari.setText("");
//            tampil();
//        }else{
//            Valid.pindah(evt, BtnCari, TPasien);
//        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbTriaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTriaseMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                ChkAccor.setSelected(true);
                isMenu();
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbTriaseMouseClicked

    private void tbTriaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriaseKeyPressed
//       if(tabMode.getRowCount()!=0){
//            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
//                TCari.setText("");
//                TCari.requestFocus();
//            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//                if(tbTriase.getSelectedRow()!= -1){
//                    try {
//                        Valid.tabelKosong(tabModeSkala1);
//                        Valid.tabelKosong(tabModeSkala2);
//                        Valid.tabelKosong(tabModeSkala3);
//                        Valid.tabelKosong(tabModeSkala4);
//                        Valid.tabelKosong(tabModeSkala5);
//                        TNoRw.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(),0).toString());
//                        TNoRM.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(),1).toString());
//                        TPasien.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(),2).toString());
//                        Valid.SetTgl2(TanggalKunjungan,tbTriase.getValueAt(tbTriase.getSelectedRow(),3).toString());
//                        CaraMasuk.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(),4).toString());
//                        Transportasi.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(),5).toString());
//                        AlasanKedatangan.setSelectedItem(tbTriase.getValueAt(tbTriase.getSelectedRow(),6).toString());
//                        KeteranganKedatangan.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(),7).toString());
//                        KdKasus.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(),8).toString());
//                        NmKasus.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(),9).toString());
//                        TabPilihan.setSelectedIndex(0);
//                        ps=koneksi.prepareStatement(
//                                "select data_triase_igdprimer.keluhan_utama,data_triase_igdprimer.kebutuhan_khusus,data_triase_igdprimer.catatan,"+
//                                "data_triase_igdprimer.plan,data_triase_igdprimer.tanggaltriase,data_triase_igdprimer.nik,data_triase_igd.tekanan_darah,"+
//                                "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
//                                "data_triase_igd.no_rawat from data_triase_igdprimer inner join data_triase_igd on data_triase_igd.no_rawat="+
//                                "data_triase_igdprimer.no_rawat where data_triase_igd.no_rawat=?");
//                        try {
//                            ps.setString(1,tbTriase.getValueAt(tbTriase.getSelectedRow(),0).toString());
//                            rs=ps.executeQuery();
//                            if(rs.next()){
//                                kodepetugas=rs.getString("nik");
//                                PrimerKeluhanUtama.setText(rs.getString("keluhan_utama"));
//                                PrimerSuhu.setText(rs.getString("suhu"));
//                                PrimerNyeri.setText(rs.getString("nyeri"));
//                                PrimerTensi.setText(rs.getString("tekanan_darah"));
//                                PrimerNadi.setText(rs.getString("nadi"));
//                                PrimerSaturasi.setText(rs.getString("saturasi_o2"));
//                                PrimerRespirasi.setText(rs.getString("pernapasan"));
//                                PrimerKubutuhanKusus.setSelectedItem(rs.getString("kebutuhan_khusus"));
//                                TabTriase.setSelectedIndex(0);
//
//                                ps2=koneksi.prepareStatement(
//                                        "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
//                                        "from master_triase_pemeriksaan inner join master_triase_skala1 inner join data_triase_igddetail_skala1 "+
//                                        "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala1.kode_pemeriksaan and "+
//                                        "master_triase_skala1.kode_skala1=data_triase_igddetail_skala1.kode_skala1 where data_triase_igddetail_skala1.no_rawat=? "+
//                                        "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
//                                try {
//                                    ps2.setString(1,rs.getString("no_rawat"));
//                                    rs2=ps2.executeQuery();
//                                    if(rs2.next()){
//                                        TabSkala1dan2.setSelectedIndex(0);
//                                        Valid.tabelKosong(tabModePemeriksaan);
//                                        Valid.tabelKosong(tabModeSkala1);
//                                        rs2.beforeFirst();
//                                        while(rs2.next()){
//                                            tabModePemeriksaan.addRow(new String[]{rs2.getString("kode_pemeriksaan"),rs2.getString("nama_pemeriksaan")});
//                                            ps3=koneksi.prepareStatement(
//                                                    "select master_triase_skala1.kode_skala1,master_triase_skala1.pengkajian_skala1 from master_triase_skala1 inner join data_triase_igddetail_skala1 "+
//                                                    "on master_triase_skala1.kode_skala1=data_triase_igddetail_skala1.kode_skala1 where "+
//                                                    "master_triase_skala1.kode_pemeriksaan=? and data_triase_igddetail_skala1.no_rawat=? "+
//                                                    "order by data_triase_igddetail_skala1.kode_skala1");
//                                            try {
//                                                ps3.setString(1,rs2.getString("kode_pemeriksaan"));
//                                                ps3.setString(2,rs.getString("no_rawat"));
//                                                rs3=ps3.executeQuery();
//                                                while(rs3.next()){
//                                                    tabModeSkala1.addRow(new Object[]{true,rs3.getString("kode_skala1"),rs3.getString("pengkajian_skala1")});
//                                                }
//                                            } catch (Exception e) {
//                                                System.out.println("Notif : "+e);
//                                            } finally{
//                                                if(rs3!=null){
//                                                    rs3.close();
//                                                }
//                                                if(ps3!=null){
//                                                    ps3.close();
//                                                }
//                                            }
//                                        }
//                                    }
//                                } catch (Exception e) {
//                                    System.out.println("Notif : "+e);
//                                } finally{
//                                    if(rs2!=null){
//                                        rs2.close();
//                                    }
//                                    if(ps2!=null){
//                                        ps2.close();
//                                    }
//                                }
//
//                                ps2=koneksi.prepareStatement(
//                                        "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
//                                        "from master_triase_pemeriksaan inner join master_triase_skala2 inner join data_triase_igddetail_skala2 "+
//                                        "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala2.kode_pemeriksaan and "+
//                                        "master_triase_skala2.kode_skala2=data_triase_igddetail_skala2.kode_skala2 where data_triase_igddetail_skala2.no_rawat=? "+
//                                        "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
//                                try {
//                                    ps2.setString(1,rs.getString("no_rawat"));
//                                    rs2=ps2.executeQuery();
//                                    if(rs2.next()){
//                                        TabSkala1dan2.setSelectedIndex(1);
//                                        Valid.tabelKosong(tabModePemeriksaan);
//                                        Valid.tabelKosong(tabModeSkala2);
//                                        rs2.beforeFirst();
//                                        while(rs2.next()){
//                                            tabModePemeriksaan.addRow(new String[]{rs2.getString("kode_pemeriksaan"),rs2.getString("nama_pemeriksaan")});
//                                            ps3=koneksi.prepareStatement(
//                                                    "select master_triase_skala2.kode_skala2,master_triase_skala2.pengkajian_skala2 from master_triase_skala2 inner join data_triase_igddetail_skala2 "+
//                                                    "on master_triase_skala2.kode_skala2=data_triase_igddetail_skala2.kode_skala2 where "+
//                                                    "master_triase_skala2.kode_pemeriksaan=? and data_triase_igddetail_skala2.no_rawat=? "+
//                                                    "order by data_triase_igddetail_skala2.kode_skala2");
//                                            try {
//                                                ps3.setString(1,rs2.getString("kode_pemeriksaan"));
//                                                ps3.setString(2,rs.getString("no_rawat"));
//                                                rs3=ps3.executeQuery();
//                                                while(rs3.next()){
//                                                    tabModeSkala2.addRow(new Object[]{true,rs3.getString("kode_skala2"),rs3.getString("pengkajian_skala2")});
//                                                }
//                                            } catch (Exception e) {
//                                                System.out.println("Notif : "+e);
//                                            } finally{
//                                                if(rs3!=null){
//                                                    rs3.close();
//                                                }
//                                                if(ps3!=null){
//                                                    ps3.close();
//                                                }
//                                            }
//                                        }
//                                    }
//                                } catch (Exception e) {
//                                    System.out.println("Notif : "+e);
//                                } finally{
//                                    if(rs2!=null){
//                                        rs2.close();
//                                    }
//                                    if(ps2!=null){
//                                        ps2.close();
//                                    }
//                                }
//
//                                if(rs.getString("plan").equals("Ruang Resusitasi")){
//                                    PrimerResusitasi.setSelected(true);
//                                }else if(rs.getString("plan").equals("Ruang Kritis")){
//                                    PrimerKritis.setSelected(true);
//                                }
//                                
//                                PrimerTanggalTriase.setDate(rs.getDate("tanggaltriase"));
//                                PrimerCatatan.setText(rs.getString("catatan"));
//                            }
//                        } catch (Exception e) {
//                            System.out.println("Notifikasi : "+e);
//                        } finally{
//                            if(rs!=null){
//                                rs.close();
//                            }
//                            if(ps!=null){
//                                ps.close();
//                            }
//                        }
//
//                        ps=koneksi.prepareStatement(
//                                "select data_triase_igdsekunder.anamnesa_singkat,data_triase_igdsekunder.catatan,"+
//                                "data_triase_igdsekunder.plan,data_triase_igdsekunder.tanggaltriase,data_triase_igdsekunder.nik,data_triase_igd.tekanan_darah,"+
//                                "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
//                                "data_triase_igd.no_rawat from data_triase_igdsekunder inner join data_triase_igd on data_triase_igd.no_rawat="+
//                                "data_triase_igdsekunder.no_rawat where data_triase_igd.no_rawat=?");
//                        try {
//                            ps.setString(1,tbTriase.getValueAt(tbTriase.getSelectedRow(),0).toString());
//                            rs=ps.executeQuery();
//                            if(rs.next()){
//                                kodepetugas=rs.getString("nik");
//                                SekunderAnamnesa.setText(rs.getString("anamnesa_singkat"));
//                                SekunderSuhu.setText(rs.getString("suhu"));
//                                SekunderNyeri.setText(rs.getString("nyeri"));
//                                SekunderTensi.setText(rs.getString("tekanan_darah"));
//                                SekunderNadi.setText(rs.getString("nadi"));
//                                SekunderSaturasi.setText(rs.getString("saturasi_o2"));
//                                SekunderRespirasi.setText(rs.getString("pernapasan"));
//                                TabTriase.setSelectedIndex(1);
//
//                                ps2=koneksi.prepareStatement(
//                                        "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
//                                        "from master_triase_pemeriksaan inner join master_triase_skala3 inner join data_triase_igddetail_skala3 "+
//                                        "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala3.kode_pemeriksaan and "+
//                                        "master_triase_skala3.kode_skala3=data_triase_igddetail_skala3.kode_skala3 where data_triase_igddetail_skala3.no_rawat=? "+
//                                        "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
//                                try {
//                                    ps2.setString(1,rs.getString("no_rawat"));
//                                    rs2=ps2.executeQuery();
//                                    if(rs2.next()){
//                                        TabSkala3dan4dan5.setSelectedIndex(0);
//                                        Valid.tabelKosong(tabModePemeriksaan2);
//                                        Valid.tabelKosong(tabModeSkala3);
//                                        rs2.beforeFirst();
//                                        while(rs2.next()){
//                                            tabModePemeriksaan2.addRow(new String[]{rs2.getString("kode_pemeriksaan"),rs2.getString("nama_pemeriksaan")});
//                                            ps3=koneksi.prepareStatement(
//                                                    "select master_triase_skala3.kode_skala3,master_triase_skala3.pengkajian_skala3 from master_triase_skala3 inner join data_triase_igddetail_skala3 "+
//                                                    "on master_triase_skala3.kode_skala3=data_triase_igddetail_skala3.kode_skala3 where "+
//                                                    "master_triase_skala3.kode_pemeriksaan=? and data_triase_igddetail_skala3.no_rawat=? "+
//                                                    "order by data_triase_igddetail_skala3.kode_skala3");
//                                            try {
//                                                ps3.setString(1,rs2.getString("kode_pemeriksaan"));
//                                                ps3.setString(2,rs.getString("no_rawat"));
//                                                rs3=ps3.executeQuery();
//                                                while(rs3.next()){
//                                                    tabModeSkala3.addRow(new Object[]{true,rs3.getString("kode_skala3"),rs3.getString("pengkajian_skala3")});
//                                                }
//                                            } catch (Exception e) {
//                                                System.out.println("Notif : "+e);
//                                            } finally{
//                                                if(rs3!=null){
//                                                    rs3.close();
//                                                }
//                                                if(ps3!=null){
//                                                    ps3.close();
//                                                }
//                                            }
//                                        }
//                                    }
//                                } catch (Exception e) {
//                                    System.out.println("Notif : "+e);
//                                } finally{
//                                    if(rs2!=null){
//                                        rs2.close();
//                                    }
//                                    if(ps2!=null){
//                                        ps2.close();
//                                    }
//                                }
//
//                                ps2=koneksi.prepareStatement(
//                                        "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
//                                        "from master_triase_pemeriksaan inner join master_triase_skala4 inner join data_triase_igddetail_skala4 "+
//                                        "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala4.kode_pemeriksaan and "+
//                                        "master_triase_skala4.kode_skala4=data_triase_igddetail_skala4.kode_skala4 where data_triase_igddetail_skala4.no_rawat=? "+
//                                        "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
//                                try {
//                                    ps2.setString(1,rs.getString("no_rawat"));
//                                    rs2=ps2.executeQuery();
//                                    if(rs2.next()){
//                                        TabSkala3dan4dan5.setSelectedIndex(1);
//                                        Valid.tabelKosong(tabModePemeriksaan2);
//                                        Valid.tabelKosong(tabModeSkala4);
//                                        rs2.beforeFirst();
//                                        while(rs2.next()){
//                                            tabModePemeriksaan2.addRow(new String[]{rs2.getString("kode_pemeriksaan"),rs2.getString("nama_pemeriksaan")});
//                                            ps3=koneksi.prepareStatement(
//                                                    "select master_triase_skala4.kode_skala4,master_triase_skala4.pengkajian_skala4 from master_triase_skala4 inner join data_triase_igddetail_skala4 "+
//                                                    "on master_triase_skala4.kode_skala4=data_triase_igddetail_skala4.kode_skala4 where "+
//                                                    "master_triase_skala4.kode_pemeriksaan=? and data_triase_igddetail_skala4.no_rawat=? "+
//                                                    "order by data_triase_igddetail_skala4.kode_skala4");
//                                            try {
//                                                ps3.setString(1,rs2.getString("kode_pemeriksaan"));
//                                                ps3.setString(2,rs.getString("no_rawat"));
//                                                rs3=ps3.executeQuery();
//                                                while(rs3.next()){
//                                                    tabModeSkala4.addRow(new Object[]{true,rs3.getString("kode_skala4"),rs3.getString("pengkajian_skala4")});
//                                                }
//                                            } catch (Exception e) {
//                                                System.out.println("Notif : "+e);
//                                            } finally{
//                                                if(rs3!=null){
//                                                    rs3.close();
//                                                }
//                                                if(ps3!=null){
//                                                    ps3.close();
//                                                }
//                                            }
//                                        }
//                                    }
//                                } catch (Exception e) {
//                                    System.out.println("Notif : "+e);
//                                } finally{
//                                    if(rs2!=null){
//                                        rs2.close();
//                                    }
//                                    if(ps2!=null){
//                                        ps2.close();
//                                    }
//                                }
//
//                                ps2=koneksi.prepareStatement(
//                                        "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
//                                        "from master_triase_pemeriksaan inner join master_triase_skala5 inner join data_triase_igddetail_skala5 "+
//                                        "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala5.kode_pemeriksaan and "+
//                                        "master_triase_skala5.kode_skala5=data_triase_igddetail_skala5.kode_skala5 where data_triase_igddetail_skala5.no_rawat=? "+
//                                        "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
//                                try {
//                                    ps2.setString(1,rs.getString("no_rawat"));
//                                    rs2=ps2.executeQuery();
//                                    if(rs2.next()){
//                                        TabSkala3dan4dan5.setSelectedIndex(2);
//                                        Valid.tabelKosong(tabModePemeriksaan2);
//                                        Valid.tabelKosong(tabModeSkala5);
//                                        rs2.beforeFirst();
//                                        while(rs2.next()){
//                                            tabModePemeriksaan2.addRow(new String[]{rs2.getString("kode_pemeriksaan"),rs2.getString("nama_pemeriksaan")});
//                                            ps3=koneksi.prepareStatement(
//                                                    "select master_triase_skala5.kode_skala5,master_triase_skala5.pengkajian_skala5 from master_triase_skala5 inner join data_triase_igddetail_skala5 "+
//                                                    "on master_triase_skala5.kode_skala5=data_triase_igddetail_skala5.kode_skala5 where "+
//                                                    "master_triase_skala5.kode_pemeriksaan=? and data_triase_igddetail_skala5.no_rawat=? "+
//                                                    "order by data_triase_igddetail_skala5.kode_skala5");
//                                            try {
//                                                ps3.setString(1,rs2.getString("kode_pemeriksaan"));
//                                                ps3.setString(2,rs.getString("no_rawat"));
//                                                rs3=ps3.executeQuery();
//                                                while(rs3.next()){
//                                                    tabModeSkala5.addRow(new Object[]{true,rs3.getString("kode_skala5"),rs3.getString("pengkajian_skala5")});
//                                                }
//                                            } catch (Exception e) {
//                                                System.out.println("Notif : "+e);
//                                            } finally{
//                                                if(rs3!=null){
//                                                    rs3.close();
//                                                }
//                                                if(ps3!=null){
//                                                    ps3.close();
//                                                }
//                                            }
//                                        }
//                                    }
//                                } catch (Exception e) {
//                                    System.out.println("Notif : "+e);
//                                } finally{
//                                    if(rs2!=null){
//                                        rs2.close();
//                                    }
//                                    if(ps2!=null){
//                                        ps2.close();
//                                    }
//                                }
//
//                                if(rs.getString("plan").equals("Zona Kuning")){
//                                    SekunderZonaKuning.setSelected(true);
//                                }else if(rs.getString("plan").equals("Zona Hijau")){
//                                    SekunderZonaHijau.setSelected(true);
//                                }
//                                
//                                SekunderTanggalTriase.setDate(rs.getDate("tanggaltriase"));
//                                SekunderCatatan.setText(rs.getString("catatan"));
//                            }
//                        } catch (Exception e) {
//                            System.out.println("Notifikasi : "+e);
//                        } finally{
//                            if(rs!=null){
//                                rs.close();
//                            }
//                            if(ps!=null){
//                                ps.close();
//                            }
//                        }
//                    } catch (Exception e) {
//                        System.out.println("Notif : "+e);
//                    }
//                    TNoRM1.setText("");
//                    TPasien1.setText("");
//                    LoadHTML.setText("");
//                    ChkAccor.setSelected(false);
//                    isMenu();
//                }
//            }           
//        }
}//GEN-LAST:event_tbTriaseKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        tampilPemeriksaan();
//        tampilPemeriksaan2();
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TabPilihanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPilihanMouseClicked
        if(TabPilihan.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabPilihanMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
//        if(this.getHeight()<620){   
//            ScrollTriase.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//            FormTriase.setPreferredSize(new Dimension(FormTriase.WIDTH,500));
//            if(this.getWidth()<780){
//                ScrollTriase.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);                                
//                FormTriase.setPreferredSize(new Dimension(770,500));
//            }else{
//                ScrollTriase.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);                
//            }
//        }else{
//            ScrollTriase.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);            
//            if(this.getWidth()<780){
//                ScrollTriase.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);                                
//                FormTriase.setPreferredSize(new Dimension(770,FormTriase.HEIGHT));
//            }else{
//                ScrollTriase.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);                
//            }
//        }
    }//GEN-LAST:event_formWindowActivated

    private void tbTriaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTriaseKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbTriaseKeyReleased

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if(tbTriase.getSelectedRow()!= -1){
            isMenu();
        }else{
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data yang mau ditampilkan triasenya...!!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        if(TNoRM1.getText().trim().equals("")||TPasien1.getText().trim().equals("")){
            Valid.textKosong(TCari,"Pasien");
        }else{
            if(tbTriase.getSelectedRow()> -1){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                i=0;
                if(LoadHTML.getText().contains("#AA0000")){
                    Map<String, Object> param = new HashMap<>(); 
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());   
                    param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                    try {
                        ps=koneksi.prepareStatement(
                            "select data_triase_igdprimer.keluhan_utama,data_triase_igdprimer.kebutuhan_khusus,data_triase_igdprimer.catatan,"+
                            "data_triase_igdprimer.plan,data_triase_igdprimer.tanggaltriase,data_triase_igdprimer.nik,data_triase_igd.tekanan_darah,"+
                            "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                            "data_triase_igd.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,pegawai.nama,data_triase_igd.tgl_kunjungan, "+
                            "data_triase_igd.cara_masuk,master_triase_macam_kasus.macam_kasus from data_triase_igdprimer inner join data_triase_igd "+
                            "inner join pasien inner join pegawai inner join reg_periksa inner join master_triase_macam_kasus on "+
                            "data_triase_igd.no_rawat=data_triase_igdprimer.no_rawat and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                            "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and pegawai.nik=data_triase_igdprimer.nik "+
                            "and master_triase_macam_kasus.kode_kasus=data_triase_igd.kode_kasus where data_triase_igd.no_rawat=?");
                        try {
                            ps.setString(1,tbTriase.getValueAt(tbTriase.getSelectedRow(),0).toString());
                            rs=ps.executeQuery();
                            if(rs.next()){
                                param.put("norawat",rs.getString("no_rawat"));
                                param.put("norm",rs.getString("no_rkm_medis"));
                                param.put("namapasien",rs.getString("nm_pasien"));
                                param.put("tanggallahir",rs.getDate("tgl_lahir"));
                                param.put("jk",rs.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"));
                                param.put("tanggalkunjungan",rs.getDate("tgl_kunjungan"));
                                param.put("jamkunjungan",rs.getString("tgl_kunjungan").toString().substring(11,19));
                                param.put("caradatang",rs.getString("cara_masuk"));
                                param.put("macamkasus",rs.getString("macam_kasus"));
                                param.put("keluhanutama",rs.getString("keluhan_utama"));
                                param.put("kebutuhankhusus",rs.getString("kebutuhan_khusus"));
                                param.put("plan",rs.getString("plan"));
                                param.put("tanggaltriase",rs.getDate("tanggaltriase"));
                                param.put("jamtriase",rs.getString("tanggaltriase").toString().substring(11,19));
                                param.put("pegawai",rs.getString("nama"));
                                param.put("catatan",rs.getString("catatan"));
                                param.put("tandavital","Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan"));
                                finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("nik"));
                                param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nama")+"\nID "+(finger.equals("")?rs.getString("nik"):finger)+"\n"+Valid.SetTgl3(rs.getString("tanggaltriase"))); 
                                ps2=koneksi.prepareStatement(
                                    "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                    "from master_triase_pemeriksaan inner join master_triase_skala1 inner join data_triase_igddetail_skala1 "+
                                    "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala1.kode_pemeriksaan and "+
                                    "master_triase_skala1.kode_skala1=data_triase_igddetail_skala1.kode_skala1 where data_triase_igddetail_skala1.no_rawat=? "+
                                    "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                try {
                                    Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
                                    ps2.setString(1,rs.getString("no_rawat"));
                                    rs2=ps2.executeQuery();
                                    while(rs2.next()){
                                        datatriase="";
                                        ps3=koneksi.prepareStatement(
                                            "select master_triase_skala1.pengkajian_skala1 from master_triase_skala1 inner join data_triase_igddetail_skala1 "+
                                            "on master_triase_skala1.kode_skala1=data_triase_igddetail_skala1.kode_skala1 where "+
                                            "master_triase_skala1.kode_pemeriksaan=? and data_triase_igddetail_skala1.no_rawat=? "+
                                            "order by data_triase_igddetail_skala1.kode_skala1");
                                        try {
                                            ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                            ps3.setString(2,rs.getString("no_rawat"));
                                            rs3=ps3.executeQuery();
                                            while(rs3.next()){
                                                datatriase=rs3.getString("pengkajian_skala1")+", "+datatriase;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Notif : "+e);
                                        } finally{
                                            if(rs3!=null){
                                                rs3.close();
                                            }
                                            if(ps3!=null){
                                                ps3.close();
                                            }
                                        }
                                        
                                        if(datatriase.endsWith(", ")){
                                            datatriase = datatriase.substring(0,datatriase.length() - 2);
                                        }
                                        Sequel.menyimpan2("temporary","'"+i+"','"+rs2.getString("nama_pemeriksaan")+"','"+datatriase+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Transaksi");
                                        i++;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notif : "+e);
                                } finally{
                                    if(rs2!=null){
                                        rs2.close();
                                    }
                                    if(ps2!=null){
                                        ps2.close();
                                    }
                                }
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
                                
                    pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih Lembar/PDF Triase Skala 1..!","Pilihan",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Lembar Triase Skala 1","PDF Triase Skala 1"},"Lambar Triase Skala 1");
                    switch (pilihan) {
                        case "Lembar Triase Skala 1":
                              Valid.MyReportqry("rptLembarTriaseSkala1.jasper","report","::[ Triase Skala 1 ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
                              break;
                        case "PDF Triase Skala 1":
                              Valid.MyReportqrypdf("rptLembarTriaseSkala1.jasper","report","::[ Triase Skala 1 ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
                              break;
                    } 
                }else if(LoadHTML.getText().contains("#FF0000")){
                    Map<String, Object> param = new HashMap<>(); 
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());   
                    param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                    try {
                        ps=koneksi.prepareStatement(
                            "select data_triase_igdprimer.keluhan_utama,data_triase_igdprimer.kebutuhan_khusus,data_triase_igdprimer.catatan,"+
                            "data_triase_igdprimer.plan,data_triase_igdprimer.tanggaltriase,data_triase_igdprimer.nik,data_triase_igd.tekanan_darah,"+
                            "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                            "data_triase_igd.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,pegawai.nama,data_triase_igd.tgl_kunjungan, "+
                            "data_triase_igd.cara_masuk,master_triase_macam_kasus.macam_kasus from data_triase_igdprimer inner join data_triase_igd "+
                            "inner join pasien inner join pegawai inner join reg_periksa inner join master_triase_macam_kasus on "+
                            "data_triase_igd.no_rawat=data_triase_igdprimer.no_rawat and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                            "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and pegawai.nik=data_triase_igdprimer.nik "+
                            "and master_triase_macam_kasus.kode_kasus=data_triase_igd.kode_kasus where data_triase_igd.no_rawat=?");
                        try {
                            ps.setString(1,tbTriase.getValueAt(tbTriase.getSelectedRow(),0).toString());
                            rs=ps.executeQuery();
                            if(rs.next()){
                                param.put("norawat",rs.getString("no_rawat"));
                                param.put("norm",rs.getString("no_rkm_medis"));
                                param.put("namapasien",rs.getString("nm_pasien"));
                                param.put("tanggallahir",rs.getDate("tgl_lahir"));
                                param.put("jk",rs.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"));
                                param.put("tanggalkunjungan",rs.getDate("tgl_kunjungan"));
                                param.put("jamkunjungan",rs.getString("tgl_kunjungan").toString().substring(11,19));
                                param.put("caradatang",rs.getString("cara_masuk"));
                                param.put("macamkasus",rs.getString("macam_kasus"));
                                param.put("keluhanutama",rs.getString("keluhan_utama"));
                                param.put("kebutuhankhusus",rs.getString("kebutuhan_khusus"));
                                param.put("plan",rs.getString("plan"));
                                param.put("tanggaltriase",rs.getDate("tanggaltriase"));
                                param.put("tandavital","Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan"));
                                param.put("jamtriase",rs.getString("tanggaltriase").toString().substring(11,19));
                                param.put("pegawai",rs.getString("nama"));
                                param.put("catatan",rs.getString("catatan"));
                                finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("nik"));
                                param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nama")+"\nID "+(finger.equals("")?rs.getString("nik"):finger)+"\n"+Valid.SetTgl3(rs.getString("tanggaltriase"))); 
                                ps2=koneksi.prepareStatement(
                                    "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                    "from master_triase_pemeriksaan inner join master_triase_skala2 inner join data_triase_igddetail_skala2 "+
                                    "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala2.kode_pemeriksaan and "+
                                    "master_triase_skala2.kode_skala2=data_triase_igddetail_skala2.kode_skala2 where data_triase_igddetail_skala2.no_rawat=? "+
                                    "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                try {
                                    Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
                                    ps2.setString(1,rs.getString("no_rawat"));
                                    rs2=ps2.executeQuery();
                                    while(rs2.next()){
                                        datatriase="";
                                        ps3=koneksi.prepareStatement(
                                            "select master_triase_skala2.pengkajian_skala2 from master_triase_skala2 inner join data_triase_igddetail_skala2 "+
                                            "on master_triase_skala2.kode_skala2=data_triase_igddetail_skala2.kode_skala2 where "+
                                            "master_triase_skala2.kode_pemeriksaan=? and data_triase_igddetail_skala2.no_rawat=? "+
                                            "order by data_triase_igddetail_skala2.kode_skala2");
                                        try {
                                            ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                            ps3.setString(2,rs.getString("no_rawat"));
                                            rs3=ps3.executeQuery();
                                            while(rs3.next()){
                                                datatriase=rs3.getString("pengkajian_skala2")+", "+datatriase;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Notif : "+e);
                                        } finally{
                                            if(rs3!=null){
                                                rs3.close();
                                            }
                                            if(ps3!=null){
                                                ps3.close();
                                            }
                                        }
                                        
                                        if(datatriase.endsWith(", ")){
                                            datatriase = datatriase.substring(0,datatriase.length() - 2);
                                        }
                                        Sequel.menyimpan2("temporary","'"+i+"','"+rs2.getString("nama_pemeriksaan")+"','"+datatriase+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Transaksi");
                                        i++;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notif : "+e);
                                } finally{
                                    if(rs2!=null){
                                        rs2.close();
                                    }
                                    if(ps2!=null){
                                        ps2.close();
                                    }
                                }
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
                                
                    pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih Lembar/PDF Triase Skala 2..!","Pilihan",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Lembar Triase Skala 2","PDF Triase Skala 2"},"Lambar Triase Skala 2");
                    switch (pilihan) {
                        case "Lembar Triase Skala 2":
                              Valid.MyReportqry("rptLembarTriaseSkala2.jasper","report","::[ Triase Skala 2 ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
                              break;
                        case "PDF Triase Skala 2":
                              Valid.MyReportqrypdf("rptLembarTriaseSkala2.jasper","report","::[ Triase Skala 2 ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
                              break;
                    } 
                }else if(LoadHTML.getText().contains("#C8C800")){
                    Map<String, Object> param = new HashMap<>(); 
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());   
                    param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                    try {
                        ps=koneksi.prepareStatement(
                            "select data_triase_igdsekunder.anamnesa_singkat,data_triase_igdsekunder.catatan,"+
                            "data_triase_igdsekunder.plan,data_triase_igdsekunder.tanggaltriase,data_triase_igdsekunder.nik,data_triase_igd.tekanan_darah,"+
                            "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                            "data_triase_igd.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,pegawai.nama,data_triase_igd.tgl_kunjungan, "+
                            "data_triase_igd.cara_masuk,master_triase_macam_kasus.macam_kasus from data_triase_igdsekunder inner join data_triase_igd "+
                            "inner join pasien inner join pegawai inner join reg_periksa inner join master_triase_macam_kasus on "+
                            "data_triase_igd.no_rawat=data_triase_igdsekunder.no_rawat and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                            "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and pegawai.nik=data_triase_igdsekunder.nik "+
                            "and master_triase_macam_kasus.kode_kasus=data_triase_igd.kode_kasus where data_triase_igd.no_rawat=?");
                        try {
                            ps.setString(1,tbTriase.getValueAt(tbTriase.getSelectedRow(),0).toString());
                            rs=ps.executeQuery();
                            if(rs.next()){
                                param.put("norawat",rs.getString("no_rawat"));
                                param.put("norm",rs.getString("no_rkm_medis"));
                                param.put("namapasien",rs.getString("nm_pasien"));
                                param.put("tanggallahir",rs.getDate("tgl_lahir"));
                                param.put("jk",rs.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"));
                                param.put("tanggalkunjungan",rs.getDate("tgl_kunjungan"));
                                param.put("jamkunjungan",rs.getString("tgl_kunjungan").toString().substring(11,19));
                                param.put("caradatang",rs.getString("cara_masuk"));
                                param.put("macamkasus",rs.getString("macam_kasus"));
                                param.put("keluhanutama",rs.getString("anamnesa_singkat"));
                                param.put("plan",rs.getString("plan"));
                                param.put("tanggaltriase",rs.getDate("tanggaltriase"));
                                param.put("tandavital","Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan"));
                                param.put("jamtriase",rs.getString("tanggaltriase").toString().substring(11,19));
                                param.put("pegawai",rs.getString("nama"));
                                param.put("catatan",rs.getString("catatan"));
                                finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("nik"));
                                param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nama")+"\nID "+(finger.equals("")?rs.getString("nik"):finger)+"\n"+Valid.SetTgl3(rs.getString("tanggaltriase"))); 
                                ps2=koneksi.prepareStatement(
                                    "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                    "from master_triase_pemeriksaan inner join master_triase_skala3 inner join data_triase_igddetail_skala3 "+
                                    "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala3.kode_pemeriksaan and "+
                                    "master_triase_skala3.kode_skala3=data_triase_igddetail_skala3.kode_skala3 where data_triase_igddetail_skala3.no_rawat=? "+
                                    "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                try {
                                    Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
                                    ps2.setString(1,rs.getString("no_rawat"));
                                    rs2=ps2.executeQuery();
                                    while(rs2.next()){
                                        datatriase="";
                                        ps3=koneksi.prepareStatement(
                                            "select master_triase_skala3.pengkajian_skala3 from master_triase_skala3 inner join data_triase_igddetail_skala3 "+
                                            "on master_triase_skala3.kode_skala3=data_triase_igddetail_skala3.kode_skala3 where "+
                                            "master_triase_skala3.kode_pemeriksaan=? and data_triase_igddetail_skala3.no_rawat=? "+
                                            "order by data_triase_igddetail_skala3.kode_skala3");
                                        try {
                                            ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                            ps3.setString(2,rs.getString("no_rawat"));
                                            rs3=ps3.executeQuery();
                                            while(rs3.next()){
                                                datatriase=rs3.getString("pengkajian_skala3")+", "+datatriase;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Notif : "+e);
                                        } finally{
                                            if(rs3!=null){
                                                rs3.close();
                                            }
                                            if(ps3!=null){
                                                ps3.close();
                                            }
                                        }
                                        
                                        if(datatriase.endsWith(", ")){
                                            datatriase = datatriase.substring(0,datatriase.length() - 2);
                                        }
                                        Sequel.menyimpan2("temporary","'"+i+"','"+rs2.getString("nama_pemeriksaan")+"','"+datatriase+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Transaksi");
                                        i++;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notif : "+e);
                                } finally{
                                    if(rs2!=null){
                                        rs2.close();
                                    }
                                    if(ps2!=null){
                                        ps2.close();
                                    }
                                }
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
                                
                    pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih Lembar/PDF Triase Skala 3..!","Pilihan",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Lembar Triase Skala 3","PDF Triase Skala 3"},"Lembar Triase Skala 3");
                    switch (pilihan) {
                        case "Lembar Triase Skala 3":
                              Valid.MyReportqry("rptLembarTriaseSkala3.jasper","report","::[ Triase Skala 3 ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
                              break;
                        case "PDF Triase Skala 3":
                              Valid.MyReportqrypdf("rptLembarTriaseSkala3.jasper","report","::[ Triase Skala 3 ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
                              break;
                    }
                }else if(LoadHTML.getText().contains("#00AA00")){
                    Map<String, Object> param = new HashMap<>(); 
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());   
                    param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                    try {
                        ps=koneksi.prepareStatement(
                            "select data_triase_igdsekunder.anamnesa_singkat,data_triase_igdsekunder.catatan,"+
                            "data_triase_igdsekunder.plan,data_triase_igdsekunder.tanggaltriase,data_triase_igdsekunder.nik,data_triase_igd.tekanan_darah,"+
                            "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                            "data_triase_igd.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,pegawai.nama,data_triase_igd.tgl_kunjungan, "+
                            "data_triase_igd.cara_masuk,master_triase_macam_kasus.macam_kasus from data_triase_igdsekunder inner join data_triase_igd "+
                            "inner join pasien inner join pegawai inner join reg_periksa inner join master_triase_macam_kasus on "+
                            "data_triase_igd.no_rawat=data_triase_igdsekunder.no_rawat and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                            "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and pegawai.nik=data_triase_igdsekunder.nik "+
                            "and master_triase_macam_kasus.kode_kasus=data_triase_igd.kode_kasus where data_triase_igd.no_rawat=?");
                        try {
                            ps.setString(1,tbTriase.getValueAt(tbTriase.getSelectedRow(),0).toString());
                            rs=ps.executeQuery();
                            if(rs.next()){
                                param.put("norawat",rs.getString("no_rawat"));
                                param.put("norm",rs.getString("no_rkm_medis"));
                                param.put("namapasien",rs.getString("nm_pasien"));
                                param.put("tanggallahir",rs.getDate("tgl_lahir"));
                                param.put("jk",rs.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"));
                                param.put("tanggalkunjungan",rs.getDate("tgl_kunjungan"));
                                param.put("jamkunjungan",rs.getString("tgl_kunjungan").toString().substring(11,19));
                                param.put("caradatang",rs.getString("cara_masuk"));
                                param.put("macamkasus",rs.getString("macam_kasus"));
                                param.put("keluhanutama",rs.getString("anamnesa_singkat"));
                                param.put("plan",rs.getString("plan"));
                                param.put("tanggaltriase",rs.getDate("tanggaltriase"));
                                param.put("tandavital","Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan"));
                                param.put("jamtriase",rs.getString("tanggaltriase").toString().substring(11,19));
                                param.put("pegawai",rs.getString("nama"));
                                param.put("catatan",rs.getString("catatan"));
                                finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("nik"));
                                param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nama")+"\nID "+(finger.equals("")?rs.getString("nik"):finger)+"\n"+Valid.SetTgl3(rs.getString("tanggaltriase"))); 
                                ps2=koneksi.prepareStatement(
                                    "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                    "from master_triase_pemeriksaan inner join master_triase_skala4 inner join data_triase_igddetail_skala4 "+
                                    "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala4.kode_pemeriksaan and "+
                                    "master_triase_skala4.kode_skala4=data_triase_igddetail_skala4.kode_skala4 where data_triase_igddetail_skala4.no_rawat=? "+
                                    "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                try {
                                    Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
                                    ps2.setString(1,rs.getString("no_rawat"));
                                    rs2=ps2.executeQuery();
                                    while(rs2.next()){
                                        datatriase="";
                                        ps3=koneksi.prepareStatement(
                                            "select master_triase_skala4.pengkajian_skala4 from master_triase_skala4 inner join data_triase_igddetail_skala4 "+
                                            "on master_triase_skala4.kode_skala4=data_triase_igddetail_skala4.kode_skala4 where "+
                                            "master_triase_skala4.kode_pemeriksaan=? and data_triase_igddetail_skala4.no_rawat=? "+
                                            "order by data_triase_igddetail_skala4.kode_skala4");
                                        try {
                                            ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                            ps3.setString(2,rs.getString("no_rawat"));
                                            rs3=ps3.executeQuery();
                                            while(rs3.next()){
                                                datatriase=rs3.getString("pengkajian_skala4")+", "+datatriase;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Notif : "+e);
                                        } finally{
                                            if(rs3!=null){
                                                rs3.close();
                                            }
                                            if(ps3!=null){
                                                ps3.close();
                                            }
                                        }
                                        
                                        if(datatriase.endsWith(", ")){
                                            datatriase = datatriase.substring(0,datatriase.length() - 2);
                                        }
                                        Sequel.menyimpan2("temporary","'"+i+"','"+rs2.getString("nama_pemeriksaan")+"','"+datatriase+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Transaksi");
                                        i++;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notif : "+e);
                                } finally{
                                    if(rs2!=null){
                                        rs2.close();
                                    }
                                    if(ps2!=null){
                                        ps2.close();
                                    }
                                }
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
                                
                    pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih Lembar/PDF Triase Skala 4..!","Pilihan",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Lembar Triase Skala 4","PDF Triase Skala 4"},"Lembar Triase Skala 4");
                    switch (pilihan) {
                        case "Lembar Triase Skala 4":
                              Valid.MyReportqry("rptLembarTriaseSkala4.jasper","report","::[ Triase Skala 4 ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
                              break;
                        case "PDF Triase Skala 4":
                              Valid.MyReportqrypdf("rptLembarTriaseSkala4.jasper","report","::[ Triase Skala 4 ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
                              break;
                    }
                }else if(LoadHTML.getText().contains("#969696")){
                    Map<String, Object> param = new HashMap<>(); 
                    param.put("namars",akses.getnamars());
                    param.put("alamatrs",akses.getalamatrs());
                    param.put("kotars",akses.getkabupatenrs());
                    param.put("propinsirs",akses.getpropinsirs());
                    param.put("kontakrs",akses.getkontakrs());
                    param.put("emailrs",akses.getemailrs());   
                    param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                    try {
                        ps=koneksi.prepareStatement(
                            "select data_triase_igdsekunder.anamnesa_singkat,data_triase_igdsekunder.catatan,"+
                            "data_triase_igdsekunder.plan,data_triase_igdsekunder.tanggaltriase,data_triase_igdsekunder.nik,data_triase_igd.tekanan_darah,"+
                            "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                            "data_triase_igd.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,pegawai.nama,data_triase_igd.tgl_kunjungan, "+
                            "data_triase_igd.cara_masuk,master_triase_macam_kasus.macam_kasus from data_triase_igdsekunder inner join data_triase_igd "+
                            "inner join pasien inner join pegawai inner join reg_periksa inner join master_triase_macam_kasus on "+
                            "data_triase_igd.no_rawat=data_triase_igdsekunder.no_rawat and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                            "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and pegawai.nik=data_triase_igdsekunder.nik "+
                            "and master_triase_macam_kasus.kode_kasus=data_triase_igd.kode_kasus where data_triase_igd.no_rawat=?");
                        try {
                            ps.setString(1,tbTriase.getValueAt(tbTriase.getSelectedRow(),0).toString());
                            rs=ps.executeQuery();
                            if(rs.next()){
                                param.put("norawat",rs.getString("no_rawat"));
                                param.put("norm",rs.getString("no_rkm_medis"));
                                param.put("namapasien",rs.getString("nm_pasien"));
                                param.put("tanggallahir",rs.getDate("tgl_lahir"));
                                param.put("jk",rs.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan"));
                                param.put("tanggalkunjungan",rs.getDate("tgl_kunjungan"));
                                param.put("jamkunjungan",rs.getString("tgl_kunjungan").toString().substring(11,19));
                                param.put("caradatang",rs.getString("cara_masuk"));
                                param.put("macamkasus",rs.getString("macam_kasus"));
                                param.put("keluhanutama",rs.getString("anamnesa_singkat"));
                                param.put("plan",rs.getString("plan"));
                                param.put("tanggaltriase",rs.getDate("tanggaltriase"));
                                param.put("tandavital","Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan"));
                                param.put("jamtriase",rs.getString("tanggaltriase").toString().substring(11,19));
                                param.put("pegawai",rs.getString("nama"));
                                param.put("catatan",rs.getString("catatan"));
                                finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",rs.getString("nik"));
                                param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+rs.getString("nama")+"\nID "+(finger.equals("")?rs.getString("nik"):finger)+"\n"+Valid.SetTgl3(rs.getString("tanggaltriase"))); 
                                ps2=koneksi.prepareStatement(
                                    "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                    "from master_triase_pemeriksaan inner join master_triase_skala5 inner join data_triase_igddetail_skala5 "+
                                    "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala5.kode_pemeriksaan and "+
                                    "master_triase_skala5.kode_skala5=data_triase_igddetail_skala5.kode_skala5 where data_triase_igddetail_skala5.no_rawat=? "+
                                    "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                                try {
                                    Sequel.queryu("delete from temporary where temp37='"+akses.getalamatip()+"'");
                                    ps2.setString(1,rs.getString("no_rawat"));
                                    rs2=ps2.executeQuery();
                                    while(rs2.next()){
                                        datatriase="";
                                        ps3=koneksi.prepareStatement(
                                            "select master_triase_skala5.pengkajian_skala5 from master_triase_skala5 inner join data_triase_igddetail_skala5 "+
                                            "on master_triase_skala5.kode_skala5=data_triase_igddetail_skala5.kode_skala5 where "+
                                            "master_triase_skala5.kode_pemeriksaan=? and data_triase_igddetail_skala5.no_rawat=? "+
                                            "order by data_triase_igddetail_skala5.kode_skala5");
                                        try {
                                            ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                            ps3.setString(2,rs.getString("no_rawat"));
                                            rs3=ps3.executeQuery();
                                            while(rs3.next()){
                                                datatriase=rs3.getString("pengkajian_skala5")+", "+datatriase;
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Notif : "+e);
                                        } finally{
                                            if(rs3!=null){
                                                rs3.close();
                                            }
                                            if(ps3!=null){
                                                ps3.close();
                                            }
                                        }
                                        
                                        if(datatriase.endsWith(", ")){
                                            datatriase = datatriase.substring(0,datatriase.length() - 2);
                                        }
                                        Sequel.menyimpan2("temporary","'"+i+"','"+rs2.getString("nama_pemeriksaan")+"','"+datatriase+"','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','"+akses.getalamatip()+"'","Transaksi");
                                        i++;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notif : "+e);
                                } finally{
                                    if(rs2!=null){
                                        rs2.close();
                                    }
                                    if(ps2!=null){
                                        ps2.close();
                                    }
                                }
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
                                
                    pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih Lembar/PDF Triase Skala 5..!","Pilihan",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Lembar Triase Skala 5","PDF Triase Skala 5"},"Lembar Triase Skala 5");
                    switch (pilihan) {
                        case "Lembar Triase Skala 5":
                              Valid.MyReportqry("rptLembarTriaseSkala5.jasper","report","::[ Triase Skala 5 ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
                              break;
                        case "PDF Triase Skala 5":
                              Valid.MyReportqry("rptLembarTriaseSkala5.jasper","report","::[ Triase Skala 5 ]::","select * from temporary where temporary.temp37='"+akses.getalamatip()+"' order by temporary.no",param);
                              break;
                    }
                }
                this.setCursor(Cursor.getDefaultCursor());
            }else{
                JOptionPane.showMessageDialog(null,"Silahkan pilih terlebih dahulu data yang mau dicetak data personal triasenya..!!!");
            }
        }
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DataTriaseIGD dialog = new DataTriaseIGD(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint1;
    private widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.editorpane LoadHTML2;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane ScrollHTML;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM1;
    private widget.TextBox TPasien1;
    private javax.swing.JTabbedPane TabPilihan;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel34;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbTriase;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,data_triase_igd.tgl_kunjungan,"+
                    "data_triase_igd.cara_masuk,data_triase_igd.alat_transportasi,data_triase_igd.alasan_kedatangan,"+
                    "data_triase_igd.keterangan_kedatangan,data_triase_igd.kode_kasus,master_triase_macam_kasus.macam_kasus "+
                    "from reg_periksa inner join pasien inner join data_triase_igd inner join master_triase_macam_kasus "+
                    "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=data_triase_igd.no_rawat "+
                    "and data_triase_igd.kode_kasus=master_triase_macam_kasus.kode_kasus "+
                    "where data_triase_igd.tgl_kunjungan between ? and ? and reg_periksa.no_rawat like ? or "+
                    "data_triase_igd.tgl_kunjungan between ? and ? and pasien.no_rkm_medis like ? or "+
                    "data_triase_igd.tgl_kunjungan between ? and ? and pasien.nm_pasien like ? or "+
                    "data_triase_igd.tgl_kunjungan between ? and ? and data_triase_igd.cara_masuk like ? or "+
                    "data_triase_igd.tgl_kunjungan between ? and ? and data_triase_igd.alat_transportasi like ? or "+
                    "data_triase_igd.tgl_kunjungan between ? and ? and data_triase_igd.alasan_kedatangan like ? or "+
                    "data_triase_igd.tgl_kunjungan between ? and ? and data_triase_igd.keterangan_kedatangan like ? or "+
                    "data_triase_igd.tgl_kunjungan between ? and ? and master_triase_macam_kasus.macam_kasus like ? order by data_triase_igd.tgl_kunjungan");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(8,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(9,"%"+TCari.getText().trim()+"%");
                ps.setString(10,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(11,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(12,"%"+TCari.getText().trim()+"%");
                ps.setString(13,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(14,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(15,"%"+TCari.getText().trim()+"%");
                ps.setString(16,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(17,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(18,"%"+TCari.getText().trim()+"%");
                ps.setString(19,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(20,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(21,"%"+TCari.getText().trim()+"%");
                ps.setString(22,Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00");
                ps.setString(23,Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59");
                ps.setString(24,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_kunjungan"),
                        rs.getString("cara_masuk"),rs.getString("alat_transportasi"),rs.getString("alasan_kedatangan"),
                        rs.getString("keterangan_kedatangan"),rs.getString("kode_kasus"),rs.getString("macam_kasus")
                    });
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
        LCount.setText(""+tabMode.getRowCount());
    }
    
    private void getData() {
        if(tbTriase.getSelectedRow()!= -1){
            try {
                TNoRM1.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(),1).toString());
                TPasien1.setText(tbTriase.getValueAt(tbTriase.getSelectedRow(),2).toString());
                ps=koneksi.prepareStatement(
                        "select data_triase_igdprimer.keluhan_utama,data_triase_igdprimer.kebutuhan_khusus,data_triase_igdprimer.catatan,"+
                        "data_triase_igdprimer.plan,data_triase_igdprimer.tanggaltriase,data_triase_igdprimer.nik,data_triase_igd.tekanan_darah,"+
                        "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                        "data_triase_igd.no_rawat from data_triase_igdprimer inner join data_triase_igd on data_triase_igd.no_rawat="+
                        "data_triase_igdprimer.no_rawat where data_triase_igd.no_rawat=?");
                try {
                    ps.setString(1,tbTriase.getValueAt(tbTriase.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    if(rs.next()){
                        kodepetugas=rs.getString("nik");
                        htmlContent = new StringBuilder();
                        htmlContent.append(    
                            "<tr class='isi'>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35%'>Keterangan</td>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65%'>Triase Primer</td>"+
                            "</tr>"+
                            "<tr class='isi'>"+
                                "<td valign='middle'>Keluhan Utama</td>"+
                                "<td valign='middle'>"+rs.getString("keluhan_utama").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                            "</tr>"+
                            "<tr class='isi'>"+
                                "<td valign='middle'>Tanda Vital</td>"+
                                "<td valign='middle'>Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan")+"</td>"+
                            "</tr>"+
                            "<tr class='isi'>"+
                                "<td valign='middle'>Kebutuhan Khusus</td>"+
                                "<td valign='middle'>"+rs.getString("kebutuhan_khusus")+"</td>"+
                            "</tr>"
                        );
                        
                        ps2=koneksi.prepareStatement(
                                "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                "from master_triase_pemeriksaan inner join master_triase_skala1 inner join data_triase_igddetail_skala1 "+
                                "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala1.kode_pemeriksaan and "+
                                "master_triase_skala1.kode_skala1=data_triase_igddetail_skala1.kode_skala1 where data_triase_igddetail_skala1.no_rawat=? "+
                                "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                        try {
                            ps2.setString(1,rs.getString("no_rawat"));
                            rs2=ps2.executeQuery();
                            if(rs2.next()){
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Pemeriksaan</td>"+
                                        "<td valign='middle' bgcolor='#AA0000' color='ffffff' align='center'>Immediate/Segera</td>"+
                                    "</tr>");
                                rs2.beforeFirst();
                                while(rs2.next()){
                                    htmlContent.append(                             
                                        "<tr class='isi'>"+
                                            "<td valign='middle'>"+rs2.getString("nama_pemeriksaan")+"</td>"+
                                            "<td valign='middle' bgcolor='#AA0000' color='ffffff'>"+
                                                "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0'>"
                                    );
                                    ps3=koneksi.prepareStatement(
                                            "select master_triase_skala1.pengkajian_skala1 from master_triase_skala1 inner join data_triase_igddetail_skala1 "+
                                            "on master_triase_skala1.kode_skala1=data_triase_igddetail_skala1.kode_skala1 where "+
                                            "master_triase_skala1.kode_pemeriksaan=? and data_triase_igddetail_skala1.no_rawat=? "+
                                            "order by data_triase_igddetail_skala1.kode_skala1");
                                    try {
                                        ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                        ps3.setString(2,rs.getString("no_rawat"));
                                        rs3=ps3.executeQuery();
                                        while(rs3.next()){
                                            htmlContent.append(                             
                                                "<tr class='isi'>"+
                                                    "<td border='0' valign='middle' bgcolor='#AA0000' color='ffffff' width='100%'>"+rs3.getString("pengkajian_skala1")+"</td>"+
                                                "</tr>"
                                            );
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Notif : "+e);
                                    } finally{
                                        if(rs3!=null){
                                            rs3.close();
                                        }
                                        if(ps3!=null){
                                            ps3.close();
                                        }
                                    }
                                    htmlContent.append(
                                                "</table>"+
                                            "</td>"+
                                        "</tr>"
                                    );
                                }
                                keputusan="#AA0000";
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : "+e);
                        } finally{
                            if(rs2!=null){
                                rs2.close();
                            }
                            if(ps2!=null){
                                ps2.close();
                            }
                        }
                        
                        ps2=koneksi.prepareStatement(
                                "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                "from master_triase_pemeriksaan inner join master_triase_skala2 inner join data_triase_igddetail_skala2 "+
                                "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala2.kode_pemeriksaan and "+
                                "master_triase_skala2.kode_skala2=data_triase_igddetail_skala2.kode_skala2 where data_triase_igddetail_skala2.no_rawat=? "+
                                "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                        try {
                            ps2.setString(1,rs.getString("no_rawat"));
                            rs2=ps2.executeQuery();
                            if(rs2.next()){
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Pemeriksaan</td>"+
                                        "<td valign='middle' bgcolor='#FF0000' color='ffffff' align='center'>Emergensi</td>"+
                                    "</tr>");
                                rs2.beforeFirst();
                                while(rs2.next()){
                                    htmlContent.append(                             
                                        "<tr class='isi'>"+
                                            "<td valign='middle'>"+rs2.getString("nama_pemeriksaan")+"</td>"+
                                            "<td valign='middle' bgcolor='#FF0000' color='ffffff'>"+
                                                "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0'>"
                                    );
                                    ps3=koneksi.prepareStatement(
                                            "select master_triase_skala2.pengkajian_skala2 from master_triase_skala2 inner join data_triase_igddetail_skala2 "+
                                            "on master_triase_skala2.kode_skala2=data_triase_igddetail_skala2.kode_skala2 where "+
                                            "master_triase_skala2.kode_pemeriksaan=? and data_triase_igddetail_skala2.no_rawat=? "+
                                            "order by data_triase_igddetail_skala2.kode_skala2");
                                    try {
                                        ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                        ps3.setString(2,rs.getString("no_rawat"));
                                        rs3=ps3.executeQuery();
                                        while(rs3.next()){
                                            htmlContent.append(                             
                                                "<tr class='isi'>"+
                                                    "<td border='0' valign='middle' bgcolor='#FF0000' color='ffffff' width='100%'>"+rs3.getString("pengkajian_skala2")+"</td>"+
                                                "</tr>"
                                            );
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Notif : "+e);
                                    } finally{
                                        if(rs3!=null){
                                            rs3.close();
                                        }
                                        if(ps3!=null){
                                            ps3.close();
                                        }
                                    }
                                    htmlContent.append(
                                                "</table>"+
                                            "</td>"+
                                        "</tr>"
                                    );
                                }
                                keputusan="#FF0000";
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : "+e);
                        } finally{
                            if(rs2!=null){
                                rs2.close();
                            }
                            if(ps2!=null){
                                ps2.close();
                            }
                        }
                        
                        htmlContent.append(    
                            "<tr class='isi'>"+
                                "<td valign='middle'>Plan/Keputusan</td>"+
                                "<td valign='middle' bgcolor='"+keputusan+"' color='ffffff'>Zona Merah "+rs.getString("plan")+"</td>"+
                            "</tr>"+                       
                            "<tr class='isi'>"+
                                "<td valign='middle'>&nbsp;</td>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center'>Dokter/Petugas Triase</td>"+
                            "</tr>"+
                            "<tr class='isi'>"+
                                "<td valign='middle'>Tanggal & Jam</td>"+
                                "<td valign='middle'>"+rs.getString("tanggaltriase")+"</td>"+
                            "</tr>"+
                            "<tr class='isi'>"+
                                "<td valign='middle'>Catatan</td>"+
                                "<td valign='middle'>"+rs.getString("catatan")+"</td>"+
                            "</tr>"+
                            "<tr class='isi'>"+
                                "<td valign='middle'>Nama Dokter/Petugas</td>"+
                                "<td valign='middle'>"+rs.getString("nik")+" "+Sequel.cariIsi("select pegawai.nama from pegawai where pegawai.nik=?",rs.getString("nik"))+"</td>"+
                            "</tr>"
                        );
                        
                        LoadHTML.setText(
                            "<html>"+
                              "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                               htmlContent.toString()+
                              "</table>"+
                            "</html>");
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : "+e);
                } finally{
                    if(rs!=null){
                        rs.close();
                    }
                    if(ps!=null){
                        ps.close();
                    }
                }
                
                ps=koneksi.prepareStatement(
                        "select data_triase_igdsekunder.anamnesa_singkat,data_triase_igdsekunder.catatan,"+
                        "data_triase_igdsekunder.plan,data_triase_igdsekunder.tanggaltriase,data_triase_igdsekunder.nik,data_triase_igd.tekanan_darah,"+
                        "data_triase_igd.nadi,data_triase_igd.pernapasan,data_triase_igd.suhu,data_triase_igd.saturasi_o2,data_triase_igd.nyeri,"+
                        "data_triase_igd.no_rawat from data_triase_igdsekunder inner join data_triase_igd on data_triase_igd.no_rawat="+
                        "data_triase_igdsekunder.no_rawat where data_triase_igd.no_rawat=?");
                try {
                    ps.setString(1,tbTriase.getValueAt(tbTriase.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    if(rs.next()){
                        kodepetugas=rs.getString("nik");
                        htmlContent = new StringBuilder();
                        htmlContent.append(    
                            "<tr class='isi'>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center' width='35%'>Keterangan</td>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center' width='65%'>Triase Sekunder</td>"+
                            "</tr>"+
                            "<tr class='isi'>"+
                                "<td valign='middle'>Anamnesa Singkat</td>"+
                                "<td valign='middle'>"+rs.getString("anamnesa_singkat").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                            "</tr>"+
                            "<tr class='isi'>"+
                                "<td valign='middle'>Tanda Vital</td>"+
                                "<td valign='middle'>Suhu (C) : "+rs.getString("suhu")+", Nyeri : "+rs.getString("nyeri")+", Tensi : "+rs.getString("tekanan_darah")+", Nadi(/menit) : "+rs.getString("nadi")+", Saturasi O²(%) : "+rs.getString("saturasi_o2")+", Respirasi(/menit) : "+rs.getString("pernapasan")+"</td>"+
                            "</tr>"
                        );
                        
                        ps2=koneksi.prepareStatement(
                                "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                "from master_triase_pemeriksaan inner join master_triase_skala3 inner join data_triase_igddetail_skala3 "+
                                "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala3.kode_pemeriksaan and "+
                                "master_triase_skala3.kode_skala3=data_triase_igddetail_skala3.kode_skala3 where data_triase_igddetail_skala3.no_rawat=? "+
                                "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                        try {
                            ps2.setString(1,rs.getString("no_rawat"));
                            rs2=ps2.executeQuery();
                            if(rs2.next()){
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Pemeriksaan</td>"+
                                        "<td valign='middle' bgcolor='#C8C800' color='ffffff' align='center'>Urgensi</td>"+
                                    "</tr>");
                                rs2.beforeFirst();
                                while(rs2.next()){
                                    htmlContent.append(                             
                                        "<tr class='isi'>"+
                                            "<td valign='middle'>"+rs2.getString("nama_pemeriksaan")+"</td>"+
                                            "<td valign='middle' bgcolor='#C8C800' color='ffffff'>"+
                                                "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0'>"
                                    );
                                    ps3=koneksi.prepareStatement(
                                            "select master_triase_skala3.pengkajian_skala3 from master_triase_skala3 inner join data_triase_igddetail_skala3 "+
                                            "on master_triase_skala3.kode_skala3=data_triase_igddetail_skala3.kode_skala3 where "+
                                            "master_triase_skala3.kode_pemeriksaan=? and data_triase_igddetail_skala3.no_rawat=? "+
                                            "order by data_triase_igddetail_skala3.kode_skala3");
                                    try {
                                        ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                        ps3.setString(2,rs.getString("no_rawat"));
                                        rs3=ps3.executeQuery();
                                        while(rs3.next()){
                                            htmlContent.append(                             
                                                "<tr class='isi'>"+
                                                    "<td border='0' valign='middle' bgcolor='#C8C800' color='ffffff' width='100%'>"+rs3.getString("pengkajian_skala3")+"</td>"+
                                                "</tr>"
                                            );
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Notif : "+e);
                                    } finally{
                                        if(rs3!=null){
                                            rs3.close();
                                        }
                                        if(ps3!=null){
                                            ps3.close();
                                        }
                                    }
                                    htmlContent.append(
                                                "</table>"+
                                            "</td>"+
                                        "</tr>"
                                    );
                                }
                                keputusan="#C8C800";
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : "+e);
                        } finally{
                            if(rs2!=null){
                                rs2.close();
                            }
                            if(ps2!=null){
                                ps2.close();
                            }
                        }
                        
                        ps2=koneksi.prepareStatement(
                                "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                "from master_triase_pemeriksaan inner join master_triase_skala4 inner join data_triase_igddetail_skala4 "+
                                "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala4.kode_pemeriksaan and "+
                                "master_triase_skala4.kode_skala4=data_triase_igddetail_skala4.kode_skala4 where data_triase_igddetail_skala4.no_rawat=? "+
                                "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                        try {
                            ps2.setString(1,rs.getString("no_rawat"));
                            rs2=ps2.executeQuery();
                            if(rs2.next()){
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Pemeriksaan</td>"+
                                        "<td valign='middle' bgcolor='#00AA00' color='ffffff' align='center'>Semi Urgensi/Urgensi Rendah</td>"+
                                    "</tr>");
                                rs2.beforeFirst();
                                while(rs2.next()){
                                    htmlContent.append(                             
                                        "<tr class='isi'>"+
                                            "<td valign='middle'>"+rs2.getString("nama_pemeriksaan")+"</td>"+
                                            "<td valign='middle' bgcolor='#00AA00' color='ffffff'>"+
                                                "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0'>"
                                    );
                                    ps3=koneksi.prepareStatement(
                                            "select master_triase_skala4.pengkajian_skala4 from master_triase_skala4 inner join data_triase_igddetail_skala4 "+
                                            "on master_triase_skala4.kode_skala4=data_triase_igddetail_skala4.kode_skala4 where "+
                                            "master_triase_skala4.kode_pemeriksaan=? and data_triase_igddetail_skala4.no_rawat=? "+
                                            "order by data_triase_igddetail_skala4.kode_skala4");
                                    try {
                                        ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                        ps3.setString(2,rs.getString("no_rawat"));
                                        rs3=ps3.executeQuery();
                                        while(rs3.next()){
                                            htmlContent.append(                             
                                                "<tr class='isi'>"+
                                                    "<td border='0' valign='middle' bgcolor='#00AA00' color='ffffff' width='100%'>"+rs3.getString("pengkajian_skala4")+"</td>"+
                                                "</tr>"
                                            );
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Notif : "+e);
                                    } finally{
                                        if(rs3!=null){
                                            rs3.close();
                                        }
                                        if(ps3!=null){
                                            ps3.close();
                                        }
                                    }
                                    htmlContent.append(
                                                "</table>"+
                                            "</td>"+
                                        "</tr>"
                                    );
                                }
                                keputusan="#00AA00";
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : "+e);
                        } finally{
                            if(rs2!=null){
                                rs2.close();
                            }
                            if(ps2!=null){
                                ps2.close();
                            }
                        }
                        
                        ps2=koneksi.prepareStatement(
                                "select master_triase_pemeriksaan.kode_pemeriksaan,master_triase_pemeriksaan.nama_pemeriksaan "+
                                "from master_triase_pemeriksaan inner join master_triase_skala5 inner join data_triase_igddetail_skala5 "+
                                "on master_triase_pemeriksaan.kode_pemeriksaan=master_triase_skala5.kode_pemeriksaan and "+
                                "master_triase_skala5.kode_skala5=data_triase_igddetail_skala5.kode_skala5 where data_triase_igddetail_skala5.no_rawat=? "+
                                "group by master_triase_pemeriksaan.kode_pemeriksaan order by master_triase_pemeriksaan.kode_pemeriksaan");
                        try {
                            ps2.setString(1,rs.getString("no_rawat"));
                            rs2=ps2.executeQuery();
                            if(rs2.next()){
                                htmlContent.append(                             
                                    "<tr class='isi'>"+
                                        "<td valign='middle' bgcolor='#FFFAF8' align='center'>Pemeriksaan</td>"+
                                        "<td valign='middle' bgcolor='#969696' color='ffffff' align='center'>Non Urgensi</td>"+
                                    "</tr>");
                                rs2.beforeFirst();
                                while(rs2.next()){
                                    htmlContent.append(                             
                                        "<tr class='isi'>"+
                                            "<td valign='middle'>"+rs2.getString("nama_pemeriksaan")+"</td>"+
                                            "<td valign='middle' bgcolor='#969696' color='ffffff'>"+
                                                "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0'>"
                                    );
                                    ps3=koneksi.prepareStatement(
                                            "select master_triase_skala5.pengkajian_skala5 from master_triase_skala5 inner join data_triase_igddetail_skala5 "+
                                            "on master_triase_skala5.kode_skala5=data_triase_igddetail_skala5.kode_skala5 where "+
                                            "master_triase_skala5.kode_pemeriksaan=? and data_triase_igddetail_skala5.no_rawat=? "+
                                            "order by data_triase_igddetail_skala5.kode_skala5");
                                    try {
                                        ps3.setString(1,rs2.getString("kode_pemeriksaan"));
                                        ps3.setString(2,rs.getString("no_rawat"));
                                        rs3=ps3.executeQuery();
                                        while(rs3.next()){
                                            htmlContent.append(                             
                                                "<tr class='isi'>"+
                                                    "<td border='0' valign='middle' bgcolor='#969696' color='ffffff' width='100%'>"+rs3.getString("pengkajian_skala5")+"</td>"+
                                                "</tr>"
                                            );
                                        }
                                    } catch (Exception e) {
                                        System.out.println("Notif : "+e);
                                    } finally{
                                        if(rs3!=null){
                                            rs3.close();
                                        }
                                        if(ps3!=null){
                                            ps3.close();
                                        }
                                    }
                                    htmlContent.append(
                                                "</table>"+
                                            "</td>"+
                                        "</tr>"
                                    );
                                }
                                keputusan="#969696";
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : "+e);
                        } finally{
                            if(rs2!=null){
                                rs2.close();
                            }
                            if(ps2!=null){
                                ps2.close();
                            }
                        }
                        
                        htmlContent.append(    
                            "<tr class='isi'>"+
                                "<td valign='middle'>Plan/Keputusan</td>"+
                                "<td valign='middle' bgcolor='"+keputusan+"' color='ffffff'>"+rs.getString("plan")+"</td>"+
                            "</tr>"
                        );
                        htmlContent.append(                             
                            "<tr class='isi'>"+
                                "<td valign='middle'>&nbsp;</td>"+
                                "<td valign='middle' bgcolor='#FFFAF8' align='center'>Dokter/Petugas Triase</td>"+
                            "</tr>"+
                            "<tr class='isi'>"+
                                "<td valign='middle'>Tanggal & Jam</td>"+
                                "<td valign='middle'>"+rs.getString("tanggaltriase")+"</td>"+
                            "</tr>"+
                            "<tr class='isi'>"+
                                "<td valign='middle'>Catatan</td>"+
                                "<td valign='middle'>"+rs.getString("catatan")+"</td>"+
                            "</tr>"+
                            "<tr class='isi'>"+
                                "<td valign='middle'>Nama Dokter/Petugas</td>"+
                                "<td valign='middle'>"+rs.getString("nik")+" "+Sequel.cariIsi("select pegawai.nama from pegawai where pegawai.nik=?",rs.getString("nik"))+"</td>"+
                            "</tr>"
                        );
                        
                        LoadHTML.setText(
                            "<html>"+
                              "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                               htmlContent.toString()+
                              "</table>"+
                            "</html>");
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : "+e);
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
    
    private void isMenu(){
        if(ChkAccor.isSelected()==true){
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(470,HEIGHT));
            FormMenu.setVisible(true);
            ScrollHTML.setVisible(true);
            ChkAccor.setVisible(true);
        }else if(ChkAccor.isSelected()==false){
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15,HEIGHT));
            FormMenu.setVisible(false);
            ScrollHTML.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }
    
    public void setNoRm(String no_reg){
        TCari.setText(no_reg);
        tampil();
    }
    
}
