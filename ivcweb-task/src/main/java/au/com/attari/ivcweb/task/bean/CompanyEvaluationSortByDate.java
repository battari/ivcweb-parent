/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.attari.ivcweb.task.bean;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 *
 * @author battari
 */
public class CompanyEvaluationSortByDate implements Comparator<CompanyEvaluation> {

    public int compare(CompanyEvaluation a, CompanyEvaluation b) {
        return a.getDate().compareTo(b.getDate());
    }
}
