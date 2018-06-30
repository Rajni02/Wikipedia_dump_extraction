/* Split a Big XML file into small xml files, each splitted files contain exactly one page*/

import javax.xml.transform.TransformerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.stream.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.*;
import javax.xml.transform.*;
import javax.xml.stream.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stax.StAXSource;

// split_xml_file Class
public class Split_xml_file
    {
// Main Class
        public static void main(String[] s)
        {
                String suffix = "\n</page>\n";
                int count=0;
                try 
                {

                     int i=1;
                     XMLInputFactory xif = XMLInputFactory.newInstance();                       // Create a new instance of XMLInputFactory         
                     XMLStreamReader xsr = xif.createXMLStreamReader(new FileReader("enwiki-20170201-pages-meta-history9.xml-p002254931p002307949"));       //
                     xsr.nextTag(); 

                     TransformerFactory tf = TransformerFactory.newInstance();
                     Transformer t = tf.newTransformer();
                     t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");                // Omit the declaration of Xml File
                     while(xsr.nextTag() == XMLStreamConstants.START_ELEMENT)                    // Split the Xml file, if it get the new tag. <page> is at 1st level, so it split on the basis of <page> tag 
                     {
                         File file = new File("Splitted_file"  +i+ ".xml");                      // Create a new splitted Xml file
                         FileOutputStream fos=new FileOutputStream(file,true);
                         t.transform(new StAXSource(xsr), new StreamResult(fos));
                         i++;

                     }

                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
        }

    }
