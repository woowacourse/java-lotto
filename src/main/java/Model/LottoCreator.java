package Model;

import Constant.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoCreator {

    private static final int LOTTO_COUNT = 6;

    public static List<Integer> createLotto() {
        List<Integer> lottoNumber = new ArrayList<>();

        addRandomNumber(lottoNumber);
        return lottoNumber;
    }

    private static void addRandomNumber(List<Integer> lottoNumber) {

        for (int i = 0; i < LOTTO_COUNT; i++) {
            int randomNumber = createRandomNumber();
            randomNumber = createNonDuplicateNumber(lottoNumber, randomNumber);
            lottoNumber.add(randomNumber);
        }
    }

    private static int createNonDuplicateNumber(List<Integer> lottoNumber, int randomNumber) {
        if (lottoNumber.contains(randomNumber)) {
            randomNumber = createRandomNumber();
            createNonDuplicateNumber(lottoNumber, randomNumber);
        }
        return randomNumber;
    }

    private static int createRandomNumber() {
        Random random = new Random();
        return random.nextInt(Constants.LOTTO_MAX_NUMBER) + 1;
    }

}
