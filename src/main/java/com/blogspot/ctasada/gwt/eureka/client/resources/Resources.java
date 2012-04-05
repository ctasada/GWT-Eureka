package com.blogspot.ctasada.gwt.eureka.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
	Resources INSTANCE = GWT.create(Resources.class);

	@Source("com/blogspot/ctasada/gwt/eureka/client/resources/eureka.css")
	EurekaCssResource css();

	@Source("com/blogspot/ctasada/gwt/eureka/client/resources/images/buttons/button_AM.png")
	ImageResource timePickerAM();

	@Source("com/blogspot/ctasada/gwt/eureka/client/resources/images/buttons/button_PM.png")
	ImageResource timePickerPM();
	
	@Source("com/blogspot/ctasada/gwt/eureka/client/resources/images/buttons/button_AM_small.png")
	ImageResource timePickerAMSmall();

	@Source("com/blogspot/ctasada/gwt/eureka/client/resources/images/buttons/button_PM_small.png")
	ImageResource timePickerPMSmall();
	
	@Source("com/blogspot/ctasada/gwt/eureka/client/resources/images/gwtLogo.png")
	ImageResource gwtLogo();
}
