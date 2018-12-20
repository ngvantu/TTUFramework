/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.Common;

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
    public abstract ICanAddWhere<T> Select<T>();
    public abstract int Insert<T>(T obj);
    public abstract int Update<T>(T obj);
    public abstract int Delete<T>(T obj);
    public abstract List<T> ExecuteQuery<T>(String query);
    public abstract List<T> ExecuteQueryWithOutRelationship<T>(String query);
    public abstract int ExecuteNonQuery(String query);
}
