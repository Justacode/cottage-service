package ru.kpfu.itis.mustafin.services;

import ru.kpfu.itis.mustafin.models.Rent;

import java.util.List;

public interface RentService {

    public List<Rent> getAll();

    public Rent get(Long id);

    public Rent save(Rent rent);

    public void delete(Long id);

    public void delete(Rent rent);
}
