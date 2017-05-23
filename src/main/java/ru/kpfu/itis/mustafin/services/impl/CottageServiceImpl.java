package ru.kpfu.itis.mustafin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.mustafin.models.Cottage;
import ru.kpfu.itis.mustafin.repositories.CottageRepository;
import ru.kpfu.itis.mustafin.services.CottageService;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CottageServiceImpl implements CottageService {

    @Autowired
    CottageRepository repository;

    @PostConstruct
    public void generateTestData() {
        String yes = "имеется";
        String no = "не имеется";
        String permit = "разрешено";
        String deny = "запрещено";
        save(new Cottage(132, 12, no, yes, yes, deny, 9000));
        save(new Cottage(864, 18, yes, yes, no, permit, 14500));
        save(new Cottage(742, 23, yes, yes, yes, permit, 19000));
        save(new Cottage(421, 11, no, yes, yes, permit, 8000));
        save(new Cottage(853, 16, yes, no, yes, deny, 13000));
        save(new Cottage(643, 14, yes, yes, no, permit, 11000));
        save(new Cottage(254, 21, yes, no, yes, deny, 14000));
        save(new Cottage(753, 32, yes, yes, no, deny, 32000));
        save(new Cottage(530, 7, no, no, no, permit, 5000));
    }

    @Override
    public List<Cottage> getAll() {
        return repository.findAll();
    }

    @Override
    public Cottage get(Long id) {
        return repository.getOne(id);
    }

    @Override
    public Cottage getByCottageNumber(Integer cottageNumber) {
        return repository.findByCottageNumber(cottageNumber);
    }

    @Override
    public Cottage save(Cottage cottage) {
        return repository.saveAndFlush(cottage);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public void delete(Cottage cottage) {
        repository.delete(cottage);
    }

}
