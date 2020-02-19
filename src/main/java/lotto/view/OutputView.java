package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class OutputView {
	public static void printWrongMoneyInput() {
		System.out.println("금액이 정수가 아닙니다. 다시 입력해주세요.");
	}

	public static void printLottoCount(int lottoCount) {
		System.out.println(lottoCount + "개를 구매했습니다.");
	}

	public static void printLottos(Lottos lottos) {
		for (Lotto lotto : lottos.getLottos()) {
			System.out.println(lotto.getLottoNumber());
		}
	}
}
