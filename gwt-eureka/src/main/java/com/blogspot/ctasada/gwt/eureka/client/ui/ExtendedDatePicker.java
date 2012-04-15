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
 * This extended DatePicker uses a modified DatePicker source code. In this way
 * we can add some extra features.
 * 
 * <li>Indicate the first day of the week, independently of the i18 format <li>
 * Indicate a minimum valid date for the calendar <li>Indicate a maximum valid
 * date for the calendar
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

	/**
	 * Constructor.
	 * 
	 * @param firstDayofWeek
	 *            First Day of the Week (0 - Sunday, 1 - Monday, ...).
	 * 
	 * @param minDate
	 *            Minimum allowed date.
	 * @param maxDate
	 *            Maximum allowed date.
	 */
	public ExtendedDatePicker(int firstDayOfWeek, Date minDate, Date maxDate) {
		super(new DefaultMonthSelector(), new DefaultCalendarView(minDate,
				maxDate), new ExtendedCalendarModel(firstDayOfWeek));
	}

	public ExtendedDatePicker(MonthSelector monthSelector, CalendarView view,
			CalendarModel model) {
		super(monthSelector, view, model);
	}

	public static class ExtendedCalendarModel extends CalendarModel {
		public static final int MILLIS_IN_A_SECOND = 1000;
		public static final long MILLIS_IN_A_DAY = MILLIS_IN_A_SECOND * 60 * 60 * 24;

		private int firstDayofWeek = 1;

		public ExtendedCalendarModel() {
			super();
		}

		/**
		 * 
		 * @param firstDayofWeek
		 *            First Day of the Week (0 - Sunday, 1 - Monday, ...).
		 */
		public ExtendedCalendarModel(int firstDayofWeek) {
			this.firstDayofWeek = firstDayofWeek;
		}

		@Override
		public Date getCurrentFirstDayOfFirstWeek() {
			Date firstDisplayed = super.getCurrentFirstDayOfFirstWeek();
			Date d = new Date(firstDisplayed.getTime()
					+ (firstDayofWeek * MILLIS_IN_A_DAY));

			return d;
		}
	}
}
