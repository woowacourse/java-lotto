package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class LottoCreator {

    private static final int LOTTO_COUNT = 6;
    private static final int LOTTO_MAX_NUMBER = 45;


    public static List<Integer> createLotto() {
        List<Integer> lottoNumber = new ArrayList<>();

        addRandomNumber(lottoNumber);
        lottoNumber.sort(Comparator.naturalOrder());
        return lottoNumber;
    }

    private static void addRandomNumber(List<Integer> lottoNumber) {
        int count = LOTTO_COUNT;
        Random random = new Random();

        while (count > 0) {
            int randomNumber = random.nextInt(LOTTO_MAX_NUMBER) + 1;
            if (lottoNumber.contains(randomNumber)) {
                continue;
            }
            lottoNumber.add(randomNumber);
            count--;
        }
    }
}
