package guru.springframework.msscbrewery.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbrewery.service.BeerService;
import guru.springframework.msscbrewery.service.BeerServiceImpl;
import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/v1/beer")
@RestController
@Slf4j
public class BeerController {

	private final BeerService beerService;
	
	
	
	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}


	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){
		log.info("Handling Get mapping");
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
		
	}
	
	@PostMapping //Adding a new beer Handling POST Requests 
	public ResponseEntity createBeer(@RequestBody BeerDto beerDto) {
		log.info("Handling POST mapping");
		BeerDto savedDto = beerService.save(beerDto);
		
		//Adding headers 
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location",  "/api/v1/beer/" + savedDto.getId().toString());
		return new ResponseEntity(headers,HttpStatus.CREATED);
	}	
	
	@PutMapping("/{beerId}") //Updating a beer REMEMBER TO USE RESQUESTBODY 
	public ResponseEntity handleUpdate (@PathVariable("beerId") UUID beerId,@RequestBody BeerDto beerDto){
		log.info("Handling PUT mapping");
		beerService.updateBeer(beerId, beerDto);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
		
	}
	@DeleteMapping("/{beerId}") 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		log.info("Handling DELETE mapping");
		beerService.delete(beerId);
	}
}
