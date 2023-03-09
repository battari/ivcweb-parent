/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package au.com.attari.ivcweb.task.webclient;

import au.com.attari.ivcweb.task.bean.CompanyEvaluation;

import java.util.ArrayList;

/**
 *
 * @author battari
 */
public interface CompanyEvaluationWebClientIface {

    public boolean create(CompanyEvaluation inCompanyEvaluation);

    public boolean existsByCompanyAndDateAndRequiredRate(CompanyEvaluation inCompanyEvaluation);

    public ArrayList<CompanyEvaluation> listByCompanyAndDateAndRequiredRate(CompanyEvaluation inCompanyEvaluation);
}
