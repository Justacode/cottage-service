package ru.kpfu.itis.mustafin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.mustafin.models.Cottage;

@Repository
public interface CottageRepository extends JpaRepository<Cottage, Long> {

    public Cottage findByCottageNumber(Integer cottageNumber);

}
