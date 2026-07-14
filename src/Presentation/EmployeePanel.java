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
    
    public EmployeePanel() {
        initComponents();
        populateRolesDropdown();
    }
    
    public void loadTable(){  
        
        EmployeeService service = new EmployeeService();
        employeeTable.setModel(service.getTableModel());
    }
    
    private void populateRolesDropdown() {
    roleCmb.removeAllItems(); // Clear default placeholders
    
    // Explicitly query your database roles metadata
    String sql = "SELECT `name` FROM `roles`";
    try (
        Connection conn = DBConnection.GetConnection();
        java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
        java.sql.ResultSet rs = stmt.executeQuery()
    ) {
        while (rs.next()) {
            roleCmb.addItem(rs.getString("name"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,"Could not load database roles into UI: " + e.getMessage());
        // Fallback options in case database connection fails during startup
        roleCmb.addItem("Standard_Employee");
        roleCmb.addItem("HR_Admin");
    }
}
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EmployeeTopPanel = new javax.swing.JPanel();
        idTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nameTxt = new javax.swing.JTextField();
        deleteBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        departmentTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        contactNumTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        salaryTxt = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        roleCmb = new javax.swing.JComboBox<>();
        dialogBtn = new javax.swing.JButton();
        EmployeeBodyPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        EmployeeTopPanel.setBackground(new java.awt.Color(51, 51, 51));
        EmployeeTopPanel.setPreferredSize(new java.awt.Dimension(869, 200));

        idTxt.setEditable(false);
        idTxt.setBackground(new java.awt.Color(102, 102, 102));
        idTxt.setForeground(new java.awt.Color(204, 204, 204));
        idTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxtActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("[ ID: ]");

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("[ Name: ]");

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("[ Role: ]");

        nameTxt.setBackground(new java.awt.Color(102, 102, 102));
        nameTxt.setForeground(new java.awt.Color(204, 204, 204));

        deleteBtn.setBackground(new java.awt.Color(102, 102, 102));
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setText("Remove Employee");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("[ Department: ]");

        departmentTxt.setBackground(new java.awt.Color(102, 102, 102));
        departmentTxt.setForeground(new java.awt.Color(204, 204, 204));

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("[ Contact Number ]");

        contactNumTxt.setBackground(new java.awt.Color(102, 102, 102));
        contactNumTxt.setForeground(new java.awt.Color(204, 204, 204));

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("[ Email ]");

        emailTxt.setBackground(new java.awt.Color(102, 102, 102));
        emailTxt.setForeground(new java.awt.Color(204, 204, 204));

        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("[ Salary ]");

        salaryTxt.setBackground(new java.awt.Color(102, 102, 102));
        salaryTxt.setForeground(new java.awt.Color(204, 204, 204));

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear Form");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("Employee Records");

        roleCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        roleCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roleCmbActionPerformed(evt);
            }
        });

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
                    .addComponent(jLabel8)
                    .addGroup(EmployeeTopPanelLayout.createSequentialGroup()
                        .addGroup(EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EmployeeTopPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roleCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EmployeeTopPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salaryTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19)
                        .addGroup(EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clearBtn)
                            .addGroup(EmployeeTopPanelLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(departmentTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(contactNumTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(61, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(departmentTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(contactNumTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roleCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(salaryTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(clearBtn)))
                .addGap(18, 18, 18))
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

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
    
    }//GEN-LAST:event_idTxtActionPerformed

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
                // 1. Extract the selected row index and validate against empty clicks
        int index = employeeTable.getSelectedRow();
        if (index < 0) {
            return; 
        }

        try {
            // 2. Safely extract the primary key ID from column index 0
            String selectedId = employeeTable.getValueAt(index, 0).toString();
            int employeeId = Integer.parseInt(selectedId);

            // 3. FIX: Call fetchRecord() to match your EmployeeService signature
            Employee emp = service.fetchRecord(employeeId);

            if (emp != null) {
                // 4. FIX: Use your actual streamlined model getter methods
                idTxt.setText(String.valueOf(emp.getUserId()));
                nameTxt.setText(emp.getName());

                // If your view form uses these fields, populate them safely (handling potential null strings)
                //if (emp.getPosition() != null) positiontTxt.setText(emp.getPosition());
                if (emp.getDepartment() != null) departmentTxt.setText(emp.getDepartment());
                if (emp.getContactNum() != null) contactNumTxt.setText(emp.getContactNum());

                // Reaches into the nested User sub-object to display their security identity email
                if (emp.getUser() != null) {
                    emailTxt.setText(emp.getUser().getEmail());
                }

                // Populates your newly updated double salary field cleanly into text format
                salaryTxt.setText(String.valueOf(emp.getSalary()));
            }

        } catch (NumberFormatException | NullPointerException ex) {
            // Graceful error fallback to keep the GUI responsive
            System.err.println("Error rendering row selection: " + ex.getMessage());
        }
    }//GEN-LAST:event_employeeTableMouseClicked

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // 1. Get the currently selected row from the JTable
        int selectedRow = employeeTable.getSelectedRow();
    
    // 2. Ensure a row is actually selected
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an employee from the table first.", "No Row Selected", 
            JOptionPane.WARNING_MESSAGE);
        return;
        }
    
        try {
        // 3. Extract and parse the employee ID from column index 0
            Object cellValue = employeeTable.getValueAt(selectedRow, 0);
            int employeeId = Integer.parseInt(cellValue.toString());
        
        // 4. Fetch the full record from your business layer
            Employee existingEmp = service.fetchRecord(employeeId); 
        
            if (existingEmp != null) {
            // 5. Safely get the containing Frame parent reference
                java.awt.Window parentWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
            
            // 6. Construct and display the Dialog modal with the record metadata
                UpdateRecordDialog dialog = new UpdateRecordDialog((java.awt.Frame) parentWindow, true, existingEmp);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            
            // 7. Refresh your data presentation table after the updates are committed/disposed
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "The selected employee record could not be found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        
        } catch (NumberFormatException | NullPointerException ex) {
            JOptionPane.showMessageDialog(this, 
            "Error parsing Employee ID from selected row: " + ex.getMessage(), "Data Error", 
            JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_updateBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        idTxt.setText("");
        nameTxt.setText("");      
        departmentTxt.setText("");
        contactNumTxt.setText("");
        emailTxt.setText("");
        salaryTxt.setText("");
    }//GEN-LAST:event_clearBtnActionPerformed

    private void dialogBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dialogBtnActionPerformed
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        AddRecordDialog addDialog = new AddRecordDialog((Frame) parentWindow, true);
        
        addDialog.setLocationRelativeTo(null);
        addDialog.setVisible(true);
        loadTable();
    }//GEN-LAST:event_dialogBtnActionPerformed

    private void roleCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roleCmbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roleCmbActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EmployeeBodyPanel;
    private javax.swing.JPanel EmployeeTopPanel;
    private javax.swing.JButton clearBtn;
    private javax.swing.JTextField contactNumTxt;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JTextField departmentTxt;
    private javax.swing.JButton dialogBtn;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTable employeeTable;
    private javax.swing.JTextField idTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameTxt;
    private javax.swing.JComboBox<String> roleCmb;
    private javax.swing.JTextField salaryTxt;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
