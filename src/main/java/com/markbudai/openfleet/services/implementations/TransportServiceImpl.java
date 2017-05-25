package com.markbudai.openfleet.services.implementations;

import com.markbudai.openfleet.dao.repositories.TransportRepository;
import com.markbudai.openfleet.model.Employee;
import com.markbudai.openfleet.model.Transport;
import com.markbudai.openfleet.services.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mark on 2017. 05. 10..
 */
@Service
public class TransportServiceImpl implements TransportService {

    private TransportRepository transportRepository;

    @Autowired
    public TransportServiceImpl(TransportRepository repository){
        this.transportRepository = repository;
    }

    @Override
    public List<Transport> getAllTransports() {
        return transportRepository.getAllTransports();
    }

    @Override
    public void addTransport(Transport t) {
        transportRepository.addTransport(t);
    }

    @Override
    public List<Transport> getTransportByEmployee(Employee e) {
        List<Transport> transports = transportRepository.getAllTransports();
        return transports.stream().filter(p->p.getEmployee().equals(e)).collect(Collectors.toList());
    }

    @Override
    public Transport getTransportById(long id){
        return transportRepository.getTransportById(id);
    }

    @Override
    public void updateTransport(Transport t) {
        transportRepository.updateTransport(t);
    }

}