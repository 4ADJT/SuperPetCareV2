package br.com.superpetcare.superpetcare.domain.pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PetRepository extends JpaRepository<PetEntity, UUID> {

}
