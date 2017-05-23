package ru.kpfu.itis.mustafin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.mustafin.models.Rent;

public interface RentRepository extends JpaRepository<Rent, Long> {
}
