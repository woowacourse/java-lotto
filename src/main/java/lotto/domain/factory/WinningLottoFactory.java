package lotto.domain.factory;

import lotto.domain.LottoTicket;
import lotto.domain.WinningLotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningLottoFactory {
    private static final String DELIMITER = ",";

    public static WinningLotto create(final String input, int bonusBall) {
        List<String> list = Arrays.asList(input.split(DELIMITER));
        List<Integer> temp = new ArrayList<>();
        for (String s : list) {
            temp.add(Integer.parseInt(s));
        }
        return new WinningLotto(new LottoTicket(temp), bonusBall);
    }
}
