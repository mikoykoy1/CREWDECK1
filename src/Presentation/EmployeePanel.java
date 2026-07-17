package Presentation;

import java.awt.Frame;
import javax.swing.SwingUtilities;
import java.awt.Window;
import DAO.DBConnection;
import javax.swing.table.DefaultTableModel;
import Service.EmployeeService;
import Model.Employee;
import Model.User;
import Presentation.AddRecordDialog;
import Presentation.UpdateRecordDialog;
import Service.UserService;
import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;


public class EmployeePanel extends javax.swing.JPanel {
    
    private static final EmployeeService service = new EmployeeService();
    DefaultTableModel model;
    private Integer selectedEmployeeId = null;
    
    public EmployeePanel() {
        initComponents();
        loadTable();

    // ---------- SEARCH PLACEHOLDER ----------
    searchTxt.setText("Search employees...");
    searchTxt.setForeground(Color.GRAY);

    searchTxt.addFocusListener(new java.awt.event.FocusAdapter() {

        @Override
        public void focusGained(java.awt.event.FocusEvent e) {

            if (searchTxt.getText().equals("Search employees...")) {
                searchTxt.setText("");
                searchTxt.setForeground(Color.BLACK);
            }

        }

        @Override
        public void focusLost(java.awt.event.FocusEvent e) {

            if (searchTxt.getText().isEmpty()) {
                searchTxt.setText("Search employees...");
                searchTxt.setForeground(Color.GRAY);
            }

        }

    });

    // ---------- LIVE SEARCH ----------
    searchTxt.getDocument().addDocumentListener(
        new javax.swing.event.DocumentListener() {

            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                search();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                search();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                search();
            }
        });    

    }
    
            //LOAD TABLE
            public void loadTable() {
                try {

                    EmployeeService empService = new EmployeeService();
                    DefaultTableModel model = empService.getTableModel();

                    if (model != null) {
                        employeeTable.setModel(model); // Set the model to your JTable component
                    }

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error refreshing table: " + e.getMessage());
                }
        }
            private void search() {

            javax.swing.table.TableRowSorter<
                    javax.swing.table.TableModel> sorter =
                    new javax.swing.table.TableRowSorter<>(employeeTable.getModel());

            employeeTable.setRowSorter(sorter);

            String text = searchTxt.getText();

            if (text.trim().length() == 0 ||
                    text.equals("Search employees...")) {

                sorter.setRowFilter(null);

            } else {

                sorter.setRowFilter(
                        javax.swing.RowFilter.regexFilter("(?i)" + text));

            }

        }
    
    

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        EmployeeTopPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        EmployeeBodyPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        updateBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        searchTxt = new javax.swing.JTextField();
        departmentFilter = new javax.swing.JComboBox<>();
        departmentFilter1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setBackground(new java.awt.Color(156, 163, 175));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("CREWDECK");

        jLabel2.setBackground(new java.awt.Color(156, 163, 175));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("/");

        jLabel3.setBackground(new java.awt.Color(17, 24, 39));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Employee");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addContainerGap(700, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 40));

        EmployeeTopPanel.setBackground(new java.awt.Color(255, 255, 255));
        EmployeeTopPanel.setPreferredSize(new java.awt.Dimension(869, 150));

        javax.swing.GroupLayout EmployeeTopPanelLayout = new javax.swing.GroupLayout(EmployeeTopPanel);
        EmployeeTopPanel.setLayout(EmployeeTopPanelLayout);
        EmployeeTopPanelLayout.setHorizontalGroup(
            EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
        );
        EmployeeTopPanelLayout.setVerticalGroup(
            EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(EmployeeTopPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 40));

        EmployeeBodyPanel.setBackground(new java.awt.Color(255, 255, 255));
        EmployeeBodyPanel.setPreferredSize(new java.awt.Dimension(869, 465));
        EmployeeBodyPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 102));

        employeeTable.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        employeeTable.setForeground(new java.awt.Color(204, 204, 204));
        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "User ID", "Name", "Department", "Salary", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(employeeTable);

        EmployeeBodyPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 850, 310));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel6.setBackground(new java.awt.Color(17, 24, 39));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Employee");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(776, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        EmployeeBodyPanel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 850, -1));

        updateBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        EmployeeBodyPanel.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, -1, -1));

        deleteBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deleteBtn.setText("Remove");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        EmployeeBodyPanel.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, -1, -1));

        addBtn.setBackground(new java.awt.Color(0, 153, 255));
        addBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        EmployeeBodyPanel.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        searchTxt.setText("search");
        EmployeeBodyPanel.add(searchTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 120, 30));

        departmentFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Information Technology", "Finance", "Human Resources", "Operations", "Sales" }));
        EmployeeBodyPanel.add(departmentFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, 170, 30));

        departmentFilter1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Information Technology", "Finance", "Human Resources", "Operations", "Sales" }));
        EmployeeBodyPanel.add(departmentFilter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 170, 30));

        jScrollPane3.setViewportView(EmployeeBodyPanel);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 890, 530));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        jLabel10.setBackground(new java.awt.Color(56, 58, 64));
        jLabel10.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("CREWDECK — Employee Management System  © 2026  —  v1.0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(232, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 900, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int selectedRow = employeeTable.getSelectedRow();
        
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this employee? This will permanently wipe out their security credentials and profile record.", 
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Object cellValue = employeeTable.getValueAt(selectedRow, 0);
                    int employeeId = Integer.parseInt(cellValue.toString());
                    service.removeEmployee(employeeId);
                    loadTable();
                    JOptionPane.showMessageDialog(this, 
                    "Employee records deleted successfully.");
                    
                } catch (NumberFormatException | NullPointerException ex) {
                    JOptionPane.showMessageDialog(this, 
                    "Error parsing Employee ID from selected row: " + ex.getMessage(), 
                    "Data Error", 
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        } 
        else {
            JOptionPane.showMessageDialog(this, 
            "Please click on an employee row in the table first to select them.", 
            "No Row Selected", 
            JOptionPane.WARNING_MESSAGE);
        }       
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void employeeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeTableMouseClicked
                int selectedRow = employeeTable.getSelectedRow();
    
    // If no row is actually selected, reset selection and exit
        if (selectedRow < 0) {
        selectedEmployeeId = null;
        updateBtn.setEnabled(false);
        deleteBtn.setEnabled(false);
        return;
        }

        try {
        // 1. Get the ID from the first column (Column 0) of the selected row
            Object idValue = employeeTable.getValueAt(selectedRow, 0);
            selectedEmployeeId = Integer.parseInt(idValue.toString());

        // 2. Enable your action buttons now that a valid row is selected
            updateBtn.setEnabled(true);
            deleteBtn.setEnabled(true);


        } catch (NumberFormatException | NullPointerException e) {
            System.err.println("Error reading selected row: " + e.getMessage());
            selectedEmployeeId = null;
            updateBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }
    }//GEN-LAST:event_employeeTableMouseClicked

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        if (selectedEmployeeId == null) {
            JOptionPane.showMessageDialog(this, 
            "Please select an employee from the table first.", 
            "No Selection", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Open the update dialog for this specific employee
    openUpdateDialog(selectedEmployeeId);
}

// Helper method to open the Update Dialog
    private void openUpdateDialog(int employeeId) {
        try {
            // Fetch the employee's current database details
            Employee emp = service.fetchRecord(employeeId); 
        
            if (emp != null) {
                // Pass the employee data to your Update dialog
                UpdateRecordDialog updateDialog = new UpdateRecordDialog(
                    (Frame) SwingUtilities.getWindowAncestor(this), 
                    true, 
                    emp,
                    this
                );
                updateDialog.setLocationRelativeTo(this);
                updateDialog.setVisible(true);
            
            // Refresh table after the dialog closes in case they made changes
                loadTable(); 
            } else {
                JOptionPane.showMessageDialog(this, "Employee record not found.");
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error opening record: " + e.getMessage());
        }

    }//GEN-LAST:event_updateBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        AddRecordDialog addDialog = new AddRecordDialog((Frame) parentWindow, true);
        
        addDialog.setLocationRelativeTo(null);
        addDialog.setVisible(true);
        loadTable();
    }//GEN-LAST:event_addBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EmployeeBodyPanel;
    private javax.swing.JPanel EmployeeTopPanel;
    private javax.swing.JButton addBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JComboBox<String> departmentFilter;
    private javax.swing.JComboBox<String> departmentFilter1;
    private javax.swing.JTable employeeTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
