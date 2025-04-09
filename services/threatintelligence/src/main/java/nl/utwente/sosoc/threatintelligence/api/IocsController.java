package nl.utwente.sosoc.threatintelligence.api;

import nl.utwente.sosoc.threatintelligence.entity.IOCEntity;
import nl.utwente.sosoc.threatintelligence.repository.IOCRepository;
import nl.utwente.sosoc.threatintelligence.model.IOC;
import nl.utwente.sosoc.threatintelligence.util.EntityMapper;
import org.modelmapper.spi.DestinationSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
public class IocsController implements IocsApi {

    @Autowired private IOCRepository iocRepository;
    @Autowired private EntityMapper entityMapper;

    @Override
    public ResponseEntity<List<IOC>> getIOCs() {
        List<IOC> iocs = StreamSupport.stream(iocRepository.findAll().spliterator(), false)
            .map(entityMapper.to(IOC.class))
            .toList();
        return ResponseEntity.ok(iocs);
    }

    @Override
    public ResponseEntity<IOC> getIOC(UUID id) {
        return iocRepository.findById(id)
            .map(entityMapper.to(IOC.class))
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> postIOC(IOC ioc) {
        iocRepository.save(entityMapper.to(
            IOCEntity.class,
            (DestinationSetter<IOCEntity, UUID>) IOCEntity::setId // skip ID
        ).apply(ioc));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> putIOC(UUID id, IOC ioc) {
        iocRepository.save(entityMapper.to(IOCEntity.class).apply(ioc.id(id)));
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteIOC(UUID id) {
        if (iocRepository.existsById(id)) {
            iocRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
