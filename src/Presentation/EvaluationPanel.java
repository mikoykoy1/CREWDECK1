package Presentation;


import Model.Employee;
import Model.EmployeeStatus;
import Model.Period;
import Service.EmployeeService;
import Service.EvaluationService;
import Service.UserSession;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.border.Border;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author CpELaboratory 216
 */
public class EvaluationPanel extends javax.swing.JPanel {
    private final EvaluationService evalService = new EvaluationService();
    private final EmployeeService empService = new EmployeeService();
    private List<Employee> employeeDropdownList = new ArrayList<>();
    private final Border defaultBorder = BorderFactory.createLineBorder(new Color(229, 230, 235), 1); // Light Gray
    private final Border activeBorder = BorderFactory.createLineBorder(new Color(37, 99, 235), 1);   // Electric Blue
    
    public EvaluationPanel() {
        initComponents();
        updateRating(sliderEvaluation.getValue());
        lblScore.setText(String.valueOf(sliderEvaluation.getValue()));
        sliderEvaluation.setPaintLabels(false);
        sliderEvaluation.setPaintTicks(false);
        periodCmb.setModel(new DefaultComboBoxModel(Period.values()));  
        sliderEvaluation.addChangeListener(e -> {
            int score = sliderEvaluation.getValue();
            lblScore.setText(String.valueOf(score));
            updateRating(score);
        });

             // Automatically fill out the dropdown search target list on load
            populateEmployeeComboBox();

            // Set up an action listener so clicking a new employee refreshes their history log
            employeeJcom.addActionListener(e -> loadEvaluationHistoryTable());
        
            addFocusBorder(employeeJcom);
            addFocusBorder(remarksTxt); // Replace with your actual text area name
            addFocusBorder(periodCmb);
          
    }
    
    
    
    private void populateEmployeeComboBox() {
        employeeJcom.removeAllItems();
        try {
            // Fetch current active personnel
            employeeDropdownList = empService.fetchAllRecords(); 

            for (Employee emp : employeeDropdownList) {
                // Display an easily searchable presentation option string
                employeeJcom.addItem(emp.getName() + " (ID: " + emp.getUserId() + ")");
            }

            // Load the history log grid for whichever employee is highlighted first.
            if (!employeeDropdownList.isEmpty()) {
                loadEvaluationHistoryTable();
            }
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Failed to load employee list: " + ex.getMessage());
        }
    }
    
    private void loadEvaluationHistoryTable() {
        int selectedIndex = employeeJcom.getSelectedIndex();

        if (selectedIndex < 0 || selectedIndex >= employeeDropdownList.size()) {
            return;
        }

        Employee selectedEmp = employeeDropdownList.get(selectedIndex);
        int currentUserId = UserSession.getInstance().getLoggedInUser().getId();

        // Use the peer-filtered data model so users see interactions relative to the selection context
        jTable2.setModel(
            evalService.getFilteredEvaluationsModel(selectedEmp.getUserId(), currentUserId)
        );

        // Colorize based on the Text Rating string column (Column Index 4 represents the score metric text)
        jTable2.getColumnModel().getColumn(4).setCellRenderer(
            new javax.swing.table.DefaultTableCellRenderer() {
                @Override
                public java.awt.Component getTableCellRendererComponent(
                        javax.swing.JTable table,
                        Object value,
                        boolean isSelected,
                        boolean hasFocus,
                        int row,
                        int column) {

                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    if (value == null) return this;
                    String text = value.toString();

                    // Custom paint operations matching score properties
                    if (text.startsWith("9") || text.equals("100 / 100") || text.contains("Outstanding")) {
                        setForeground(new Color(34, 197, 94)); // Green
                    } else if (text.startsWith("8")) {
                        setForeground(new Color(37, 99, 235)); // Blue
                    } else if (text.startsWith("7")) {
                        setForeground(new Color(249, 115, 22)); // Orange
                    } else {
                        setForeground(new Color(239, 68, 68)); // Red
                    }

                    setHorizontalAlignment(CENTER);
                    return this;
                }
            });
    }
    

    // Method to determine rating label
    private void updateRating(int score) {
        String rating;
        Color color;

            if (score >= 90) {
                rating = "Outstanding";
                color = new Color(34, 197, 94);      // Green
            } else if (score >= 80) {
                rating = "Very Satisfactory";
                color = new Color(37, 99, 235);      // Blue
            } else if (score >= 70) {
                rating = "Satisfactory";
                color = new Color(249, 115, 22);     // Orange
            } else if (score >= 60) {
                rating = "Needs Improvement";
                color = new Color(239, 68, 68);      // Red
            } else {
                rating = "Unsatisfactory";
                color = new Color(185, 28, 28);      // Dark Red
            }

        // Score
        lblRating.setText(rating);
        lblRating.setForeground(color);
    }
    
    private void addFocusBorder(JComponent component) {

    component.setBorder(defaultBorder);

        component.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                component.setBorder(activeBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                component.setBorder(defaultBorder);
            }
        });
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        employeeJcom = new javax.swing.JComboBox<>();
        sliderEvaluation = new javax.swing.JSlider();
        jLabel6 = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        lblRating = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        remarksTxt = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        saveBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        periodCmb = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(56, 58, 64));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        jLabel37.setBackground(new java.awt.Color(56, 58, 64));
        jLabel37.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(153, 153, 153));
        jLabel37.setText("CREWDECK — Employee Management System  © 2026  —  v1.0");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel37)
                .addContainerGap())
        );

        add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 890, 50));

        jScrollPane4.setBackground(new java.awt.Color(102, 102, 102));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Employee", "Remarks", "Evaluation Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable2);

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("New Evaluation");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setBackground(new java.awt.Color(56, 58, 64));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Search & Select Employee");

        employeeJcom.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        employeeJcom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setBackground(new java.awt.Color(56, 58, 64));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("EVALUAATION SCORE (0 - 100)");

        lblScore.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblScore.setText(".");

        lblRating.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRating.setForeground(new java.awt.Color(255, 255, 255));
        lblRating.setText(".                       ");

        jLabel7.setBackground(new java.awt.Color(56, 58, 64));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("REMARKS");

        remarksTxt.setColumns(20);
        remarksTxt.setRows(5);
        remarksTxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jScrollPane3.setViewportView(remarksTxt);

        jLabel8.setBackground(new java.awt.Color(56, 58, 64));
        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("EVALUATION PERIOD");

        saveBtn.setBackground(new java.awt.Color(37, 99, 235));
        saveBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        saveBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveBtn.setText("Save Evaluation");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        clearBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(107, 114, 128));
        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        periodCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(lblRating, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeJcom, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderEvaluation, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(periodCmb, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saveBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(26, 26, 26)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(employeeJcom, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sliderEvaluation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(lblScore)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRating)
                .addGap(5, 5, 5)
                .addComponent(jLabel7)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(periodCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel4);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 890, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(156, 163, 175));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("CREWDECK");

        jLabel2.setBackground(new java.awt.Color(156, 163, 175));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("/");

        jLabel3.setBackground(new java.awt.Color(17, 24, 39));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Evaluations");

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
                .addContainerGap(690, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        int selectedIndex = employeeJcom.getSelectedIndex();
        if (selectedIndex < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please select an employee first.");
            return;
        }

        Employee targetEmp = employeeDropdownList.get(selectedIndex);
        int score = sliderEvaluation.getValue();
        String remarks = remarksTxt.getText().trim();
        Period selectedPeriod = (Period) periodCmb.getSelectedItem();

        if (remarks.isEmpty() || selectedPeriod==null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill out all evaluation fields.");
            return;
        }

        int currentAdminId = UserSession.getInstance().getLoggedInUser().getId();// Replace with your active session user ID if available

        // Save record using service layer
        boolean isSuccess = evalService.submitEvaluation(targetEmp.getUserId(), currentAdminId, selectedPeriod.toString(), score, remarks);

        if (isSuccess) {
            javax.swing.JOptionPane.showMessageDialog(this, "Evaluation saved successfully!");
            // Clear text controls and refresh history instantly using the new service layout!
            remarksTxt.setText("");
            periodCmb.setSelectedIndex(0);
            sliderEvaluation.setValue(80); 
            loadEvaluationHistoryTable(); 
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Error saving evaluation record.");
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        remarksTxt.setText("");
        periodCmb.setSelectedIndex(0);
        sliderEvaluation.setValue(80);
        lblScore.setText("80");
        updateRating(80);   
    }//GEN-LAST:event_clearBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> employeeJcom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblRating;
    private javax.swing.JLabel lblScore;
    private javax.swing.JComboBox<String> periodCmb;
    private javax.swing.JTextArea remarksTxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JSlider sliderEvaluation;
    // End of variables declaration//GEN-END:variables
}
