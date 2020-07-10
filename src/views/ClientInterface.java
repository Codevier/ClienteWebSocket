/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author gugle
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.*;

public class ClientInterface extends JFrame {
    private final JLabel lblTitle, lblClient, lblIp, lblMessage, lblMessages;
    private final JTextField txtMessage, txtClient, txtIp;
    private final JTextArea txtMessages;
    private final JButton btnSubmit, btnLimpiar;
    private final JPanel panel;
    
    private Socket socketClient;
    
    public ClientInterface(String title){
        lblTitle    = new JLabel("Chat (Cliente)");
        lblClient   = new JLabel("Nombrte: ");
        lblIp       = new JLabel("Ip: ");
        lblMessage  = new JLabel ("Mensaje: ");
        lblMessages = new JLabel ("Chat: ");
        
        txtMessage  = new JTextField (25);
        txtMessages = new JTextArea (10, 25);
        txtClient   = new JTextField (25);
        txtIp       = new JTextField (25);
        
        btnSubmit   = new JButton("Enviar");
        btnLimpiar  = new JButton("Limpiar");
        
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //System.out.println("Hola funciona");
                    socketClient = new Socket("192.168.1.15", 5555);
                    try (DataOutputStream outputStream = new DataOutputStream(socketClient.getOutputStream())) {
                        outputStream.writeUTF(txtMessage.getText());
                        txtMessages.setText(txtMessages.getText() + "\n" + txtMessage.getText());
                        outputStream.close();
                    }
                    socketClient.close();
                } catch (IOException ex) {
                    txtMessages.setText(txtMessages.getText() + "\n" + txtMessage.getText());
                    System.out.println(ex.getMessage());
                }
                
            }
        });
        
        panel = new JPanel();
        
        panel.add(lblTitle);
        panel.add(lblIp);        panel.add(txtIp);
        panel.add(lblClient);    panel.add(txtClient);
        panel.add(lblMessages);  panel.add(txtMessages);
        panel.add(lblMessage);   panel.add(txtMessage);
        panel.add(btnSubmit);    panel.add(btnLimpiar);
        
        this.setBounds(800, 350, 320, 410);
        this.add(panel);
        this.setVisible(true);
        this.setTitle(title);
    }
    
}
