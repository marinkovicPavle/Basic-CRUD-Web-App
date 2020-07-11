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
import rva.jpa.Student;
import rva.repository.StudentRepository;

@Api(tags = { "Student CRUD operacije" })
@RestController
@CrossOrigin
public class StudentRestController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate; //Test 1

	@Autowired
	private StudentRepository studentRepository;
	
	@ApiOperation(value = "Vraća kolekciju svih studenata iz baze podataka")
	@GetMapping("student")
	public Collection<Student> getStudenti() {
		return studentRepository.findAll();
	}
	
	@ApiOperation(value = "Vraca studenta iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@GetMapping("student/{id}")
	public Student getStudent(@PathVariable("id") Integer id) {
		return studentRepository.getOne(id);
	}
	
	@ApiOperation(value = "Vraca kolekciju studenata iz baze podataka koji u indeksu sadrže string prosleđen kao path varijabla")
	@GetMapping("studentIndeks/{brIndeksa}")
	public Collection<Student> getStudentByBrojIndeksa(@PathVariable("brIndeksa") String brIndeksa) {
		return studentRepository.findByBrojIndeksaContainingIgnoreCase(brIndeksa);
	}
	
	/* POST */
	@ApiOperation(value = "Upisuje studenta u bazu podataka")
	@PostMapping("student")
	public ResponseEntity<Student> insertProjekat(@RequestBody Student student) {
		if(!studentRepository.existsById(student.getId())) {
			studentRepository.save(student);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	/* UPDATE */
	@ApiOperation(value = "Modifikuje postojećeg studenta u bazi podataka")
	@PutMapping("student")
	public ResponseEntity<Student> updateProjekat(@RequestBody Student student) {
		if(!studentRepository.existsById(student.getId())) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		studentRepository.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	/* DELETE */
	@ApiOperation(value = "Briše studenta iz baze podataka čija je id vrednost prosleđena kao path varijabla")
	@DeleteMapping("student/{id}")
	public ResponseEntity<Student> deleteProjekat(@PathVariable ("id") Integer id) {
		if(!studentRepository.existsById(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		studentRepository.deleteById(id);
		if (id== -100) {
			jdbcTemplate.execute(" INSERT INTO \"student\" (\"id\", \"ime\", \"prezime\", \"broj_indeksa\", \"grupa\", \"projekat\")"
					+ " VALUES (-100, 'Ime Test', 'Prezime Test', 'Index Test', 2, 2) ");
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
