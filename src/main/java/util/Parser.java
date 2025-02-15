package util;

import java.util.List;

public class Parser {

    public static int parseOneNumber(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("정수 값을 입력해주세요.");
        }
    }

    public static List<Integer> parseNumbers(List<String> values) {
        try {
            return values.stream()
                    .map(string -> Integer.parseInt(string.trim()))
                    .toList();
        } catch (NumberFormatException e) {
            //TODO : 10자리 초과 입력시 에러메세지 분기처리
            throw new IllegalArgumentException("정수 값을 입력해주세요.");
        }
    }
}
