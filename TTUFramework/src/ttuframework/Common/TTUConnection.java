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
 */
public abstract class TTUConnection {
    protected String connectionString;

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    
    public abstract void Open();
    public abstract void Close();
    public abstract <T> ICanAddWhere<T> Select();
    public abstract <T> int Insert(T obj);
    public abstract <T> int Update(T obj);
    public abstract <T> int Delete(T obj);
    public abstract <T> List<T> ExecuteQuery(String query);
    public abstract <T> List<T> ExecuteQueryWithOutRelationship(String query);
    public abstract int ExecuteNonQuery(String query);
}
