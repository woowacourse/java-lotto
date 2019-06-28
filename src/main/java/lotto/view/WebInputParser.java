package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebInputParser {
    private static final String lottoDelimiter = "\r\n";
    private static final String lottoNumberDelimiter = ",";

    public static Money getMoney(String moneyInput) {
        return new Money(Integer.parseInt(moneyInput));
    }

    public static int getNumberOfManualLotto(Money money, String numberOfManualLottoInput) {
        int numberOfManualLotto = Integer.parseInt(numberOfManualLottoInput);
        money.validateNumberOfManualLotto(numberOfManualLotto);
        return numberOfManualLotto;
    }

    public static LottoTickets getLottoTickets(Money money, int numberOfManualLotto, String manualLottoInput) {
        if (numberOfManualLotto == 0) {
            return LottoMachine.generateTickets(money);
        }
        List<Lotto> manualLotto = getManualLotto(manualLottoInput.split(lottoDelimiter));
        validateManualLotto(numberOfManualLotto, manualLotto);
        return LottoMachine.generateTickets(money, manualLotto);
    }

    private static List<Lotto> getManualLotto(String[] manualLottoInput) {
        List<Lotto> manualLotto = new ArrayList<>();
        for (String s : manualLottoInput) {
            List<String> lottoNumbersInput = Arrays.asList(s.split(lottoNumberDelimiter));
            manualLotto.add(ManualLottoGenerator.generate(lottoNumbersInput));
        }
        return manualLotto;
    }

    private static void validateManualLotto(int numberOfManualLotto, List<Lotto> manualLotto) {
        if (manualLotto.size() != numberOfManualLotto) {
            throw new IllegalArgumentException("입력된 수동 로또의 개수가 맞지 않습니다.");
        }
    }

    public static WinningLotto getWinningLotto(String winningNumbersInput, String bonusBallInput) {
        Lotto lotto = ManualLottoGenerator.generate(Arrays.asList(winningNumbersInput.split(lottoNumberDelimiter)));
        LottoNumber bonusBall = new LottoNumber(Integer.parseInt(bonusBallInput));
        return new WinningLotto(lotto, bonusBall);
    }
}
