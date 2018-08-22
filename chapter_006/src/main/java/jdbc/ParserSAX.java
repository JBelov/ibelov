package jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class ParserSAX {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreSQL.class);

    /**
     * SAX parser with HandlerSAX.
     *
     * @param file input xstl file to parse values from.
     * @return sum of values from file.
     */
    public long parseAndCount(File file) {
        long result = 0;
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        HandlerSAX handler = new HandlerSAX();
        InputStream inputStream;
        SAXParser parser;
        try {
            inputStream = new FileInputStream(file);
            parser = factory.newSAXParser();
            parser.parse(inputStream, handler);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            LOGGER.error(e.getMessage(), e);
        }
        for (Integer item : handler.getList()) {
            result += item;
        }
        return result;
    }
}
