package fit.pis.crm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class Functions {

    private Functions() {
 
    }

    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

}
