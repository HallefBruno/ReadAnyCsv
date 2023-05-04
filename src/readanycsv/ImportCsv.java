package readanycsv;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ImportCsv extends javax.swing.JFrame {

  public ImportCsv() {
    initComponents();
    setLocationRelativeTo(null);
    getContentPane().setBackground(Color.BLACK);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    tbCsv = new javax.swing.JTable();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    tfPath = new javax.swing.JTextField();
    btnImport = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Import Any Csv");
    setResizable(false);

    tbCsv.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {

      }
    ));
    jScrollPane1.setViewportView(tbCsv);

    jLabel1.setBackground(new java.awt.Color(0, 0, 0));
    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 0));
    jLabel1.setText("Import Any Csv");
    jLabel1.setOpaque(true);

    jLabel2.setBackground(new java.awt.Color(0, 0, 0));
    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setText("Path:");
    jLabel2.setOpaque(true);

    tfPath.setBackground(new java.awt.Color(255, 255, 204));
    tfPath.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
    tfPath.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

    btnImport.setBackground(new java.awt.Color(204, 204, 204));
    btnImport.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    btnImport.setForeground(new java.awt.Color(0, 0, 0));
    btnImport.setText("Import");
    btnImport.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
    btnImport.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnImportActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel2)
            .addGap(18, 18, 18)
            .addComponent(tfPath, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 11, Short.MAX_VALUE)))
        .addContainerGap())
      .addGroup(layout.createSequentialGroup()
        .addGap(226, 226, 226)
        .addComponent(jLabel1)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGap(16, 16, 16)
        .addComponent(jLabel1)
        .addGap(41, 41, 41)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel2)
          .addComponent(tfPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap())
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
    JFileChooser filechooser = new JFileChooser();
    filechooser.setAcceptAllFileFilterUsed(false);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "CSV");
    filechooser.addChoosableFileFilter(filter);
    int op = filechooser.showOpenDialog(null);
    if (op == JFileChooser.APPROVE_OPTION) {
      File f = filechooser.getSelectedFile();
      String filepath = f.getPath();
      String fi = f.getName();
      tfPath.setText(fi);
      
      DefaultTableModel model = new DefaultTableModel();
      tbCsv.setModel(model);
      
      try (BufferedReader br = new BufferedReader(new FileReader(filepath, Charset.forName("UTF-8")))) {
        String line;
        int row = 0;
        while ((line = br.readLine()) != null) {
          String[] columns = line.split(",");
          if(row == 0) {
            for(String colum : columns) {
              model.addColumn(colum);
            }
          }
          if(row > 0) {
            model.addRow(Arrays.asList(columns).stream().map(col -> col.trim()).collect(Collectors.toList()).toArray());
          }
          row++;
        }
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
  }//GEN-LAST:event_btnImportActionPerformed


  public static void main(String args[]) {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(ImportCsv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    java.awt.EventQueue.invokeLater(() -> {
      new ImportCsv().setVisible(true);
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnImport;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable tbCsv;
  private javax.swing.JTextField tfPath;
  // End of variables declaration//GEN-END:variables
}
