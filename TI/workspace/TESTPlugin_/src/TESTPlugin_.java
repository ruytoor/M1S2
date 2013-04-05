import ij.*;
import ij.plugin.filter.*;
import ij.process.*;
import ij.gui.*;

public class TESTPlugin_ implements PlugInFilter {

	ImagePlus imp;	// Fenêtre contenant l'image de référence
	int width;		// Largeur de la fenêtre
	int height;		// Hauteur de la fenêtre


	public int setup(String arg, ImagePlus imp) {
		/* à compléter*/
		this.imp = imp;
		return PlugInFilter.DOES_RGB;
	}


	public void run(ImageProcessor ip) {
		// Lecture des dimensions de la fenêtre
		width = imp.getWidth();
		height = imp.getHeight();

		// Dispositions possibles pour le CFA
		String[] orders = {"R-G-R", "B-G-B", "G-R-G", "G-B-G-B"};

		// Définition de l'interface
		GenericDialog dia = new GenericDialog("Génération de l'image CFA...", IJ.getInstance());
		dia.addChoice("Début de première ligne :", orders, orders[2]);
		dia.showDialog();

		// Lecture de la réponse de l'utilisateur
		if (dia.wasCanceled()) return;
		int order = dia.getNextChoiceIndex();

		// Génération de l'image CFA
		/* à compléter */
		ImagePlus impCFA=new ImagePlus("CFA", cfa(order));
		impCFA.show();
	}

	//Génère l'image CFA
	ImageProcessor cfa(int row_order) {
		// Image couleur de référence et ses dimensions
		ImageProcessor ip = imp.getProcessor();
		width = imp.getWidth();
		height = imp.getHeight();

		int pixel_value = 0;	// Valeur du pixel source
		ImageProcessor cfa_ip = new ByteProcessor(width,height);	// Image CFA générée

		// Échantillons G
		for (int y=0; y<height; y+=2) {
			for (int x=0; x<width; x+=2) {
				pixel_value = ip.getPixel(x,y);
				int green = (int)(pixel_value & 0x00ff00)>>8;
			cfa_ip.putPixel(x,y,green);
			}
		}
		for (int y=1; y<height; y+=2) {
			for (int x=1; x<width; x+=2) {
				pixel_value = ip.getPixel(x,y);
				int green = (int)(pixel_value & 0x00ff00)>>8;
			cfa_ip.putPixel(x,y,green);
			}
		}
		// Échantillons R
		for (int y=0; y<height; y+=2) {
			for (int x=1; x<width; x+=2) {
				pixel_value = ip.getPixel(x,y);
				int red = (int)(pixel_value & 0xff0000)>>16;
			cfa_ip.putPixel(x,y,red);
			}
		}
		// Échantillons B
		for (int y=1; y<height; y+=2) {
			for (int x=0; x<width; x+=2) {
				pixel_value = ip.getPixel(x,y);
				int blue = (int)(pixel_value & 0x0000ff);
				cfa_ip.putPixel(x,y,blue);
			}
		}

		return cfa_ip;
	}	
}