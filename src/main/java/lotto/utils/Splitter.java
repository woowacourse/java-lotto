package lotto.utils;

import java.util.List;

public class Splitter {

    public static List<String> splitByComma(String input) {

        String regex = "\\s*\\d+\\s*(,\\s*\\d+\\s*)*";
        try {
            if (!input.matches(regex)) {
                throw new IllegalArgumentException("[ERROR] 올바르지 않은 입력값 입니다.");
            }
            return List.of(input.split(","));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 입력값 입니다.");
        }
    }
}
