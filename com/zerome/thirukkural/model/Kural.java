package thirukkural.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Kural{
    @JsonProperty("Number")
    private int number;
    @JsonProperty("Line1")
    private String line1;
    @JsonProperty("Line2")
    private String line2;
    @JsonProperty("Translation")
    private String translation;
    @JsonProperty("mv")
    private String definition1;
    @JsonProperty("sp")
    private String definition2;
    @JsonProperty("mk")
    private String definition3;
    private String explanation;
    private String couplet;
    private String transliteration1;
    private String transliteration2;

    public int getNumber() {
        return number;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getTranslation() {
        return translation;
    }

    public String getDefinition1() {
        return definition1;
    }

    public String getDefinition2() {
        return definition2;
    }

    public String getDefinition3() {
        return definition3;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getCouplet() {
        return couplet;
    }

    public String getTransliteration1() {
        return transliteration1;
    }

    public String getTransliteration2() {
        return transliteration2;
    }

}


