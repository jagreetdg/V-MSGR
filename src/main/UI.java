package main;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import util.OSExecute;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JFormattedTextField;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
//import com.jgoodies.forms.factories.DefaultComponentFactory;

public class UI extends JFrame {

	//private JPanel contentPane;
	private JLabel lblDeveloperJagreet;
	public static JTextArea log = null;
	public Icon ic = new ImageIcon("./viscera.png");
	public Icon ic2 = new ImageIcon("./viscera2.png");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void writeLogReport(Object obj){
		log.append(obj.toString());
	}

	public static void writeLogReportln(Object obj){
		//FOR DEBUG DUDE : log.append("LINE "+counter+++"\n");
		//System.out.println("HERE IS THE SHIT" +obj.toString());
		log.append(obj.toString()+"\n");
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setTitle("V-MSGR : UI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//contentPane = new JPanel();
		///*contentPane.*/setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		///*contentPane.*/setLayout(null);

		lblDeveloperJagreet = new JLabel("Developer : Jagreet Das Gupta");
		lblDeveloperJagreet.setBounds(210, 239, 224, 24);
		/*contentPane.*/getContentPane().add(lblDeveloperJagreet);

		JButton btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Server_Main.run();
				btnStartServer.setEnabled(false);
			}
		});
		btnStartServer.setBounds(22, 52, 147, 34);
		/*contentPane.*/getContentPane().add(btnStartServer);

		JButton btnRegisterClient = new JButton("Register Client");
		btnRegisterClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client_Main.run();
			}
		});
		btnRegisterClient.setBounds(22, 94, 147, 34);
		/*contentPane.*/getContentPane().add(btnRegisterClient);
		JButton Icon = new JButton("");
		Icon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				    Process clientProcess = Runtime.getRuntime().exec(new String[] {"firefox", "www.viscerali.wordpress.com"});
				    clientProcess.waitFor();
				    } catch (Exception exep){
				        exep.printStackTrace();
				    }
			}
		});
		/*Icon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map.Entry<String, String> streams = null;
				try{
					streams = OSExecute.getStreams("ls");
				}catch(IOException exep){
					//THIS FUCKING EXCEPTION IS NEVER GONNA BE THROWN
					//IF IT IS THEN GET THE SHIT OUTTA MY CODE
					writeLogReportln("ERROR : \n"+exep);
				}
				if(streams.getKey()!=null)
					writeLogReportln(streams.getKey());
				if(streams.getValue()!=null)
					writeLogReportln(streams.getValue());
			}
		});*/
		Icon.setIcon(ic);
		Icon.setRolloverIcon(ic2);
		Icon.setRolloverEnabled(true);
		Icon.setBounds(22, 140, 147, 123);
		/*contentPane.*/getContentPane().add(Icon);
		
		JPanel panel = new JPanel();
		panel.setBounds(97, 12, 244, 28);
		/*contentPane.*/getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(105, 12, 233, 22);
		panel.add(panel_2);
		panel_2.setLayout(null);
		//TODO : /*contentPane.*/add(txtrLogMessages);

		JLabel lblVmsgr = new JLabel("V-MSGR : Minimialistic IM Client");
		lblVmsgr.setBounds(0, 0, 233, 22);
		panel_2.add(lblVmsgr);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Output Log", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(204, 37, 223, 205);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 17, 213, 182);
		panel_1.add(scrollPane);
		
		JTextArea textArea = log = new JTextArea();
		scrollPane.setViewportView(textArea);
		
	}
	public Border getLblDeveloperJagreetBorder() {
		return lblDeveloperJagreet.getBorder();
	}
	public void setLblDeveloperJagreetBorder(Border border) {
		lblDeveloperJagreet.setBorder(border);
	}
}
