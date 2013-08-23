package com.blogspot.ctasada.gwt.eureka.client.ui;

import java.util.Date;

import com.blogspot.ctasada.gwt.eureka.theme.standard.client.ResourcesBundle;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Input box that allows to type time values.
 * 
 * The box allows to type hours and minutes, both with am/pm format or 24h. As
 * an extra it allows to move block of 15 minutes, quite useful for scheduling
 * systems.
 * 
 * @author Carlos Tasada
 * 
 */
@SuppressWarnings("deprecation")
public class TimeBox extends Composite implements
		HasValueChangeHandlers<Long>, HasValue<Long>, HasBlurHandlers,
		Focusable {

	private static final String STYLE_TIMEPICKER = "timePicker";
	private static final String STYLE_TIMEPICKER_ENTRY = "timePickerEntry";
	private static final String STYLE_TIMEPICKER_READONLY = "timePickerReadOnly";
	private static final ImageResource IMG_TIMEPICKER_AM = ResourcesBundle.INSTANCE.timePickerAM();
	private static final ImageResource IMG_TIMEPICKER_PM = ResourcesBundle.INSTANCE.timePickerPM();
	
	/**
	 * Defines the possible precisions when comparing hours.
	 */
	public static enum TIME_PRECISION {
		MINUTE, QUARTER_HOUR
	};
	
	/**
	 * Defines the number of milliseconds in a second.
	 */
	private static final long ONE_SECOND = 1000;
	/**
	 * Defines the number of milliseconds in a minute.
	 */
	private static final long ONE_MINUTE = ONE_SECOND * 60;
	/**
	 * Defines the number of milliseconds in an hour.
	 */
	private static final long ONE_HOUR = ONE_MINUTE * 60;
	
	private final ValueTextBox hoursBox;
	private final ValueTextBox minutesBox;
	private final int minutesSteps;
	private boolean readOnly = false;
	private final VerticalPanel container;
	private final HorizontalPanel timePanel;
	private final Date timeValue;
	private final ToggleButton ampmButton;
	private final boolean useAMPM;
	
	/**
	 * Default Constructor.
	 * @param time Hour that will show the widget
	 */
	public TimeBox(Date time) {
		this(time, false);
	}

	/**
	 * Default Constructor.
	 * @param time Hour that will show the widget
	 * @param useAMPM Indicates if uses am/pm format
	 */
	public TimeBox(Date time, boolean useAMPM) {
		this(time, TIME_PRECISION.MINUTE, useAMPM);
	}
	
	/**
	 * Default Constructor.
	 * @param time Hour that will show the widget
	 * @param precision Indicates if widget precission
	 * @param useAMPM Indicates if uses am/pm format
	 */
	public TimeBox(Date time, TIME_PRECISION precision, boolean useAMPM) {
		timeValue = time;
		this.useAMPM = useAMPM;
		
		container = new VerticalPanel();

		int hour = time.getHours();
		int minutes = time.getMinutes();

		if (precision == TIME_PRECISION.QUARTER_HOUR) {
			minutesSteps = 15;
		} else {
			minutesSteps = 1;
		}

		hoursBox = new ValueTextBox(hour, 0, 24);
		hoursBox.setMinDigits(2);
		hoursBox.setSteps(0);

		minutesBox = new ValueTextBox(minutes, 0, 59);
		minutesBox.setMinDigits(2);
		minutesBox.setSteps(0);

		Label separator = new Label(":");

		hoursBox.setWidth("30px");
		minutesBox.setWidth("30px");
		
		hoursBox.setStyleName(getStyleTimePickerEntry());
		separator.setStyleName(getStyleTimePickerEntry());
		minutesBox.setStyleName(getStyleTimePickerEntry());

		timePanel = new HorizontalPanel();
		timePanel.setStyleName(getStyleTimePicker());

		timePanel.add(hoursBox);
		timePanel.add(separator);
		timePanel.add(minutesBox);

		ampmButton = new ToggleButton(getStyleTimePickerAM(), getStyleTimePickerPM());
		if (useAMPM) {
			timePanel.add(ampmButton);
			ampmButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (timeValue.getHours() >= 12) {
						timeValue.setTime(timeValue.getTime() - (12 * ONE_HOUR));
					} else {
						timeValue.setTime(timeValue.getTime() + (12 * ONE_HOUR));
					}
					fireValueChange();
				}
			});
		}

		setReadOnly(readOnly);
		
		timePanel.setCellVerticalAlignment(separator,
				HasVerticalAlignment.ALIGN_MIDDLE);

		container.add(timePanel);

		initWidget(container);

		hoursBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				fireValueChange();
			}
		});
		hoursBox.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (isReadOnly()) {
					return;
				}

				int keyCode = event.getNativeEvent().getKeyCode();

				int hour = Integer.parseInt(hoursBox.getValue());
				int oldHour = timeValue.getHours();
				boolean stepKey = false;

				switch (keyCode) {
				case KeyCodes.KEY_UP:
					stepKey = true;
					timeValue.setHours(timeValue.getHours() + 1);
					break;
				case KeyCodes.KEY_DOWN:
					stepKey = true;
					timeValue.setHours(timeValue.getHours() - 1);
					break;
				}
				updateTimeValue(stepKey, oldHour, hour);
			}
		});
		hoursBox.addMouseWheelHandler(new MouseWheelHandler() {
			public void onMouseWheel(MouseWheelEvent event) {
				int hour = Integer.parseInt(hoursBox.getValue());
				int oldHour = timeValue.getHours();
				if (event.isNorth()) {
					timeValue.setHours(timeValue.getHours() + 1);
				} else {
					timeValue.setHours(timeValue.getHours() - 1);
				}
				updateTimeValue(true, oldHour, hour);
			}
		});
		hoursBox.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				fireBlurEvent();
			}
		});
		
		minutesBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				fireValueChange();
			}
		});
		minutesBox.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (isReadOnly()) {
					return;
				}

				int keyCode = event.getNativeEvent().getKeyCode();

				int minutes = Integer.parseInt(minutesBox.getValue());
				boolean stepKey = false;

				int oldMinutes = timeValue.getMinutes();

				switch (keyCode) {
				case KeyCodes.KEY_UP:
					stepKey = true;
					increaseValue();
					return;
				case KeyCodes.KEY_DOWN:
					stepKey = true;
					decreaseValue();
					return;
				}

				if (!stepKey && (oldMinutes != minutes)) {
					Date tmpValue = (Date)timeValue.clone();
					
					int hour = Integer.parseInt(hoursBox.getValue());

					timeValue.setHours(hour);
					timeValue.setMinutes(minutes);
					
					if (isUseAMPM()) {
						// In AM/PM if was PM keep it. This case only happens when typing the number
						if (tmpValue.getHours() >= 12) {
							timeValue.setTime(timeValue.getTime() + (12 * ONE_HOUR));	
						}
					}

					fireValueChange();
				}
			}
		});
		minutesBox.addMouseWheelHandler(new MouseWheelHandler() {
			public void onMouseWheel(MouseWheelEvent event) {
				if (event.isNorth()) {
					increaseValue();
				} else {
					decreaseValue();
				}
			}
		});
		minutesBox.addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				fireBlurEvent();
			}
		});
		
		// Trying to force that the whole value is selected when receiving the focus
		hoursBox.addFocusHandler(new FocusHandler() {
			@Override
			public void onFocus(FocusEvent event) {
			    hoursBox.setFocus(true);
			    hoursBox.selectAll();
			}
		});
		minutesBox.addFocusHandler(new FocusHandler() {
			@Override
			public void onFocus(FocusEvent event) {
			    minutesBox.setFocus(true);
				minutesBox.selectAll();
			}
		});
		// Making sure the cursor is always in the last position so the editing works as expected
		hoursBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				hoursBox.setCursorPos(hoursBox.getText().length());
			}
		});
		minutesBox.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				minutesBox.setCursorPos(minutesBox.getText().length());
			}
		});
	}

	private void updateTimeValue(boolean stepKey, int oldHour, int hour) {
		if (stepKey || (oldHour != hour)) {
			if (stepKey) {
				hour = timeValue.getHours();
			}
			if (hour > 12 && isUseAMPM()) {
				hour -= 12;
			}

			hoursBox.setValue(String.valueOf(hour), false);
			int minutes = Integer.parseInt(minutesBox.getValue());

			if (!stepKey) {
				Date tmpValue = (Date)timeValue.clone();
				
				timeValue.setHours(hour);
				timeValue.setMinutes(minutes);
				
				if (isUseAMPM()) {
					// In AM/PM if was PM keep it. This case only happens when typing the number
					if (tmpValue.getHours() >= 12) {
						timeValue.setTime(timeValue.getTime() + (12 * ONE_HOUR));	
					}
				}
			}

			fireValueChange();
			
			updateAMPM();
		}
	}
	
	private boolean isUseAMPM() {
		return useAMPM;
	}
	
	private void updateAMPM() {
		if (isUseAMPM()) {
			if (timeValue.getHours() >= 12) {
				ampmButton.setDown(true);
			} else {
				ampmButton.setDown(false);
			}
		}
	}

	public void increaseValue() {
		increaseValue(true);
	}

	public void increaseValue(boolean fireEvents) {
		int hours = timeValue.getHours();
		int oldHours = hours;

		int minutes = timeValue.getMinutes();
		int oldMinutes = minutes;

		if (minutesSteps == 15) {
			if (minutes < 15) {
				minutes = 15;
			} else if (minutes < 30) {
				minutes = 30;
			} else if (minutes < 45) {
				minutes = 45;
			} else {
				minutes = 0;
				hours++;
				if (hours > 23) {
					hours = 0;
				}
			}
		} else {
			minutes++;
		}

		if ((oldMinutes != minutes) || (oldHours != hours)) {
			timeValue.setHours(hours);
			timeValue.setMinutes(minutes);
			setValue(timeValue.getTime(), fireEvents);
		}
	}

	public void decreaseValue() {
		decreaseValue(true);
	}

	public void decreaseValue(boolean fireEvents) {
		int hours = timeValue.getHours();
		int oldHours = hours;

		int minutes = timeValue.getMinutes();
		int oldMinutes = minutes;

		if (minutesSteps == 15) {
			if (minutes >= 45) {
				minutes = 30;
			} else if (minutes >= 30) {
				minutes = 15;
			} else if (minutes >= 15) {
				minutes = 0;
			} else {
				minutes = 45;
				hours--;
				if (hours < 0) {
					hours = 23;
				}
			}
		} else {
			minutes--;
		}

		if ((oldMinutes != minutes) || (oldHours != hours)) {
			timeValue.setHours(hours);
			timeValue.setMinutes(minutes);
			setValue(timeValue.getTime(), fireEvents);
		}
	}

	public long getTime() {
		return timeValue.getTime();
	}

	private void fireValueChange() {
		ValueChangeEvent.fire(this, getValue());
	}

	private void fireBlurEvent() {
		NativeEvent event = Document.get().createBlurEvent();
		BlurEvent.fireNativeEvent(event, this);
	}

	@Override
	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Long> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	@Override
	public HandlerRegistration addBlurHandler(BlurHandler handler) {
		return addHandler(handler, BlurEvent.getType());
	}

	@Override
	public Long getValue() {
		return timeValue.getTime();
	}

	public String getValueAsString() {		
		DateTimeFormat fmt;
		if (isUseAMPM()) {
			fmt = DateTimeFormat.getFormat("h:mm a");
		} else {
			fmt = DateTimeFormat.getFormat("h:mm");
		}

		return fmt.format(timeValue);
	}

	@Override
	public void setValue(Long value) {
		setValue(value, false);
	}

	@Override
	public void setValue(Long time, boolean fireEvents) {

		Date tmpValue = new Date(time);
		
		int hour = tmpValue.getHours();
		int minutes = tmpValue.getMinutes();

		if (hour > 12 && isUseAMPM()) {
			hour -= 12;
		}
		
		hoursBox.setValue(String.valueOf(hour), false);
		minutesBox.setValue(String.valueOf(minutes), false);

		if (fireEvents) {
			ValueChangeEvent.fireIfNotEqual(this, timeValue.getTime(), time);
		}

		timeValue.setTime(time);

		updateAMPM();
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;

		hoursBox.setReadOnly(readOnly);
		minutesBox.setReadOnly(readOnly);
		ampmButton.setEnabled(!readOnly);
		
		if (readOnly) {
			timePanel.setStyleName(getStyleTimePickerReadOnly());
		}
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	@Override
	public int getTabIndex() {
		return hoursBox.getTabIndex();
	}

	@Override
	public void setAccessKey(char key) {
		hoursBox.setAccessKey(key);
	}

	@Override
	public void setFocus(boolean focused) {
		hoursBox.setFocus(focused);
		hoursBox.selectAll();
	}

	@Override
	public void setTabIndex(int index) {
		hoursBox.setTabIndex(index);
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
