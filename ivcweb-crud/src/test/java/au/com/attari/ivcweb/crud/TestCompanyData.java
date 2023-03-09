package au.com.attari.ivcweb.crud;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import au.com.attari.ivcweb.crud.model.CompanyData;
import au.com.attari.ivcweb.crud.repository.CompanyDataRepository;

import java.util.Optional;
import javax.transaction.Transactional;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = IVCWEBCrudApplication.class)
// @DataJpaTest
public class TestCompanyData {

    @Autowired
    CompanyDataRepository repository;

    @Test
    public void testSuccessRepository() throws Exception {

        CompanyData cd = new CompanyData();
        cd.setId(1001);
        cd.setCompany("XXX");
        cd.setCapitalization("1000000000");
        cd.setExchange("ASX");
        cd.setEquity("1000000000");
        cd.setSharesIssued("1000000000");
        cd.setRoe("0.200");
        cd.setDate("2023/02");

        repository.save(cd);

        Optional<CompanyData> queryResult = repository.findById(1001);

        Assertions.assertEquals(false,queryResult.isEmpty());
        Assertions.assertEquals(false,queryResult.get().getCompany().isEmpty());
    }

    @Test
    public void TestFailRepositoryTest() throws Exception {

        CompanyData cd = new CompanyData();
        cd.setId(1001);
        cd.setCompany("XXX");
        cd.setCapitalization("1000000000");
        cd.setExchange("ASX");
        cd.setEquity("1000000000");
        cd.setSharesIssued("1000000000");
        cd.setRoe("0.200");
        cd.setDate("2023/02");

        repository.save(cd);

        Optional<CompanyData> queryResult = repository.findById(1002);

        Assertions.assertEquals(true, queryResult.isEmpty());
        // Assertions.assertEquals(true, queryResult.get().getAuthor().isEmpty());
    }

}
