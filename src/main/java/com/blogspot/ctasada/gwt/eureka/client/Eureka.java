package com.blogspot.ctasada.gwt.eureka.client;

import java.util.Date;

import com.blogspot.ctasada.gwt.eureka.client.resources.Resources;
import com.blogspot.ctasada.gwt.eureka.client.ui.IOSButton;
import com.blogspot.ctasada.gwt.eureka.client.ui.SmallTimeBox;
import com.blogspot.ctasada.gwt.eureka.client.ui.TimeBox;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Eureka implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Resources.INSTANCE.css().ensureInjected();
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.addStyleName("mainBackground");
		hPanel.setWidth("500px");
		hPanel.setHeight("300px");
		hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		// Time Picker Examples
		VerticalPanel vTimePicker = createTimePicker();
		hPanel.add(vTimePicker);
		
		// Time Picker Small Examples
		VerticalPanel vSmallTimePicker = createSmallTimePicker();
		hPanel.add(vSmallTimePicker);

		// iOSButton Examples
		VerticalPanel vIOSButton = createIOSButton();
		hPanel.add(vIOSButton);
		
		RootPanel.get().add(hPanel);
	}
	
	private VerticalPanel getNewVerticalPanel(String label) {
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.addStyleName("mainBackground");
		vPanel.setWidth("100%");
		vPanel.setHeight("100%");
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

		vPanel.add(new Label(label));

		return vPanel;
	}
	
	@SuppressWarnings("deprecation")
	private VerticalPanel createTimePicker() {
		VerticalPanel vPanel = getNewVerticalPanel("TimePicker Examples");
		
		Date now = new Date();
		now.setHours(12);
		now.setMinutes(0);
		
		TimeBox timePicker = new TimeBox(now);
		vPanel.add(timePicker);
		
		TimeBox timePicker_readonly = new TimeBox(now);
		timePicker_readonly.setReadOnly(true);
		vPanel.add(timePicker_readonly);
		
		TimeBox timePicker_ampm = new TimeBox(now, true);
		vPanel.add(timePicker_ampm);

		TimeBox timePicker_ampm_readonly = new TimeBox(now, true);
		timePicker_ampm_readonly.setReadOnly(true);
		vPanel.add(timePicker_ampm_readonly);
		
		return vPanel;
	}
	
	@SuppressWarnings("deprecation")
	private VerticalPanel createSmallTimePicker() {
		VerticalPanel vPanel = getNewVerticalPanel("Small TimePicker Examples");
		
		Date now = new Date();
		now.setHours(12);
		now.setMinutes(0);
		
		SmallTimeBox smallTimePicker = new SmallTimeBox(now);
		vPanel.add(smallTimePicker);
		
		SmallTimeBox smallTimePicker_readonly = new SmallTimeBox(now);
		smallTimePicker_readonly.setReadOnly(true);
		vPanel.add(smallTimePicker_readonly);
		
		SmallTimeBox smallTimePicker_ampm = new SmallTimeBox(now, true);
		vPanel.add(smallTimePicker_ampm);

		SmallTimeBox smallTimePicker_ampm_readonly = new SmallTimeBox(now, true);
		smallTimePicker_ampm_readonly.setReadOnly(true);
		vPanel.add(smallTimePicker_ampm_readonly);
		
		return vPanel;
	}
	
	private VerticalPanel createIOSButton() {
		VerticalPanel vPanel = getNewVerticalPanel("iOSButton Examples");
		
		IOSButton button = new IOSButton(new Image(Resources.INSTANCE.gwtLogo()));
		button.setBadge(11);
		vPanel.add(button);
		
		return vPanel;
	}	
}
