package com.blogspot.ctasada.gwt.eureka.client.ui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DefaultDateTimeFormatInfo;
import com.google.gwt.user.datepicker.client.CalendarModel;
import com.google.gwt.user.datepicker.client.CalendarView;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.google.gwt.user.datepicker.client.DefaultCalendarView;
import com.google.gwt.user.datepicker.client.DefaultMonthSelector;
import com.google.gwt.user.datepicker.client.MonthSelector;

/**
 * This extended DatePicker uses a modified DatePicker source code. In this way we can add some extra features.
 * 
 * <li> Indicate the first day of the week, independently of the i18 format
 * <li> Indicate a minimum valid date for the calendar
 * <li> Indicate a maximum valid date for the calendar
 * 
 * @author Carlos Tasada
 */
public class ExtendedDatePicker extends DatePicker {

	private static int firstDayOfWeek;
	static {
		if (GWT.isClient()) {
			DefaultDateTimeFormatInfo timeConstants = new DefaultDateTimeFormatInfo();
			firstDayOfWeek = timeConstants.firstDayOfTheWeek();
		}
	}

	public ExtendedDatePicker() {
		this(firstDayOfWeek);
	}

	public ExtendedDatePicker(int firstDayOfWeek) {
		this(firstDayOfWeek, null, null);
	}
	
	public ExtendedDatePicker(int firstDayOfWeek, Date minDate, Date maxDate) {
		super(new DefaultMonthSelector(), new DefaultCalendarView(
				firstDayOfWeek, minDate, maxDate), new CalendarModel());
	}

	public ExtendedDatePicker(MonthSelector monthSelector, CalendarView view,
			CalendarModel model) {
		super(monthSelector, view, model);
	}
}
