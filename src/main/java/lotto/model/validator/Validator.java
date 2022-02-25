package lotto.model.validator;

import java.util.List;

public class Validator {

    public static boolean isValidRange(int number) {
        return number >= 1 && number <= 45;
    }

    public static boolean isValidLength(List<Integer> numbers) {
        return numbers.size() == 6;
    }

    public static boolean isValidRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDuplicate(List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }
}
