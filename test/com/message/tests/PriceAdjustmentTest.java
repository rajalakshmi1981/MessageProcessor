package com.message.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.message.log.PriceAdjustment;
import com.message.objects.Product;

public class PriceAdjustmentTest {
	
	@Test
	public void PriceAdjustmentAdd() {
		Product product = new Product("apples");
		product.setAdjustmentOperator("Add");
		product.setProductPrice(10.00);
		product.setTotalPrice(10.00);
		product.setTotalQuantity(1);
		
		PriceAdjustment priceAdjustment = new PriceAdjustment(product);
		assertEquals(20.00,priceAdjustment.getAdjustedPrice(),0.00);
	}
	
	@Test
	public void PriceAdjustmentSubtract() {
		Product product = new Product("apples");
		product.setAdjustmentOperator("Subtract");
		product.setProductPrice(10.00);
		product.setTotalPrice(20.00);
		product.setTotalQuantity(1);
		
		PriceAdjustment priceAdjustment = new PriceAdjustment(product);
		assertEquals(10.00,priceAdjustment.getAdjustedPrice(),0.00);
	}
	
	@Test
	public void PriceAdjustmentMultiply() {
		Product product = new Product("apples");
		product.setAdjustmentOperator("Multiply");
		product.setProductPrice(10.00);
		product.setTotalPrice(10.00);
		product.setTotalQuantity(1);
		
		PriceAdjustment priceAdjustment = new PriceAdjustment(product);
		assertEquals(120.00,priceAdjustment.getAdjustedPrice(),0.00);
	}
	
	@Test
	public void PriceAdjustmentInvalid() {
		Product product = new Product("apples");
		product.setAdjustmentOperator("Divide");
		product.setProductPrice(10.00);
		product.setTotalPrice(10.00);
		product.setTotalQuantity(1);
		
		PriceAdjustment priceAdjustment = new PriceAdjustment(product);
		assertEquals(0.00,priceAdjustment.getAdjustedPrice(),0.00);
	}

}
