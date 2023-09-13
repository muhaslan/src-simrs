package rekammedis;

import bridging.BPJSCekKartu;
import bridging.BPJSCekNIK2;
import bridging.BPJSCekRujukanKartuPCare;
import bridging.BPJSCekRujukanKartuRS;
import bridging.CoronaPasien;
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
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
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
import kepegawaian.DlgCariPetugas;
import simrskhanza.DlgCatatan;
import simrskhanza.DlgIGD;
import simrskhanza.DlgPasien;
import simrskhanza.DlgReg;

/**
 *
 * @author dosen
 */
public class RMSKriningPasien extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabMode2,tabMode3;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps1;
    private ResultSet rs,rs1;
    private int i=0;
    private DlgPasien pasien=new DlgPasien(null,false);
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private DlgCariPetugas petugas2=new DlgCariPetugas(null,false);
    private DlgCatatan catatan=new DlgCatatan(null,false);
//    private String pengurutan="",bulan="",tahun="",awalantahun="",awalanbulan="",posisitahun="",pilihan="",nokartu="",finger="",validasiregistrasi=Sequel.cariIsi("select wajib_closing_kasir from set_validasi_registrasi"),
//            validasicatatan=Sequel.cariIsi("select set_validasi_catatan.tampilkan_catatan from set_validasi_catatan");
    private Date tanggal;
    private LocalDate today=LocalDate.now();
    private LocalDate birthday;
    private Period p;
    

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMSKriningPasien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        // tab pemeriksaan telepon
        tabMode=new DefaultTableModel(null, new Object[]{
            "No. Rekam Medis","Nama Pasien","Tanggal Lahir","Umur","Alamat","Jenis Kelamin","Tanggal Skrining","Jam Skrining","KD Petugas","Petugas Skrining","KD Penerima Telepon","Penerima Telepon",
            "Nama Penelepon","No. Penelepon","GCS","Suhu","TD","Nadi","Pernafasan","Saturasi","Diagnosa","Rekomendasi"
        }){
          @Override public boolean isCellEditable(int rowIndex, int collIndex){return false;}  
        };
        tbPasienTelpon.setModel(tabMode);
        
        tbPasienTelpon.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasienTelpon.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 22; i++) {
            TableColumn column = tbPasienTelpon.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(150);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(100);
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
            }else if(i==7){
                column.setPreferredWidth(100);
//                column.setMinWidth(0);
//                column.setMaxWidth(0);
            }else if(i==8){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==9){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==10){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==11){
                column.setPreferredWidth(130);
            }else if(i==12){
                column.setPreferredWidth(120);
            }else if(i==13){
                column.setPreferredWidth(120);
            }else if(i==14){
                column.setPreferredWidth(50);
            }else if(i==15){
                column.setPreferredWidth(50);
            }else if(i==16){
                column.setPreferredWidth(50);
            }else if(i==17){
                column.setPreferredWidth(50);
            }else if(i==18){
                column.setPreferredWidth(80);
            }else if(i==19){
                column.setPreferredWidth(80);
            }else if(i==20){
                column.setPreferredWidth(120);
            }else if(i==21){
                column.setPreferredWidth(120);
            }
        }
        tbPasienTelpon.setDefaultRenderer(Object.class, new WarnaTable());
        
        // tab pemeriksaan pasien rujuk
        tabMode2=new DefaultTableModel(null, new Object[]{
            "Nama Pasien","Tanggal Lahir","Umur","Alamat","Jenis Kelamin","Tanggal Skrining","Petugas Skrining"
        }){
          @Override public boolean isCellEditable(int rowIndex, int collIndex){return false;}  
        };
        tbPasienRujuk.setModel(tabMode2);
        
        tbPasienRujuk.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasienRujuk.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbPasienRujuk.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(65);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(150);
            }else if(i==4){
                column.setPreferredWidth(60);
            }else if(i==5){
                column.setPreferredWidth(140);
            }else if(i==6){
                column.setPreferredWidth(30);
            }
        }
        tbPasienTelpon.setDefaultRenderer(Object.class, new WarnaTable());
        
        // tab pemeriksaan datang dari rumah/puskesmas
        tabMode3=new DefaultTableModel(null, new Object[]{
            "Nama Pasien","Tanggal Lahir","Umur","Alamat","Jenis Kelamin","Tanggal Skrining","Petugas Skrining"
        }){
          @Override public boolean isCellEditable(int rowIndex, int collIndex){return false;}  
        };
        tbPasienDatang.setModel(tabMode3);
        
        tbPasienDatang.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPasienDatang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbPasienDatang.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(65);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(150);
            }else if(i==4){
                column.setPreferredWidth(60);
            }else if(i==5){
                column.setPreferredWidth(140);
            }else if(i==6){
                column.setPreferredWidth(30);
            }
        }
        tbPasienDatang.setDefaultRenderer(Object.class, new WarnaTable());
        
//        tabMode=new DefaultTableModel(null,new Object[]{
//                "Tgl.Skrining","Jam Skrining","No.R.M.","Nama Pasien","Tgl.Lahir","Ibu Kandung","J.K.","Geriatri",
//                "Kesadaran","Pernafasan","Nyeri Dada","Skala Nyeri","Keputusan","NIP","Nama Petugas"
//            }){
//              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
//        };
//        tbObat.setModel(tabMode);
//
//        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
//        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
//        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//


//        TNoRM.setDocument(new batasInput((byte)17).getKata(TNoRM));
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
        
//        ChkInput.setSelected(false);
//        isForm();
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pasien.getTable().getSelectedRow()!= -1){  
//                    TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),1).toString());
                    TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),2).toString());   
//                    JK.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),4).toString().replaceAll("L","LAKI-LAKI").replaceAll("P","PEREMPUAN")); 
//                    Lahir.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),6).toString()); 
//                    Umur.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),7).toString()); 
                }  
                if(pasien.getTable2().getSelectedRow()!= -1){  
//                    TNoRM.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(),1).toString());
                    TNoRM.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(),2).toString());   
//                    JK.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(),4).toString().replaceAll("L","LAKI-LAKI").replaceAll("P","PEREMPUAN"));
//                    Lahir.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(),6).toString());  
//                    Umur.setText(pasien.getTable2().getValueAt(pasien.getTable2().getSelectedRow(),7).toString()); 
                }  
                if(pasien.getTable3().getSelectedRow()!= -1){  
//                    TNoRM.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(),1).toString());
                    TNoRM.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(),2).toString());   
//                    JK.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(),4).toString().replaceAll("L","LAKI-LAKI").replaceAll("P","PEREMPUAN"));
//                    Lahir.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(),6).toString());  
//                    Umur.setText(pasien.getTable3().getValueAt(pasien.getTable3().getSelectedRow(),7).toString()); 
                }  
//                TNoRM.requestFocus();
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
        
        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pasien.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });    
        
        pasien.getTable2().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pasien.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        }); 
        
        pasien.getTable3().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pasien.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){                   
                    KdPetugas1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NmPetugas1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),2).toString());
                }  
                KdPetugas1.requestFocus();
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
        
        petugas2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas2.getTable().getSelectedRow()!= -1){                   
                    KdPetugas.setText(petugas2.getTable().getValueAt(petugas2.getTable().getSelectedRow(),0).toString());
                    NmPetugas.setText(petugas2.getTable().getValueAt(petugas2.getTable().getSelectedRow(),2).toString());
                }  
                KdPetugas.requestFocus();
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
        
        pasien.addWindowListener(new WindowListener(){
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {}

            @Override
            public void windowClosed(WindowEvent e) {
               if(pasien.getTable().getSelectedRow()!=-1){
                   TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),1).toString());
                   TPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),2).toString());
                   Valid.SetTgl(DTPLahir1,pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),6).toString());
                   Alamat.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),8).toString());
                   switch(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),4).toString()){
                       case("L"):
                           JK.setSelectedItem("Laki-Laki");
                           break;
                       case("P"):
                           JK.setSelectedItem("Perempuan");
                           break;
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
        
        jam();
        
        ChkInput.setSelected(false);
        ChkInput1.setSelected(false);
        ChkInput3.setSelected(false);
        ChkLainnya.setSelected(false);
        isForm();
        isForm2();
        isForm3();
        isCttn();
        
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
        MnLembarSkriningRalan = new javax.swing.JMenuItem();
        MnPDFSkriningRalan = new javax.swing.JMenuItem();
        ppPasienCorona = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnEdit = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass7 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRM = new widget.TextBox();
        DTPReg = new widget.Tanggal();
        jLabel9 = new widget.Label();
        CmbJam = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        CmbDetik = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        jLabel11 = new widget.Label();
        jLabel5 = new widget.Label();
        jLabel7 = new widget.Label();
        Alamat = new widget.TextBox();
        jLabel21 = new widget.Label();
        BtnPtg1 = new widget.Button();
        jLabel27 = new widget.Label();
        KdPetugas1 = new widget.TextBox();
        NmPetugas1 = new widget.TextBox();
        DTPLahir1 = new widget.Tanggal();
        jLabel29 = new widget.Label();
        TUmurTh = new widget.TextBox();
        jLabel45 = new widget.Label();
        TUmurBl = new widget.TextBox();
        jLabel30 = new widget.Label();
        TUmurHr = new widget.TextBox();
        jLabel46 = new widget.Label();
        CPasien = new widget.Button();
        JK = new widget.ComboBox();
        TPasien = new widget.TextBox();
        TabSkrining = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        jPanel1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbPasienTelpon = new widget.Table();
        PanelInput1 = new javax.swing.JPanel();
        FormInput2 = new widget.PanelBiasa();
        jLabel8 = new widget.Label();
        NmPenelpon = new widget.TextBox();
        jLabel20 = new widget.Label();
        KdPetugas = new widget.TextBox();
        NmPetugas = new widget.TextBox();
        BtnPtg = new widget.Button();
        scrollPane1 = new widget.ScrollPane();
        KeluhanSaatIni = new widget.TextArea();
        jLabel33 = new widget.Label();
        jLabel16 = new widget.Label();
        NoTelpon = new widget.TextBox();
        jLabel34 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        KondisiSaatIni = new widget.TextArea();
        jLabel35 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        Tindakan = new widget.TextArea();
        jLabel18 = new widget.Label();
        GCS = new widget.TextBox();
        Nadi = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        TD = new widget.TextBox();
        jLabel25 = new widget.Label();
        Suhu = new widget.TextBox();
        jLabel26 = new widget.Label();
        Pernafasan = new widget.TextBox();
        jLabel28 = new widget.Label();
        Saturasi = new widget.TextBox();
        Diagnosa = new widget.TextBox();
        jLabel22 = new widget.Label();
        jLabel41 = new widget.Label();
        Rekomendasi = new widget.ComboBox();
        jLabel36 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        Saran = new widget.TextArea();
        ChkInput = new widget.CekBox();
        PanelAccorCttn = new widget.PanelBiasa();
        ChkLainnya = new widget.CekBox();
        TabData = new javax.swing.JTabbedPane();
        FormKeluhan = new widget.PanelBiasa();
        Scroll11 = new widget.ScrollPane();
        ChkKeluhan = new widget.TextArea();
        FormKondisi = new widget.PanelBiasa();
        Scroll10 = new widget.ScrollPane();
        ChkKondisi = new widget.TextArea();
        FormTindakan = new widget.PanelBiasa();
        Scroll12 = new widget.ScrollPane();
        ChkTindakan = new widget.TextArea();
        FormSaran = new widget.PanelBiasa();
        Scroll13 = new widget.ScrollPane();
        ChkSaran = new widget.TextArea();
        internalFrame3 = new widget.InternalFrame();
        jPanel2 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbPasienRujuk = new widget.Table();
        PanelInput2 = new javax.swing.JPanel();
        FormInput3 = new widget.PanelBiasa();
        jLabel12 = new widget.Label();
        NmPenelpon1 = new widget.TextBox();
        scrollPane5 = new widget.ScrollPane();
        KeluhanSaatIni1 = new widget.TextArea();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        KondisiSaatIni1 = new widget.TextArea();
        jLabel39 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        Tindakan1 = new widget.TextArea();
        jLabel31 = new widget.Label();
        GCS1 = new widget.TextBox();
        Nadi1 = new widget.TextBox();
        jLabel32 = new widget.Label();
        jLabel40 = new widget.Label();
        TD1 = new widget.TextBox();
        jLabel42 = new widget.Label();
        Suhu1 = new widget.TextBox();
        jLabel43 = new widget.Label();
        Pernafasan1 = new widget.TextBox();
        jLabel44 = new widget.Label();
        Saturasi1 = new widget.TextBox();
        jLabel13 = new widget.Label();
        NmPenelpon2 = new widget.TextBox();
        jLabel14 = new widget.Label();
        NmPenelpon3 = new widget.TextBox();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        Saturasi2 = new widget.TextBox();
        jLabel57 = new widget.Label();
        ChkInput1 = new widget.CekBox();
        internalFrame4 = new widget.InternalFrame();
        jPanel4 = new javax.swing.JPanel();
        scrollPane9 = new widget.ScrollPane();
        tbPasienDatang = new widget.Table();
        PanelInput3 = new javax.swing.JPanel();
        FormInput4 = new widget.PanelBiasa();
        scrollPane10 = new widget.ScrollPane();
        KeluhanSaatIni2 = new widget.TextArea();
        jLabel48 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        KondisiSaatIni2 = new widget.TextArea();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        Saran2 = new widget.TextArea();
        ChkInput3 = new widget.CekBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnLembarSkriningRalan.setBackground(new java.awt.Color(255, 255, 254));
        MnLembarSkriningRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarSkriningRalan.setForeground(new java.awt.Color(50, 50, 50));
        MnLembarSkriningRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarSkriningRalan.setText("Lembar Skrining Ralan");
        MnLembarSkriningRalan.setName("MnLembarSkriningRalan"); // NOI18N
        MnLembarSkriningRalan.setPreferredSize(new java.awt.Dimension(230, 26));
        MnLembarSkriningRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarSkriningRalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLembarSkriningRalan);

        MnPDFSkriningRalan.setBackground(new java.awt.Color(255, 255, 254));
        MnPDFSkriningRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPDFSkriningRalan.setForeground(new java.awt.Color(50, 50, 50));
        MnPDFSkriningRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPDFSkriningRalan.setText("PDF Skrining Ralan");
        MnPDFSkriningRalan.setName("MnPDFSkriningRalan"); // NOI18N
        MnPDFSkriningRalan.setPreferredSize(new java.awt.Dimension(230, 26));
        MnPDFSkriningRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPDFSkriningRalanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPDFSkriningRalan);

        ppPasienCorona.setBackground(new java.awt.Color(255, 255, 254));
        ppPasienCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPasienCorona.setForeground(new java.awt.Color(50, 50, 50));
        ppPasienCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPasienCorona.setText("Bridging Pasien Corona Kemenkes");
        ppPasienCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPasienCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPasienCorona.setName("ppPasienCorona"); // NOI18N
        ppPasienCorona.setPreferredSize(new java.awt.Dimension(230, 26));
        ppPasienCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPasienCoronaBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppPasienCorona);

        jPopupMenu1.getAccessibleContext().setAccessibleName("");
        jPopupMenu1.getAccessibleContext().setAccessibleDescription("");
        jPopupMenu1.getAccessibleContext().setAccessibleParent(internalFrame1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Skrining Awal Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/add-file-16x16.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Regist");
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setText("Periode :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass7.add(jLabel15);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-09-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(DTPCari1);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass7.add(jLabel17);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-09-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass7.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(230, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass7.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('7');
        BtnCari.setToolTipText("Alt+7");
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
        panelGlass7.add(BtnCari);

        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(jLabel10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(LCount);

        jPanel3.add(panelGlass7, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 110));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel4.setText("Pasien :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 40, 80, 23);

        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNoRMActionPerformed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(85, 40, 80, 23);

        DTPReg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-09-2023" }));
        DTPReg.setDate(new java.util.Date(1694619442000L));
        DTPReg.setDisplayFormat("dd-MM-yyyy");
        DTPReg.setName("DTPReg"); // NOI18N
        DTPReg.setOpaque(false);
        DTPReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTPRegActionPerformed(evt);
            }
        });
        DTPReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPRegKeyPressed(evt);
            }
        });
        FormInput.add(DTPReg);
        DTPReg.setBounds(280, 70, 90, 23);

        jLabel9.setText("Jam Sekrining :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(370, 70, 90, 23);

        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        CmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJamKeyPressed(evt);
            }
        });
        FormInput.add(CmbJam);
        CmbJam.setBounds(465, 70, 62, 23);

        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        CmbMenit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenitKeyPressed(evt);
            }
        });
        FormInput.add(CmbMenit);
        CmbMenit.setBounds(530, 70, 62, 23);

        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        CmbDetik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbDetikKeyPressed(evt);
            }
        });
        FormInput.add(CmbDetik);
        CmbDetik.setBounds(595, 70, 62, 23);

        ChkJln.setBorder(null);
        ChkJln.setSelected(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        FormInput.add(ChkJln);
        ChkJln.setBounds(655, 70, 23, 23);

        jLabel11.setText("Tanggal Skrining :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(176, 70, 100, 23);

        jLabel5.setText("Tgl.Lahir :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(370, 40, 60, 23);

        jLabel7.setText("J. K. :");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput.add(jLabel7);
        jLabel7.setBounds(0, 70, 80, 23);

        Alamat.setHighlighter(null);
        Alamat.setName("Alamat"); // NOI18N
        FormInput.add(Alamat);
        Alamat.setBounds(815, 40, 310, 23);

        jLabel21.setText("Alamat :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(740, 40, 70, 23);

        BtnPtg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPtg1.setMnemonic('X');
        BtnPtg1.setToolTipText("Alt+X");
        BtnPtg1.setName("BtnPtg1"); // NOI18N
        BtnPtg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPtg1ActionPerformed(evt);
            }
        });
        BtnPtg1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPtg1KeyPressed(evt);
            }
        });
        FormInput.add(BtnPtg1);
        BtnPtg1.setBounds(1125, 70, 28, 23);

        jLabel27.setText("Petugas Skrining :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(690, 70, 90, 23);

        KdPetugas1.setEditable(false);
        KdPetugas1.setHighlighter(null);
        KdPetugas1.setName("KdPetugas1"); // NOI18N
        KdPetugas1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugas1KeyPressed(evt);
            }
        });
        FormInput.add(KdPetugas1);
        KdPetugas1.setBounds(785, 70, 130, 23);

        NmPetugas1.setEditable(false);
        NmPetugas1.setHighlighter(null);
        NmPetugas1.setName("NmPetugas1"); // NOI18N
        NmPetugas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NmPetugas1ActionPerformed(evt);
            }
        });
        FormInput.add(NmPetugas1);
        NmPetugas1.setBounds(915, 70, 212, 23);

        DTPLahir1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "13-09-2023" }));
        DTPLahir1.setDate(new java.util.Date(1694571206000L));
        DTPLahir1.setDisplayFormat("dd-MM-yyyy");
        DTPLahir1.setName("DTPLahir1"); // NOI18N
        DTPLahir1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPLahir1ItemStateChanged(evt);
            }
        });
        DTPLahir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTPLahir1ActionPerformed(evt);
            }
        });
        FormInput.add(DTPLahir1);
        DTPLahir1.setBounds(435, 40, 90, 22);

        jLabel29.setText("Umur :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(490, 40, 95, 23);

        TUmurTh.setName("TUmurTh"); // NOI18N
        TUmurTh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurThKeyPressed(evt);
            }
        });
        FormInput.add(TUmurTh);
        TUmurTh.setBounds(590, 40, 35, 23);

        jLabel45.setText("Th");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(617, 40, 20, 23);

        TUmurBl.setName("TUmurBl"); // NOI18N
        TUmurBl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurBlKeyPressed(evt);
            }
        });
        FormInput.add(TUmurBl);
        TUmurBl.setBounds(640, 40, 35, 23);

        jLabel30.setText("Bl");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(665, 40, 20, 23);

        TUmurHr.setName("TUmurHr"); // NOI18N
        TUmurHr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TUmurHrKeyPressed(evt);
            }
        });
        FormInput.add(TUmurHr);
        TUmurHr.setBounds(690, 40, 35, 23);

        jLabel46.setText("Hr");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(717, 40, 20, 23);

        CPasien.setText("Lihat Daftar Pasien");
        CPasien.setName("CPasien"); // NOI18N
        CPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CPasienActionPerformed(evt);
            }
        });
        FormInput.add(CPasien);
        CPasien.setBounds(50, 8, 140, 23);

        JK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Perempuan", "Laki-Laki" }));
        JK.setName("JK"); // NOI18N
        FormInput.add(JK);
        JK.setBounds(85, 70, 90, 23);

        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TPasienActionPerformed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(165, 40, 200, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabSkrining.setBackground(new java.awt.Color(255, 255, 254));
        TabSkrining.setForeground(new java.awt.Color(50, 50, 50));
        TabSkrining.setName("TabSkrining"); // NOI18N

        internalFrame2.setBackground(new java.awt.Color(255, 255, 254));
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(192, 10));
        jPanel1.setLayout(new java.awt.BorderLayout());

        Scroll.setToolTipText("Klik data di table, kemudian klik kanan untuk menampilkan menu yang dibutuhkan");
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPasienTelpon.setAutoCreateRowSorter(true);
        tbPasienTelpon.setToolTipText("Klik data di table, kemudian klik kanan untuk menampilkan menu yang dibutuhkan");
        tbPasienTelpon.setComponentPopupMenu(jPopupMenu1);
        tbPasienTelpon.setName("tbPasienTelpon"); // NOI18N
        tbPasienTelpon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienTelponMouseClicked(evt);
            }
        });
        tbPasienTelpon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienTelponKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPasienTelponKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbPasienTelpon);

        jPanel1.add(Scroll, java.awt.BorderLayout.CENTER);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(490, 205));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(490, 167));
        FormInput2.setLayout(null);

        jLabel8.setText("Nama Penelepon : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput2.add(jLabel8);
        jLabel8.setBounds(5, 40, 100, 23);

        NmPenelpon.setHighlighter(null);
        NmPenelpon.setName("NmPenelpon"); // NOI18N
        NmPenelpon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmPenelponKeyPressed(evt);
            }
        });
        FormInput2.add(NmPenelpon);
        NmPenelpon.setBounds(108, 40, 190, 23);

        jLabel20.setText("Petugas / Penerima Telephone :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput2.add(jLabel20);
        jLabel20.setBounds(0, 10, 170, 23);

        KdPetugas.setEditable(false);
        KdPetugas.setHighlighter(null);
        KdPetugas.setName("KdPetugas"); // NOI18N
        KdPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdPetugasKeyPressed(evt);
            }
        });
        FormInput2.add(KdPetugas);
        KdPetugas.setBounds(175, 10, 130, 23);

        NmPetugas.setEditable(false);
        NmPetugas.setHighlighter(null);
        NmPetugas.setName("NmPetugas"); // NOI18N
        FormInput2.add(NmPetugas);
        NmPetugas.setBounds(307, 10, 212, 23);

        BtnPtg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPtg.setMnemonic('X');
        BtnPtg.setToolTipText("Alt+X");
        BtnPtg.setName("BtnPtg"); // NOI18N
        BtnPtg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPtgActionPerformed(evt);
            }
        });
        BtnPtg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPtgKeyPressed(evt);
            }
        });
        FormInput2.add(BtnPtg);
        BtnPtg.setBounds(517, 10, 28, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        KeluhanSaatIni.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KeluhanSaatIni.setColumns(20);
        KeluhanSaatIni.setRows(5);
        KeluhanSaatIni.setName("KeluhanSaatIni"); // NOI18N
        KeluhanSaatIni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanSaatIniKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(KeluhanSaatIni);

        FormInput2.add(scrollPane1);
        scrollPane1.setBounds(108, 70, 310, 43);

        jLabel33.setText("Keluhan Saat Ini :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput2.add(jLabel33);
        jLabel33.setBounds(-22, 70, 125, 23);

        jLabel16.setText("Nomor Telephone : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput2.add(jLabel16);
        jLabel16.setBounds(300, 40, 100, 23);

        NoTelpon.setHighlighter(null);
        NoTelpon.setName("NoTelpon"); // NOI18N
        NoTelpon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoTelponKeyPressed(evt);
            }
        });
        FormInput2.add(NoTelpon);
        NoTelpon.setBounds(400, 40, 190, 23);

        jLabel34.setText("Kondisi Saat Ini :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput2.add(jLabel34);
        jLabel34.setBounds(-22, 120, 125, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        KondisiSaatIni.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KondisiSaatIni.setColumns(20);
        KondisiSaatIni.setRows(5);
        KondisiSaatIni.setName("KondisiSaatIni"); // NOI18N
        KondisiSaatIni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KondisiSaatIniKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(KondisiSaatIni);

        FormInput2.add(scrollPane2);
        scrollPane2.setBounds(108, 120, 310, 43);

        jLabel35.setText("Tindakan/Terapi :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput2.add(jLabel35);
        jLabel35.setBounds(390, 70, 125, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        Tindakan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tindakan.setColumns(20);
        Tindakan.setRows(5);
        Tindakan.setName("Tindakan"); // NOI18N
        Tindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindakanKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(Tindakan);

        FormInput2.add(scrollPane3);
        scrollPane3.setBounds(520, 70, 310, 43);

        jLabel18.setText("GCS :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput2.add(jLabel18);
        jLabel18.setBounds(425, 120, 30, 23);

        GCS.setHighlighter(null);
        GCS.setName("GCS"); // NOI18N
        GCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCSKeyPressed(evt);
            }
        });
        FormInput2.add(GCS);
        GCS.setBounds(460, 120, 50, 23);

        Nadi.setHighlighter(null);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput2.add(Nadi);
        Nadi.setBounds(460, 145, 50, 23);

        jLabel23.setText("Nadi :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput2.add(jLabel23);
        jLabel23.setBounds(425, 145, 30, 23);

        jLabel24.setText("TD :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput2.add(jLabel24);
        jLabel24.setBounds(510, 120, 30, 23);

        TD.setHighlighter(null);
        TD.setName("TD"); // NOI18N
        TD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDKeyPressed(evt);
            }
        });
        FormInput2.add(TD);
        TD.setBounds(545, 120, 50, 23);

        jLabel25.setText("Suhu :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput2.add(jLabel25);
        jLabel25.setBounds(593, 120, 40, 23);

        Suhu.setHighlighter(null);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput2.add(Suhu);
        Suhu.setBounds(638, 120, 50, 23);

        jLabel26.setText("Pernafasan :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput2.add(jLabel26);
        jLabel26.setBounds(510, 145, 70, 23);

        Pernafasan.setHighlighter(null);
        Pernafasan.setName("Pernafasan"); // NOI18N
        Pernafasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PernafasanActionPerformed(evt);
            }
        });
        Pernafasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PernafasanKeyPressed(evt);
            }
        });
        FormInput2.add(Pernafasan);
        Pernafasan.setBounds(585, 145, 50, 23);

        jLabel28.setText("Saturasi :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput2.add(jLabel28);
        jLabel28.setBounds(640, 145, 50, 23);

        Saturasi.setHighlighter(null);
        Saturasi.setName("Saturasi"); // NOI18N
        Saturasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaturasiActionPerformed(evt);
            }
        });
        Saturasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SaturasiKeyPressed(evt);
            }
        });
        FormInput2.add(Saturasi);
        Saturasi.setBounds(695, 145, 50, 23);

        Diagnosa.setHighlighter(null);
        Diagnosa.setName("Diagnosa"); // NOI18N
        Diagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaKeyPressed(evt);
            }
        });
        FormInput2.add(Diagnosa);
        Diagnosa.setBounds(905, 70, 190, 23);

        jLabel22.setText("Diagnosa :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput2.add(jLabel22);
        jLabel22.setBounds(840, 70, 60, 23);

        jLabel41.setText("Rekomendasi :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput2.add(jLabel41);
        jLabel41.setBounds(833, 100, 100, 23);

        Rekomendasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Terima", "Tidak Terima" }));
        Rekomendasi.setName("Rekomendasi"); // NOI18N
        Rekomendasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RekomendasiKeyPressed(evt);
            }
        });
        FormInput2.add(Rekomendasi);
        Rekomendasi.setBounds(938, 100, 118, 23);

        jLabel36.setText("Saran Selama Perjalanan :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput2.add(jLabel36);
        jLabel36.setBounds(795, 130, 140, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        Saran.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Saran.setColumns(20);
        Saran.setRows(5);
        Saran.setName("Saran"); // NOI18N
        Saran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SaranKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(Saran);

        FormInput2.add(scrollPane4);
        scrollPane4.setBounds(938, 130, 310, 43);

        PanelInput1.add(FormInput2, java.awt.BorderLayout.CENTER);

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
        PanelInput1.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(PanelInput1, java.awt.BorderLayout.PAGE_START);

        PanelAccorCttn.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccorCttn.setName("PanelAccorCttn"); // NOI18N
        PanelAccorCttn.setPreferredSize(new java.awt.Dimension(445, 43));
        PanelAccorCttn.setVerifyInputWhenFocusTarget(false);
        PanelAccorCttn.setLayout(new java.awt.BorderLayout(1, 1));

        ChkLainnya.setBackground(new java.awt.Color(255, 250, 248));
        ChkLainnya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkLainnya.setSelected(true);
        ChkLainnya.setFocusable(false);
        ChkLainnya.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkLainnya.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkLainnya.setName("ChkLainnya"); // NOI18N
        ChkLainnya.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkLainnya.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkLainnya.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkLainnya.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkLainnya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLainnyaActionPerformed(evt);
            }
        });
        PanelAccorCttn.add(ChkLainnya, java.awt.BorderLayout.WEST);

        TabData.setBackground(new java.awt.Color(254, 255, 254));
        TabData.setForeground(new java.awt.Color(50, 50, 50));
        TabData.setName("TabData"); // NOI18N
        TabData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabDataMouseClicked(evt);
            }
        });

        FormKeluhan.setBackground(new java.awt.Color(255, 255, 255));
        FormKeluhan.setBorder(null);
        FormKeluhan.setName("FormKeluhan"); // NOI18N
        FormKeluhan.setPreferredSize(new java.awt.Dimension(115, 73));
        FormKeluhan.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        ChkKeluhan.setEditable(false);
        ChkKeluhan.setBorder(null);
        ChkKeluhan.setColumns(25);
        ChkKeluhan.setRows(40);
        ChkKeluhan.setName("ChkKeluhan"); // NOI18N
        Scroll11.setViewportView(ChkKeluhan);

        FormKeluhan.add(Scroll11, java.awt.BorderLayout.CENTER);

        TabData.addTab("Keluhan Saat Ini", FormKeluhan);

        FormKondisi.setBackground(new java.awt.Color(255, 255, 255));
        FormKondisi.setBorder(null);
        FormKondisi.setName("FormKondisi"); // NOI18N
        FormKondisi.setPreferredSize(new java.awt.Dimension(115, 73));
        FormKondisi.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        ChkKondisi.setEditable(false);
        ChkKondisi.setBorder(null);
        ChkKondisi.setColumns(25);
        ChkKondisi.setRows(40);
        ChkKondisi.setName("ChkKondisi"); // NOI18N
        Scroll10.setViewportView(ChkKondisi);

        FormKondisi.add(Scroll10, java.awt.BorderLayout.CENTER);

        TabData.addTab("Kondisi Saat Ini", FormKondisi);

        FormTindakan.setBackground(new java.awt.Color(255, 255, 255));
        FormTindakan.setBorder(null);
        FormTindakan.setName("FormTindakan"); // NOI18N
        FormTindakan.setPreferredSize(new java.awt.Dimension(115, 73));
        FormTindakan.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        ChkTindakan.setEditable(false);
        ChkTindakan.setBorder(null);
        ChkTindakan.setColumns(25);
        ChkTindakan.setRows(40);
        ChkTindakan.setName("ChkTindakan"); // NOI18N
        Scroll12.setViewportView(ChkTindakan);

        FormTindakan.add(Scroll12, java.awt.BorderLayout.CENTER);

        TabData.addTab("Tindakan/Terapi", FormTindakan);

        FormSaran.setBackground(new java.awt.Color(255, 255, 255));
        FormSaran.setBorder(null);
        FormSaran.setName("FormSaran"); // NOI18N
        FormSaran.setPreferredSize(new java.awt.Dimension(115, 73));
        FormSaran.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll13.setName("Scroll13"); // NOI18N
        Scroll13.setOpaque(true);

        ChkSaran.setEditable(false);
        ChkSaran.setBorder(null);
        ChkSaran.setColumns(25);
        ChkSaran.setRows(40);
        ChkSaran.setName("ChkSaran"); // NOI18N
        Scroll13.setViewportView(ChkSaran);

        FormSaran.add(Scroll13, java.awt.BorderLayout.CENTER);

        TabData.addTab("Saran", FormSaran);

        PanelAccorCttn.add(TabData, java.awt.BorderLayout.CENTER);

        jPanel1.add(PanelAccorCttn, java.awt.BorderLayout.EAST);

        internalFrame2.add(jPanel1, java.awt.BorderLayout.CENTER);

        TabSkrining.addTab("Via Telepon", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(192, 10));
        jPanel2.setLayout(new java.awt.BorderLayout());

        Scroll1.setToolTipText("Klik data di table, kemudian klik kanan untuk menampilkan menu yang dibutuhkan");
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbPasienRujuk.setAutoCreateRowSorter(true);
        tbPasienRujuk.setToolTipText("Klik data di table, kemudian klik kanan untuk menampilkan menu yang dibutuhkan");
        tbPasienRujuk.setName("tbPasienRujuk"); // NOI18N
        tbPasienRujuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienRujukMouseClicked(evt);
            }
        });
        tbPasienRujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienRujukKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPasienRujukKeyReleased(evt);
            }
        });
        Scroll1.setViewportView(tbPasienRujuk);

        jPanel2.add(Scroll1, java.awt.BorderLayout.CENTER);

        PanelInput2.setName("PanelInput2"); // NOI18N
        PanelInput2.setOpaque(false);
        PanelInput2.setPreferredSize(new java.awt.Dimension(490, 200));
        PanelInput2.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(490, 167));
        FormInput3.setLayout(null);

        jLabel12.setText("Diagnosa Rujukan :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput3.add(jLabel12);
        jLabel12.setBounds(5, 40, 100, 23);

        NmPenelpon1.setHighlighter(null);
        NmPenelpon1.setName("NmPenelpon1"); // NOI18N
        NmPenelpon1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmPenelpon1KeyPressed(evt);
            }
        });
        FormInput3.add(NmPenelpon1);
        NmPenelpon1.setBounds(108, 40, 190, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        KeluhanSaatIni1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KeluhanSaatIni1.setColumns(20);
        KeluhanSaatIni1.setRows(5);
        KeluhanSaatIni1.setName("KeluhanSaatIni1"); // NOI18N
        KeluhanSaatIni1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanSaatIni1KeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(KeluhanSaatIni1);

        FormInput3.add(scrollPane5);
        scrollPane5.setBounds(108, 70, 310, 43);

        jLabel37.setText("Keluhan Saat Ini :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput3.add(jLabel37);
        jLabel37.setBounds(-22, 70, 125, 23);

        jLabel38.setText("Keadaani Saat Ini :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput3.add(jLabel38);
        jLabel38.setBounds(-22, 120, 125, 23);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane6.setName("scrollPane6"); // NOI18N

        KondisiSaatIni1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KondisiSaatIni1.setColumns(20);
        KondisiSaatIni1.setRows(5);
        KondisiSaatIni1.setName("KondisiSaatIni1"); // NOI18N
        KondisiSaatIni1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KondisiSaatIni1KeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(KondisiSaatIni1);

        FormInput3.add(scrollPane6);
        scrollPane6.setBounds(108, 120, 310, 43);

        jLabel39.setText("Tindakan/Terapi :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput3.add(jLabel39);
        jLabel39.setBounds(390, 70, 125, 23);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        Tindakan1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tindakan1.setColumns(20);
        Tindakan1.setRows(5);
        Tindakan1.setName("Tindakan1"); // NOI18N
        Tindakan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tindakan1KeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(Tindakan1);

        FormInput3.add(scrollPane7);
        scrollPane7.setBounds(520, 70, 310, 43);

        jLabel31.setText("GCS :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput3.add(jLabel31);
        jLabel31.setBounds(425, 120, 30, 23);

        GCS1.setHighlighter(null);
        GCS1.setName("GCS1"); // NOI18N
        GCS1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                GCS1KeyPressed(evt);
            }
        });
        FormInput3.add(GCS1);
        GCS1.setBounds(460, 120, 50, 23);

        Nadi1.setHighlighter(null);
        Nadi1.setName("Nadi1"); // NOI18N
        Nadi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Nadi1KeyPressed(evt);
            }
        });
        FormInput3.add(Nadi1);
        Nadi1.setBounds(460, 150, 50, 23);

        jLabel32.setText("Nadi :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput3.add(jLabel32);
        jLabel32.setBounds(425, 150, 30, 23);

        jLabel40.setText("%");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput3.add(jLabel40);
        jLabel40.setBounds(840, 150, 11, 23);

        TD1.setHighlighter(null);
        TD1.setName("TD1"); // NOI18N
        TD1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TD1KeyPressed(evt);
            }
        });
        FormInput3.add(TD1);
        TD1.setBounds(552, 120, 50, 23);

        jLabel42.setText("Suhu :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput3.add(jLabel42);
        jLabel42.setBounds(640, 120, 40, 23);

        Suhu1.setHighlighter(null);
        Suhu1.setName("Suhu1"); // NOI18N
        Suhu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Suhu1KeyPressed(evt);
            }
        });
        FormInput3.add(Suhu1);
        Suhu1.setBounds(685, 120, 50, 23);

        jLabel43.setText("Pernafasan :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput3.add(jLabel43);
        jLabel43.setBounds(560, 150, 70, 23);

        Pernafasan1.setHighlighter(null);
        Pernafasan1.setName("Pernafasan1"); // NOI18N
        Pernafasan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pernafasan1ActionPerformed(evt);
            }
        });
        Pernafasan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Pernafasan1KeyPressed(evt);
            }
        });
        FormInput3.add(Pernafasan1);
        Pernafasan1.setBounds(635, 150, 50, 23);

        jLabel44.setText("Saturasi :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput3.add(jLabel44);
        jLabel44.setBounds(735, 150, 50, 23);

        Saturasi1.setHighlighter(null);
        Saturasi1.setName("Saturasi1"); // NOI18N
        Saturasi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Saturasi1ActionPerformed(evt);
            }
        });
        Saturasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Saturasi1KeyPressed(evt);
            }
        });
        FormInput3.add(Saturasi1);
        Saturasi1.setBounds(790, 150, 50, 23);

        jLabel13.setText("Nama RS/Klinik/Puskesmas : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput3.add(jLabel13);
        jLabel13.setBounds(1, 10, 150, 23);

        NmPenelpon2.setHighlighter(null);
        NmPenelpon2.setName("NmPenelpon2"); // NOI18N
        NmPenelpon2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmPenelpon2KeyPressed(evt);
            }
        });
        FormInput3.add(NmPenelpon2);
        NmPenelpon2.setBounds(153, 10, 190, 23);

        jLabel14.setText("Alasan Dirujuk :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput3.add(jLabel14);
        jLabel14.setBounds(350, 10, 80, 23);

        NmPenelpon3.setHighlighter(null);
        NmPenelpon3.setName("NmPenelpon3"); // NOI18N
        NmPenelpon3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmPenelpon3KeyPressed(evt);
            }
        });
        FormInput3.add(NmPenelpon3);
        NmPenelpon3.setBounds(433, 10, 190, 23);

        jLabel51.setText("TD :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput3.add(jLabel51);
        jLabel51.setBounds(517, 120, 30, 23);

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("°C");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput3.add(jLabel52);
        jLabel52.setBounds(735, 120, 20, 23);

        jLabel53.setText("mmHg");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput3.add(jLabel53);
        jLabel53.setBounds(595, 120, 40, 23);

        jLabel54.setText("x/menit");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput3.add(jLabel54);
        jLabel54.setBounds(510, 150, 40, 23);

        jLabel55.setText("x/menit");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput3.add(jLabel55);
        jLabel55.setBounds(684, 150, 40, 23);

        jLabel56.setText("Saturasi :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput3.add(jLabel56);
        jLabel56.setBounds(860, 150, 50, 23);

        Saturasi2.setHighlighter(null);
        Saturasi2.setName("Saturasi2"); // NOI18N
        Saturasi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Saturasi2ActionPerformed(evt);
            }
        });
        Saturasi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Saturasi2KeyPressed(evt);
            }
        });
        FormInput3.add(Saturasi2);
        Saturasi2.setBounds(920, 150, 50, 23);

        jLabel57.setText("cc");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput3.add(jLabel57);
        jLabel57.setBounds(962, 150, 20, 23);

        PanelInput2.add(FormInput3, java.awt.BorderLayout.CENTER);

        ChkInput1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setMnemonic('I');
        ChkInput1.setText(".: Input Data");
        ChkInput1.setToolTipText("Alt+I");
        ChkInput1.setBorderPainted(true);
        ChkInput1.setBorderPaintedFlat(true);
        ChkInput1.setFocusable(false);
        ChkInput1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput1.setName("ChkInput1"); // NOI18N
        ChkInput1.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput1ActionPerformed(evt);
            }
        });
        PanelInput2.add(ChkInput1, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(PanelInput2, java.awt.BorderLayout.PAGE_START);

        internalFrame3.add(jPanel2, java.awt.BorderLayout.CENTER);

        TabSkrining.addTab("Pasien Rujuk", internalFrame3);

        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setLayout(new java.awt.BorderLayout());

        scrollPane9.setToolTipText("Klik data di table, kemudian klik kanan untuk menampilkan menu yang dibutuhkan");
        scrollPane9.setName("scrollPane9"); // NOI18N
        scrollPane9.setOpaque(true);

        tbPasienDatang.setAutoCreateRowSorter(true);
        tbPasienDatang.setToolTipText("Klik data di table, kemudian klik kanan untuk menampilkan menu yang dibutuhkan");
        tbPasienDatang.setName("tbPasienDatang"); // NOI18N
        tbPasienDatang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPasienDatangMouseClicked(evt);
            }
        });
        tbPasienDatang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPasienDatangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPasienDatangKeyReleased(evt);
            }
        });
        scrollPane9.setViewportView(tbPasienDatang);

        jPanel4.add(scrollPane9, java.awt.BorderLayout.CENTER);

        PanelInput3.setName("PanelInput3"); // NOI18N
        PanelInput3.setPreferredSize(new java.awt.Dimension(100, 135));
        PanelInput3.setLayout(new java.awt.BorderLayout());

        FormInput4.setName("FormInput4"); // NOI18N
        FormInput4.setPreferredSize(new java.awt.Dimension(490, 100));
        FormInput4.setLayout(null);

        scrollPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane10.setName("scrollPane10"); // NOI18N

        KeluhanSaatIni2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KeluhanSaatIni2.setColumns(20);
        KeluhanSaatIni2.setRows(5);
        KeluhanSaatIni2.setName("KeluhanSaatIni2"); // NOI18N
        KeluhanSaatIni2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeluhanSaatIni2KeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(KeluhanSaatIni2);

        FormInput4.add(scrollPane10);
        scrollPane10.setBounds(110, 10, 310, 43);

        jLabel48.setText("Keluhan Saat Ini :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput4.add(jLabel48);
        jLabel48.setBounds(15, 10, 90, 23);

        scrollPane11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane11.setName("scrollPane11"); // NOI18N

        KondisiSaatIni2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KondisiSaatIni2.setColumns(20);
        KondisiSaatIni2.setRows(5);
        KondisiSaatIni2.setName("KondisiSaatIni2"); // NOI18N
        KondisiSaatIni2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KondisiSaatIni2KeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(KondisiSaatIni2);

        FormInput4.add(scrollPane11);
        scrollPane11.setBounds(110, 60, 310, 43);

        jLabel49.setText("Kondisi Saat Ini :");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput4.add(jLabel49);
        jLabel49.setBounds(-20, 60, 125, 23);

        jLabel50.setText("Saran Selama Perjalanan :");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput4.add(jLabel50);
        jLabel50.setBounds(420, 10, 140, 23);

        scrollPane12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane12.setName("scrollPane12"); // NOI18N

        Saran2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Saran2.setColumns(20);
        Saran2.setRows(5);
        Saran2.setName("Saran2"); // NOI18N
        Saran2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Saran2KeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Saran2);

        FormInput4.add(scrollPane12);
        scrollPane12.setBounds(563, 10, 310, 43);

        PanelInput3.add(FormInput4, java.awt.BorderLayout.CENTER);

        ChkInput3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput3.setMnemonic('I');
        ChkInput3.setText(".: Input Data");
        ChkInput3.setToolTipText("Alt+I");
        ChkInput3.setBorderPainted(true);
        ChkInput3.setBorderPaintedFlat(true);
        ChkInput3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput3.setName("ChkInput3"); // NOI18N
        ChkInput3.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput3ActionPerformed(evt);
            }
        });
        PanelInput3.add(ChkInput3, java.awt.BorderLayout.PAGE_END);

        jPanel4.add(PanelInput3, java.awt.BorderLayout.PAGE_START);

        internalFrame4.add(jPanel4, java.awt.BorderLayout.CENTER);

        TabSkrining.addTab("Dari Rumah / Datang Sendiri", internalFrame4);

        internalFrame1.add(TabSkrining, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRM.getText().trim().equals("")||TPasien.getText().trim().equals("")){
             Valid.textKosong(TNoRM,"Pasien");
        }else if(KdPetugas1.getText().trim().equals("")||NmPetugas1.getText().trim().equals("")){
            Valid.textKosong(BtnPtg1,"Petugas");
        }else{
            if(TabSkrining.getSelectedIndex()==0){
                if(KdPetugas.getText().trim().equals("")||NmPetugas.getText().trim().equals("")){
                    Valid.textKosong(BtnPtg,"Petugas");
                }else if(NmPenelpon.getText().trim().equals("")){
                    Valid.textKosong(NmPenelpon, "Nama Penelpon");
                }else if(NoTelpon.getText().trim().equals("")){
                    Valid.textKosong(NoTelpon, "Nomor Telepon");
                }else if(KeluhanSaatIni.getText().trim().equals("")){
                    Valid.textKosong(KeluhanSaatIni, "Keluhan Saat Ini");
                }else if(KondisiSaatIni.getText().trim().equals("")){
                    Valid.textKosong(KondisiSaatIni, "Kondisi Saat Ini");
                }else if(Tindakan.getText().trim().equals("")){
                    Valid.textKosong(Tindakan, "Tindakan / Terapi");
                }else if(GCS.getText().trim().equals("")){
                    Valid.textKosong(GCS, "GCS");
                }else if(TD.getText().trim().equals("")){
                    Valid.textKosong(TD, "TD");
                }else if(Suhu.getText().trim().equals("")){
                    Valid.textKosong(Nadi, "Suhu");
                }else if(Pernafasan.getText().trim().equals("")){
                    Valid.textKosong(Pernafasan, "Pernafasan");
                }else if(Saturasi.getText().trim().equals("")){
                    Valid.textKosong(Saturasi, "Saturasi");
                }else if(Diagnosa.getText().trim().equals("")){
                    Valid.textKosong(Diagnosa, "Diagnosa");
                }else if(Saran.getText().trim().equals("")){
                    Valid.textKosong(Saran, "Saran");
                }else{
                    String jk="";
                    if(JK.getSelectedItem().toString().equals("Laki-Laki")){
                       jk="L";
                    }else if(JK.getSelectedItem().toString().equals("Perempuan")){
                        jk="P";
                    }
                    if(Sequel.menyimpantf("skrining_pasien_telepon","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Skrining Pasien Telepon",26,new String[]{
                        Valid.SetTgl(DTPReg.getSelectedItem().toString()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),TNoRM.getText(),TPasien.getText(),Valid.SetTgl(DTPLahir1.getSelectedItem().toString()+""),TUmurTh.getText()+" Th "+TUmurBl.getText()+" Bl "+TUmurHr.getText()+" Hr",Alamat.getText(),
                        jk,KdPetugas1.getText(),NmPetugas1.getText(),
                        // field tab skrining telepon
                        KdPetugas.getText(),NmPetugas.getText(),NmPenelpon.getText(),NoTelpon.getText(),KeluhanSaatIni.getText(),
                        Tindakan.getText(),KondisiSaatIni.getText(),GCS.getText(),TD.getText(),Suhu.getText(),Nadi.getText(),
                        Pernafasan.getText(),Saturasi.getText(),Diagnosa.getText(),Rekomendasi.getSelectedItem().toString(),Saran.getText()
                    })==true){
                        emptTeks();
                        tampil();
                    }
                }
            }else if(TabSkrining.getSelectedIndex()==1){
                // 
            }else if(TabSkrining.getSelectedIndex()==2){
                //
            }
        }
        
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
           Valid.pindah(evt,BtnPtg,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
//        ChkInput.setSelected(true);
//        isForm(); 
//        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            emptTeks();
//        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
//        if(tbObat.getSelectedRow()> -1){ 
//            if(akses.getkode3().equals("Admin Utama")){
//                hapus();
//            }else{
//                if(KdPetugas.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString())){
//                    hapus();
//                }else{
//                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
//                }
//            }
//        }else{
//            JOptionPane.showMessageDialog(null,"Maaf silahkan pilih data terlebih dahulu..!!");
//        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        if(tabMode.getRowCount()==0){
//            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
//            BtnBatal.requestFocus();
//        }else if(tabMode.getRowCount()!=0){
//            Map<String, Object> param = new HashMap<>(); 
//            param.put("namars",akses.getnamars());
//            param.put("alamatrs",akses.getalamatrs());
//            param.put("kotars",akses.getkabupatenrs());
//            param.put("propinsirs",akses.getpropinsirs());
//            param.put("kontakrs",akses.getkontakrs());
//            param.put("emailrs",akses.getemailrs());   
//            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
//            Valid.MyReportqry("rptSkriningRalan.jasper","report","::[ Data Skrining Rawat Jalan ]::",
//                    "select skrining_rawat_jalan.tanggal,skrining_rawat_jalan.jam,skrining_rawat_jalan.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"+
//                    "pasien.nm_ibu,pasien.jk,skrining_rawat_jalan.geriatri,skrining_rawat_jalan.kesadaran,skrining_rawat_jalan.pernapasan,"+
//                    "skrining_rawat_jalan.nyeri_dada,skrining_rawat_jalan.skala_nyeri,skrining_rawat_jalan.keputusan,skrining_rawat_jalan.nip,petugas.nama "+
//                    "from skrining_rawat_jalan inner join pasien inner join petugas on skrining_rawat_jalan.no_rkm_medis=pasien.no_rkm_medis and skrining_rawat_jalan.nip=petugas.nip where "+
//                    "skrining_rawat_jalan.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and skrining_rawat_jalan.no_rkm_medis like '%"+TCari.getText().trim()+"%' or "+
//                    "skrining_rawat_jalan.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
//                    "skrining_rawat_jalan.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and pasien.nm_ibu like '%"+TCari.getText().trim()+"%' or "+
//                    "skrining_rawat_jalan.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and skrining_rawat_jalan.kesadaran like '%"+TCari.getText().trim()+"%' or "+
//                    "skrining_rawat_jalan.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and skrining_rawat_jalan.pernapasan like '%"+TCari.getText().trim()+"%' or "+
//                    "skrining_rawat_jalan.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and skrining_rawat_jalan.nyeri_dada like '%"+TCari.getText().trim()+"%' or "+
//                    "skrining_rawat_jalan.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and skrining_rawat_jalan.keputusan like '%"+TCari.getText().trim()+"%' or "+
//                    "skrining_rawat_jalan.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and skrining_rawat_jalan.nip like '%"+TCari.getText().trim()+"%' or "+
//                    "skrining_rawat_jalan.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and petugas.nama like '%"+TCari.getText().trim()+"%' order by skrining_rawat_jalan.tanggal desc",param);
//        }
//        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TNoRM);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
//        if(tbObat.getSelectedRow()> -1){ 
//            try{
//                pilihan = (String)JOptionPane.showInputDialog(null,"Silahkan pilih cara registrasi..!!","Pilihan Registrasi",JOptionPane.QUESTION_MESSAGE,null,new Object[]{"Via Registrasi","Via IGD","Via Cek No.Kartu VClaim","Via Cek NIK VClaim","Via Cek Rujukan Kartu PCare di VClaim","Via Cek Rujukan Kartu RS di VClaim"},"Via Registrasi");
//                switch (pilihan) {
//                    case "Via Registrasi":  
//                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                        DlgReg reg=new DlgReg(null,false);
//                        reg.emptTeks();    
//                        reg.isCek();
//                        reg.setSize(internalFrame1.getWidth()-20, internalFrame1.getHeight()-20);
//                        reg.setLocationRelativeTo(internalFrame1);
//                        reg.SetPasien(TNoRM.getText());
//                        reg.setVisible(true);
//                        this.setCursor(Cursor.getDefaultCursor()); 
//                        break;
//                    case "Via IGD":
//                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                        DlgIGD igd=new DlgIGD(null,false);
//                        igd.emptTeks();    
//                        igd.isCek();
//                        igd.setSize(internalFrame1.getWidth()-20, internalFrame1.getHeight()-20);
//                        igd.setLocationRelativeTo(internalFrame1);
//                        igd.SetPasien(TNoRM.getText());
//                        igd.setVisible(true);
//                        this.setCursor(Cursor.getDefaultCursor()); 
//                        break;
//                    case "Via Cek No.Kartu VClaim":
//                        nokartu=Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?",TNoRM.getText());
//                        if(nokartu.equals("")){
//                            Valid.textKosong(TCari,"No.Kartu JKN");
//                        }else{
//                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                            BPJSCekKartu form=new BPJSCekKartu(null,false);
//                            form.isCek();
//                            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
//                            form.setLocationRelativeTo(internalFrame1);
//                            form.SetNoKartu(nokartu);
//                            form.setVisible(true);
//                            this.setCursor(Cursor.getDefaultCursor());
//                        }                                
//                        break;
//                    case "Via Cek NIK VClaim":
//                        nokartu=Sequel.cariIsi("select pasien.no_ktp from pasien where pasien.no_rkm_medis=?",TNoRM.getText());
//                        if(nokartu.equals("")){
//                            Valid.textKosong(TCari,"No.KTP");
//                        }else{
//                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                            BPJSCekNIK2 form=new BPJSCekNIK2(null,false);
//                            form.isCek();
//                            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
//                            form.setLocationRelativeTo(internalFrame1);
//                            form.SetNoKTP(nokartu);
//                            form.setVisible(true);
//                            this.setCursor(Cursor.getDefaultCursor());
//                        }     
//                        break;
//                    case "Via Cek Rujukan Kartu PCare di VClaim":
//                        nokartu=Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?",TNoRM.getText());
//                        if(nokartu.equals("")){
//                            Valid.textKosong(TCari,"No.Kartu JKN");
//                        }else{
//                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                            BPJSCekRujukanKartuPCare form=new BPJSCekRujukanKartuPCare(null,false);
//                            form.isCek();
//                            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
//                            form.setLocationRelativeTo(internalFrame1);
//                            form.SetNoKartu(nokartu);
//                            form.setVisible(true);
//                            this.setCursor(Cursor.getDefaultCursor());
//                        }    
//                        break;
//                    case "Via Cek Rujukan Kartu RS di VClaim":
//                        nokartu=Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?",TNoRM.getText());
//                        if(nokartu.equals("")){
//                            Valid.textKosong(TCari,"No.Kartu JKN");
//                        }else{
//                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                            BPJSCekRujukanKartuRS form=new BPJSCekRujukanKartuRS(null,false);
//                            form.isCek();
//                            form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
//                            form.setLocationRelativeTo(internalFrame1);
//                            form.SetNoKartu(nokartu);
//                            form.setVisible(true);
//                            this.setCursor(Cursor.getDefaultCursor());
//                        } 
//                        break;
//                }
//            }catch(Exception e){
//                System.out.println("Notif : "+e);
//            }
//        }else{
//            JOptionPane.showMessageDialog(null,"Maaf silahkan pilih data terlebih dahulu..!!");
//        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

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

    private void CmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJamKeyPressed
        Valid.pindah(evt,DTPLahir1,CmbMenit);
    }//GEN-LAST:event_CmbJamKeyPressed

    private void CmbMenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbMenitKeyPressed
        Valid.pindah(evt,CmbJam,CmbDetik);
    }//GEN-LAST:event_CmbMenitKeyPressed

    private void CmbDetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbDetikKeyPressed
//        Valid.pindah(evt,CmbMenit,Kesadaran);
    }//GEN-LAST:event_CmbDetikKeyPressed

    private void MnLembarSkriningRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarSkriningRalanActionPerformed
//        if(tbObat.getSelectedRow()> -1){ 
//            Map<String, Object> param = new HashMap<>(); 
//            param.put("namars",akses.getnamars());
//            param.put("alamatrs",akses.getalamatrs());
//            param.put("kotars",akses.getkabupatenrs());
//            param.put("propinsirs",akses.getpropinsirs());
//            param.put("kontakrs",akses.getkontakrs());
//            param.put("emailrs",akses.getemailrs());   
//            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
//            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
//            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),14).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),13).toString():finger)+"\n"+DTPReg.getSelectedItem()); 
//            Valid.MyReportqry("rptLembarSkriningRalan.jasper","report","::[ Lembar Skrining Rawat Jalan ]::",
//                    "select skrining_rawat_jalan.tanggal,skrining_rawat_jalan.jam,skrining_rawat_jalan.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"+
//                    "pasien.nm_ibu,pasien.jk,skrining_rawat_jalan.geriatri,skrining_rawat_jalan.kesadaran,skrining_rawat_jalan.pernapasan,"+
//                    "skrining_rawat_jalan.nyeri_dada,skrining_rawat_jalan.skala_nyeri,skrining_rawat_jalan.keputusan,skrining_rawat_jalan.nip,petugas.nama "+
//                    "from skrining_rawat_jalan inner join pasien inner join petugas on skrining_rawat_jalan.no_rkm_medis=pasien.no_rkm_medis and skrining_rawat_jalan.nip=petugas.nip where skrining_rawat_jalan.no_rkm_medis='"+TNoRM.getText()+"'",param);
//        }else{
//            JOptionPane.showMessageDialog(null,"Maaf silahkan pilih data terlebih dahulu..!!");
//        }
    }//GEN-LAST:event_MnLembarSkriningRalanActionPerformed

    private void MnPDFSkriningRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPDFSkriningRalanActionPerformed
//        if(tbObat.getSelectedRow()> -1){ 
//            Map<String, Object> param = new HashMap<>(); 
//            param.put("namars",akses.getnamars());
//            param.put("alamatrs",akses.getalamatrs());
//            param.put("kotars",akses.getkabupatenrs());
//            param.put("propinsirs",akses.getpropinsirs());
//            param.put("kontakrs",akses.getkontakrs());
//            param.put("emailrs",akses.getemailrs());   
//            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
//            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
//            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),14).toString()+"\nID "+(finger.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),13).toString():finger)+"\n"+DTPReg.getSelectedItem()); 
//            Valid.MyReportqrypdf("rptLembarSkriningRalan.jasper","report","::[ Lembar Skrining Rawat Jalan ]::",
//                    "select skrining_rawat_jalan.tanggal,skrining_rawat_jalan.jam,skrining_rawat_jalan.no_rkm_medis,pasien.nm_pasien,pasien.tgl_lahir,"+
//                    "pasien.nm_ibu,pasien.jk,skrining_rawat_jalan.geriatri,skrining_rawat_jalan.kesadaran,skrining_rawat_jalan.pernapasan,"+
//                    "skrining_rawat_jalan.nyeri_dada,skrining_rawat_jalan.skala_nyeri,skrining_rawat_jalan.keputusan,skrining_rawat_jalan.nip,petugas.nama "+
//                    "from skrining_rawat_jalan inner join pasien inner join petugas on skrining_rawat_jalan.no_rkm_medis=pasien.no_rkm_medis and skrining_rawat_jalan.nip=petugas.nip where skrining_rawat_jalan.no_rkm_medis='"+TNoRM.getText()+"'",param);
//        }else{
//            JOptionPane.showMessageDialog(null,"Maaf silahkan pilih data terlebih dahulu..!!");
//        }
    }//GEN-LAST:event_MnPDFSkriningRalanActionPerformed

    private void ppPasienCoronaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPasienCoronaBtnPrintActionPerformed
//        if(tabMode.getRowCount()==0){
//            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");
//            TNoRM.requestFocus();
//        }else if(TPasien.getText().trim().equals("")){
//            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
//            tbObat.requestFocus();
//        }else{
//            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//            CoronaPasien form=new CoronaPasien(null,false);
//            form.setPasien(TNoRM.getText());
//            form.isCek();
//            form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
//            form.setLocationRelativeTo(internalFrame1);
//            form.setVisible(true);
//            this.setCursor(Cursor.getDefaultCursor());
//        }
    }//GEN-LAST:event_ppPasienCoronaBtnPrintActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void BtnPtgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPtgKeyPressed
        //        Valid.pindah(evt,Keputusan,BtnSimpan);
    }//GEN-LAST:event_BtnPtgKeyPressed

    private void BtnPtgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPtgActionPerformed
        petugas2.emptTeks();
        petugas2.isCek();
        petugas2.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas2.setLocationRelativeTo(internalFrame1);
        petugas2.setVisible(true);
    }//GEN-LAST:event_BtnPtgActionPerformed

    private void KdPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPetugasKeyPressed

    private void NmPenelponKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmPenelponKeyPressed
//        Valid.pindah(evt,TNoReg,DTPReg);
    }//GEN-LAST:event_NmPenelponKeyPressed

    private void KdPetugas1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdPetugas1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdPetugas1KeyPressed

    private void BtnPtg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPtg1ActionPerformed
        // TODO add your handling code here:
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPtg1ActionPerformed

    private void BtnPtg1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPtg1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnPtg1KeyPressed

    private void KeluhanSaatIniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanSaatIniKeyPressed
//        Valid.pindah2(evt,Hubungan,RPS);
    }//GEN-LAST:event_KeluhanSaatIniKeyPressed

    private void NoTelponKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoTelponKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoTelponKeyPressed

    private void KondisiSaatIniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KondisiSaatIniKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KondisiSaatIniKeyPressed

    private void TindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindakanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TindakanKeyPressed

    private void GCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GCSKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NadiKeyPressed

    private void TDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuhuKeyPressed

    private void PernafasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PernafasanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PernafasanKeyPressed

    private void PernafasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PernafasanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PernafasanActionPerformed

    private void SaturasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaturasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SaturasiActionPerformed

    private void SaturasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SaturasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SaturasiKeyPressed

    private void DiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaKeyPressed

    private void RekomendasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RekomendasiKeyPressed
//        Valid.pindah(evt,Alergi,Kesadaran);
    }//GEN-LAST:event_RekomendasiKeyPressed

    private void SaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SaranKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SaranKeyPressed

    private void tbPasienTelponMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienTelponMouseClicked
        if(TabSkrining.getSelectedIndex()==0){
             if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
        }
    }//GEN-LAST:event_tbPasienTelponMouseClicked

    private void tbPasienTelponKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienTelponKeyPressed
//        if(tabMode.getRowCount()!=0){
//            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//                i=tbPetugas.getSelectedColumn();
//                if(i==1){
//                    if(MnKamarInap.isEnabled()==true){
//                        MnKamarInapActionPerformed(null);
//                    }
//                }else if(i==2){
//                    if(akses.gettindakan_ralan()==true){
//                        MnRawatJalanActionPerformed(null);
//                    }
//                }else if(i==3){
//                    if(akses.getberi_obat()==true){
//                        MnPemberianObatActionPerformed(null);
//                    }
//                }else if(i==4){
//                    if(akses.getperiksa_lab()==true){
//                        MnPeriksaLabActionPerformed(null);
//                    }
//                }else if(i==5){
//                    if(akses.getrujukan_masuk()==true){
//                        MnRujukMasukActionPerformed(null);
//                    }
//                }
//            }else if(evt.getKeyCode()==KeyEvent.VK_L){
//                MnBarcodeRM9ActionPerformed(null);
//            }else if(evt.getKeyCode()==KeyEvent.VK_Z){
//                MnSPBK1ActionPerformed(null);
//            }else if(evt.getKeyCode()==KeyEvent.VK_W){
//                MnLembarRalanActionPerformed(null);
//            }
//        }
    }//GEN-LAST:event_tbPasienTelponKeyPressed

    private void tbPasienTelponKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienTelponKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPasienTelponKeyReleased

    private void tbPasienRujukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienRujukMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPasienRujukMouseClicked

    private void tbPasienRujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienRujukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPasienRujukKeyPressed

    private void tbPasienRujukKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienRujukKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPasienRujukKeyReleased

    private void NmPenelpon1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmPenelpon1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmPenelpon1KeyPressed

    private void KeluhanSaatIni1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanSaatIni1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeluhanSaatIni1KeyPressed

    private void KondisiSaatIni1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KondisiSaatIni1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KondisiSaatIni1KeyPressed

    private void Tindakan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tindakan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tindakan1KeyPressed

    private void GCS1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GCS1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_GCS1KeyPressed

    private void Nadi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Nadi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nadi1KeyPressed

    private void TD1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TD1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TD1KeyPressed

    private void Suhu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Suhu1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Suhu1KeyPressed

    private void Pernafasan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pernafasan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pernafasan1ActionPerformed

    private void Pernafasan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Pernafasan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pernafasan1KeyPressed

    private void Saturasi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Saturasi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Saturasi1ActionPerformed

    private void Saturasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Saturasi1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Saturasi1KeyPressed

    private void ChkInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput1ActionPerformed
        // TODO add your handling code here:
        isForm2();
    }//GEN-LAST:event_ChkInput1ActionPerformed

    private void KeluhanSaatIni2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeluhanSaatIni2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KeluhanSaatIni2KeyPressed

    private void KondisiSaatIni2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KondisiSaatIni2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KondisiSaatIni2KeyPressed

    private void Saran2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Saran2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Saran2KeyPressed

    private void NmPenelpon2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmPenelpon2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmPenelpon2KeyPressed

    private void NmPenelpon3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmPenelpon3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmPenelpon3KeyPressed

    private void Saturasi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Saturasi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Saturasi2ActionPerformed

    private void Saturasi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Saturasi2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Saturasi2KeyPressed

    private void ChkInput3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput3ActionPerformed
        // TODO add your handling code here:
        isForm3();
    }//GEN-LAST:event_ChkInput3ActionPerformed

    private void NmPetugas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NmPetugas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NmPetugas1ActionPerformed

    private void TUmurThKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurThKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //       CmbUmur.requestFocus();
            try {
                Valid.SetTgl(DTPLahir1,Sequel.cariIsi("select DATE_SUB('"+Valid.SetTgl(DTPLahir1.getSelectedItem()+"")+"', interval "+TUmurTh.getText()+" year)"));
            } catch (Exception e) {
                System.out.println(e);
            }
            TUmurBl.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            DTPLahir1.requestFocus();
        }
    }//GEN-LAST:event_TUmurThKeyPressed

    private void TUmurBlKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurBlKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            TUmurHr.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            TUmurTh.requestFocus();
        }
    }//GEN-LAST:event_TUmurBlKeyPressed

    private void TUmurHrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TUmurHrKeyPressed
      
    }//GEN-LAST:event_TUmurHrKeyPressed

    private void DTPLahir1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPLahir1ItemStateChanged
        // TODO add your handling code here:
        tanggal = DTPLahir1.getDate();
        birthday = tanggal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        p = Period.between(birthday,today);
        TUmurTh.setText(String.valueOf(p.getYears()));
        TUmurBl.setText(String.valueOf(p.getMonths()));
        TUmurHr.setText(String.valueOf(p.getDays()));
    }//GEN-LAST:event_DTPLahir1ItemStateChanged

    private void DTPLahir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTPLahir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPLahir1ActionPerformed

    private void CPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPasienActionPerformed
        // TODO add your handling code here:
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }//GEN-LAST:event_CPasienActionPerformed

    private void TNoRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNoRMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRMActionPerformed

    private void tbPasienDatangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPasienDatangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPasienDatangMouseClicked

    private void tbPasienDatangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienDatangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPasienDatangKeyPressed

    private void tbPasienDatangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPasienDatangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbPasienDatangKeyReleased

    private void TPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TPasienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPasienActionPerformed

    private void DTPRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPRegKeyPressed
        //        Valid.pindah(evt,TNoRM,CmbJam);
    }//GEN-LAST:event_DTPRegKeyPressed

    private void DTPRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTPRegActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPRegActionPerformed

    private void ChkLainnyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLainnyaActionPerformed
        if(TNoRM.getText().equals("")||TPasien.getText().equals("")){
            ChkLainnya.setSelected(false);
            JOptionPane.showMessageDialog(null,"Maaf, No Rekam Medis Tidak Boleh Kosong!!!!");
        }else{
            ChkLainnya.setSelected(true);
            isCttn();
            setLainnya();
            tampil();
        }
    }//GEN-LAST:event_ChkLainnyaActionPerformed

    private void TabDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabDataMouseClicked
        //        tampilOrthanc();
    }//GEN-LAST:event_TabDataMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSKriningPasien dialog = new RMSKriningPasien(new javax.swing.JFrame(), true);
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
    private widget.TextBox Alamat;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnPtg;
    private widget.Button BtnPtg1;
    private widget.Button BtnSimpan;
    private widget.Button CPasien;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkInput1;
    private widget.CekBox ChkInput3;
    private widget.CekBox ChkJln;
    private widget.TextArea ChkKeluhan;
    private widget.TextArea ChkKondisi;
    private widget.CekBox ChkLainnya;
    private widget.TextArea ChkSaran;
    private widget.TextArea ChkTindakan;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbMenit;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPLahir1;
    private widget.Tanggal DTPReg;
    private widget.TextBox Diagnosa;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput2;
    private widget.PanelBiasa FormInput3;
    private widget.PanelBiasa FormInput4;
    private widget.PanelBiasa FormKeluhan;
    private widget.PanelBiasa FormKondisi;
    private widget.PanelBiasa FormSaran;
    private widget.PanelBiasa FormTindakan;
    private widget.TextBox GCS;
    private widget.TextBox GCS1;
    private widget.ComboBox JK;
    private widget.TextBox KdPetugas;
    private widget.TextBox KdPetugas1;
    private widget.TextArea KeluhanSaatIni;
    private widget.TextArea KeluhanSaatIni1;
    private widget.TextArea KeluhanSaatIni2;
    private widget.TextArea KondisiSaatIni;
    private widget.TextArea KondisiSaatIni1;
    private widget.TextArea KondisiSaatIni2;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnLembarSkriningRalan;
    private javax.swing.JMenuItem MnPDFSkriningRalan;
    private widget.TextBox Nadi;
    private widget.TextBox Nadi1;
    private widget.TextBox NmPenelpon;
    private widget.TextBox NmPenelpon1;
    private widget.TextBox NmPenelpon2;
    private widget.TextBox NmPenelpon3;
    private widget.TextBox NmPetugas;
    private widget.TextBox NmPetugas1;
    private widget.TextBox NoTelpon;
    private widget.PanelBiasa PanelAccorCttn;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelInput2;
    private javax.swing.JPanel PanelInput3;
    private widget.TextBox Pernafasan;
    private widget.TextBox Pernafasan1;
    private widget.ComboBox Rekomendasi;
    private widget.TextArea Saran;
    private widget.TextArea Saran2;
    private widget.TextBox Saturasi;
    private widget.TextBox Saturasi1;
    private widget.TextBox Saturasi2;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll13;
    private widget.TextBox Suhu;
    private widget.TextBox Suhu1;
    private widget.TextBox TCari;
    private widget.TextBox TD;
    private widget.TextBox TD1;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private widget.TextBox TUmurBl;
    private widget.TextBox TUmurHr;
    private widget.TextBox TUmurTh;
    private javax.swing.JTabbedPane TabData;
    private javax.swing.JTabbedPane TabSkrining;
    private widget.TextArea Tindakan;
    private widget.TextArea Tindakan1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private javax.swing.JMenuItem ppPasienCorona;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbPasienDatang;
    private widget.Table tbPasienRujuk;
    private widget.Table tbPasienTelpon;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        if(TabSkrining.getSelectedIndex()==0){
            try {
            Valid.tabelKosong(tabMode);
            ps=koneksi.prepareStatement(
                    "select skrining_pasien_telepon.no_rkm_medis,skrining_pasien_telepon.nm_pasien,skrining_pasien_telepon.tgl_lahir,skrining_pasien_telepon.umur,"+
                    "skrining_pasien_telepon.alamat,skrining_pasien_telepon.jk,skrining_pasien_telepon.tanggal,skrining_pasien_telepon.jam,skrining_pasien_telepon.kd_petugas,"+
                    "skrining_pasien_telepon.nm_petugas,skrining_pasien_telepon.kd_ptelepon,skrining_pasien_telepon.nm_ptelepon,skrining_pasien_telepon.nm_penelepon,skrining_pasien_telepon.no_telepon, "+
                    "skrining_pasien_telepon.gcs,skrining_pasien_telepon.suhu,skrining_pasien_telepon.td,skrining_pasien_telepon.nadi,skrining_pasien_telepon.pernafasan,skrining_pasien_telepon.saturasi, "+
                    "skrining_pasien_telepon.diagnosa,skrining_pasien_telepon.rekomendasi,skrining_pasien_telepon.keluhan,skrining_pasien_telepon.tindakan,skrining_pasien_telepon.kondisi,skrining_pasien_telepon.saran "+
                    "from skrining_pasien_telepon where "+
                    "skrining_pasien_telepon.tanggal between ? and ? and skrining_pasien_telepon.no_rkm_medis like ? or "+
                    "skrining_pasien_telepon.tanggal between ? and ? and skrining_pasien_telepon.nm_pasien like ? or "+
                    "skrining_pasien_telepon.tanggal between ? and ? and skrining_pasien_telepon.nm_ptelepon like ? or "+
                    "skrining_pasien_telepon.tanggal between ? and ? and skrining_pasien_telepon.nm_petugas like ? order by skrining_pasien_telepon.tanggal desc");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(3,"%"+TCari.getText().trim()+"%");
                ps.setString(4,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(5,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(6,"%"+TCari.getText().trim()+"%");
                ps.setString(7,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(8,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(9,"%"+TCari.getText().trim()+"%");
                ps.setString(10,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(11,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(12,"%"+TCari.getText().trim()+"%");
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("tgl_lahir"),rs.getString("umur"),rs.getString("alamat"),
                        rs.getString("jk"),rs.getString("tanggal"),rs.getString("jam"),rs.getString("kd_petugas"),rs.getString("nm_petugas"),
                        rs.getString("kd_ptelepon"),rs.getString("nm_ptelepon"),rs.getString("nm_penelepon"),rs.getString("no_telepon"),rs.getString("gcs"),
                        rs.getString("suhu"),rs.getString("td"),rs.getString("nadi"),rs.getString("pernafasan"),rs.getString("saturasi")
                        ,rs.getString("diagnosa"),rs.getString("rekomendasi")
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
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
        }
    }

    public void emptTeks() {
        TNoRM.setText("");
        TPasien.setText("");
        DTPLahir1.setDate(new Date());
        Alamat.setText("");
        JK.setSelectedIndex(0);
        DTPReg.setDate(new Date());
        KdPetugas1.setText("");
        NmPetugas1.setText("");
        KdPetugas.setText("");
        NmPetugas.setText("");
        NmPenelpon.setText("");
        NoTelpon.setText("");
        KeluhanSaatIni.setText("");
        KondisiSaatIni.setText("");
        Tindakan.setText("");
        GCS.setText("");
        TD.setText("");
        Suhu.setText("");
        Nadi.setText("");
        Pernafasan.setText("");
        Saturasi.setText("");
        Diagnosa.setText("");
        Rekomendasi.setSelectedIndex(0);
        Saran.setText("");
        
        TNoRM.requestFocus();
    }
    
    private void getData() {
        if(TabSkrining.getSelectedIndex()==0){
            if(tbPasienTelpon.getSelectedRow()!= -1){
                TNoRM.setText(tbPasienTelpon.getValueAt(tbPasienTelpon.getSelectedRow(),0).toString());
                TPasien.setText(tbPasienTelpon.getValueAt(tbPasienTelpon.getSelectedRow(),1).toString());
                
                isCttn();
            }
        }
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH,205));
            FormInput2.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput1.setPreferredSize(new Dimension(WIDTH,20));
            FormInput2.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
    
    private void isForm2(){
        if(ChkInput1.isSelected()==true){
            ChkInput1.setVisible(false);
            PanelInput2.setPreferredSize(new Dimension(WIDTH,200));
            FormInput3.setVisible(true);      
            ChkInput1.setVisible(true);
        }else if(ChkInput1.isSelected()==false){           
            ChkInput1.setVisible(false);            
            PanelInput2.setPreferredSize(new Dimension(WIDTH,20));
            FormInput3.setVisible(false);      
            ChkInput1.setVisible(true);
        }
    }
    
    private void isForm3(){
        if(ChkInput3.isSelected()==true){
            ChkInput3.setVisible(false);
            PanelInput3.setPreferredSize(new Dimension(WIDTH,135));
            FormInput4.setVisible(true);      
            ChkInput3.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput3.setVisible(false);            
            PanelInput3.setPreferredSize(new Dimension(WIDTH,20));
            FormInput4.setVisible(false);      
            ChkInput3.setVisible(true);
        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getsekrining_rawat_jalan());
        BtnHapus.setEnabled(akses.getsekrining_rawat_jalan());
        BtnEdit.setEnabled(akses.getsekrining_rawat_jalan());
        ppPasienCorona.setEnabled(akses.getpasien_corona());
        if(akses.getjml2()>=1){
            KdPetugas.setEditable(false);
            BtnPtg.setEnabled(false);
            KdPetugas.setText(akses.getkode2());
            Sequel.cariIsi("select petugas.nama from petugas where petugas.nip=?", NmPetugas,KdPetugas.getText());
            if(NmPetugas.getText().equals("")){
                KdPetugas.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        }   
    }

    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkJln.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkJln.isSelected()==false){
                    nilai_jam =CmbJam.getSelectedIndex();
                    nilai_menit =CmbMenit.getSelectedIndex();
                    nilai_detik =CmbDetik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                CmbJam.setSelectedItem(jam);
                CmbMenit.setSelectedItem(menit);
                CmbDetik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void isPas(){
//        if(validasiregistrasi.equals("Yes")){
//            if(Sequel.cariInteger("select count(no_rkm_medis) from reg_periksa where no_rkm_medis=? and status_bayar='Belum Bayar' and stts<>'Batal'",TNoRM.getText())>0){
//                JOptionPane.showMessageDialog(rootPane,"Maaf, pasien pada kunjungan sebelumnya memiliki tagihan yang belum di closing.\nSilahkan konfirmasi dengan pihak kasir.. !!");
//            }else{
//                if(validasicatatan.equals("Yes")){
//                    if(Sequel.cariInteger("select count(no_rkm_medis) from catatan_pasien where no_rkm_medis=?",TNoRM.getText())>0){
//                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
////                        catatan.setNoRm(TNoRM.getText());
//                        catatan.setSize(720,330);
//                        catatan.setLocationRelativeTo(internalFrame1);
//                        catatan.setVisible(true);
//                        this.setCursor(Cursor.getDefaultCursor());
//                    }
//                }                    
//                isCekPasien();
//            }
//        }else{
//            if(validasicatatan.equals("Yes")){
//                if(Sequel.cariInteger("select count(no_rkm_medis) from catatan_pasien where no_rkm_medis=?",TNoRM.getText())>0){
//                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//                    catatan.setNoRm(TNoRM.getText());
//                    catatan.setSize(720,330);
//                    catatan.setLocationRelativeTo(internalFrame1);
//                    catatan.setVisible(true);
//                    this.setCursor(Cursor.getDefaultCursor());
//                }
//            }
//            isCekPasien();
//        }        
    }

    private void isCekPasien() {
//        if(!TNoRM.equals("")){
//            try {
//                ps=koneksi.prepareStatement("select nm_pasien,jk,tgl_lahir,nm_ibu from pasien where no_rkm_medis=?");
//                try {
//                    ps.setString(1,TNoRM.getText());
//                    rs=ps.executeQuery();
//                    if(rs.next()){
//                        TPasien.setText(rs.getString("nm_pasien"));
//                        JK.setText(rs.getString("jk"));
//                        Lahir.setText(rs.getString("tgl_lahir"));
//                        Umur.setText(rs.getString("nm_ibu"));
//                    }
//                } catch (Exception e) {
//                    System.out.println("Notif : "+e);
//                } finally{
//                    if(rs!=null){
//                        rs.close();
//                    }
//                    if(ps!=null){
//                        ps.close();
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("Notif : "+e);
//            }
//        }
    }

//    private void hapus() {
//        if(Sequel.queryu2tf("delete from skrining_rawat_jalan where tanggal=? and jam=? and no_rkm_medis=?",3,new String[]{
//                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),1).toString(),tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()
//            })==true){
//            tampil();
//        }
//    }
    
    private void isCttn(){
        if(ChkLainnya.isSelected()==true){
            ChkLainnya.setVisible(false);
//            PanelAccorCttn.setPreferredSize(new Dimension(internalFrame7.getWidth()-300,HEIGHT));
            PanelAccorCttn.setPreferredSize(new Dimension(470, HEIGHT));
            TabData.setVisible(true);  
            ChkLainnya.setVisible(true);
        }else if(ChkLainnya.isSelected()==false){    
            ChkLainnya.setVisible(false);
            PanelAccorCttn.setPreferredSize(new Dimension(15,HEIGHT));
            TabData.setVisible(false);
            ChkLainnya.setVisible(true);
        }
    }
    
    private void setLainnya(){
        if(TabData.isVisible()==true){
            if(!TNoRM.getText().equals("")){
                 try {
            ps1=koneksi.prepareStatement(
                    "select skrining_pasien_telepon.keluhan,skrining_pasien_telepon.kondisi,skrining_pasien_telepon.tindakan,skrining_pasien_telepon.saran "+
                    "from skrining_pasien_telepon where skrining_pasien_telepon.no_rkm_medis=?");
            try {
                ps1.setString(1,TNoRM.getText());
                rs1=ps1.executeQuery();
                while(rs1.next()){
                    ChkKeluhan.setText(rs1.getString("keluhan"));
                    ChkKondisi.setText(rs1.getString("kondisi"));
                    ChkTindakan.setText(rs1.getString("tindakan"));
                    ChkSaran.setText(rs1.getString("saran"));
                }
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs1!=null){
                    rs1.close();
                }
                if(ps1!=null){
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
            }
        }
    }

}
