package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static final String DELIMITER = ",";

    public static Lotto parseWinningLotto(String numbers) {
        List<Number> userLotto = new ArrayList<>();
        String[] winnerNumbers = numbers.split(DELIMITER);

        for (String number : winnerNumbers) {
            userLotto.add(Number.of(Integer.parseInt(number)));
        }

        return new Lotto(userLotto);
    }

    public static List<Lotto> parseLotto(String[] numbers) {
        List<Lotto> userLotto = new ArrayList<>();

        for (String number : numbers) {
            String[] oneNumbers = number.split(DELIMITER);
            userLotto.add(new Lotto(oneNumbers));
        }

        return userLotto;
    }

    public static String[] parseLottoStrings(String userLottoString) {
        String temp = userLottoString
                .replace("[[" , "")
                .replace("]]", "")
                .replace(", ",",");
        return temp.split("\\],\\[");
    }
}
