package lotto.service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class RandomLottoServiceImpl implements RandomLottoService {

    private static final Random random = new Random();

    private int generateRandomNumber(int maxNumber) {
        return random.nextInt(maxNumber) + 1;
    }

    private List<LottoNumber> generateRandomLottoNumbers(int maxSize, int maxNumber) {
        List<Integer> randomNumbers = generateUniqueRandomNumbers(maxSize, maxNumber);

        return randomNumbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private List<Integer> generateUniqueRandomNumbers(int maxSize, int maxNumber) {
        Set<Integer> numberSet = new HashSet<>();

        while (numberSet.size() < maxSize) {
            numberSet.add(generateRandomNumber(maxNumber));
        }

        return numberSet.stream()
                .sorted()
                .toList();
    }

    public List<Lotto> generateLottoList(int count, int maxSize, int maxNumber) {
        return IntStream.range(0, count)
                .mapToObj(i -> generateRandomLottoNumbers(maxSize, maxNumber))
                .map(Lotto::new)
                .toList();
    }
}
