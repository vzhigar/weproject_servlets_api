package by.gsu.epamlab.beans;

import java.sql.Date;

public class Task {
    private int id;
    private String name;
    private Date date;
    private String description;
    private String fileName;

    public Task() {
    }

    public Task(int id, String name, Date date, String description, String fileName) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.fileName = fileName;
    }

    public Task(int id, String name, String date, String description, String fileName) {
        this.id = id;
        this.name = name;
        this.date = Date.valueOf(date);
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return name + " " + date + " " + description;
    }
}
