import controller.LottoController;
import service.LottoService;

public class Application {
	public static void main(String[] args) {
		LottoController controller = new LottoController(new LottoService());
		controller.run();
	}
}