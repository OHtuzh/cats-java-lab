import org.example.controllers.CatController;
import org.example.models.cat.Cat;
import org.example.models.cat.Color;
import org.example.services.CatService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import org.example.dao.CatDao;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TestCatController {
    private CatDao catDao = Mockito.mock(CatDao.class);
    private CatService catService = new CatService(catDao);
    private CatController catController = new CatController(catService);

    @Test
    public void testAddCat() {
        Cat cat = new Cat();
        cat.setColor(Color.BLACK);
        cat.setName("name");
        cat.setBreed("breed");

        doNothing().when(catDao).save(cat);

        catController.saveCat(cat);

        verify(catDao, times(1)).save(cat);
    }
}
