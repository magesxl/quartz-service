package com.example.pay.design.builder;


public class Computer {
    private String cpu;//必须
    private String ram;//必须
    private int usbCount;//可选
    private String keyboard;//可选
    private String display;//可选

    private Computer(String cpu, String ram, int usbCount, String keyboard, String display) {
        this.cpu = cpu;
        this.ram = ram;
        this.usbCount = usbCount;
        this.keyboard = keyboard;
        this.display = display;
    }

    public static Computer.Builder builder() {
        return new Computer.Builder();
    }

    public static class Builder {
        private String cpu;//必须
        private String ram;//必须
        private int usbCount;//可选
        private String keyboard;//可选
        private String display;//可选

        public Computer.Builder cpu(String cpu) {
            this.cpu = cpu;
            return this;

        }

        public Computer.Builder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public Computer.Builder usbCount(int usbCount) {
            this.usbCount = usbCount;
            return this;
        }

        public Computer.Builder keyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Computer.Builder display(String display) {
            this.display = display;
            return this;
        }

        public Computer build() {
            return new Computer(this.cpu, this.ram, this.usbCount, this.keyboard, this.display);
        }
    }
}
