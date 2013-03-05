package TP5;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

public class AddUndoableListe extends AbstractUndoableEdit {
	private DefaultListModel list;
	private int indice;
	private Object o;
	
	public AddUndoableListe(DefaultListModel list,int indice,Object o){
		this.list=list;
		this.indice=indice;
		this.o=o;
	}
	
	@Override
	public void undo() throws CannotUndoException {
		// TODO Auto-generated method stub
		super.undo();
		list.remove(indice);
	}
	
	@Override
	public void redo() throws CannotRedoException {
		// TODO Auto-generated method stub
		super.redo();
		list.add(indice, o);
	}
}
