package com.pcm.webtools.widgets.cellbrowser;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.pcm.webtools.widgets.cellbrowser.auxiliar.HorizontalPanelKeyHandlers;
import com.pcm.webtools.widgets.cellbrowser.events.OnCellShowEvent;
import com.pcm.webtools.widgets.cellbrowser.events.OnCellShowHandler;
/**
 * Widget that behaves similary to GWT CellBrowser widget but created to
 * be easily styled using UiBinder.
 * When the widget is initialized an array of cells is passed to the item. 
 * These cells may contain children which would determine the content to be displayed
 * @author Oscar Bartra
 */
public class CellBrowser extends Composite{
	private HorizontalPanelKeyHandlers columns;
	public CellBrowser(Cell[] cells){
		columns = new HorizontalPanelKeyHandlers();
		columns.setHeight("100%");
		for (int i =0; i < cells.length; i++)
			addEventHandlers(cells[i], 0);
		columns.add(new CellPanel(cells));
		columns.addKeyDownHandler(new KeyDownHandler(){
			@Override public void onKeyDown(KeyDownEvent event) {
				switch (event.getNativeKeyCode()){
					case KeyCodes.KEY_DOWN:
						getLastColumn().selectNext();
						break;
					case KeyCodes.KEY_UP:
						getLastColumn().selectPrevious();
						break;
					case KeyCodes.KEY_LEFT:
						removeLastPanel();
						break;
					case KeyCodes.KEY_RIGHT:
						getLastColumn().selectCurrent();
						break;
				}
			}});
		initWidget(columns);
	}
	
	private void addEventHandlers(final Cell cell, final int level){
		cell.addCellShownHandler(new OnCellShowHandler(){
			@Override public void onCellShown(OnCellShowEvent event) {
				CellPanel subpanel = cell.getSubItems();
				displayCells(subpanel, level+1);
				if (subpanel != null){
					for (int i = 0; i < subpanel.size(); i++)
						addEventHandlers(subpanel.get(i), level+1);
				}
			}
		});
	}
	private void removePriors(int position){
		for (int i = columns.getWidgetCount() -1; i >= position ; i--){
			Widget w = columns.getWidget(i);
			if (w.isAttached())columns.remove(w);
		}
	}
	void displayCells(CellPanel cells, int level){
		removePriors(level);
		if (cells == null) return;
		columns.add(cells);
	}
	private void removeLastPanel(){ 
		if (columns.getWidgetCount() > 1) 
			removePriors(columns.getWidgetCount()-1); 
	}
	protected CellPanel getLastColumn(){ return (CellPanel) columns.getWidget(columns.getWidgetCount()-1);}
}