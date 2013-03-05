/**
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 */

import model.*;
import view.*;
import controller.*;

public class TemperatureMVC {
	public TemperatureMVC() {
		TemperatureModel tempmod = new TemperatureModel();
		TemperatureController tempcontrolC = new TemperatureController(tempmod);
		TemperatureController tempcontrolF = new TemperatureController(tempmod);
		TemperatureController temperatureController=  new TemperatureController(tempmod);
		TemperatureVueCelsuis pvc = new TemperatureVueCelsuis(tempmod, tempcontrolC, 100, 200);
		TemperatureVueFarenheit tvf = new TemperatureVueFarenheit(tempmod, tempcontrolF, 100, 350);
		TemperatureVueSlider tvs= new TemperatureVueSlider("Slider", tempmod);
		tempcontrolC.addView(pvc);
		tempcontrolF.addView(tvf);	
		
	}
	
	public static void main(String args[]) {
		//Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new TemperatureMVC();
      }
    });
	}
}
