/*
 * Recoder.java
 *
 * Created on 9 ������� 2007 �., 22:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.svo.vector2utf;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 *
 * @author svo
 */
public class �������������� {
    /** Creates a new instance of Recoder */
    public ��������������(String inn, String outn, String ���������) {
        ������ = inn;
        ���� = outn;
        
        ������������������(�������.�������().������������������������������(���������));
        ���������������� = �������.�������().��������������������������������(���������);
    }
    

    private void ������������������(char[][] �����) {
        for(int i = 0; i < �����.length; i++) {
            ����.put(�����[i][0], �����[i][1]);
        }
    }
    
    public void ������������() throws IOException {
        InputStreamReader ��� = new InputStreamReader(new FileInputStream(������), ����������������);
        ������ = new BufferedReader(���);
        
        ������ = new PrintWriter(����, "utf-8");
    }
    
    public void ��������() throws IOException {
        String ������;
        
        while ((������ = ������.readLine()) != null) {
            ������.println(�����������������(������));
        }
        System.err.println("��� ���");
    }
    
    public void ��() throws IOException {
        try {
            ������.close();
            ������.close();
        } catch (Exception ex) {
            throw new IOException("�� ������ � ������� ����..");
        }
    }
    
    private String �����������������(String ����) {
        StringBuffer ����� = new StringBuffer();
        for (int i = 0; i < ����.length(); i++) {
            char c = ����.charAt(i);
            char q = ����.containsKey(c) ? ����.get(c) : c;
            �����.append(q);
        }
        
        return �����.toString();
    }
    
    private HashMap<Character,Character> ���� = new HashMap<Character,Character>();
    private String ������, ����;
    private BufferedReader ������ = null;
    private PrintWriter ������ = null;
    private String ���������������� = "cp866";
}

