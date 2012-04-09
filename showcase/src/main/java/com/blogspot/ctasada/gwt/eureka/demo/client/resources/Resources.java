package com.blogspot.ctasada.gwt.eureka.demo.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle {
	Resources INSTANCE = GWT.create(Resources.class);
	
	@Source("com/blogspot/ctasada/gwt/eureka/demo/client/resources/images/gwtLogo.png")
	ImageResource gwtLogo();
}
