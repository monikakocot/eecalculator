package com.example.calculator.service;


import com.example.calculator.model.Compressor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InMemoryCompressorService implements CompressorService {

    static Map<Long, Compressor> compressorssDB = new HashMap<>();

    @Override
    public List<Compressor> findAll() {
        return new ArrayList<>(compressorssDB.values());
    }

    @Override
    public void saveAll(List<Compressor> compressors) {
        long nextId = getNextId();
        for (Compressor compressor : compressors) {
            if (compressor.getId() == 0) {
                compressor.setId(nextId++);
            }
        }

        Map<Long, Compressor> compressorMap = compressors.stream()
                .collect(Collectors.toMap(Compressor::getId, Function.identity()));

        compressorssDB.putAll(compressorMap);
    }

    private Long getNextId() {
        return compressorssDB.keySet()
                .stream()
                .mapToLong(value -> value)
                .max()
                .orElse(0) + 1;
    }
}
