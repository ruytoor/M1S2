package Controleur;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

import Model.ModelPreCalcule;

public class ColorControleurPreCalcule extends JPanel {//implements Observer{

	ModelPreCalcule colorModel;
	JSlider slide;
	static Random r=new Random();
	private int nbMax;
	private UndoManager uManager;

	//aidé par http://hci.uwaterloo.ca/courses/cs349/s11/resources/java/UndoDemo.java
	int lastSliderValue = 0;
	boolean undoRedoInProgress=false;

	public ColorControleurPreCalcule(int nbMax,ModelPreCalcule m){
		this.nbMax=nbMax;
		colorModel = m;
		uManager=new UndoManager();


		slide=new JSlider(JSlider.HORIZONTAL, 0, nbMax, 0);//m.getCouleur().getRed());
		JPanel undoRedo=new JPanel();
		undoRedo.setLayout(new GridLayout(1, 2));

		final JButton bUndo=new JButton("undo");
		final JButton bRedo=new JButton("redo");
		/*
		slide.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				uManager.addEdit(new MyUndoableEdit(slide, oldValue, slide.getValue()));
				colorModel.setCouleur(slide.getValue());
				oldValue=slide.getValue();
				bUndo.setEnabled(uManager.canUndo());
			}
		});
		 */

		// aidée avec http://hci.uwaterloo.ca/courses/cs349/s11/resources/java/UndoDemo.java
		slide.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{

				colorModel.setCouleur(slide.getValue());
				if (!slide.getValueIsAdjusting())
				{

					if (!undoRedoInProgress)
					{
						final int newValue = slide.getValue();
						final int oldValue = lastSliderValue;
						UndoableEdit undoableEdit = new AbstractUndoableEdit()
						{
							public void redo() throws javax.swing.undo.CannotRedoException
							{
								super.redo();
								undoRedoInProgress = true;
								slide.setValue(newValue);
								undoRedoInProgress = false;
								bUndo.setEnabled(uManager.canUndo());
								bRedo.setEnabled(uManager.canRedo());
							}

							public void undo() throws javax.swing.undo.CannotUndoException
							{
								super.undo();
								undoRedoInProgress = true;
								slide.setValue(oldValue);
								undoRedoInProgress = false;
								bUndo.setEnabled(uManager.canUndo());
								bRedo.setEnabled(uManager.canRedo());
							}
						};
						uManager.addEdit(undoableEdit);
						bUndo.setEnabled(uManager.canUndo());
						bRedo.setEnabled(uManager.canRedo());
					}
					lastSliderValue = slide.getValue();
				}
			}
		});






		bUndo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				uManager.undo();



				bUndo.setEnabled(uManager.canUndo());
				bRedo.setEnabled(uManager.canRedo());
			}
		});

		bRedo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				uManager.redo();

				bUndo.setEnabled(uManager.canUndo());
				bRedo.setEnabled(uManager.canRedo());
			}
		});
		bUndo.setEnabled(uManager.canUndo());
		bRedo.setEnabled(uManager.canRedo());
		undoRedo.add(bUndo);
		undoRedo.add(bRedo);
		this.setLayout(new BorderLayout());
		this.add(new JLabel("couleur :"),BorderLayout.NORTH);
		this.add(slide,BorderLayout.CENTER);
		this.add(undoRedo,BorderLayout.SOUTH);


	}
	public void setRandom(){
		slide.setValue(r.nextInt(nbMax));
		colorModel.setCouleur(slide.getValue());
	}
}
