package lotto;

import lotto.controller.LottoGame;
import lotto.domain.NonPlayerCharacter;
import lotto.domain.Player;

public class LottoApplication {
    public static void main(String[] args) {
        NonPlayerCharacter nonPlayerCharacter = new NonPlayerCharacter();
        Player player = new Player();
        LottoGame lottoGame = new LottoGame(nonPlayerCharacter, player);
        try {
            lottoGame.play();
        } catch (RuntimeException e) {
            System.out.println(String.format("다음과 같은 이유로 게임을 종료합니다. - %s", e.getMessage()));
            System.exit(-1);
        }
    }
}
