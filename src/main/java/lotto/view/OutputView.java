package lotto.view;

import lotto.dto.LottoDto;
import lotto.dto.LottoDtos;

public class OutputView {
	public static void printWrongMoneyInput() {
		System.out.println("금액이 정수가 아닙니다. 다시 입력해주세요.");
	}

	public static void printLottoCount(int lottoCount) {
		System.out.println(lottoCount + "개를 구매했습니다.");
	}

	public static void printLottos(LottoDtos lottoDtos) {
		for (LottoDto lotto : lottoDtos.getLottoDtos()) {
			System.out.println(lotto.getLottoNumber());
		}
	}
}
