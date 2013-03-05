package IUT2;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CompteurVueControleur extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	CompteurModele m;
	JLabel label = new JLabel("0");
	JButton inc = new JButton("+");
	JButton dec = new JButton("-");
	
	public CompteurVueControleur(final CompteurModele m){
		super();
		this.m = m;
		m.addObserver(this);
		this.setLayout(new FlowLayout());
		add(label);add(inc);add(dec);
		
		// Ajout d'un controleur (type ActionListener) sur le bouton inc
		inc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				m.inc();				
			}
		});

		// Ajout d'un controleur (type ActionListener) sur le bouton dec
		dec.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				m.dec();
			}
			
		});
		
		// Ajout d'un controleur (type MouseWheelListener) sur le label, juste pour rire
		label.addMouseWheelListener(new MouseWheelListener(){
			public void mouseWheelMoved(MouseWheelEvent e) {
				int k = m.get()+ e.getUnitsToScroll();
				m.set(k);
			}
			
		});
	}
	
	public void update(Observable arg0, Object arg1) {
		label.setText(""+((CompteurModele)arg0).get());
	}

}
