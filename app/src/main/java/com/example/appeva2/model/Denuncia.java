package com.example.appeva2.model;

public class Denuncia {

    private String id;
    private String title;
    private String direction;
    private String state;



    public Denuncia() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }
}
