/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.Annotation;

import ttuframework.Common.DataType;


/**
 *
 * @author Tu Nguyen
 */
public @interface Column {
    public String Name();
    public DataType DataType();
}
