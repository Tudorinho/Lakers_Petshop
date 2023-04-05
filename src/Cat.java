import java.util.Set;
import java.util.Map;

public class Cat extends Pet {
    private boolean isIndoor;
    private String furColor;

    public Cat(String name, int age, String breed, double weight, boolean isIndoor, String furColor) {
        super(name, age, breed, weight);
        this.isIndoor = isIndoor;
        this.furColor = furColor;
    }

    public boolean getIsIndoor() {
        return isIndoor;
    }

    public String getFurColor() {
        return furColor;
    }

    public void setIsIndoor(boolean isIndoor) {
        this.isIndoor = isIndoor;
    }

    public void setFurColor(String furColor) {
        this.furColor = furColor;
    }
}

