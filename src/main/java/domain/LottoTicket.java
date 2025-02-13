package domain;

import java.util.List;


public class LottoTicket {
    public static int LOTTO_SIZE = 6;
    public static int LOTTO_PRICE = 1000;
    public static int LOTTO_MIN_NUMBER = 1;
    public static int LOTTO_MAX_NUMBER = 45;

    private List<Integer> numbers;

    public LottoTicket(List<Integer> numbers) {
        this.numbers = numbers;
    }
 
    public int countMatchedNumbers(List<Integer> winningNumbers) {
        int count = 0;
        for (int number : numbers) {
            if (winningNumbers.contains(number)) {
                count++;
            }
        }
        return count;
    }

    public boolean hasBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public int getSize() {
        return numbers.size();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
