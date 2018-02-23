package pro.anuj.citrine.persistence;

public enum Criterion {

    Like("like"),
    Equals("eq"),
    NotEquals("neq"),
    LessThan("lt"),
    GreaterThan("gt"),
    GreaterThanEqualTo("gte"),
    LessThanEqualTo("lte"),
    And("and"),
    Or("or");


    private String apiVerb;

    Criterion(String apiVerb) {
        this.apiVerb = apiVerb;
    }

    public String getApiVerb() {
        return apiVerb;
    }


    @Override
    public String toString() {
        return apiVerb;
    }
}
