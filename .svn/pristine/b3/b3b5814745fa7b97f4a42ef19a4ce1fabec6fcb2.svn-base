package com.cardprototype.page;

import javax.servlet.http.HttpServletRequest;

import org.springframework.mobile.device.Device;

public abstract class AbstractController {

	protected boolean isDesktopUser(Device device) {
		return device == null || device.isNormal();
	}


	/**
	 * Helper method to create an absolute URL based on the current Request URI.
	 *
	 * Useful for creating URL variables to use in JSP / JS...
	 *
	 * @param request
	 * @param url
	 * @return
	 */
	public static String createUrl(HttpServletRequest request, String url) {
		return new StringBuffer(getBaseURL(request)).append(url).toString();
	}


	/**
	 * Retrieve the base URL of the deployed web application.
	 *
	 * @param request
	 * @return a String containing the url in the format:
	 *         &lt;scheme&gt;://&lt;servername
	 *         &gt;:&lt;port&gt;&lt;contextpath&gt;/
	 */
	protected static String getBaseURL(HttpServletRequest request) {
		StringBuffer baseUrl = new StringBuffer();
		baseUrl.append(request.getRequestURL().toString().replace(request.getServletPath(), ""));
		return baseUrl.toString();
	}

}
