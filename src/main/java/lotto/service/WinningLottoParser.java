package lotto.service;

import lotto.domain.*;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WinningLottoParser {
    private static final Pattern WINNING_LOTTO_PATTERN = Pattern.compile("^[0-9]+(,\\s[0-9]+){5}$");
    private static final Pattern BONUS_NO_PATTERN = Pattern.compile("^[0-9]+$");

    public static WinningLotto parse(String inputWinningLotto, String inputBonusNo) {
        if (!WINNING_LOTTO_PATTERN.matcher(inputWinningLotto).matches()) {
            throw new IllegalArgumentException("당첨번호는 6개의 숫자와 쉼표로 구성됩니다.");
        }
        if (!BONUS_NO_PATTERN.matcher(inputBonusNo).matches()) {
            throw new IllegalArgumentException("보너스번호는 숫자로 구성됩니다.");
        }
        return new WinningLotto(LottoFactory.create(parseLottoGenerator(inputWinningLotto)),
                LottoNo.of(Integer.parseInt(inputBonusNo)));
    }

    public static WinningLotto parse(List<Integer> winningLotto, int bonusNo) {
        return new WinningLotto(Lotto.of(winningLotto), LottoNo.of(bonusNo));
    }

    private static LottoNoGenerator parseLottoGenerator(String manualLotto) {
        try {
            return new LottoNoManualGenerator(
                    Arrays.stream(manualLotto.split(", "))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
            );
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨번호는 6개와 쉼표로 구성되어야 합니다.");
        }
    }
}
