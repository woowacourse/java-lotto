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

    public int getRank(List<Integer> winningNumbers, int bonusBall){
         int count = (int)numbers.stream()
                .filter(winningNumbers::contains)
                .count();
         if(count == 6){
             return 1;
         }
         if(count == 5 && numbers.contains(bonusBall)){
             return 2;
         }
         if(count == 5){
             return 3;
         }
         if(count == 4){
             return 4;
         }
         if(count == 3){
             return 5;
         }
         return 6;
    }
}
