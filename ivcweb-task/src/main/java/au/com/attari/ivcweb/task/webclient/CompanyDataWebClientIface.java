/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package au.com.attari.ivcweb.task.webclient;

import au.com.attari.ivcweb.task.bean.CompanyData;

import java.util.List;

/**
 *
 * @author battari
 */
public interface CompanyDataWebClientIface {

    public boolean create(CompanyData companyData);

     public List<CompanyData> listAll();

     public List<CompanyData> listAllByMaxDate(String exchange);

      public boolean existsByCompanyAndDate(CompanyData inCompanyData);

      public List<CompanyData> listByCompanyAndDate(CompanyData inCompanyData);

}
