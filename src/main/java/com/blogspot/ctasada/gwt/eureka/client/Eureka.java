package com.blogspot.ctasada.gwt.eureka.client;

import java.util.Date;

import com.blogspot.ctasada.gwt.eureka.client.resources.Resources;
import com.blogspot.ctasada.gwt.eureka.client.ui.SmallTimeBox;
import com.blogspot.ctasada.gwt.eureka.client.ui.TimeBox;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	@SuppressWarnings("deprecation")
	public void onModuleLoad() {
		Resources.INSTANCE.css().ensureInjected();
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.addStyleName("mainBackground");
		hPanel.setWidth("500px");
		hPanel.setHeight("300px");
		hPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		// Time Picker Examples
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.addStyleName("mainBackground");
		vPanel.setWidth("100%");
		vPanel.setHeight("100%");
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		vPanel.add(new Label("TimePicker Examples"));
		
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
		
		hPanel.add(vPanel);
		
		// Time Picker Small Examples
		VerticalPanel vSmallPanel = new VerticalPanel();
		vSmallPanel.addStyleName("mainBackground");
		vSmallPanel.setWidth("100%");
		vSmallPanel.setHeight("100%");
		vSmallPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vSmallPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		vSmallPanel.add(new Label("Small TimePicker Examples"));
		
		SmallTimeBox smallTimePicker = new SmallTimeBox(now);
		vSmallPanel.add(smallTimePicker);
		
		SmallTimeBox smallTimePicker_readonly = new SmallTimeBox(now);
		smallTimePicker_readonly.setReadOnly(true);
		vSmallPanel.add(smallTimePicker_readonly);
		
		SmallTimeBox smallTimePicker_ampm = new SmallTimeBox(now, true);
		vSmallPanel.add(smallTimePicker_ampm);

		SmallTimeBox smallTimePicker_ampm_readonly = new SmallTimeBox(now, true);
		smallTimePicker_ampm_readonly.setReadOnly(true);
		vSmallPanel.add(smallTimePicker_ampm_readonly);
		
		hPanel.add(vSmallPanel);
		
		RootPanel.get().add(hPanel);
	}
}
