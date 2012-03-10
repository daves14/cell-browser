package com.pcm.webtools.widgets.cellbrowser.events;

import com.google.gwt.event.shared.GwtEvent;
import com.pcm.webtools.widgets.cellbrowser.auxiliar.BaseCellPanel;

public class OnPanelRemovedEvent extends GwtEvent<OnPanelRemovedHandler> {

	private final BaseCellPanel panel;
	
		public OnPanelRemovedEvent(BaseCellPanel panel) {
		super();
		this.panel = panel;
	}

	public static final Type<OnPanelRemovedHandler> TYPE = new Type<OnPanelRemovedHandler>();
	
	@Override public Type<OnPanelRemovedHandler> getAssociatedType() {
		return TYPE;
	}

	@Override protected void dispatch(OnPanelRemovedHandler handler) {
		handler.onPanelRemoved(this);
	}

	public BaseCellPanel getRemovedPanel(){return panel;}
}
