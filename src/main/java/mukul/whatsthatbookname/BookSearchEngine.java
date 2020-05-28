package mukul.whatsthatbookname;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.internal.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class BookSearchEngine {
	
	private static String book_search_baseUrl="https://www.goodreads.com/search/index.xml";
	private static String key="km5rewhds8zLajBXGjUKA";
	
	public List<String[]> getBookNames(String query) throws Exception{
		List<String[]> bookNames=new ArrayList<String[]>();
		Map<String, String> parameters=new HashMap<String, String>();
		parameters.put("key", key);
		parameters.put("q", query);
		XMLParser xmlParser=new XMLParser(getResponse(parameters).asString());
		List<Map<String, Object>> bookList=xmlParser.getNodeMapList("best_book");
		bookList=bookList.size()>5?bookList.subList(0, 5):bookList;
		bookList.forEach(map->{
			bookNames.add(new String[] {map.get("id").toString(),map.get("title").toString()});
		});
		return bookNames;
	}
	
	private Response getResponse(Map<String, String> parameters) {
		RestAssured.baseURI =book_search_baseUrl;
		RequestSpecification request = RestAssured.given();
		parameters.forEach((k,v)->request.queryParam(k, v));
		return request.get();
	}
}
