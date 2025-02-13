package util;

import java.util.Arrays;
import java.util.List;

public class InputConverter {
    public static Integer convertNumber(String input){
        Validator.validateNumberFormat(input.trim());
        return Integer.parseInt(input.trim());
    }

    public static List<Integer> convertWinningLotto(String originalInput){
        String input = originalInput.trim();
        Validator.validateWinningLottoPattern(originalInput);
        return Arrays.stream(input.split(", ")).map(Integer::parseInt).toList();
    }

    static private class Validator{
        private static void validateWinningLottoPattern(String originalInput){
            String input = originalInput.trim();
            if(!input.matches("^\\d+(, \\d+){5}$")){
                throw new IllegalArgumentException("잘못된 입력 형식");
            }
        }

        private static void validateNumberFormat(String input){
            if(!input.matches("\\d+")){
                throw new IllegalArgumentException("숫자 타입 오류");
            }
        }
    }
}
