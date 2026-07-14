package Presentation;

import java.awt.Frame;
import javax.swing.SwingUtilities;
import java.awt.Window;
import DAO.DBConnection;
import javax.swing.table.DefaultTableModel;
import Service.EmployeeService;
import Model.Employee;
import Model.User;
import Service.UserService;
import java.sql.*;
import javax.swing.JOptionPane;


public class EmployeePanel extends javax.swing.JPanel {
    
    private static final EmployeeService service = new EmployeeService();
    DefaultTableModel model;
    private Integer selectedEmployeeId = null;
    
    public EmployeePanel() {
        initComponents();
        
    }
    
    public void loadTable(){  
        
        EmployeeService service = new EmployeeService();
        employeeTable.setModel(service.getTableModel());
    }
    
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EmployeeTopPanel = new javax.swing.JPanel();
        deleteBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        dialogBtn = new javax.swing.JButton();
        EmployeeBodyPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        EmployeeTopPanel.setBackground(new java.awt.Color(51, 51, 51));
        EmployeeTopPanel.setPreferredSize(new java.awt.Dimension(869, 200));

        deleteBtn.setBackground(new java.awt.Color(102, 102, 102));
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Remove Employee");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Employee Records");

        dialogBtn.setBackground(new java.awt.Color(102, 102, 255));
        dialogBtn.setForeground(new java.awt.Color(255, 255, 255));
        dialogBtn.setText("Add Employee");
        dialogBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dialogBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EmployeeTopPanelLayout = new javax.swing.GroupLayout(EmployeeTopPanel);
        EmployeeTopPanel.setLayout(EmployeeTopPanelLayout);
        EmployeeTopPanelLayout.setHorizontalGroup(
            EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeTopPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EmployeeTopPanelLayout.createSequentialGroup()
                        .addComponent(dialogBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateBtn))
                    .addComponent(jLabel8))
                .addContainerGap(483, Short.MAX_VALUE))
        );
        EmployeeTopPanelLayout.setVerticalGroup(
            EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeeTopPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dialogBtn)
                    .addComponent(deleteBtn)
                    .addComponent(updateBtn))
                .addGap(95, 95, 95))
        );

        add(EmployeeTopPanel, java.awt.BorderLayout.PAGE_START);

        EmployeeBodyPanel.setBackground(new java.awt.Color(102, 102, 102));
        EmployeeBodyPanel.setPreferredSize(new java.awt.Dimension(869, 465));
        EmployeeBodyPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(new java.awt.Color(102, 102, 102));

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

        jScrollPane1.setViewportView(jScrollPane2);

        EmployeeBodyPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(EmployeeBodyPanel, java.awt.BorderLayout.CENTER);
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
        javax.swing.JOptionPane.showMessageDialog(this, 
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
                    (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this), 
                    true, 
                    emp
                );
                updateDialog.setLocationRelativeTo(this);
                updateDialog.setVisible(true);
            
                // Refresh table after the dialog closes in case they made changes
                loadTable(); 
            } else {
               javax.swing.JOptionPane.showMessageDialog(this, "Employee record not found.");
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error opening record: " + e.getMessage());
        }

    }//GEN-LAST:event_updateBtnActionPerformed

    private void dialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialogBtnActionPerformed
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        AddRecordDialog addDialog = new AddRecordDialog((Frame) parentWindow, true);
        
        addDialog.setLocationRelativeTo(null);
        addDialog.setVisible(true);
        loadTable();
    }//GEN-LAST:event_dialogBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EmployeeBodyPanel;
    private javax.swing.JPanel EmployeeTopPanel;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton dialogBtn;
    private javax.swing.JTable employeeTable;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
