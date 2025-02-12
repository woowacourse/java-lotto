package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Lotto {
    private List<Integer> numbers = new ArrayList<>();

    public Lotto() {
        createNumbers();
    }

    private void createNumbers() {
        Random random = new Random();
        do {
            int randomNumber = random.nextInt(1, 51);
            addNumber(randomNumber);
        } while (numbers.size() < 6);
        Collections.sort(numbers);
    }

    private void addNumber(int randomNumber) {
        if(!numbers.contains(randomNumber)) {
            numbers.add(randomNumber);
        }
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
