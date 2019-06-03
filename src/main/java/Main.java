import domain.*;
import exception.BonusBallDuplicationException;
import exception.LottoException;
import exception.NumberOutOfRangeException;
import util.LottoParser;
import util.MoneyParser;
import util.NonNegativeIntegerParse;
import util.WinningLottoParser;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final String EMPTY = "";

    public static void main(String[] args) {
        Money money = readMoney(EMPTY);

        int numLottos = readNumNonRandomLottos(money, EMPTY);
        LottoGroup lottoGroup = LottoSimulator.purchase(readNonRandomLottos(numLottos), money);
        OutputView.printLottoGroup(lottoGroup);

        WinningLotto winningLotto = readWinningLotto(EMPTY);
        RankAnalysis rankAnalysis = LottoSimulator.analyze(winningLotto, lottoGroup);
        OutputView.printRankAnalysis(rankAnalysis);
    }

    private static Money readMoney(String notifyingMessage) {
        String input = InputView.readMoney(notifyingMessage);
        try {
            return MoneyParser.parse(input);
        } catch (IllegalArgumentException e) {
            return readMoney(notifyingMessage);
        }
    }

    private static WinningLotto readWinningLotto(String notifyingMessage) {
        List<String> inputs = InputView.readWinningLotto(notifyingMessage);
        try {
            return WinningLottoParser.parse(inputs.get(0), inputs.get(1));
        } catch (BonusBallDuplicationException e) {
            return readWinningLotto(e.getMessage());
        } catch (LottoException e) {
            return readWinningLotto(e.getMessage());
        } catch (IllegalArgumentException e) {
            return readWinningLotto(e.getMessage());
        }
    }

    private static int readNumNonRandomLottos(Money money, String notifyingMessage) {
        String input = InputView.readNumNonRandomLottos(notifyingMessage);
        try {
            int numLotto = NonNegativeIntegerParse.parse(input);
            validateCanBuy(money, numLotto);
            return numLotto;
        } catch (IllegalArgumentException e) {
            return readNumNonRandomLottos(money, e.getMessage());
        }
    }

    private static void validateCanBuy(Money money, int numLotto) {
        Money totalPrice = (Lotto.PRICE.times(numLotto));
        if (money.lessThan(totalPrice)) {
            throw new IllegalArgumentException(
                    String.format("로또를 구매하기에 돈이 부족합니다. (%d 원 보유중)", money.toInt()));
        }
    }

    private static List<Lotto> readNonRandomLottos(int numLottos) {
        InputView.readNonRandomLottos();
        return Stream.generate(() -> readNonRandomLotto(EMPTY))
                .limit(numLottos)
                .collect(Collectors.toList());
    }

    private static Lotto readNonRandomLotto(String notifyingMessage) {
        String input = InputView.readNonRandomLotto(notifyingMessage);
        try {
            return LottoParser.parse(input);
        } catch (LottoException e) {
            return readNonRandomLotto(e.getMessage());
        } catch (NumberOutOfRangeException e) {
            return readNonRandomLotto(e.getMessage());
        } catch (IllegalArgumentException e) {
            return readNonRandomLotto(e.getMessage());
        }
    }
}
