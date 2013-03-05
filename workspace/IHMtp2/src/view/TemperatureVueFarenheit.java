/**
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 */

package view;

import java.util.Observable;
import java.awt.event.*;
import model.*;
import controller.*;

public class TemperatureVueFarenheit extends TemperatureVue {
	public TemperatureVueFarenheit(TemperatureModel modele,
			TemperatureController controleur, int posX, int posY) {
		super("Temperature Farenheit",modele, controleur, posX, posY);
		setDisplay(""+model.getF());
		addUpListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.augmenteDegresF();
			}});
		addDownListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.diminueDegresF();
			}});
		addDisplayListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double tempF = getDisplay();
				controller.fixeDegresF(tempF);
			}});
	}

	public void update(Observable s, Object o) {
		setDisplay(""+model().getF());
	}
}
