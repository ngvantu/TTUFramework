/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.query;

/**
 *
 * @author Tu Nguyen
 */
public interface QueryWhere<T> {
    QueryHaving<T> addWhere(String condition);
}