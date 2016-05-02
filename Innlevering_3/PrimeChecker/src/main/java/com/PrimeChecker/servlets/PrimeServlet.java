package com.PrimeChecker.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.PrimeChecker.logic.Prime;

@WebServlet("/primeServlet")
public class PrimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(PrimeServlet.class);
	Prime optimus = new Prime();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
								throws ServletException, IOException {
		
		try {
			optimus.checkIfPrime(req.getParameter("nrToCheck"));
			if(optimus.getIsPrime() == true){
				String tmp =  output(req, "");
				loggingInfo(req);
				reDirect(req, resp, tmp);
			} else {
				String tmp = output(req, "not ");
				loggingInfo(req);
				reDirect(req, resp, tmp);
			}
		} catch (NumberFormatException e) {
			logger.error("ERROR: " + e);
			String tmp =  req.getParameter("nrToCheck") + " - dude, that's not a number";
			reDirect(req, resp, tmp);
		}
	}
	
	private void loggingInfo(HttpServletRequest req){
		logger.info("Request for number " + req.getParameter("nrToCheck"));
	}
	
	private void reDirect(HttpServletRequest req, HttpServletResponse resp, String tmp){
		try {
			req.setAttribute("nr", tmp);
			req.getRequestDispatcher("/result.jsp").forward(req, resp);
		} catch (ServletException e) {
			logger.error("Error with servlet: " + e);
		} catch (IOException e) {
			logger.error("Error with IO: " + e);
		}
	}
	
	private String output(HttpServletRequest req, String answer){
		return "Number " + req.getParameter("nrToCheck") + " is " + answer + "a prime";
	}
}
