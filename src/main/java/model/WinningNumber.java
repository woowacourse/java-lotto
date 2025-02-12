package model;

import controller.LottoController;
import java.util.ArrayList;
import java.util.List;

public class WinningNumber {
    private final List<Integer> numbers = new ArrayList<>();

    public WinningNumber(String inputWinningNumbers) {

        String[] winningNumbers = inputWinningNumbers.split(", ");
        if(winningNumbers.length != LottoConstants.NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호의 개수를 6개로 입력해주세요.");
        }

        for(String winningNumber : winningNumbers) {
            try {
                int number = Integer.parseInt(winningNumber);
                if(number < LottoConstants.MIN_NUMBER || number > LottoConstants.MAX_NUMBER) {
                    throw new IllegalArgumentException("당첨 번호는 1~45 사이의 정수로 입력해주세요.");
                }
                if(numbers.contains(number)) {
                    throw new IllegalArgumentException("당첨 번호 간 중복 없이 입력해주세요.");
                }
                 numbers.add(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("당첨 번호는 정수로 입력해주세요.");
            }
        }
    }

    public boolean contains(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public int findMatchingCountWith(List<Integer> lottoNumbers) {
        return (int) numbers.stream()
                .filter(n -> lottoNumbers.contains(n))
                .count();
    }
}
