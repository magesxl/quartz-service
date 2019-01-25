package com.example.pay.design.decorator;

public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        super.draw();
        setDraw(decoratedShape);
        setDraw1(decoratedShape);
    }

    private void setDraw(Shape decoratedShape) {
        System.out.println("我是红色");
    }
    private void setDraw1(Shape decoratedShape) {
        System.out.println("我是红色1");
    }
}
