package TP2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class monMain {

	public static int cmp;
	public static JLabel jb;
	static int r,v,b;
	/**
	 * @param args
	 */
	public static void main(String[] args){
		r=v=b=0;
		JFrame f =new JFrame();
		Container c = f.getContentPane();
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JPanel p3=new JPanel();
		JSlider js1=new JSlider(JSlider.HORIZONTAL);
		js1.setMajorTickSpacing(255);
		js1.setMinorTickSpacing(0);
		js1.setPaintTicks(true);
		JSlider js2=new JSlider(JSlider.HORIZONTAL);	
		js2.setMajorTickSpacing(255);
		js2.setMinorTickSpacing(0);
		js2.setPaintTicks(true);
		JSlider js3=new JSlider(JSlider.HORIZONTAL);
		js3.setMajorTickSpacing(255);
		js3.setMinorTickSpacing(0);
		js3.setPaintTicks(true);
		p1.setLayout(new GridLayout(3, 1));
		p1.add(js1);
		p1.add(js2);
		p1.add(js3);
		p2.setLayout(new GridLayout(3, 1));
		JTextField t1=new JTextField("0", 3);
		JTextField t2=new JTextField("0", 3);
		JTextField t3=new JTextField("0", 3);
		p2.add(t1);
		p2.add(t2);
		p2.add(t3);
		p3.setLayout(new BoxLayout(p3, BoxLayout.PAGE_AXIS));
		JTextField t4=new JTextField("000000", 6);
		p3.add(t4);
		JPanel pcolor=new JPanel();
		pcolor.setPreferredSize(new Dimension(50,50));
		pcolor.setBackground(new Color(0, 0, 0));
		p3.add(pcolor);
		f.setLayout(new FlowLayout());
		c.add(p1);
		c.add(p2);
		c.add(p3);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main4(String[] args){
		final ArdoiseMagique am=new ArdoiseMagique();
		JFrame f= new JFrame();
		f.getContentPane().add(am);
		am.setPreferredSize(new Dimension(400,400));
		am.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent arg0) {
				// TODO Stub de la méthode généré automatiquement
			}

			@Override
			public void mouseDragged(MouseEvent arg0) {
				// TODO Stub de la méthode généré automatiquement
				//if press boutton 1
					am.addPoint(arg0.getX(), arg0.getY());
			}
		});
		am.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Stub de la méthode généré automatiquement

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Stub de la méthode généré automatiquement
				if(arg0.getButton()==1)
					am.newCurve();
				else
					am.clear();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Stub de la méthode généré automatiquement

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Stub de la méthode généré automatiquement

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Stub de la méthode généré automatiquement
				System.out.println(arg0.getButton());
				if(arg0.getButton()==1)
					am.newCurve();
				else
					am.clear();
			}
		});

		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	public static void main3(String[] args){
		JFrame jf=new JFrame();
		Container c = jf.getContentPane();
		jb=new JLabel("");
		c.add(jb);
		ActionListener al=new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Stub de la méthode généré automatiquement
				jb.setText(((JButton)(arg0.getSource())).getText());
			}
		};
		JButton jb;
		c.add(jb=new JButton("B 1"));
		jb.addActionListener(al);
		c.add(jb=new JButton("B 2"));
		jb.addActionListener(al);
		c.add(jb=new JButton("B 3"));
		jb.addActionListener(al);
		jf.setLayout(new FlowLayout());
		jf.pack();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}



	public static void main2(String[] args) {
		// TODO Stub de la méthode généré automatiquement
		JFrame frame =new JFrame("Frame 1");
		JButton button =new JButton("Clic me  :-)");
		cmp=0;
		jb=new JLabel("0");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Stub de la méthode généré automatiquement
				cmp++;
				jb.setText(""+cmp);
			}
		});
		Container c=frame.getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.PAGE_AXIS));
		c.add(jb);
		c.add(button);
		button.setPreferredSize(new Dimension(50, 150));
		frame.setPreferredSize(new Dimension(200, 200));
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new FermetureFenetre());
	}

}
