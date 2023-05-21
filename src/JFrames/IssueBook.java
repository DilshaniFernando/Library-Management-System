/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class IssueBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBook
     */
    public IssueBook() {
        initComponents();
    }
    
    //fetch the details from the database, display book panel
    public void getBookDetails(){
        int BookId = Integer.parseInt(txt_bookid.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ?");
            
            pst.setInt(1,BookId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                lbl_bookId.setText(rs.getString("book_Id"));
                lbl_bookName.setText(rs.getString("book_Name"));
                lbl_author.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));
            }else{
                lbl_bookError.setText("Invalid Book Id");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //fetch student from database, dispaly student panel
    public void getstudentDetails(){
        int BookId = Integer.parseInt(txt_studentId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from student_details where student_id = ?");
            
            pst.setInt(1,BookId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                lbl_studentId.setText(rs.getString("student_Id"));
                lbl_studentName.setText(rs.getString("name"));
                lbl_course.setText(rs.getString("course"));
                lbl_branch.setText(rs.getString("branch"));
            }else{
                lbl_studentError.setText("Invalid Student Id");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //insert issue book details to database
    public boolean issueBook(){
        boolean isIssued = false;
        int BookId = Integer.parseInt(txt_bookid.getText());
        int StudentId = Integer.parseInt(txt_studentId.getText());
        String BookName = lbl_bookName.getText();
        String StudentName = lbl_studentName.getText();
        
        Date uIssueDate = date_issuedate.getDatoFecha();
        Date uDueDate = date_duedate.getDatoFecha();
        
        Long l1 = uIssueDate.getTime();
        Long l2 = uDueDate.getTime();
        
        java.sql.Date sIssuedate = new java.sql.Date(l1);
        java.sql.Date sDuedate = new java.sql.Date(l2);
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "insert into issue_book_details(book_id, Book_name,student_id, Student_name, issue_date, due_date, ststus) values(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, BookId);
            pst.setString(2, BookName);
            pst.setInt(3, StudentId);
            pst.setString(4, StudentName);
            pst.setDate(5, sIssuedate);
            pst.setDate(6, sDuedate);
            pst.setString(7,"Pending");
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                isIssued = true;
            }else{
                isIssued = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return isIssued;
    }
    
    //updating book count
    public void updateBookCount(){
        
        int BookId = Integer.parseInt(txt_bookid.getText());
        
        try{
            Connection con  = DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity-1 where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, BookId);
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                JOptionPane.showMessageDialog(this, "Book count Updated");
                int initialCount = Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialCount - 1));
            }else{
                JOptionPane.showMessageDialog(this, "Book count can not Updated");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //checkin whether book already allocated or not
    public boolean isAlreadyIssued(){
        boolean isAlreadyIssued = false;
        int BookId = Integer.parseInt(txt_bookid.getText());
        int StudentId = Integer.parseInt(txt_studentId.getText());
        
        try{
            Connection con  = DBConnection.getConnection();
            String sql = "select * from issue_book_details where book_id = ? and student_id = ? and ststus = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, BookId);
            pst.setInt(2, StudentId);
            pst.setString(3, "Pending");
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                isAlreadyIssued = true;  
            }else{
                isAlreadyIssued = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return isAlreadyIssued;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panek_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbl_bookError = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbl_branch = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbl_studentError = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbl_studentId = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_bookid = new app.bolivia.swing.JCTextField();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel12 = new javax.swing.JLabel();
        date_duedate = new rojeru_san.componentes.RSDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        date_issuedate = new rojeru_san.componentes.RSDateChooser();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panek_main.setBackground(new java.awt.Color(255, 255, 255));
        panek_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel3.setText("  Back");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 50));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel1.setText("  Book Details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 300, -1));

        lbl_bookError.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(255, 255, 51));
        jPanel1.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 570, 250, 25));

        lbl_quantity.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jPanel1.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, 220, 25));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Book Name: ");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Author: ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Book Id: ");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        lbl_bookId.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jPanel1.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 220, 25));

        lbl_bookName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jPanel1.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 220, 25));

        lbl_author.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jPanel1.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 220, 25));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantity:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, -1));

        panek_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 810));

        jPanel4.setBackground(new java.awt.Color(102, 102, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 330, 5));

        lbl_branch.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jPanel4.add(lbl_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 490, 220, 25));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Student Name: ");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, -1, -1));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Course: ");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        lbl_studentError.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        lbl_studentError.setForeground(new java.awt.Color(255, 255, 51));
        jPanel4.add(lbl_studentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, 240, 25));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Student Id: ");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        lbl_studentId.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jPanel4.add(lbl_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 220, 25));

        lbl_studentName.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jPanel4.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 220, 25));

        lbl_course.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jPanel4.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 420, 220, 25));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel16.setText("  Student Details");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Branch:");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, -1));

        panek_main.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 420, 810));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 25)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 51));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel15.setText("  Issue Books");
        panek_main.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 120, -1, -1));

        jPanel5.setBackground(new java.awt.Color(255, 51, 5));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panek_main.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 180, 270, 5));

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("X");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel7.add(jLabel10);

        panek_main.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1340, 0, 70, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 51, 51));
        jLabel11.setText("Book Id:");
        panek_main.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 290, -1, -1));

        txt_bookid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_bookid.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_bookid.setPlaceholder("Enter Book Id...");
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });
        panek_main.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 280, 350, 40));

        txt_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 51, 51)));
        txt_studentId.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        txt_studentId.setPlaceholder("Enter Student Id...");
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        txt_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIdActionPerformed(evt);
            }
        });
        panek_main.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 370, 350, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 0, 17)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setText("Due Date:");
        panek_main.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 550, -1, -1));

        date_duedate.setColorBackground(new java.awt.Color(255, 51, 51));
        date_duedate.setColorForeground(new java.awt.Color(0, 0, 0));
        date_duedate.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        date_duedate.setPlaceholder("Select Due Data");
        panek_main.add(date_duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 540, 340, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 0, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setText("Student Id:");
        panek_main.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 380, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 0, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("Issue Date:");
        panek_main.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 470, -1, -1));

        date_issuedate.setColorBackground(new java.awt.Color(255, 51, 51));
        date_issuedate.setColorForeground(new java.awt.Color(0, 0, 0));
        date_issuedate.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        date_issuedate.setPlaceholder("Select Issue Data");
        panek_main.add(date_issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 460, 340, -1));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle1.setText("issue Book");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        panek_main.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 630, 410, 60));

        getContentPane().add(panek_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1420, 810));

        setSize(new java.awt.Dimension(1411, 803));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        if(!txt_bookid.getText().equals("")){
            getBookDetails();
        }
        
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
        if(!txt_studentId.getText().equals("")){
            getstudentDetails();
        }
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdActionPerformed

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        if(lbl_quantity.getText().equals("0")){
                JOptionPane.showMessageDialog(this, "Book is Not Available");   
        }else{
            if(isAlreadyIssued() == false){
                if(issueBook() == true){
                    JOptionPane.showMessageDialog(this, "Book Issued Successfully");
                    updateBookCount();
                }else{  
                    JOptionPane.showMessageDialog(this, "Can not Issue Book");
                }
            }else{
                JOptionPane.showMessageDialog(this, "Student already has this book");
            }
        } 
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_duedate;
    private rojeru_san.componentes.RSDateChooser date_issuedate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_branch;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentError;
    private javax.swing.JLabel lbl_studentId;
    private javax.swing.JLabel lbl_studentName;
    private javax.swing.JPanel panek_main;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private app.bolivia.swing.JCTextField txt_bookid;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
