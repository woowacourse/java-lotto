package lotto.model;

import java.util.List;

public class Lotto {

    private List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int countMatchingNumber(Lotto lotto) {
        int count =  0;
        for (int number : lotto.numbers) {
            if (this.numbers.contains(number)) {
                count++;
            }
        }
        return count;
    }

   public boolean winBonusNumber(int number) {
       return numbers.contains(number);
   }
}
