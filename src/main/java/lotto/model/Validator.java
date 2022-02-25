package lotto.model;

import java.util.List;

class Validator {

    static boolean isValidRange(int number) {
        return number >= 1 && number <= 45;
    }

    static boolean isValidLength(List<Integer> numbers) {
        return numbers.size() == 6;
    }

    static boolean isValidRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 1 || number > 45) {
                return false;
            }
        }
        return true;
    }

    static boolean isDuplicate(List<Integer> numbers) {
        return numbers.size() != numbers.stream().distinct().count();
    }
}
