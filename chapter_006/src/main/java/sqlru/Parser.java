package sqlru;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ivan Belov (ivan@belov.org)
 * @version $Id$
 * @since 0.1
 */
public class Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);
    private final SimpleDateFormat format = new SimpleDateFormat("d MMM yy, HH:mm", new Locale("ru", "RU"));
    private String find;
    private String ignore;

    /**
     * Constructor. Loads resources.
     */
    Parser(ResourceBundle resource) {
        this.find = resource.getString("find");
        this.ignore = resource.getString("ignore");
    }

    /**
     * Parse information from the remote website and provide it as list of Items.
     *
     * @param lastStoredDate The date of last parsing.
     * @return List of results.
     */
    List<Item> parse(Timestamp lastStoredDate) {

        List<Item> result = new LinkedList<>();
        for (int i = 1; i < 100; i++) {
            try {
                Document doc = Jsoup.connect("http://www.sql.ru/forum/job-offers/" + i).get();
                Elements elements = doc.getElementsByClass("postslisttopic");
                int skip = 3;
                for (Element el : elements) {
                    if (skip-- <= 0) {
                        if (lastStoredDate.after(parseDate(el.parent().child(5).text())) && !el.child(0).text().contains("Важно:")) {
                            break;
                        }
                        if (el.child(0).text().toLowerCase().contains(find.toLowerCase())
                                && !el.child(0).text().toLowerCase().contains(ignore.toLowerCase())) {
                            result.add(new Item(
                                    Integer.parseInt(el.child(0).attr("href").substring(24, 31)),
                                    el.child(0).attr("href"),
                                    el.child(0).text(),
                                    parseDate(el.parent().child(5).text())));
                            System.out.printf("id %d \n %s \n %s \n %s \n",
                                    Integer.parseInt(el.child(0).attr("href").substring(24, 31)),
                                    el.child(0).attr("href"),
                                    el.child(0).text(),
                                    parseDate(el.parent().child(5).text()));
                        }
                    }
                }
            } catch (IOException e) {
                LOGGER.error("IO error:", e);
            }
        }
        return result;
    }

    /**
     * Formats date from the source to Timestamp. Very depends of the source website view.
     *
     * @param date string date from the source website.
     * @return Timestamp correct date.
     */
    private Timestamp parseDate(String date) {
        Calendar calendar = Calendar.getInstance();
        if (date.contains("сегодня")) {
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(9, 11)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(12, 14)));
        } else if (date.contains("вчера")) {
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(7, 9)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(10, 12)));
        } else {
            try {
                calendar.setTime(format.parse(date));
            } catch (ParseException e) {
                LOGGER.error("IO error", e);
            }
        }
        return new Timestamp(calendar.getTimeInMillis());
    }
}
