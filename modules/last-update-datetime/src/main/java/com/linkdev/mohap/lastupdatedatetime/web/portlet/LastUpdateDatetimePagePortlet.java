package com.linkdev.mohap.lastupdatedatetime.web.portlet;

import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.linkdev.mohap.lastupdatedatetime.web.constants.LastUpdateDatetimePagePortletKeys;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Mahmoud.Abdullah
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Last Update Date and Time For The Page",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + LastUpdateDatetimePagePortletKeys.LASTUPDATEDATETIMEPAGE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class LastUpdateDatetimePagePortlet extends MVCPortlet {
	 @Override
	    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
	            throws IOException, PortletException {

	        HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(renderRequest);
	        ThemeDisplay themeDisplay = (ThemeDisplay) httpRequest.getAttribute(WebKeys.THEME_DISPLAY);
	        Layout layout = themeDisplay.getLayout();

	        Date lastModifiedDate = layout.getModifiedDate();
	        Locale userLocale = themeDisplay.getLocale();

	        // Format the date
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a", userLocale);
	        String formattedDate = dateFormat.format(lastModifiedDate);

	        // Custom logic for Arabic AM/PM translation
	        if (userLocale.getLanguage().equals(new Locale("ar").getLanguage())) {
	            formattedDate = formattedDate.replace("AM", "ص").replace("PM", "م");
	        }

	        renderRequest.setAttribute("lastModifiedDate", formattedDate);

	        super.doView(renderRequest, renderResponse);
	    }
}