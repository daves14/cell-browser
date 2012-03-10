package com.pcm.webtools.widgets.cellbrowser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.pcm.webtools.widgets.cellbrowser.events.OnCellRemovedEvent;
import com.pcm.webtools.widgets.cellbrowser.events.OnCellShowEvent;
import com.pcm.webtools.widgets.cellbrowser.events.OnCellShowHandler;
/**
 * Individual Unit that constitutes the CellBrowser.
 * It can hold any widget and contain any number of subItems grouped in a CellPanel
 * @author Oscar Bartra
 */
public class Cell extends Composite implements HasHandlers, OnCellShowHandler{
	private static CellUiBinder uiBinder = GWT.create(CellUiBinder.class);
	private CellPanel subitems;
	private boolean canremainhighlighted;
	interface CellUiBinder extends UiBinder<Widget, Cell> {	}
	private HandlerManager handlermanager;
	interface Style extends CssResource {
		String leaf();
		String notleaf();
		String highlighted();
		String mouseover();
	}
	
	@UiField Style style;
	@UiField SimplePanel content;
	@UiField Label arrow;
	@UiField FocusPanel panel;
	@UiField Label dash;
	/**The default empty constructor creates a separation line between elements */
	public Cell(){
		this(	20, 
				new HTML("<hr style='width: 100%; color:gray; background-color:gray; height:1px; border:0px;'>"), 
				null,
				false
			); 
		canremainhighlighted = false;
		dash.removeFromParent();
	}
	public Cell(Widget currentitem, CellPanel subitems){ this(20, currentitem, subitems);}
	public Cell(Widget currentitem, CellPanel subitems, boolean hoverhighlights){this(20, currentitem, subitems, hoverhighlights);}
	public Cell(int cellheight, Widget currentitem, CellPanel subitems){this(cellheight, currentitem, subitems, true);}
	public Cell(int cellheight, Widget currentitem, CellPanel subitems, boolean hoverhighlights){
		handlermanager = new HandlerManager(this);
		canremainhighlighted = true;
		initWidget(uiBinder.createAndBindUi(this));
		 
		content.setWidget(currentitem);
		setSubItems(subitems);
		
		panel.setHeight(cellheight + "px");
		if (hoverhighlights){
			panel.addMouseOverHandler(new MouseOverHandler(){@Override public void onMouseOver(MouseOverEvent event) {panel.addStyleName(style.mouseover());}});
			panel.addMouseOutHandler(new MouseOutHandler(){@Override public void onMouseOut(MouseOutEvent event) {panel.removeStyleName(style.mouseover());}});	
		}
	}

	@UiHandler ("panel") void onClick(ClickEvent event) {this.showSubItems();}
	/** @return the Widget that the Cell contains */
	public Widget getSelf(){ return content.getWidget();}
	/** Sets a new Widget for the cell, replacing any previous widget */
	public void setWidget(Widget widget){ content.setWidget(widget);}
	/** removes the cell from the DOM and fires the OnCellRemovedEvent */
	public void remove(){
		if (subitems != null) subitems.remove();
		removeFromParent();
		fireEvent(new OnCellRemovedEvent(this));
	}
	/** removes all cell sub items converting it into a leaf cell */
	public void removeSubItems(){
		if (subitems != null){
			this.getSubItems().remove();
			subitems = null;
		}
		arrow.setText("");
		content.addStyleName(style.leaf());
	}
	/** Adds subitems to the current cell */
	public void setSubItems(CellPanel subitems){ 
		this.subitems = subitems;
		if (this.subitems == null){
			arrow.setText("");
			content.addStyleName(style.leaf());
		}else{
			arrow.setText("►");
			content.addStyleName(style.notleaf());
		}
	}
	/** If the element has subitems it fires the event that causes the content to be shown*/
	public void showSubItems(){
		if (!isLeaf())
			handlermanager.fireEvent(new OnCellShowEvent(this));
	}
	/** @return a CellPanel containing all subitems */
	public CellPanel getSubItems(){ return subitems;}
	/** @return whether the current cell has subitems (not a leaf, false) or not (leaf, true) */
	public boolean isLeaf(){ return subitems == null;}
	/** Sets the value for whether the current cell can be highlighted. Non highlightable cells can not be browsed using the keyboard. All leaf cells are non-highlightable*/
	public void setCanHighlight(boolean value){ canremainhighlighted = value;}
	/** @return whether the current cell can be highlighted. Non highlightable cells can not be browsed using the keyboard. All leaf cells are non-highlightable */
	private boolean getCanHighlight(){return canremainhighlighted;}
	/** if the cell can be highlighted it sets the highlight state (on/off) to value*/
	public void setHighlighted(boolean value){
		if (value && getCanHighlight() && !isLeaf()){
			addStyleName(style.highlighted());
		}else
			removeStyleName(style.highlighted());
	}
	@Override public void onCellShown(OnCellShowEvent event) {}
	/** Adds a CellShowHandler event to the current cell */
	public HandlerRegistration addCellShownHandler(OnCellShowHandler handler){
		return handlermanager.addHandler(OnCellShowEvent.TYPE, handler);
	}
}