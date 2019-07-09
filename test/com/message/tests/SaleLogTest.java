package com.message.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.message.objects.Product;
import com.message.objects.Sale;

public class SaleLogTest {
	
	@Test
	public void TestSaleLogProduct() {
		String saleNotice = "20 sales of apples at 10p each";
		Sale sale = new Sale();
		sale.processNotification(saleNotice);
		
		assertEquals(((Product)sale.log.getProduct("apples")).getProductType(), "apples");
		assertEquals(0.1,((Product)sale.log.getProduct("apples")).getProductPrice(), 0.00);
		assertEquals(2.00,((Product)sale.log.getProduct("apples")).getTotalPrice(), 0.00);
		assertEquals(20,((Product)sale.log.getProduct("apples")).getTotalQuantity());
		
		assertEquals(0.00,sale.log.getTotalSalesValue(),0.00);
	}
	
	@Test
	public void TestSaleLogProductInvalid() {
		String saleNotice = " sales of apples at 10p";
		Sale sale = new Sale();
		sale.processNotification(saleNotice);
		
		assertEquals(((Product)sale.log.getProduct("apples")).getProductType(), "apples");
		assertEquals(0.0,((Product)sale.log.getProduct("apples")).getProductPrice(), 0.00);
		assertEquals(0.0,((Product)sale.log.getProduct("apples")).getTotalPrice(), 0.00);
		assertEquals(0,((Product)sale.log.getProduct("apples")).getTotalQuantity());
		
		assertEquals(0.00,sale.log.getTotalSalesValue(),0.00);
	}
	
	@Test
	public void TestSaleLogSalesValue11Items() {
		String saleNotice = "20 sales of apples at 10p each";
		Sale sale = new Sale();
		
		for(int i=0; i<=10; i++) {
			sale.processNotification(saleNotice);
		}
		
		assertEquals(((Product)sale.log.getProduct("apples")).getProductType(), "apples");
		assertEquals(0.1,((Product)sale.log.getProduct("apples")).getProductPrice(), 0.00);
		assertEquals(22.00,((Product)sale.log.getProduct("apples")).getTotalPrice(), 0.00);
		assertEquals(220,((Product)sale.log.getProduct("apples")).getTotalQuantity());
		
		assertEquals(0.00,sale.log.getTotalSalesValue(),22.00);
	}
	
	@Test
	public void TestSaleLogLineItems10Items() {
		String saleNotice = "20 sales of apples at 10p each";
		Sale sale = new Sale();
		
		for(int i=0; i<10; i++) {
			sale.processNotification(saleNotice);
		}
		
		assertEquals(sale.log.getNormalReports().size(),10);
		
		assertEquals(((Product)sale.log.getProduct("apples")).getProductType(), "apples");
		assertEquals(0.1,((Product)sale.log.getProduct("apples")).getProductPrice(), 0.00);
		assertEquals(20.00,((Product)sale.log.getProduct("apples")).getTotalPrice(), 0.00);
		assertEquals(200,((Product)sale.log.getProduct("apples")).getTotalQuantity());
		
		assertEquals(0.00,sale.log.getTotalSalesValue(),22.00);
	}

}
