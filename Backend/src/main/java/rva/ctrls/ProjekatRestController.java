package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Projekat;
import rva.repository.ProjekatRepository;

@Api(tags = { "Projekat CRUD operacije" })
@RestController
@CrossOrigin
public class ProjekatRestController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ProjekatRepository projekatRepository;
	
	/* GET */
	@ApiOperation(value = "Vraća kolekciju svih projekata iz baze podataka")
	@GetMapping("projekat")
	public Collection<Projekat> getProjekti() {
		return projekatRepository.findAll();
	}
	
	@ApiOperation(value = "Vraca projekat iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@GetMapping("projekat/{id}")
	public Projekat getProjekat(@PathVariable("id") Integer id) {
		return projekatRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraca kolekciju projekata iz baze podataka koji u oznaci sadrže string prosleđen kao path varijabla")
	@GetMapping("projekatOznaka/{oznaka}")
	public Collection<Projekat> getProjekatByOznaka(@PathVariable("oznaka") String oznaka) {
		return projekatRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	/* POST */
	@ApiOperation(value = "Upisuje projekat u bazu podataka")
	@PostMapping("projekat")
	public ResponseEntity<Projekat> insertProjekat(@RequestBody Projekat projekat) {
		if(!projekatRepository.existsById(projekat.getId())) {
			projekatRepository.save(projekat);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	/* UPDATE */
	@ApiOperation(value = "Modifikuje postojeći projekat u bazi podataka")
	@PutMapping("projekat")
	public ResponseEntity<Projekat> updateProjekat(@RequestBody Projekat projekat) {
		if(!projekatRepository.existsById(projekat.getId())) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		projekatRepository.save(projekat);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/* DELETE */
	@ApiOperation(value = "Briše projekat iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("projekat/{id}")
	public ResponseEntity<Projekat> deleteProjekat(@PathVariable ("id") Integer id) {
		if(!projekatRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		projekatRepository.deleteById(id);
		if (id == -100) {
			jdbcTemplate.execute(" INSERT INTO \"projekat\"(\"id\", \"naziv\", \"opis\", \"oznaka\") VALUES (-100, 'Naziv test', 'Opis test', 'test') ");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
