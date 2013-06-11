 package com.eyesbet.app.xml;
 
 import javax.xml.parsers.SAXParser;
 import javax.xml.parsers.SAXParserFactory;
 import org.xml.sax.helpers.DefaultHandler;
 
 public class FeedSaxParser
 {
   private SAXParser parser;
 
   public FeedSaxParser(String url, DefaultHandler handler)
     throws Exception
   {
     SAXParserFactory factory = SAXParserFactory.newInstance();

     this.parser = factory.newSAXParser();
			
     this.parser.parse(url, handler);
   }
 }

