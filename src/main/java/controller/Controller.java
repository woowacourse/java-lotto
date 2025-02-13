package controller;

import constant.LottoConstants;
import domain.Lotto;
import domain.Lottos;
import domain.PrizeResult;
import domain.Rank;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import service.LottoMaker;
import service.parser.BonusNumberParser;
import service.parser.MoneyParser;
import service.parser.WinningNumberParser;
import view.InputView;
import view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoMaker lottoMaker;

    public Controller(InputView inputView, OutputView outputView, LottoMaker lottoMaker) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoMaker = lottoMaker;
    }

    public void start() {
        int lottoCount = inputLottoMoney();

        Lottos lottos = buyLotto(lottoCount);
        outputView.displayLottoNumbers(lottos);

        List<Integer> winningNumbers = WinningNumberParser.parseWinningNumbers(inputView.askWinningNumber());
        int bonusNumber = BonusNumberParser.parseBonusNumber(winningNumbers, inputView.askBonusNumber());

        EnumMap<Rank, Integer> map = new EnumMap<>(Rank.class);
        for (int idx = 0; idx < lottos.size(); idx++) {
            Set<Integer> set = new HashSet<>(lottos.getLottoByIndex(idx).getNumbers());
            set.addAll(winningNumbers);

            int judge = 12 - set.size();
            if (judge == 3) {
                map.put(Rank.RANK5, map.getOrDefault(Rank.RANK5, 0) + 1);
            } else if (judge == 4) {
                map.put(Rank.RANK4, map.getOrDefault(Rank.RANK4, 0) + 1);
            } else if (judge == 5) {
                if (lottos.getLottoByIndex(idx).getNumbers().contains(bonusNumber)) {
                    map.put(Rank.RANK2, map.getOrDefault(Rank.RANK2, 0) + 1);
                } else {
                    map.put(Rank.RANK3, map.getOrDefault(Rank.RANK3, 0) + 1);
                }
            } else if (judge == 6) {
                map.put(Rank.RANK1, map.getOrDefault(Rank.RANK1, 0) + 1);
            }
        }

        // TODO: 통계 결과 출력
    }

    private Lottos buyLotto(int count) {
        ArrayList<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(lottoMaker.createLotto());
        }

        return new Lottos(lottos);
    }

    private int inputLottoMoney() {
        while (true) {
            try {
                String response = inputView.askForNormal();
                return MoneyParser.parseLottoCount(response);
            } catch (IllegalArgumentException e) {
                outputView.displayErrorMessage(e.getMessage());
            }
        }
    }
}