package duckservice.duckservice.model;

public enum DuckType {
    MALLARD, REDHEAD, RUBBER_DUCK, DECOY_DUCK;

    @Override
    public String toString() {
        return switch (this) {
            case MALLARD -> "Mallard";
            case REDHEAD -> "Redhead";
            case DECOY_DUCK -> "Decoy";
            case RUBBER_DUCK -> "Rubber";
            default -> "unspecified";
        };
    }


    public static DuckType toEnum(String value) {
        return switch (value.toLowerCase()) {
            case "mallard" -> DuckType.MALLARD;
            case "redhead" -> DuckType.REDHEAD;
            case "decoy" -> DuckType.DECOY_DUCK;
            case "rubber" -> DuckType.RUBBER_DUCK;
            default -> null;
        };
    }
}
