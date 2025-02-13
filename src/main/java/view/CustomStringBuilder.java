package view;

public class CustomStringBuilder {
    private final StringBuilder stringBuilder = new StringBuilder();

    public void appendLine(String content) {
        stringBuilder.append(content).append('\n');
    }

    public void print() {
        System.out.println(stringBuilder);
    }
}
