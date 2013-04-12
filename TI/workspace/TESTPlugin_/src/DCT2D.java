
import ij.process.*;	// Pour classe Float Processor
import java.awt.Rectangle;

abstract public class DCT2D {

	// ---------------------------------------------------------------------------------
	/**
	 * Transformation DCT 2D directe (m�thode de classe) utilisant la s�parabilit�
	 * @param fp Signal 2D d'entr�e et de sortie (MxN) (FloatProcessor)
	 */ 
	public static void forwardDCT(FloatProcessor fp) {

		int width=fp.getWidth();
		int height=fp.getHeight();


		//	float data[][]=fp.getFloatArray();
		double tmp[];

		// Traiter les lignes
		for(int i=0;i<height;++i){
			tmp=new double[width];
			
			for(int d=0;d<width;++d)
				tmp[d]=fp.getPixelValue(d, i)-128;
			
			tmp=DCT1D.forwardDCT(tmp);
			
			for(int y=0;y<width;++y)
				fp.putPixelValue(y, i, tmp[y]);
			
		}

		
		// Traiter les colonnes de l'image r�sultant du traitement des lignes
		for(int i=0;i<width;++i){
			tmp=new double[height];
			
			for(int d=0;d<height;++d)
				tmp[d]=fp.getPixelValue(i, d);
			
			tmp=DCT1D.forwardDCT(tmp);
			
			for(int y=0;y<height;++y){
				fp.putPixelValue(i, y, tmp[y]);
			}
			
		}
	}

	// ---------------------------------------------------------------------------------
	/**
	 * Transformation DCT 2D inverse (m�thode de classe)
	 * @param fp Signal 2D d'entr�e et de sortie (FloatProcessor)
	 */
	public static void inverseDCT(FloatProcessor fp) {

		int width=fp.getWidth();
		int height=fp.getHeight();


		//	float data[][]=fp.getFloatArray();
		double tmp[];

		// Traiter les lignes
		for(int i=0;i<height;++i){
			tmp=new double[width];
			
			for(int d=0;d<width;++d)
				tmp[d]=fp.getPixelValue(d, i);
			
			tmp=DCT1D.inverseDCT(tmp);
			
			for(int y=0;y<width;++y)
				fp.putPixelValue(y, i, tmp[y]);
			
		}

		
		// Traiter les colonnes de l'image r�sultant du traitement des lignes
		for(int i=0;i<width;++i){
			tmp=new double[height];
			
			for(int d=0;d<height;++d)
				tmp[d]=fp.getPixelValue(i, d);
			
			tmp=DCT1D.inverseDCT(tmp);
			
			for(int y=0;y<height;++y){
				fp.putPixelValue(i, y, tmp[y])+128;
			}
			
		}
	}
}