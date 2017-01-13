package main; 
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
public class SwingConsole {
	public static void run(final JFrame f, final int width, final int height){
		run(f,width,height,f.getClass().getSimpleName());
	}
	public static void run(final JFrame f, final int width, final int height,String name){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				f.setTitle(name);
				f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				f.setSize(width, height);
				f.setVisible(true);
			}
		});
	}
}
