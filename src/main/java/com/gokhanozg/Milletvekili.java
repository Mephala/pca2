package com.gokhanozg;

/**
 * Created by gokhanozg on 3/10/17.
 */
public class Milletvekili {
    private String name;
    private String parti;
    private String il;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Milletvekili that = (Milletvekili) o;

        if (!name.equals(that.name)) return false;
        return il.equals(that.il);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + il.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Milletvekili{" +
                "name='" + name + '\'' +
                ", parti='" + parti + '\'' +
                ", il='" + il + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParti() {
        return parti;
    }

    public void setParti(String parti) {
        this.parti = parti;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }
}
