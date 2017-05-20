package tests.supplier;

import com.markbudai.openfleet.dao.providers.TransportProvider;
import com.markbudai.openfleet.model.Transport;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Class supplying sample Transport objects for testing purposes.
 */
public class TransportSupplier {
    /**
     * Function which creates a sample Transport object for testing.
     * @return the sample Transport object for testing.
     */
    public static Transport getSampleTransport(){
        Transport transport = new Transport();
        Month month = LocalDate.now().getMonth();
        transport.setCargo_weight(1);
        transport.setCargo_name("Sample Cargo");
        transport.setId(1);
        transport.setCargo_count(1);
        transport.setStart(LocalDateTime.of(2017,month.getValue(),1,1,0));
        transport.setFinish(LocalDateTime.of(2017,month.getValue(),10,10,10));
        transport.setTime_of_load(LocalDateTime.of(2017,month.getValue(),1,8,0));
        transport.setTime_of_unload(LocalDateTime.of(2017,month.getValue(),10,8,0));
        transport.setStart(LocalDateTime.of(2017,month.getValue(),1,8,0));
        transport.setFinish(LocalDateTime.of(2017,month.getValue(),10,8,0));
        transport.setPlace_of_load(LocationSupplier.getSampleLocation());
        transport.setPlace_of_unload(LocationSupplier.getSampleLocation());
        transport.setEmployee(EmployeeSupplier.getSampleEmployee());
        transport.setTractor(TractorSupplier.getSampleTractor());
        transport.setTrailer(TrailerSupplier.getSampleTrailer());
        return transport;
    }

    public static Transport getAnotherMonthsSampleTransport(){
        Transport transport = new Transport();
        transport.setCargo_weight(10);
        transport.setCargo_name("Sample Cargo II");
        transport.setId(2);
        transport.setCargo_count(2);
        transport.setStart(LocalDateTime.of(2017,4,1,1,0));
        transport.setFinish(LocalDateTime.of(2017,4,10,10,10));
        transport.setTime_of_load(LocalDateTime.of(2017,4,1,8,0));
        transport.setTime_of_unload(LocalDateTime.of(2017,4,10,8,0));
        transport.setStart(LocalDateTime.of(2017,4,1,8,0));
        transport.setFinish(LocalDateTime.of(2017,4,10,8,0));
        transport.setPlace_of_load(LocationSupplier.getSampleLocation());
        transport.setPlace_of_unload(LocationSupplier.getSampleLocation());
        transport.setEmployee(EmployeeSupplier.getSampleEmployee());
        transport.setTractor(TractorSupplier.getSampleTractor());
        transport.setTrailer(TrailerSupplier.getSampleTrailer());
        return transport;
    }

    public static TransportProvider getMockProvider(){
        TransportProvider transportProvider = Mockito.mock(TransportProvider.class);
        Mockito.when(transportProvider.getTransportById(1)).thenReturn(getSampleTransport());
        Mockito.when(transportProvider.getTransportByEmployee(EmployeeSupplier.getSampleEmployee())).thenReturn(getSampleTransportList());
        return transportProvider;
    }

    public static List<Transport> getSampleTransportList(){
        List<Transport> list = new ArrayList<>();
        list.add(getSampleTransport());
        list.add(getAnotherMonthsSampleTransport());
        return list;
    }
}
