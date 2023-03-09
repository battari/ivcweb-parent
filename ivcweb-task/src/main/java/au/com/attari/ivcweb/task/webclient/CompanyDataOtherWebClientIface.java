/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package au.com.attari.ivcweb.task.webclient;

import au.com.attari.ivcweb.task.bean.CompanyDataOther;

import java.util.ArrayList;

/**
 *
 * @author battari
 */
public interface CompanyDataOtherWebClientIface {

    public boolean create(CompanyDataOther companyDataOther);

     public ArrayList<CompanyDataOther> listAll();

     public ArrayList<CompanyDataOther> listAllByMaxDate();

      public boolean existsByCompanyAndDate(CompanyDataOther inCompanyDataOther);

      public ArrayList<CompanyDataOther> listByCompanyAndDate(CompanyDataOther inCompanyDataOther);

}
