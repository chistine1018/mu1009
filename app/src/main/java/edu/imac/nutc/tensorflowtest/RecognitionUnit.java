package edu.imac.nutc.tensorflowtest;

/**
 * Created by isa on 3/13/18.
 */

public class RecognitionUnit {
    private  float value = 0;
    private  String label = "";

    public float getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
