package za.co.lindaring.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

public final class CookbookUtil {

    private CookbookUtil() {
    }

    public static String formatDate(Date date) {
        return DateFormatUtils.format(date, "dd MMM yyyy");
    }

}
