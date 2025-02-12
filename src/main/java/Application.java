import controller.Controller;
import service.Service;

public class Application {

    public static void main(String[] args) {
        Service service = new Service();
        Controller controller = new Controller(service);
        controller.run();
    }
}
