package com.example.calculator.service;

import com.example.calculator.model.Compressor;

import java.util.List;

public interface CompressorService {

    List<Compressor> findAll();

    void saveAll(List<Compressor> compressors);
}
