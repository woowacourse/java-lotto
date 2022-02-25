import controller.LottoController;
import service.LottoMachine;

public class Application {
	public static void main(String[] args) {
		// TODO: 프로그램 구현
		LottoController controller = new LottoController(new LottoMachine());
		controller.run();
	}
}