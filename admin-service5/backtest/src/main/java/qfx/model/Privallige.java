package qfx.model;

/**
 * Created by asus b250 on 2017/9/13.
 */
public class Privallige {
    private int id;
    private int  value;

    @Override
    public String toString() {
        return "Privallige{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Privallige(int id, int value) {

        this.id = id;
        this.value = value;
    }

    public Privallige(){

    }
}
