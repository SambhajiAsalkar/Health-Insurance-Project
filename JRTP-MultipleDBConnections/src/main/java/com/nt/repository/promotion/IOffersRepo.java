package com.nt.repository.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.model.promotions.Offers;
@Repository
public interface IOffersRepo extends JpaRepository<Offers, Integer> {

}
