package ru.kpfu.itis.mustafin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.mustafin.models.Rent;
import ru.kpfu.itis.mustafin.repositories.RentRepository;
import ru.kpfu.itis.mustafin.services.RentService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    @Autowired
    RentRepository repository;

    @PostConstruct
    public void generateTestData() {
        save(new Rent("Петров Алексей Иванович", "75230434233", 530, "2017-05-27", "2017-05-28"));
    }

    @Override
    public List<Rent> getAll() {
        return repository.findAll();
    }

    @Override
    public Rent get(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Rent save(Rent rent) {
        return repository.saveAndFlush(rent);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void delete(Rent rent) {
        repository.delete(rent);
    }
}
