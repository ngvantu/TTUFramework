/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.Common;

import java.util.List;

/**
 *
 * @author Tu Nguyen
 * @param <T>
 */
public interface IQuery<T> {
    List<T> ExecuteQuery<T>();
    List<T> ExecuteQueryWithoutRelationship<T>();
    int ExecuteNonQuery();
}
