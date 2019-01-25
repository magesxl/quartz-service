package com.example.pay.design.decorator;

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Shape circular = new Circular();
        RedShapeDecorator redShapeDecorator = new RedShapeDecorator(circular);
        redShapeDecorator.draw();

        Shape rectangle = new Rectangle();
        RedShapeDecorator redShapeDecorator2 = new RedShapeDecorator(rectangle);
        redShapeDecorator2.draw();

        BlueShapeDecorator blueShapeDecorator = new BlueShapeDecorator(rectangle);
        blueShapeDecorator.draw();
    }
}
