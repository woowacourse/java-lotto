package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumber {

    private static final int MINIMUM_NUMBER = 1;
    private static final int MAXIMUM_NUMBER = 45;
    private static final Random random = new Random();

    private RandomNumber() {
    }

    public static List<Integer> generateNumbers(int size) {
        List<Integer> numbers = new ArrayList<>();

        while(numbers.size()!=size){
            int generatedNumber = generate();

            if(!numbers.contains(generatedNumber)){
                numbers.add(generatedNumber);
            }
        }

        Collections.sort(numbers);

        return numbers;
    }

    private static int generate() {
        return random.nextInt(MINIMUM_NUMBER, MAXIMUM_NUMBER + 1);
    }
}
