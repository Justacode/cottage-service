package ru.kpfu.itis.mustafin.services;

import ru.kpfu.itis.mustafin.models.Cottage;

import java.util.List;

public interface CottageService {

    public List<Cottage> getAll();

    public Cottage get(Long id);

    public Cottage getByCottageNumber(Integer cottageNumber);

    public Cottage save(Cottage cottage);

    public void delete(Long id);

    public void delete(Cottage cottage);

}
