package com.pcm.webtools.widgets.cellbrowser.auxiliar;

import java.util.Iterator;

import com.google.gwt.event.dom.client.HasAllKeyHandlers;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InsertPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * This code is based on:
 * http://stackoverflow.com/questions/4420548/register-keydownhandler-on-gwt-verticalpanel
 */

public class HorizontalPanelKeyHandlers extends Composite implements HasWidgets, InsertPanel.ForIsWidget, HasAllKeyHandlers{
	private HorizontalPanel hpanel;
	private FocusPanel fpanel;
	
	public HorizontalPanelKeyHandlers(){
		hpanel = new HorizontalPanel();
		fpanel = new FocusPanel();
		fpanel.setWidget(hpanel);
		initWidget(fpanel);
	}
	
	@Override public HandlerRegistration addKeyUpHandler(KeyUpHandler handler) {
		return fpanel.addKeyUpHandler(handler);
	}

	@Override public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
		return fpanel.addKeyDownHandler(handler);
	}

	@Override public HandlerRegistration addKeyPressHandler(KeyPressHandler handler) {
		return fpanel.addKeyPressHandler(handler);
	}

	@Override public void add(Widget w) {
		hpanel.add(w);
	}

	@Override public void clear() {
		hpanel.clear();		
	}

	@Override public Iterator<Widget> iterator() {
		return hpanel.iterator();
	}

	@Override public boolean remove(Widget w) {
		return hpanel.remove(w);
	}

	@Override public void insert(Widget w, int beforeIndex) {
		hpanel.insert(w, beforeIndex);
	}

	@Override public Widget getWidget(int index) {
		return hpanel.getWidget(index);
	}

	@Override public int getWidgetCount() {
		return hpanel.getWidgetCount();
	}

	@Override public int getWidgetIndex(Widget child) {
		return hpanel.getWidgetIndex(child);
	}

	@Override public boolean remove(int index) {
		return hpanel.remove(index);
	}

	@Override public int getWidgetIndex(IsWidget child) {
		return hpanel.getWidgetIndex(child);
	}

	@Override public void add(IsWidget w) {
		hpanel.add(w);
	}

	@Override public void insert(IsWidget w, int beforeIndex) {
		hpanel.insert(w, beforeIndex);
	}
	@Override public void setSize(String width, String height){
		hpanel.setSize(width, height);
		fpanel.setSize(width, height);
	}
	@Override public void setWidth(String width){
		hpanel.setWidth(width);
		fpanel.setWidth(width);
	}
	@Override public void setHeight(String height){
		hpanel.setHeight(height);
		fpanel.setHeight(height);
	}
}