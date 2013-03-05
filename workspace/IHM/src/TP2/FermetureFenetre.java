package TP2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FermetureFenetre extends WindowAdapter {

	public void windowClosing(WindowEvent e){
		System.out.println("Fenetre en cours de fermeture");
		System.exit(0);
	}
}
