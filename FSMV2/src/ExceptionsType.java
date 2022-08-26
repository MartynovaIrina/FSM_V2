public enum ExceptionsType {
    TRANSITION_ERROR("Error. No transition."),
    WRONG_SYMBOL("Wrong symbol.");

    private String errorString;
    private ExceptionsType(String errorString) {
        this.errorString = errorString;
    }
    public String getErrorString() {
        return errorString;
    }
}
