package lotto.controller;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.RandomNumberGenerator;
import lotto.view.InputView;

import java.util.HashSet;
import java.util.List;

public class LottoMachine {
    private final Lottos lottos;
    private final InputView inputView;

    public LottoMachine(final InputView inputView) {
        this.lottos = new Lottos();
        this.inputView = inputView;
    }

    public void run() {
        String money = inputView.readLine();
        issueLottoTickets(money);
    }

    private void issueLottoTickets(final String money) {
        int buyingAmount = parseInt(money);
        if (buyingAmount % Lottos.UNIT_PRICE != 0) {
            throw new IllegalArgumentException("천원 단위로 입력해 주세요.");
        }
        int count = buyingAmount / Lottos.UNIT_PRICE;

        // count만큼 로또 발행하고 lottos에 추가하기
        for (int i = 0; i < count; i++) {
            addLotto();
        }
    }

    private void addLotto() {
        List<Integer> randomNumbers = RandomNumberGenerator.generate();
        while(hasDuplication(randomNumbers)) {
            randomNumbers = RandomNumberGenerator.generate();
        }
        lottos.add(new Lotto(randomNumbers));
    }

    private boolean hasDuplication(List<Integer> randomNumbers) {
        HashSet<Integer> uniqueNumbers = new HashSet<>(randomNumbers);
        if (uniqueNumbers.size() != randomNumbers.size()) {
            return true;
        }
        return false;
    }

    private int parseInt(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 금액 형식입니다.");
        }
    }
}
