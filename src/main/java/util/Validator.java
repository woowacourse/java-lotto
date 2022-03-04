package util;

public class Validator {

    private Validator() {}

    public static void checkArgumentIsNull(Object... os) {
        for (Object o : os) {
            isNull(o);
        }
    }

    private static void isNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }
}
