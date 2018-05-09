package qfx.model;

import java.util.Date;

/**
 * Created by asus b250 on 2017/9/13.
 */
public class Log {
    private int id;

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String text;
    private String updateTime;

    public int getId() {
        return id;
    }

    public Log(int id, String updateTime, String text, String name) {
        this.id = id;
        this.updateTime = updateTime;
        this.text = text;
        this.name = name;
    }

    public Log(){

    }
}
