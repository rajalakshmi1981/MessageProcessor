package com.message.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.message.parser.MessageParser;

public class MessageParserTest {
	
	@Test
	public void TestParseMessageInvalid() {
		String message = "";
		MessageParser parser = new MessageParser(message);
		assertEquals(parser.getProductType(), "");
		assertEquals(parser.getProductQuantity(), 0);
	}
	
	@Test
	public void TestParseMessageType1() {
		String message = "Apple at 10p";
		MessageParser parser = new MessageParser(message);
		assertEquals(parser.getProductType(), "apples");
		assertEquals(parser.getProductQuantity(), 1);
	}
	
	@Test
	public void TestParseMessageType2() {
		String message = "20 sales of apples at 10p each";
		MessageParser parser = new MessageParser(message);
		assertEquals(parser.getProductType(), "apples");
		assertEquals(parser.getProductQuantity(), 20);
	}
	
	@Test
	public void TestParseMessageType3() {
		String message = "Add 20p Apple";
		MessageParser parser = new MessageParser(message);
		assertEquals(parser.getProductType(), "apples");
		assertEquals(parser.getProductQuantity(), 0);
	}
	
	@Test
	public void TestParseMessageType1Invalid() {
		String message = "Apple 10p";
		MessageParser parser = new MessageParser(message);
		assertEquals(parser.getProductType(), "");
		assertEquals(parser.getProductQuantity(), 0);
	}
	
	@Test
	public void TestParseMessageType2Invalid() {
		String message = "sales banana at 10p";
		MessageParser parser = new MessageParser(message);
		assertEquals(parser.getProductType(), "");
		assertEquals(parser.getProductQuantity(), 0);
	}
	
	@Test
	public void TestParseMessageType3Invalid() {
		String message = "Divide 10p Apple";
		MessageParser parser = new MessageParser(message);
		assertEquals(parser.getProductType(), "");
		assertEquals(parser.getProductQuantity(), 0);
	}
	

}
