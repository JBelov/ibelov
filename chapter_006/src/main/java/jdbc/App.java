package jdbc;

import java.io.File;
import java.util.ResourceBundle;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class App {

    /**
     * Main method.
     * <p>
     * 1. Creates SQL lite database and fill it with n serial int values. (StoreSQL.class)
     * 2. Gets all entries from database and generates XML file from it. (StoreXML.class)
     * 3. Converts XML file into XSTL file. (ConvertXSQT.class)
     * 4. Parse XSTL and count sum of all items. (ParserSAX.class)
     *
     * @param n      number of values.
     * @param config name of config file. (use "lite" by default).
     */
    public long start(int n, String config) {
        long result;
        ResourceBundle resource = ResourceBundle.getBundle(config);
        String resultXML = resource.getString("resultXML");
        String resultXSTL = resource.getString("resultXSTL");
        String scheme = resource.getString("scheme");
        File result1 = new File(resultXML);
        File result2 = new File(resultXSTL);

        try (StoreSQL storeSQL = new StoreSQL(config)) {
            storeSQL.generate(n);
            StoreXML storeXML = new StoreXML(result1);
            storeXML.save(storeSQL.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(result1, result2, scheme);
        ParserSAX parserSAX = new ParserSAX();
        result = parserSAX.parseAndCount(result2);
        System.out.println(result);
        return result;
    }
}
