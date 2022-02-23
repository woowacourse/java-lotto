package view;

import domain.Lotto;
import domain.Lottos;

import java.util.stream.Collectors;

public class OutputView {

	public static void printLottos(Lottos lottos) {
		System.out.println(lottos.getLottosSize() + "개를 구매했습니다.");
		for (Lotto lotto : lottos.getLottos()) {
			printLotto(lotto);
		}
	}

	private static void printLotto(Lotto lotto) {
		System.out.println(
			"[" + lotto.getNumbers().stream().map(String::valueOf).collect(Collectors.joining(", ")) + "]");
	}
}
