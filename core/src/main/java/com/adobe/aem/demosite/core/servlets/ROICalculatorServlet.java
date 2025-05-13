package com.adobe.aem.demosite.core.servlets;

import static com.adobe.aem.demosite.core.constants.ROICalculatorConstants.DEMOSITE;
import static com.adobe.aem.demosite.core.constants.ROICalculatorConstants.NT_UNSTRUCTURED;
import static com.adobe.aem.demosite.core.constants.ROICalculatorConstants.ROI;
import static com.adobe.aem.demosite.core.constants.ROICalculatorConstants.ROI_NODE_PATH;
import static com.adobe.aem.demosite.core.constants.ROICalculatorConstants.ROOT_NODE_PATH;
import static com.adobe.aem.demosite.core.constants.ROICalculatorConstants.VAR_NODE_PATH;

import java.io.IOException;
import java.util.UUID;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(resourceTypes = "sling/servlet/default", methods = HttpConstants.METHOD_POST, selectors = "roicalc", extensions = "html")
public class ROICalculatorServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1260496339316437209L;

	final Logger log = LoggerFactory.getLogger(ROICalculatorServlet.class);

	@Override
	protected void doPost(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
			throws ServletException, IOException {

		// Fetching input data from request
		final String userId = request.getParameter("userId");
		final String investment = request.getParameter("investment");
		final String revenue = request.getParameter("revenue");
		final String expenses = request.getParameter("expenses");

		if (userId == null || investment == null || revenue == null || expenses == null) {
			return;
		}
		// Calculating roi
		double roi = calculateROI(investment, revenue, expenses);
					
		final ResourceResolver resourceResolver = request.getResourceResolver();
		final Session session = resourceResolver.adaptTo(Session.class);

		try {
			final Node itemNode = getItemNode(session, userId);
			itemNode.setProperty("userId", userId);
			itemNode.setProperty("investment", investment);
			itemNode.setProperty("revenue", revenue);
			itemNode.setProperty("expenses", expenses);
			itemNode.setProperty("roi", roi);	
		} catch (final RepositoryException excepetion) {
			log.error(excepetion.getMessage());
		} finally {
			try {
				session.save();
				response.getWriter().write(String.valueOf(roi));
			} catch (RepositoryException repoExcepetion) {
				log.error(repoExcepetion.getMessage());
			}
		}

	}

	private Node getItemNode(final Session session, final String userId) {
		Node userRoiNode = createUserRoiNode(session, userId);
		UUID uuid = UUID.randomUUID();
		final Node itemNode = createChildNode(userRoiNode, uuid.toString());
		return itemNode;
	}

	private double calculateROI(final String investment, final String revenue, final String expenses) {
		double investmentValue = Double.parseDouble(investment);
		double revenueValue = Double.parseDouble(revenue);
		double expensesValue = Double.parseDouble(expenses);

		// Calculating roi
		double roi = ((revenueValue - expensesValue) / investmentValue) * 100;

		log.debug("roi : {} ", roi);
		return roi;

	}

	private Node getNode(final Session session, final String path) {
		try {
			return session.getNode(path);
		} catch (RepositoryException excepetion) {
			log.error(excepetion.getMessage());
		}
		return null;
	}

	private Node createChildNode(final Node node, final String name) {
		try {
			return node.addNode(name, NT_UNSTRUCTURED);
		} catch (final RepositoryException excepetion) {
			log.error(excepetion.getMessage());
		}
		return null;
	}

	
	/**
	 * This method creates node /var/demosite/roi/{userId} 
	 * 
	 * @param session
	 * @param userId
	 * @return Node
	 */
	private Node createUserRoiNode(final Session session, final String userId) {
		Node rootNode = getNode(session, ROOT_NODE_PATH);
		if (rootNode == null) {
			final Node varNode = getNode(session, VAR_NODE_PATH);
			rootNode = createChildNode(varNode, DEMOSITE);
		}

		Node roiNode = getNode(session, ROI_NODE_PATH);

		if (roiNode == null) {
			roiNode = createChildNode(rootNode, ROI);
		}

		final String userRoiPath = ROI_NODE_PATH + "/" + userId;
		Node userRoiNode = getNode(session, userRoiPath);

		if (userRoiNode == null) {
			userRoiNode = createChildNode(roiNode, userId);
		}
		return userRoiNode;

	}
}
