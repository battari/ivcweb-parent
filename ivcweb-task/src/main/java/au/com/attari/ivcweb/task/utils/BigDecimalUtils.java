package au.com.attari.ivcweb.task.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {
	
	public final static BigDecimal BG_10 = new BigDecimal(10);
	public final static BigDecimal BG_100 = new BigDecimal(100);

    public static BigDecimal convertToBigDecimal(String st) {

        BigDecimal fl = null;

        if(st.endsWith("B")) {
            fl = new BigDecimal(st.substring(0, st.length() -1 ));
            BigDecimal billions = new BigDecimal("1000000000.00");
            fl = fl.multiply(billions);
        }
        else if(st.endsWith("M")) {
            fl = new BigDecimal(st.substring(0, st.length() -1 ));
            BigDecimal millions = new BigDecimal("1000000.00");
            fl = fl.multiply(millions);
        }
        else {
            fl = new BigDecimal(st);
        }

        return fl;
    }

    public static boolean isLessThanTen(String st) {

    	BigDecimal fl = new BigDecimal(st);

        if(convertToBigDecimal(st).compareTo(BG_10) < 0) {
            return true;
        }
        return false;
    }

    public static BigDecimal convertJavaBigDecimalStringToBigDecimal(String st) {

        // BigDecimal fl = BigDecimal.parseBigDecimal(st);
        BigDecimal fl = new BigDecimal(st);

        return fl;
    }

}
