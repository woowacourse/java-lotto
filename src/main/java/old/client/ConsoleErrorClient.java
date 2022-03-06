package old.client;

public class ConsoleErrorClient implements ErrorClient {

    private ConsoleErrorClient() {
    }

    private static class ConsoleErrorClientHelper {
        private static final ErrorClient INSTANCE = new ConsoleErrorClient();
    }

    public static ErrorClient getInstance() {
        return ConsoleErrorClientHelper.INSTANCE;
    }

    @Override
    public void error(String message) {
        System.out.print(message);
    }
}
