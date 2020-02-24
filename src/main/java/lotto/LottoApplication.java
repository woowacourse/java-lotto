package lotto;

import lotto.domain.LottoGame;

public class LottoApplication {
    public static void main(String[] args) {
        LottoGame lottoGame = new LottoGame();
        try {
            lottoGame.play();
        } catch (RuntimeException e) {
            System.out.println(String.format("다음과 같은 이유로 게임을 종료합니다. - %s", e.getMessage()));
            System.exit(-1);
        }
    }
}
