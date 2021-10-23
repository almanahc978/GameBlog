package org.kamil.test.unit.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.function.ToDoubleBiFunction;

import org.hibernate.mapping.TableOwner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.kamil.controller.GameController;
import org.kamil.conversion.GameModelAssembler;
import org.kamil.model.Game;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = GameController.class)
public class GameRestControllerUnitTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private GameController gameController;

	@MockBean
	private GameModelAssembler gameModelAssembler;

	@Test
	void getAllTest() throws Exception {

	}

}
