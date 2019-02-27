package com.example.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class CompressorForm {

    private List<Compressor> compressors;

    public CompressorForm() {
        this.compressors = new ArrayList<>();
    }

    public CompressorForm(List<Compressor> compressors) {
        this.compressors = compressors;
    }

    public void addCompressor(Compressor compressor){
        this.compressors.add(compressor);
    }

    //GETTERS I SETTERS

    public List<Compressor> getCompressors() {
        return compressors;
    }

    public void setCompressors(List<Compressor> compressors) {
        this.compressors = compressors;
    }

    @Override
    public String toString() {
        return "CompressorForm{" +
                "compressors=" + compressors +
                '}';
    }
}
