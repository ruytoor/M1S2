package UndoRedo;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

import Model.ModelPreCalcule;

public class MyUndoableEdit extends AbstractUndoableEdit {

	private JSlider s;
	private int iold;
	private int inew;
	public MyUndoableEdit(JSlider s,int iold,int inew) {
		// TODO Auto-generated constructor stub
		super();
		this.s=s;
		this.iold=iold;
		this.inew=inew;
	}
	
    public void undo() throws CannotUndoException {
    	super.undo();
    	s.setValue(iold);
    }

    public void redo() throws CannotUndoException {
    	super.redo();
    	s.setValue(inew);
    }
}
