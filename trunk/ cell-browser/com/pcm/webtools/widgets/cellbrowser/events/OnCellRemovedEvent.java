package com.pcm.webtools.widgets.cellbrowser.events;

import com.google.gwt.event.shared.GwtEvent;
import com.pcm.webtools.widgets.cellbrowser.Cell;

public class OnCellRemovedEvent extends GwtEvent<OnCellRemovedHandler> {

	private final Cell cell;
	
	public OnCellRemovedEvent(Cell cell){
		super();
		this.cell = cell;
	}
	
	public static final Type<OnCellRemovedHandler> TYPE = new Type<OnCellRemovedHandler>();
	
	@Override public Type<OnCellRemovedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override protected void dispatch(OnCellRemovedHandler handler) {
		handler.onCellRemoved(this);
	}

	public Cell getRemovedCell(){return cell;}
}
