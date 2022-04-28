/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet1_annuaire;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Inclusiv
 */
public class Hashage {
    public String getHash(byte[] inputBytes){
        String hashValue = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            md.update(inputBytes);
            byte[] digestByte = md.digest();
            hashValue = DatatypeConverter.printHexBinary(digestByte).toLowerCase();
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hashage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashValue;
    }
}
