
import javax.swing.table.DefaultTableModel;


public class EmployeePanel extends javax.swing.JPanel {

    /**
     * Creates new form EmployeePanel
     */
    DefaultTableModel model;
    
    public EmployeePanel() {
        initComponents();
        
        String[] columns = {"Employee ID", "Full Name", "Position", "Department"};
        model = new DefaultTableModel(columns, 0);
        jTable2.setModel(model);
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
        PositiontTxt = new javax.swing.JTextField();
        AddBtn = new javax.swing.JButton();
        DelBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        DepartmentTxt = new javax.swing.JTextField();
        EmployeeBodyPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        EmployeeTopPanel.setBackground(new java.awt.Color(255, 255, 204));
        EmployeeTopPanel.setPreferredSize(new java.awt.Dimension(636, 92));

        idTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxtActionPerformed(evt);
            }
        });

        jLabel1.setText("[ ID: ]");

        jLabel3.setText("[ Name: ]");

        jLabel4.setText("[ Position: ]");

        AddBtn.setText("Add");
        AddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtnActionPerformed(evt);
            }
        });

        DelBtn.setText("Delete");
        DelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DelBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("[ Department: ]");

        javax.swing.GroupLayout EmployeeTopPanelLayout = new javax.swing.GroupLayout(EmployeeTopPanel);
        EmployeeTopPanel.setLayout(EmployeeTopPanelLayout);
        EmployeeTopPanelLayout.setHorizontalGroup(
            EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeTopPanelLayout.createSequentialGroup()
                .addGroup(EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EmployeeTopPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PositiontTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DepartmentTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EmployeeTopPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(AddBtn)
                        .addGap(18, 18, 18)
                        .addComponent(DelBtn)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        EmployeeTopPanelLayout.setVerticalGroup(
            EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeeTopPanelLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddBtn)
                    .addComponent(DelBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EmployeeTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(nameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PositiontTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(DepartmentTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        add(EmployeeTopPanel, java.awt.BorderLayout.PAGE_START);

        EmployeeBodyPanel.setBackground(new java.awt.Color(255, 255, 255));
        EmployeeBodyPanel.setLayout(new java.awt.BorderLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        EmployeeBodyPanel.add(jScrollPane2, java.awt.BorderLayout.PAGE_START);

        add(EmployeeBodyPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
    
    }//GEN-LAST:event_idTxtActionPerformed

    private void DelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DelBtnActionPerformed
        int selectedRow = jTable2.getSelectedRow();
        
        if (selectedRow >= 0) {
            int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this employee?", 
            "Confirm Deletion", 
            javax.swing.JOptionPane.YES_NO_OPTION);
            
            if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            model.removeRow(selectedRow);
            }
            
        }
        else {
            javax.swing.JOptionPane.showMessageDialog(this, 
            "Please click on an employee row in the table first to select them.", 
            "No Row Selected", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
    }
        
        
        
    }//GEN-LAST:event_DelBtnActionPerformed

    private void AddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtnActionPerformed
        String id = idTxt.getText().trim();
        String name = nameTxt.getText().trim();
        String position = PositiontTxt.getText().trim();
        String department = DepartmentTxt.getText().trim();
        
        if (id.isEmpty() || name.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Please fill in the ID and Name fields.");
            return;
        }
        
        Object[] rowData = {id, name, position, department};
        
        model.addRow(rowData);
        
        idTxt.setText("");
        nameTxt.setText("");
        PositiontTxt.setText("");
        DepartmentTxt.setText("");
    }//GEN-LAST:event_AddBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBtn;
    private javax.swing.JButton DelBtn;
    private javax.swing.JTextField DepartmentTxt;
    private javax.swing.JPanel EmployeeBodyPanel;
    private javax.swing.JPanel EmployeeTopPanel;
    private javax.swing.JTextField PositiontTxt;
    private javax.swing.JTextField idTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField nameTxt;
    // End of variables declaration//GEN-END:variables
}
