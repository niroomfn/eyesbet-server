/*    */ package com.eyesbet.app.xml;
/*    */ 
/*    */ import javax.xml.parsers.SAXParser;
/*    */ import javax.xml.parsers.SAXParserFactory;
/*    */ import org.xml.sax.helpers.DefaultHandler;
/*    */ 
/*    */ public class FeedSaxParser
/*    */ {
/*    */   private SAXParser parser;
/*    */ 
/*    */   public FeedSaxParser(String url, DefaultHandler handler)
/*    */     throws Exception
/*    */   {
/* 15 */     SAXParserFactory factory = SAXParserFactory.newInstance();

/* 17 */     this.parser = factory.newSAXParser();
			
/* 18 */     this.parser.parse(url, handler);
/*    */   }
/*    */ }

