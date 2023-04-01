/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Asus
 */
public enum Role {
    R001("R001"), R002("R002"), ADMIN("ADMIN");
    public final String role;
    Role(String role) {
        this.role = role;
    }
}
