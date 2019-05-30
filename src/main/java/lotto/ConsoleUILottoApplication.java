package lotto;

        import lotto.domain.*;
        import lotto.domain.Number;
        import lotto.utils.Converter;
        import lotto.view.InputView;
        import lotto.view.OutputView;

        import java.util.List;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        Price price = new Price(InputView.inputPrice());
        Lottos lottos = new Lottos(AutoLottoFactory.generateLottos(price.getCountOfLotto()));
        OutputView.printLottos(lottos);

        Lotto lotto = new Lotto(Converter.convertNumbers(InputView.inputWinningNumber()));
        WinningLotto winningLotto = new WinningLotto(lotto, Number.of(InputView.inputBonusBall()));
        List<Rank> ranks = lottos.match(winningLotto);
        LottoResult lottoResult = new LottoResult(ranks);
        OutputView.printStatistic(lottoResult.removeMissResult());
        OutputView.printYeild(lottoResult.findYield(price.getPrice()));
    }
}
