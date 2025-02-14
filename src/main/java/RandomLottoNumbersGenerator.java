import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomLottoNumbersGenerator implements RandomNumbersGenerator {
    @Override
    public List<Integer> generate() {
        validate(Number.MIN, Number.MAX, Lotto.SIZE);

        Random random = new Random();
        Set<Integer> numbers = new HashSet<>();
        while(numbers.size() < Lotto.SIZE) {
            int number = random.nextInt(Number.MAX - Number.MIN + 1) + Number.MIN;
            numbers.add(number);
        }
        return numbers.stream().toList();
    }

    private static void validate(int start, int end, int count) {
        if (end - start + 1 < count) {
            throw new IllegalArgumentException("난수 생성 범위가 생성 가능 개수보다 적습니다.");
        }
    }
}
