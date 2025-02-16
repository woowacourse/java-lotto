package Model;

import Constant.Constants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoCreator {

    private static final int LOTTO_COUNT = 6;

    public static List<Integer> createLotto() {
        Set<Integer> nonDuplicateLottoNumbers = new HashSet<>();

        while (nonDuplicateLottoNumbers.size() < LOTTO_COUNT) {
            nonDuplicateLottoNumbers.add(createRandomNumber());
        }
        return new ArrayList<>(nonDuplicateLottoNumbers);
    }

    private static int createRandomNumber() {
        Random random = new Random();
        return random.nextInt(Constants.LOTTO_MAX_NUMBER) + 1;
    }

}
