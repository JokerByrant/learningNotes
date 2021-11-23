package com.sxh.ioc.bean;

/**
 * @author sxh
 * @date 2021/11/23
 */
public class Cat {
    private String variety;

    private String color;

    private int gender;

    public Cat(String variety, String color, int gender) {
        this.variety = variety;
        this.color = color;
        this.gender = gender;
    }

    public Cat() {
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Cat{" + "variety='" + variety + '\'' + ", color='" + color + '\'' + ", gender=" + gender + '}';
    }
}
