/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homologaciones;

import com.sun.awt.AWTUtilities;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.awt.print.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
/**
 *
 * @author JOHN EDWAR
 */
public class Detalle_solicitud extends javax.swing.JFrame implements Printable {

    /**
     * Creates new form Detalle_solicitud
     */
    int x,y;
    String nombreP,estadoS, codigoP,solicitudP, programaDestino;
    conexionBD conexion = new conexionBD();
    
    public Detalle_solicitud() {
        initComponents();
        this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
    }
    public int print(Graphics graf,PageFormat pagfor, int page) throws PrinterException{
        if(page > 0){
            return NO_SUCH_PAGE;
        }
        Graphics2D bot = (Graphics2D) graf;
        bot.translate(pagfor.getImageableX() - 5, pagfor.getImageableY() + 20);
        bot.scale(0.7, 0.7);
        jPanelImprimir.printAll(graf);
        return PAGE_EXISTS;
    
    }
    public void setInformacion(String estado,String codigo, String nombre, String solicitud, String programaD) {
        
       nombreP=nombre;
       estadoS= estado;
       codigoP= codigo;
       solicitudP=solicitud;
       programaDestino= programaD;
       String programaOrigen="";
        System.out.println("Solicitud # "+ solicitudP);
       String sql="select cedula from ficha  where codFicha='"+codigoP+"'";
       conexion.consulta(sql);
               
       conexion.consulta(sql);
       String cedula="";
       ArrayList<String[]> datos = conexion.consulta(sql);
       
        for (int i = 0; i < datos.size(); i++) {
            
            String [] auxiliar =datos.get(i);
            cedula=auxiliar[i];
            
        }
       // para traer el nombre del programa del codigo origen del usuario 
        String sql2="select B.codPrograma,B.nombre from ficha A, programa B where A.codFicha='"+codigoP+"' and A.codPrograma =B.codPrograma";
       
         ArrayList<String[]> datos1= conexion.consulta(sql2);
       
        for (int i = 0; i < datos1.size(); i++) {
            
            String [] auxiliar =datos1.get(i);
            for (int j = 0; j < auxiliar.length; j++) {
                
                if(j!=(auxiliar.length-1)){
                    programaOrigen+=auxiliar[j];
                }
                else{
                    programaOrigen+="-"+auxiliar[j];
                }
                
            }
        }
        ///----------------------------------------------------
        
        // nombre y codigo del programa del  destino del usuario
       String sql3="select nombre from programa where codPrograma='"+programaDestino+"'";
      
       ArrayList<String[]> datos2 = conexion.consulta(sql3);
       
        for (int i = 0; i < datos2.size(); i++) {
            
            String [] auxiliar =datos2.get(i);
               programaDestino +="-"+auxiliar[i];
        }
    ///----------------------------------------------------
     
   // para agregar a la tabla las asignaturas de solicitudDetalle
     String sql4="select codAsignatura, nombre, nota from solicitudDetalle where numSolicitud='"+Integer.parseInt(solicitudP)+"'";
     
       ArrayList<String[]> datos3 = conexion.consulta(sql4);
       
        DefaultTableModel modelo1 = (DefaultTableModel)jTAsignaturasSolicitud.getModel();
     
        while(modelo1.getRowCount() > 0){
            modelo1.removeRow(0);
        }
        for(String[] fila : datos3){           
            modelo1.addRow(fila);
        
       }
   
   //--------------------------------------------------
    // para agregar a la tabla las asignaturas equivalentes
    
          String cadenaS =null;
         String [] separaCadena=null;
         int pDestino=0;
        
            cadenaS= programaDestino;
            
           separaCadena=cadenaS.split("-");
        
        for (int i = 0; i < separaCadena.length; i++) {
            
             pDestino = Integer.parseInt(separaCadena[i]);
             break;
        }
        
     String sql5="select C.codPrograma, A.codAsignatura, B.nombre from asignatura A, solicitudDetalle B, programaAsignatura C where B.numSolicitud ='"+solicitudP+"' and C.codPrograma='"+Integer.parseInt(programaD)+"' and A.codAsignatura=B.codAsignatura  and B.codAsignatura= C.codAsignatura ";
     
       ArrayList<String[]> datos4 = conexion.consulta(sql5);
       
        DefaultTableModel modelo2 = (DefaultTableModel)jTAsignaturasEquivalentes.getModel();
     
        while(modelo2.getRowCount() > 0){
            modelo1.removeRow(0);
        }
        for(String[] fila : datos4){           
            modelo2.addRow(fila);
        
       }
   
   //--------------------------------------------------
       jLCedula.setText(cedula);
       jLNombre.setText(nombreP);
       jLCodigoOrigen.setText(codigoP);
       
       jLEstado.setText(estadoS);
       jLProgramaD.setText(programaDestino);
       jLProgramaO.setText(programaOrigen);
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLCerrar = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanelImprimir = new javax.swing.JPanel();
        jLLogoUnivalle = new javax.swing.JLabel();
        jLTexto_superior = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jLCodigoOrigen = new javax.swing.JLabel();
        jLEstadoSolicitud = new javax.swing.JLabel();
        jLProgramaDestino = new javax.swing.JLabel();
        jLProgramaD = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLEstado = new javax.swing.JLabel();
        jLCC = new javax.swing.JLabel();
        jLCedula = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAsignaturasSolicitud = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAsignaturasEquivalentes = new javax.swing.JTable();
        jLProgramaOrigen = new javax.swing.JLabel();
        jLProgramaO = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cerrar.png"))); // NOI18N
        jLCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLCerrarMouseClicked(evt);
            }
        });
        getContentPane().add(jLCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, -1, -1));

        jButton3.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/impresoraTamaño.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.setFocusPainted(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 100, 50, 40));

        jPanelImprimir.setBackground(new java.awt.Color(255, 255, 255));
        jPanelImprimir.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLLogoUnivalle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logoUnivalle.png"))); // NOI18N
        jPanelImprimir.add(jLLogoUnivalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, -1));

        jLTexto_superior.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jLTexto_superior.setForeground(new java.awt.Color(255, 0, 0));
        jLTexto_superior.setText("UNIVERSIDAD DEL VALLE");
        jLTexto_superior.setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));
        jLTexto_superior.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLTexto_superiorMouseDragged(evt);
            }
        });
        jLTexto_superior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLTexto_superiorMousePressed(evt);
            }
        });
        jPanelImprimir.add(jLTexto_superior, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, -1, -1));

        jLNombre.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jLNombre.setText("nombre");
        jPanelImprimir.add(jLNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 210, 300, 20));

        jLCodigoOrigen.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jLCodigoOrigen.setText("codigo origen");
        jPanelImprimir.add(jLCodigoOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, 270, 20));

        jLEstadoSolicitud.setFont(new java.awt.Font("Open Sans", 1, 16)); // NOI18N
        jLEstadoSolicitud.setText("Estado solicitud -->");
        jPanelImprimir.add(jLEstadoSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, 180, 20));

        jLProgramaDestino.setFont(new java.awt.Font("Open Sans", 1, 16)); // NOI18N
        jLProgramaDestino.setText("Programa destino ");
        jPanelImprimir.add(jLProgramaDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, 160, -1));

        jLProgramaD.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jLProgramaD.setText("programa d");
        jPanelImprimir.add(jLProgramaD, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, 340, -1));

        jLabel3.setFont(new java.awt.Font("Open Sans", 1, 16)); // NOI18N
        jLabel3.setText("Informacion estudiante");
        jPanelImprimir.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        jLEstado.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jLEstado.setText("Estado");
        jPanelImprimir.add(jLEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 130, 130, -1));

        jLCC.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jLCC.setText("C.C ");
        jPanelImprimir.add(jLCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 180, -1, -1));

        jLCedula.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jLCedula.setText("cedula ");
        jPanelImprimir.add(jLCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, -1, -1));

        jLabel1.setFont(new java.awt.Font("Open Sans", 1, 16)); // NOI18N
        jLabel1.setText("Informacion Solicitud");
        jPanelImprimir.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, -1, -1));

        jTAsignaturasSolicitud.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTAsignaturasSolicitud);

        jPanelImprimir.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 490, 290));

        jTAsignaturasEquivalentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Programa", "Codigo", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTAsignaturasEquivalentes);

        jPanelImprimir.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, 480, 290));

        jLProgramaOrigen.setFont(new java.awt.Font("Open Sans", 1, 16)); // NOI18N
        jLProgramaOrigen.setText("Programa origen");
        jPanelImprimir.add(jLProgramaOrigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, -1, -1));

        jLProgramaO.setFont(new java.awt.Font("Open Sans", 0, 16)); // NOI18N
        jLProgramaO.setText("Programa o");
        jPanelImprimir.add(jLProgramaO, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, -1, -1));

        getContentPane().add(jPanelImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1020, 640));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/recuadro_base.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1110, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLTexto_superiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLTexto_superiorMouseDragged
        // TODO add your handling code here:
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_jLTexto_superiorMouseDragged

    private void jLTexto_superiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLTexto_superiorMousePressed
        // TODO add your handling code here:
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_jLTexto_superiorMousePressed

    private void jLCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLCerrarMouseClicked
        // TODO add your handling code here:
        
            this.dispose();
        
    }//GEN-LAST:event_jLCerrarMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
            PrinterJob imprimir = PrinterJob.getPrinterJob();
            imprimir.setPrintable(this);
            boolean impr = imprimir.printDialog();
            
            if(impr){
                imprimir.print();
            }
        }
        catch(PrinterException pex){
            System.out.println("Error\n" + pex);
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Detalle_solicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Detalle_solicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Detalle_solicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Detalle_solicitud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                 try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //hace parte de lo del marco de la interfaz
//                    
//                } catch (Exception ex) {
//                    System.out.println("Error de recuadro interfaz");
//                } 
//                new Detalle_solicitud().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLCC;
    private javax.swing.JLabel jLCedula;
    private javax.swing.JLabel jLCerrar;
    private javax.swing.JLabel jLCodigoOrigen;
    private javax.swing.JLabel jLEstado;
    private javax.swing.JLabel jLEstadoSolicitud;
    private javax.swing.JLabel jLLogoUnivalle;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLProgramaD;
    private javax.swing.JLabel jLProgramaDestino;
    private javax.swing.JLabel jLProgramaO;
    private javax.swing.JLabel jLProgramaOrigen;
    private javax.swing.JLabel jLTexto_superior;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelImprimir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTAsignaturasEquivalentes;
    private javax.swing.JTable jTAsignaturasSolicitud;
    // End of variables declaration//GEN-END:variables
}
