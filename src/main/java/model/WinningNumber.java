package model;

import java.util.HashSet;
import java.util.List;

public class WinningNumber {
    private final List<Integer> numbers;

    public WinningNumber(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumberDuplication(numbers);
        for (int number : numbers) {
            validateNumberRange(number);
        }
        this.numbers = numbers;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public int findMatchingCountWith(List<Integer> lottoNumbers) {
        return (int) numbers.stream()
                .filter(n -> lottoNumbers.contains(n))
                .count();
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LottoConstants.NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호의 개수를 6개로 입력해주세요.");
        }
    }

    private void validateNumberRange(int number) {
        if (number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
            throw new IllegalArgumentException("당첨 번호는 1~45 사이의 정수로 입력해주세요.");
        }
    }

    private void validateNumberDuplication(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("당첨 번호 간 중복 없이 입력해주세요.");
        }
    }

//    private void validateNumberType(Runnable task, String winningNumber) {
//        try {
//            task.run();
//        } catch (NumberFormatException e) {
//            if (winningNumber.length() > 10) {
//                throw new IllegalArgumentException("당첨 번호는 10자리 이하의 정수로 입력해주세요.");
//            }
//            throw new IllegalArgumentException("당첨 번호는 정수로 입력해주세요.");
//        }
//    }
}
