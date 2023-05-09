package au.com.attari.ivcweb.crud;

import au.com.attari.ivcweb.crud.model.CompanyDataOther;
import au.com.attari.ivcweb.crud.repository.CompanyDataOtherRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class TestCompanyDataOtherRepository {

    @MockBean
    private CompanyDataOtherRepository companyDataOtherRepository;

    CompanyDataOther companyDataOther;

    AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        CompanyDataOther companyDataOther = new CompanyDataOther(1, "XXX", "ASX", "2023/04", "XXX CO", "15", "0.5", "1.0");
    }

    @Test
    public void save_test() {
        when(companyDataOtherRepository.save(companyDataOther)).thenReturn(companyDataOther);
        CompanyDataOther cdo = companyDataOtherRepository.save(companyDataOther);
        System.out.println(cdo);
    }



}
