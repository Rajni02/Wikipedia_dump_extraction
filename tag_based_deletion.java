/*
    We have large no. of splitted  xml files, each file contain a single page . 
    Tag and value is given by user.The objective of this code is to find the tag 
    in each  splitted xml file and match that tag value with splitted file's tag value,
    if tag and its value is matched with splitted file's tag - values then kept that
    splitted xml file otherwise delete it. At the end we have only those xml files who's 
    tag-values matched with user input tag and value.
*/


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.*;
import java.io.InputStreamReader;
import java.io.IOException;
// Page Class
class Page
    {
        private String firstText = null;
     
        public void setFirstText(String str)
        {
            firstText =  str;
        }
 
        public String getFirstText()
        {       
            if(firstText == null)
            {
                return null;
            }
            else
            {
                return firstText;
            }
        }
    }
 
// tag_based_deletion class 
public class tag_based_deletion {
        // Main class
        public static void main(String[] args) throws FileNotFoundException,XMLStreamException, FactoryConfigurationError 
        {
            
            try 
            {
                int j;
                int range = Integer.parseInt(args[0]);

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the tag: " );
                String  tag = reader.readLine();

                System.out.println("Enter the value: " );
                String  number = reader.readLine();
                int value = Integer.parseInt(number);

	    	  	
                for(j=1; j<range; j++)
                {
                    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                    File fr = new File("Splitted_file"  +j+ ".xml");
                    FileInputStream in = new FileInputStream(fr);
                    XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
                    
                    Page page = null;
                    
                    boolean flag = false;
         
                    while (eventReader.hasNext()) 
                    {
                        XMLEvent event = eventReader.nextEvent();
                        
                        // Start the event
                        if (event.isStartElement())
                        {
                            
                            StartElement startElement = event.asStartElement();
             
                            if (startElement.getName().getLocalPart().equals("page"))
                            {
                                // inside the page element 
                                page = new Page();
                            
                            }
                        }
         
                        // start element
                        if (event.isStartElement()) 
                        {
                            // find the tag element
                            if (event.asStartElement().getName().getLocalPart().equals(tag)) 
                            {
                                
                                event = eventReader.nextEvent();
                                if(page.getFirstText() == null)
                                {   
                                    // print the tag value
                                    System.out.println(event.asCharacters().getData());
                                    int num = Integer.parseInt(event.asCharacters().getData());

                                    //if the tag value is matched with user input then set the flag
                                    if(num==value )
                                    {
                                            System.out.println("Matched Value :" + event.asCharacters().getData());
                                            flag = true;
                                            //fileReader.close();
                                            
                                    }
                                        page.setFirstText("notnull");
                                    

                                }  

                            }
                        }
                    
                    }

                    in.close();
                    // if the flag value is false then delete the file
                    if(flag == false)
                    {
                        fr.delete();
                    }
                }
                
            }
        
    
        catch(Exception e){
		System.out.println(e.getMessage());
               
            }    
 
        }
    }


