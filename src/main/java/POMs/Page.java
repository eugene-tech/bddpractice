package POMs;

public interface Page {
    void openThisPage();
    void checkTitle(String expected);
    String getTitle();
    String getMenuTitle();
    String getPageHeader();
}
