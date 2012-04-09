package com.blogspot.ctasada.gwt.eureka.client.ui;

import java.util.Date;

import com.blogspot.ctasada.gwt.eureka.theme.standard.client.ResourcesBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Image;

/**
 * Same as the TimeBox, but uses different styles to show an smaller widget.
 * 
 * @author Carlos Tasada
 *
 */
public class SmallTimeBox extends TimeBox {

	private static final String STYLE_TIMEPICKER = "timePicker";
	private static final String STYLE_TIMEPICKER_ENTRY = "timePickerEntrySmall";
	private static final String STYLE_TIMEPICKER_READONLY = "timePickerReadOnlySmall";
	private static final ImageResource IMG_TIMEPICKER_AM = ResourcesBundle.INSTANCE.timePickerAMSmall();
	private static final ImageResource IMG_TIMEPICKER_PM = ResourcesBundle.INSTANCE.timePickerPMSmall();
	
	public SmallTimeBox(Date time) {
		this(time, false);
	}

	public SmallTimeBox(Date time, boolean useAMPM) {
		this(time, TIME_PRECISION.QUARTER_HOUR, useAMPM);
	}
	
	public SmallTimeBox(Date time, TIME_PRECISION precision, boolean useAMPM) {
		super(time, precision, useAMPM);
	}

	protected String getStyleTimePickerEntry() {
		return STYLE_TIMEPICKER_ENTRY;
	}

	protected String getStyleTimePicker() {
		return STYLE_TIMEPICKER;
	}

	protected String getStyleTimePickerReadOnly() {
		return STYLE_TIMEPICKER_READONLY;
	}

	protected Image getStyleTimePickerAM() {
		return new Image(IMG_TIMEPICKER_AM);
	}

	protected Image getStyleTimePickerPM() {
		return new Image(IMG_TIMEPICKER_PM);
	}
}
