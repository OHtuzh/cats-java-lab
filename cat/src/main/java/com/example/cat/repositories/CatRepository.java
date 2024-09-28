package com.example.cat.repositories;


import com.example.cat.models.Cat;
import com.example.cat.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Integer>, JpaSpecificationExecutor<Cat> {
    Cat findByIdAndOwnerId(Integer catId, Integer ownerId);

    List<Cat> findAllByOwnerId(Integer ownerId);
}
