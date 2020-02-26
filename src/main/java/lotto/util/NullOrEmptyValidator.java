package lotto.util;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class NullOrEmptyValidator {

    private static final String MESSAGE_FOR_NULL_ARGUMENT = "전달받은 값이 null 입니다.";
    private static final String MESSAGE_FOR_NULL_OR_EMPTY_ARGUMENT = "전달받은 값이 null 혹은 empty 입니다.";

    public static void isNull(Object object) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException(MESSAGE_FOR_NULL_ARGUMENT);
        }
    }

    public static void isNullOrEmpty(Collection collection) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_FOR_NULL_OR_EMPTY_ARGUMENT);
        }
    }

    public static void isNullOrEmpty(Map map) {
        if (Objects.isNull(map) || map.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_FOR_NULL_OR_EMPTY_ARGUMENT);
        }
    }
}
