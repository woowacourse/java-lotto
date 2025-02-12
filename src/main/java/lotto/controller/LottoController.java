package lotto.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private List<List<Integer>> lottos = new ArrayList<>();

    public void run() {
        int money = inputView.inputMoney();
        int lottoCounts = money / 1000;
        outputView.printCount(lottoCounts);
        generateLottos(lottoCounts);
        outputView.printLottos(lottos);
    }
    private List<List<Integer>> generateLottos(int lottoCounts) {
        for (int i = 0; i < lottoCounts; i ++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }

    private List<Integer> generateLotto() {
        List<Integer> lotto = new ArrayList<>();
        while(lotto.size() < 6) {
            int number = generateRandomNumber();
            checkDuplicate(lotto, number);
        }
        Collections.sort(lotto);
        return lotto;
    }

    private int generateRandomNumber() {
        return (int) ((Math.random() * 45) + 1);
    }

    private void checkDuplicate(List<Integer> lotto, int number) {
        if (!lotto.contains(number)) {
            lotto.add(number);
        }
    }
}
