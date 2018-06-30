//package java.xml.stax;
import java.io.*;
import javax.xml.*;
import javax.xml.stream.*;

public class XmlStreamReader{
	public static void ReadFile(){
		try {
				XMLInputFactory xMLInputFactory = XMLInputFactory.newFactory();
				XMLStreamReader xMLStreamReader = xMLInputFactory.createXMLStreamReader(new FileInputStream("sample.xml"));
				while(xMLStreamReader.hasNext()){
					int eventType = xMLStreamReader.getEventType();
					switch(eventType){
						case XMLStreamReader.START_ELEMENT:
							System.out.println("Start" + xMLStreamReader.getLocalName());
							if(xMLStreamReader.getAttributeCount()>0)
							{
								System.out.println("Title count" +xMLStreamReader.getAttributeCount());
								for (int i=0; i<xMLStreamReader.getAttributeCount();i++)
								{
									System.out.println(xMLStreamReader.getAttributeLocalName(i) + "-" + xMLStreamReader.getAttributeValue(i));
								} 
							}
							break;
						case XMLStreamReader.END_ELEMENT:
							System.out.println("End" + xMLStreamReader.getLocalName());

							break;
						case XMLStreamReader.CHARACTERS:
							if(!xMLStreamReader.getText().isEmpty())
								System.out.println("Charcters" + xMLStreamReader.getText());
							break;
					}
					xMLStreamReader.next();
				}
				xMLStreamReader.close();
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	public static void main (String[] args)
	{
		ReadFile();
	}



}