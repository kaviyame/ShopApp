package servlets.shopowner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import org.json.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import QueryAbstractFactory.CRUDFactoryProducer;
import QueryAbstractFactory.DML;
import database.Cart;
import database.ConnectDB;
import database.OrdersDB;
import model.CustomerCart;
import model.CustomerOrder;
import model.ShopProduct;

public class RemoveProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveProduct() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			CRUDFactoryProducer crudFactoryProducer = new CRUDFactoryProducer();

			DML dml = crudFactoryProducer.getFactory("dml").getDML("Remove");

			dml.doDMLOperation(request, response);

		} catch (NullPointerException e) {
			System.out.println(e);
		}

	}

}
