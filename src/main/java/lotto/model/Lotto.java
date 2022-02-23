package lotto.model;

import java.util.List;

public class Lotto {

    private List<Integer> numbers;
    private Rank rank;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int countMatchingNumber(List<Integer> numbers) {
        int count =  0;
        for (int number : numbers) {
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

    public void calculateRank(List<Integer> winningNumbers, int bonusNumber) {
        int count = countMatchingNumber(winningNumbers);
        boolean win = false;
        if(count == 5) {
            win = winBonusNumber(bonusNumber);
        }
        this.rank = Rank.getRank(count, win);
    }

    public boolean isRank(Rank rank) {
        return this.rank == rank;
    }

    public Rank getRank() {
        return rank;
    }
}
