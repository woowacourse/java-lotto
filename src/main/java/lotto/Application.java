package lotto;

import lotto.domain.LottoGroup;
import lotto.util.LottoGenerator;
import lotto.view.OutputView;

public class Application {
  public static void main(String[] args) {
    // 인풋 뷰

    LottoGenerator lottoGenerator = new LottoGenerator();
    LottoGroup lottoGroup = new LottoGroup();
    lottoGroup.addLotto(lottoGenerator.generate());
    lottoGroup.addLotto(lottoGenerator.generate());
    lottoGroup.addLotto(lottoGenerator.generate());
    lottoGroup.addLotto(lottoGenerator.generate());
    OutputView.printBoughtLotto(lottoGroup);
    // 구입금액 뷰
    // 로또 생성 도메인

    // manager

    // 당첨 번호 뷰
    // 보너스 볼 뷰
    // 로또 결과 도메인

    // 아웃풋 뷰
  }
}
