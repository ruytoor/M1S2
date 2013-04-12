import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;


public class dtc_ implements PlugInFilter {

	ImagePlus imp;
	
	@Override
	public int setup(String arg, ImagePlus imp) {
		// TODO Auto-generated method stub
		this.imp = imp;
		return PlugInFilter.DOES_32;
	}

	@Override
	public void run(ImageProcessor ip) {
		FloatProcessor fp=(FloatProcessor) ip.duplicate();
		// TODO Auto-generated method stub
		DCT2D.forwardDCT(fp);
		
		new ImagePlus("DCT2D", fp).show();

	}

}
