package tests.repository;

import com.markbudai.openfleet.dao.repositories.LocationRepository;
import com.markbudai.openfleet.dao.repositoryImplementations.LocationRepositoryJPA;
import com.markbudai.openfleet.exception.NotFoundException;
import com.markbudai.openfleet.model.Location;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import tests.supplier.LocationSupplier;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 2017. 05. 19..
 */
public class LocationRepositoryTest {
    private static LocationRepository locationRepository;
    private static EntityManager mockedEntityManager;


    private static EntityManager setUpMockedEntityManager(){
        EntityManager mockedEntityManager = Mockito.mock(EntityManager.class);
        Query mockedQuery = Mockito.mock(Query.class);
        Mockito.when(mockedQuery.getResultList()).thenReturn(LocationSupplier.getSampleLocationList());
        Mockito.when(mockedEntityManager.createQuery("select e from Location e")).thenReturn(mockedQuery);
        Mockito.when(mockedEntityManager.find(Location.class,999)).thenReturn(LocationSupplier.getSampleLocation());
        Mockito.when(mockedEntityManager.find(Location.class,99)).thenReturn(null);
        return mockedEntityManager;
    }

    @BeforeClass
    public static void init(){
        mockedEntityManager = setUpMockedEntityManager();
        locationRepository = new LocationRepositoryJPA(mockedEntityManager);
    }

    @Test
    public void getAllLocationsReturnsASingleElement(){
        Assert.assertEquals(1,locationRepository.getAll().size());
    }

    @Test
    public void getAllLocationsReturnsThatOneLocation(){
        List<Location> expected = new ArrayList<>();
        expected.add(LocationSupplier.getSampleLocation());
        Assert.assertEquals(expected,locationRepository.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void exceptionWhenNoLocationFound(){
        locationRepository.getById(99);
    }

    @Test(expected = NotFoundException.class)
    public void exceptionWhenDeleteNonExistingLocation(){
        locationRepository.deleteLocation(99);
    }
}
