package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

public class ConsoleOutputView {
	public static final String PURCHASE_COMPLETE_MESSAGE = "%d개를 구매했습니다.\n";
	public static final String DELIMITER = ",";

	public static void printExceptionMessage(String exceptionMessage) {
		System.out.println(exceptionMessage);
	}

	public static void printPurchaseCompleteMessage(int numberOfLotto) {
		System.out.printf(PURCHASE_COMPLETE_MESSAGE, numberOfLotto);
	}

	public static void printPurchasedLotto(List<Lotto> lottos) {
		for (Lotto lotto : lottos) {
			String lottoNumber = lotto.getLottoNumbers().stream()
				.map(LottoNumber::getNumber)
				.map(Object::toString)
				.collect(Collectors.joining(DELIMITER));
			System.out.println(wrapSquareBracket(lottoNumber));
		}
	}

	private static String wrapSquareBracket(String lottoNumber) {
		return "[" + lottoNumber + "]";
	}
}
