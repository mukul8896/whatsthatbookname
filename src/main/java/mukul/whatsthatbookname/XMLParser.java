package mukul.whatsthatbookname;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * XML parser to convert xml string into equivalet map or list
 * @author Mukul
 *
 */

public class XMLParser {
	
	Document doc;
	public XMLParser(String responseString) throws Exception {
		DocumentBuilder builder=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		this.doc=builder.parse(new InputSource(new StringReader(responseString)));
	}
	/**
	 * this method returs given XML node as Map<String,Object> object 
	 * @param nodeName
	 * @return
	 */
	public Map<String,Object> getNodeMap(String nodeName){
		Map<String, Object> map=new HashMap<String, Object>();
		NodeList nodeList=doc.getElementsByTagName(nodeName);
		
		for(int i=0;i<nodeList.getLength();i++) {
			if(nodeList.item(i).getNodeType()==Node.ELEMENT_NODE) {
				Element element=(Element) nodeList.item(i);
				NodeList childNodes=element.getChildNodes();
				
				if(!(childNodes.getLength()>1)) {
					map.put(element.getNodeName(), element.getChildNodes().item(0).getNodeValue());
					return map;
				}
				
				Set<String> nodeSet=new HashSet<String>();
				for(int j=0;j<childNodes.getLength();j++) {
					if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
						Element child=(Element)childNodes.item(j);
						nodeSet.add(child.getNodeName());
					}
				}
				
				for (String node : nodeSet) {
					if(element.getElementsByTagName(node).getLength()>1 && element.getElementsByTagName(node).item(1).getParentNode().getNodeName().equals(nodeName)) {
						map.put(node, getNodeMapList(node,element));
					}else {
						if(element.getElementsByTagName(node).item(0).getChildNodes().getLength()>1) {
							map.put(node, getNodeMap(node,element));
						}else {
							map.put(node, element.getElementsByTagName(node).item(0).getChildNodes().item(0)==null?"null":element.getElementsByTagName(node).item(0).getChildNodes().item(0).getNodeValue());
						}
					}
				}
			}
		}
		
		return map;
	}

	/**
	 * this method returs given XML node inside perticuler element as Map<String,Object> object 
	 * @param nodeName
	 * @return
	 */
	public Map<String,Object> getNodeMap(String nodeName, Element ele) {
		Map<String, Object> map=new HashMap<String, Object>();
		NodeList nodeList=ele.getElementsByTagName(nodeName);
		
		for(int i=0;i<nodeList.getLength();i++) {
			if(nodeList.item(i).getNodeType()==Node.ELEMENT_NODE) {
				Element element=(Element) nodeList.item(i);
				NodeList childNodes=element.getChildNodes();
				
				if(!(childNodes.getLength()>1)) {
					map.put(element.getNodeName(), element.getChildNodes().item(0).getNodeValue());
					return map;
				}
				
				Set<String> nodeSet=new HashSet<String>();
				for(int j=0;j<childNodes.getLength();j++) {
					if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
						Element child=(Element)childNodes.item(j);
						nodeSet.add(child.getNodeName());
					}
				}
				
				for (String node : nodeSet) {
					if(element.getElementsByTagName(node).getLength()>1 && element.getElementsByTagName(node).item(1).getParentNode().getNodeName().equals(nodeName)) {
						map.put(node, getNodeMapList(node,element));
					}else {
						if(element.getElementsByTagName(node).item(0).getChildNodes().getLength()>1) {
							map.put(node, getNodeMap(node,element));
						}else {
							map.put(node, element.getElementsByTagName(node).item(0).getChildNodes().item(0)==null?"null":element.getElementsByTagName(node).item(0).getChildNodes().item(0).getNodeValue());
						}
					}
				}
			}
		}
		
		return map;
	}

	/**
	 * this method returs given XML node inside perticuler element as List<Map<String,Object>> object 
	 * @param nodeName
	 * @return
	 */
	public List<Map<String,Object>> getNodeMapList(String nodeName, Element ele) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		NodeList nodeList=ele.getElementsByTagName(nodeName);
		
		for(int i=0;i<nodeList.getLength();i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			if(nodeList.item(i).getNodeType()==Node.ELEMENT_NODE) {
				Element element=(Element) nodeList.item(i);
				NodeList childNodes=element.getChildNodes();
				
				Set<String> nodeSet=new HashSet<String>();
				for(int j=0;j<childNodes.getLength();j++) {
					if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
						Element child=(Element)childNodes.item(j);
						nodeSet.add(child.getNodeName());
					}
				}
				
				for (String node : nodeSet) {
					if(element.getElementsByTagName(node).getLength()>1 && element.getElementsByTagName(node).item(1).getParentNode().getNodeName().equals(nodeName)) {
						map.put(node, getNodeMapList(node,element));
					}else {
						if(element.getElementsByTagName(node).item(0).getChildNodes().getLength()>1) {
							map.put(node, getNodeMap(node,element));
						}else {
							map.put(node, element.getElementsByTagName(node).item(0).getChildNodes().item(0)==null?"null":element.getElementsByTagName(node).item(0).getChildNodes().item(0).getNodeValue());
						}
					}
				}
			}
			list.add(map);
		}
		
		return list;
	}
	
	/**
	 * this method returs given XML node as List<Map<String,Object>> object 
	 * @param nodeName
	 * @return
	 */
	public List<Map<String,Object>> getNodeMapList(String nodeName) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		NodeList nodeList=doc.getElementsByTagName(nodeName);
		
		for(int i=0;i<nodeList.getLength();i++) {
			Map<String, Object> map=new HashMap<String, Object>();
			if(nodeList.item(i).getNodeType()==Node.ELEMENT_NODE) {
				Element element=(Element) nodeList.item(i);
				NodeList childNodes=element.getChildNodes();
				
				Set<String> nodeSet=new HashSet<String>();
				for(int j=0;j<childNodes.getLength();j++) {
					if(childNodes.item(j).getNodeType()==Node.ELEMENT_NODE) {
						Element child=(Element)childNodes.item(j);
						nodeSet.add(child.getNodeName());
					}
				}
				for (String node : nodeSet) {
					if(element.getElementsByTagName(node).getLength()>1 && element.getElementsByTagName(node).item(1).getParentNode().getNodeName().equals(nodeName)) {
						map.put(node, getNodeMapList(node,element));
					}else {
						if(element.getElementsByTagName(node).item(0).getChildNodes().getLength()>1) {
							map.put(node, getNodeMap(node,element));
						}else {
							map.put(node, element.getElementsByTagName(node).item(0).getChildNodes().item(0)==null?"null":element.getElementsByTagName(node).item(0).getChildNodes().item(0).getNodeValue());
						}
					}
				}
			}
			list.add(map);
		}
		return list;
	}
}
