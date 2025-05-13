package com.adobe.aem.demosite.core.servlets;

import static com.adobe.aem.demosite.core.constants.ROICalculatorConstants.ROI_NODE_PATH;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.adapter.AdapterManager;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "sling/servlet/default", methods = HttpConstants.METHOD_GET, selectors = "roihistory", extensions = "html")
public class ROIHistoryServlet extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1260496339316437209L;

	final Logger log = LoggerFactory.getLogger(ROIHistoryServlet.class);
	
	@Reference 
	AdapterManager adapterManager;

	@Override
	protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
			throws ServletException, IOException {

		// Fetching input data from request
		final String userId = request.getParameter("userId");


		if (userId == null) {
			return;
		}
					
		final ResourceResolver resourceResolver = request.getResourceResolver();
		final Session session = resourceResolver.adaptTo(Session.class);
		final Node userRoiNode = getNode(session, userId);

		if (userRoiNode == null) {
			return;
		}
		
		JSONArray historyArray = new JSONArray();
		try {
			NodeIterator childNodes = userRoiNode.getNodes();
			
			while(childNodes.hasNext()) {
				final Node childNode = childNodes.nextNode();
				final Value expenses = childNode.getProperty("expenses") != null ? childNode.getProperty("expenses").getValue() : null;
				final Value investment = childNode.getProperty("investment") != null ? childNode.getProperty("investment").getValue() : null;
				final Value revenue = childNode.getProperty("revenue") != null ? childNode.getProperty("revenue").getValue() : null;
				final Value userIdVal = childNode.getProperty("userId") != null ? childNode.getProperty("userId").getValue() : null;
				final Value roi = childNode.getProperty("roi") != null ? childNode.getProperty("roi").getValue() : null;
						
				JSONObject historyObj = new JSONObject();
				historyObj.append("userId", userIdVal);
				historyObj.append("expenses", expenses);
				historyObj.append("investment", investment);
				historyObj.append("revenue", revenue);
				historyObj.append("roi", roi);
				
				historyArray.put(historyObj);
			}
			
			response.getWriter().write(historyArray.toString());
			
		} catch (final RepositoryException repoExcepetion) {
			log.error(repoExcepetion.getMessage());
		} catch (final JSONException jsonExcepetion) {
			log.error(jsonExcepetion.getMessage());
		}
	}

	private Node getNode(final Session session, final String userId) {
		try {
			final String path = ROI_NODE_PATH + "/" + userId;
			return session.getNode(path);
		} catch (RepositoryException excepetion) {
			log.error(excepetion.getMessage());
		}
		return null;
	}

}
