/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Classes;

import java.security.MessageDigest;

/**
 *
 * @author Jason
 * 
 * Can be used to generate an SHA1 message digest
 */
public class SHA1MessageDigest {
    
    MessageDigest md;
    
    public SHA1MessageDigest() {
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (Exception e) {
            //Silently fail
        }
    }
    
    public String digestMessage(String message) {
        md.update(message.getBytes());
        byte[] output = md.digest();
        return bytesToHex(output);
    }
    
    public static String bytesToHex(byte[] b) {
        StringBuilder builder = new StringBuilder();
        char hexDigit[] = {'0','1','2','3','4','5','6','7',
                           '8','9','A','B','C','D','E','F'};
        
        for (int i = 0; i < b.length; i++) {
            builder.append(hexDigit[(b[i] >> 4) & 0x0f]);
            builder.append(hexDigit[b[i] & 0x0f]);
        }
        
        return builder.toString();
    }
}
