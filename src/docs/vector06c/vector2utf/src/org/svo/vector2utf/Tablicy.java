/*
 * �������.java
 *
 * Created on 10 ������� 2007 �., 13:00
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.svo.vector2utf;

/**
 *
 * @author svo
 */
public final class ������� {
    public final static String ����_������ = "vv";
    public final static String ���8_� = "koi8";
    
    
    private �������() {
    }
    
    public static ������� �������() {
        if (����������� == null) {
            ����������� = new �������();
        }
        return �����������;
    }
    
    public char[][] ������������������������������(String �������) {
        char[][] ����������;
        
        ���������� = ���8_�.equalsIgnoreCase(�������) ? �������_���_�_���8 :
                     ����_������.equalsIgnoreCase(�������) ? �������_���_��_��������������� :
                         �������_���_��_���������������;
        
        return ����������;
    }
    
    public String ��������������������������������(String �������) {
        return ���8_�.equalsIgnoreCase(�������) ? "koi8-r" :
                            ����_������.equalsIgnoreCase(�������) ? "cp866" :
                                "cp866";
    }

    private final static char[][] �������_���_�_���8 = {
        {'\u253c','\u2551'},
        {'\u00b2', '\u2550'},
        // double corners
        {'\u2265', '\u2554'},
        {'\u2580', '\u2557'},
        {'\u2264','\u255a'},
        {'\u2584','\u255d'},
        
        {'\u25a0', '\u2500'},
        {'\u2510', '\u2502'},
        {'\u2553', '\u2558'},
        {'\u258c', '\u255b'},
        {'\u2551', '\u2564'},
        {'\u2558', '\u2518'},
        {'\u251c', '\u2562'},
        {'\u2248', '\u255f'},
        
        {'\u2554', '\u2552'},
        {'\u252c', '\u2555'},
        {'\u2514', '\u2524'},
        {'\u2559', '\u250c'},
        {'\u2591', '\u2514'},
        {'\u2593', '\u252c'},
        
        // ���
        {'\u255a', '\u2588'},
        {'\u255d', '\u2590'},
        {'\u255c', '\u258c'},
        
        // ������ � �������� �����
        {'\u256a', '�'},
        {'\u2561', '/'},
        {'�'     , '\\'},
        {'\u2563', '\\'},
        {'\u2562', '/'},
        {'\u256b', '\u00a9'},
        {'\u256c', '\u263b'},
        
        // ����� � �������
        {'\u2567', '\u2193',},
        {'\u2566', '\u2191',},
        {'\u2564', '\u2192',},
        {'\u2565', '\u2190'},
                 
        
        // ����� �
        {'\u2560', '�'},
        
    };
    
    
    private final static char[][] �������_���_��_��������������� = 
    {
        {'�', '\u2554'},       // double UL
        {'\u2580','\u2557'},   // double UR
        {'�', '\u255a'},       // double BL
        {'\u2584', '\u255d'},  // double BR
        {'\u253c', '\u2551'},  // double ||
        {'�', '\u2550'},       // double =  
        {'\u2500', '\u2588'},   // 25% grey

        {'\u2559', '\u250c'},       // single UL
        {'\u2590','\u2510'},   // single UR
        {'\u2591', '\u2514'},       // single BL
        {'\u2558', '\u2518'},  // single BR
        
        
        {'\u25a0', '\u2500'},   // single --
        {'\u2510', '\u2502'},   // single |
        {'\u2593', '\u252c'},   // single T
        {'\u2592', '\u2534'},   // inverted single T
        {'\u2219', '\u253c'},   // single +
        
        {'�',      '\u251c'},   // single T-90 deg
        {'\u2514', '\u2524'},   // single T+90 deg
        
        {'\u2551', '\u2564'},   // double horizontal single vertical T
        {'\u251c', '\u2562'},   // single horizontal double vertical T-90
        {'�',      '\u255f'},   // single horizontal double vertical T+90
        {'�',      '\u2567'},   // double horizontal single vertical T inverted
        
        {'\u00b0', '\u2560'},
        {'\u2557', '\u256a'},
        {'\u2534', '\u2563'},
        
        {'\u255a',  '\u2593'},
        {'\u255b',  '\u2591'},
        {'\u255e',  '\u2592'},
    };
    
    private static ������� ����������� = null;
}
