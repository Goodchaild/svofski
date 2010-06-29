/*
 * Main.java
 *
 * Created on 9 ������� 2007 �., 22:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.svo.vector2utf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author svo
 */
public class ������� {
    public �������() {
    }
    
   public static void main(String[] args) {
        if (args.length < 2) {
            ���();
        }
        
        String inputFile = args[0];
        String outputFile = args[1];
        
        String ����� = �������.����_������;
        if (args.length == 3) {
            ����� = args[2];
        }

        �������������� ���� = new ��������������(inputFile, outputFile, �����);
        try {
            ����.������������();
        } catch (IOException ���) {
            ���("�������� ��� �������� ������" + ���.getLocalizedMessage());
        }
        try {
            ����.��������();
            ����.��();
        } catch (IOException ������) {
            ���("�������� �� ����������, ������ ��� " + ������);
        }
        
        System.exit(0);        
    }
    
    private static void ���() {
        System.err.println("Usage: vvp <input file> <output file> <magic>");
        System.err.println("\tmagic:\t(none):\trecode as per Vladimir Vector (only funny block drawings)");
        System.err.println("\t\tkoi8:\trecode from koi8");
        System.exit(1);
    }
    
    private static void ���(String doh) {
        System.err.println(doh);
        System.exit(1);
    }
    
    
}
