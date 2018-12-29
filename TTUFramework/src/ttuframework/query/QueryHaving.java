/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.query;

import java.util.List;

/**
 *
 * @author Tu Nguyen
 */
public interface QueryHaving<T> {
    QueryGroupBy<T> addHaving(String condition);
    List<T> run();
}
