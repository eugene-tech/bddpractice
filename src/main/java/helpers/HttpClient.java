package helpers;

import kong.unirest.Unirest;

public class HttpClient {
    public static void initHttpTimeOutConnection(int socketTimeOut,int connectTimeout){
        Unirest.config()
                .socketTimeout(socketTimeOut)
                .connectTimeout(connectTimeout);
    }

    public static String get(String url){
        return Unirest.get(url).asString().getBody();
    }


//    public static void main(String[] args) throws IOException, IllegalAccessException, IllegalConfigException, JAXBException {
//
//        LinksProperties.readProperty();
//        GeneralProperties.readProperty();
//        //
//        String url = LinksProperties.bnm_ro_official_exchange_link+GeneralProperties.date;
//
//        ValCurs valCurs;
//        valCurs = (ValCurs) XMLParser.parseXML(HttpClient.get(url), ValCurs.class);
//
//        System.out.println(valCurs.getValute().get(0).getName());
//
//
//    }
}
