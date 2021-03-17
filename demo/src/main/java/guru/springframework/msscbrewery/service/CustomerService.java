package guru.springframework.msscbrewery.service;

import java.util.UUID;

import guru.springframework.msscbrewery.web.model.CustomerDto;

public interface CustomerService {

	public CustomerDto getCustomerById(UUID id);
}
