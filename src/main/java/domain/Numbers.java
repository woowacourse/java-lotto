package domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Numbers {
    private final List<Num> numbers;

    public Numbers(List<Integer> numbers) {
        sortingNumbers(numbers);
        List<Num> sortedNumbers = new ArrayList<>();
        for(int number : numbers) {
            sortedNumbers.add(new Num(number));
            System.out.println(number);
        }
        this.numbers = sortedNumbers;
    }

    private void sortingNumbers(List<Integer> numbers) {
        numbers.sort(Comparator.naturalOrder());
    }

    public List<Integer> getNumbers() {
        List<Integer> numbersList = new ArrayList<>();
        for(Num num : numbers) {
                numbersList.add(num.getNum());
        }
        System.out.println(numbersList);
        return numbersList;
    }
}
