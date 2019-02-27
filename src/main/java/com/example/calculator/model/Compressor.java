package com.example.calculator.model;

public class Compressor {

    private long id;
   // private static int nextId =1;
    private String name;
    private Double power;
    private Double load;
    private Double energy;
    private Long potencialEnergy;
    private Long potencialEnergyGJ;
    private Long potencialPower;


//GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getLoad() {
        return load;
    }

    public void setLoad(Double load) {
        this.load = load;
    }

    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getPotencialEnergy() {
        return potencialEnergy;
    }

    public void setPotencialEnergy(Long potencialEnergy) {
        this.potencialEnergy = potencialEnergy;
    }

    public Long getPotencialEnergyGJ() {
        return potencialEnergyGJ;
    }

    public void setPotencialEnergyGJ(Long potencialEnergyGJ) {
        this.potencialEnergyGJ = potencialEnergyGJ;
    }

    public Long getPotencialPower() {
        return potencialPower;
    }

    public void setPotencialPower(Long potencialPower) {
        this.potencialPower = potencialPower;
    }

    @Override
    public String toString() {
        return "Compressor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", power=" + power +
                ", load=" + load +
                ", energy=" + energy +
                '}';
    }
}
