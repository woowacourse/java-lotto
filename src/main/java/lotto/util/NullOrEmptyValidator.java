package lotto.util;

import java.util.Collection;
import java.util.Map;

public class NullOrEmptyValidator {
    public static void isNull(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("전달받은 값이 null 입니다.");
        }
    }

    public static void isNullOrEmpty(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("전달받은 값이 null 혹은 empty 입니다.");
        }
    }

    public static void isNullOrEmpty(Map map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException("전달받은 값이 null 혹은 empty 입니다.");
        }
    }
}
