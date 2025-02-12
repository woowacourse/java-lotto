package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Lotto {

    private final List<Integer> numbers;

    public Lotto() {
        this.numbers = new ArrayList<>();
        Random random = new Random();
        
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(1, 46);
            numbers.add(number);
        }
    }

    public List<String> getNumbers() {
        return numbers.stream()
                .map(Object::toString)
                .toList();
    }
}
