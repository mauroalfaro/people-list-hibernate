import com.alfarosoft.peoplelist.PeopleListApp;
import com.alfarosoft.peoplelist.model.Employee;
import com.alfarosoft.peoplelist.model.Store;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.alfarosoft.peoplelist.service.StoreService;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.ResourceUtils;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PeopleListApp.class)
@AutoConfigureMockMvc
public class StoreControllerTest {
    @MockBean
    private StoreService storeService;

    @Autowired
    private MockMvc mvc;

    @Test
    public void whenPostingAStoreShouldReturnAccepted() throws Exception{
        File file = ResourceUtils.getFile("classpath:storeExample.json");
        String body = FileUtils.readFileToString(file);

        Store store = new Store();
        store.setId("123");

        when(storeService.addStore(store)).thenReturn(store);

        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.post("/services/stores/add").contentType(MediaType.APPLICATION_JSON).content(body)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isAccepted())
                .andReturn();

        assertEquals(202,mvcResult.getResponse().getStatus());
    }
}
