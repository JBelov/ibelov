package jdbc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class StoreXML {

    private File target;

    /**
     * Makes XML file from the DB.
     *
     * @param target file to save XML.
     */
    StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> list) throws JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(new Entries(list), target);

    }
}


