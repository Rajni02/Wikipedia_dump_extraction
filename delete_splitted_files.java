/*
    We have one text file that contain the title of pages and large no. of splitted  xml files, each file contain a single page . 
    Objective of this code is find the title of each  splitted xml file and if that title is present in our text file then kept that
    splitted xml file otherwise delete it. At the end we have only those xml files who's title is present in our text file.
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
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.*;
import java.lang.*;
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
 
public class delete_splitted_files {
    
        public static void main(String[] args) throws FileNotFoundException,XMLStreamException, FactoryConfigurationError 
        {

            try 
            {
                int j;
                int range = Integer.parseInt(args[0]);    
                for(j=1; j<range; j++)
                {
                    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                    File fr = new File("Splitted_file"  +j+ ".xml");
                    FileInputStream in = new FileInputStream(fr);
                    XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
                    //File fr = new File("E:\\Internship\\Page_Category\\enwiki\\Splitted_file"  +j+ ".xml");
                    Page page = null;
                    /*File file = new File("indian_sport_cat_page_find.txt");
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line;*/
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
                                page = new Page();
                            
                            }
                        }
         
                        // fetch data
                        if (event.isStartElement()) 
                        {

                            if (event.asStartElement().getName().getLocalPart().equals("title")) 
                            {
                                File file = new File("indian_sport_cat_page_find.txt");
                                FileReader fileReader = new FileReader(file);
                                BufferedReader bufferedReader = new BufferedReader(fileReader);
                                String line;

                                event = eventReader.nextEvent();
                                if(page.getFirstText() == null)
                                {
                                    System.out.println(event.asCharacters().getData());
                                    while ((line = bufferedReader.readLine()) != null) 
                                    {
                                        //System.out.println(event.asCharacters().getData());
                                        if((event.asCharacters().getData()).equalsIgnoreCase(line) )
                                        {
                                            System.out.println("Matched title is :" + event.asCharacters().getData());
                                            flag = true;
                                            //fileReader.close();
                                            
                                        }
                                        page.setFirstText("notnull");
                                    } 

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



/*----------------------------------------------------------------------------------------------------------------------------------------*/
/*import java.io.*;
 
public class Test
{
    public static void main(String[] args)
    {
        File file = new File("Splited_Page3.xml");
         
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
    }
}*/
