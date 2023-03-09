package au.com.attari.ivcweb.crud.repository;

import au.com.attari.ivcweb.crud.model.CompanyEvaluation;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

//repository that extends CrudRepository
public interface CompanyEvaluationRepository extends CrudRepository<CompanyEvaluation, Integer> {

    public List<CompanyEvaluation> findByCompanyAndExchange(String company, String exchange);

    public List<CompanyEvaluation>  findByCompanyAndExchangeAndDate(String company, String exchange, String date);

    public List<CompanyEvaluation>  findByCompanyAndExchangeAndDateAndRequiredRate(String company, String exchange, String date, String requiredRate );

}
