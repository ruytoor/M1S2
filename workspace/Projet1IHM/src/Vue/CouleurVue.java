package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import Controleur.ColorControleur;

public class CouleurVue extends JPanel implements Observer{

	private JPanel chooser;
	private JPanel couleur;
	private int nivGris;
	private JLabel label;
	
	public CouleurVue(int i,int nivGris,JPanel chooser){
	//	System.out.println(nivGris);
		this.setPreferredSize(new Dimension(600, 100));
		this.setLayout(new GridLayout(1, 4, 2, 2));
		JPanel jp=new JPanel();
		jp.setLayout(new GridLayout(3, 1));
		JLabel label2=new JLabel("choix n°"+(i-1));
		label2.setPreferredSize(new Dimension(100, 100));
		label=new JLabel();
		jp.add(label2);
		jp.add(label);
		JButton b=new JButton("copier hexa");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

		        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		        Color c=couleur.getBackground();
				String tmp=Integer.toHexString(c.getRGB()).toString();
				tmp=tmp.substring(2);
		        clipboard.setContents(new StringSelection(tmp), null);	
			}
		});
		jp.add(b);
		add(jp);
		this.chooser=chooser;
		this.chooser.setPreferredSize(new Dimension(300, 100));
		add(this.chooser);
		this.nivGris=nivGris;// faire du random couleur
		couleur=new JPanel();
		couleur.setPreferredSize(new Dimension(100, 100));
		JPanel vueGris=new JPanel();
		vueGris.setBackground(new Color(nivGris, nivGris, nivGris));
		vueGris.setPreferredSize(new Dimension(100, 100));
		add(couleur);
		add(vueGris);
		setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));

		this.setVisible(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		couleur.setBackground((Color)(arg));
		String tmp=Integer.toHexString(((Color)(arg)).getRGB()).toString();
		label.setText("Hexa :"+tmp.substring(2));
	}
}
