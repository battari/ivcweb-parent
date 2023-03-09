package au.com.attari.ivcweb.ivcwebmvc.util;


public class HistoricalDataUtil {

    private HistoricalDataUtil() {

    }
    private static HistoricalDataUtil _instance = new HistoricalDataUtil();

    public static HistoricalDataUtil getInstance(){
        return _instance;
    }

    public String parseFormat(String data) {
        //1. remove all ,
        //2. change tab char to ,
        //3. change -- to 0.0
        String tmp = data.replaceAll(",", "")
                .replaceAll("\t", ",")
                .replaceAll("--", "0.0")
                .replaceAll("\r", ",\n");
        return tmp;
    }
}
