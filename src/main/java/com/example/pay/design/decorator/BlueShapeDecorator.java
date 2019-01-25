package com.example.pay.design.decorator;

public class BlueShapeDecorator extends ShapeDecorator {
    public BlueShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        super.draw();
        setDraw(decoratedShape);
    }

    private void setDraw(Shape decoratedShape) {
        System.out.println("我是蓝色");
    }
}
