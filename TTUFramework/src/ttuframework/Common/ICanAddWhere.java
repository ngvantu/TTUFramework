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
public interface ICanAddWhere<T> {
    ICanAddHavingOrRun<T> Where(String condition);
}
