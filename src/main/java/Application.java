import lotto.controller.LottoController;
import lotto.view.OutputView;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/18
 */
public class Application {
	public static void main(String[] args) {
		try {
			LottoController.run();
		} catch (Exception e) {
			OutputView.printExceptionMessage(e);
		}
	}
}
