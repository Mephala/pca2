package com.gokhanozg;

import java.util.List;

/**
 * Created by gokhanozg on 3/10/17.
 */
public class Person {
    private String name;
    private List<String> parties;
    private String il;
    private List<String> companies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person that = (Person) o;

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
        return "Person{" +
                "name='" + name + '\'' +
                ", parties=" + parties +
                ", il='" + il + '\'' +
                ", companies=" + companies +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getParties() {
        return parties;
    }

    public void setParties(List<String> parties) {
        this.parties = parties;
    }

    public List<String> getCompanies() {
        return companies;
    }

    public void setCompanies(List<String> companies) {
        this.companies = companies;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }
}
