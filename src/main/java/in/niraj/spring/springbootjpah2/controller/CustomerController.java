package in.niraj.spring.springbootjpah2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.niraj.spring.springbootjpah2.controller.exceptions.CustomerNotFoundException;
import in.niraj.spring.springbootjpah2.controller.exceptions.ErrorObject;
import in.niraj.spring.springbootjpah2.entity.Customers;
import in.niraj.spring.springbootjpah2.repository.ICustomerRepo;

@RestController
@RequestMapping("/api")
public class CustomerController {
	// private final Logger log = (Logger)
	// LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	ICustomerRepo customerRepo;

	@PostMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
	// @ReteLimiter(name="emailratelimiter")
	// @RateLimiter("R")
	public ResponseEntity<Customers> saveCustomer(@RequestBody Customers customer) {
		try {
			return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customers>> getAllCustomers() {
		try {
			List<Customers> list = customerRepo.findAll();

			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ExceptionHandler
	public ResponseEntity<ErrorObject> handleException(CustomerNotFoundException ex) {
		ErrorObject eObject = new ErrorObject();
		eObject.setStatus(HttpStatus.NOT_FOUND.value());
		eObject.setMessage(ex.getMessage());
		eObject.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<ErrorObject>(eObject, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/customers/{id}")
	public Customers getEmployee(long id) {
		List<Customers> list = customerRepo.findAll();
		Optional<Customers> theEmployee = list.stream().filter(e -> e.getId() == id).findFirst();
		if (theEmployee.isPresent()) {
			return theEmployee.get();
		}
		throw new CustomerNotFoundException("Cusromer not found for the id ->" + id);
	}

	/*
	 * @GetMapping("/customerss/{id}") public ResponseEntity<Customers>
	 * getCustomerById(@PathVariable("id") long id) { Customers tutorial =
	 * customerRepo.findById(id) .orElseThrow(() -> new
	 * CustomerNotFoundException("Not found Cusromer with id = " + id));
	 * 
	 * return new ResponseEntity<>(tutorial, HttpStatus.OK); }
	 */

}