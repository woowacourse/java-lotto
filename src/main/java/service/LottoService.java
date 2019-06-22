package service;

import model.*;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoService {
    public static LottoVO purchase(int round, int investment, Stream<String> manualNumbers) {
        final WinningNumbers winningNumbers = WinningNumbersFactory.of(round);
        final List<Lotto> manualLottos = manualNumbers.map(Lotto::new)
                                                    .collect(Collectors.toList());
        final Lottos lottos = new Lottos(
                manualLottos,
                new LottoPurchaseAmount(new Money(investment), manualLottos.size())
        );
        LottoHistoryDAO.writeHistory(encodeLottos(lottos), round);
        return new LottoVO(round, winningNumbers, lottos, lottos.getResult(winningNumbers));
    }

    private static String encodeLottos(Lottos lottos) {
        return lottos.getLottos().stream()
                .map(Lotto::getNumbers)
                .map(List::stream)
                .map(x -> x.map(LottoNumber::toString))
                .map(x -> x.reduce("", (a, b) -> a + "," + b))
                .reduce("", (a, b) -> a + "/" + b)
                .replace("/,", "/");
    }

    public static LottoVO history(String date) throws SQLException, IllegalArgumentException {
        final LottoHistoryVO history = LottoHistoryDAO.getHistoryByDate(date);
        if (history.round() == 0) {
            throw new IllegalArgumentException();
        }
        final WinningNumbers winningNumbers = WinningNumbersFactory.of(history.round());
        return new LottoVO(
                history.round(),
                winningNumbers,
                decodeLottos(history.encodedLottos()),
                decodeLottos(history.encodedLottos()).getResult(winningNumbers)
        );
    }

    private static Lottos decodeLottos(String encoded) {
        return new Lottos(
                Stream.of(encoded.split("/"))
                    .filter(x -> x.trim().length() != 0)
                    .map(l -> new Lotto(l))
                    .collect(Collectors.toList())
        );
    }
}