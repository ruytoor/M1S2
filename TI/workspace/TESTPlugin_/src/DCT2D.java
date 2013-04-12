
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
			tmp=fp.getLine(0, i, width, i);
			tmp=DCT1D.forwardDCT(tmp);
			for(int y=0;y<width;++y){
				fp.putPixelValue(y, i, tmp[y]);
			}
		}

		// Traiter les colonnes de l'image r�sultant du traitement des lignes
		for(int i=0;i<width;++i){
			tmp=fp.getLine(i, 0, i, height);
			tmp=DCT1D.forwardDCT(tmp);
			for(int y=0;y<width;++y){
				fp.putPixelValue(i, y, tmp[y]);
			}
		}
		for(int x=0;x<height;++x)
			for(int y=0;y<width;++y)
				System.out.println(fp.getPixelValue(x, y));
	}

	// ---------------------------------------------------------------------------------
	/**
	 * Transformation DCT 2D inverse (m�thode de classe)
	 * @param fp Signal 2D d'entr�e et de sortie (FloatProcessor)
	 */
	public static void inverseDCT(FloatProcessor fp) {

		// Traiter les lignes
		/* � compl�ter */

		// Traiter les colonnes de l'image r�sultant du traitement des lignes
		/* � compl�ter */
	}
}