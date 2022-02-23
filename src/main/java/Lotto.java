import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public static Lotto generateNumber() {
        List<Integer> lottoRange = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoRange.add(i);
        }
        Collections.shuffle(lottoRange);
        List<Integer> numbers = lottoRange.subList(0, 5);
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public int match(List<Integer> winningNumber) {
        int count = 0;
        for (int number : numbers) {
            if (winningNumber.contains(number)) {
                count++;
            }
        }
        return count;
    }

    public boolean hasBonusBall(int bonusBall) {
        return numbers.contains(bonusBall);
    }


    @Override
    public String toString() {
        String lotto = numbers.stream().map(String::valueOf)
                .collect(Collectors.joining(", "));
        return "[" + lotto + "]";
    }
}
