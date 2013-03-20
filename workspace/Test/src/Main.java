
	
	
	import java.awt.Color;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLayeredPane;

	public class Main extends JFrame {
	  public Main() {
	    super("LayeredPane");
	    setSize(200, 150);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);

	    JLayeredPane lp = getLayeredPane();

	    // Create 3 buttons
	    JButton top = new JButton();
	    top.setBackground(Color.white);
	    top.setBounds(20, 20, 50, 50);
	    JButton middle = new JButton();
	    middle.setBackground(Color.gray);
	    middle.setBounds(40, 40, 50, 50);
	    JButton bottom = new JButton();
	    bottom.setBackground(Color.black);
	    bottom.setBounds(60, 60, 50, 50);

	    // Place the buttons in different layers
	    lp.add(middle, new Integer(2));
	    lp.add(top, new Integer(3));
	    lp.add(bottom, new Integer(1));
	  }

	  public static void main(String[] args) {
	    Main sl = new Main();
	    sl.setVisible(true);
	  }
	
	public static void main2(String[] args) {
		// TODO Auto-generated method stub
		float s=0.5653f;
		float hue=0.485f;
		float nivGris=85;		
		Color c=Color.getHSBColor(hue, s, 0.5f);
		float b=0.5f;
		while(((c.getGreen()*0.59)+(c.getBlue()*0.11)+(c.getRed()*0.3))>nivGris+1 || ((c.getGreen()*0.59)+(c.getBlue()*0.11)+(c.getRed()*0.3))<nivGris-1){
			System.out.println(((c.getGreen()*0.59)+(c.getBlue()*0.11)+(c.getRed()*0.3))+"  "+b);
			if(((c.getGreen()*0.59)+(c.getBlue()*0.11)+(c.getRed()*0.3))<nivGris)
				b+=0.005;
			else
				b-=0.005;
			c=Color.getHSBColor(hue, s, b);
		}
	}

	void t(){
		for(int i =0;i<15;++i){
			for(int s=5;s<10;++s){
				Color c=Color.getHSBColor(i/360.0f, s/10.0f, 155/255.0f);
				System.out.println(i+": "+s+" : "+c.getRed()+" "+c.getGreen()+" "+c.getBlue()+" ="+(int)((c.getGreen()*0.59)+(c.getBlue()*0.11)+(c.getRed()*0.3)));			
			}
		}
	}
}
