package unit.service;

import com.ednach.model.Producer;
import com.ednach.repository.ProducerRepository;
import com.ednach.service.impl.ProducerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProducerServiceImplTest {

    @InjectMocks
    private ProducerServiceImpl producerService;

    @Mock
    private ProducerRepository producerRepository;

    @Test
    public void testFindAll() {
        final List<Producer> producerList = Collections.singletonList(new Producer());
        when(producerRepository.findAll()).thenReturn(producerList);
        assertEquals(producerService.findAll(), producerList);
    }

    @Test
    public void testFindById() {
        final Producer producer = new Producer();
        when(producerRepository.findById(1L)).thenReturn(Optional.of(producer));
        assertEquals(producerService.findById(1L), producer);
    }

    @Test
    public void testSave() {
        final Producer producer = new Producer();
        when(producerRepository.saveAndFlush(producer)).thenReturn(producer);
        assertEquals(producerService.save(producer), producer);
    }

    @Test
    public void testUpdate() {
        final Producer producer = new Producer();
        producer.setId(1L);
        when(producerRepository.findById(1L)).thenReturn(Optional.of(producer));
        when(producerRepository.saveAndFlush(producer)).thenReturn(producer);
        assertEquals(producerService.update(producer), producer);
    }

    @Test
    public void testDelete() {
        final Producer producer = new Producer();
        producer.setId(1L);
        when(producerRepository.findById(1L)).thenReturn(Optional.of(producer));
        doNothing().when(producerRepository).delete(producer);
        assertDoesNotThrow(() -> producerService.delete(producer));
    }

    @Test
    public void testDeleteById() {
        final Producer producer = new Producer();
        producer.setId(1L);
        doNothing().when(producerRepository).deleteById(any(Long.class));
        when(producerRepository.findById(1L)).thenReturn(Optional.of(producer));
        assertDoesNotThrow(() -> producerService.deleteById(1L));
    }
}

