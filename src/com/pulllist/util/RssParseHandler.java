package com.pulllist.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.pulllist.data.RssItem;

/**
 * SAX tag handler
 * 
 * @author ITCuties
 *
 */
public class RssParseHandler extends DefaultHandler {

	private List<RssItem> rssItems;
	
	// Used to reference item while parsing
	private RssItem currentItem;
	
	// Parsing title indicator
	private boolean parsingTitle;
	// Parsing link indicator
	private boolean parsingLink;
	private boolean parsingImg; //Still needs to be done
	private boolean parsingCategory;
	private boolean parsingPubDate;
	private boolean parsingDescription;
	
	public RssParseHandler() {
		rssItems = new ArrayList<RssItem>();
	}
	
	public List<RssItem> getItems() {
		return rssItems;
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("item".equals(qName)) {
			currentItem = new RssItem();
		} else if ("title".equals(qName)) {
			parsingTitle = true;
		} else if ("feedburner:origLink".equals(qName)) {
			parsingLink = true;
		} else if("category".equals(qName)){
			parsingCategory = true;
		} else if("description".equals(qName)){
			parsingDescription = true;
		} else if("pubDate".equals(qName)){
			parsingPubDate = true;
		} 
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("item".equals(qName)) {
			rssItems.add(currentItem);
			currentItem = null;
		} else if ("title".equals(qName)) {
			parsingTitle = false;
		} else if ("feedburner:origLink".equals(qName)) {
			parsingLink = false;
		} else if("category".equals(qName)){
			parsingCategory = false;
		} else if("description".equals(qName)){
			parsingDescription = false;
		} else if("pubDate".equals(qName)){
			parsingPubDate = false;
		} 
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (parsingTitle) {
			if (currentItem != null)
				currentItem.setTitle(new String(ch, start, length));
		} else if (parsingLink) {
			if (currentItem != null) {
				currentItem.setLink(new String(ch, start, length));
				parsingLink = false;
			}
		} else if(parsingCategory){
			if(currentItem != null){
				currentItem.setCategory(new String(ch, start, length));
			}
		} else if(parsingPubDate){
			if(currentItem != null){
				currentItem.setPubDate(new String(ch, start, length));
			}
		} else if(parsingDescription){
			if(currentItem != null){
				currentItem.setDescription(new String(ch, start, length));
			}
		}
	}
	
}
