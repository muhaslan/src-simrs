/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPegawai;
import kepegawaian.DlgCariPetugas;


/**
 *
 * @author perpustakaan
 */
public final class RMIdentitasBayi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;    
//    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariPegawai petugas=new DlgCariPegawai(null,false);
    private String finger="";
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMIdentitasBayi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
//        setSize(628,674);

        tabMode=new DefaultTableModel(null,new Object[]{
            // sembunyi index 2
            "No. Rawat","No. Rekam Medis","Nama Pasien","Tanggal Lahir","kd_pegawai","Nama Pegawai","Nama Ibu","Nama Ayah","Nama Bayi",
            "Jenis Kelamin","Tgl Lahir Bayi","Jenis Persalinan","Warna Kulit","Berat Badan","Panjang","Lingkar Kepala",
            "Lingkar Data","Lingkar Perut","Lingkar Lengan Atas Bawah"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(65);
            }else if(i==2){
//                column.setPreferredWidth(160);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(100);
            }else if(i==7){
                column.setPreferredWidth(90);
            }else if(i==8){
                column.setPreferredWidth(100);
            }else if(i==9){
                column.setPreferredWidth(100);
            }else if(i==10){
                column.setPreferredWidth(100);
            }else if(i==11){
                column.setPreferredWidth(100);
            }else if(i==12){
                column.setPreferredWidth(100);
            }else if(i==13){
                column.setPreferredWidth(100);
            }else if(i==14){
                column.setPreferredWidth(100);
            }else if(i==15){
                column.setPreferredWidth(100);
            }else if(i==16){
                column.setPreferredWidth(100);
            }else if(i==17){
                column.setPreferredWidth(100);
            }else if(i==18){
                column.setPreferredWidth(100);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        NIP.setDocument(new batasInput((byte)20).getKata(NIP));
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){                   
                    NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                }  
                NIP.requestFocus();
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
        
        ChkInput.setSelected(false);
        isForm();
        Tanggal.setVisible(false);
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
        MnPenilaianLanjutanRisikoJatuh = new javax.swing.JMenuItem();
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
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel18 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        TglLahirBayi = new widget.Tanggal();
        label1 = new widget.Label();
        NamaIbu = new widget.TextBox();
        label2 = new widget.Label();
        NamaAyah = new widget.TextBox();
        label3 = new widget.Label();
        NamaBayi = new widget.TextBox();
        label4 = new widget.Label();
        JenisKelamin = new widget.ComboBox();
        label5 = new widget.Label();
        JenisPersalinan = new widget.TextBox();
        label6 = new widget.Label();
        WarnaKulit = new widget.TextBox();
        label7 = new widget.Label();
        BeratBadan = new widget.TextBox();
        label8 = new widget.Label();
        Panjang = new widget.TextBox();
        label9 = new widget.Label();
        LingkarKepala = new widget.TextBox();
        label10 = new widget.Label();
        LingkarDada = new widget.TextBox();
        label11 = new widget.Label();
        LingkarPerut = new widget.TextBox();
        label12 = new widget.Label();
        LingkarLengan = new widget.TextBox();
        Tanggal = new widget.Tanggal();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPenilaianLanjutanRisikoJatuh.setBackground(new java.awt.Color(255, 255, 254));
        MnPenilaianLanjutanRisikoJatuh.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPenilaianLanjutanRisikoJatuh.setForeground(new java.awt.Color(50, 50, 50));
        MnPenilaianLanjutanRisikoJatuh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPenilaianLanjutanRisikoJatuh.setText("Formulir Penilaian Lanjutan Risiko Jatuh Dewasa");
        MnPenilaianLanjutanRisikoJatuh.setName("MnPenilaianLanjutanRisikoJatuh"); // NOI18N
        MnPenilaianLanjutanRisikoJatuh.setPreferredSize(new java.awt.Dimension(290, 26));
        MnPenilaianLanjutanRisikoJatuh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPenilaianLanjutanRisikoJatuhActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPenilaianLanjutanRisikoJatuh);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Identitas Bayi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(LCount);

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

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-10-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-10-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
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

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelGlass9.add(BtnAll);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 466));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

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

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 20));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 20));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 80, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(84, 10, 136, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(336, 10, 285, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(222, 10, 112, 23);

        jLabel16.setText("Tgl. Lahir Bayi :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(470, 110, 80, 23);

        jLabel18.setText("Dokter/Bidan Penolong :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(10, 40, 130, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        NIP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NIPKeyPressed(evt);
            }
        });
        FormInput.add(NIP);
        NIP.setBounds(150, 40, 94, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        NamaPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaPetugasActionPerformed(evt);
            }
        });
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(250, 40, 180, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("ALt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(430, 40, 28, 23);

        jLabel8.setText("Tgl.Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(625, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(689, 10, 100, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 70, 810, 1);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 170, 810, 1);

        TglLahirBayi.setForeground(new java.awt.Color(50, 70, 50));
        TglLahirBayi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-10-2023 00:06:56" }));
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
        TglLahirBayi.setBounds(560, 110, 143, 23);

        label1.setText("Nama Ibu :");
        label1.setName("label1"); // NOI18N
        FormInput.add(label1);
        label1.setBounds(10, 80, 70, 23);

        NamaIbu.setName("NamaIbu"); // NOI18N
        FormInput.add(NamaIbu);
        NamaIbu.setBounds(90, 80, 160, 23);

        label2.setText("Jenis Kelamin :");
        label2.setName("label2"); // NOI18N
        FormInput.add(label2);
        label2.setBounds(250, 110, 80, 23);

        NamaAyah.setName("NamaAyah"); // NOI18N
        FormInput.add(NamaAyah);
        NamaAyah.setBounds(340, 80, 160, 23);

        label3.setText("Nama Bayi :");
        label3.setName("label3"); // NOI18N
        FormInput.add(label3);
        label3.setBounds(10, 110, 70, 23);

        NamaBayi.setName("NamaBayi"); // NOI18N
        FormInput.add(NamaBayi);
        NamaBayi.setBounds(90, 110, 160, 23);

        label4.setText("Nama Ayah :");
        label4.setName("label4"); // NOI18N
        FormInput.add(label4);
        label4.setBounds(260, 80, 70, 23);

        JenisKelamin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laki-Laki", "Perempuan" }));
        JenisKelamin.setName("JenisKelamin"); // NOI18N
        FormInput.add(JenisKelamin);
        JenisKelamin.setBounds(340, 110, 120, 23);

        label5.setText("Jenis Persalinan :");
        label5.setName("label5"); // NOI18N
        FormInput.add(label5);
        label5.setBounds(10, 140, 90, 23);

        JenisPersalinan.setName("JenisPersalinan"); // NOI18N
        FormInput.add(JenisPersalinan);
        JenisPersalinan.setBounds(110, 140, 160, 23);

        label6.setText("Warna Kulit :");
        label6.setName("label6"); // NOI18N
        FormInput.add(label6);
        label6.setBounds(10, 180, 90, 23);

        WarnaKulit.setName("WarnaKulit"); // NOI18N
        FormInput.add(WarnaKulit);
        WarnaKulit.setBounds(110, 180, 120, 23);

        label7.setText("Berat Badan :");
        label7.setName("label7"); // NOI18N
        FormInput.add(label7);
        label7.setBounds(10, 210, 90, 23);

        BeratBadan.setName("BeratBadan"); // NOI18N
        FormInput.add(BeratBadan);
        BeratBadan.setBounds(110, 210, 120, 23);

        label8.setText("Panjang :");
        label8.setName("label8"); // NOI18N
        FormInput.add(label8);
        label8.setBounds(10, 240, 90, 23);

        Panjang.setName("Panjang"); // NOI18N
        FormInput.add(Panjang);
        Panjang.setBounds(110, 240, 120, 23);

        label9.setText("Lingkar Kepala :");
        label9.setName("label9"); // NOI18N
        FormInput.add(label9);
        label9.setBounds(10, 270, 90, 23);

        LingkarKepala.setName("LingkarKepala"); // NOI18N
        FormInput.add(LingkarKepala);
        LingkarKepala.setBounds(110, 270, 120, 23);

        label10.setText("Lingkar Dada :");
        label10.setName("label10"); // NOI18N
        FormInput.add(label10);
        label10.setBounds(220, 180, 90, 23);

        LingkarDada.setName("LingkarDada"); // NOI18N
        FormInput.add(LingkarDada);
        LingkarDada.setBounds(320, 180, 120, 23);

        label11.setText("Lingkar Perut :");
        label11.setName("label11"); // NOI18N
        FormInput.add(label11);
        label11.setBounds(220, 210, 90, 23);

        LingkarPerut.setName("LingkarPerut"); // NOI18N
        FormInput.add(LingkarPerut);
        LingkarPerut.setBounds(320, 210, 120, 23);

        label12.setText("Lingkar Lengan Kiri Atas  :");
        label12.setName("label12"); // NOI18N
        FormInput.add(label12);
        label12.setBounds(230, 240, 140, 23);

        LingkarLengan.setName("LingkarLengan"); // NOI18N
        FormInput.add(LingkarLengan);
        LingkarLengan.setBounds(380, 240, 120, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-10-2023 02:09:00" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TanggalActionPerformed(evt);
            }
        });
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(470, 40, 143, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
//            isRawat();
//            isPsien();
//        }else{            
//            Valid.pindah(evt,TCari,Tanggal);
//        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
//        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
//            Valid.textKosong(TNoRw,"pasien");
//        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
//            Valid.textKosong(NIP,"Petugas");
//        }else if(HasilSkrining.getText().trim().equals("")){
//            Valid.textKosong(HasilSkrining,"Hasil Skrining");
//        }else if(Saran.getText().trim().equals("")){
//            Valid.textKosong(Saran,"Saran");
//        }else{
//            if(Sequel.menyimpantf("penilaian_lanjutan_resiko_jatuh_dewasa","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",18,new String[]{
//                TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Jam.getSelectedItem()+":"+Menit.getSelectedItem()+":"+Detik.getSelectedItem(),
//                SkalaResiko1.getSelectedItem().toString(),NilaiResiko1.getText(),SkalaResiko2.getSelectedItem().toString(),NilaiResiko2.getText(),
//                SkalaResiko3.getSelectedItem().toString(),NilaiResiko3.getText(),SkalaResiko4.getSelectedItem().toString(),NilaiResiko4.getText(), 
//                SkalaResiko5.getSelectedItem().toString(),NilaiResiko5.getText(),SkalaResiko6.getSelectedItem().toString(),NilaiResiko6.getText(),
//                NilaiResikoTotal.getText(),HasilSkrining.getText(),Saran.getText(),NIP.getText()
//            })==true){
//                tampil();
//                emptTeks();
//            }  
//        }
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else if(NamaIbu.getText().equals("")){
            Valid.textKosong(NamaIbu,"Nama Ibu");
        }else if(NamaAyah.getText().equals("")){
            Valid.textKosong(NamaAyah,"Nama Ayah");
        }else if(NamaBayi.getText().equals("")){
            Valid.textKosong(NamaBayi,"Nama Bayi");
        }else if(JenisPersalinan.getText().equals("")){
            Valid.textKosong(JenisPersalinan,"Jenis Persalinan");
        }else if(WarnaKulit.getText().equals("")){
            Valid.textKosong(WarnaKulit,"Warna Kulit");
        }else if(BeratBadan.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(BeratBadan,"Berat Badan");
        }else if(Panjang.getText().equals("")){
            Valid.textKosong(Panjang,"Panjang");
        }else if(LingkarKepala.getText().equals("")){
            Valid.textKosong(LingkarKepala,"Lingkar Kepala");
        }else if(LingkarDada.getText().equals("")){
            Valid.textKosong(LingkarDada,"Lingkar Dada");
        }else if(LingkarPerut.getText().equals("")){
            Valid.textKosong(LingkarPerut,"Lingkar Perut");
        }else if(LingkarLengan.getText().equals("")){
            Valid.textKosong(LingkarLengan,"Lingkar Lengan");
        }else{
            if(Sequel.menyimpantf("identitas_bayi","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",18,new String[]{
                TNoRw.getText(),TNoRM.getText(),NIP.getText(),NamaPetugas.getText(),NamaIbu.getText(),NamaAyah.getText(),
                NamaBayi.getText(),JenisKelamin.getSelectedItem().toString(),
                Valid.SetTgl(TglLahirBayi.getSelectedItem()+"")+" "+TglLahirBayi.getSelectedItem().toString().substring(11,19),
                JenisPersalinan.getText(),WarnaKulit.getText(),BeratBadan.getText(),Panjang.getText(),LingkarKepala.getText(),
                LingkarDada.getText(),LingkarPerut.getText(),LingkarLengan.getText(),
                Valid.SetTgl(Tanggal.getSelectedItem()+"")+" "+Tanggal.getSelectedItem().toString().substring(11,19),
            })==true){
                tampil();
                emptTeks();
            } 
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            BtnSimpanActionPerformed(null);
//        }else{
//            Valid.pindah(evt,Saran,BtnBatal);
//        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()>-1){
            if(akses.getkode3().equals("Admin Utama")){
                hapus();
            }else{
                if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }   
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"pasien");
        }else if(NIP.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NIP,"Petugas");
        }else if(NamaIbu.getText().equals("")){
            Valid.textKosong(NamaIbu,"Nama Ibu");
        }else if(NamaAyah.getText().equals("")){
            Valid.textKosong(NamaAyah,"Nama Ayah");
        }else if(NamaBayi.getText().equals("")){
            Valid.textKosong(NamaBayi,"Nama Bayi");
        }else if(JenisPersalinan.getText().equals("")){
            Valid.textKosong(JenisPersalinan,"Jenis Persalinan");
        }else if(WarnaKulit.getText().equals("")){
            Valid.textKosong(WarnaKulit,"Warna Kulit");
        }else if(BeratBadan.getText().trim().equals("")||NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(BeratBadan,"Berat Badan");
        }else if(Panjang.getText().equals("")){
            Valid.textKosong(Panjang,"Panjang");
        }else if(LingkarKepala.getText().equals("")){
            Valid.textKosong(LingkarKepala,"Lingkar Kepala");
        }else if(LingkarDada.getText().equals("")){
            Valid.textKosong(LingkarDada,"Lingkar Dada");
        }else if(LingkarPerut.getText().equals("")){
            Valid.textKosong(LingkarPerut,"Lingkar Perut");
        }else if(LingkarLengan.getText().equals("")){
            Valid.textKosong(LingkarLengan,"Lingkar Lengan");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode3().equals("Admin Utama")){
                    ganti();
                }else{
                    if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString())){
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

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        petugas.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
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
            if(TCari.getText().trim().equals("")){
                Valid.MyReportqry("rptLanjutanRisikoJatuhDewasa.jasper","report","::[ Data Penilaian Lanjutan Risiko Jatuh Dewasa ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,penilaian_lanjutan_resiko_jatuh_dewasa.tanggal,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala1,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai1,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala2,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai2,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala3,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai3,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala4,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai4,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala5,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai5,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala6,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai6,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_totalnilai,penilaian_lanjutan_resiko_jatuh_dewasa.hasil_skrining,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.saran,penilaian_lanjutan_resiko_jatuh_dewasa.nip,petugas.nama "+
                    "from penilaian_lanjutan_resiko_jatuh_dewasa inner join reg_periksa on penilaian_lanjutan_resiko_jatuh_dewasa.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on penilaian_lanjutan_resiko_jatuh_dewasa.nip=petugas.nip where "+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' "+
                    "order by penilaian_lanjutan_resiko_jatuh_dewasa.tanggal",param);
            }else{
                Valid.MyReportqry("rptLanjutanRisikoJatuhDewasa.jasper","report","::[ Data Penilaian Lanjutan Risiko Jatuh Dewasa ]::",
                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,penilaian_lanjutan_resiko_jatuh_dewasa.tanggal,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala1,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai1,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala2,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai2,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala3,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai3,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala4,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai4,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala5,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai5,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala6,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai6,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_totalnilai,penilaian_lanjutan_resiko_jatuh_dewasa.hasil_skrining,"+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.saran,penilaian_lanjutan_resiko_jatuh_dewasa.nip,petugas.nama "+
                    "from penilaian_lanjutan_resiko_jatuh_dewasa inner join reg_periksa on penilaian_lanjutan_resiko_jatuh_dewasa.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on penilaian_lanjutan_resiko_jatuh_dewasa.nip=petugas.nip where "+
                    "penilaian_lanjutan_resiko_jatuh_dewasa.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+" 00:00:00' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+" 23:59:59' and "+
                    "(reg_periksa.no_rawat like '%"+TCari.getText().trim()+"%' or pasien.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' "+
                    "or penilaian_lanjutan_resiko_jatuh_dewasa.nip like '%"+TCari.getText().trim()+"%' or petugas.nama like '%"+TCari.getText().trim()+"%') "+
                    "order by penilaian_lanjutan_resiko_jatuh_dewasa.tanggal ",param);
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void NIPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NIPKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
//            Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?",NamaPetugas,NIP.getText());
//        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
//            Detik.requestFocus();
//        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//            //GCS.requestFocus();
//        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
//            btnPetugasActionPerformed(null);
//        }
    }//GEN-LAST:event_NIPKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
//        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        //Valid.pindah(evt,Detik,GCS);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void MnPenilaianLanjutanRisikoJatuhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianLanjutanRisikoJatuhActionPerformed
//        if(tbObat.getSelectedRow()>-1){
//            Map<String, Object> param = new HashMap<>();
//            param.put("namars",akses.getnamars());
//            param.put("alamatrs",akses.getalamatrs());
//            param.put("kotars",akses.getkabupatenrs());
//            param.put("propinsirs",akses.getpropinsirs());
//            param.put("kontakrs",akses.getkontakrs());
//            param.put("emailrs",akses.getemailrs());   
//            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
//            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),21).toString());
//            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),22).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),21).toString():finger)+"\n"+Tanggal.getSelectedItem());
//            Valid.MyReportqry("rptFormulirPenilaianLanjutanRisikoJatuhDewasa.jasper","report","::[ Formulir Penilaian Lanjutan Risiko Jatuh Dewasa ]::",
//                    "select reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.tgl_lahir,penilaian_lanjutan_resiko_jatuh_dewasa.tanggal,"+
//                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala1,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai1,"+
//                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala2,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai2,"+
//                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala3,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai3,"+
//                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala4,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai4,"+
//                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala5,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai5,"+
//                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_skala6,penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_nilai6,"+
//                    "penilaian_lanjutan_resiko_jatuh_dewasa.penilaian_jatuhmorse_totalnilai,penilaian_lanjutan_resiko_jatuh_dewasa.hasil_skrining,"+
//                    "penilaian_lanjutan_resiko_jatuh_dewasa.saran,penilaian_lanjutan_resiko_jatuh_dewasa.nip,petugas.nama "+
//                    "from penilaian_lanjutan_resiko_jatuh_dewasa inner join reg_periksa on penilaian_lanjutan_resiko_jatuh_dewasa.no_rawat=reg_periksa.no_rawat "+
//                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
//                    "inner join petugas on penilaian_lanjutan_resiko_jatuh_dewasa.nip=petugas.nip where reg_periksa.no_rawat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'",param);
//        }
    }//GEN-LAST:event_MnPenilaianLanjutanRisikoJatuhActionPerformed

    private void TglLahirBayiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TglLahirBayiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglLahirBayiActionPerformed

    private void TglLahirBayiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglLahirBayiKeyPressed
        //Valid.pindah(evt,Rencana,Informasi);
    }//GEN-LAST:event_TglLahirBayiKeyPressed

    private void NamaPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaPetugasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaPetugasActionPerformed

    private void TanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalActionPerformed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMIdentitasBayi dialog = new RMIdentitasBayi(new javax.swing.JFrame(), true);
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
    private widget.TextBox BeratBadan;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox JenisKelamin;
    private widget.TextBox JenisPersalinan;
    private widget.Label LCount;
    private widget.TextBox LingkarDada;
    private widget.TextBox LingkarKepala;
    private widget.TextBox LingkarLengan;
    private widget.TextBox LingkarPerut;
    private javax.swing.JMenuItem MnPenilaianLanjutanRisikoJatuh;
    private widget.TextBox NIP;
    private widget.TextBox NamaAyah;
    private widget.TextBox NamaBayi;
    private widget.TextBox NamaIbu;
    private widget.TextBox NamaPetugas;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox Panjang;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.Tanggal Tanggal;
    private widget.TextBox TglLahir;
    private widget.Tanggal TglLahirBayi;
    private widget.TextBox WarnaKulit;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel4;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private widget.Label label1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label2;
    private widget.Label label3;
    private widget.Label label4;
    private widget.Label label5;
    private widget.Label label6;
    private widget.Label label7;
    private widget.Label label8;
    private widget.Label label9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables
    
    private void tampil(){
        Valid.tabelKosong(tabMode);
        try {
            ps=koneksi.prepareStatement(
                "select identitas_bayi.no_rawat,identitas_bayi.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,identitas_bayi.kd_pegawai,"+
                "identitas_bayi.nm_pegawai,identitas_bayi.nm_ibu,identitas_bayi.nm_ayah,identitas_bayi.nm_bayi, "+
                "identitas_bayi.jk,identitas_bayi.tgl_lahir_bayi,identitas_bayi.jns_persalinan,identitas_bayi.warna_kulit, "+
                "identitas_bayi.berat_badan,identitas_bayi.panjang,identitas_bayi.lingkar_kepala,identitas_bayi.lingkar_dada,"+
                "identitas_bayi.lingkar_perut,identitas_bayi.lingkar_lengan,identitas_bayi.tanggal "+
                "from identitas_bayi inner join pasien on identitas_bayi.no_rkm_medis=pasien.no_rkm_medis where "+
                "identitas_bayi.tanggal between ? and ? "+
                (TCari.getText().trim().equals("")?"":"and (identitas_bayi.no_rawat like ? or identitas_bayi.no_rkm_medis like ? or pasien.nm_pasien like ? or identitas_bayi.nm_pegawai like ?)")+
                " order by identitas_bayi.tanggal");
            
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
                          rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),
                          rs.getString("kd_pegawai"),rs.getString("nm_pegawai"),rs.getString("nm_ibu"),rs.getString("nm_ayah"),
                          rs.getString("nm_bayi"),rs.getString("jk"),rs.getString("tgl_lahir_bayi"),rs.getString("jns_persalinan"),
                          rs.getString("warna_kulit"),rs.getString("berat_badan"),rs.getString("panjang"),rs.getString("lingkar_kepala"),
                          rs.getString("lingkar_dada"),rs.getString("lingkar_perut"),rs.getString("lingkar_lengan")
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
        NIP.setText("");
        NamaPetugas.setText("");
        NamaIbu.setText("");
        NamaAyah.setText("");
        NamaBayi.setText("");
        JenisKelamin.setSelectedIndex(0);
        TglLahirBayi.setDate(new Date());
        JenisPersalinan.setText("");
        WarnaKulit.setText("");
        BeratBadan.setText("");
        Panjang.setText("");
        LingkarKepala.setText("");
        LingkarDada.setText("");
        LingkarPerut.setText("");
        LingkarLengan.setText("");
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            NIP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            NamaPetugas.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            NamaIbu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            NamaAyah.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            NamaBayi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            JenisKelamin.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            Valid.SetTgl2(TglLahirBayi,tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            JenisPersalinan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            WarnaKulit.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            BeratBadan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            Panjang.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            LingkarKepala.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            LingkarDada.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            LingkarPerut.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            LingkarLengan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
        }
    }
    private void isRawat() {
         Sequel.cariIsi("select pasien.tgl_lahir from pasien where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",TglLahir);
    }

    private void isPsien() {
        Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
        Sequel.cariIsi("select date_format(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=? ",TglLahir,TNoRM.getText());
    }
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='"+norwt+"'", DTPCari1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien();
        ChkInput.setSelected(true);
        isForm();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            if(internalFrame1.getHeight()>165){
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,325));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }else{
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,internalFrame1.getHeight()-20));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_lanjutan_resiko_jatuh_dewasa());
        BtnHapus.setEnabled(akses.getpenilaian_lanjutan_resiko_jatuh_dewasa());
        BtnEdit.setEnabled(akses.getpenilaian_lanjutan_resiko_jatuh_dewasa());
        BtnPrint.setEnabled(akses.getpenilaian_lanjutan_resiko_jatuh_dewasa()); 
        if(akses.getjml2()>=1){
            NIP.setEditable(false);
            btnPetugas.setEnabled(false);
            NIP.setText(akses.getkode2());
            Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?", NamaPetugas,NIP.getText());
            if(NamaPetugas.getText().equals("")){
                NIP.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        }            
    }

    private void ganti() {
        Sequel.mengedit("identitas_bayi","no_rawat=?",
        "no_rawat=?,no_rkm_medis=?,kd_pegawai=?,nm_pegawai=?,nm_ibu=?,nm_ayah=?,nm_bayi=?,"+
        "jk=?,tgl_lahir_bayi=?,jns_persalinan=?,warna_kulit=?,berat_badan=?,panjang=?,lingkar_kepala=?,"+
        "lingkar_dada=?,lingkar_perut=?,lingkar_lengan=?"
         ,
    18,new String[]{
            TNoRw.getText(),TNoRM.getText(),NIP.getText(),NamaPetugas.getText(),NamaIbu.getText(),NamaAyah.getText(),
            NamaBayi.getText(),JenisKelamin.getSelectedItem().toString(),
            Valid.SetTgl(TglLahirBayi.getSelectedItem()+"")+" "+TglLahirBayi.getSelectedItem().toString().substring(11,19),
            JenisPersalinan.getText(),WarnaKulit.getText(),BeratBadan.getText(),Panjang.getText(),LingkarKepala.getText(),
            LingkarDada.getText(),LingkarPerut.getText(),LingkarLengan.getText(),
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        });
        if(tabMode.getRowCount()!=0){tampil();}
        emptTeks();
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from identitas_bayi where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tampil();
            emptTeks();
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
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
    
    public void setNoRm(String norwt,Date tgl2,String norm,String nmpasien) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TCari.setText(norwt);
        Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='"+norwt+"'", DTPCari1);
        DTPCari2.setDate(tgl2);    
        isRawat(); 
        tampil();
    }
}
