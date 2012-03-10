package com.pcm.webtools.widgets.cellbrowser.events;

import com.google.gwt.event.shared.GwtEvent;
import com.pcm.webtools.widgets.cellbrowser.Cell;

public class OnCellShowEvent extends GwtEvent<OnCellShowHandler> {

	private final Cell cell;
	
	public OnCellShowEvent(Cell cell){
		super();
		this.cell = cell;
	}
	
	public static final Type<OnCellShowHandler> TYPE = new Type<OnCellShowHandler>();
	
	@Override public Type<OnCellShowHandler> getAssociatedType() {
		return TYPE;
	}

	@Override protected void dispatch(OnCellShowHandler handler) {
		handler.onCellShown(this);
	}

	public Cell getShownCell(){return cell;}
}
