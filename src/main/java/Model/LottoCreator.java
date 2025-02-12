package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class LottoCreator {

    private static final int LOTTO_COUNT = 6;

    public static List<Integer> createLotto() {
        Random random = new Random();
        List<Integer> lottoNumber = new ArrayList<>();
        int count = LOTTO_COUNT;
        while (count > 0) {
            int randomNumber = random.nextInt(45) + 1;
            if (lottoNumber.contains(randomNumber)) {
                continue;
            }
            lottoNumber.add(randomNumber);
            count--;
        }
        lottoNumber.sort(Comparator.naturalOrder());
        return lottoNumber;
    }
}
