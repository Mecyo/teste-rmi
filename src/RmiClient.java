import java.awt.Rectangle;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


  
  
public class RmiClient {  
  
    private JFrame jFrame = null;  
    private JPanel jContentPane = null;  
    private JButton butButton = null;  
    private JLabel labLabel = null; 
    private JTextField tfTxtMsg = null; 
    private JTextField tfTxtIpServer = null; 
    private ReceiveMessageInterface rmiServer;
    private Registry registry;
    private String serverAddress;
    private String serverPort;
    private String text;
      
  
      
    private JFrame getJFrame() {  
        if (jFrame == null) {  
            jFrame = new JFrame();  
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
              
            jFrame.setSize(300, 200);  
            jFrame.setContentPane(getJContentPane());  
            jFrame.setTitle("Application");  
        }  
        return jFrame;  
    }  
  
      
    private JPanel getJContentPane() {  
        if (jContentPane == null) {  
            labLabel = new JLabel();  
            labLabel.setBounds(new Rectangle(50, 35, 194, 58));  
            labLabel.setText("");  
            jContentPane = new JPanel();  
            jContentPane.setLayout(null);  
            jContentPane.add(getButButton(), null);
			jContentPane.add(getTxtIp(), null);  
			jContentPane.add(getTxtMsg(), null);  			
            jContentPane.add(labLabel, null);  
        }  
        return jContentPane;  
    }  
	
	private JTextField getTxtIp() {
		tfTxtIpServer = new JTextField( );
		tfTxtIpServer.setBounds(new Rectangle(58, 30, 150, 30));  
		return tfTxtIpServer;
	}
	private JTextField getTxtMsg() {
		tfTxtMsg = new JTextField( );
		tfTxtMsg.setBounds(new Rectangle(58, 60, 177, 30));  
		return tfTxtMsg;
	}
  
      
      
//  here i start my metod  
  
    private JButton getButButton() {  
        if (butButton == null) {  
            butButton = new JButton();  
            butButton.setBounds(new Rectangle(58, 110, 177, 50));  
            butButton.setText("Enviar");  
            butButton.addActionListener(new java.awt.event.ActionListener() {  
                public void actionPerformed(java.awt.event.ActionEvent e) {  
                    send();  
                }  
            });  
        }  
        return butButton;  
    }  
  
//  here is the metod that i cant solve to work!  
  
    public void send(){  
		serverAddress=tfTxtIpServer.getText();
		serverPort="3232";
		text=tfTxtMsg.getText();
        try{
			// get the "registry"
           registry=LocateRegistry.getRegistry(
               serverAddress,
               (new Integer(serverPort)).intValue()
           );
		   // look up the remote object
           rmiServer=(ReceiveMessageInterface)(registry.lookup("rmiServer"));
			// call the remote method
           rmiServer.receiveMessage(text);
       }
       catch(RemoteException e){
           e.printStackTrace();
       }
       catch(NotBoundException e){
           e.printStackTrace();
       }  
    }  
      
      
    public static void main (String[] args) throws java.lang.Exception {  
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
            	RmiClient application = new RmiClient();  
                application.getJFrame().setVisible(true);  
            }  
        });  
    }  
  
}  