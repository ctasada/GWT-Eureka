package com.blogspot.ctasada.gwt.eureka.client.ui;

import com.blogspot.ctasada.gwt.eureka.client.resources.Resources;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;

public class IOSButton extends Composite implements HasClickHandlers,
		ClickHandler {

	private final AbsolutePanel wrapper;
	private final PushButton pushButton;
	private final Label badge;

	private static final String STYLE_BADGES = Resources.INSTANCE.css().badges();
	private static final String STYLE_IOSBUTTON = Resources.INSTANCE.css().iOSButton();
	
	public IOSButton(Image buttonIcon) {
		wrapper = new AbsolutePanel();
		badge = new Label();
		badge.setStyleName(STYLE_BADGES);
		badge.setVisible(false);

		pushButton = new PushButton(buttonIcon);
		pushButton.setStyleName(STYLE_IOSBUTTON);

		wrapper.add(pushButton, 0, 20);
		wrapper.add(badge, 40, 10);
		wrapper.setWidth("75px");
		wrapper.setHeight("80px");

		this.addClickHandler(this);

		initWidget(wrapper);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	@Override
	public void onClick(ClickEvent event) {
		// toggleButton.fireEvent(event);
	}

	public void setEnabled(boolean enabled) {
		pushButton.setEnabled(enabled);
	}

	public void setBadge(int total) {
		if (total > 0) {
			badge.setVisible(true);
			badge.setText(String.valueOf(total));
		} else {
			badge.setVisible(false);
		}
	}
}