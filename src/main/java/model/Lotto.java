package model;

import dto.LottoNumbersResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lotto {

    private static final int NUMBER_COUNT = 6;

    private final List<Integer> numbers;

    public Lotto() {
        numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < NUMBER_COUNT; i++) {
            numbers.add(random.nextInt(45) + 1);
        }
    }

    public LottoNumbersResponse createResponse() {
        return new LottoNumbersResponse(
                numbers.stream()
                        .map(String::valueOf)
                        .toArray(String[]::new)
        );
    }

}
