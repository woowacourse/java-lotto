package domain;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers){
        this.numbers = numbers;
    }

    //TODO: getter 지우기
    public List<Integer> getNumbers() {
        return numbers;
    }

    public String toString() {
        return numbers.toString();
    }

    public Rank getRank(List<Integer> winningNumbers, int bonusBall){
         int count = (int)numbers.stream()
                .filter(winningNumbers::contains)
                .count();
         if(count == 6){
             return Rank.FIRST;
         }
         if(count == 5 && numbers.contains(bonusBall)){
             return Rank.SECOND;
         }
         if(count == 5){
             return Rank.THIRD;
         }
         if(count == 4){
             return Rank.FOURTH;
         }
         if(count == 3){
             return Rank.FIFTH;
         }
         return Rank.NONE;
    }
}
