import ij.*;
import ij.plugin.filter.*;
import ij.process.*;
import ij.gui.*;

public class sampleCFA_ implements PlugInFilter {

	ImagePlus imp;	// Fenêtre contenant l'image de référence
	int width;		// Largeur de la fenêtre
	int height;		// Hauteur de la fenêtre
	private final static float[] MASQUE_R_and_B = {0.25f,0.5f,0.25f, 0.5f,1.0f,0.5f, 0.25f,0.5f,0.25f};// RED and BLUE
	private final static float[] MASQUE_G =	{0.0f,0.25f,0.0f, 0.25f,1.0f,0.25f,0.0f,0.25f,0.0f	}; // GREEN

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
		String[] orders = {"G-R-G","G-B-G","B-G-B","R-G-R"};

		// Définition de l'interface
		GenericDialog dia = new GenericDialog("Génération de l'image CFA...", IJ.getInstance());
		dia.addChoice("Début de première ligne :", orders, orders[0]);
		dia.showDialog();

		// Lecture de la réponse de l'utilisateur
		if (dia.wasCanceled()) return;
		int order = dia.getNextChoiceIndex();
		// Génération de l'image CFA
		/* à compléter */
		ImagePlus impCFA=new ImagePlus("CFA", cfa(order));
		impCFA.show();
		Convolver con=new Convolver();
		con.setNormalize(false);

		if(order==0){// la suite se fait en G-R-G
			// Calcul des échantillons de chaque composante de l'image CFA

			ImageStack samples_stack = imp.createEmptyStack();
			ImageProcessor red;
			ImageProcessor green;
			ImageProcessor blue;

			red=cfa_samples(impCFA.getProcessor(),0);
			green=cfa_samples(impCFA.getProcessor(),1);
			blue=cfa_samples(impCFA.getProcessor(),2);


			con.convolve(red, MASQUE_R_and_B, 3, 3);
			con.convolve(blue, MASQUE_R_and_B, 3, 3);
			con.convolve(green, MASQUE_G, 3, 3);


			samples_stack.addSlice("rouge", red);	// Composante R
			samples_stack.addSlice("vert",green );// Composante G
			samples_stack.addSlice("bleu",blue );	// Composante B


			// Création de l'image résultat
			ImagePlus cfa_samples_imp = imp.createImagePlus();
			cfa_samples_imp.setStack("exercice 2", samples_stack);
			cfa_samples_imp.show();

			//----------------------fin exercice 2

			ImageStack samples_stack_ex3 = imp.createEmptyStack();
			ImageProcessor red_ex3;
			ImageProcessor green_ex3;
			ImageProcessor blue_ex3;

			green_ex3=est_G_hamilton(impCFA.getProcessor());

			red_ex3=cfa_samples(impCFA.getProcessor(),0);
			con.convolve(red_ex3, MASQUE_R_and_B, 3, 3);

			//green_ex3=cfa_samples(impCFA.getProcessor(),1);
			blue_ex3=cfa_samples(impCFA.getProcessor(),2);
			con.convolve(blue_ex3, MASQUE_R_and_B, 3, 3);

			samples_stack_ex3.addSlice("rouge", red_ex3);	// Composante R
			samples_stack_ex3.addSlice("vert",green_ex3 );// Composante G
			samples_stack_ex3.addSlice("bleu",blue_ex3 );	// Composante B

			// Création de l'image résultat
			ImagePlus cfa_samples_imp_ex3 = imp.createImagePlus();
			cfa_samples_imp_ex3.setStack("exercice 3", samples_stack_ex3);
			cfa_samples_imp_ex3.show();

		}	
	}
	/*
	 * Sur du B-G-B
	 */
	private ImageProcessor cfa_samples(ImageProcessor ip, int i) {
		width = ip.getWidth();
		height = ip.getHeight();
		ImageProcessor one_c_ip = new ByteProcessor(width,height);
		int startX,startY;
		if(i==1){ // vert
			startX=0;
			startY=0;
			for (int y=1; y<height; y+=2) {
				for (int x=1; x<width; x+=2) {
					int pixel_value = ip.getPixel(x,y);
					one_c_ip.putPixel(x,y,pixel_value);
				}
			}

		}else if(i==2){ // blue
			startX=0;
			startY=1;
		}else {// i==0 red
			startX=1;
			startY=0;
		}

		for (int y=startY; y<height; y+=2) {
			for (int x=startX; x<width; x+=2) {
				int pixel_value = ip.getPixel(x,y);
				one_c_ip.putPixel(x,y,pixel_value);
			}
		}
		return one_c_ip;
	}


	//Génère l'image CFA
	ImageProcessor cfa(int row_order) {
		// Image couleur de référence et ses dimensions
		ImageProcessor ip = imp.getProcessor();
		width = imp.getWidth();
		height = imp.getHeight();

		int pixel_value = 0;	// Valeur du pixel source
		ImageProcessor cfa_ip = new ByteProcessor(width,height);	// Image CFA générée
		int startGX1,startGY1,startGX2,startGY2,startRX,startRY,startBX,startBY;
		if(row_order==1){
			startGX1=0;
			startGY1=0;
			startGX2=1;
			startGY2=1;
			startRX=0;
			startRY=1;
			startBX=1;
			startBY=0;
		}else if(row_order==2){
			startGX1=1;
			startGY1=0;
			startGX2=0;
			startGY2=1;
			startRX=1;
			startRY=1;
			startBX=0;
			startBY=0;
		}else if(row_order==3){
			startGX1=1;
			startGY1=0;
			startGX2=0;
			startGY2=1;
			startRX=0;
			startRY=0;
			startBX=1;
			startBY=1;
		}else{//row ==0 
			startGX1=0;
			startGY1=0;
			startGX2=1;
			startGY2=1;
			startRX=1;
			startRY=0;
			startBX=0;
			startBY=1;
		}


		// Échantillons G
		for (int y=startGY1; y<height; y+=2) {
			for (int x=startGX1; x<width; x+=2) {
				pixel_value = ip.getPixel(x,y);
				int green = ((int)(pixel_value & 0x00ff00)>>8);
				cfa_ip.putPixel(x,y,green);
			}
		}
		for (int y=startGY2; y<height; y+=2) {
			for (int x=startGX2; x<width; x+=2) {
				pixel_value = ip.getPixel(x,y);
				int green = ((int)(pixel_value & 0x00ff00)>>8);
				cfa_ip.putPixel(x,y,green);
			}
		}
		// Échantillons R
		for (int y=startRY; y<height; y+=2) {
			for (int x=startRX; x<width; x+=2) {
				pixel_value = ip.getPixel(x,y);
				int red = ((int)(pixel_value & 0xff0000)>>16);
				cfa_ip.putPixel(x,y,red);
			}
		}
		// Échantillons B
		for (int y=startBY; y<height; y+=2) {
			for (int x=startBX; x<width; x+=2) {
				pixel_value = ip.getPixel(x,y);
				int blue = (int)(pixel_value & 0x0000ff);
				cfa_ip.putPixel(x,y,blue);
			}
		}

		return cfa_ip;
	}	

	ImageProcessor est_G_hamilton(ImageProcessor cfa_ip) {
		ImageProcessor est_ip = cfa_ip.duplicate();
		//les rouges
		for (int y=0; y<height; y+=2) {
			for (int x=1; x<width; x+=2) {
				int delX=Math.abs(cfa_ip.getPixel(x-1, y) - cfa_ip.getPixel(x+1, y)) + Math.abs(2*cfa_ip.getPixel(x, y)-cfa_ip.getPixel(x-2, y)-cfa_ip.getPixel(x+2, y));
				int delY=Math.abs(cfa_ip.getPixel(x, y-1) - cfa_ip.getPixel(x, y+1)) + Math.abs(2*cfa_ip.getPixel(x, y)-cfa_ip.getPixel(x, y-2)-cfa_ip.getPixel(x, y+2));
				if(delX<delY){
					est_ip.putPixel(x,y,((cfa_ip.getPixel(x-1, y) + cfa_ip.getPixel(x+1, y))/2 + (2*cfa_ip.getPixel(x, y) - cfa_ip.getPixel(x-2, y)-cfa_ip.getPixel(x+2, y))/4));
				}else if(delX>delY){
					est_ip.putPixel(x,y,((cfa_ip.getPixel(x, y-1) + cfa_ip.getPixel(x, y+1))/2 + (2*cfa_ip.getPixel(x, y) - cfa_ip.getPixel(x, y-2) - cfa_ip.getPixel(x, y+2))/4));
				}else{// delX==delY
					est_ip.putPixel(x,y,((cfa_ip.getPixel(x, y-1) + cfa_ip.getPixel(x, y+1)+cfa_ip.getPixel(x-1, y)+cfa_ip.getPixel(x+1, y))/4+(4*cfa_ip.getPixel(x, y)-cfa_ip.getPixel(x-2, y)-cfa_ip.getPixel(x+2, y)-cfa_ip.getPixel(x, y-2)-cfa_ip.getPixel(x, y+2))/8));
				}
			}
		}
		//les bleus
		for (int y=1; y<height; y+=2) {
			for (int x=0; x<width; x+=2) {
				int delX=Math.abs(cfa_ip.getPixel(x-1, y) - cfa_ip.getPixel(x+1, y)) + Math.abs(2*cfa_ip.getPixel(x, y) - cfa_ip.getPixel(x-2, y) - cfa_ip.getPixel(x+2, y));
				int delY=Math.abs(cfa_ip.getPixel(x, y-1) - cfa_ip.getPixel(x, y+1)) + Math.abs(2*cfa_ip.getPixel(x, y) - cfa_ip.getPixel(x, y-2) - cfa_ip.getPixel(x, y+2));
				if(delX<delY){
					est_ip.putPixel(x,y,((cfa_ip.getPixel(x-1, y) + cfa_ip.getPixel(x+1, y))/2 + (2*cfa_ip.getPixel(x, y) - cfa_ip.getPixel(x-2, y) - cfa_ip.getPixel(x+2, y))/4));
				}else if(delX>delY){
					est_ip.putPixel(x,y,((cfa_ip.getPixel(x, y-1) + cfa_ip.getPixel(x, y+1))/2 + (2*cfa_ip.getPixel(x, y)-cfa_ip.getPixel(x, y-2)-cfa_ip.getPixel(x, y+2))/4));
				}else{// delX==delY
					est_ip.putPixel(x,y,((cfa_ip.getPixel(x, y-1) + cfa_ip.getPixel(x, y+1) + cfa_ip.getPixel(x-1, y) + cfa_ip.getPixel(x+1, y))/4 + (4*cfa_ip.getPixel(x, y) - cfa_ip.getPixel(x-2, y) - cfa_ip.getPixel(x+2, y) - cfa_ip.getPixel(x, y-2) - cfa_ip.getPixel(x, y+2))/8));
				}
			}
		}
		return (est_ip);
	}
}