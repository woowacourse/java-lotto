import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
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
}
