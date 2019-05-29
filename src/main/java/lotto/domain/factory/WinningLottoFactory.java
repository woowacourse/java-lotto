package lotto.domain.factory;

import lotto.domain.LottoTicket;
import lotto.domain.WinningLotto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoFactory {
    private static final String DELIMITER = ",";

    public static WinningLotto create(final String input, int bonusBall) {
        List<String> list = Arrays.asList(input.split(DELIMITER));
        LottoTicket lottoTicket = new LottoTicket(list.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
        return new WinningLotto(lottoTicket, bonusBall);
    }
}
