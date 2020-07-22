package properties;

public class XPathProperties {
    //main page xpath
    public static final String language_btn = "//a[@id='language-select']";
    public static final String language_btn_POPUP="//div[@class='head-menu-bar hidden-xs']//li//a";
    public static final String menu ="//ul[@class='nav navbar-nav']//li//a//span";
    public static final String currencyViBtn ="//button[@class='btn btn-dropdown dropdown-toggle']";
    public static final String currencyList ="//div[@class='col-sm-6 conversion-param']//div[@class='chosen-drop']/ul/li";
    //Rates evolution rates xpath
    public static final String rates_evolution_menu_option = "//li[contains(@class,'page-grafic_evolutii')]";
    public static final String rates_evolution_page_header="//div[@id='graficEvolutie']/h1";
    //Bank xpath
    //Convertor xpath
    public static final String convertor_tab = "//li[contains(@class, 'page-grafic_evolutii')]";
    public static final String cash_operations_all_tabs = "//ul[@class='nav nav-pills menu-second-lvl']/li";
    //Currencies xpath
    //Exchange rates xpath
    public static final String officialListRate ="//table[@id='tabelValute']//tbody//tr//td[contains(@class, 'cod')]/span";
    public static final String exchangesRatesMenuOption ="//li[contains(@class,'page-curs_valutar_banci')]";
    public static final String officialExchangeRateTab ="//li[contains(@class,'page-curs_valutar/oficial')]";
    public static final String cashOperationsTab="//li[contains(@class,'page-curs_valutar/cash')]";
    //Exchange offices xpath

}
