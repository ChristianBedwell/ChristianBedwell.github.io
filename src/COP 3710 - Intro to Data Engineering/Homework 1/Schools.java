import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// root tag is "School-Data"
@XmlRootElement (name="School-Data")	
@XmlAccessorType(XmlAccessType.FIELD)
public class Schools {

	@XmlElementWrapper(name = "Schools")	// child tag of "School-Data" is "Schools"
	@XmlElement(name="School")	// child tag of "Schools" is "School"
	private List<School> schoolList;
	
	// getter and setter for schoolList
	public List<School> getSchoolList() {	
		return schoolList;
	}

	public void setSchoolList(List<School> schools) {
		this.schoolList = schools;
	}

	public static void main(String args[]) throws JAXBException,
			JsonParseException, JsonMappingException, IOException{

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		School objArray[] = mapper.readValue(new File("schools.json"), School[].class);

		List<School> schoolObjects = Arrays.asList(objArray);
		Schools objs = new Schools();
		objs.setSchoolList(schoolObjects);
		
		// create a marshaller to convert java object to XML
	    JAXBContext context = JAXBContext.newInstance(Schools.class);
	    Marshaller marshaller = context.createMarshaller();	
	    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    // write to file
	    marshaller.marshal(objs, new File("schools.xml"));
	}
}
