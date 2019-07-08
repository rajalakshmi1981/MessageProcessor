package com.message.objects;

import com.message.log.PriceAdjustment;
import com.message.log.SaleLog;
import com.message.parser.MessageParser;

public class Sale {

	public SaleLog log;
	private PriceAdjustment adjustPrice;
	private Product product;

	
	public Sale() {
		log = new SaleLog();
	}

	// Process notices and append product information to the relevant product in SaleLog.
	
	public boolean processNotification(String saleNotice) {


		MessageParser messageParser;

		messageParser = new MessageParser(saleNotice);

		String productType = messageParser.getProductType();

		if (productType.isEmpty()) {
			return false;
		}

		product = log.getProduct(productType);
		adjustPrice = new PriceAdjustment(product);

		product.setProductQuantity(messageParser.getProductQuantity());
		product.setTotalQuantity(messageParser.getProductQuantity());
		product.setProductPrice(messageParser.getProductPrice());
		product.setAdjustmentOperator(messageParser.getOperatorType());

		// Set the total value of the product.
		setProductTotalPrice();

		// Set the sale log reports
		log.setNormalReports(saleNotice);

		// Update the product with the new details
		log.updateProduct(product);

		return true;
	}

	// Perform adjustments if given
	// Also append the log for adjustments made.
	private void setProductTotalPrice() {
		double adjustedPrice;
		double productValue;

		if (!product.getAdjustmentOperator().isEmpty()) {
			adjustedPrice = adjustPrice.getAdjustedPrice();
			log.setAdjustmentReports(adjustPrice.adjustmentReport());
			product.setTotalPrice(adjustedPrice);
		} else {
			productValue = product.calculatePrice(product.getProductQuantity(), product.getProductPrice());
			product.appendTotalPrice(productValue);
		}
	}

}
