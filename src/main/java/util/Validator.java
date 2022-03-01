package util;

public class Validator {

    private Validator() {}

    public static void checkArgumentIsNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }
}
