package guru.springframework.msscbrewery.service;

import java.util.UUID;

import guru.springframework.msscbrewery.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID beerId);
	
	

}
