package TP1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;


public class Main {

	/**
	 *
	 	public static void main(String[] args) {
		JFrame f=new JFrame("Jfleme");
		Container c = f.getContentPane();
		f.setPreferredSize(new Dimension(500, 400));

		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
}

	 */

	public static void main(String[] args) {
		JFrame f=new JFrame("Jfleme");
		Container c = f.getContentPane();
		f.setPreferredSize(new Dimension(500, 400));
		BorderLayout bLayout= new BorderLayout();
		f.setLayout(bLayout);
		JMenuBar jmb =new JMenuBar();
		Random r=new Random();
		for(int y=0;y<5;++y){
			JMenu jm1=new JMenu("Menu "+y);
			for(int i=0;i<5;++i){
				JMenuItem jmi1=new JMenuItem("menu "+y+i);
				jmi1.setForeground(new Color(r.nextInt()));
				jm1.add(jmi1);
			}
			jm1.setForeground(new Color(r.nextInt()));
			jmb.add(jm1);
		}
		JTextArea jta=new JTextArea();
		JPanel jp=new JPanel();
		FlowLayout fl=new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		jp.setLayout(fl);
		jp.add(new Label("Recherche :"));
		jp.add(new JTextField(10));
		ImageIcon icon2 = new ImageIcon("previous_motif.gif");
		ImageIcon icon = new ImageIcon("next_motif.gif");
		jp.add(new JButton(icon2));
		jp.add(new JButton(icon));
		jp.add(new JButton("tout surligner"));
		c.add(jta,BorderLayout.CENTER);
		c.add(jp,BorderLayout.SOUTH);
		f.setJMenuBar(jmb);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void main42(String[] args) {
		JFrame f=new JFrame("Jfleme");
		Container c = f.getContentPane();
		f.setPreferredSize(new Dimension(500, 400));
		BorderLayout bLayout= new BorderLayout();
		f.setLayout(bLayout);
		JPanel jp=new JPanel();
		jp.add(new JButton("toto"));
		jp.add(new JButton("toto"));
		jp.add(new JButton("toto"));
		c.add(jp,bLayout.NORTH);
		c.add(new JButton("CENTER"),bLayout.CENTER);
		c.add(new JButton("EAST"),bLayout.EAST);
		c.add(new JButton("WEST"),bLayout.WEST);
		c.add(new JButton("SOUTH"),bLayout.SOUTH);

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				System.out.println(info.getClassName());
			}
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}


	public static void main2(String[] args) {
		JFrame f=new JFrame("Jfleme");
		Container c = f.getContentPane();
		f.setPreferredSize(new Dimension(800, 400));
		f.setLayout(null);
		JButton jb = new JButton("toto");
		jb.setBounds(5,5,80,80);
		jb.repaint();
		c.add(jb);
		jb = new JButton("toto");
		jb.setBounds(85,85,80,80);
		jb.repaint();
		c.add(jb);
		jb = new JButton("toto");
		jb.setBounds(165,165,80,80);
		jb.repaint();
		c.add(jb);

		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void main6(String[] args) {
		JFrame f=new JFrame("Jfleme");
		Container c = f.getContentPane();
		f.setPreferredSize(new Dimension(200, 200));
		BoxLayout bLayout = new BoxLayout(c, BoxLayout.PAGE_AXIS);
		f.setLayout(bLayout);
		c.add(new JButton("Button 1"));
		c.add(new JButton("Button 2"));
		c.add(new Box.Filler(new Dimension(10, 10),new Dimension(50, 50),new Dimension(100, 100)));
		c.add(new JButton("Button 4"));		

		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}






	public static void main5(String[] args) {
		JFrame f=new JFrame("Jfleme");
		Container c = f.getContentPane();
		f.setPreferredSize(new Dimension(800, 400));
		GridLayout gLayout = new GridLayout(4, 4);
		f.setLayout(gLayout);
		c.add(new JButton("Button 1"));
		c.add(new JButton("Button 2"));
		c.add(new JButton("Button 3"));
		c.add(new JButton("Button 4"));
		c.add(new JButton("Button 5"));
		c.add(new JButton("Button 6"));
		c.add(new JButton("Button 7"));
		c.add(new JButton("Button 8"));
		c.add(new JButton("Button 9"));
		c.add(new JButton("Button 1"));
		c.add(new JButton("Button 2"));
		c.add(new JButton("Button 3"));
		c.add(new JButton("Button 4"));
		c.add(new JButton("Button 5"));
		c.add(new JButton("Button 6"));


		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}




	public static void main3(String[] args) {
		JFrame f=new JFrame("Jfleme");
		Container c = f.getContentPane();
		f.setPreferredSize(new Dimension(200, 200));
		FlowLayout fLayout = new FlowLayout();
		f.setLayout(fLayout);
		JButton jb = new JButton("Button 1");
		c.add(jb);

		jb = new JButton("Button 2");
		c.add(jb);
		jb = new JButton("Button 3");
		c.add(jb);
		jb = new JButton("Button 4");
		c.add(jb);
		jb = new JButton("Button 5");
		c.add(jb);
		jb = new JButton("Button 2");
		c.add(jb);
		jb = new JButton("Button 3");
		c.add(jb);
		jb = new JButton("Button 4");
		c.add(jb);
		jb = new JButton("Button 5");
		c.add(jb);
		jb = new JButton("Button 2");
		c.add(jb);
		jb = new JButton("Button 3");
		c.add(jb);
		jb = new JButton("Button 4");
		c.add(jb);
		jb = new JButton("Button 5");
		c.add(jb);
		jb = new JButton("Button 2");
		c.add(jb);
		jb = new JButton("Button 3");
		c.add(jb);
		jb = new JButton("Button 4");
		c.add(jb);
		jb = new JButton("Button 5");
		c.add(jb);
		jb = new JButton("Button 2");
		c.add(jb);
		jb = new JButton("Button 3");
		c.add(jb);
		jb = new JButton("Button 4");
		c.add(jb);


		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

	public static void main1(String[] args) {
		JFrame f=new JFrame("Jfleme");
		Container c = f.getContentPane();
		BorderLayout bLayout = new BorderLayout();
		c.setLayout(bLayout);
		JButton jb= new JButton("NORTH");
		c.add(jb,bLayout.NORTH);
		jb= new JButton("SOUTH");
		c.add(jb,bLayout.SOUTH);
		jb= new JButton("EAST");
		c.add(jb,bLayout.EAST);
		jb= new JButton("WEST");
		c.add(jb,bLayout.WEST);
		jb= new JButton("CENTER");
		c.add(jb,bLayout.CENTER);

		f.pack();
		f.setVisible(true);

	}


	public static void main2() {
		// TODO Stub de la méthode généré automatiquement

		//JWindow jw=new JWindow();
		JFrame jw = new JFrame("JFrame");
		Container c =jw.getContentPane();
		c.add(new JLabel("Hello World !!!"));
		jw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jw.setPreferredSize(new Dimension(400,400));
		jw.setResizable(false);
		jw.setLocation(200, 200);
		jw.pack();
		//jw.setLocationRelativeTo(null);
		jw.setVisible(true);
		JDialog jd = new JDialog(jw,"JDialog",true);
		jd.setLocationRelativeTo(jw);
		jd.setPreferredSize(new Dimension(200, 100));
		JDialog jd2 = new JDialog(jw,"JDialog 2",false);

		jd2.setLocationRelativeTo(jd);
		jd2.setPreferredSize(new Dimension(200, 100));
		jd2.pack();
		jd2.setVisible(true);

		jd.pack();
		jd.setVisible(true);

	}

}
