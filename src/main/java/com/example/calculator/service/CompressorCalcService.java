package com.example.calculator.service;

import com.example.calculator.model.Compressor;
import com.example.calculator.model.CompressorForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompressorCalcService {

    private long workHours;

    @Autowired
    CompressorService compressorService;


    //todo partInAll method
    public Double calculateEnergyToGJ(Compressor compressor){
        double energyGJ = compressor.getEnergy() * 0.278;
        Math.round(energyGJ);
        return energyGJ;
    }

    public Long calculatePotencial(Compressor compressor){
        double potencialEnergy = compressor.getEnergy()*0.55;
        return Math.round(potencialEnergy);
    }

    public Long calculatePotencialToGJ(Compressor compressor){
        double potencialEnergyGJ = compressor.getEnergy()*0.55 * 0.278;
        return Math.round(potencialEnergyGJ);
    }

    public Long calculatePotencialPower(Compressor compressor){
        double potencialPower = (this.calculatePotencial(compressor))/(12*workHours*4);
        return Math.round(potencialPower);
    }

    public long sumOfPower(CompressorService compressorService){
        Double sum =
                compressorService.findAll()
                        .stream()
                        .mapToDouble(s->s.getPower())
                        .sum();

        return Math.round(sum);
    }
    public long sumOfEnergy(CompressorService compressorService){
        Double sum =
                compressorService.findAll()
                        .stream()
                        .mapToDouble(s->s.getEnergy())
                        .sum();

        return Math.round(sum);
    }

    public long sumOfPotencial(CompressorService compressorService){
        Double sum =
                compressorService.findAll()
                .stream()
                .mapToDouble(s->s.getPotencialEnergy())
                .sum();

        return Math.round(sum);
    }

    public long sumOfPotencialGJ(CompressorService compressorService){
        Double sum =
                compressorService.findAll()
                        .stream()
                        .mapToDouble(s->s.getPotencialEnergyGJ())
                        .sum();

        return Math.round(sum);
    }

    public long sumOfPotencialPower(CompressorService compressorService){
        Double sum =
                compressorService.findAll()
                        .stream()
                        .mapToDouble(s->s.getPotencialPower())
                        .sum();

        return Math.round(sum);
    }





    //GETTERS AND SETTERS


    public long getWorkHours() {
        return workHours;
    }

    public void setWorkHours(long workHours) {
        this.workHours = workHours;
    }
}
