package test;

import java.io.File;   
import java.util.Iterator;   
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;   
import org.dom4j.DocumentException;   
import org.dom4j.Element;   
import org.dom4j.io.SAXReader;   
   

public class XmlHelper {   
	static String fileName =  System.getProperty("user.dir")+"\\src\\test\\webElement.xml";
	static File xmlFile=new File(fileName);   
	static SAXReader saxReader = new SAXReader();
	private static String value; 

	public static String parserXml(String path,int attrIndex) throws DocumentException {   
	/*
	 * @path
	 * like:/test/login/input/userName
	 * or:/login/input/userName
	 */
		value = null;
		if(!(path.trim().substring(0, 5).equals("/test"))){
			path = "/test" + path;
		}
		Document document = saxReader.read(xmlFile); 
		Element testNode = document.getRootElement();  //get "test" node
		for(Iterator<?> i = testNode.elementIterator(); i.hasNext();){   
			Element caseNode = (Element) i.next();//get "login" node
			for(Iterator<?> j = caseNode.elementIterator(); j.hasNext();){   
				Element objectNode = (Element) j.next();   //get "object" node,such as "input" or "assert" node
				for(Iterator<?> k = objectNode.elementIterator(); k.hasNext();){   
					Element lastNode = (Element) k.next(); //get "last" node,such as "userName" or "passWord" node
					if(lastNode.getPath().trim().equals(path)){
						List<Attribute> listAttr = lastNode.attributes();
						Attribute attr = listAttr.get(attrIndex);//get lastNode's attribute
						value = attr.getValue();
						break;
					}
				}
			}
		}
		return value;
	}
	
	public static String parserXml(String path,String attrName) throws DocumentException {   
	/*
	 * @path
	 * like:/test/login/input/userName
	 * or:/login/input/userName
	 */
		value = null;
		if(!(path.trim().substring(0, 5).equals("/test"))){
			path = "/test" + path;
		}
		Document document = saxReader.read(xmlFile); 
		Element testNode = document.getRootElement();  //get "test" node
		for(Iterator<?> i = testNode.elementIterator(); i.hasNext();){   
			Element caseNode = (Element) i.next();//get "login" node
			for(Iterator<?> j = caseNode.elementIterator(); j.hasNext();){   
				Element objectNode = (Element) j.next();   //get "object" node,such as "input" or "assert" node
				for(Iterator<?> k = objectNode.elementIterator(); k.hasNext();){   
					Element lastNode = (Element) k.next(); //get "last" node,such as "userName" or "passWord" node
					if(lastNode.getPath().trim().equals(path)){
						value = lastNode.attribute(attrName).getValue();
						break;
					}
				}			
			}		
		}
		return value;
	}

	public static String parserXml(String caseName,String input,String attrName,int attrIndex) throws DocumentException {   
		
		value = null;
		String path = "/test" + "/" + caseName + "/" + input + "/" + attrName;
		Document document = saxReader.read(xmlFile); 
		Element testNode = document.getRootElement();  //get "test" node
		for(Iterator<?> i = testNode.elementIterator(); i.hasNext();){   
			Element caseNode = (Element) i.next();//get "login" node
			for(Iterator<?> j = caseNode.elementIterator(); j.hasNext();){   
				Element objectNode = (Element) j.next();   //get "object" node,such as "input" or "assert" node
				for(Iterator<?> k = objectNode.elementIterator(); k.hasNext();){   
					Element lastNode = (Element) k.next(); //get "last" node,such as "userName" or "passWord" node
					if(lastNode.getPath().trim().equals(path)){
						List<Attribute> listAttr = lastNode.attributes();
						Attribute attr = listAttr.get(attrIndex);//get lastNode's attribute
						value = attr.getValue();
						break;
					}
				}
			}
		}
		return value;
	}
}

		
