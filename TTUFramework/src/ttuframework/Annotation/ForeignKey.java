/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttuframework.Annotation;

/**
 *
 * @author Tu Nguyen
 */
public @interface ForeignKey {
    public String RelationshipID();
    public String Name();
    public String References();
}