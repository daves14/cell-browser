package com.pcm.webtools.widgets.cellbrowser.auxiliar;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.pcm.webtools.widgets.cellbrowser.Cell;
import com.pcm.webtools.widgets.cellbrowser.events.OnCellRemovedEvent;
import com.pcm.webtools.widgets.cellbrowser.events.OnCellRemovedHandler;
import com.pcm.webtools.widgets.cellbrowser.events.OnPanelRemovedEvent;

public class BaseCellPanel  extends VerticalPanel implements HasHandlers{

	private HandlerManager handlermanager;
	public BaseCellPanel(){
		handlermanager = new HandlerManager(this);
	}
	public void add(Cell c) {
		super.add(c);
	}
	public boolean remove(Cell c) {
		return super.remove(c);
	}
	public Cell getCell(int index){
		return (Cell)this.getWidget(index);
	}
	public int getCellIndex(Cell c){
		return super.getWidgetIndex(c);
	}
	public void remove(){
		removeFromParent();
	    fireEvent(new OnPanelRemovedEvent(this));
	}
	
	@Override public void fireEvent(GwtEvent<?> event){handlermanager.fireEvent(event);}
	public HandlerRegistration addCellRemovedEventHandler(OnCellRemovedHandler handler){
		return handlermanager.addHandler(OnCellRemovedEvent.TYPE, handler);
	}
}