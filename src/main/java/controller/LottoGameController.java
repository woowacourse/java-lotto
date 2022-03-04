package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGameController {

    public void run() {
        LottoDispenser lottoDispenser = buildDispenserFromInputMoney();
        List<Lotto> lottos = buyLottos(lottoDispenser);
        OutputView.printLottos(lottos);

        WinningLotto winningLotto = getWinningLottoFromInput();
        PrizeMachine prizeMachine = new PrizeMachine(lottos);
        Map<Prize, Integer> prizeResult = prizeMachine.calculatePrizeResult(winningLotto);

        OutputView.printFinalStatistic(prizeResult);
        OutputView.printEarningRate(prizeMachine.calculateEarningRate());
    }

    private List<Lotto> buyLottos(LottoDispenser lottoDispenser) {
        List<Lotto> manualLottos = buyManualLottos(lottoDispenser);
        List<Lotto> autoLottos = buyAutoLottos(lottoDispenser);
        OutputView.printLottosCount(manualLottos.size(), autoLottos.size());
        return Stream.of(manualLottos, autoLottos)
                .flatMap(lottos -> lottos.stream())
                .collect(Collectors.toList());
    }

    private LottoDispenser buildDispenserFromInputMoney() {
        try {
            return new LottoDispenser(InputView.inputMoney());
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return buildDispenserFromInputMoney();
        }
    }

    private int getManualLottoCountFromInput(LottoDispenser lottoDispenser) {
        try {
            int manualLottosCount = InputView.inputManualLottosCount();
            lottoDispenser.checkEnoughMoneyRemain(manualLottosCount);
            return manualLottosCount;
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getManualLottoCountFromInput(lottoDispenser);
        }
    }

    private List<Lotto> buyManualLottos(LottoDispenser lottoDispenser) {
        int manualLottosCount = getManualLottoCountFromInput(lottoDispenser);
        InputView.showMessageInputLottoNumbers();

        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualLottosCount; i++) {
            manualLottos.add(buyLottoFromInput(lottoDispenser));
        }
        return manualLottos;
    }

    private List<Lotto> buyAutoLottos(LottoDispenser lottoDispenser) {
        return lottoDispenser.buyAutoLottos();
    }

    private Lotto buyLottoFromInput(LottoDispenser lottoDispenser) {
        try {
            return lottoDispenser.buyManualLotto(InputView.inputLottoNumbers());
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return buyLottoFromInput(lottoDispenser);
        }
    }

    private WinningLotto getWinningLottoFromInput() {
        try {
            Lotto lotto  = getLottoFromInput();
            int bonus = getBonusNumberFromInput();
            return new WinningLotto(lotto, bonus);
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getWinningLottoFromInput();
        }
    }

    private Lotto getLottoFromInput() {
        try {
            Lotto lotto  = new Lotto(InputView.inputWinningLottoNumbers());
            return lotto;
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getLottoFromInput();
        }
    }

    private int getBonusNumberFromInput() {
        try {
            int bonus = (InputView.inputBonus());
            return bonus;
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getBonusNumberFromInput();
        }
    }

}
