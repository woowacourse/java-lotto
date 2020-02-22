package domain.lottonumber;

import domain.lottoresult.LottoResult;
import domain.lottoresult.LottoWinner;

import java.util.ArrayList;
import java.util.List;

/**
 * LottoGame 클래스는 LottoNumbers(6개의 로또번호 세트)의 목록을 가지는 클래스이다.
 * LottoNumbers를 추가하는 메서드와 결과값 생성 메서드를 가진다.
 */
public class LottoGame {
    List<LottoTicket> lottoNumbers = new ArrayList<>();

    public void add(LottoTicket lotto) {
        lottoNumbers.add(lotto);
    }

    public void makeResult(LottoResult lottoResult, LottoWinner lottoWinner) {
        for (LottoTicket lotto : lottoNumbers) {
            lottoResult.add(lottoWinner.createRank(lotto));
        }
    }
}
