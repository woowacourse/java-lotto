import lotto.controller.LottoController;
import lotto.view.OutputView;

/**
 * 로또 어플리케이션의 메인, try-catch로 예외처리
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/18
 */
public class Application {
	public static void main(String[] args) {
		try {
			LottoController.run();
		} catch (RuntimeException e) {
			OutputView.printExceptionMessage(e);
		}
	}
}
