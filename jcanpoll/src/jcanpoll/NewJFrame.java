/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcanpoll;

import java.util.ArrayList;

/**
 *
 * @author deh
 */
public class NewJFrame extends javax.swing.JFrame {
    public Canmsginfo cmi;
    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();
        Canmsginfo c = new Canmsginfo(); 
        cmi = c;
    }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        pccPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("pccPU").createEntityManager();
        canidQuery = java.beans.Beans.isDesignTime() ? null : pccPUEntityManager.createQuery("SELECT c FROM Canid c");
        canidList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : canidQuery.getResultList();
        canidQuery3 = java.beans.Beans.isDesignTime() ? null : pccPUEntityManager.createQuery("SELECT c FROM Canid c");
        canidList3 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : canidQuery3.getResultList();
        canidQuery1 = java.beans.Beans.isDesignTime() ? null : pccPUEntityManager.createQuery("SELECT c FROM Canid c");
        canidList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : canidQuery1.getResultList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Canid Name", "Canid Hex", "Description", "Can Msg Fmt"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, canidList1, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${canidName}"));
        columnBinding.setColumnName("Canid Name");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${canidHex}"));
        columnBinding.setColumnName("Canid Hex");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${description}"));
        columnBinding.setColumnName("Description");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${canMsgFmt}"));
        columnBinding.setColumnName("Can Msg Fmt");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable1FocusLost(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.setAutoCreateRowSorter(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 357, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1FocusLost

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int Col = jTable1.getSelectedColumn();  // Selected Col (0 - n)
        int Row = jTable1.getSelectedRow();     // Selected Row (0 - n)
        
        // Testing
        System.out.format("CLIC Col ct: %d Col: %d Row: %d\n",jTable1.getColumnCount(),jTable1.getSelectedColumn(),jTable1.getSelectedRow());
        String s = jTable1.getValueAt(Row, Col).toString();
        System.out.format("Selected: %s\n",s);
        
        /* Extract and Copy row-col selection */
        cmi.can_name = jTable1.getValueAt(Row, 0).toString();
        cmi.can_hex = Long.parseLong(jTable1.getValueAt(Row, 1).toString(), 16);
        cmi.descript_canid = jTable1.getValueAt(Row, 2).toString();
        cmi.can_msg_fmt = jTable1.getValueAt(Row, 3).toString();
        System.out.format("cmi: %s\t0x%08X %s\t%s\n",cmi.can_name,cmi.can_hex,cmi.descript_canid,cmi.can_msg_fmt);
        
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.util.List<jcanpoll.Canid> canidList;
    private java.util.List<jcanpoll.Canid> canidList1;
    private java.util.List<jcanpoll.Canid> canidList3;
    private javax.persistence.Query canidQuery;
    private javax.persistence.Query canidQuery1;
    private javax.persistence.Query canidQuery3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.persistence.EntityManager pccPUEntityManager;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
