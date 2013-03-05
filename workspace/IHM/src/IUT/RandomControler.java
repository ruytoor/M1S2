package IUT;


public class RandomControler {
	private RVBModel model;
	
	public RandomControler(RVBModel m){
		model=m;
	}
	
	public void go(int turn) {
		for (int i=0; i<turn; i++) {
		try {
		Thread.sleep(1500);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		int idx = ((int) (Math.random()*100)) % 3;
		model.set(idx, (int) (Math.random()*100));
		}
		}

}
