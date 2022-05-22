package rockstar.web;

import java.io.IOException;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectHandler extends AbstractHandler {

	public static final String ROCKYROCKSTAR_ORG = "rockyrockstar.org";
	public static final String WWW_ROCKYROCKSTAR_ORG = "www." + ROCKYROCKSTAR_ORG;
	public static final String HTTP_HEADER_LOCATION = "Location";
	public static final String SCHEME_HTTPS = "https";
	public static final int PORT_HTTPS = 443;

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String scheme = baseRequest.getScheme();
//		String host = baseRequest.getServerName();
		int port = baseRequest.getServerPort();
		String path = baseRequest.getPathInContext();

		if ((scheme != null && !scheme.equalsIgnoreCase(SCHEME_HTTPS))
				|| (port != PORT_HTTPS)) {
			String targetPort = (port == PORT_HTTPS) ? "" : (":" + PORT_HTTPS);

			baseRequest.setHandled(true);
			response.setStatus(HttpStatus.MOVED_PERMANENTLY_301);
			response.addHeader(HTTP_HEADER_LOCATION, SCHEME_HTTPS + "://" + WWW_ROCKYROCKSTAR_ORG + targetPort + path);
		}

	}

}
