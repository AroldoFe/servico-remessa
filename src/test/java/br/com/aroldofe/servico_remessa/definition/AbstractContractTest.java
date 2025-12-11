package br.com.aroldofe.servico_remessa.definition;

import br.com.aroldofe.servico_remessa.config.DefaultExceptionHandler;
import br.com.aroldofe.servico_remessa.utils.anotation_test_category.UnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@UnitTest
@Execution(ExecutionMode.CONCURRENT)
@SpringJUnitWebConfig
@Import(DefaultExceptionHandler.class)
public class AbstractContractTest {
    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }
}
