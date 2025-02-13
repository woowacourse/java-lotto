import controller.LottoController;
import factory.LottoFactory;

public class main {

    public static void main(String[] args) {
        LottoController controller = LottoFactory.create();
        controller.run();
    }
}
