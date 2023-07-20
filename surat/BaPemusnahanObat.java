/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * kontribusi dari dokter Salim Mulyana
 */

package surat;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgKadaluarsaBatch;
import inventory.DlgObatKadaluarsa;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
//new code is here
import kepegawaian.DlgCariPetugas;
//import inventory.DlgKadaluarsaBatch;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 
 * @author salimmulyana
 */
public final class BaPemusnahanObat extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    // new code is here
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private int i=0;
    private String tgl,finger="";
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public BaPemusnahanObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No. Berita Acara","Nama Apoteker","No.SIPA","Nama Apotek","Alamat Apotek","Nama Saksi 1","NIP/NPRS Saksi 1","Jabatan Saksi 1","Nama Saksi 2","NIP/NPRS Saksi 2","Jabatan Saksi 2","Tempat Pemusnahan Obat","Hari/Tanggal","Hari","Tanggal"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(150);
            }else if(i==1){
                column.setPreferredWidth(150);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else if(i==3){
                column.setPreferredWidth(150);
            }else if(i==4){
                column.setPreferredWidth(150);
            }else if(i==5){
                column.setPreferredWidth(150);
            }else if(i==6){
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(150);
            }else if(i==9){
                column.setPreferredWidth(150);
            }else if(i==10){
                column.setPreferredWidth(150);
            }else if(i==11){
                column.setPreferredWidth(150);
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
            }else if(i==12){
                column.setPreferredWidth(150);
            }else if(i==13){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==14){
//                column.setPreferredWidth(150);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        NoBa.setDocument(new batasInput((byte)30).getKata(NoBa));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));           
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
        
//        dokter.addWindowListener(new WindowListener() {
//            @Override
//            public void windowOpened(WindowEvent e) {}
//            @Override
//            public void windowClosing(WindowEvent e) {}
//            @Override
//            public void windowClosed(WindowEvent e) {
//                if(dokter.getTable().getSelectedRow()!= -1){
//                    KdDok.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
//                    TDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
//                }   
//                KdDok.requestFocus();
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
        
        // new code is here
//        petugas.addWindowListener(new WindowListener(){
//            @Override
//            public void windowOpened(WindowEvent e) {}
//
//            @Override
//            public void windowClosing(WindowEvent e) {}
//
//            @Override
//            public void windowClosed(WindowEvent e) {
//                if (petugas.getTable().getSelectedRow()!= -1){
//                    TDokter2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
//                }
//                TDokter2.requestFocus();
//            }
//
//            @Override
//            public void windowIconified(WindowEvent e) {}
//
//            @Override
//            public void windowDeiconified(WindowEvent e) {}
//
//            @Override
//            public void windowActivated(WindowEvent e) {}
//
//            @Override
//            public void windowDeactivated(WindowEvent e) {}
//        });
//        ChkInput.setSelected(false);
//        isForm();
    }
        
        

    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnCetakSuratSKBN3 = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnDataObat = new widget.Button();
        BtnKeluar = new widget.Button();
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
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        NamaApoteker = new widget.TextBox();
        TanggalSurat = new widget.Tanggal();
        jLabel13 = new widget.Label();
        jLabel10 = new widget.Label();
        NoSipa = new widget.TextBox();
        jLabel11 = new widget.Label();
        NamaApotek = new widget.TextBox();
        jLabel12 = new widget.Label();
        AlamatApotek = new widget.TextBox();
        jLabel8 = new widget.Label();
        NamaSaksi1 = new widget.TextBox();
        jLabel14 = new widget.Label();
        KdSaksi1 = new widget.TextBox();
        jLabel15 = new widget.Label();
        JabatanSaksi1 = new widget.TextBox();
        jLabel16 = new widget.Label();
        NamaSaksi2 = new widget.TextBox();
        jLabel17 = new widget.Label();
        KdSaksi2 = new widget.TextBox();
        jLabel18 = new widget.Label();
        JabatanSaksi2 = new widget.TextBox();
        HariBa = new widget.ComboBox();
        jLabel9 = new widget.Label();
        NoBa = new widget.TextBox();
        jLabel20 = new widget.Label();
        TmpPemusnahan = new widget.TextBox();
        ChkInput = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnCetakSuratSKBN3.setBackground(new java.awt.Color(250, 250, 250));
        MnCetakSuratSKBN3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSKBN3.setForeground(new java.awt.Color(50, 50, 50));
        MnCetakSuratSKBN3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSKBN3.setText("Cetak SKBN 4");
        MnCetakSuratSKBN3.setName("MnCetakSuratSKBN3"); // NOI18N
        MnCetakSuratSKBN3.setPreferredSize(new java.awt.Dimension(140, 26));
        MnCetakSuratSKBN3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSKBN3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakSuratSKBN3);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ BA Pemusnahan Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObatKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
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

        BtnDataObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        BtnDataObat.setMnemonic('B');
        BtnDataObat.setText("Data Obat");
        BtnDataObat.setToolTipText("Alt+B");
        BtnDataObat.setName("BtnDataObat"); // NOI18N
        BtnDataObat.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnDataObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataObatActionPerformed(evt);
            }
        });
        BtnDataObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDataObatKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnDataObat);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl. Surat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-05-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-05-2023" }));
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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 185));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 165));
        FormInput.setLayout(null);

        jLabel4.setText("Nama Apoteker :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(20, 40, 90, 23);

        NamaApoteker.setHighlighter(null);
        NamaApoteker.setName("NamaApoteker"); // NOI18N
        NamaApoteker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaApotekerActionPerformed(evt);
            }
        });
        NamaApoteker.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaApotekerKeyPressed(evt);
            }
        });
        FormInput.add(NamaApoteker);
        NamaApoteker.setBounds(120, 40, 141, 23);

        TanggalSurat.setForeground(new java.awt.Color(50, 70, 50));
        TanggalSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-05-2023" }));
        TanggalSurat.setDisplayFormat("dd-MM-yyyy");
        TanggalSurat.setName("TanggalSurat"); // NOI18N
        TanggalSurat.setOpaque(false);
        TanggalSurat.setPreferredSize(new java.awt.Dimension(141, 18));
        TanggalSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TanggalSuratActionPerformed(evt);
            }
        });
        TanggalSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalSuratKeyPressed(evt);
            }
        });
        FormInput.add(TanggalSurat);
        TanggalSurat.setBounds(600, 130, 90, 23);

        jLabel13.setText("Tanggal Surat :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(390, 130, 90, 23);

        jLabel10.setText("Nomor SIPA :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(240, 40, 90, 23);

        NoSipa.setHighlighter(null);
        NoSipa.setName("NoSipa"); // NOI18N
        NoSipa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoSipaActionPerformed(evt);
            }
        });
        NoSipa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSipaKeyPressed(evt);
            }
        });
        FormInput.add(NoSipa);
        NoSipa.setBounds(340, 40, 200, 23);

        jLabel11.setText("Nama Apotek :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(530, 40, 90, 23);

        NamaApotek.setHighlighter(null);
        NamaApotek.setName("NamaApotek"); // NOI18N
        NamaApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaApotekActionPerformed(evt);
            }
        });
        NamaApotek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaApotekKeyPressed(evt);
            }
        });
        FormInput.add(NamaApotek);
        NamaApotek.setBounds(630, 40, 141, 23);

        jLabel12.setText("Alamat Apotek :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(770, 40, 90, 23);

        AlamatApotek.setHighlighter(null);
        AlamatApotek.setName("AlamatApotek"); // NOI18N
        AlamatApotek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlamatApotekActionPerformed(evt);
            }
        });
        AlamatApotek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatApotekKeyPressed(evt);
            }
        });
        FormInput.add(AlamatApotek);
        AlamatApotek.setBounds(870, 40, 200, 23);

        jLabel8.setText("Nama Saksi 1 :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(20, 70, 90, 23);

        NamaSaksi1.setHighlighter(null);
        NamaSaksi1.setName("NamaSaksi1"); // NOI18N
        NamaSaksi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaSaksi1ActionPerformed(evt);
            }
        });
        NamaSaksi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaSaksi1KeyPressed(evt);
            }
        });
        FormInput.add(NamaSaksi1);
        NamaSaksi1.setBounds(120, 70, 141, 23);

        jLabel14.setText("NIP/NPRS Saksi 1 :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(250, 70, 110, 23);

        KdSaksi1.setHighlighter(null);
        KdSaksi1.setName("KdSaksi1"); // NOI18N
        KdSaksi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KdSaksi1ActionPerformed(evt);
            }
        });
        KdSaksi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdSaksi1KeyPressed(evt);
            }
        });
        FormInput.add(KdSaksi1);
        KdSaksi1.setBounds(365, 70, 175, 23);

        jLabel15.setText("Jabatan Saksi 1 :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(540, 70, 90, 23);

        JabatanSaksi1.setHighlighter(null);
        JabatanSaksi1.setName("JabatanSaksi1"); // NOI18N
        JabatanSaksi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JabatanSaksi1ActionPerformed(evt);
            }
        });
        JabatanSaksi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JabatanSaksi1KeyPressed(evt);
            }
        });
        FormInput.add(JabatanSaksi1);
        JabatanSaksi1.setBounds(635, 70, 141, 23);

        jLabel16.setText("Nama Saksi 2 :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(20, 100, 90, 23);

        NamaSaksi2.setHighlighter(null);
        NamaSaksi2.setName("NamaSaksi2"); // NOI18N
        NamaSaksi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaSaksi2ActionPerformed(evt);
            }
        });
        NamaSaksi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaSaksi2KeyPressed(evt);
            }
        });
        FormInput.add(NamaSaksi2);
        NamaSaksi2.setBounds(120, 100, 141, 23);

        jLabel17.setText("NIP/NPRS Saksi 2 :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(250, 100, 110, 23);

        KdSaksi2.setHighlighter(null);
        KdSaksi2.setName("KdSaksi2"); // NOI18N
        KdSaksi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KdSaksi2ActionPerformed(evt);
            }
        });
        KdSaksi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdSaksi2KeyPressed(evt);
            }
        });
        FormInput.add(KdSaksi2);
        KdSaksi2.setBounds(365, 100, 175, 23);

        jLabel18.setText("Jabatan Saksi 2 :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(540, 100, 90, 23);

        JabatanSaksi2.setHighlighter(null);
        JabatanSaksi2.setName("JabatanSaksi2"); // NOI18N
        JabatanSaksi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JabatanSaksi2ActionPerformed(evt);
            }
        });
        JabatanSaksi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JabatanSaksi2KeyPressed(evt);
            }
        });
        FormInput.add(JabatanSaksi2);
        JabatanSaksi2.setBounds(635, 100, 141, 23);

        HariBa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu" }));
        HariBa.setName("HariBa"); // NOI18N
        HariBa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HariBaActionPerformed(evt);
            }
        });
        HariBa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HariBaKeyPressed(evt);
            }
        });
        FormInput.add(HariBa);
        HariBa.setBounds(490, 130, 100, 23);

        jLabel9.setText("No. Berita Acara :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(20, 10, 90, 23);

        NoBa.setHighlighter(null);
        NoBa.setName("NoBa"); // NOI18N
        NoBa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoBaActionPerformed(evt);
            }
        });
        NoBa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoBaKeyPressed(evt);
            }
        });
        FormInput.add(NoBa);
        NoBa.setBounds(120, 10, 210, 23);

        jLabel20.setText("Tempat Pemusnahan Obat :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(15, 130, 150, 23);

        TmpPemusnahan.setHighlighter(null);
        TmpPemusnahan.setName("TmpPemusnahan"); // NOI18N
        TmpPemusnahan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TmpPemusnahanActionPerformed(evt);
            }
        });
        TmpPemusnahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmpPemusnahanKeyPressed(evt);
            }
        });
        FormInput.add(TmpPemusnahan);
        TmpPemusnahan.setBounds(175, 130, 200, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void NamaApotekerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaApotekerKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
////            isRawat();
////            isPsien();
//        }else{            
//            Valid.pindah(evt,TCari,Keperluan);
//        }
}//GEN-LAST:event_NamaApotekerKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        // new code is here
        if(NoBa.getText().trim().equals("")){
            Valid.textKosong(NamaApoteker,"No Berita Acara");
        }else if(NamaApoteker.getText().trim().equals("")){
            Valid.textKosong(NamaApoteker,"Nama Apoteker");
        }else if(NoSipa.getText().trim().equals("")){
            Valid.textKosong(NoSipa,"Nomor SIPA");
        }else if(NamaApotek.getText().trim().equals("")){
            Valid.textKosong(NamaApotek,"Nama Apotek");
        }else if(AlamatApotek.getText().trim().equals("")){
            Valid.textKosong(AlamatApotek,"Alamat Apotek");
        }else if(NamaSaksi1.getText().trim().equals("")){
            Valid.textKosong(NamaSaksi1,"Nama Saksi 1");
        }else if(KdSaksi1.getText().trim().equals("")){
            Valid.textKosong(KdSaksi1,"NIP/NPRS Saksi 1");
        }else if(JabatanSaksi1.getText().trim().equals("")){
            Valid.textKosong(JabatanSaksi1,"Jabatan Saksi 1");
        }else if(NamaSaksi2.getText().trim().equals("")){
            Valid.textKosong(NamaSaksi2,"Nama Saksi 2");
        }else if(KdSaksi2.getText().trim().equals("")){
            Valid.textKosong(KdSaksi2,"NIP/NPRS Saksi 2");
        }else if(JabatanSaksi2.getText().trim().equals("")){
            Valid.textKosong(JabatanSaksi2,"Jabatan Saksi 2");
        }else if(TmpPemusnahan.getText().trim().equals("")){
            Valid.textKosong(TmpPemusnahan,"Jabatan Saksi 2");
        }else{
            Sequel.menyimpan("ba_pemusnahan_obat","?,?,?,?,?,?,?,?,?,?,?,?,?,?","No BA",14,new String[]{
                NoBa.getText(),NamaApoteker.getText(),NoSipa.getText(),NamaApotek.getText(),AlamatApotek.getText(),NamaSaksi1.getText(),
                KdSaksi1.getText(),JabatanSaksi1.getText(),NamaSaksi2.getText(),KdSaksi2.getText(),JabatanSaksi2.getText(),
                Valid.SetTgl(TanggalSurat.getSelectedItem()+""),HariBa.getSelectedItem().toString(),TmpPemusnahan.getText()
            });
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            BtnSimpanActionPerformed(null);
//        }else{
//            Valid.pindah(evt,hasil6,BtnBatal);
//        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm(); 
        
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        Valid.hapusTable(tabMode,NoBa,"ba_pemusnahan_obat","no_ba");
        tampil();
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        // new code is here
        if(NoBa.getText().trim().equals("")){
            Valid.textKosong(NamaApoteker,"No Berita Acara");
        }else if(NamaApoteker.getText().trim().equals("")){
            Valid.textKosong(NamaApoteker,"Nama Apoteker");
        }else if(NoSipa.getText().trim().equals("")){
            Valid.textKosong(NoSipa,"Nomor SIPA");
        }else if(NamaApotek.getText().trim().equals("")){
            Valid.textKosong(NamaApotek,"Nama Apotek");
        }else if(AlamatApotek.getText().trim().equals("")){
            Valid.textKosong(AlamatApotek,"Alamat Apotek");
        }else if(NamaSaksi1.getText().trim().equals("")){
            Valid.textKosong(NamaSaksi1,"Nama Saksi 1");
        }else if(KdSaksi1.getText().trim().equals("")){
            Valid.textKosong(KdSaksi1,"NIP/NPRS Saksi 1");
        }else if(JabatanSaksi1.getText().trim().equals("")){
            Valid.textKosong(JabatanSaksi1,"Jabatan Saksi 1");
        }else if(NamaSaksi2.getText().trim().equals("")){
            Valid.textKosong(NamaSaksi2,"Nama Saksi 2");
        }else if(KdSaksi2.getText().trim().equals("")){
            Valid.textKosong(KdSaksi2,"NIP/NPRS Saksi 2");
        }else if(JabatanSaksi2.getText().trim().equals("")){
            Valid.textKosong(JabatanSaksi2,"Jabatan Saksi 2");
        }else{    
            if(tbObat.getSelectedRow()!= -1){
                Sequel.mengedit("ba_pemusnahan_obat","no_ba=?","no_ba=?,nama_apoteker=?,no_sipa=?,nama_apotek=?,alamat_apotek=?,"
                     + "nama_saksi1=?,nip_saksi1=?,jabatan_saksi1=?,nama_saksi2=?,nip_saksi2=?,jabatan_saksi2=?,tanggal_ba=?,hari_ba=?,tmp_pemusnahan=?",
                     15,new String[]{
                     NoBa.getText(),NamaApoteker.getText(),NoSipa.getText(),NamaApotek.getText(),AlamatApotek.getText(),NamaSaksi1.getText(),
                     KdSaksi1.getText(),JabatanSaksi1.getText(),NamaSaksi2.getText(),KdSaksi2.getText(),JabatanSaksi2.getText(),
                     Valid.SetTgl(TanggalSurat.getSelectedItem()+""),HariBa.getSelectedItem().toString(),TmpPemusnahan.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
                 });
            }
            tampil();
            emptTeks();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            tgl=" surat_skbn.tanggalsurat between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
            if(TCari.getText().trim().equals("")){
                Valid.MyReportqry("rptDataSuratSKBN.jasper","report","::[ Data Surat Keterangan Bebas Narkoba ]::",
                     "select surat_skbn.no_surat,surat_skbn.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien, "+
                     "surat_skbn.tanggalsurat,surat_skbn.kategori,surat_skbn.kd_dokter,dokter.nm_dokter,surat_skbn.keperluan, "+
                     "surat_skbn.opiat,surat_skbn.ganja,surat_skbn.amphetamin,surat_skbn.methamphetamin,surat_skbn.benzodiazepin,surat_skbn.cocain "+                   
                     "from surat_skbn inner join reg_periksa on surat_skbn.no_rawat=reg_periksa.no_rawat "+
                     "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                     "inner join dokter on surat_skbn.kd_dokter=dokter.kd_dokter "+
                     "where "+tgl+"order by surat_skbn.no_surat",param);
            }else{
                Valid.MyReportqry("rptDataSuratSKBN.jasper","report","::[ Data Surat Keterangan Pasien ]::",
                     "select surat_skbn.no_surat,surat_skbn.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien, "+
                     "surat_skbn.tanggalsurat,surat_skbn.kategori,surat_skbn.kd_dokter,dokter.nm_dokter,surat_skbn.keperluan, "+
                     "surat_skbn.opiat,surat_skbn.ganja,surat_skbn.amphetamin,surat_skbn.methamphetamin,surat_skbn.benzodiazepin,surat_skbn.cocain "+                   
                     "from surat_skbn inner join reg_periksa on surat_skbn.no_rawat=reg_periksa.no_rawat "+
                     "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                     "inner join dokter on surat_skbn.kd_dokter=dokter.kd_dokter "+
                     "where "+tgl+"and no_surat like '%"+TCari.getText().trim()+"%' or "+
                     tgl+"and surat_skbn.no_rawat like '%"+TCari.getText().trim()+"%' or "+
                     tgl+"and reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or "+
                     tgl+"and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                     tgl+"and surat_skbn.tanggalsurat like '%"+TCari.getText().trim()+"%' or "+
                     tgl+"and surat_skbn.kategori like '%"+TCari.getText().trim()+"%' "+
                     "order by surat_skbn.no_surat",param);
            }
            
        }
        this.setCursor(Cursor.getDefaultCursor());        
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
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
//            tampil();
//            TCari.setText("");
//        }else{
//            Valid.pindah(evt, BtnCari, TPasien);
//        }
}//GEN-LAST:event_BtnAllKeyPressed
   
                                  
    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
       isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void TanggalSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalSuratActionPerformed

    private void TanggalSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalSuratKeyPressed
//        Valid.pindah(evt,NoSurat,hasil1);
    }//GEN-LAST:event_TanggalSuratKeyPressed

//    new code is here
    public static String getMd5(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void MnCetakSuratSKBN3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSKBN3ActionPerformed
        if(NamaApoteker.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data BA...!!!");
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting"));
                param.put("logo2",Sequel.cariGambar("select all_logo.logo from all_logo where id= '1'"));
                param.put("no_ba",NoBa.getText());
                param.put("nama_apoteker",NamaApoteker.getText());
                param.put("no_sipa",NoSipa.getText());
                param.put("nama_apotek",NamaApotek.getText());
                param.put("alamat_apotek",AlamatApotek.getText());
                param.put("nama_saksi1",NamaSaksi1.getText());
                param.put("nip_saksi1",KdSaksi1.getText());
                param.put("jabatan_saksi1",JabatanSaksi1.getText());
                param.put("nama_saksi2",NamaSaksi2.getText());
                param.put("nip_saksi2",KdSaksi2.getText());
                param.put("jabatan_saksi2",JabatanSaksi2.getText());
                param.put("tanggal_ba",TanggalSurat.getSelectedItem().toString());
                param.put("hari_ba",HariBa.getSelectedItem().toString());
                param.put("tmp_pemusnahan",TmpPemusnahan.getText());
                Valid.MyReportqry("rptBaObat.jasper","report",":: [ BA Pemusnahan Obat ] ::",
                    "select ba_pemusnahan_obat.no_ba,ba_pemusnahan_obat.tanggal_ba,ba_pemusnahan_obat.hari_ba, "+
                    "SUBSTRING_INDEX(SUBSTRING_INDEX(tanggal_ba,'-',1),'-','-1') as tahun, "+
                    "SUBSTRING_INDEX(SUBSTRING_INDEX(tanggal_ba,'-',2),'-','-1') as bulan, "+
                    "SUBSTRING_INDEX(SUBSTRING_INDEX(tanggal_ba,'-',3),'-','-1') as hari, "+
                    "ba_pemusnahan_obat.tmp_pemusnahan, "+
                    "ba_pemusnahan_obat.nama_apoteker,ba_pemusnahan_obat.no_sipa,ba_pemusnahan_obat.nama_apotek, "+
                    "ba_pemusnahan_obat.alamat_apotek,ba_pemusnahan_obat.nama_saksi1,ba_pemusnahan_obat.nip_saksi1, "+
                    "ba_pemusnahan_obat.jabatan_saksi1,ba_pemusnahan_obat.nama_saksi2,ba_pemusnahan_obat.nip_saksi2,ba_pemusnahan_obat.jabatan_saksi2 "+
                    "from ba_pemusnahan_obat where ba_pemusnahan_obat.no_ba='"+NoBa.getText()+"' ",param
                );
                this.setCursor(Cursor.getDefaultCursor());  
       }
    }//GEN-LAST:event_MnCetakSuratSKBN3ActionPerformed

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyReleased

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void NamaApotekerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaApotekerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaApotekerActionPerformed

    private void NoSipaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoSipaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoSipaActionPerformed

    private void NoSipaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSipaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoSipaKeyPressed

    private void NamaApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaApotekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaApotekActionPerformed

    private void NamaApotekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaApotekKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaApotekKeyPressed

    private void AlamatApotekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlamatApotekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlamatApotekActionPerformed

    private void AlamatApotekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatApotekKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlamatApotekKeyPressed

    private void NamaSaksi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaSaksi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaSaksi1ActionPerformed

    private void NamaSaksi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaSaksi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaSaksi1KeyPressed

    private void KdSaksi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KdSaksi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdSaksi1ActionPerformed

    private void KdSaksi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdSaksi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdSaksi1KeyPressed

    private void JabatanSaksi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JabatanSaksi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JabatanSaksi1ActionPerformed

    private void JabatanSaksi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JabatanSaksi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JabatanSaksi1KeyPressed

    private void NamaSaksi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaSaksi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaSaksi2ActionPerformed

    private void NamaSaksi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaSaksi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaSaksi2KeyPressed

    private void KdSaksi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KdSaksi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdSaksi2ActionPerformed

    private void KdSaksi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdSaksi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdSaksi2KeyPressed

    private void JabatanSaksi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JabatanSaksi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JabatanSaksi2ActionPerformed

    private void JabatanSaksi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JabatanSaksi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JabatanSaksi2KeyPressed

    private void HariBaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HariBaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HariBaActionPerformed

    private void HariBaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HariBaKeyPressed
        
    }//GEN-LAST:event_HariBaKeyPressed

    private void NoBaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoBaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoBaActionPerformed

    private void NoBaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoBaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoBaKeyPressed

    private void TmpPemusnahanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TmpPemusnahanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TmpPemusnahanActionPerformed

    private void TmpPemusnahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmpPemusnahanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TmpPemusnahanKeyPressed

    private void BtnDataObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataObatActionPerformed
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DlgObatKadaluarsa listobat=new DlgObatKadaluarsa(null, false);
        listobat.isCek();
        listobat.setSize(internalFrame1.getWidth()-20, internalFrame1.getHeight()-20);
        listobat.setLocationRelativeTo(internalFrame1);
        listobat.setAlwaysOnTop(false);
        listobat.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnDataObatActionPerformed

    private void BtnDataObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDataObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnDataObatKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BaPemusnahanObat dialog = new BaPemusnahanObat(new javax.swing.JFrame(), true);
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
    private widget.TextBox AlamatApotek;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDataObat;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox HariBa;
    private widget.TextBox JabatanSaksi1;
    private widget.TextBox JabatanSaksi2;
    private widget.TextBox KdSaksi1;
    private widget.TextBox KdSaksi2;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnCetakSuratSKBN3;
    private widget.TextBox NamaApotek;
    private widget.TextBox NamaApoteker;
    private widget.TextBox NamaSaksi1;
    private widget.TextBox NamaSaksi2;
    private widget.TextBox NoBa;
    private widget.TextBox NoSipa;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.Tanggal TanggalSurat;
    private widget.TextBox TmpPemusnahan;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            tgl=" ba_pemusnahan_obat.tanggal_ba between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' ";
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select ba_pemusnahan_obat.no_ba, ba_pemusnahan_obat.nama_apoteker,ba_pemusnahan_obat.no_sipa,ba_pemusnahan_obat.nama_apotek, "+
                    "ba_pemusnahan_obat.alamat_apotek,ba_pemusnahan_obat.nama_saksi1,ba_pemusnahan_obat.nip_saksi1,ba_pemusnahan_obat.jabatan_saksi1, "+
                    "ba_pemusnahan_obat.nama_saksi2,ba_pemusnahan_obat.nip_saksi2,ba_pemusnahan_obat.jabatan_saksi2,ba_pemusnahan_obat.tmp_pemusnahan, "
                  + "CONCAT(ba_pemusnahan_obat.hari_ba,', ',ba_pemusnahan_obat.tanggal_ba) as waktu,ba_pemusnahan_obat.hari_ba,ba_pemusnahan_obat.tanggal_ba "+
                    "from ba_pemusnahan_obat where "+tgl+"order by ba_pemusnahan_obat.no_ba");
            }else{
                ps=koneksi.prepareStatement(
                    "select ba_pemusnahan_obat.no_ba, ba_pemusnahan_obat.nama_apoteker,ba_pemusnahan_obat.no_sipa,ba_pemusnahan_obat.nama_apotek, "+
                    "ba_pemusnahan_obat.alamat_apotek,ba_pemusnahan_obat.nama_saksi1,ba_pemusnahan_obat.nip_saksi1,ba_pemusnahan_obat.jabatan_saksi1, "+
                    "ba_pemusnahan_obat.nama_saksi2,ba_pemusnahan_obat.nip_saksi2,ba_pemusnahan_obat.jabatan_saksi2,ba_pemusnahan_obat.tmp_pemusnahan, "
                  + "CONCAT(ba_pemusnahan_obat.hari_ba,', ',ba_pemusnahan_obat.tanggal_ba) as waktu,ba_pemusnahan_obat.hari_ba,ba_pemusnahan_obat.tanggal_ba,ba_pemusnahan_obat.tmp_pemusnahan "+
                    "where "+tgl+"and ba_pemusnahan_obat.no_ba like like '%"+TCari.getText().trim()+"%' or "+
                    tgl+"and ba_pemusnahan_obat.nama_apoteker like '%"+TCari.getText().trim()+"%' or "+
                    tgl+"and ba_pemusnahan_obat.nama_saksi1 like '%"+TCari.getText().trim()+"%' or "+
                    tgl+"and ba_pemusnahan_obat.nama_saksi2 like '%"+TCari.getText().trim()+"%' or "+
                    "order by ba_pemusnahan_obat.no_ba");
            }
            
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9),
                        rs.getString(10),rs.getString(11),rs.getString(12),
                        rs.getString(13),rs.getString(14),rs.getString(15)
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

    public void emptTeks() {
        NamaApoteker.setText("");
        NoSipa.setText("");
        NamaApotek.setText("");
        AlamatApotek.setText("");
        NamaSaksi1.setText("");
        KdSaksi1.setText("");
        JabatanSaksi1.setText("");
        NamaSaksi2.setText("");
        KdSaksi2.setText("");
        JabatanSaksi2.setText("");
        TanggalSurat.setDate(new Date());
        HariBa.setSelectedItem("Senin");
        TmpPemusnahan.setText("");
        autoBA();
        NoBa.requestFocus();
    }

 
    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            NoBa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            NamaApoteker.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            NoSipa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            NamaApotek.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            AlamatApotek.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            NamaSaksi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            KdSaksi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            JabatanSaksi1.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            NamaSaksi2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            KdSaksi2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            JabatanSaksi2.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            TmpPemusnahan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
//            Valid.SetTgl(TanggalSurat,tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            HariBa.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Valid.SetTgl(TanggalSurat,tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
        }
    }

//    public void setNoRm(String norwt,String norm,String namapasien,String kodedokter,String namadokter,Date tgl1, Date tgl2) {
//        NamaApoteker.setText(norwt);
//        TCari.setText(norwt);
//        DTPCari1.setDate(tgl1);
//        DTPCari2.setDate(tgl2);
//        ChkInput.setSelected(true);
//        isForm();
//        autoBA();
//    }
    
//    private void isRawat() {
//         Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat='"+NamaApoteker.getText()+"' ",TNoRM);
//    }

//    private void isPsien() {
//        Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
//    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,185));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    private void autoBA() {
           Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(ba_pemusnahan_obat.no_ba,3),signed)),0) from ba_pemusnahan_obat where ba_pemusnahan_obat.tanggal_ba='"+Valid.SetTgl(TanggalSurat.getSelectedItem()+"")+"' ",
                  "BA"+TanggalSurat.getSelectedItem().toString().substring(6,10)+TanggalSurat.getSelectedItem().toString().substring(3,5)+TanggalSurat.getSelectedItem().toString().substring(0,2),3,NoBa);
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getsurat_bebas_narkoba());
        BtnHapus.setEnabled(akses.getsurat_bebas_narkoba());
        BtnEdit.setEnabled(akses.getsurat_bebas_narkoba());
    }
}
