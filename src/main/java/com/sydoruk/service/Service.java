package com.sydoruk.service;

import com.sydoruk.model.Detail;
import com.sydoruk.repository.Repository;
import com.sydoruk.util.FactoryUtil;

import java.util.List;
import java.util.Optional;

public class Service {

    private static Service instance;
    private final Repository repository;

    private Service() {
        this.repository = Repository.getInstance();
    }

    public static synchronized Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public Detail createdAndSave() {
        final FactoryUtil factory = new FactoryUtil();
        final Detail detail = factory.robotsWork();
        Optional.of(detail).ifPresentOrElse(d -> repository.save(detail),
                () -> new IllegalArgumentException("Detail not save"));
        return detail;
    }

    public List getAllId(){
        return repository.getAllId();
    }

    public Detail findById(final String id) {
        return repository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getTotalNumberOfDetail(){
        return repository.numberOfDetail();
    }

    public String getTotalExtractedFuel(){
        return repository.getSumExtractedFuel();
    }

    public String getTotalUsedFuel(){
        return repository.getSumUsedFuel();
    }

    public String getTotalBrokenChips(){
        return repository.getSumBrokenChips();
    }

}