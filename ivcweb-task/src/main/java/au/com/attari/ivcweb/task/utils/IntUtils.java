
package au.com.attari.ivcweb.task.utils;

public class IntUtils {

    public static Long convertToInt(String st) {
        Long in = null;
        if(st.indexOf(".") > -1) {
            in = BigDecimalUtils.convertToBigDecimal(st).longValue();
        }
        else {
            if (st.endsWith("B")) {
                in = Long.valueOf(st.substring(0, st.length() - 1));
                in *= 1000000000;
            } else if (st.endsWith("M")) {
                in = Long.valueOf(st.substring(0, st.length() - 1));
                in *= 1000000;
            }
            else {
                in = Long.valueOf(st);
            }
        }

        return in;
    }

}
