package com.nt.runner;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.model.prod.Product;
import com.nt.model.promotions.Offers;
import com.nt.repository.prod.IProductRepo;
import com.nt.repository.promotion.IOffersRepo;
@Component
public class MultiDataRunner implements CommandLineRunner {
	@Autowired
	private IProductRepo prodRepo;
  @Autowired
  private IOffersRepo offersRepo;

	@Override
	public void run(String... args) throws Exception {
		Product prod=new Product("Table",50000.0,10.0,"IKEA");
		
int idVal=prodRepo.save(prod).getPid();
System.out.println("Product is saved with id value:"+idVal);
System.out.println("============================");
  
Offers offers=new Offers("Diwali Offer","DWL-553",75.0f,LocalDateTime.now());
int idVal1=offersRepo.save(offers).getOfferId();
System.out.println("offer is saved with id value:"+idVal1);
System.out.println("============================");
  
	}

}
