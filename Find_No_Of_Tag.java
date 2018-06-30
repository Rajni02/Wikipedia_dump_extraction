/*
	Objective of this code is to find the  total no. of tag in an XML file
*/

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// TagFind class defined
public class Find_No_Of_Tag {
	// main class defined
    public static void main(String[] args)
    {
    	// Initialize the count variable
        int tag_count = 0;
        try {
            
            	XMLInputFactory factory = XMLInputFactory.newInstance();
            	FileReader fr = new FileReader("enwiki.xml");
        		XMLEventReader eventReader =factory.createXMLEventReader(fr);

        		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				System.out.println("Enter the tag: " );
				String  tag = reader.readLine();
      
                while(eventReader.hasNext())
                 {
					XMLEvent event=eventReader.nextEvent(); 
				    if(event.isStartElement() && event.asStartElement().getName().getLocalPart().equals(tag))
				    {
				    	tag_count++;
				    	System.out.println("Page Start:");
				    	System.out.println("No. of " + tag +"'s : " + tag_count);
					}

					if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals(tag)) 
					{
						System.out.println("page end.\n");
					}
				}
				fr.close();
			} 
                	
        catch(Exception e){
                System.out.println(e.getMessage());
            }

    }
 }   












