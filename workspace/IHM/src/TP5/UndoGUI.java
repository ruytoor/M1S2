package TP5;
/**
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 */

import java.awt.Event;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

public class UndoGUI {
	private DefaultListModel listModel;
	private JTextField text;
	private JList list;
	
	UndoGUI() {
		//action 
		MyActionButton refaire = new MyActionButton("Refaire");
		MyActionButton annuler = new MyActionButton("Annuler");
		final MyActionButton ajouter= new MyActionButton("Ajouter");;
		MyActionButton supprimer= new MyActionButton("Supprimer");
		// JFrame
		JFrame fen = new JFrame("Undo/Redo");
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Liste
		listModel = new DefaultListModel();
		listModel.addElement("Universite Lille 1");
		listModel.addElement("Master informatique");
		listModel.addElement("IHM");
		list = new JList(listModel);
		JScrollPane listScroller = new JScrollPane(list);
		text = new JTextField();
		listModel.addListDataListener(new ListDataListener() {
			
			@Override
			public void intervalRemoved(ListDataEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void intervalAdded(ListDataEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void contentsChanged(ListDataEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		text.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					if (text.getText().length() > 0) {
						listModel.addElement(text.getText());
						text.setText("");
					} 
				}
			}
		});
		
		
		JButton boutonAjouter = new JButton(ajouter);
		boutonAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (text.getText().length() > 0) {
					listModel.addElement(text.getText());
					text.setText("");
				} 
			} 
		});
		
		
		
		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Edition");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem(supprimer);
		menu.add(menuItem);
		menuItem = new JMenuItem(annuler);
		menu.add(menuItem);
		menuItem = new JMenuItem(refaire);
		menu.add(menuItem);
		
		// ToolBar
		JToolBar toolBar = new JToolBar("Barre d'outils");
		JButton boutonSupprimer = new JButton(supprimer);
		boutonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    int index = list.getSelectedIndex();
			    if (index != -1) listModel.remove(index);
			} 
		});
		
		JButton boutonAnnuler = new JButton(annuler);
		JButton boutonRefaire = new JButton(refaire);
		toolBar.add(boutonSupprimer);
		toolBar.add(boutonAnnuler);
		toolBar.add(boutonRefaire);
		
		boutonAnnuler.getAction().setEnabled(false);
		boutonRefaire.getAction().setEnabled(false);
		
		fen.setJMenuBar(menuBar);
		fen.add(toolBar);
		fen.getContentPane().setLayout(new BoxLayout(fen.getContentPane(),BoxLayout.Y_AXIS));
		fen.getContentPane().add(listScroller);
		fen.getContentPane().add(text);
		fen.getContentPane().add(boutonAjouter);
		fen.pack();
		fen.setVisible(true);		
	}
	
	public static void main(String[] args) {
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			new UndoGUI();
		    }
		});
	}

	
	public class MyActionButton extends AbstractAction{

		public MyActionButton(String texte){
			super(texte);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}