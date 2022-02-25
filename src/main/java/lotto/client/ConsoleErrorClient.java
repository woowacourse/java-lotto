package lotto.client;

public class ConsoleErrorClient implements ErrorClient {

    @Override
    public void error(String message) {
        System.out.print(message);
    }
}
