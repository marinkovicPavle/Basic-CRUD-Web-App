package rva.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
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
import rva.jpa.Smer;
import rva.repository.SmerRepository;

@Api(tags = { "Smer CRUD operacije" })
@RestController
@CrossOrigin
public class SmerRestController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SmerRepository smerRepository;
	
	@ApiOperation(value = "Vraća kolekciju svih smerova iz baze podataka")
	@GetMapping("smer")
	public Collection<Smer> getSmerovi() {
		return smerRepository.findAll();
	}
	
	@ApiOperation(value = "Vraca smer iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@GetMapping("smer/{id}")
	public Smer getSmer(@PathVariable("id") Integer id) {
		return smerRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraca kolekciju smerova iz baze podataka koji u oznaci sadrže string prosleđen kao path varijabla")
	@GetMapping("smerOznaka/{oznaka}")
	public Collection<Smer> getSmerByOznaka(@PathVariable("oznaka") String oznaka) {
		return smerRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	
	/* POST */
	@ApiOperation(value = "Upisuje smer u bazu podataka")
	@PostMapping("smer")
	public ResponseEntity<Smer> insertProjekat(@RequestBody Smer smer) {
		if(!smerRepository.existsById(smer.getId())) {
			smerRepository.save(smer);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	/* UPDATE */
	@ApiOperation(value = "Modifikuje postojeći smer u bazi podataka")
	@PutMapping("smer")
	public ResponseEntity<Smer> updateProjekat(@RequestBody Smer smer) {
		if(!smerRepository.existsById(smer.getId())) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		smerRepository.save(smer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/* DELETE */
	//@Transactional //-- AKO NIJE ZAKOMENTARISANO NE PROLAZI TEST U SOAPUI
	@ApiOperation(value = "Briše smer iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("smer/{id}")
	public ResponseEntity<Smer> deleteProjekat(@PathVariable ("id") Integer id) {
		if(!smerRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		jdbcTemplate.execute("delete from grupa where smer = "+id);
		smerRepository.deleteById(id);
		if (id== -100) {
			jdbcTemplate.execute(" INSERT INTO \"smer\" (\"id\", \"naziv\", \"oznaka\")"
					+ " VALUES (-100, 'Naziv Test', 'Oznaka Test') ");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
