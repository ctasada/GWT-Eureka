package com.blogspot.ctasada.gwt.eureka.demo.client;

import java.util.Date;

import com.blogspot.ctasada.gwt.eureka.client.ui.ExtendedDatePicker;
import com.blogspot.ctasada.gwt.eureka.client.ui.IOSButton;
import com.blogspot.ctasada.gwt.eureka.client.ui.SmallTimeBox;
import com.blogspot.ctasada.gwt.eureka.client.ui.TimeBox;
import com.blogspot.ctasada.gwt.eureka.demo.client.resources.Resources;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Eureka implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		DockPanel dock = new DockPanel();
		dock.addStyleName("mainBackground");
		dock.setWidth("700px");
		dock.setHeight("300px");
		dock.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		dock.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);

		dock.add(createHeader(), DockPanel.NORTH);

		// Time Picker Examples
		dock.add(createTimePicker(), DockPanel.WEST);
		// Time Picker Small Examples
		dock.add(createSmallTimePicker(), DockPanel.WEST);
		// iOSButton Examples
		dock.add(createIOSButton(), DockPanel.WEST);
		// DatePicker Examples
		dock.add(createDatePicker(), DockPanel.WEST);

		RootPanel.get().add(dock);
	}

	private HorizontalPanel createHeader() {
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setWidth("100%");
		hPanel.setHeight("80px");
		hPanel.setStyleName("headerBackground");

		Label title = new Label("GWT-Eureka Library");
		title.setStyleName("headerFont");
		Label subtitle = new Label("Widgets Demo");
		subtitle.setStyleName("subheaderFont");

		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(title);
		vPanel.add(subtitle);

		Anchor link = new Anchor("GWT-Eureka Homepage",
				"https://github.com/ctasada/GWT-Eureka");

		hPanel.add(vPanel);
		hPanel.add(link);
		hPanel.setSpacing(20);

		return hPanel;
	}

	private VerticalPanel getNewVerticalPanel(String label) {
		VerticalPanel vPanel = new VerticalPanel();
		// vPanel.addStyleName("mainBackground");
		vPanel.setWidth("100%");
		vPanel.setHeight("100%");
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		vPanel.setSpacing(12);

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

		IOSButton button = new IOSButton(
				new Image(Resources.INSTANCE.gwtLogo()));
		button.setBadge(11);
		vPanel.add(button);

		return vPanel;
	}

	private VerticalPanel createDatePicker() {
		VerticalPanel vPanel = getNewVerticalPanel("DatePicker Examples");
		final Label text = new Label();
		
		Date today = new Date();
		Date nextWeek = new Date();
		nextWeek.setDate(today.getDate() + 7);

		ExtendedDatePicker datePicker = new ExtendedDatePicker(0, today,
				nextWeek);
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM).format(date);
				text.setText(dateString);
			}

		});
		vPanel.add(datePicker);
		vPanel.add(text);

		datePicker.setValue(new Date());
		
		return vPanel;
	}

}
