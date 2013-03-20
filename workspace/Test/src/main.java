import java.awt.Color;


public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
