package POMs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Page {
    static final Logger log = LogManager.getLogger(CursMD.class);
    void openThisPage();
    void checkTitle(String expected);
    String getTitle();
    String getMenuTitle();
    String getPageHeader();
}
