package components;

import java2d.game.GameComponent;
import java2d.game.Maths;

public class HP extends GameComponent {

    public ValueChanged valueChanged;

    private int value;

    private int maxValue = 100;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        int pv = this.value;

        if (value != pv) {
            this.value = Maths.clamp(value, 0, maxValue);
            raiseValueChanged(this.value, pv);
        }
    }

    private void raiseValueChanged(int newValue, int oldValue) {
        if (valueChanged != null)
            valueChanged.changed(newValue, oldValue);
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public float getRatio() {
        return maxValue == 0 ? 1f : ((float) value) / maxValue;
    }

    public void reset() {
        value = maxValue;
    }

    @FunctionalInterface
    public interface ValueChanged {

        void changed(int newValue, int oldValue);
    }
}
