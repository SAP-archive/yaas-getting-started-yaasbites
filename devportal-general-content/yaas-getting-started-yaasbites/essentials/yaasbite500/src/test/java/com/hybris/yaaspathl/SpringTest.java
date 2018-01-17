package com.hybris.yaaspathl;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import guru.nidi.ramltester.RamlDefinition;
import guru.nidi.ramltester.RamlLoaders;
import guru.nidi.ramltester.SimpleReportAggregator;
import guru.nidi.ramltester.junit.ExpectedUsage;

import java.util.List;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.raml.parser.rule.ValidationResult;
import org.raml.parser.visitor.RamlValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.hybris.yaaspaths.Application;
import com.hybris.yaaspaths.Tip;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class SpringTest {
	
	private static RamlDefinition api = RamlLoaders.fromClasspath().load( "api.raml" );	    
    private static SimpleReportAggregator aggregator = new SimpleReportAggregator();
    @ClassRule
    public static ExpectedUsage expectedUsage = new ExpectedUsage(aggregator);

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
	@Test
	// Validate the syntax of the RAML fkle
	public void ramlCheck() {
		String ramlLocation = "src/main/resources/api.raml";
		List<ValidationResult> results = RamlValidationService.createDefault().validate(ramlLocation);
		results.stream().forEach( s -> System.out.println("--> "+s));
		assertTrue(results.size() == 0);
	}

    @Test
    // Test the service, and check that we have tested all of the api as described in the RAML
    public void tips() throws Exception {
		Gson gson = new Gson();
		
		mockMvc.perform(post("/tips")
			 .contentType(MediaType.APPLICATION_JSON)
			 .content(gson.toJson(new Tip(1, "Dont Eat Fireworks")))
			 .accept(MediaType.parseMediaType("application/json")))
			 .andExpect(api.matches().aggregating(aggregator))
			 .andExpect(status().isCreated());
	
		mockMvc.perform(get("/tips")
			.accept(MediaType.parseMediaType("application/json")))
			.andExpect(api.matches().aggregating(aggregator))
			.andExpect(status().isOk())
			.andExpect(content().json("[{id:1, tip: 'Dont Eat Fireworks'}]"));	
		
		mockMvc.perform(put("/tips/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(gson.toJson(new Tip(1, "Do Eat Fireworks")))
				.accept(MediaType.parseMediaType("application/json")))
				.andExpect(api.matches().aggregating(aggregator))
				.andExpect(status().isOk())
				.andExpect(content().json("{id:1, tip: 'Do Eat Fireworks'}"));	

		mockMvc.perform(delete("/tips/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(api.matches().aggregating(aggregator))
		        .andExpect(status().isNoContent() );

	
		
    }
	
}
