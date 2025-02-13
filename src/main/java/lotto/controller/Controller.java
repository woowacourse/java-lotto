package lotto.controller;

import java.util.List;

import lotto.domain.Amount;
import lotto.domain.Lotto;
import lotto.domain.Wallet;
import lotto.dto.MatchCountDto;

public class Controller {

    public void run() {
        Amount amount = new Amount(1000);
        Wallet wallet = new Wallet(amount);

        Lotto matchLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int bonus = 45;
        //매치 카운트
        List<MatchCountDto> matchCount = wallet.matchCount(matchLotto, bonus);
        // 계산 서비스로 전달
        // 매치 통계 한테 각 갯수받기

        // -> 전체 출력

        // 수익률 //

    }
}
