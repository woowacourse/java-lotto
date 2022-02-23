package lotto.model;

import java.util.List;

public class Lotto {

    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int countMatchingNumber(Lotto lotto) {
        int count =  0;
        for (int number : lotto.numbers) {
            count += containNumber(number);
        }
        return count;
    }

    private int containNumber(int number) {
        if (this.numbers.contains(number)) {
            return 1;
        }
        return 0;
    }

   public boolean winBonusNumber(int number) {
       return numbers.contains(number);
   }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
