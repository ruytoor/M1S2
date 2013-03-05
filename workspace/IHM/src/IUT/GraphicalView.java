package IUT;
import java.awt.*;
import java.util.*;
import javax.swing.*;




public class GraphicalView extends JPanel implements Observer {
	protected RVBModel model;
	protected Color[] colors;
	
	public GraphicalView(RVBModel m, Color r, Color v, Color b) {
		this.model = m;
		this.model.addObserver(this);
		this.colors = new Color[]{r, v, b};
		setOpaque(true);
	}
	
	public void update(Observable ob, Object o) {
		repaint();
		//System.out.println("update  Graphical view");
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(300, 100);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Rectangle r = g.getClipBounds();
		int start = 0;
		for (int i=0; i<3; i++) {
			int width = (int) (model.get(i) * r.getWidth() / 100);
			g.setColor(colors[i]);
			g.fillRect(start, 0, start+width, (int) r.getHeight());
			start += width;
		}
	}
	
	/*public static void main(String[] args) {
		Model m = new Model(33,34,33);
		Model.createJFrame("TextView", m, new TextView(m)).setVisible(true);
		Model.createJFrame("SliderView", m, new SliderView(m)).setVisible(true);
		Model.createJFrame("GraphicalView", m, new GraphicalView(m, Color.RED, 
				Color.GREEN, Color.BLUE)).setVisible(true);
	} 
	*/
	
}
