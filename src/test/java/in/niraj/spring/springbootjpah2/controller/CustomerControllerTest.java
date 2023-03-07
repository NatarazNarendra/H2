package in.niraj.spring.springbootjpah2.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import in.niraj.spring.springbootjpah2.entity.Customers;
import in.niraj.spring.springbootjpah2.repository.ICustomerRepo;
import in.niraj.spring.springbootjpah2.util.JsonUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	@MockBean
	private ICustomerRepo customerRepo;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test() {
		System.out.println("JUNIY-NARENDRS");
	}

	@Test
	public void test1() throws Exception {
		List<Customers> tutorials = new ArrayList<>(Arrays.asList(new Customers((long) 1, "narendra ", "HYD")));
		when(customerRepo.findAll()).thenReturn(tutorials);
		this.mockMvc.perform(get("/api/customers")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void shouldReturnListOfTutorialss() throws Exception {
		long id = 1L;
		when(customerRepo.findById(1l)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/customerss/{id}", id)).andDo(print()).andExpect(status().isNotFound());
	}
	
	/*
	 * @Test public void createUser_whenPostMethod() throws Exception { Customers
	 * user = new Customers((long) 1, "narendra","Bombay");
	 * given(customerRepo.save(user)).willReturn(user); user.setName("narendra");
	 * mockMvc.perform(post("/api") // .contentType(MediaType.APPLICATION_JSON)
	 * .content(JsonUtil.toJson(user))) .andExpect(status().isCreated()); //
	 * .andExpect(jsonPath("$.name", is(user.getName()))); }
	 */
}
