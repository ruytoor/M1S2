package IUT;

import java.util.Observable;
import java.util.Observer;

public class TextualView implements Observer {

	
	public TextualView(RVBModel m){
	m.addObserver(this);
	}
	
	@Override
	public void update(Observable model, Object arg1) {
		System.out.println("r:"+((RVBModel)model).getR()+" v:"+((RVBModel)model).getV()+" b:"+((RVBModel)model).getB());
		
	}

}
