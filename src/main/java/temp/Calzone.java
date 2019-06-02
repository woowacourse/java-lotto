package temp;

public class Calzone extends Pizza {
    private final boolean sauce;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauce = false;

        public Builder(boolean sauce) {
            this.sauce = sauce;
        }

        public Builder sauceInside() {
            sauce = true;
            return this;
        }


        @Override
        public Calzone build() {
            return new Calzone(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private Calzone(Builder builder) {
        super(builder);
        sauce = builder.sauce;
    }
}
