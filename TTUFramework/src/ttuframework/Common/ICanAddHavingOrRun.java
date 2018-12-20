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
public interface ICanAddHavingOrRun<T> {
    ICanAddGroupBy<T> Having(String condition);
    List<T> Run();
}
