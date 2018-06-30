/*  
    Ojective of this code is to create a text file that contained the matched ns values

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
// tag_based_deletion Class 
public class matched_ns_tag {
        // Main class
        public static void main(String[] args) throws FileNotFoundException,XMLStreamException, FactoryConfigurationError 
        {
            
            try 
            {
                int j;
                int range = Integer.parseInt(args[0]);
                
                // Creating a text file that contain 
                File  matched_file = new File("title_ns_matched.txt");
                FileOutputStream fos = new FileOutputStream(matched_file);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

                // read the tag and value from user input
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                System.out.println("Enter the tag: " );
                String  tag = reader.readLine();

                System.out.println("Enter the value: " );
                String  number = reader.readLine();
                int value = Integer.parseInt(number);

                bw.write("Title of Page that contain ns ="+ value);
                bw.newLine();

                
                for(j=1; j<range; j++)
                {
                    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                    File fr = new File("Splitted_file"  +j+ ".xml");
                    FileInputStream in = new FileInputStream(fr);
                    XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
                    
                    Page page = null;
                    
                    boolean flag = false;
                    
                    //  eventReader has next event 
                    while (eventReader.hasNext()) 
                    {
                        XMLEvent event = eventReader.nextEvent();
                        
                        // Start the event
                        if (event.isStartElement())
                        {
                            
                            StartElement startElement = event.asStartElement();
             
                            if (startElement.getName().getLocalPart().equals("page"))
                            {   // inside the page element
                                page = new Page();
                            
                            }
                        }
         
                        // start new element
                        if (event.isStartElement()) 
                        {
                            // match the tag value
                            if (event.asStartElement().getName().getLocalPart().equals(tag)) 
                            {
                                
                                event = eventReader.nextEvent();
                                if(page.getFirstText() == null)
                                {
                                    // get the value of element 
                                    System.out.println(event.asCharacters().getData());
                                    int num = Integer.parseInt(event.asCharacters().getData());
                                    
                                    // if user input value is matched with element value the write that value in a text file 
                                    if(num==value )
                                    {
                                            System.out.println("Matched Value :" + event.asCharacters().getData());

                                            bw.write(event.asCharacters().getData());
                                            bw.newLine();
                                            flag = true;
                                            //fileReader.close();
                                            
                                    }
                                        page.setFirstText("notnull");
                                    

                                }  

                            }
                        }
                    
                    }

                    in.close();
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


