package constant.pattern;

public enum InputPattern {

    INTEGER_PATTERN("\\d+"),
    ;

    private final String content;

    InputPattern(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
