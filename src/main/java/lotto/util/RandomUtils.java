package lotto.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * RandomUtils 클래스
 *
 * @author 히히, 토니
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class RandomUtils {
    public static List<Integer> getRandomIntList(int lottoNumberAmount, int firstNumber, int lastNumber) {
        List<Integer> possibleNumbers = new ArrayList<>();

        for (int i = 1; i <= lastNumber; i++) {
            possibleNumbers.add(i);
        }
        Collections.shuffle(possibleNumbers);
        return possibleNumbers.subList(0, 6);
    }

    private static int getRandomInt(int startNumber, int lastNumber) {
        Random random = new Random();
        return random.nextInt(lastNumber - startNumber + 1) + startNumber;
    }
}
