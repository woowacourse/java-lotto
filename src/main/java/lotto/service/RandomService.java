package lotto.service;

import java.util.List;

public interface RandomService {

    List<List<Integer>> generateRandomNumbersList(int count, int maxSize, int maxNumber);
}
