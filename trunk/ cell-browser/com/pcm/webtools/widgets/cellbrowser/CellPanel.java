package com.pcm.webtools.widgets.cellbrowser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.pcm.webtools.widgets.cellbrowser.auxiliar.BaseCellPanel;
import com.pcm.webtools.widgets.cellbrowser.events.OnCellShowEvent;
import com.pcm.webtools.widgets.cellbrowser.events.OnCellShowHandler;
/**
 * CellPanel is the Panel that contains all the individual Cells
 * @author Oscar Bartra
 */
public class CellPanel extends Composite{

	static final int COLUMN_WIDTH = 300;
	static final int HEADER_HEIGHT = 20;
	static final int FOOTER_HEIGHT = 25;
	private static CellPanelUiBinder uiBinder = GWT.create(CellPanelUiBinder.class);
	private int selecteditem = -1;
	interface CellPanelUiBinder extends UiBinder<Widget, CellPanel> {}
	@UiField BaseCellPanel panel;
	@UiField SimplePanel header;
	@UiField SimplePanel footer;
	@UiField DockLayoutPanel wrapper;
	/** Creates a new cellpanel with the specified width */
	public CellPanel(Cell[] cells, int column_width){
		this(cells);
		setWidth(column_width + "px");
	}
	/** Creates a new cellpanel with the default width */
	public CellPanel(Cell[] cells) {
		initWidget(uiBinder.createAndBindUi(this));
		for (Cell c : cells) addCell(c);
		setWidth(COLUMN_WIDTH + "px");
	}
	/** Removes the panel from the DOM **/
	void remove(){ panel.remove();}
	/** Removes a cell c1 from the panel **/
	public void removeCell(Cell c1){
		if (c1 == null || panel == null) return;
		for (int i = 0; i < panel.getWidgetCount(); i++){
			Cell c2 = panel.getCell(i);
			if (c1.equals(c2)) {
				c2.remove();
			}
		}
	}
	/** Removes all cells from the panel */
	public void removeAllCells(){
		for (int i = size()-1; i >= 0; i--)
			removeCell(get(i));
		selecteditem = -1;
	}
	/** Adds a cell to the panel */
	public void addCell(final Cell c){ 
		panel.add(c);
		c.addCellShownHandler(new OnCellShowHandler(){
			@Override public void onCellShown(OnCellShowEvent event) {
				selecteditem = panel.getCellIndex(event.getShownCell());
				for (int i = 0; i < panel.getWidgetCount(); i++)
						panel.getCell(i).setHighlighted(false);	
				event.getShownCell().setHighlighted(true);
			}});
	}
	/** Sets the top header of the panel.
	 * If this value is not set the height of this element is 0 by default
	 * @param c cell to use as Header
	 */
	public void setHeader(final Cell c){
		if (c == null) return;
		wrapper.setWidgetSize(header, HEADER_HEIGHT);
		header.setWidget(c);
	}
	/** Sets the footer of the panel.
	 * If this value is not set the height of this element is 0 by default
	 * @param c cell to use as Footer
	 */
	public void setFooter(final Cell c){
		if (c == null) return;
		wrapper.setWidgetSize(footer, FOOTER_HEIGHT);
		footer.setWidget(c);
	}
	/** @return the number of Cells that the panel contains */
	public int size(){ return panel.getWidgetCount();}
	/** @return a cell at the position index. Returns null if index is out of bounds */
	public Cell get(int index){
		if (index < 0 || index > size()) return null;
		else return panel.getCell(index);
	}
	/**Selects the following cell, if none are selected it selects the first one*/
	void selectNext(){
		if (selecteditem +1 >= panel.getWidgetCount()) return;
		selecteditem++;
		highlightCell(panel.getCell(selecteditem));
	}
	/**Selects the current cell, (it shows its content if it had previously been unselected)*/
	void selectCurrent(){
		if (selecteditem == -1) return;
		Cell c = panel.getCell(selecteditem);
		c.showSubItems();
	}
	/**Selects the previous cell, if none are selected it selects the first one*/
	void selectPrevious(){
		if (selecteditem < 0) return;
		selecteditem--;
		if (selecteditem == -1) highlightCell(panel.getCell(0));
		else highlightCell(panel.getCell(selecteditem));
	}
	/** 
	 * Highlights the cell c
	 * @param c Cell to highlight
	 */
	private void highlightCell(Cell c){
		selecteditem = panel.getCellIndex(c);
		for (int i = 0; i < panel.getWidgetCount(); i++)
				panel.getCell(i).setHighlighted(false);	
		c.setHighlighted(true);
	}
	/** Adds an array of cells to the panel */
	public void addContent(Cell[] subItems) {
		for (Cell c : subItems) addCell(c);
	}
	/** Replaces the current content of the panel with the new array of cells.
	 * It is a synonym of calling removeAllCells() and addContent(Cell[] subItems) */
	public void setContent(Cell[] subItems){
		removeAllCells();
		addContent(subItems);
	}
}