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
import rva.jpa.Grupa;
import rva.jpa.Smer;
import rva.repository.GrupaRepository;
import rva.repository.SmerRepository;

@Api(tags = { "Grupa CRUD operacije" })
@RestController
@CrossOrigin
public class GrupaRestContoller {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private GrupaRepository grupaRepository;
	
	@Autowired
	private SmerRepository smerRepository;
	
	/* GET */
	@ApiOperation(value = "Vraća kolekciju svih grupa iz baze podataka")
	@GetMapping("grupa")
	public Collection<Grupa> getGrupe() {
		return grupaRepository.findAll();
	}
	
	@ApiOperation(value = "Vraca grupu iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@GetMapping("grupa/{id}")
	public Grupa getGrupa(@PathVariable("id") Integer id) {
		return grupaRepository.getOne(id);
	}
	
	@GetMapping("grupeZaSmerId/{id}")
	public Collection<Grupa> getGrupeForSmer(@PathVariable("id") int id){
		Smer s = smerRepository.getOne(id);
		return grupaRepository.findBySmer(s);
	}
	
	@ApiOperation(value = "Vraca kolekciju grupa iz baze podataka koje u oznaci sadrže string prosleđen kao path varijabla")
	@GetMapping("grupaOznaka/{oznaka}")
	public Collection<Grupa> getGrupaByOznaka(@PathVariable("oznaka") String oznaka) {
		return grupaRepository.findByOznakaContainingIgnoreCase(oznaka);
	}
	
	/* POST */
	@ApiOperation(value = "Upisuje grupu u bazu podataka")
	@PostMapping("grupa")
	public ResponseEntity<Grupa> insertGrupa(@RequestBody Grupa grupa) {
		if(!grupaRepository.existsById(grupa.getId())) {
			grupaRepository.save(grupa);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	/* UPDATE */
	@ApiOperation(value = "Modifikuje postojeću grupu u bazi podataka")
	@PutMapping("grupa")
	public ResponseEntity<Grupa> updateProjekat(@RequestBody Grupa grupa) {
		if(!grupaRepository.existsById(grupa.getId())) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		grupaRepository.save(grupa);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/* DELETE */
	@ApiOperation(value = "Briše grupu iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("grupa/{id}")
	public ResponseEntity<Grupa> deleteProjekat(@PathVariable ("id") Integer id) {
		if(!grupaRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		grupaRepository.deleteById(id);
		if (id== -100) {
			jdbcTemplate.execute(" INSERT INTO \"grupa\" (\"id\", \"oznaka\", \"smer\")"
					+ " VALUES (-100, 'Test', 3) ");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
