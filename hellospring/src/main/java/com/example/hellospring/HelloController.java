package com.example.hellospring;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.*;
import java.io.*;

@RestController
public class HelloController {

	@Value("${TEST}")
	private String configTest;
	
	@Value("${logging.config}")
	private String configRef;

	@RequestMapping("/")
	public String index() {
		try {
			URL fileUrl = new URL(configRef);
			BufferedReader in = new BufferedReader(
			new InputStreamReader(fileUrl.openStream()));

			StringBuilder configText = new StringBuilder();
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				configText.append(inputLine);
			in.close();

			return MessageFormat.format("Config Test Value: {0}, Config ref Value: {1}", configTest, configText);
		}
		catch (Exception ex) {
           return ex.toString();
		}
	}

}