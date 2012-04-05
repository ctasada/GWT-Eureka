package com.blogspot.ctasada.gwt.eureka.client.ui;

import java.util.Date;

import com.blogspot.ctasada.gwt.eureka.client.resources.Resources;
import com.google.gwt.resources.client.ImageResource;

/**
 * Same as the TimeBox, but uses different styles to show an smaller widget.
 * 
 * @author Carlos Tasada
 *
 */
public class SmallTimeBox extends TimeBox {

	private static final String STYLE_TIMEPICKER = Resources.INSTANCE.css().timePicker();
	private static final String STYLE_TIMEPICKER_ENTRY = Resources.INSTANCE.css().timePickerEntrySmall();
	private static final String STYLE_TIMEPICKER_READONLY = Resources.INSTANCE.css().timePickerReadOnlySmall();
	private static final ImageResource IMG_TIMEPICKER_AM = Resources.INSTANCE.timePickerAMSmall();
	private static final ImageResource IMG_TIMEPICKER_PM = Resources.INSTANCE.timePickerPMSmall();
	
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

	protected ImageResource getStyleTimePickerAM() {
		return IMG_TIMEPICKER_AM;
	}

	protected ImageResource getStyleTimePickerPM() {
		return IMG_TIMEPICKER_PM;
	}
}
