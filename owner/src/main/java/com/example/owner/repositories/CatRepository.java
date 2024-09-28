package com.example.owner.repositories;


import com.example.owner.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer>, JpaSpecificationExecutor<Cat> {
    Cat findByIdAndOwnerId(Integer catId, Integer ownerId);

    List<Cat> findAllByOwnerId(Integer ownerId);
}
