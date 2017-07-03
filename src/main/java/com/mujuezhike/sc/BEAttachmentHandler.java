package com.mujuezhike.sc;

import com.gargoylesoftware.htmlunit.html.HtmlAttributeChangeEvent;
import com.gargoylesoftware.htmlunit.html.HtmlAttributeChangeListener;

public class BEAttachmentHandler implements HtmlAttributeChangeListener{

	@Override
	public void attributeAdded(HtmlAttributeChangeEvent event) {
		System.out.println(event.toString());
	}

	@Override
	public void attributeRemoved(HtmlAttributeChangeEvent event) {
		System.out.println(event.toString());
	}

	@Override
	public void attributeReplaced(HtmlAttributeChangeEvent event) {
		System.out.println(event.toString());
	}

}
