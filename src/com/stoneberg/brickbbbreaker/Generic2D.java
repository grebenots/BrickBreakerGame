package com.stoneberg.brickbbbreaker;

public class Generic2D<T> {
    private T x;
    private T y;

    public Generic2D(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public void set(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public void setX(T x) {
        this.x = x;
    }

    public void setY(T y) {
        this.y = y;
    }

    public Generic2D<T> get() {
        return this;
    }

    public T getX() {
        return x;
    }
    public T getY() {
        return y;
    }
}