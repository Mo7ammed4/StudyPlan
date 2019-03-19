/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectalgorithm;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Abdalelah
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        
    }


    ArrayList<Course> ar=new ArrayList<>();
    public void createStudyPlan(ArrayList<Course> al) {
        ar=al;
        DefaultTableModel model = (DefaultTableModel) CourseTable.getModel();
        TableRowSorter sort=new TableRowSorter(model);
        CourseTable.setRowSorter(sort);
        Object rowData[] = new Object[4];
        int csms = 1;
        int i = 0;
        int n = 0;
        int smsar[] = new int[8];
        for (Course c : al) {
            System.out.println("current semester value = " + csms);
            String crs = c.getName();
            String[] pre = c.getPrerequisite();
            int cr = c.getCreditHours();

            rowData[0] = crs;
            String str = "";
            int size = pre.length;
            int x = 1;
            for (String s : pre) {
                str += s;

                if (x < size) {
                    str += " // ";
                }
                x++;

            }
            rowData[1] = str;
            rowData[2] = c.getCreditHours();
            if (!pre[0].equals(" ")) {
                for (Course cu : al) {
                    if (pre[0].equals(cu.getName())) {
                        int preSem = cu.getSemester();
                        System.out.println("compare pre: " + pre[0] + " with course: " + cu.getName());
                        if (pre.length > 1) {
                            int temp = 0;
                            System.out.println("2 pre");
                            for (int y = 0; y < pre.length; y++) {
                                for (Course cus : al) {
                                    if (pre[y].equals(cus.getName())) {
                                        System.out.println("compare pre: " + pre[y] + " with course: " + cus.getName());
                                        System.out.println("for " + crs + " check semester valu pre= " + cus.getSemester() + " course= " + csms);
                                        if (cus.getSemester() == csms || cus.getSemester() > csms) {
                                            System.out.println("couse " + crs + " is in semester " + (cus.getSemester() + 1));
                                            if (preSem > temp) {
                                                temp = cus.getSemester() + 1;
                                            }
                                        } else {
                                            System.out.println("couse " + crs + " is in semester " + csms);
                                            if (csms > temp) {
                                                temp = csms;
                                            }
                                        }
                                        continue;
                                    }
                                }
                            }
                            for (int j = temp; j < 8; j++) {
                                if (smsar[temp - 1] < 18) {
                                    smsar[temp - 1] += c.getCreditHours();
                                    System.out.println("added to " + (temp - 1) + " new value=" + smsar[temp - 1]);
                                    break;
                                } else {
                                    temp++;
                                }
                            }
                            c.setSemester(temp);
                            rowData[3] = c.getSemester();

                        } else {
                            System.out.println("1 pre");
                            System.out.println("for " + crs + " check semester valu pre= " + preSem + " course= " + csms);
                            if (preSem == csms || preSem > csms) {
                                System.out.println("couse " + crs + " is in semester " + (preSem + 1));
                                int temp1 = preSem;
                                for (int j = temp1; j < 8; j++) {
                                    System.out.println("temp1= " + temp1);
                                    if (smsar[temp1] < 18) {
                                        smsar[temp1] += c.getCreditHours();
                                        System.out.println("added to " + (temp1) + " new value=" + smsar[temp1]);
                                        break;
                                    } else {
                                        temp1++;
                                    }
                                }
                                c.setSemester(temp1 + 1);
                            } else {
                                System.out.println("couse " + crs + " is in semester " + csms);
                                for (int j = 0; j < 8; j++) {
                                    if (smsar[csms - 1] < 18) {
                                        smsar[csms - 1] += c.getCreditHours();
                                        System.out.println("added to " + (csms - 1) + " new value=" + smsar[csms - 1]);
                                        break;
                                    } else {
                                        csms++;
                                    }
                                }
                                c.setSemester(csms);
                            }
                        }
                        rowData[3] = c.getSemester();
                    }
                }
            } else {
                System.out.println("couse " + crs + " is in semester " + csms);

                for (int j = 0; j < 8; j++) {
                    if (smsar[csms - 1] < 18) {
                        smsar[csms - 1] += c.getCreditHours();
                        System.out.println("added to " + (csms - 1) + " new value=" + smsar[csms - 1]);
                        break;
                    } else {
                        csms++;
                    }
                }
                c.setSemester(csms);
                rowData[3] = c.getSemester();

            }
            model.addRow(rowData);
            for (int j = 0; j < 8; j++) {
                if (smsar[csms - 1] < 18) {
                    break;
                } else {
                    csms++;
                }
            }

        }
        for (int g : smsar) {
            System.out.print(g + " ");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        CourseTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CourseTable.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CourseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Course Name", "Prerequisite", "Credit Hours", "semester"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
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
        CourseTable.setRowHeight(20);
        CourseTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(CourseTable);

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        MainMenu m=new MainMenu(ar);
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CourseTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
