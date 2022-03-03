package view;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ExceptionHandler {

    private static final Map<Class<? extends Exception>, Function<Exception, String>> errorMessages = new HashMap<>();

    static {
        errorMessages.put(IllegalArgumentException.class, e -> e.getMessage());
        errorMessages.put(NumberFormatException.class, e -> "입력값이 반드시 숫자여야 합니다.");
        errorMessages.put(NullPointerException.class, e -> "입력값을 찾을 수 없습니다.");
    }

    public static String getExceptionMessage(Exception e) {
        if (errorMessages.containsKey(e.getClass()))  {
            return errorMessages.get(e.getClass()).apply(e);
        }
        return "예상하지 못한 문제가 생겼습니다." + e;
    }
}
