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
public interface Query {
    <T> List<T> executeQuery();
    <T> List<T> executeQueryWithOutRelationship();
    int executeNonQuery();
}
