package br.edu.univas.si.lab4.projeto.model;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class TextDocument extends PlainDocument {  
	private static final long serialVersionUID = 1L;
	   
	private int tamanhoMax;  
	  
	   public TextDocument(int tamanho) {  
	      super();  
	      tamanhoMax = tamanho;  
	   }  
	  
	   public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {  
	   
	       if (str == null) return;  
	            
	       String oldString = getText (0, getLength() );  
	       String newString = oldString.substring(0, offs) + str + oldString.substring(offs);   
	                 
	       if (newString.length() > tamanhoMax) {  
	          super.insertString(offs, "", a);  
	       } else {  
	          super.insertString(offs, str, a);  
	       }  
	               
	   }  
	  
	} 
