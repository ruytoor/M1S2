package IUT;
import java.util.Observer;
public interface RVBModel {
public int getR();
public int getV();
public int getB();
public int get(int idx);
public void setR(int r);
public void setV(int v);
public void setB(int b);
public void set(int idx, int value);
public void addObserver(Observer o);
}
