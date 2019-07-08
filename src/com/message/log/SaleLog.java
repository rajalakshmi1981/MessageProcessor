package com.message.log;

import java.util.ArrayList;
import java.util.HashMap;

import com.message.objects.Product;

public class SaleLog {


	private HashMap<String, Product> lineItems = new HashMap();

	private double totalSalesValue;

	private ArrayList normalReports;

	private ArrayList adjustmentReports;

	public SaleLog() {
		normalReports = new ArrayList();
		adjustmentReports = new ArrayList();
		totalSalesValue = 0.0;
	}

	public Product getProduct(String type) {
		return lineItems.getOrDefault(type, new Product(type));
	}

	public void updateProduct(Product product) {
		lineItems.put(product.getProductType(), product);
	}

	public ArrayList getNormalReports() {
		return normalReports;
	}

	public void setNormalReports(String normalReport) {
		normalReports.add(normalReport);
	}

	public ArrayList getAdjustmentReports() {
		return adjustmentReports;
	}

	public void setAdjustmentReports(String adjustmentReport) {
		adjustmentReports.add(adjustmentReport);
	}

	// return the total sales value
	public double getTotalSalesValue() {
		return totalSalesValue;
	}

	// Append any given value to the totalSalesValue field
	public void appendTotalSalesValue(double productTotalPrice) {
		totalSalesValue += productTotalPrice;
	}

	// Set total sales value with the given value
	public void setTotalSalesValue(double productTotalPrice) {
		totalSalesValue = productTotalPrice;
	}

	/*
	 * Report outputs sales information to system console on every 10th report
	 * and Displays in a table formatted structure and stops
	 * execution of the application after 50th message iteration.
	 */
	public void report() {

		// Report after 10th iteration and not at the beginning.
		if ((normalReports.size() % 10) == 0 && normalReports.size() != 0) {
			setTotalSalesValue(0.0);
			
			System.out.println("10 sales appended to log");
			System.out.println("*************** Sales Log Report *****************");
			System.out.println("|Product           |Quantity   |Value      |");
			lineItems.forEach((k, v) -> getFormmatedReport(k, v));
			System.out.println("-------------------------------------------");
			System.out.println(String.format("|%-30s|%-11.2f|", "Total Sales", getTotalSalesValue()));
			System.out.println("-------------------------------------------");
			System.out.println("End\n\n");
			
		}

		// Report and stop execution after 50th message
		if ((normalReports.size() % 50) == 0 && normalReports.size() != 0) {
			System.out.println(
					"Application has reached 50 messages and cannot process further messages. Following are the adjustment records made;\n");

			// Display all the adjustment reports so far recorded.
			getAdjustmentReports().forEach(System.out::println);
			System.exit(1);
		}
	}

	// Format the report with right padding structure. populates product details on
	// each line.
	public void getFormmatedReport(String type, Product product) {
		String lineItem = String.format("|%-18s|%-11d|%-11.2f|", product.getProductType(), product.getTotalQuantity(),
				product.getTotalPrice());
		appendTotalSalesValue(product.getTotalPrice());
		System.out.println(lineItem);
	}

}
