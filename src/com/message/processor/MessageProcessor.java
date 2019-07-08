package com.message.processor;

import java.io.BufferedReader;
import java.io.FileReader;

import com.message.objects.Sale;

public class MessageProcessor {
	public static void main(String[] args) {
		
		Sale sale = new Sale();

		// read Input Messages from test file
		try {
			String line;
			BufferedReader inputFile = new BufferedReader(new FileReader("testInput/inputmessages.txt"));
			while ((line = inputFile.readLine()) != null) {
				// process message
				sale.processNotification(line);

				// process the report
				sale.log.report();
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
}
