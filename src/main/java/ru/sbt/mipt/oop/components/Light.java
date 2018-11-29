package ru.sbt.mipt.oop.components;

public class Light extends Component {

    public Light(String idName, boolean isOn) {
        super(idName);
        this.setState(isOn);
    }

    @Override
    public void print() {
        if (this.isState()) {
            System.out.println("Light " + this.getId() + " is on!");
        } else {
            System.out.println("Light " + this.getId() + " is off!");
        }
    }
}
