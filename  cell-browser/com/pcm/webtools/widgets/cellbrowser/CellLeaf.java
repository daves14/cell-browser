package com.pcm.webtools.widgets.cellbrowser;

import com.google.gwt.user.client.ui.Widget;
/**
 * This is a convenience class that simplifies the creation of cells that will have no children.
 * @author Oscar Bartra
 */
public class CellLeaf extends Cell{

	public CellLeaf(int cellheight, Widget currentitem) {
		super(cellheight, currentitem, null);
	}
	public CellLeaf(int cellheight, Widget currentitem, boolean hoverhighlights){
		super(cellheight, currentitem, null, hoverhighlights);
	}
	public CellLeaf(Widget currentitem) {
		super(currentitem, null);
	}
	public CellLeaf(Widget currentitem, boolean hoverhighlights){
		super(currentitem, null, hoverhighlights);
	}
	/**The default empty constructor creates a separation line between elements */
	public CellLeaf() {super();}
}
