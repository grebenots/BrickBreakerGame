package com.stoneberg.brickbreaker;

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

    public T getX() {
        return x;
    }
    public T getY() {
        return y;
    }
}