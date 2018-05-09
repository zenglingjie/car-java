package qfx.model;

/**
 * Created by asus b250 on 2017/9/21.
 */
public class Settings {
    private int id;
    private String name;
    private int type;
    private int enable;

    @Override
    public String toString() {
        return "Settings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", enable=" + enable +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Settings(int id, int enable, int type, String name) {
        this.id = id;
        this.enable = enable;

        this.type = type;
        this.name = name;
    }

    public Settings(){

    }
}
