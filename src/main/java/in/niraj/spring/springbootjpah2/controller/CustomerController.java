package in.niraj.spring.springbootjpah2.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import in.niraj.spring.springbootjpah2.controller.exceptions.CustomerNotFoundException;
import in.niraj.spring.springbootjpah2.controller.exceptions.ErrorObject;
import in.niraj.spring.springbootjpah2.entity.Customers;
import in.niraj.spring.springbootjpah2.repository.ICustomerRepo;

@RestController
@RequestMapping("/api")
public class CustomerController {
//	 private final Logger log = (Logger)
	//LoggerFactory.getLogger(CustomerController.class.getName());
	@Autowired
	ICustomerRepo customerRepo;
//Postmapping approach :1
	/*
	 * @PostMapping(value = "/customers") // @ReteLimiter(name="emailratelimiter")
	 * // @RateLimiter("R") public ResponseEntity<Customers>
	 * saveCustomer(@RequestBody Customers customer) {
	 * log.info("customer",customer); try { return new
	 * ResponseEntity<>(customerRepo.save(customer), HttpStatus.CREATED); } catch
	 * (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 
	}*/
//Postmapping approach :2	
	 @PostMapping
	    @ResponseStatus(HttpStatus.CREATED)
	  //  @ApiOperation(value = "Execute POST method")
	    public ResponseEntity<Customers> createNewUser_whenPostUser(@RequestBody Customers user) {

		 Customers createdUser = customerRepo.save(user);

	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(createdUser.getId())
	                .toUri();

	        return ResponseEntity.created(uri).body(createdUser);
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