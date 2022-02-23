import controller.LottoController;
import domain.LottoMachine;

public class Application {
	public static void main(String[] args) {
		// TODO: 프로그램 구현

		LottoController controller = new LottoController();
		controller.run();
	}
}