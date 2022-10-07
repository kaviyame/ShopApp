package servlets.shopowner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import model.ShopProduct;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import QueryAbstractFactory.CRUDFactoryProducer;
import QueryAbstractFactory.DML;
import QueryAbstractFactory.DQL;
import database.OrdersDB;

public class MyProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyProducts() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			CRUDFactoryProducer crudFactoryProducer = new CRUDFactoryProducer();

			DQL dql = crudFactoryProducer.getFactory("dql").getDQL("Show");

			dql.doDQLOperation(request, response);

		} catch (NullPointerException e) {
			System.out.println(e);
		}

	}

}
