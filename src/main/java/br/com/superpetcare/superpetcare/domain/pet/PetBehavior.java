package br.com.superpetcare.superpetcare.domain.pet;

public enum PetBehavior {
    DOCILE("Docile Behavior"),
    AGGRESSIVE("Aggressive  Behavior");

    private final String displayName;

    PetBehavior(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
