import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import java.lang.*; 
import java.io.*;
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



public class last_revision{
    public static void main(String[] args)
    {
        int revision_count = 0;
        try {
            
                File file = new File(args[0]);
                XMLInputFactory factory = XMLInputFactory.newInstance();
                FileReader fr = new FileReader(file);
                XMLEventReader event_Reader =factory.createXMLEventReader(fr);

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                StringBuilder content = null;
                //PrintWriter br = new PrintWriter(new FileWriter("last_revision.txt"));

                File title_file = new File("last_revision.txt");
                FileOutputStream f1 = new FileOutputStream(title_file);
                BufferedWriter br = new BufferedWriter(new OutputStreamWriter(f1));


                while(event_Reader.hasNext())
                {
                    XMLEvent event=event_Reader.nextEvent(); 
                    if(event.isStartElement() && event.asStartElement().getName().getLocalPart().equals("revision"))
                    {
                        revision_count++;
                    }
                }
                //System.out.println("\t\tTotal no. of revision tag is : " + revision_count);
                fr.close();

                InputStream in = new FileInputStream(file);
                XMLEventReader eventReader = factory.createXMLEventReader(in);

                Page page = null;
 
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
                        if (event.asStartElement().getName().getLocalPart().equals("revision"))
                        {
                            event = eventReader.nextEvent();
                            revision_count--;
                               //System.out.println(revision_count);
                            if(revision_count==0)
                            {
                               
                                //System.out.println("-------------------------Last Revision tag----------------------------");
                                    
                                while (eventReader.hasNext())
                                { 
                                    event = eventReader.nextEvent();

                                    if(event.isStartElement()) 
                                    {    
                                        content = new StringBuilder();
                                        System.out.println("<"+ event.asStartElement().getName().getLocalPart()+ ">");
                                        br.write("<"+ event.asStartElement().getName().getLocalPart()+ ">");
                                    } 
                                    else if(event.isEndElement())
                                    {
                                        if(content != null) 
                                        {
                                              String leafText = content.toString();
                                        } 
                                        content = null;
                                    } 
                                    else if(event.isCharacters()) 
                                    {
                                        if(content != null) 
                                        {
                                            content.append(event.asCharacters().getData());
                                            System.out.println(content.toString());
                                            br.write(content.toString());
                                        }
                                    }
                                          // other event types here
                                }

                                    // Start the event

                                    /*if (event.isStartElement()) 
                                    {
                                        if (event.asStartElement().getName().getLocalPart().equals("id"))
                                        {
                                            event = eventReader.nextEvent();
                                            System.out.println("\t\tId : " + event.asCharacters().getData());
                                        }

                                    } 
                                    if (event.isStartElement()) 
                                    {
                                        if (event.asStartElement().getName().getLocalPart().equals("parentid"))
                                        {
                                            event = eventReader.nextEvent();
                                            System.out.println("\t\tParent Id : " + event.asCharacters().getData());
                                        }

                                    }

                                    if (event.isStartElement()) 
                                    {
                                        if (event.asStartElement().getName().getLocalPart().equals("timestamp"))
                                        {
                                            event = eventReader.nextEvent();
                                            System.out.println("\t\tTimestamp : " + event.asCharacters().getData());
                                        }

                                    }
                                    if (event.isStartElement()) 
                                    {
                                        if (event.asStartElement().getName().getLocalPart().equals("contributor"))
                                        {
                                            event = eventReader.nextEvent();
                                            System.out.println("\t\tContributor : " + event.asCharacters().getData());
                                            while(event_Reader.hasNext())
                                            {
                                                event=event_Reader.nextEvent();
                                                if (event.isStartElement()) 
                                                {
                                                    if (event.asStartElement().getName().getLocalPart().equals("username"))
                                                    {
                                                        event = eventReader.nextEvent();
                                                        System.out.println("        \t\tUsername : " + event.asCharacters().getData());
                                                    }

                                                }
                                                if (event.isStartElement()) 
                                                {
                                                    if (event.asStartElement().getName().getLocalPart().equals("id"))
                                                    {
                                                        event = eventReader.nextEvent();
                                                        System.out.println("        \t\tId : " + event.asCharacters().getData());
                                                    }

                                                }
                                            } 
                                        }

                                    } 
                                 
                                    if (event.isStartElement()) 
                                    {
                                        if (event.asStartElement().getName().getLocalPart().equals("model"))
                                        {
                                            event = eventReader.nextEvent();
                                            System.out.println("\t\tModel : " + event.asCharacters().getData());
                                        }

                                    } 
                                    if (event.isStartElement()) 
                                    {
                                        if (event.asStartElement().getName().getLocalPart().equals("format"))
                                        {
                                            event = eventReader.nextEvent();
                                            System.out.println("\t\tFormat : " + event.asCharacters().getData());
                                        }

                                    }
                                    if (event.isStartElement()) 
                                    {
                                        if (event.asStartElement().getName().getLocalPart().equals("text"))
                                        {
                                            event = eventReader.nextEvent();
                                            content = new StringBuilder();
                                            System.out.println("        \t\tText : " + event.asCharacters().getData());
                                        }

                                    }

                                    else if(event.isEndElement())
                                    {
                                        if(content != null) 
                                        {
                                              String leafText = content.toString();
                                        } 
                
                                        content = null;
                                    } 
                                    else if(event.isCharacters()) 
                                    {
                                        if(content != null) 
                                        {
                                            content.append(event.asCharacters().getData());
                                            System.out.println(content);
                                        }
                                    }

                                    if (event.isStartElement()) 
                                    {
                                        if (event.asStartElement().getName().getLocalPart().equals("sha1"))
                                        {
                                            event = eventReader.nextEvent();
                                            System.out.println("\t\tSha1 : " + event.asCharacters().getData());
                                        }

                                    }*/     
                                
                                }
                            
         
                        }
                    }
  
                }
                br.close();
            } 
                    
        catch(Exception e){
                System.out.println(e.getMessage());
            }

    }
 }   








