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
 
public class write_matched_title {
    
        public static void main(String[] args) throws FileNotFoundException,XMLStreamException, FactoryConfigurationError 
        {

            try 
            {
                int j;
                int range = Integer.parseInt(args[0]); 

                // Creating a title file
                PrintWriter b1 = new PrintWriter(new FileWriter("title_of_splitted_file.txt"));
                /*File title_file = new File("title_of_splitted_file.txt");
                FileOutputStream f1 = new FileOutputStream(title_file);
                BufferedWriter b1 = new BufferedWriter(new OutputStreamWriter(f1));*/

                // Creating a matched File
                PrintWriter b2 = new PrintWriter(new FileWriter("title_matched_file.txt"));
                /*File  matched_file = new File("title_matched_file.txt");
                FileOutputStream f2 = new FileOutputStream(matched_file);
                BufferedWriter b2 = new BufferedWriter(new OutputStreamWriter(f2));*/


                for(j=1; j<range; j++)
                {
                    //Opening Splitted file and parse them using Stax parser
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
                                //String title; 
                                
                                event = eventReader.nextEvent();
                                if(page.getFirstText() == null)
                                {


                                    //Writing a title of splitted file in a text file
                                    b1.write(event.asCharacters().getData());
                                    //b1.newLine();
                                  
                                    while ((line = bufferedReader.readLine()) != null) 
                                    {

                                        if((event.asCharacters().getData()).equalsIgnoreCase(line) )
                                        {
                                            System.out.println("Matched title is :" + line);
                                            // Writing a matched title in a text file
                                            b2.write(line);
                                            //b2.newLine();

                                            flag = true;
                                            
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

                b2.close();
                b1.close();
                
            }
        
    
        catch(Exception e){
                System.out.println(e.getMessage());
            }    
 
        }
    }


