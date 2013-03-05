package IUT;
import java.util.Observable;

public class Model extends Observable implements RVBModel  {
	private int r;
	private int v;
	private int b;
	
	/*0private void observableAction(){
		this.setChanged();
		this.notifyObservers();
		this.clearChanged();
	}*/
	
	public Model(int r,int v,int b){
		this.r=r;
		this.b=b;
		this.v=v;
	}

	public String toString(){
		return ("r :"+r+" v :"+v+" b "+b);
	}
	
	@Override
	public int get(int idx) {
		if(idx==0)
			return r;
		if(idx==1)
			return v;
		if(idx==2)
			return b;
		return -1;
	}

	@Override
	public int getB() {
		// TODO Auto-generated method stub
		return b;
	}

	@Override
	public int getR() {
		// TODO Auto-generated method stub
		return r;
	}

	@Override
	public int getV() {
		// TODO Auto-generated method stub
		return v;
	}

	@Override
	public void set(int idx, int value) {
		if(idx==0)
			setR(value);
		if(idx==1)
			setV(value);
		if(idx==2)
			setB(value);
		
		//System.out.println(this.toString());
		this.setChanged();
		this.notifyObservers();
		//this.observableAction();

	}

	@Override
	public void setB(int b) {
		this.b=b;
		int avant=getV()+getR();
		setrV(getV()*(100-b)/avant);
		setrR(getR()*(100-b)/avant);
	}

	@Override
	public void setR(int r) {
		this.r=r;
		int avant=getV()+getB();
		setrV(getV()*(100-r)/avant);
		setrB(getB()*(100-r)/avant);
		}

	@Override
	public void setV(int v) {
		this.v=v;
		int avant=getB()+getR();
		setrB(getB()*(100-v)/avant);
		setrR(getR()*(100-v)/avant);
		}
	
	private void setrB(int b){
		this.b=b;
	}
	private void setrV(int v){
		this.v=v;
	}
	private void setrR(int r){
		this.r=r;
	}
}
