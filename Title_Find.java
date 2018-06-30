/*
    Wikimedia dumps contains large no. of pages 
    Objective of this code is to find title of pages that are contained in this wikimedia dump .

*/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
 
// definition of page Class 
class Page{
    private String firstText = null;
 
    public void setFirstText(String str){
        firstText =  str;
    }
 
    public String getFirstText(){       
        if(firstText == null){
            return null;
        }else{
            return firstText;
        }
    }
}

// Title class 
public class Title_Find {
    // main class
    public static void main(String[] args) throws FileNotFoundException,XMLStreamException, FactoryConfigurationError {

    try {
        
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
 
        InputStream in = new FileInputStream("enwiki-20170201-pages-meta-history9.xml-p002254931p002307949");
        XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
 
        Page page = null;
 
        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            // Start the event
            if (event.isStartElement()) {
 
                StartElement startElement = event.asStartElement();
 
                if (startElement.getName().getLocalPart().equals("page")) {
                    page = new Page();

                    }
                }
 
                // fetch data
                if (event.isStartElement()) 
                {
                    if (event.asStartElement().getName().getLocalPart().equals("title"))
                    {
                        event = eventReader.nextEvent();
 
                        if(page.getFirstText() == null)
                        {
                            System.out.println("Page title is :  "
                                    + event.asCharacters().getData());
                            page.setFirstText("notnull");
                           
                        }
 
                    }
                }
            }

        }
        catch(Exception e){
                System.out.println(e.getMessage());
            }    
 
        }
    }






















//-------------------------------------------------------------------------------------------------------------------
/*import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import java.util.Iterator;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.xml.stream.events.*;
import java.util.*;

public class Title_Find {
    public static void main(String[] args)
    {
        boolean title = false;
        try {
            
            	XMLInputFactory factory = XMLInputFactory.newInstance();
        		XMLEventReader eventReader =factory.createXMLEventReader(new FileReader("enwiki.xml"));

        		/*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter the tag: " );
				String  tag = reader.readLine();*/
      
                /*while(eventReader.hasNext())
                 {
					XMLEvent event = eventReader.nextEvent();
 
            if (event.isStartElement())
            {
                StartElement element = (StartElement)event;
 
                Iterator<Attribute> iterator = element.getAttributes();
                while (iterator.hasNext())
                {
                    Attribute attribute = iterator.next();
                    QName name = attribute.getName();
                    String value = attribute.getValue();
                    //System.out.println(name+" = " + value);
                }
 
                if (element.getName().toString().equalsIgnoreCase("tilte"))
                {
                    title = true;
                }
            }


            if (event.isEndElement())
            {
                EndElement element = (EndElement) event;
 
                if (element.getName().toString().equalsIgnoreCase("title"))
                {
                    title= false;

                }

            }

            if (event.isCharacters())
            {
                
                Characters element = (Characters) event;
                if (title)
                {
                    System.out.println(element.getTextCharacters());
                }

            }
        }

			} 
                	
        catch(Exception e){
                System.out.println(e.getMessage());
            }

    }
 }*/   