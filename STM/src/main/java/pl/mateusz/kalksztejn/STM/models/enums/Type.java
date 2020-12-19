package pl.mateusz.kalksztejn.STM.models.enums;

public enum Type {
    TASK("TASK"),
    BUG("BUG"),
    FEATURE("FEATURE");
    private  final String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
