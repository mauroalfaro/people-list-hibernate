import com.alfarosoft.peoplelist.PeopleListApp;
import com.alfarosoft.peoplelist.model.Customer;
import com.alfarosoft.peoplelist.model.Employee;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.alfarosoft.peoplelist.service.EmployeeService;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ResourceUtils;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PeopleListApp.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenPostingAnEmployeeShouldReturnAccepted() throws Exception{
        File file = ResourceUtils.getFile("classpath:employeeExample.json");
        String body = FileUtils.readFileToString(file);

        Employee employee = new Employee();
        employee.setId("123");

        when(employeeService.addEmployee(employee)).thenReturn(employee);

        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post("/services/employees/add").contentType(MediaType.APPLICATION_JSON).content(body)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn();

        assertEquals(202,mvcResult.getResponse().getStatus());
    }
}
